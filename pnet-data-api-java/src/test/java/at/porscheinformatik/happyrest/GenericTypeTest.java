package at.porscheinformatik.happyrest;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.jupiter.api.Test;

import at.porscheinformatik.happyrest.GenericType.Of;

public class GenericTypeTest
{

    public interface A<V>
    {
        V getValue();
    }

    public interface B<K, V> extends A<V>
    {
        K getKey();
    }

    public static class C<V> implements B<Integer, V>
    {
        private final Integer key;
        private final V value;

        public C(Integer key, V value)
        {
            super();

            this.key = key;
            this.value = value;
        }

        @Override
        public Integer getKey()
        {
            return key;
        }

        @Override
        public V getValue()
        {
            return value;
        }
    }

    public static class D extends C<String>
    {
        public D(Integer key, String value)
        {
            super(key, value);
        }

        public String getDescription()
        {
            return null;
        }
    }

    public interface E extends A<List<String>>
    {
        // intentionally left blank
    }

    public abstract class AbstractF<K, V>
    {
        // intentionally left blank
    }

    public abstract class AbstractG<T> extends AbstractF<Integer, A<T>>
    {
        // intentionally left blank
    }

    public class H extends AbstractG<String>
    {
        // intentionally left blank
    }

    /**
     * For testing
     */
    public class ListOfIntegers extends ArrayList<Integer>
    {
        private static final long serialVersionUID = 8852725659573380898L;

        public ListOfIntegers()
        {
            super();
        }

        public ListOfIntegers(Collection<? extends Integer> c)
        {
            super(c);
        }

        public ListOfIntegers(int initialCapacity)
        {
            super(initialCapacity);
        }
    }

    /**
     * For testing
     */
    public class ListOfStrings extends ArrayList<String>
    {
        private static final long serialVersionUID = 8852725659573380898L;

        public ListOfStrings()
        {
            super();
        }

        public ListOfStrings(Collection<? extends String> c)
        {
            super(c);
        }

        public ListOfStrings(int initialCapacity)
        {
            super(initialCapacity);
        }
    }

    @Test
    public void testImplementedByNonGeneric()
    {
        GenericType<CharSequence> genericType = GenericType.build(CharSequence.class).implementedBy(String.class);

        assertThat(genericType.getType(), equalTo(CharSequence.class));
        assertThat(genericType.getNumberOfArguments(), is(0));
        assertThat(genericType.getTypeName(), is("java.lang.CharSequence"));
        assertThat(genericType.getSimpleTypeName(), is("CharSequence"));
        assertThat(genericType.isInstance("Test"), is(true));
        assertThat(genericType.isInstance(1), is(false));
    }

    @Test
    public void testImplementedBy() throws NoSuchFieldException, SecurityException
    {
        GenericType<A<?>> genericType = GenericType.build(A.class).implementedBy(D.class);

        assertThat(genericType.getType(), equalTo(A.class));
        assertThat(genericType.getNumberOfArguments(), is(1));
        assertThat(genericType.getArgumentClass(0), equalTo(String.class));
        assertThat(genericType.getTypeName(), is("at.porscheinformatik.happyrest.GenericTypeTest$A<java.lang.String>"));
        assertThat(genericType.getSimpleTypeName(), is("GenericTypeTest$A<String>"));
        assertThat(genericType.isAssignableFrom(A.class), is(true));
        assertThat(genericType.isAssignableFrom(B.class), is(true));
        assertThat(genericType.isAssignableFrom(GenericType.of(A.class)), is(true));
        assertThat(genericType.isAssignableFrom(GenericType.of(B.class)), is(true));
        assertThat(genericType.isAssignableFrom(GenericType.build(A.class).implementedBy(D.class)), is(true));
        assertThat(genericType.isAssignableFrom(GenericType.build(A.class).with(String.class)), is(true));
        assertThat(genericType.isAssignableFrom(GenericType.build(A.class).with(Integer.class)), is(false));

        GenericType<Object> innerGenericType = genericType.getArgument(0);

        assertThat(innerGenericType.getType(), equalTo(String.class));
        assertThat(innerGenericType.getNumberOfArguments(), is(0));
        assertThat(innerGenericType.getTypeName(), is("java.lang.String"));
        assertThat(innerGenericType.getSimpleTypeName(), is("String"));
    }

    @Test
    public void testTypes() throws NoSuchFieldException, SecurityException, NoSuchMethodException
    {
        GenericType<Object> cClass = GenericType.build(C.class).implementedBy(D.class);
        GenericType<Object> dClass = GenericType.of(D.class);

        Field valueField = C.class.getDeclaredField("value");

        assertThat(GenericType
            .build(valueField.getDeclaringClass())
            .implementedBy(D.class)
            .findArgument(valueField.getGenericType()), is(GenericType.of(String.class)));

        assertThat(cClass.findArgument(valueField.getGenericType()), is(GenericType.of(String.class)));

        Method keyMethod = D.class.getMethod("getKey");

        assertThat(GenericType
            .build(keyMethod.getDeclaringClass())
            .implementedBy(D.class)
            .findArgument(keyMethod.getGenericReturnType()), is(GenericType.of(Integer.class)));

        assertThat(cClass.findArgument(keyMethod.getGenericReturnType()), is(GenericType.of(Integer.class)));

        assertThat(dClass.findArgument(keyMethod.getGenericReturnType()), is(GenericType.of(Integer.class)));

        Method valueMethod = D.class.getMethod("getValue");

        assertThat(GenericType
            .build(valueMethod.getDeclaringClass())
            .implementedBy(D.class)
            .findArgument(valueMethod.getGenericReturnType()), is(GenericType.of(String.class)));

        assertThat(cClass.findArgument(valueMethod.getGenericReturnType()), is(GenericType.of(String.class)));

        Method descriptionMethod = D.class.getMethod("getDescription");

        assertThat(GenericType
            .build(descriptionMethod.getDeclaringClass())
            .implementedBy(D.class)
            .findArgument(descriptionMethod.getGenericReturnType()), is(GenericType.of(String.class)));

        assertThat(dClass.findArgument(descriptionMethod.getGenericReturnType()), is(GenericType.of(String.class)));
    }

    @Test
    public void testInstanceBy()
    {
        D d = new D(1, "value");
        GenericType<D> genericType = GenericType.build(B.class).instancedBy(d);

        assertThat(genericType.getType(), equalTo(B.class));
        assertThat(genericType.getNumberOfArguments(), is(2));
        assertThat(genericType.getArgumentClass(0), equalTo(Integer.class));
        assertThat(genericType.getArgumentClass(1), equalTo(String.class));
        assertThat(genericType.getTypeName(),
            is("at.porscheinformatik.happyrest.GenericTypeTest$B<java.lang.Integer, java.lang.String>"));
        assertThat(genericType.getSimpleTypeName(), is("GenericTypeTest$B<Integer, String>"));
        assertThat(genericType.isInstance(d), is(true));

        GenericType<Object> innerGenericType = genericType.getArgument(0);

        assertThat(innerGenericType.getType(), equalTo(Integer.class));
        assertThat(innerGenericType.getNumberOfArguments(), is(0));
        assertThat(innerGenericType.getTypeName(), is("java.lang.Integer"));
        assertThat(innerGenericType.getSimpleTypeName(), is("Integer"));

        innerGenericType = genericType.getArgument(1);

        assertThat(innerGenericType.getType(), equalTo(String.class));
        assertThat(innerGenericType.getNumberOfArguments(), is(0));
        assertThat(innerGenericType.getTypeName(), is("java.lang.String"));
        assertThat(innerGenericType.getSimpleTypeName(), is("String"));
    }

    @Test
    public void testMissingType()
    {
        C<Double> c = new C<>(1, Math.PI);
        GenericType<A<?>> genericType = GenericType.build(A.class).instancedBy(c);

        assertThat(genericType.getType(), equalTo(A.class));
        assertThat(genericType.getNumberOfArguments(), is(1));
        assertThat(genericType.getArgumentClass(0), nullValue()); // the type information is not available
        assertThat(genericType.getTypeName(), is("at.porscheinformatik.happyrest.GenericTypeTest$A<V>"));
        assertThat(genericType.getSimpleTypeName(), is("GenericTypeTest$A<V>"));

        GenericType<Object> innerGenericType = genericType.getArgument(0);

        assertThat(innerGenericType.getType(), nullValue());
        assertThat(innerGenericType.getNumberOfArguments(), is(0));
        assertThat(innerGenericType.getTypeName(), is("V"));
        assertThat(innerGenericType.getSimpleTypeName(), is("V"));
    }

    @Test
    public void testDoppeltGemoppelt()
    {
        GenericType<A<?>> genericType = GenericType.build(A.class).implementedBy(E.class);

        assertThat(genericType.getType(), equalTo(A.class));
        assertThat(genericType.getNumberOfArguments(), is(1));
        assertThat(genericType.getArgumentClass(0), equalTo(List.class));
        assertThat(genericType.getTypeName(),
            is("at.porscheinformatik.happyrest.GenericTypeTest$A<java.util.List<java.lang.String>>"));
        assertThat(genericType.getSimpleTypeName(), is("GenericTypeTest$A<List<String>>"));

        GenericType<Object> innerGenericType = genericType.getArgument(0);

        assertThat(innerGenericType.getType(), equalTo(List.class));
        assertThat(innerGenericType.getNumberOfArguments(), is(1));
        assertThat(innerGenericType.getArgumentClass(0), equalTo(String.class));
        assertThat(innerGenericType.getTypeName(), is("java.util.List<java.lang.String>"));
        assertThat(innerGenericType.getSimpleTypeName(), is("List<String>"));

        innerGenericType = innerGenericType.getArgument(0);

        assertThat(innerGenericType.getType(), equalTo(String.class));
        assertThat(innerGenericType.getNumberOfArguments(), is(0));
        assertThat(innerGenericType.getTypeName(), is("java.lang.String"));
        assertThat(innerGenericType.getSimpleTypeName(), is("String"));

        GenericType.Of<A<List<String>>> genericTypeOfOf = new GenericType.Of<A<List<String>>>()
        {
            // intentionally left blank
        };

        assertThat(genericType.equals(genericTypeOfOf), is(true));
        assertThat(genericTypeOfOf.equals(genericType), is(true));
        assertThat(genericType.hashCode(), is(genericTypeOfOf.hashCode()));
    }

    @Test
    public void testOfString()
    {
        Of<String> genericType = new Of<String>()
        {
            // intentionally left blank
        };

        assertThat(genericType.getType(), equalTo(String.class));
        assertThat(genericType.getNumberOfArguments(), is(0));
        assertThat(genericType.getTypeName(), is("java.lang.String"));
        assertThat(genericType.getSimpleTypeName(), is("String"));

        assertThat(GenericType.of(String.class).equals(genericType), is(true));
        assertThat(genericType.equals(GenericType.of(String.class)), is(true));
        assertThat(GenericType.of(Integer.class).equals(genericType), is(false));
        assertThat(genericType.equals(GenericType.of(Integer.class)), is(false));

        assertThat(genericType.hashCode(), is(GenericType.of(String.class).hashCode()));
        assertThat(genericType.hashCode(), not(is(GenericType.of(Integer.class).hashCode())));

        assertThat(genericType.isInstance(null), is(true));
        assertThat(genericType.isInstance(1), is(false));
        assertThat(genericType.isInstance("test"), is(true));
        assertThat(genericType.isInstance(new Object()), is(false));
    }

    @Test
    public void testOfObject()
    {
        Of<Object> genericType = new Of<Object>()
        {
            // intentionally left blank
        };

        assertThat(genericType.getType(), equalTo(Object.class));
        assertThat(genericType.getNumberOfArguments(), is(0));
        assertThat(genericType.getTypeName(), is("java.lang.Object"));
        assertThat(genericType.getSimpleTypeName(), is("Object"));

        assertThat(GenericType.of(Object.class).equals(genericType), is(true));
        assertThat(genericType.equals(GenericType.of(Object.class)), is(true));
        assertThat(GenericType.of(Integer.class).equals(genericType), is(false));
        assertThat(genericType.equals(GenericType.of(Integer.class)), is(false));

        assertThat(genericType.hashCode(), is(GenericType.of(Object.class).hashCode()));
        assertThat(genericType.hashCode(), not(is(GenericType.of(Integer.class).hashCode())));

        assertThat(genericType.isInstance(null), is(true));
        assertThat(genericType.isInstance(1), is(true));
        assertThat(genericType.isInstance("test"), is(true));
        assertThat(genericType.isInstance(new Object()), is(true));
    }

    @Test
    public void testDoppeltGemoppeltOf()
    {
        Of<List<String>> genericType = new Of<List<String>>()
        {
            // intentionally left blank
        };

        assertThat(genericType.getType(), equalTo(List.class));
        assertThat(genericType.getNumberOfArguments(), is(1));
        assertThat(genericType.getArgumentClass(0), equalTo(String.class));
        assertThat(genericType.getTypeName(), is("java.util.List<java.lang.String>"));
        assertThat(genericType.getSimpleTypeName(), is("List<String>"));
        assertThat(genericType.isInstance(null), is(true));
        assertThat(genericType.isInstance(new ArrayList<String>()), is(true));
        assertThat(genericType.isInstance(new ArrayList<Integer>()), is(true)); // type info gets lost during compilation
        assertThat(genericType.isInstance(new ListOfStrings()), is(true));
        assertThat(genericType.isInstance(new ListOfIntegers()), is(false));

        GenericType<Object> innerGenericType = genericType.getArgument(0);

        assertThat(innerGenericType.getType(), equalTo(String.class));
        assertThat(innerGenericType.getNumberOfArguments(), is(0));
        assertThat(innerGenericType.getTypeName(), is("java.lang.String"));
        assertThat(innerGenericType.getSimpleTypeName(), is("String"));
    }

    @Test
    public void testSimpleInstanceOf()
    {
        assertThat(GenericType.of(Integer.class).isInstance(1), is(true));
        assertThat(GenericType.of(Number.class).isInstance(1), is(true));
        assertThat(GenericType.of(String.class).isInstance(1), is(false));
        assertThat(GenericType.of(Object.class).isInstance(1), is(true));

        Object obj = new Object();

        assertThat(GenericType.of(Integer.class).isInstance(obj), is(false));
        assertThat(GenericType.of(Number.class).isInstance(obj), is(false));
        assertThat(GenericType.of(String.class).isInstance(obj), is(false));
        assertThat(GenericType.of(Object.class).isInstance(obj), is(true));

        assertThat(GenericType.of(Integer.class).isInstance(null), is(true));
        assertThat(GenericType.of(Number.class).isInstance(null), is(true));
        assertThat(GenericType.of(String.class).isInstance(null), is(true));
        assertThat(GenericType.of(Object.class).isInstance(null), is(true));
    }

    @Test
    public void testInstanceOfKnownType()
    {
        ListOfIntegers nullListOfIntegers = null;
        ListOfStrings nullListOfStrings = null;
        ListOfIntegers emptyListOfIntegers = new ListOfIntegers();
        ListOfStrings emptyListOfStrings = new ListOfStrings();
        ListOfIntegers listOfIntegers = new ListOfIntegers(Arrays.asList(1, 2, 3));
        ListOfStrings listOfStrings = new ListOfStrings(Arrays.asList("one", "two", "three"));

        GenericType<Object> type = GenericType.build(Collection.class).with(Integer.class);

        assertThat(type.isInstance(nullListOfIntegers), is(true));
        assertThat(type.isInstance(nullListOfStrings), is(true)); // no type info available
        assertThat(type.isInstance(emptyListOfIntegers), is(true));
        assertThat(type.isInstance(emptyListOfStrings), is(false));
        assertThat(type.isInstance(listOfIntegers), is(true));
        assertThat(type.isInstance(listOfStrings), is(false));
    }

    @Test
    public void testInstanceOf()
    {
        GenericType<Object> type = GenericType.build(Collection.class).with(Integer.class);
        List<Object> nullList = null;
        List<Integer> emptyIntegerList = new ArrayList<>();
        List<String> emptyStringList = new ArrayList<>();
        List<Integer> integerList = Arrays.asList(1, 2, 3);
        List<String> stringList = Arrays.asList("one", "two", "three");
        List<Integer> typedListWithIntegers = new ListOfIntegers(Arrays.asList(1, 2, 3));
        List<String> typedListWithStrings = new ListOfStrings(Arrays.asList("one", "two", "three"));

        assertThat(type.isInstance(nullList), is(true));
        assertThat(type.isInstance(emptyIntegerList), is(true));
        assertThat(type.isInstance(emptyStringList), is(true)); // type info gets lost during compilation
        assertThat(type.isInstance(integerList), is(true));
        assertThat(type.isInstance(stringList), is(true)); // type info gets lost during compilation
        assertThat(type.isInstance(typedListWithIntegers), is(true));
        assertThat(type.isInstance(typedListWithStrings), is(false));
    }

    @Test
    public void testDoubleInstancedBy()
    {
        GenericType<Object> type = GenericType.build(AbstractF.class).instancedBy(new H());

        assertThat(type.getArgument(0), is(GenericType.of(Integer.class)));
        assertThat(type.getArgument(1), is(GenericType.build(A.class).with(String.class)));
    }

    @Test
    public void testDoubleImplementedBy()
    {
        GenericType<Object> type = GenericType.build(AbstractF.class).implementedBy(H.class);

        assertThat(type.getArgument(0), is(GenericType.of(Integer.class)));
        assertThat(type.getArgument(1), is(GenericType.build(A.class).with(String.class)));
    }

}
