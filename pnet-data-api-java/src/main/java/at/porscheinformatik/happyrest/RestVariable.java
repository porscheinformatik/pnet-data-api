package at.porscheinformatik.happyrest;

/**
 * A variable in a path
 *
 * @author ham
 */
public final class RestVariable extends AbstractRestAttribute {

    protected RestVariable(String name, Object value) {
        super(name, value);
    }
}
