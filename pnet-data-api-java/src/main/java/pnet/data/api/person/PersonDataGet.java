package pnet.data.api.person;

import java.util.Arrays;
import java.util.List;

import pnet.data.api.PnetDataClientException;
import pnet.data.api.client.PnetDataClientResultPage;
import pnet.data.api.util.AbstractGet;
import pnet.data.api.util.ById;
import pnet.data.api.util.GetFunction;
import pnet.data.api.util.Pair;
import pnet.data.api.util.RestrictDatedBackUntil;
import pnet.data.api.util.RestrictTenant;

/**
 * @author cet
 *
 */
public class PersonDataGet extends AbstractGet<PersonDataDTO, PersonDataGet>
    implements RestrictTenant<PersonDataGet>, RestrictDatedBackUntil<PersonDataGet>, ById<PersonDataDTO, PersonDataGet>
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
        return execute("externalId", externalIds, pageIndex, itemsPerPage);
    }

    public PersonDataDTO byGuid(String guid) throws PnetDataClientException
    {
        return allByGuids(Arrays.asList(guid), 0, 1).first();
    }

    public PnetDataClientResultPage<PersonDataDTO> allByGuids(List<String> guids, int pageIndex, int itemsPerPage)
        throws PnetDataClientException
    {
        return execute("guid", guids, pageIndex, itemsPerPage);
    }

    public PersonDataDTO byPrefferedUserId(String prefferedUserIds) throws PnetDataClientException
    {
        return allByPrefferedUserIds(Arrays.asList(prefferedUserIds), 0, 1).first();
    }

    public PnetDataClientResultPage<PersonDataDTO> allByPrefferedUserIds(List<String> prefferedUserIds, int pageIndex,
        int itemsPerPage) throws PnetDataClientException
    {
        return execute("preferredUserId", prefferedUserIds, pageIndex, itemsPerPage);
    }

    public PersonDataDTO byEmail(String email) throws PnetDataClientException
    {
        return allByEmails(Arrays.asList(email), 0, 1).first();
    }

    public PnetDataClientResultPage<PersonDataDTO> allByEmails(List<String> emails, int pageIndex, int itemsPerPage)
        throws PnetDataClientException
    {
        return execute("email", emails, pageIndex, itemsPerPage);
    }

    public PersonDataDTO byPersonnelNumber(String personnelNumber) throws PnetDataClientException
    {
        return allByPersonnelNumbers(Arrays.asList(personnelNumber), 0, 1).first();
    }

    public PnetDataClientResultPage<PersonDataDTO> allByPersonnelNumbers(List<String> personnelNumbers, int pageIndex,
        int itemsPerPage) throws PnetDataClientException
    {
        return execute("personnelNumber", personnelNumbers, pageIndex, itemsPerPage);
    }
}
