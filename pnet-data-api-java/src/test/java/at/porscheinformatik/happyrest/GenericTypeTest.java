package at.porscheinformatik.happyrest;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import at.porscheinformatik.happyrest.GenericType.Of;

public class GenericTypeTest
{

    public interface A<V>
    {
        Optional<V> getValue();
    }

    public interface B<K, V> extends A<V>
    {
        K getKey();
    }

    public static class C<V> implements B<Integer, V>
    {
        private final Integer key;
        private final Optional<V> value;

        public C(Integer key, Optional<V> value)
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
        public Optional<V> getValue()
        {
            return value;
        }
    }

    public static class D extends C<String>
    {
        public D(Integer key, Optional<String> value)
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

    public abstract static class AbstractF<K, V>
    {
        // intentionally left blank
    }

    public abstract static class AbstractG<T> extends AbstractF<Integer, A<T>>
    {
        // intentionally left blank
    }

    public static class H extends AbstractG<String>
    {
        // intentionally left blank
    }

    public static class I extends D
    {
        public I(Integer key, Optional<String> value)
        {
            super(key, value);
        }
    }

    public static class AbstractClassWithInnerType<T>
    {
        public class InnerType
        {
            public T getT()
            {
                return null;
            }
        }
    }

    public static class ConcreteClassWithInheritedInnerType extends AbstractClassWithInnerType<Byte>
    {
        // intentionally left blank
    }

    /**
     * For testing
     */
    public static class ListOfIntegers extends ArrayList<Integer>
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
    public static class ListOfStrings extends ArrayList<String>
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
        GenericType<CharSequence> genericJavaType = GenericType.build(CharSequence.class).implementedBy(String.class);

        assertThat(genericJavaType.getType(), equalTo(CharSequence.class));
        assertThat(genericJavaType.getNumberOfArguments(), is(0));
        assertThat(genericJavaType.getTypeName(), is("java.lang.CharSequence"));
        assertThat(genericJavaType.getSimpleTypeName(), is("CharSequence"));
        assertThat(genericJavaType.isInstance("Test"), is(true));
        assertThat(genericJavaType.isInstance(1), is(false));
    }

    @Test
    public void testImplementedBy() throws NoSuchFieldException, SecurityException
    {
        GenericType<A<?>> genericJavaType = GenericType.build(A.class).implementedBy(D.class);

        assertThat(genericJavaType.getType(), equalTo(A.class));
        assertThat(genericJavaType.getNumberOfArguments(), is(1));
        assertThat(genericJavaType.getArgumentClass(0), equalTo(String.class));
        assertThat(genericJavaType.getTypeName(), endsWith("GenericTypeTest$A<java.lang.String>"));
        assertThat(genericJavaType.getSimpleTypeName(), is("GenericTypeTest$A<String>"));
        assertThat(genericJavaType.isAssignableFrom(A.class), is(true));
        assertThat(genericJavaType.isAssignableFrom(B.class), is(true));
        assertThat(genericJavaType.isAssignableFrom(GenericType.of(A.class)), is(true));
        assertThat(genericJavaType.isAssignableFrom(GenericType.of(B.class)), is(true));
        assertThat(genericJavaType.isAssignableFrom(GenericType.build(A.class).implementedBy(D.class)), is(true));
        assertThat(genericJavaType.isAssignableFrom(GenericType.build(A.class).with(String.class)), is(true));
        assertThat(genericJavaType.isAssignableFrom(GenericType.build(A.class).with(Integer.class)), is(false));

        GenericType<Object> innerGenericJavaType = genericJavaType.getArgument(0);

        assertThat(innerGenericJavaType.getType(), equalTo(String.class));
        assertThat(innerGenericJavaType.getNumberOfArguments(), is(0));
        assertThat(innerGenericJavaType.getTypeName(), is("java.lang.String"));
        assertThat(innerGenericJavaType.getSimpleTypeName(), is("String"));
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
            .resolve(valueField.getGenericType()), is(GenericType.build(Optional.class).with(String.class)));

        assertThat(cClass.resolve(valueField.getGenericType()),
            is(GenericType.build(Optional.class).with(String.class)));

        Method keyMethod = D.class.getMethod("getKey");

        assertThat(GenericType
            .build(keyMethod.getDeclaringClass())
            .implementedBy(D.class)
            .resolve(keyMethod.getGenericReturnType()), is(GenericType.INTEGER));

        assertThat(cClass.resolve(keyMethod.getGenericReturnType()), is(GenericType.INTEGER));

        assertThat(dClass.resolve(keyMethod.getGenericReturnType()), is(GenericType.INTEGER));

        Method valueMethod = D.class.getMethod("getValue");

        assertThat(GenericType
            .build(valueMethod.getDeclaringClass())
            .implementedBy(D.class)
            .resolve(valueMethod.getGenericReturnType()), is(GenericType.build(Optional.class).with(String.class)));

        assertThat(cClass.resolve(valueMethod.getGenericReturnType()),
            is(GenericType.build(Optional.class).with(String.class)));

        Method descriptionMethod = D.class.getMethod("getDescription");

        assertThat(GenericType
            .build(descriptionMethod.getDeclaringClass())
            .implementedBy(D.class)
            .resolve(descriptionMethod.getGenericReturnType()), is(GenericType.STRING));

        assertThat(dClass.resolve(descriptionMethod.getGenericReturnType()), is(GenericType.STRING));
    }

    @Test
    public void testInstanceBy()
    {
        D d = new D(1, Optional.of("value"));
        GenericType<D> genericJavaType = GenericType.build(B.class).instancedBy(d);

        assertThat(genericJavaType.getType(), equalTo(B.class));
        assertThat(genericJavaType.getNumberOfArguments(), is(2));
        assertThat(genericJavaType.getArgumentClass(0), equalTo(Integer.class));
        assertThat(genericJavaType.getArgumentClass(1), equalTo(String.class));
        assertThat(genericJavaType.getTypeName(), endsWith("GenericTypeTest$B<java.lang.Integer, java.lang.String>"));
        assertThat(genericJavaType.getSimpleTypeName(), is("GenericTypeTest$B<Integer, String>"));
        assertThat(genericJavaType.isInstance(d), is(true));

        GenericType<Object> innerGenericJavaType = genericJavaType.getArgument(0);

        assertThat(innerGenericJavaType.getType(), equalTo(Integer.class));
        assertThat(innerGenericJavaType.getNumberOfArguments(), is(0));
        assertThat(innerGenericJavaType.getTypeName(), is("java.lang.Integer"));
        assertThat(innerGenericJavaType.getSimpleTypeName(), is("Integer"));

        innerGenericJavaType = genericJavaType.getArgument(1);

        assertThat(innerGenericJavaType.getType(), equalTo(String.class));
        assertThat(innerGenericJavaType.getNumberOfArguments(), is(0));
        assertThat(innerGenericJavaType.getTypeName(), is("java.lang.String"));
        assertThat(innerGenericJavaType.getSimpleTypeName(), is("String"));
    }

    @Test
    public void testMissingType()
    {
        C<Double> c = new C<>(1, Optional.of(Math.PI));
        GenericType<A<?>> genericJavaType = GenericType.build(A.class).instancedBy(c);

        assertThat(genericJavaType.getType(), equalTo(A.class));
        assertThat(genericJavaType.getNumberOfArguments(), is(1));
        assertThat(genericJavaType.getArgumentClass(0), nullValue()); // the type information is not available
        assertThat(genericJavaType.getTypeName(), endsWith("GenericTypeTest$A<V>"));
        assertThat(genericJavaType.getSimpleTypeName(), is("GenericTypeTest$A<V>"));

        GenericType<Object> innerGenericJavaType = genericJavaType.getArgument(0);

        assertThat(innerGenericJavaType.getType(), nullValue());
        assertThat(innerGenericJavaType.getNumberOfArguments(), is(0));
        assertThat(innerGenericJavaType.getTypeName(), is("V"));
        assertThat(innerGenericJavaType.getSimpleTypeName(), is("V"));
    }

    @Test
    public void testDoppeltGemoppelt()
    {
        GenericType<A<?>> genericJavaType = GenericType.build(A.class).implementedBy(E.class);

        assertThat(genericJavaType.getType(), equalTo(A.class));
        assertThat(genericJavaType.getNumberOfArguments(), is(1));
        assertThat(genericJavaType.getArgumentClass(0), equalTo(List.class));
        assertThat(genericJavaType.getTypeName(), endsWith("GenericTypeTest$A<java.util.List<java.lang.String>>"));
        assertThat(genericJavaType.getSimpleTypeName(), is("GenericTypeTest$A<List<String>>"));

        GenericType<Object> innerGenericJavaType = genericJavaType.getArgument(0);

        assertThat(innerGenericJavaType.getType(), equalTo(List.class));
        assertThat(innerGenericJavaType.getNumberOfArguments(), is(1));
        assertThat(innerGenericJavaType.getArgumentClass(0), equalTo(String.class));
        assertThat(innerGenericJavaType.getTypeName(), is("java.util.List<java.lang.String>"));
        assertThat(innerGenericJavaType.getSimpleTypeName(), is("List<String>"));

        innerGenericJavaType = innerGenericJavaType.getArgument(0);

        assertThat(innerGenericJavaType.getType(), equalTo(String.class));
        assertThat(innerGenericJavaType.getNumberOfArguments(), is(0));
        assertThat(innerGenericJavaType.getTypeName(), is("java.lang.String"));
        assertThat(innerGenericJavaType.getSimpleTypeName(), is("String"));

        GenericType.Of<A<List<String>>> GenericJavaTypeOfOf = new GenericType.Of<>()
        {
            // intentionally left blank
        };

        assertThat(genericJavaType.equals(GenericJavaTypeOfOf), is(true));
        assertThat(GenericJavaTypeOfOf.equals(genericJavaType), is(true));
        assertThat(genericJavaType.hashCode(), is(GenericJavaTypeOfOf.hashCode()));
    }

    @Test
    public void testOfString()
    {
        Of<String> type = new Of<>()
        {
            // intentionally left blank
        };

        assertThat(type.getType(), equalTo(String.class));
        assertThat(type.getNumberOfArguments(), is(0));
        assertThat(type.getTypeName(), is("java.lang.String"));
        assertThat(type.getSimpleTypeName(), is("String"));

        assertThat(GenericType.STRING.equals(type), is(true));
        assertThat(type.equals(GenericType.STRING), is(true));
        assertThat(GenericType.INTEGER.equals(type), is(false));
        assertThat(type.equals(GenericType.INTEGER), is(false));

        assertThat(type.hashCode(), is(GenericType.STRING.hashCode()));
        assertThat(type.hashCode(), not(is(GenericType.INTEGER.hashCode())));

        assertThat(type.isInstance(null), is(true));
        assertThat(type.isInstance(1), is(false));
        assertThat(type.isInstance("test"), is(true));
        assertThat(type.isInstance(new Object()), is(false));
    }

    @Test
    public void testOfObject()
    {
        Of<Object> type = new Of<>()
        {
            // intentionally left blank
        };

        assertThat(type.getType(), equalTo(Object.class));
        assertThat(type.getNumberOfArguments(), is(0));
        assertThat(type.getTypeName(), is("java.lang.Object"));
        assertThat(type.getSimpleTypeName(), is("Object"));

        assertThat(GenericType.OBJECT.equals(type), is(true));
        assertThat(type.equals(GenericType.OBJECT), is(true));
        assertThat(GenericType.INTEGER.equals(type), is(false));
        assertThat(type.equals(GenericType.INTEGER), is(false));

        assertThat(type.hashCode(), is(GenericType.OBJECT.hashCode()));
        assertThat(type.hashCode(), not(is(GenericType.INTEGER.hashCode())));

        assertThat(type.isInstance(null), is(true));
        assertThat(type.isInstance(1), is(true));
        assertThat(type.isInstance("test"), is(true));
        assertThat(type.isInstance(new Object()), is(true));
    }

    @Test
    public void testDoppeltGemoppeltOf()
    {
        Of<List<String>> genericJavaType = new Of<>()
        {
            // intentionally left blank
        };

        assertThat(genericJavaType.getType(), equalTo(List.class));
        assertThat(genericJavaType.getNumberOfArguments(), is(1));
        assertThat(genericJavaType.getArgumentClass(0), equalTo(String.class));
        assertThat(genericJavaType.getTypeName(), is("java.util.List<java.lang.String>"));
        assertThat(genericJavaType.getSimpleTypeName(), is("List<String>"));
        assertThat(genericJavaType.isInstance(null), is(true));
        assertThat(genericJavaType.isInstance(new ArrayList<>()), is(true));
        assertThat(genericJavaType.isInstance(new ArrayList<>()), is(true)); // type info gets lost during compilation
        assertThat(genericJavaType.isInstance(new ListOfStrings()), is(true));
        assertThat(genericJavaType.isInstance(new ListOfIntegers()), is(false));

        GenericType<Object> innerGenericJavaType = genericJavaType.getArgument(0);

        assertThat(innerGenericJavaType.getType(), equalTo(String.class));
        assertThat(innerGenericJavaType.getNumberOfArguments(), is(0));
        assertThat(innerGenericJavaType.getTypeName(), is("java.lang.String"));
        assertThat(innerGenericJavaType.getSimpleTypeName(), is("String"));
    }

    @Test
    public void testSimpleInstanceOf()
    {
        assertThat(GenericType.INTEGER.isInstance(1), is(true));
        assertThat(GenericType.NUMBER.isInstance(1), is(true));
        assertThat(GenericType.STRING.isInstance(1), is(false));
        assertThat(GenericType.OBJECT.isInstance(1), is(true));

        Object obj = new Object();

        assertThat(GenericType.INTEGER.isInstance(obj), is(false));
        assertThat(GenericType.NUMBER.isInstance(obj), is(false));
        assertThat(GenericType.STRING.isInstance(obj), is(false));
        assertThat(GenericType.OBJECT.isInstance(obj), is(true));

        assertThat(GenericType.INTEGER.isInstance(null), is(true));
        assertThat(GenericType.NUMBER.isInstance(null), is(true));
        assertThat(GenericType.STRING.isInstance(null), is(true));
        assertThat(GenericType.OBJECT.isInstance(null), is(true));
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
        assertThat(GenericType.STRING.isInstance(null), is(true));
        assertThat(GenericType.STRING.isInstance("string"), is(true));
        assertThat(GenericType.STRING.isInstance(1), is(false));
    }

    @Test
    public void testInstanceOfList()
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

        assertThat(type.getArgument(0), is(GenericType.INTEGER));
        assertThat(type.getArgument(1), is(GenericType.build(A.class).with(String.class)));
    }

    @Test
    public void testDoubleImplementedBy()
    {
        GenericType<Object> type = GenericType.build(AbstractF.class).implementedBy(H.class);

        assertThat(type.getArgument(0), is(GenericType.INTEGER));
        assertThat(type.getArgument(1), is(GenericType.build(A.class).with(String.class)));
    }

    @Test
    public void testImplementedByGenericJavaType()
    {
        GenericType<Object> type = GenericType.build(A.class).implementedBy(GenericType.of(E.class));

        assertThat(type.getArgument(0), is(GenericType.build(List.class).with(String.class)));
    }

    @Test
    public void testImplementedBySelfGenericJavaType()
    {
        GenericType<Object> type =
            GenericType.build(A.class).implementedBy(GenericType.build(A.class).with(String.class));

        assertThat(type.getArgument(0), is(GenericType.STRING));
    }

    @Test
    public void testNonGenericBuild()
    {
        assertThat(GenericType.build(String.class).with(), is(GenericType.STRING));
    }

    @Test
    public void testInvalidNumberOfParameters()
    {
        assertThrows(IllegalArgumentException.class, () -> GenericType.build(B.class).with());
        assertThrows(IllegalArgumentException.class, () -> GenericType.build(B.class).with(String.class));
        assertThrows(IllegalArgumentException.class,
            () -> GenericType.build(B.class).with(String.class, String.class, String.class));
    }

    @Test
    public void testComponentTypeOfIntArray()
    {
        int[] array = new int[0];
        GenericType<int[]> type = GenericType.of(array.getClass());

        assertThat(type.getComponentType(), is(GenericType.INTEGER));
    }

    @Test
    public void testComponentTypeOfIntegerArray()
    {
        Integer[] array = new Integer[0];
        GenericType<Integer[]> type = GenericType.of(array.getClass());

        assertThat(type.getComponentType(), is(GenericType.INTEGER));
    }
}