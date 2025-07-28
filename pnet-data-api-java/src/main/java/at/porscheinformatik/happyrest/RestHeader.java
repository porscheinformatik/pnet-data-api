package at.porscheinformatik.happyrest;

/**
 * A header value for a request
 *
 * @author ham
 */
public final class RestHeader extends AbstractRestAttribute {

    protected RestHeader(String name, String value) {
        super(name, value);
    }
}
