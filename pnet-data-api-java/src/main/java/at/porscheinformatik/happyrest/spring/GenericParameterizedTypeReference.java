package at.porscheinformatik.happyrest.spring;

import at.porscheinformatik.happyrest.GenericType;
import java.lang.reflect.Type;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.lang.Nullable;

/**
 * A wrapper for the {@link ParameterizedTypeReference} ... least hacky as possible.
 *
 * @param <T> the type
 * @author ham
 */
public class GenericParameterizedTypeReference<T> extends ParameterizedTypeReference<T> {

    public static <T> GenericParameterizedTypeReference<T> of(GenericType<T> generic) {
        return new GenericParameterizedTypeReference<>(generic);
    }

    private final GenericType<T> generic;

    public GenericParameterizedTypeReference(GenericType<T> generic) {
        super();
        this.generic = generic;
    }

    @Override
    public Type getType() {
        return generic;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        return (
            this == obj ||
            (obj instanceof ParameterizedTypeReference &&
                getType().equals(((ParameterizedTypeReference<?>) obj).getType()))
        );
    }

    @Override
    public int hashCode() {
        return getType().hashCode();
    }

    @Override
    public String toString() {
        return "ParameterizedTypeReference<" + getType() + ">";
    }
}
