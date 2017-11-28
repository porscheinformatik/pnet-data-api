package pnet.data.api.consent;

import java.io.Serializable;
import java.time.LocalDateTime;

import pnet.data.api.application.ApplicationMatchcode;

/**
 * Holds a constent of a user and an application
 *
 * @author ham
 */
public class ConsentDataDTO implements Serializable
{

    private static final long serialVersionUID = 1281444716362891435L;

    private final Integer personId;
    private final ApplicationMatchcode applicationMatchcode;
    private final boolean consent;
    private final LocalDateTime lastUpdate;

    public ConsentDataDTO(Integer personId, ApplicationMatchcode applicationMatchcode, boolean consent,
        LocalDateTime lastUpdate)
    {
        super();
        this.personId = personId;
        this.applicationMatchcode = applicationMatchcode;
        this.consent = consent;
        this.lastUpdate = lastUpdate;
    }

    public Integer getPersonId()
    {
        return personId;
    }

    public ApplicationMatchcode getApplicationMatchcode()
    {
        return applicationMatchcode;
    }

    public boolean isConsent()
    {
        return consent;
    }

    public LocalDateTime getLastUpdate()
    {
        return lastUpdate;
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;

        result = prime * result + ((applicationMatchcode == null) ? 0 : applicationMatchcode.hashCode());
        result = prime * result + ((personId == null) ? 0 : personId.hashCode());

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

        ConsentDataDTO other = (ConsentDataDTO) obj;

        if (applicationMatchcode == null)
        {
            if (other.applicationMatchcode != null)
            {
                return false;
            }
        }
        else if (!applicationMatchcode.equals(other.applicationMatchcode))
        {
            return false;
        }

        if (personId == null)
        {
            if (other.personId != null)
            {
                return false;
            }
        }
        else if (!personId.equals(other.personId))
        {
            return false;
        }

        return true;
    }

    @Override
    public String toString()
    {
        return String.format("ConsentDataDTO [personId=%s, applicationMatchcode=%s, consent=%s, lastUpdate=%s]",
            personId, applicationMatchcode, consent, lastUpdate);
    }

}
