package at.porscheinformatik.happyrest;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import org.junit.Test;

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

    @Test
    public void testImplementedByNonGeneric()
    {
        GenericType<CharSequence> genericType = GenericType.of(CharSequence.class).implementedBy(String.class);

        assertThat(genericType.getType(), equalTo(CharSequence.class));
        assertThat(genericType.getArguments().length, is(0));
    }

    @Test
    public void testImplementedBy()
    {
        GenericType<A<?>> genericType = GenericType.of(A.class).implementedBy(D.class);
        Class<?> typeArgument = genericType.getArgument(0);

        assertThat(typeArgument, equalTo(String.class));
    }

    @Test
    public void testInstanceBy()
    {
        D d = new D();
        GenericType<D> genericType = GenericType.of(B.class).instancedBy(d);

        assertThat(genericType.getArgument(0), equalTo(Integer.class));
        assertThat(genericType.getArgument(1), equalTo(String.class));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidInstancedBy()
    {
        C<Double> c = new C<>();
        GenericType<A<?>> genericType = GenericType.of(A.class).instancedBy(c);
        Class<?> typeArgument = genericType.getArguments()[0];

        assertThat(typeArgument, equalTo(Double.class));
    }

    @Test
    public void testOf()
    {
        Class<Object> argument = new GenericType.Of<C<String>>()
        {
        }.getArgument(0);

        System.out.println(argument);
    }

}
