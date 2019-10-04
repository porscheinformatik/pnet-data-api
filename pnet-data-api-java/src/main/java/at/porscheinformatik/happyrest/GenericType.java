package at.porscheinformatik.happyrest;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Defines a generic, parameterized type for info at runtime. <br>
 * <br>
 * If you need <code>List&lt;String&gt;</code> as type, just type
 * <code>GenericType.build(List.class).with(String.class)</code><br>
 * <br>
 * Or you can used an anonymous subtype: <code>new GenericType.Of&lt;List&lt;String&gt;&gt;(){}</code><br>
 * <br>
 *
 * @author ham
 * @param <T> the generic type
 */
public interface GenericType<T> extends ParameterizedType
{

    static <T> Builder<T> build(Class<?> rawType)
    {
        return new Builder<>(null, null, rawType);
    }

    @SuppressWarnings("unchecked")
    static <T> GenericType<T> of(Type type)
    {
        if (type == null)
        {
            return null;
        }

        if (type instanceof GenericType<?>)
        {
            return (GenericType<T>) type;
        }

        if (type instanceof ParameterizedType)
        {
            ParameterizedType parameterizedType = (ParameterizedType) type;

            return new Of<>(null, parameterizedType.getOwnerType(), (Class<?>) parameterizedType.getRawType(),
                parameterizedType.getActualTypeArguments());
        }

        if (type instanceof TypeVariable<?>)
        {
            TypeVariable<?> typeVariable = (TypeVariable<?>) type;

            return new Of<>(typeVariable.getName(), null, null);
        }

        if (type instanceof Class<?>)
        {
            Class<?> clazz = (Class<?>) type;
            return new Of<>(null, clazz.getEnclosingClass(), clazz, of(clazz.getTypeParameters()));
        }

        throw new UnsupportedOperationException("Type of " + type.getClass() + " not supported");
    }

    static GenericType<?>[] of(Type... types)
    {
        GenericType<?>[] genericTypes = new GenericType<?>[types.length];

        for (int i = 0; i < types.length; i++)
        {
            genericTypes[i] = of(types[i]);
        }

        return genericTypes;
    }

    /**
     * Create a generic type by subtyping it.
     *
     * @author ham
     * @param <S> the generic type
     */
    class Of<S> implements GenericType<S>
    {
        private final String name;
        private final Type ownerType;
        private final Class<?> rawType;
        private final Type[] actualTypeArguments;
        private final GenericType<?>[] arguments;

        public Of()
        {
            GenericType<Object> genericType = build(Of.class).implementedBy(getClass()).getArgument(0);

            this.name = null;
            this.ownerType = genericType.getOwnerType();
            this.rawType = genericType.getType();
            this.actualTypeArguments = genericType.getActualTypeArguments();
            this.arguments = genericType.getArguments();
        }

        Of(String name, Type ownerType, Class<?> rawType, Type... actualTypeArguments)
        {
            super();

            this.name = name;
            this.ownerType = ownerType;
            this.rawType = rawType;
            this.actualTypeArguments = actualTypeArguments;
            this.arguments = of(actualTypeArguments);
        }

        @Override
        public String getName()
        {
            return name;
        }

        @Override
        public Type[] getActualTypeArguments()
        {
            return actualTypeArguments;
        }

        @Override
        public GenericType<?>[] getArguments()
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
            return (ownerType == null ? 0 : ownerType.hashCode()) ^ Arrays.hashCode(arguments) ^ rawType.hashCode();
        }

        @Override
        public String toString()
        {
            StringBuilder builder = new StringBuilder(rawType != null ? rawType.getTypeName() : name);

            if (arguments.length > 0)
            {
                builder
                    .append('<')
                    .append(Arrays.stream(arguments).map(Type::getTypeName).collect(Collectors.joining(", ")))
                    .append('>');
            }

            return builder.toString();
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
        private final String name;
        private final Type ownerType;
        private final Class<?> rawType;

        public Builder(String name, Type ownerType, Class<?> rawType)
        {
            super();

            this.name = name;
            this.ownerType = ownerType;
            this.rawType = rawType;
        }

        public Builder<T> named(String name)
        {
            return new Builder<>(name, ownerType, rawType);
        }

        public Builder<T> ownedBy(Type ownerType)
        {
            if (this.ownerType != null)
            {
                throw new IllegalArgumentException("OwnerType already set");
            }

            if (ownerType == null)
            {
                return this;
            }

            return new Builder<>(name, ownerType, rawType);
        }

        public <U extends T> GenericType<U> instancedBy(T instance)
        {
            return implementedBy(Objects.requireNonNull(instance, "Instance is null").getClass());
        }

        public <U extends T> GenericType<U> implementedBy(Class<?> implementingType)
        {
            return with(findTypeArguments(rawType, implementingType));
        }

        public <U extends T> GenericType<U> with(Type... arguments)
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

            return new Of<>(name, ownerType, rawType, arguments);
        }

        protected static Type[] findTypeArguments(Type rawType, Class<?> implementingType)
        {
            Type genericType = findGenericType(rawType, implementingType);

            if (genericType == null)
            {
                throw new IllegalArgumentException(implementingType + " does not extend or implement " + rawType);
            }

            Type[] parameters;

            if (genericType instanceof ParameterizedType)
            {
                parameters = ((ParameterizedType) genericType).getActualTypeArguments();
            }
            else if (genericType instanceof Class<?>)
            {
                parameters = ((Class<?>) genericType).getTypeParameters();
            }
            else
            {
                throw new UnsupportedOperationException("Unsupported type: " + genericType.getClass());
            }

            Type[] results = new Type[parameters.length];

            for (int i = 0; i < parameters.length; i++)
            {
                results[i] = findTypeArgument(parameters[i], implementingType);
            }

            return results;
        }

        protected static Type findTypeArgument(Type parameter, Class<?> implementingType)
        {
            if (parameter instanceof Class<?>)
            {
                return parameter;
            }

            if (parameter instanceof TypeVariable<?>)
            {

                TypeVariable<?> typeVariable = (TypeVariable<?>) parameter;
                Class<?> genericDeclaration = (Class<?>) typeVariable.getGenericDeclaration();

                if (implementingType == genericDeclaration)
                {
                    return parameter;
                }

                String name = typeVariable.getName();
                TypeVariable<?>[] parameters = genericDeclaration.getTypeParameters();
                Type[] variableTypes = findTypeArguments(genericDeclaration, implementingType);

                for (int j = 0; j < Math.min(parameters.length, variableTypes.length); j++)
                {
                    if (name.equals(parameters[j].getName()))
                    {
                        return variableTypes[j];
                    }
                }

                return typeVariable;
            }

            if (parameter instanceof ParameterizedType)
            {
                return build((Class<?>) ((ParameterizedType) parameter).getRawType())
                    .with(((ParameterizedType) parameter).getActualTypeArguments());
            }

            throw new UnsupportedOperationException("Unsupported type of " + parameter.getClass());
        }

        protected static Type findGenericType(Type type, Class<?> implementingType)
        {
            if (implementingType == type)
            {
                return implementingType;
            }

            Class<?>[] interfaces = implementingType.getInterfaces();

            for (int i = 0; i < interfaces.length; i++)
            {
                if (interfaces[i] == type)
                {
                    return implementingType.getGenericInterfaces()[i];
                }
            }

            if (implementingType.getSuperclass() == type)
            {
                return implementingType.getGenericSuperclass();
            }

            for (Class<?> interf : interfaces)
            {
                Type result = findGenericType(type, interf);

                if (result != null)
                {
                    return result;
                }
            }

            Class<?> superclass = implementingType.getSuperclass();

            if (superclass != null)
            {
                Type result = findGenericType(type, superclass);

                if (result != null)
                {
                    return result;
                }
            }

            return null;
        }

        protected static String getShortName(String currentName)
        {
            int beginIndex = currentName.lastIndexOf('.');

            if (beginIndex >= 0)
            {
                currentName = currentName.substring(beginIndex + 1);
            }

            return currentName;
        }
    }

    String getName();

    @SuppressWarnings("unchecked")
    default Class<T> getType()
    {
        return (Class<T>) getRawType();
    }

    GenericType<?>[] getArguments();

    default int getNumberOfArguments()
    {
        return getArguments().length;
    }

    default <U> GenericType<U> getArgument(int index)
    {
        return of(getActualTypeArguments()[index]);
    }

    @SuppressWarnings("unchecked")
    default <U> Class<U> getArgumentClass(int index)
    {
        return (Class<U>) getArgument(index).getType();
    }

    default String getSimpleTypeName()
    {
        Type rawType = getRawType();
        StringBuilder builder =
            new StringBuilder(rawType != null ? Builder.getShortName(rawType.getTypeName()) : getName());

        GenericType<?>[] arguments = getArguments();

        if (arguments.length > 0)
        {
            builder
                .append('<')
                .append(Arrays.stream(arguments).map(GenericType::getSimpleTypeName).collect(Collectors.joining(", ")))
                .append('>');
        }

        return builder.toString();
    }

    default boolean isInstance(Object obj)
    {
        if (obj == null)
        {
            return true;
        }

        if (!getType().isInstance(obj))
        {
            return false;
        }

        return isAssignableFrom(obj.getClass());
    }

    default boolean isAssignableFrom(Class<?> type)
    {
        if (!getType().isAssignableFrom(type))
        {
            return false;
        }

        return isAssignableFrom(build(getType()).implementedBy(type));
    }

    default boolean isAssignableFrom(GenericType<?> type)
    {
        Class<?> otherType = type.getType();

        if (otherType != null && !getType().isAssignableFrom(otherType))
        {
            return false;
        }

        GenericType<?>[] arguments = getArguments();
        GenericType<?>[] otherArguments = type.getArguments();

        for (int i = 0; i < arguments.length; i++)
        {
            if (!arguments[i].isAssignableFrom(otherArguments[i]))
            {
                return false;
            }
        }

        return true;
    }

}
