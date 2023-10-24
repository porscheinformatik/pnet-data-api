package pnet.data.api.client;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

import pnet.data.api.PnetDataClientException;
import pnet.data.api.PnetDataClientTechnicalException;

/**
 * A {@link Spliterator}, that's capable of loading additional pages while iterating over the items.
 *
 * @author HAM
 * @param <T> the type of item
 */
public class PnetDataClientPageSpliterator<T> implements Spliterator<T>
{

    private PnetDataClientResultPage<T> page;
    private Iterator<T> iterator;

    public PnetDataClientPageSpliterator(PnetDataClientResultPage<T> page)
    {
        super();

        this.page = page;

        iterator = page.iterator();
    }

    @Override
    public boolean tryAdvance(Consumer<? super T> action)
    {
        while (!iterator.hasNext())
        {
            if (page.isEmpty())
            {
                return false;
            }

            try
            {
                page = page.nextPage();
            }
            catch (PnetDataClientException e)
            {
                throw new PnetDataClientTechnicalException("Failed to scroll results", e);
            }

            if (page == null)
            {
                return false;
            }

            iterator = page.iterator();
        }

        action.accept(iterator.next());

        return true;
    }

    @Override
    public Spliterator<T> trySplit()
    {
        return null;
    }

    @Override
    public long estimateSize()
    {
        return page.getTotalNumberOfItems();
    }

    @Override
    public int characteristics()
    {
        return Spliterator.IMMUTABLE | Spliterator.NONNULL;
    }
}
