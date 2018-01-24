package pnet.data.api;

import java.util.Collection;

/**
 * A collection with an option to request more results
 *
 * @author ham
 * @param <T> the type of result
 */
public interface ResultCollection<T> extends Collection<T>
{

    /**
     * @return true if there are more results.
     */
    boolean hasNextCollection();

    /**
     * @return returns the next {@link ResultCollection}. Executes a call. Null otherwise.
     */
    ResultCollection<T> getNextCollection();

}
