package at.porscheinformatik.happyrest;

import java.util.Objects;

/**
 * @author yda
 */
public abstract class AbstractRestAttribute implements RestAttribute
{
    private final String name;
    private final Object value;

    protected AbstractRestAttribute(String name, Object value)
    {
        super();

        this.name = name;
        this.value = value;
    }

    @Override
    public String getName()
    {
        return name;
    }

    @Override
    public Object getValue()
    {
        return value;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(name, value);
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
        if (getClass() != obj.getClass())
        {
            return false;
        }
        AbstractRestAttribute other = (AbstractRestAttribute) obj;
        return Objects.equals(name, other.name) && Objects.equals(value, other.value);
    }

    @Override
    public String toString()
    {
        return String.format("%s %s = %s", getClass().getSimpleName(), getName(), getValue());
    }
}