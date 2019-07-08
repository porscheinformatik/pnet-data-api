package pnet.data.api.person;

import java.io.Serializable;
import java.util.Locale;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Known languages of the user.
 *
 * @author HAM
 */
@ApiModel(description = "Holds a language of the person.")
public class PersonLanguageLinkDTO implements Serializable
{

    private static final long serialVersionUID = 5456631116216468972L;

    @ApiModelProperty(notes = "The language as IETF language tag.")
    private final Locale language;

    @ApiModelProperty(notes = "The level of competence.")
    private final LanguageLevel level;

    public PersonLanguageLinkDTO(@JsonProperty("language") Locale language, @JsonProperty("level") LanguageLevel level)
    {
        super();
        this.language = language;
        this.level = level;
    }

    public Locale getLanguage()
    {
        return language;
    }

    public LanguageLevel getLevel()
    {
        return level;
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((language == null) ? 0 : language.hashCode());
        result = prime * result + ((level == null) ? 0 : level.hashCode());
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
        if (!(obj instanceof PersonLanguageLinkDTO))
        {
            return false;
        }
        PersonLanguageLinkDTO other = (PersonLanguageLinkDTO) obj;
        if (language == null)
        {
            if (other.language != null)
            {
                return false;
            }
        }
        else if (!language.equals(other.language))
        {
            return false;
        }
        if (level != other.level)
        {
            return false;
        }
        return true;
    }
}
