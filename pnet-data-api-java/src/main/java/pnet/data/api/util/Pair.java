package pnet.data.api.util;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * A pair of two values
 *
 * @author HAM
 * @param <Left> the left value
 * @param <Right> the right value
 */
public class Pair<Left, Right>
{
    public static <Left, Right> Pair<Left, Right> of(Left left, Right right)
    {
        return new Pair<>(left, right);
    }

    private final Left left;
    private final Right right;

    @JsonCreator
    public Pair(@JsonProperty("left") Left left, @JsonProperty("right") Right right)
    {
        super();
        this.left = left;
        this.right = right;
    }

    public Left getLeft()
    {
        return left;
    }

    public Right getRight()
    {
        return right;
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;

        result = prime * result + ((left == null) ? 0 : left.hashCode());
        result = prime * result + ((right == null) ? 0 : right.hashCode());

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

        if (!(obj instanceof Pair))
        {
            return false;
        }

        Pair<?, ?> other = (Pair<?, ?>) obj;

        if (left == null)
        {
            if (other.left != null)
            {
                return false;
            }
        }
        else if (!left.equals(other.left))
        {
            return false;
        }

        if (right == null)
        {
            if (other.right != null)
            {
                return false;
            }
        }
        else if (!right.equals(other.right))
        {
            return false;
        }

        return true;
    }

    @Override
    public String toString()
    {
        return String.format("(%s, %s)", left, right);
    }

}
