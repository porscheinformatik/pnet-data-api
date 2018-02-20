package at.porscheinformatik.happyrest;

/**
 * A parameter for a request
 *
 * @author ham
 */
public final class RestParameter extends AbstractRestAttribute
{

    protected RestParameter(String name, Object value)
    {
        super(name, value);
    }

}
