package pnet.data.api.person;

import java.util.Arrays;
import java.util.List;

import pnet.data.api.PnetDataClientException;
import pnet.data.api.client.PnetDataClientResultPage;
import pnet.data.api.util.AbstractGet;
import pnet.data.api.util.ById;
import pnet.data.api.util.GetFunction;
import pnet.data.api.util.IncludeInactive;
import pnet.data.api.util.Pair;
import pnet.data.api.util.RestrictCredentialsAvailable;
import pnet.data.api.util.RestrictDatedBackUntil;
import pnet.data.api.util.RestrictEmail;
import pnet.data.api.util.RestrictExternalId;
import pnet.data.api.util.RestrictGuid;
import pnet.data.api.util.RestrictPersonnelNumber;
import pnet.data.api.util.RestrictPreferredUserId;
import pnet.data.api.util.RestrictTenant;

/**
 * @author cet
 */
public class PersonDataGet extends AbstractGet<PersonDataDTO, PersonDataGet> implements RestrictTenant<PersonDataGet>,
    RestrictExternalId<PersonDataGet>, RestrictGuid<PersonDataGet>, RestrictPreferredUserId<PersonDataGet>,
    RestrictEmail<PersonDataGet>, RestrictDatedBackUntil<PersonDataGet>, RestrictPersonnelNumber<PersonDataGet>,
    RestrictCredentialsAvailable<PersonDataGet>, IncludeInactive<PersonDataGet>, ById<PersonDataDTO, PersonDataGet>
{

    public PersonDataGet(GetFunction<PersonDataDTO> getFunction, List<Pair<String, Object>> restricts)
    {
        super(getFunction, restricts);
    }

    public PersonDataDTO byExternalId(String externalId) throws PnetDataClientException
    {
        return allByExternalIds(Arrays.asList(externalId), 0, 1).first();
    }

    public PnetDataClientResultPage<PersonDataDTO> allByExternalIds(List<String> externalIds, int pageIndex,
        int itemsPerPage) throws PnetDataClientException
    {
        return externalIds(externalIds).execute(pageIndex, itemsPerPage);
    }

    public PersonDataDTO byGuid(String guid) throws PnetDataClientException
    {
        return allByGuids(Arrays.asList(guid), 0, 1).first();
    }

    public PnetDataClientResultPage<PersonDataDTO> allByGuids(List<String> guids, int pageIndex, int itemsPerPage)
        throws PnetDataClientException
    {
        return guids(guids).execute(pageIndex, itemsPerPage);
    }

    @Deprecated
    public PersonDataDTO byPrefferedUserId(String preferredUserIds) throws PnetDataClientException
    {
        return byPreferredUserId(preferredUserIds);
    }

    @Deprecated
    public PnetDataClientResultPage<PersonDataDTO> allByPrefferedUserIds(List<String> preferredUserIds, int pageIndex,
        int itemsPerPage) throws PnetDataClientException
    {
        return preferredUserIds(preferredUserIds).execute(pageIndex, itemsPerPage);
    }

    public PersonDataDTO byPreferredUserId(String preferredUserIds) throws PnetDataClientException
    {
        return allByPreferredUserIds(Arrays.asList(preferredUserIds), 0, 1).first();
    }

    public PnetDataClientResultPage<PersonDataDTO> allByPreferredUserIds(List<String> preferredUserIds, int pageIndex,
        int itemsPerPage) throws PnetDataClientException
    {
        return preferredUserIds(preferredUserIds).execute(pageIndex, itemsPerPage);
    }

    public PersonDataDTO byEmail(String email) throws PnetDataClientException
    {
        return allByEmails(Arrays.asList(email), 0, 1).first();
    }

    public PnetDataClientResultPage<PersonDataDTO> allByEmails(List<String> emails, int pageIndex, int itemsPerPage)
        throws PnetDataClientException
    {
        return emails(emails).execute(pageIndex, itemsPerPage);
    }

    public PersonDataDTO byPersonnelNumber(String personnelNumber) throws PnetDataClientException
    {
        return allByPersonnelNumbers(Arrays.asList(personnelNumber), 0, 1).first();
    }

    public PnetDataClientResultPage<PersonDataDTO> allByPersonnelNumbers(List<String> personnelNumbers, int pageIndex,
        int itemsPerPage) throws PnetDataClientException
    {
        return personnelNumbers(personnelNumbers).execute(pageIndex, itemsPerPage);
    }
}
