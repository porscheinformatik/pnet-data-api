package at.porscheinformatik.happyrest;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import java.util.List;

import org.junit.Test;

import at.porscheinformatik.happyrest.GenericType.Of;

/**
 * <pre>
 *           ___
 *         O(o o)O
 *           (_)     - Ook!
 * </pre>
 */
public class GenericTypeTest
{

    private interface A<U>
    {
    }

    private interface B<S, T> extends A<T>
    {
    }

    private class C<U> implements B<Integer, U>
    {
    }

    private class D extends C<String>
    {
    }

    private interface E extends A<List<String>>
    {
    }

    @Test
    public void testImplementedByNonGeneric()
    {
        GenericType<CharSequence> genericType = GenericType.build(CharSequence.class).implementedBy(String.class);

        assertThat(genericType.getType(), equalTo(CharSequence.class));
        assertThat(genericType.getNumberOfArguments(), is(0));
        assertThat(genericType.getTypeName(), is("java.lang.CharSequence"));
    }

    @Test
    public void testImplementedBy()
    {
        GenericType<A<?>> genericType = GenericType.build(A.class).implementedBy(D.class);

        assertThat(genericType.getType(), equalTo(A.class));
        assertThat(genericType.getNumberOfArguments(), is(1));
        assertThat(genericType.getArgumentClass(0), equalTo(String.class));
        assertThat(genericType.getTypeName(), is("at.porscheinformatik.happyrest.GenericTypeTest$A<java.lang.String>"));

        GenericType<Object> innerGenericType = genericType.getArgument(0);

        assertThat(innerGenericType.getType(), equalTo(String.class));
        assertThat(innerGenericType.getNumberOfArguments(), is(0));
        assertThat(innerGenericType.getTypeName(), is("java.lang.String"));
    }

    @Test
    public void testInstanceBy()
    {
        D d = new D();
        GenericType<D> genericType = GenericType.build(B.class).instancedBy(d);

        assertThat(genericType.getType(), equalTo(B.class));
        assertThat(genericType.getNumberOfArguments(), is(2));
        assertThat(genericType.getArgumentClass(0), equalTo(Integer.class));
        assertThat(genericType.getArgumentClass(1), equalTo(String.class));
        assertThat(genericType.getTypeName(),
            is("at.porscheinformatik.happyrest.GenericTypeTest$B<java.lang.Integer, java.lang.String>"));

        GenericType<Object> innerGenericType = genericType.getArgument(0);

        assertThat(innerGenericType.getType(), equalTo(Integer.class));
        assertThat(innerGenericType.getNumberOfArguments(), is(0));
        assertThat(innerGenericType.getTypeName(), is("java.lang.Integer"));

        innerGenericType = genericType.getArgument(1);

        assertThat(innerGenericType.getType(), equalTo(String.class));
        assertThat(innerGenericType.getNumberOfArguments(), is(0));
        assertThat(innerGenericType.getTypeName(), is("java.lang.String"));
    }

    @Test
    public void testMissingType()
    {
        C<Double> c = new C<>();
        GenericType<A<?>> genericType = GenericType.build(A.class).instancedBy(c);

        assertThat(genericType.getType(), equalTo(A.class));
        assertThat(genericType.getNumberOfArguments(), is(1));
        assertThat(genericType.getArgumentClass(0), nullValue()); // the type information is not available
        assertThat(genericType.getTypeName(), is("at.porscheinformatik.happyrest.GenericTypeTest$A<U>"));

        GenericType<Object> innerGenericType = genericType.getArgument(0);

        assertThat(innerGenericType.getType(), nullValue());
        assertThat(innerGenericType.getNumberOfArguments(), is(0));
        assertThat(innerGenericType.getTypeName(), is("U"));
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

        GenericType<Object> innerGenericType = genericType.getArgument(0);

        assertThat(innerGenericType.getType(), equalTo(List.class));
        assertThat(innerGenericType.getNumberOfArguments(), is(1));
        assertThat(innerGenericType.getArgumentClass(0), equalTo(String.class));
        assertThat(innerGenericType.getTypeName(), is("java.util.List<java.lang.String>"));

        innerGenericType = innerGenericType.getArgument(0);

        assertThat(innerGenericType.getType(), equalTo(String.class));
        assertThat(innerGenericType.getNumberOfArguments(), is(0));
        assertThat(innerGenericType.getTypeName(), is("java.lang.String"));
    }

    @Test
    public void testOf()
    {
        Of<String> genericType = new Of<String>()
        {
        };

        assertThat(genericType.getType(), equalTo(String.class));
        assertThat(genericType.getNumberOfArguments(), is(0));
        assertThat(genericType.getTypeName(), is("java.lang.String"));
    }

    @Test
    public void testDoppeltGemoppeltOf()
    {
        Of<List<String>> genericType = new Of<List<String>>()
        {
        };

        assertThat(genericType.getType(), equalTo(List.class));
        assertThat(genericType.getNumberOfArguments(), is(1));
        assertThat(genericType.getArgumentClass(0), equalTo(String.class));
        assertThat(genericType.getTypeName(), is("java.util.List<java.lang.String>"));

        GenericType<Object> innerGenericType = genericType.getArgument(0);

        assertThat(innerGenericType.getType(), equalTo(String.class));
        assertThat(innerGenericType.getNumberOfArguments(), is(0));
        assertThat(innerGenericType.getTypeName(), is("java.lang.String"));

    }

}
