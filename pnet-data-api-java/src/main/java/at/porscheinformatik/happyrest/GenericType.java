package at.porscheinformatik.happyrest;

import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
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
    GenericType<GenericType<?>> TYPE = GenericType.of(GenericType.class);

    GenericType<Void> VOID = GenericType.of(Void.class);
    GenericType<Object> OBJECT = GenericType.of(Object.class);
    GenericType<Boolean> BOOLEAN = GenericType.of(Boolean.class);
    GenericType<Byte> BYTE = GenericType.of(Byte.class);
    GenericType<Short> SHORT = GenericType.of(Short.class);
    GenericType<Integer> INTEGER = GenericType.of(Integer.class);
    GenericType<Long> LONG = GenericType.of(Long.class);
    GenericType<Float> FLOAT = GenericType.of(Float.class);
    GenericType<Double> DOUBLE = GenericType.of(Double.class);
    GenericType<Number> NUMBER = GenericType.of(Number.class);
    GenericType<Character> CHARACTER = GenericType.of(Character.class);
    GenericType<String> STRING = GenericType.of(String.class);

    static <T> Builder<T> build(Class<?> rawType)
    {
        return new Builder<>(null, null, Objects.requireNonNull(rawType, "RawType is null"), rawType.isArray());
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
            Class<?> rawType = (Class<?>) parameterizedType.getRawType();

            return new Of<>(null, parameterizedType.getOwnerType(), rawType, Of.EMPTY_TYPES, Of.EMPTY_TYPES,
                parameterizedType.getActualTypeArguments());
        }

        if (type instanceof TypeVariable<?>)
        {
            TypeVariable<?> typeVariable = (TypeVariable<?>) type;

            return new Of<>(typeVariable.getName(), null, null, Of.EMPTY_TYPES, Of.EMPTY_TYPES);
        }

        if (type instanceof Class<?>)
        {
            Class<?> clazz = (Class<?>) type;

            return new Of<>(null, clazz.getEnclosingClass(), clazz, Of.EMPTY_TYPES, Of.EMPTY_TYPES,
                of(clazz.getTypeParameters()));
        }

        if (type instanceof WildcardType)
        {
            WildcardType wildcardType = (WildcardType) type;

            return new Of<>(null, null, null, wildcardType.getLowerBounds(), wildcardType.getUpperBounds());
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

    static GenericType<?> ofInstance(Object instance)
    {
        return GenericType.build(Objects.requireNonNull(instance, "Instance is null").getClass()).instancedBy(instance);
    }

    static <U> GenericType<U> wildcard()
    {
        return wildcard(Of.EMPTY_TYPES, Of.EMPTY_TYPES);
    }

    static <U> GenericType<U> wildcard(Type[] lowerBounds, Type[] upperBounds)
    {
        return new Of<>("?", null, null, lowerBounds, upperBounds);
    }

    static <U> GenericType<U> wildcardSuper(Type... lowerBounds)
    {
        return new Of<>("?", null, null, lowerBounds, null);
    }

    static <U> GenericType<U> wildcardExtends(Type... upperBounds)
    {
        return new Of<>("?", null, null, null, upperBounds);
    }

    /**
     * Create a generic type by subtyping it.
     *
     * @author ham
     * @param <S> the generic type
     */
    class Of<S> implements GenericType<S>
    {
        private static final Type[] EMPTY_TYPES = new Type[0];

        private final String name;
        private final Type ownerType;
        private final Class<?> rawType;
        private final GenericType<?> componentType;
        private final Type[] lowerBounds;
        private final Type[] upperBounds;
        private final Type[] actualTypeArguments;
        private final GenericType<?>[] arguments;

        public Of()
        {
            GenericType<Object> genericType = build(Of.class).implementedBy(getClass()).getArgument(0);

            name = null;
            ownerType = genericType.getOwnerType();
            rawType = genericType.getType();
            componentType = genericType.getComponentType();
            lowerBounds = EMPTY_TYPES;
            upperBounds = EMPTY_TYPES;
            actualTypeArguments = genericType.getActualTypeArguments();
            arguments = genericType.getArguments();
        }

        Of(String name, Type ownerType, Class<?> rawType, Type[] lowerBounds, Type[] upperBounds,
            Type... actualTypeArguments)
        {
            this(name, ownerType, rawType,
                rawType != null && rawType.getComponentType() != null
                    ? GenericType.build(rawType.getComponentType()).implementedBy(rawType.getComponentType()) : null,
                lowerBounds, upperBounds, actualTypeArguments);
        }

        Of(String name, Type ownerType, Class<?> rawType, GenericType<?> componentType, Type[] lowerBounds,
            Type[] upperBounds, Type... actualTypeArguments)
        {
            super();

            this.name = name;
            this.ownerType = ownerType;
            this.rawType = RestUtils.cultivate(rawType);
            this.componentType = componentType;
            this.lowerBounds = lowerBounds != null ? lowerBounds : EMPTY_TYPES;
            this.upperBounds = upperBounds != null ? upperBounds : EMPTY_TYPES;
            this.actualTypeArguments = actualTypeArguments;
            arguments = of(actualTypeArguments);
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

        public boolean isWildcardType()
        {
            return rawType == null;
        }

        @Override
        public Type[] getLowerBounds()
        {
            return lowerBounds;
        }

        @Override
        public Type[] getUpperBounds()
        {
            return upperBounds;
        }

        @Override
        public boolean isArray()
        {
            return componentType != null;
        }

        @Override
        public GenericType<?> getComponentType()
        {
            return componentType;
        }

        @Override
        public int hashCode()
        {
            final int prime = 31;
            int result = 1;

            result = prime * result + Arrays.hashCode(arguments);
            result = prime * result + Objects.hash(ownerType, rawType);

            return result;
        }

        @Override
        public boolean equals(Object obj)
        {
            if (this == obj)
            {
                return true;
            }

            if (!(obj instanceof Of))
            {
                return false;
            }

            Of<?> other = (Of<?>) obj;

            return Arrays.equals(arguments, other.arguments)
                && Objects.equals(ownerType, other.ownerType)
                && Objects.equals(rawType, other.rawType)
                && Arrays.equals(lowerBounds, other.lowerBounds)
                && Arrays.equals(upperBounds, other.upperBounds);
        }

        @Override
        public String toString()
        {
            StringBuilder builder = new StringBuilder();

            if (rawType != null)
            {
                builder.append(rawType.getTypeName());
            }
            else if (name != null)
            {
                builder.append(name);
            }
            else
            {
                builder.append("?");
            }

            if (arguments.length > 0)
            {
                builder
                    .append('<')
                    .append(Arrays.stream(arguments).map(Type::getTypeName).collect(Collectors.joining(", ")))
                    .append('>');
            }

            if (lowerBounds.length > 0)
            {
                builder
                    .append(" super ")
                    .append(Arrays.stream(lowerBounds).map(Type::getTypeName).collect(Collectors.joining(" & ")));
            }

            if (upperBounds.length > 0)
            {
                builder
                    .append(" extends ")
                    .append(Arrays.stream(upperBounds).map(Type::getTypeName).collect(Collectors.joining(" & ")));
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
        private final boolean array;

        public Builder(String name, Type ownerType, Class<?> rawType, boolean array)
        {
            super();

            this.name = name;
            this.ownerType = ownerType;
            this.rawType = rawType;
            this.array = array;
        }

        public Builder<T> named(String name)
        {
            return new Builder<>(name, ownerType, rawType, array);
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

            return new Builder<>(name, ownerType, rawType, array);
        }

        public <U extends T> GenericType<U> instancedBy(T instance)
        {
            return implementedBy(Objects.requireNonNull(instance, "Instance is null").getClass());
        }

        public <U extends T> GenericType<U> implementedBy(GenericType<?> implementingType)
        {
            return with(findTypeArguments(rawType, implementingType));
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

            return new Of<>(name, ownerType, rawType, Of.EMPTY_TYPES, Of.EMPTY_TYPES, arguments);
        }

        public <U extends T> GenericType<U> raw()
        {
            return new Of<>(name, ownerType, rawType, Of.EMPTY_TYPES, Of.EMPTY_TYPES);
        }

        protected static Type[] findTypeArguments(Type rawType, GenericType<?> implementingType)
        {
            return implementingType.resolve(findTypeArguments(rawType, (Class<?>) implementingType.getRawType()));
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
            if (parameter instanceof Class<?> || parameter instanceof WildcardType)
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
                Type[] actualTypeArguments = ((ParameterizedType) parameter).getActualTypeArguments();

                for (int i = 0; i < actualTypeArguments.length; ++i)
                {
                    actualTypeArguments[i] = findTypeArgument(actualTypeArguments[i], implementingType);
                }

                return build((Class<?>) ((ParameterizedType) parameter).getRawType()).with(actualTypeArguments);
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

    Type[] getLowerBounds();

    Type[] getUpperBounds();

    boolean isArray();

    GenericType<?> getComponentType();

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

    default GenericType<?>[] resolve(Type[] types)
    {
        GenericType<?>[] results = new GenericType<?>[types.length];

        for (int i = 0; i < types.length; i++)
        {
            results[i] = resolve(types[i]);
        }

        return results;
    }

    @SuppressWarnings("unchecked")
    default <U> GenericType<U> resolve(Type type)
    {
        try
        {
            if (type instanceof Of<?>)
            {
                return (GenericType<U>) type;
            }

            if (type instanceof Class<?>)
            {
                return GenericType.of(type);
            }

            if (type instanceof ParameterizedType)
            {
                return GenericType
                    .build((Class<?>) ((ParameterizedType) type).getRawType())
                    .with(resolve(((ParameterizedType) type).getActualTypeArguments()));
            }

            if (type instanceof TypeVariable<?>)
            {
                return resolve(((TypeVariable<?>) type).getName());
            }

            if (type instanceof WildcardType)
            {
                return GenericType
                    .wildcard(((WildcardType) type).getLowerBounds(), ((WildcardType) type).getUpperBounds());
            }

            if (type instanceof GenericArrayType)
            {
                return GenericType.of(((GenericArrayType) type).getGenericComponentType());
            }

            throw new UnsupportedOperationException("Unsupported type of " + type.getClass());
        }
        catch (Exception e)
        {
            throw new IllegalArgumentException("Failed to resolve type: " + type.getTypeName(), e);
        }
    }

    default <U> GenericType<U> resolve(String name)
    {
        TypeVariable<Class<T>>[] typeParameters = getType().getTypeParameters();

        for (int i = 0; i < typeParameters.length; ++i)
        {
            if (name.equals(typeParameters[i].getName()))
            {
                return getArgument(i);
            }
        }

        return GenericType.wildcard(typeParameters, typeParameters);
        //        throw new IllegalArgumentException(String
        //            .format("Type parameter %s not found. Known type prameters are: %s", name,
        //                Arrays.stream(typeParameters).map(TypeVariable::getName).collect(Collectors.joining(", "))));
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

    @SuppressWarnings("unchecked")
    default T cast(Object obj)
    {
        return (T) obj;
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

    default GenericType<?> getGenericSupertype()
    {
        Class<? super T> superclass = getType().getSuperclass();

        return superclass != null ? GenericType.build(superclass).implementedBy(this) : null;
    }

    default GenericType<?>[] getGenericInterfaces()
    {
        Class<?>[] interfaces = getType().getInterfaces();
        GenericType<?>[] genericInterfaces = new GenericType<?>[interfaces.length];

        for (int i = 0; i < interfaces.length; i++)
        {
            genericInterfaces[i] = GenericType.build(interfaces[i]).implementedBy(this);
        }

        return genericInterfaces;
    }
}