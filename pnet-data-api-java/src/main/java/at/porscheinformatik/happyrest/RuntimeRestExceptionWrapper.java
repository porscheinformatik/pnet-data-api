package at.porscheinformatik.happyrest;

public class RuntimeRestExceptionWrapper extends RuntimeException
{
    private static final long serialVersionUID = 8431874357366627619L;

    public RuntimeRestExceptionWrapper(RestException cause)
    {
        super(cause);
    }

    @Override
    public synchronized RestException getCause()
    {
        return (RestException) super.getCause();
    }
}
