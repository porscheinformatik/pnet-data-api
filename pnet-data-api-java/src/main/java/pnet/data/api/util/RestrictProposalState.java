package pnet.data.api.util;

import java.util.Collection;

import pnet.data.api.proposal.ProposalState;

/**
 * Restricts states.
 *
 * @author ham
 * @param <SELF> the state of the filter for chaining
 */
public interface RestrictProposalState<SELF extends Restrict<SELF>> extends Restrict<SELF>
{

    default SELF state(ProposalState... proposalStates)
    {
        return restrict("state", (Object[]) proposalStates);
    }

    default SELF states(Collection<ProposalState> proposalStates)
    {
        return state(proposalStates.toArray(new ProposalState[proposalStates.size()]));
    }

}
