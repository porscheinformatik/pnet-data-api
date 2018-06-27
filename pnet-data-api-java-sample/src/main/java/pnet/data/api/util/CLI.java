package pnet.data.api.util;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.Reader;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * A command line interpreter - parses the input, creates process and executes them. Code borrowed from
 * https://github.com/thred/tiny-console
 *
 * @author Manfred Hantschel
 */
public class CLI
{

    private static final Collator DICTIONARY_COLLATOR;

    static
    {
        DICTIONARY_COLLATOR = Collator.getInstance();

        DICTIONARY_COLLATOR.setStrength(Collator.PRIMARY);
        DICTIONARY_COLLATOR.setDecomposition(Collator.CANONICAL_DECOMPOSITION);
    }

    @Documented
    @Retention(RUNTIME)
    @Target(METHOD)
    public @interface Command
    {

        String name()

        default "";

        String format() default "";

        String description() default "";

    }

    /**
     * A scanner for the shell
     *
     * @author Manfred Hantschel
     */
    protected static class Scanner
    {
        private final Reader reader;

        private int offset = 0;
        private int line = 0;
        private int column = 0;
        private int ch;
        private int nextCh;
        private boolean skipLF = false;
        private boolean lookedAhead = false;

        public Scanner(Reader reader)
        {
            super();

            this.reader = reader;
        }

        public int getOffset()
        {
            return offset;
        }

        public int getLine()
        {
            return line;
        }

        public int getColumn()
        {
            return column;
        }

        public int get()
        {
            return ch;
        }

        public int next() throws IOException
        {
            if (lookedAhead)
            {
                ch = nextCh;
                lookedAhead = false;
            }
            else
            {
                ch = read();
            }

            offset += 1;

            if (ch == '\n')
            {
                line += 1;
                column = 0;
            }

            return ch;
        }

        public int lookAhead() throws IOException
        {
            if (lookedAhead)
            {
                return nextCh;
            }

            nextCh = read();
            lookedAhead = true;

            return nextCh;
        }

        protected int read() throws IOException
        {
            int ch = reader.read();

            if ((ch == '\n') && (skipLF))
            {
                ch = reader.read();
            }

            skipLF = false;

            if (ch == '\r')
            {
                ch = '\n';
                skipLF = true;
            }

            return ch;
        }
    }

    protected static class Token
    {

        public enum Type
        {
            END_OF_FILE,

            END_OF_LINE,

            SEPARATOR,

            STRING,

            COMMAND,
        }

        private final Type type;
        private final int line;
        private final int column;
        private final String value;

        public Token(Type type, int line, int column, String value)
        {
            super();

            this.type = type;
            this.line = line;
            this.column = column;
            this.value = value;
        }

        public Type getType()
        {
            return type;
        }

        public int getLine()
        {
            return line;
        }

        public int getColumn()
        {
            return column;
        }

        public String getValue()
        {
            return value;
        }

    }

    /**
     * A tokenizer for the shell
     *
     * @author Manfred Hantschel
     */
    protected static class Tokenizer
    {
        private final Scanner scanner;

        public Tokenizer(Scanner scanner)
        {
            super();

            this.scanner = scanner;
        }

        public Token read() throws IOException
        {
            int ch;
            int line;
            int column;

            do
            {
                ch = scanner.next();
                line = scanner.getLine();
                column = scanner.getColumn();
            } while (ch == ' ');

            if (ch < 0)
            {
                return new Token(Token.Type.END_OF_FILE, line, column, null);
            }

            if ((ch == '\n') || (ch == ';'))
            {
                return new Token(Token.Type.END_OF_LINE, line, column, String.valueOf((char) ch));
            }

            if (ch == '\'')
            {
                StringBuilder builder = new StringBuilder();

                while (true)
                {
                    ch = scanner.next();

                    if (ch == '\'')
                    {
                        return new Token(Token.Type.STRING, line, column, builder.toString());
                    }

                    builder.append((char) ch);
                }
            }

            if (ch == '\"')
            {
                StringBuilder builder = new StringBuilder();

                while (true)
                {
                    ch = scanner.next();

                    if (ch == '\"')
                    {
                        return new Token(Token.Type.STRING, line, column, builder.toString());
                    }

                    builder.append((char) ch);
                }
            }

            StringBuilder builder = new StringBuilder();

            while (true)
            {
                if (ch == '\\')
                {
                    ch = scanner.next();
                }

                builder.append((char) ch);

                ch = scanner.lookAhead();

                if ((ch == ' ') || (ch == '\n') || (ch == ';'))
                {
                    return new Token(Token.Type.COMMAND, line, column, builder.toString());
                }

                ch = scanner.next();
            }
        }

        protected static boolean isWhitespace(char ch)
        {
            return (ch == ' ') || (ch == '\t') || (ch == '\r') || (ch == '\n');
        }
    }

    protected static class Parser
    {

        private final Tokenizer tokenizer;

        public Parser(Tokenizer tokenizer)
        {
            super();

            this.tokenizer = tokenizer;
        }

        public Arguments parse() throws IOException
        {
            List<String> tokens = new ArrayList<>();

            while (true)
            {
                Token token = tokenizer.read();

                switch (token.getType())
                {
                    case END_OF_FILE:
                        return null;

                    case END_OF_LINE:
                    case SEPARATOR:
                        return new Arguments(tokens);

                    case STRING:
                    case COMMAND:
                        tokens.add(token.getValue());
                        break;
                }
            }
        }
    }

    /**
     * Accessor for arguments. The common concept is, that when consuming an argument, it will be removed.
     *
     * @author Manfred Hantschel
     */
    public static class Arguments implements Iterable<String>, Cloneable
    {

        private final List<String> args;

        public Arguments(String... args)
        {
            this(new ArrayList<>(Arrays.asList(args)));
        }

        public Arguments(List<String> args)
        {
            super();

            this.args = args;
        }

        @Override
        public Arguments clone()
        {
            return new Arguments(new ArrayList<>(args));
        }

        /**
         * Adds an argument at the end of the list of arguments
         *
         * @param argument the argument
         * @return the {@link Arguments} itself
         */
        public Arguments add(String argument)
        {
            return add(args.size(), argument);
        }

        /**
         * Adds an argument at the specified index
         *
         * @param index the index
         * @param argument the argument
         * @return the {@link Arguments} itself
         */
        public Arguments add(int index, String argument)
        {
            args.add(index, argument);

            return this;
        }

        /**
         * Returns true if no argument is available (anymore).
         *
         * @return true if empty
         */
        public boolean isEmpty()
        {
            return args.isEmpty();
        }

        /**
         * Returns the number of (remaining) arguments.
         *
         * @return the number of (remaining) arguments
         */
        public int size()
        {
            return args.size();
        }

        /**
         * Returns the first index of one of the specified arguments.
         *
         * @param keys the arguments
         * @return the first index, -1 if none was found
         */
        public int indexOf(String... keys)
        {
            int index = Integer.MAX_VALUE;

            for (String key : keys)
            {
                int currentIndex = args.indexOf(key);

                if ((currentIndex >= 0) && (currentIndex < index))
                {
                    index = currentIndex;
                }
            }

            return (index < Integer.MAX_VALUE) ? index : -1;
        }

        /**
         * Returns the last index of one of the specified arguments.
         *
         * @param keys the arguments
         * @return the last index, -1 if none was found
         */
        public int lastIndexOf(String... keys)
        {
            int index = -1;

            for (String key : keys)
            {
                int currentIndex = args.lastIndexOf(key);

                if (currentIndex > index)
                {
                    index = currentIndex;
                }
            }

            return index;
        }

        /**
         * {@inheritDoc}
         *
         * @see java.lang.Iterable#iterator()
         */
        @Override
        public Iterator<String> iterator()
        {
            return args.iterator();
        }

        /**
         * Returns a new {@link Arguments} object with all arguments, starting at the current index. The arguments will
         * be removed from the original list of arguments.
         *
         * @return a new {@link Arguments} object
         */
        public Arguments consume()
        {
            return consume(0);
        }

        /**
         * Returns a new {@link Arguments} object with all arguments, starting at the specified index. The arguments
         * will be removed from the original list of arguments.
         *
         * @param startIndex the start index
         * @return a new {@link Arguments} object
         */
        public Arguments consume(int startIndex)
        {
            List<String> result = new ArrayList<>();

            while (startIndex < args.size())
            {
                result.add(args.remove(startIndex));
            }

            return new Arguments(result);
        }

        public Object consume(Class<?> type)
        {
            if (type.isAssignableFrom(String.class))
            {
                return consumeString();
            }

            throw new UnsupportedOperationException("Type not supported: " + type);
        }

        /**
         * Removes and returns the first arguments as string.
         *
         * @return the first argument as string, null if there is no argument
         */
        public String consumeString()
        {
            return consumeString(0);
        }

        /**
         * Removes and returns the argument at the specified index as string.
         *
         * @param index the index
         * @return the argument at the specified index as string, null if the index is out of bounds.
         */
        public String consumeString(int index)
        {
            if (isEmpty())
            {
                return null;
            }

            if (index >= size())
            {
                return null;
            }

            return args.remove(index);
        }

        /**
         * Searches for the specified argument. If found, removes it and returns and removes the next argument.
         *
         * @param key the argument
         * @return the value part (next argument), null if key was not found
         */
        public String consumeString(String key)
        {
            int indexOf = args.indexOf(key);

            if (indexOf < 0)
            {
                return null;
            }

            args.remove(indexOf);

            if (indexOf >= args.size())
            {
                throw new IllegalArgumentException(String.format("Invalid argument: %s. Value is missing.", key));
            }

            return args.remove(indexOf);
        }

        /**
         * Removes and returns the first arguments as long.
         *
         * @return the first argument as long, null if there is no argument
         */
        public Long consumeLong()
        {
            return consumeLong(0);
        }

        /**
         * Removes and returns the argument at the specified index as long.
         *
         * @param index the index
         * @return the argument at the specified index as long, null if the index is out of bounds.
         */
        public Long consumeLong(int index)
        {
            String value = consumeString(index);

            if (value == null)
            {
                return null;
            }

            try
            {
                return Long.decode(value);
            }
            catch (NumberFormatException e)
            {
                throw new IllegalArgumentException(String.format("Invalid number: %s", value));
            }
        }

        /**
         * Searches for the specified argument. If found, removes it and returns and removes the next argument.
         *
         * @param key the argument
         * @return the value part (next argument), null if key was not found
         */
        public Long consumeLong(String key)
        {
            String value = consumeString(key);

            if (value == null)
            {
                return null;
            }

            try
            {
                return Long.decode(value);
            }
            catch (NumberFormatException e)
            {
                throw new IllegalArgumentException(String.format("Invalid number: %s", value));
            }
        }

        /**
         * Removes and returns the first arguments as double.
         *
         * @return the first argument as double, null if there is no argument
         */
        public Double consumeDouble()
        {
            return consumeDouble(0);
        }

        /**
         * Removes and returns the argument at the specified index as double.
         *
         * @param index the index
         * @return the argument at the specified index as double, null if the index is out of bounds.
         */
        public Double consumeDouble(int index)
        {
            String value = consumeString(index);

            if (value == null)
            {
                return null;
            }

            try
            {
                return Double.valueOf(value);
            }
            catch (NumberFormatException e)
            {
                throw new IllegalArgumentException(String.format("Invalid number: %s", value));
            }
        }

        /**
         * Searches for the specified argument. If found, removes it and returns and removes the next argument.
         *
         * @param key the argument
         * @return the value part (next argument), null if key was not found
         */
        public Double consumeDouble(String key)
        {
            String value = consumeString(key);

            if (value == null)
            {
                return null;
            }

            try
            {
                return Double.valueOf(value);
            }
            catch (NumberFormatException e)
            {
                throw new IllegalArgumentException(String.format("Invalid number: %s", value));
            }
        }

        /**
         * Consumes the specified argument.
         *
         * @param flag the argument
         * @return true if the argument was found, false otherwise.
         */
        public boolean consumeFlag(String flag)
        {
            return args.remove(flag);
        }

        /**
         * {@inheritDoc}
         *
         * @see java.lang.Object#toString()
         */
        @Override
        public String toString()
        {
            StringBuilder builder = new StringBuilder();

            for (String arg : args)
            {
                if (builder.length() > 0)
                {
                    builder.append(" ");
                }

                builder.append(arg);
            }

            return builder.toString();
        }
    }

    protected static abstract class Handler implements Comparable<Handler>
    {

        private final String name;
        private final String format;
        private final String description;

        public Handler(String name, String format, String description)
        {
            super();

            this.name = name;
            this.format = format;
            this.description = description;
        }

        public String getName()
        {
            return name;
        }

        public String getFormat()
        {
            return format;
        }

        public String getDescription()
        {
            return description;
        }

        public String describe(int leftLength)
        {
            StringBuilder builder = new StringBuilder(name);

            builder.append(" ");

            if (format != null)
            {
                builder.append(format);
                builder.append(" ");
            }

            while (builder.length() < leftLength)
            {
                builder.append(".");
            }

            builder.append(" ");
            builder.append(description);

            return builder.toString();
        }

        public abstract void handle(Arguments arguments);

        @Override
        public int compareTo(Handler o)
        {
            return DICTIONARY_COLLATOR.compare(name, o.name);
        }
    }

    private final Map<String, Handler> handlers = new HashMap<>();

    protected final InputStream in;
    protected final PrintStream out;
    protected final PrintStream err;

    private final InputStreamReader reader;
    private final Scanner scanner;
    private final Tokenizer tokenizer;
    private final Parser parser;

    public CLI()
    {
        this(System.in, System.out, System.err);
    }

    public CLI(InputStream in, PrintStream out, PrintStream err)
    {
        super();

        this.in = in;
        this.out = out;
        this.err = err;

        reader = new InputStreamReader(in);
        scanner = new Scanner(reader);
        tokenizer = new Tokenizer(scanner);
        parser = new Parser(tokenizer);

        register(this);
    }

    public void register(Object instance)
    {
        Class<?> type = instance.getClass();

        Method[] methods = type.getMethods();

        for (Method method : methods)
        {
            Command command = method.getAnnotation(Command.class);

            if (command != null)
            {
                Class<?>[] parameterTypes = method.getParameterTypes();

                String name = command.name();

                if (name == null || name.length() == 0)
                {
                    name = method.getName();
                }

                String format = command.format();

                if (format == null || format.length() == 0)
                {

                    if (parameterTypes.length > 0)
                    {
                        format =
                            Arrays.stream(parameterTypes).map(Class::getSimpleName).collect(Collectors.joining(" "));
                    }
                }

                String description = command.description();

                register(new Handler(name, format, description)
                {
                    @Override
                    public void handle(Arguments arguments)
                    {
                        Object[] args = new Object[parameterTypes.length];

                        for (int i = 0; i < parameterTypes.length; i++)
                        {
                            args[i] = arguments.consume(parameterTypes[i]);
                        }

                        try
                        {
                            method.invoke(instance, args);
                        }
                        catch (IllegalAccessException e)
                        {
                            e.printStackTrace();
                        }
                        catch (IllegalArgumentException e)
                        {
                            e.printStackTrace();
                        }
                        catch (InvocationTargetException e)
                        {
                            e.printStackTrace();
                        }
                    }
                });
            }
        }
    }

    protected void register(Handler handler)
    {
        handlers.put(handler.getName().toLowerCase(), handler);
    }

    public Arguments consume(String prompt) throws IOException
    {
        if (prompt != null && prompt.length() > 0)
        {
            writeOut("\n%s", prompt);
        }
        else
        {
            writeOut("\n> ");
        }

        return parser.parse();
    }

    public void consumeCommand(String prompt) throws IOException
    {
        Arguments arguments = consume(prompt);

        String command = arguments.consumeString();

        if (command == null)
        {
            return;
        }

        command = command.trim().toLowerCase();

        Handler handler = handlers.get(command);

        if (handler == null)
        {
            warn("Unknown command: %s", command);

            return;
        }

        handler.handle(arguments);
    }

    @CLI.Command(description = "Prints this help.")
    public void help()
    {
        List<Handler> handlers = new ArrayList<>(this.handlers.values());

        Collections.sort(handlers);

        for (Handler handler : handlers)
        {
            info(handler.describe(20));
        }
    }

    @CLI.Command(description = "Exit this program.")
    public void exit()
    {
        info("Good bye.");
        System.exit(0);
    }

    protected void writeOut(String message, Object... args)
    {
        out.printf(message, args);
    }

    protected void writeOut(Throwable ex)
    {
        if (ex != null)
        {
            out.printf("\n");
            ex.printStackTrace(out);
        }
    }

    protected void writeErr(Object message, Object... args)
    {
        err.printf(String.valueOf(message), args);
    }

    protected void writeErr(Throwable ex)
    {
        if (ex != null)
        {
            err.printf("\n");
            ex.printStackTrace(err);
        }
    }

    public void info(Object message, Object... args)
    {
        info(message, (Throwable) null, args);
    }

    public void info(Object message, Throwable ex, Object... args)
    {
        writeOut("\n" + message, args);
        writeOut(ex);
    }

    public void warn(Object message, Object... args)
    {
        warn(message, (Throwable) null, args);
    }

    public void warn(Object message, Throwable ex, Object... args)
    {
        writeErr("\n" + message, args);
        writeErr(ex);
    }

    public void error(Object message, Object... args)
    {
        error(message, (Throwable) null, args);
    }

    public void error(Object message, Throwable ex, Object... args)
    {
        writeErr("\n" + message, args);
        writeErr(ex);
    }
}
