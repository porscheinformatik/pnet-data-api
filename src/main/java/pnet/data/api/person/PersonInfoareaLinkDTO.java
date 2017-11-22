package pnet.data.api.person;

import com.fasterxml.jackson.annotation.JsonProperty;

import pnet.data.api.activity.ActivityMatchcode;

/**
 * Holds the activity of a person for one company and brand.
 *
 * @author ham
 */
public class PersonInfoareaLinkDTO
{

    private final ActivityMatchcode activityMatchcode;
    private final boolean dueToFunction;

    public PersonInfoareaLinkDTO(@JsonProperty("activityMatchcode") ActivityMatchcode activityMatchcode,
        @JsonProperty("dueToFunction") boolean dueToFunction)
    {
        super();

        this.activityMatchcode = activityMatchcode;
        this.dueToFunction = dueToFunction;
    }

    public ActivityMatchcode getActivityMatchcode()
    {
        return activityMatchcode;
    }

    public boolean isDueToFunction()
    {
        return dueToFunction;
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;

        result = prime * result + ((activityMatchcode == null) ? 0 : activityMatchcode.hashCode());

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

        if (getClass() != obj.getClass())
        {
            return false;
        }

        PersonInfoareaLinkDTO other = (PersonInfoareaLinkDTO) obj;

        if (activityMatchcode == null)
        {
            if (other.activityMatchcode != null)
            {
                return false;
            }
        }
        else if (!activityMatchcode.equals(other.activityMatchcode))
        {
            return false;
        }

        return true;
    }

    @Override
    public String toString()
    {
        return String.format("%s [dueToFunction=%s]", activityMatchcode, dueToFunction);
    }

}
