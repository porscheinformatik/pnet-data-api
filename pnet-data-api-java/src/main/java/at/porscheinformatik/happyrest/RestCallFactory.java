package at.porscheinformatik.happyrest;

/**
 * Factory for REST calls
 *
 * @author ham
 */
public interface RestCallFactory {
    default RestCall create() {
        return url(null);
    }

    /**
     * Creates a new REST call
     *
     * @param url the URL
     * @return a REST call
     */
    RestCall url(String url);
}
