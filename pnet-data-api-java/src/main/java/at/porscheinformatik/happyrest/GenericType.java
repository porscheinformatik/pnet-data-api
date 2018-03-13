package at.porscheinformatik.happyrest;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.Arrays;
import java.util.Objects;

/**
 * Defines a generic, parameterized type for info at runtime. <br>
 * <br>
 * If you need <code>List&lt;String&gt;</code> as type, just type
 * <code>GenericType.of(List.class).with(String.class)</code><br>
 * <br>
 * Or you can used an anonymous subtype: <code>new GenericType.Of&lt;List&lt;String&gt;&gt;(){}</code><br>
 * <br>
 *
 * @author ham
 * @param <T> the generic type
 */
public interface GenericType<T> extends ParameterizedType
{

    static <T> Builder<T> of(Class<? extends T> rawType)
    {
        return new Builder<>(null, rawType);
    }

    /**
     * Create a generic type by subtyping it.
     *
     * @author ham
     * @param <S> the generic type
     */
    class Of<S> implements GenericType<S>
    {
        private final ParameterizedType type;
        private final Class<?>[] arguments;

        public Of()
        {
            Type type = getType(getClass(), Of.class);

            if (!(type instanceof ParameterizedType))
            {
                throw new IllegalArgumentException("The " + getClass() + " is not parameterized");
            }

            this.type = (ParameterizedType) type;
            this.arguments = getTypeArguments(getClass(), type);

        }

        @Override
        public Class<?>[] getArguments()
        {
            return arguments;
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

        public static Class<?>[] getTypeArguments(Class<?> implementingType, Class<?> rawType)
        {
            Type type = getType(implementingType, rawType);

            if (type == null)
            {
                throw new IllegalArgumentException(implementingType + " does not extend or implement " + rawType);
            }

            return getTypeArguments(implementingType, type);
        }

        public static Class<?>[] getTypeArguments(Class<?> implementingType, Type type)
        {
            Type[] types;

            if (type instanceof ParameterizedType)
            {
                types = ((ParameterizedType) type).getActualTypeArguments();
            }
            else if (type instanceof Class<?>)
            {
                types = ((Class<?>) type).getTypeParameters();
            }
            else
            {
                throw new UnsupportedOperationException("Unsupported type: " + type.getClass());
            }

            Class<?>[] classes = new Class<?>[types.length];

            for (int i = 0; i < types.length; i++)
            {
                if (types[i] instanceof Class<?>)
                {
                    classes[i] = (Class<?>) types[i];
                    continue;
                }

                if (types[i] instanceof TypeVariable<?> && implementingType != type)
                {
                    TypeVariable<?> typeVariable = (TypeVariable<?>) types[i];
                    String name = typeVariable.getName();
                    Class<?> genericDeclaration = (Class<?>) typeVariable.getGenericDeclaration();
                    TypeVariable<?>[] parameters = genericDeclaration.getTypeParameters();
                    Class<?>[] variableTypes = getTypeArguments(implementingType, genericDeclaration);

                    for (int j = 0; j < Math.min(parameters.length, variableTypes.length); j++)
                    {
                        if (name.equals(parameters[j].getName()))
                        {
                            classes[i] = variableTypes[j];
                            break;
                        }
                    }

                    if (classes[i] == null)
                    {
                        throw new IllegalArgumentException("Definition of type parameter "
                            + name
                            + " of "
                            + genericDeclaration
                            + " is not available at runtime");
                    }
                }
            }

            return classes;
        }

        public static Type getType(Class<?> implementingType, Class<?> rawType)
        {
            if (implementingType == rawType)
            {
                return implementingType;
            }

            Class<?>[] interfaces = implementingType.getInterfaces();

            for (int i = 0; i < interfaces.length; i++)
            {
                if (interfaces[i] == rawType)
                {
                    return implementingType.getGenericInterfaces()[i];
                }
            }

            if (implementingType.getSuperclass() == rawType)
            {
                return implementingType.getGenericSuperclass();
            }

            for (Class<?> interf : interfaces)
            {
                Type type = getType(interf, rawType);

                if (type != null)
                {
                    return type;
                }
            }

            Class<?> superclass = implementingType.getSuperclass();

            if (superclass != null)
            {
                Type type = getType(superclass, rawType);

                if (type != null)
                {
                    return type;
                }
            }

            return null;
        }
    }

    /**
     * A builder for GenericTypes.
     *
     * @author ham
     * @param <T> the type
     */
    class Builder<T>
    {
        private final Type ownerType;
        private final Class<? extends T> rawType;

        public Builder(Type ownerType, Class<? extends T> rawType)
        {
            super();

            this.ownerType = ownerType;
            this.rawType = rawType;
        }

        public Builder<T> ownedBy(Type ownerType)
        {
            if (this.ownerType != null)
            {
                throw new IllegalArgumentException("OwnerType already set");
            }

            return new Builder<>(ownerType, rawType);
        }

        public <U extends T> GenericType<U> implementedBy(Class<?> implementingType)
        {
            return with(Of.getTypeArguments(implementingType, rawType));
        }

        public <U extends T> GenericType<U> instancedBy(T instance)
        {
            return implementedBy(Objects.requireNonNull(instance, "Instance is null").getClass());
        }

        public <U extends T> GenericType<U> with(Class<?>... arguments)
        {
            Objects.requireNonNull(rawType, "RawType is null");

            if (rawType.getTypeParameters().length != arguments.length)
            {
                throw new IllegalArgumentException("Invalid number of type arguments");
            }

            for (Type type : arguments)
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

            return new GenericType<U>()
            {
                @Override
                public Class<?>[] getArguments()
                {
                    return arguments;
                }

                @Override
                public Type[] getActualTypeArguments()
                {
                    return arguments;
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

                    Arrays.stream(arguments).forEach(builder::append);

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
                    return (ownerType == null ? 0 : ownerType.hashCode()) ^ arguments.hashCode() ^ rawType.hashCode();
                }
            };
        }
    }

    default Class<?> getType()
    {
        return (Class<?>) getRawType();
    }

    default int getNumberOfArguments()
    {
        return getArguments().length;
    }

    Class<?>[] getArguments();

    @SuppressWarnings("unchecked")
    default <U> Class<U> getArgument(int index)
    {
        return (Class<U>) getArguments()[index];
    }

}
