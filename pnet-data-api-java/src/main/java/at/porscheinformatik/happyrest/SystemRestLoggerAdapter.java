package at.porscheinformatik.happyrest;

/**
 * A logger adapter for the System streams
 *
 * @author HAM
 */
public class SystemRestLoggerAdapter extends PrintStreamLoggerAdapter
{

    public static final SystemRestLoggerAdapter INSTANCE = new SystemRestLoggerAdapter();

    public SystemRestLoggerAdapter()
    {
        super(System.out);
    }

}
