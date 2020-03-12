package pnet.data.api.proposal;

import java.util.List;

import pnet.data.api.util.AbstractFind;
import pnet.data.api.util.FindFunction;
import pnet.data.api.util.Pair;
import pnet.data.api.util.RestrictArchived;
import pnet.data.api.util.RestrictId;
import pnet.data.api.util.RestrictPersonId;
import pnet.data.api.util.RestrictProposalState;
import pnet.data.api.util.RestrictProposalType;
import pnet.data.api.util.RestrictRejected;
import pnet.data.api.util.RestrictUpdatedAfter;
import pnet.data.api.util.Scrollable;

public class ProposalDataFind extends AbstractFind<ProposalItemDTO, ProposalDataFind>
    implements RestrictId<ProposalDataFind>, RestrictPersonId<ProposalDataFind>, RestrictProposalType<ProposalDataFind>,
    RestrictProposalState<ProposalDataFind>, RestrictUpdatedAfter<ProposalDataFind>, RestrictRejected<ProposalDataFind>,
    RestrictArchived<ProposalDataFind>, Scrollable<ProposalDataFind>
{

    public ProposalDataFind(FindFunction<ProposalItemDTO> findFunction, List<Pair<String, Object>> restricts)
    {
        super(findFunction, restricts);
    }
}