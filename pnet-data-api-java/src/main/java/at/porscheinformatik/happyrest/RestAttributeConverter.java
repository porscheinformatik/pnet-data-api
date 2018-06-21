package at.porscheinformatik.happyrest;

/**
 * A converter for parameters, attributes and header values
 *
 * @author ham
 */
@FunctionalInterface
public interface RestAttributeConverter
{

    String convertAttributeToString(Object value);

}
