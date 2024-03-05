/* Copyright 2017 Porsche Informatik GmbH
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package pnet.data.api.application;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import pnet.data.api.util.WithDescriptions;
import pnet.data.api.util.WithLabels;
import pnet.data.api.util.WithLastUpdate;
import pnet.data.api.util.WithMatchcode;

/**
 * Holds an application.
 *
 * @author ham
 */
@Schema(description = "Holds all information about an application")
public class ApplicationDataDTO implements WithMatchcode, WithLabels, WithDescriptions, WithLastUpdate, Serializable
{
    @Serial
    private static final long serialVersionUID = -8043695004224267387L;

    @Schema(description = "The unique matchcode of the application.")
    private final String matchcode;

    @Schema(description = "The label of the application with all existing translations.")
    private Map<Locale, String> labels;

    @Schema(description = "The description of the application with all existing translations.")
    private Map<Locale, String> descriptions;

    @Schema(description = "The list of all scopes this application is permitted to use.")
    private Collection<ApplicationScopeLinkDTO> scopes;

    @Schema(description = "The time and date when the application was last changed.")
    private LocalDateTime lastUpdate;

    public ApplicationDataDTO(@JsonProperty("matchcode") String matchcode)
    {
        super();

        this.matchcode = matchcode;
    }

    @Override
    public String getMatchcode()
    {
        return matchcode;
    }

    @Override
    public Map<Locale, String> getLabels()
    {
        return labels;
    }

    public void setLabels(Map<Locale, String> labels)
    {
        this.labels = labels;
    }

    @Override
    public Map<Locale, String> getDescriptions()
    {
        return descriptions;
    }

    public void setDescriptions(Map<Locale, String> descriptions)
    {
        this.descriptions = descriptions;
    }

    public Collection<ApplicationScopeLinkDTO> getScopes()
    {
        return scopes;
    }

    public Optional<ApplicationScopeLinkDTO> findScope(Predicate<? super ApplicationScopeLinkDTO> predicate)
    {
        return scopes == null ? Optional.empty() : scopes.stream().filter(predicate).findFirst();
    }

    public void setScopes(Collection<ApplicationScopeLinkDTO> scopes)
    {
        this.scopes = scopes;
    }

    @Override
    public LocalDateTime getLastUpdate()
    {
        return lastUpdate;
    }

    public void setLastUpdate(LocalDateTime lastUpdate)
    {
        this.lastUpdate = lastUpdate;
    }

    @Override
    public String toString()
    {
        return String.format("ApplicationDataDTO [matchcode=%s, labels=%s, descriptions=%s, scopes=%s, lastUpdate=%s]",
            matchcode, labels, descriptions, scopes, lastUpdate);
    }
}
