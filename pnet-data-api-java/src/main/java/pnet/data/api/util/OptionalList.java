package pnet.data.api.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * A list that may be null internally.
 *
 * @author ham
 * @param <T> the type
 */
public class OptionalList<T> implements List<T>
{

    private final Object semaphore = new Object();

    private List<T> list;

    public OptionalList()
    {
        this(null);
    }

    public OptionalList(List<T> list)
    {
        super();

        this.list = list;
    }

    public List<T> getList()
    {
        return list;
    }

    @Override
    public int size()
    {
        return list != null ? list.size() : 0;
    }

    @Override
    public boolean isEmpty()
    {
        return list == null || list.isEmpty();
    }

    @Override
    public boolean contains(Object o)
    {
        return list != null && list.contains(o);
    }

    @Override
    public Iterator<T> iterator()
    {
        return list != null ? list.iterator() : Collections.emptyListIterator();
    }

    @Override
    public Object[] toArray()
    {
        return list != null ? list.toArray() : null;
    }

    @SuppressWarnings("hiding")
    @Override
    public <T> T[] toArray(T[] a)
    {
        return list != null ? list.toArray(a) : null;
    }

    @Override
    public boolean add(T e)
    {
        synchronized (semaphore)
        {
            if (list == null)
            {
                list = new ArrayList<>();
            }

            return list.add(e);
        }
    }

    @Override
    public boolean remove(Object o)
    {
        return list != null ? list.remove(o) : false;
    }

    @Override
    public boolean containsAll(Collection<?> c)
    {
        return list != null && list.containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends T> c)
    {
        synchronized (semaphore)
        {
            if (list == null)
            {
                list = new ArrayList<>();
            }

            return list.addAll(c);
        }
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c)
    {
        synchronized (semaphore)
        {
            if (list == null)
            {
                list = new ArrayList<>();
            }

            return list.addAll(index, c);
        }
    }

    @Override
    public boolean removeAll(Collection<?> c)
    {
        return list != null ? removeAll(c) : false;
    }

    @Override
    public boolean retainAll(Collection<?> c)
    {
        return list != null ? retainAll(c) : false;
    }

    @Override
    public void clear()
    {
        if (list != null)
        {
            list.clear();
        }
    }

    @Override
    public T get(int index)
    {
        return list != null ? list.get(index) : null;
    }

    @Override
    public T set(int index, T element)
    {
        synchronized (semaphore)
        {
            if (list == null)
            {
                list = new ArrayList<>();
            }

            return list.set(index, element);
        }
    }

    @Override
    public void add(int index, T element)
    {
        synchronized (semaphore)
        {
            if (list == null)
            {
                list = new ArrayList<>();
            }

            list.add(index, element);
        }
    }

    @Override
    public T remove(int index)
    {
        return list != null ? list.remove(index) : null;
    }

    @Override
    public int indexOf(Object o)
    {
        return list != null ? list.indexOf(o) : -1;
    }

    @Override
    public int lastIndexOf(Object o)
    {
        return list != null ? list.lastIndexOf(o) : -1;
    }

    @Override
    public ListIterator<T> listIterator()
    {
        return list != null ? list.listIterator() : Collections.emptyListIterator();
    }

    @Override
    public ListIterator<T> listIterator(int index)
    {
        return list != null ? list.listIterator(index) : Collections.emptyListIterator();
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex)
    {
        return list != null ? list.subList(fromIndex, toIndex) : null;
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((list == null) ? 0 : list.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
        {
            return true;
        }
        if (obj == null)
        {
            return false;
        }
        if (!(obj instanceof OptionalList))
        {
            return false;
        }

        OptionalList<?> other = (OptionalList<?>) obj;

        if (list == null)
        {
            if (other.list != null)
            {
                return false;
            }
        }
        else if (!list.equals(other.list))
        {
            return false;
        }

        return true;
    }

    @Override
    public String toString()
    {
        return list != null ? list.toString() : "null";
    }

}
