package at.porscheinformatik.happyrest;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import org.springframework.util.Assert;

/**
 * Defines a generic type. This class is based on Spring's ParameterizedTypeReference.
 *
 * @author ham
 * @param <T> the generic type
 */
public interface GenericType<T>
{

    default Type getType()
    {
        Class<?> parameterizedTypeReferenceSubclass = getClass();
        Type type = parameterizedTypeReferenceSubclass.getGenericSuperclass();

        Assert.isInstanceOf(ParameterizedType.class, type, "Type must be a parameterized type");

        ParameterizedType parameterizedType = (ParameterizedType) type;

        Assert.isTrue(parameterizedType.getActualTypeArguments().length == 1, "Number of type arguments must be 1");

        return parameterizedType.getActualTypeArguments()[0];
    }

}
