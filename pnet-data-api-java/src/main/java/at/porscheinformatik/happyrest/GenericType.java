package at.porscheinformatik.happyrest;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Objects;

/**
 * Defines a generic, parameterized type for info at runtime. <br>
 * <br>
 * If you need <code>List&lt;String&gt;</code> as type, just type
 * <code>GenericType.of(List.class, String.class)</code><br>
 * <br>
 * Or you can used an anonymous subtype: <code>new GenericType.Of&lt;List&lt;String&gt;&gt;(){}</code><br>
 * <br>
 *
 * @author ham
 * @param <T> the generic type
 */
public interface GenericType<T> extends ParameterizedType
{

    /**
     * Create a generic type by subtyping it.
     *
     * @author ham
     * @param <S> the generic type
     */
    class Of<S> implements GenericType<S>
    {
        private final ParameterizedType type;

        public Of()
        {
            Class<?> parameterizedTypeReferenceSubclass = findOf(getClass());
            Type type = parameterizedTypeReferenceSubclass.getGenericSuperclass();

            if (!(type instanceof ParameterizedType))
            {
                throw new IllegalArgumentException("Of must be parameterized");
            }

            type = ((ParameterizedType) type).getActualTypeArguments()[0];

            if (!(type instanceof ParameterizedType))
            {
                throw new IllegalArgumentException("Type must be parameterized");
            }

            this.type = (ParameterizedType) type;
        }

        @Override
        public Type[] getActualTypeArguments()
        {
            return type.getActualTypeArguments();
        }

        @Override
        public Type getRawType()
        {
            return type.getRawType();
        }

        @Override
        public Type getOwnerType()
        {
            return type.getOwnerType();
        }

        @Override
        public int hashCode()
        {
            final int prime = 31;
            int result = 1;

            result = prime * result + ((type == null) ? 0 : type.hashCode());

            return result;
        }

        @Override
        public boolean equals(Object obj)
        {
            if (this == obj)
            {
                return true;
            }

            if (obj == null)
            {
                return false;
            }

            if (!(obj instanceof Of))
            {
                return false;
            }

            Of<?> other = (Of<?>) obj;

            if (type == null)
            {
                if (other.type != null)
                {
                    return false;
                }
            }
            else if (!type.equals(other.type))
            {
                return false;
            }

            return true;
        }

        @Override
        public String toString()
        {
            return type.toString();
        }

        private static Class<?> findOf(Class<?> child)
        {
            Class<?> parent = child.getSuperclass();

            if (Object.class == parent)
            {
                throw new IllegalStateException("Expected " + Of.class + " as superclass");
            }
            else if (Of.class == parent)
            {
                return child;
            }
            else
            {
                return findOf(parent);
            }
        }

    }

    static <T> GenericType<T> of(Class<T> rawType, Type... typeArguments)
    {
        return subtypeOf(null, rawType, typeArguments);
    }

    static <T> GenericType<T> subtypeOf(Type ownerType, Class<?> rawType, Type... typeArguments)
    {
        Objects.requireNonNull(rawType, "RawType is null");

        if (rawType.getTypeParameters().length != typeArguments.length)
        {
            throw new IllegalArgumentException("Invalid number of type arguments");
        }

        for (Type type : typeArguments)
        {
            if (Void.TYPE == type
                || Boolean.TYPE == type
                || Byte.TYPE == type
                || Short.TYPE == type
                || Character.TYPE == type
                || Integer.TYPE == type
                || Long.TYPE == type
                || Float.TYPE == type
                || Double.TYPE == type)
            {
                throw new IllegalArgumentException("Primitive type is not allowed");
            }
        }

        return new GenericType<T>()//
        {

            @Override
            public Type[] getActualTypeArguments()
            {
                return typeArguments;
            }

            @Override
            public Type getRawType()
            {
                return rawType;
            }

            @Override
            public Type getOwnerType()
            {
                return ownerType;
            }

            @Override
            public String toString()
            {
                StringBuilder builder = new StringBuilder();

                builder.append(rawType.getName()).append('<');

                Arrays.stream(typeArguments).forEach(builder::append);

                return builder.append('>').toString();
            }

            @Override
            public boolean equals(Object other)
            {
                if (!(other instanceof ParameterizedType))
                {
                    return false;
                }

                ParameterizedType that = (ParameterizedType) other;

                return getRawType().equals(that.getRawType())
                    && Objects.equals(getOwnerType(), that.getOwnerType())
                    && Arrays.equals(getActualTypeArguments(), that.getActualTypeArguments());
            }

            @Override
            public int hashCode()
            {
                return (ownerType == null ? 0 : ownerType.hashCode()) ^ typeArguments.hashCode() ^ rawType.hashCode();
            }
        };
    }

}
