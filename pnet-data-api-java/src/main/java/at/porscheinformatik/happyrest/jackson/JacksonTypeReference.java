package at.porscheinformatik.happyrest.jackson;

import java.lang.reflect.Type;

import com.fasterxml.jackson.core.type.TypeReference;

import at.porscheinformatik.happyrest.GenericType;

/**
 * A wrapper for the {@link TypeReference} ... least hacky as possible.
 *
 * @param <T> the type
 * @author ham
 */
public class JacksonTypeReference<T> extends TypeReference<T>
{

    public static <T> JacksonTypeReference<T> of(GenericType<T> generic)
    {
        return new JacksonTypeReference<>(generic);
    }

    private final GenericType<T> generic;

    public JacksonTypeReference(GenericType<T> generic)
    {
        super();

        this.generic = generic;
    }

    @Override
    public Type getType()
    {
        return generic;
    }

    @Override
    public boolean equals(Object obj)
    {
        return (this == obj || (obj instanceof TypeReference && getType().equals(((TypeReference<?>) obj).getType())));
    }

    @Override
    public int hashCode()
    {
        return getType().hashCode();
    }

    @Override
    public String toString()
    {
        return "GenericTypeReference<" + getType() + ">";
    }

}
