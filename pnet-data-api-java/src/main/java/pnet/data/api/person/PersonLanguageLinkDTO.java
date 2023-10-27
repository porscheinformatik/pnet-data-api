package pnet.data.api.person;

import java.io.Serializable;
import java.util.Locale;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Known languages of the user.
 *
 * @author HAM
 */
@Schema(description = "Holds a language of the person.")
public class PersonLanguageLinkDTO implements Serializable
{

    private static final long serialVersionUID = 5456631116216468972L;

    @Schema(description = "The language as IETF language tag.")
    private final Locale language;

    @Schema(description = "The level of competence.")
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
        return Objects.hash(language, level);
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
        if (!(obj instanceof PersonLanguageLinkDTO other))
        {
            return false;
        }
        if (!Objects.equals(language, other.language))
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
