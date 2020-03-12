package pnet.data.api.proposal;

import java.util.List;

import pnet.data.api.util.AbstractSearch;
import pnet.data.api.util.Pair;
import pnet.data.api.util.RestrictArchived;
import pnet.data.api.util.RestrictPersonId;
import pnet.data.api.util.RestrictProposalState;
import pnet.data.api.util.RestrictProposalType;
import pnet.data.api.util.RestrictRejected;
import pnet.data.api.util.SearchFunction;

public class ProposalDataSearch extends AbstractSearch<ProposalItemDTO, ProposalDataSearch>
    implements RestrictPersonId<ProposalDataSearch>, RestrictProposalType<ProposalDataSearch>,
    RestrictProposalState<ProposalDataSearch>, RestrictRejected<ProposalDataSearch>,
    RestrictArchived<ProposalDataSearch>
{

    public ProposalDataSearch(SearchFunction<ProposalItemDTO> searchFunction, List<Pair<String, Object>> restricts)
    {
        super(searchFunction, restricts);
    }
}