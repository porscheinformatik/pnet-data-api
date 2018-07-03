package at.porscheinformatik.happyrest;

/**
 * @author yda
 */
public interface RestAttribute
{

    static RestHeader header(String name, String value)
    {
        return new RestHeader(name, value);
    }

    static RestVariable variable(String name, Object value)
    {
        return new RestVariable(name, value);
    }

    static RestParameter parameter(String name, Object value)
    {
        return new RestParameter(name, value);
    }

    String getName();

    Object getValue();
    
}