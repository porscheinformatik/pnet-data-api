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
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A command line interpreter - parses the input, creates process and executes them. Code borrowed from
 * https://github.com/thred/tiny-console
 *
 * @author Manfred Hantschel
 */
public class CLI
{

    private static final Logger LOG = LoggerFactory.getLogger(CLI.class);

    private static final Collator DICTIONARY_COLLATOR;

    static
    {
        DICTIONARY_COLLATOR = Collator.getInstance();

        DICTIONARY_COLLATOR.setStrength(Collator.PRIMARY);
        DICTIONARY_COLLATOR.setDecomposition(Collator.CANONICAL_DECOMPOSITION);
    }

    /**
     * A command.
     */
    @Documented
    @Retention(RUNTIME)
    @Target(METHOD)
    public @interface Command
    {

        String[] name() default {};

        String format()

        default "";

        String description() default "";

    }

    /**
     * A scanner for the shell
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
                return readString(line, column, '\'');
            }

            if (ch == '\"')
            {
                return readString(line, column, '\"');
            }

            return readCommand(ch, line, column);
        }

        private Token readString(int line, int column, char delimiter) throws IOException
        {
            StringBuilder builder = new StringBuilder();

            while (true)
            {
                int ch = scanner.next();

                if (ch == delimiter)
                {
                    return new Token(Token.Type.STRING, line, column, builder.toString());
                }

                builder.append((char) ch);
            }
        }

        private Token readCommand(int ch, int line, int column) throws IOException
        {
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

    /**
     * The parser
     */
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
                    default:
                        throw new UnsupportedOperationException("Unsupported token type: " + token.getType());
                }
            }
        }
    }

    /**
     * Accessor for arguments. The common concept is, that when consuming an argument, it will be removed.
     */
    public static class Arguments implements Iterable<String>
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

        public Arguments copy()
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

        @SuppressWarnings("unchecked")
        protected <Any, T extends Enum<T>> Any convert(String value, Class<Any> type)
        {
            if (value == null)
            {
                return null;
            }

            if (String.class.isAssignableFrom(type))
            {
                return (Any) value;
            }

            if (Boolean.class.isAssignableFrom(type))
            {
                return (Any) Boolean.valueOf(Boolean.parseBoolean(value));
            }

            if (Byte.class.isAssignableFrom(type))
            {
                return (Any) Byte.decode(value);
            }

            if (Short.class.isAssignableFrom(type))
            {
                return (Any) Short.decode(value);
            }

            if (Integer.class.isAssignableFrom(type))
            {
                return (Any) Integer.decode(value);
            }

            if (Long.class.isAssignableFrom(type))
            {
                return (Any) Long.decode(value);
            }

            if (Float.class.isAssignableFrom(type))
            {
                return (Any) Float.valueOf(Float.parseFloat(value));
            }

            if (Double.class.isAssignableFrom(type))
            {
                return (Any) Double.valueOf(Double.parseDouble(value));
            }

            if (Character.class.isAssignableFrom(type))
            {
                if (value.length() > 1)
                {
                    throw new IllegalArgumentException("Character expected");
                }

                return (Any) Character.valueOf(value.charAt(0));
            }

            if (Enum.class.isAssignableFrom(type))
            {
                return (Any) Enum.valueOf((Class<T>) type, value);
            }

            throw new UnsupportedOperationException("Type not supported: " + type);
        }

        /**
         * Returns a new {@link Arguments} object with all arguments, starting at the current index. The arguments will
         * be removed from the original list of arguments.
         *
         * @return a new {@link Arguments} object
         */
        public Arguments consumeAll()
        {
            return consumeAll(0);
        }

        /**
         * Returns a new {@link Arguments} object with all arguments, starting at the specified index. The arguments
         * will be removed from the original list of arguments.
         *
         * @param startIndex the start index
         * @return a new {@link Arguments} object
         */
        public Arguments consumeAll(int startIndex)
        {
            List<String> result = new ArrayList<>();

            while (startIndex < args.size())
            {
                result.add(args.remove(startIndex));
            }

            return new Arguments(result);
        }

        public <Any> Optional<Any> consume(Class<Any> type)
        {
            return consume(0, type);
        }

        @SuppressWarnings("unchecked")
        public <Any> Optional<Any> consume(int index, Class<Any> type)
        {
            if (isEmpty())
            {
                return Optional.empty();
            }

            if (index >= size())
            {
                return Optional.empty();
            }

            if (type.isArray())
            {
                return (Optional<Any>) consumeArray(index, type.getComponentType());
            }

            return Optional.of(convert(args.remove(index), type));
        }

        @SuppressWarnings("unchecked")
        public <Any> Optional<Any[]> consumeArray(int index, Class<Any> componentType)
        {
            Optional<Any> value = consume(index, componentType);

            if (!value.isPresent())
            {
                return Optional.empty();
            }

            List<Any> list = new ArrayList<>();

            while (value.isPresent())
            {
                list.add(value.get());
                value = consume(index, componentType);
            }

            return Optional.of(list.<Any> toArray((Any[]) Array.newInstance(componentType, list.size())));
        }

        /**
         * Searches for the specified argument. If found, removes it and returns and removes the next argument.
         *
         * @param <Any> the type of argument to consume
         * @param key the argument
         * @param type the type of the argument
         * @return the value part (next argument), null if key was not found
         */
        public <Any> Optional<Any> consume(String key, Class<Any> type)
        {
            int indexOf = args.indexOf(key);

            if (indexOf < 0)
            {
                return Optional.empty();
            }

            args.remove(indexOf);

            if (indexOf >= args.size())
            {
                throw new IllegalArgumentException(String.format("Invalid argument: %s. Value is missing.", key));
            }

            return consume(indexOf, type);
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

    protected static class Handler implements Comparable<Handler>
    {
        private final List<Handler> subHandlers = new ArrayList<>();

        private final String name;

        private String format;
        private String description;
        private Consumer<Arguments> consumer;

        public Handler(String name)
        {
            super();

            this.name = name;
        }

        public String getName()
        {
            return name;
        }

        public String getFormat()
        {
            return null;
        }

        public String getDescription()
        {
            return null;
        }

        public String getHelp(String prefix, String... qs)
        {
            StringBuilder builder = new StringBuilder(getConsumerHelp(prefix, qs));
            List<Handler> handlers = new ArrayList<>(subHandlers);

            Collections.sort(handlers);

            for (Handler handler : handlers)
            {
                String help = handler
                    .getHelp((prefix != null && prefix.length() > 0 ? prefix + " " : "") + handler.getName(), qs);

                if (help != null && help.length() > 0)
                {
                    if (builder.length() > 0)
                    {
                        builder.append("\n\n");
                    }

                    builder.append(help);
                }
            }

            String result = builder.toString();

            return containsEach(result, qs) ? result : null;
        }

        protected String getConsumerHelp(String prefix, String... qs)
        {
            if (consumer == null)
            {
                return "";
            }

            StringBuilder builder = new StringBuilder(prefix);

            if (consumer != null)
            {
                if (format != null)
                {
                    builder.append(" ");
                    builder.append(format);
                }

                builder.append("\n\t");
                builder.append(description.replace("\n", "\n\t"));
            }

            String result = builder.toString();

            return containsEach(result, qs) ? result : "";
        }

        public void register(Object instance)
        {
            Class<?> type = instance.getClass();

            Method[] methods = type.getMethods();

            for (Method method : methods)
            {
                register(instance, method);
            }
        }

        private void register(Object instance, Method method)
        {
            Command command = method.getAnnotation(Command.class);

            if (command != null)
            {
                Class<?>[] parameterTypes = method.getParameterTypes();

                String[] names = command.name();

                if (names == null || names.length == 0)
                {
                    names = new String[]{method.getName()};
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

                register(instance, method, parameterTypes, names, format, description);
            }
        }

        protected void register(Object instance, Method method, Class<?>[] parameterTypes, String[] names,
            String format, String description)
        {
            for (String name : names)
            {
                register(instance, method, parameterTypes, name, format, description);
            }
        }

        protected void register(Object instance, Method method, Class<?>[] parameterTypes, String name, String format,
            String description)
        {
            if (name == null)
            {
                this.format = format;
                this.description = description;

                consumer = arguments -> {
                    Object[] args = new Object[parameterTypes.length];

                    for (int i = 0; i < parameterTypes.length; i++)
                    {
                        args[i] = arguments.consume(parameterTypes[i]).orElse(null);
                    }

                    try
                    {
                        method.invoke(instance, args);
                    }
                    catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e)
                    {
                        LOG.error("Invocation failed", e);
                    }
                };
            }
            else
            {
                String command = null;

                name = name.trim();

                int index = name.indexOf(' ');

                if (index >= 0)
                {
                    command = name.substring(index);
                    name = name.substring(0, index);
                }

                Handler handler = find(name).orElse(null);

                if (handler == null)
                {
                    handler = register(new Handler(name));
                }

                handler.register(instance, method, parameterTypes, command, format, description);
            }
        }

        protected <AnyHandler extends Handler> AnyHandler register(AnyHandler handler)
        {
            subHandlers.add(handler);

            return handler;
        }

        protected Optional<Handler> find(String name)
        {
            String simplifiedName = simplify(name);

            return subHandlers
                .stream()
                .filter(handler -> Objects.equals(simplifiedName, simplify(handler.getName())))
                .findFirst();
        }

        public void handle(Arguments arguments)
        {
            Arguments argumentsCopy = arguments.copy();
            Optional<String> optionalCommand = argumentsCopy.consume(String.class);

            if (optionalCommand.isPresent())
            {
                String command = optionalCommand.get().trim();

                Optional<Handler> optionalHandler = find(command);

                if (optionalHandler.isPresent())
                {
                    optionalHandler.get().handle(argumentsCopy);

                    return;
                }
            }

            if (consumer != null)
            {
                consumer.accept(arguments);

                return;
            }

            throw new IllegalArgumentException("Invalid command.");
        }

        @Override
        public String toString()
        {
            return simplify(name);
        }

        @Override
        public int compareTo(Handler o)
        {
            return DICTIONARY_COLLATOR.compare(getName(), o.getName());
        }
    }

    private final Handler handler = new Handler(null);

    protected final InputStream in;
    protected final PrintStream out;
    protected final PrintStream err;

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

        InputStreamReader reader = new InputStreamReader(in);
        Scanner scanner = new Scanner(reader);
        Tokenizer tokenizer = new Tokenizer(scanner);

        parser = new Parser(tokenizer);

        register(this);
    }

    public void register(Object instance)
    {
        handler.register(instance);
    }

    public Arguments consume(String prompt)
    {
        if (prompt != null && prompt.length() > 0)
        {
            writeOut("\n%s", prompt);
        }
        else
        {
            writeOut("\n> ");
        }

        try
        {
            return parser.parse();
        }
        catch (IOException e)
        {
            throw new IllegalStateException("Failed to read command", e);
        }
    }

    public boolean consumeCommand(String prompt)
    {
        Arguments command = consume(prompt);

        if (command == null)
        {
            return false;
        }

        handler.handle(command);

        return true;
    }

    @CLI.Command(name = {"help", "?"}, format = "[q]", description = "Prints this help.")
    public void help(String... qs)
    {
        String help = handler.getHelp("", qs);

        if (help.length() == 0)
        {
            info("No help found for: %s", Arrays.stream(qs).collect(Collectors.joining(" ")));
        }
        else
        {
            info(help);
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
        if (args == null || args.length == 0)
        {
            out.print(message);
        }
        else
        {
            out.printf(message, args);
        }
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
            ex.printStackTrace(err);
            err.printf("\n");
        }
    }

    public void info(Object message, Object... args)
    {
        info(message, (Throwable) null, args);
    }

    public void info(Object message, Throwable ex, Object... args)
    {
        writeOut(message + "\n", args);
        writeOut(ex);
    }

    public void warn(Object message, Object... args)
    {
        warn(message, (Throwable) null, args);
    }

    public void warn(Object message, Throwable ex, Object... args)
    {
        writeErr(message + "\n", args);
        writeErr(ex);
    }

    public void error(Object message, Object... args)
    {
        error(message, (Throwable) null, args);
    }

    public void error(Object message, Throwable ex, Object... args)
    {
        writeErr(message + "\n", args);
        writeErr(ex);
    }

    private static String simplify(String s)
    {
        //        return s.replaceAll("[^\\p{IsLatin}^\\d]", "").toLowerCase();
        return s.toLowerCase();
    }

    private static boolean containsEach(String s, String... qs)
    {
        if (qs == null || qs.length == 0)
        {
            return true;
        }

        s = s.toLowerCase();

        for (String q : qs)
        {
            if (!s.contains(q.toLowerCase()))
            {
                return false;
            }
        }

        return true;
    }

}
