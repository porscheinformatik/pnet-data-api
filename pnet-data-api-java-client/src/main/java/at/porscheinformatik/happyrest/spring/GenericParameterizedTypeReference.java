package at.porscheinformatik.happyrest.spring;

import java.lang.reflect.Type;

import org.springframework.core.ParameterizedTypeReference;

import at.porscheinformatik.happyrest.GenericType;

/**
 * A wrapper for the {@link ParameterizedTypeReference} ... least hacky as possible.
 *
 * @author ham
 * @param <T> the type
 */
public class GenericParameterizedTypeReference<T> extends ParameterizedTypeReference<T>
{

    public static <T, GenericT extends GenericType<T>> GenericParameterizedTypeReference<T> of(GenericType<T> generic)
    {
        return new GenericParameterizedTypeReference<>(generic);
    }

    private final GenericType<T> generic;

    public GenericParameterizedTypeReference(GenericType<T> generic)
    {
        super();

        this.generic = generic;
    }

    @Override
    public Type getType()
    {
        return generic.getType();
    }

    @Override
    public boolean equals(Object obj)
    {
        return (this == obj
            || (obj instanceof ParameterizedTypeReference
                && getType().equals(((ParameterizedTypeReference<?>) obj).getType())));
    }

    @Override
    public int hashCode()
    {
        return getType().hashCode();
    }

    @Override
    public String toString()
    {
        return "ParameterizedTypeReference<" + getType() + ">";
    }

}
