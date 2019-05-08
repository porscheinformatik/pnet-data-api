package pnet.data.api.util;

/**
 * Some binary response with data and type
 * 
 * @author HAM
 */
public class Resource
{
    private final String type;
    private final byte[] data;

    public Resource(String type, byte[] data)
    {
        super();
        this.type = type;
        this.data = data;
    }

    public String getType()
    {
        return type;
    }

    public byte[] getData()
    {
        return data;
    }
}
