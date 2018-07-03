package at.porscheinformatik.happyrest;

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
    public String toString()
    {
        return String.format("%s %s = %s", getClass().getSimpleName(), getName(), getValue());
    }
}