package pnet.data.api.util;

import java.util.Collection;

import pnet.data.api.proposal.ProposalType;

/**
 * Restricts types.
 *
 * @author ham
 * @param <SELF> the type of the filter for chaining
 */
public interface RestrictProposalType<SELF extends Restrict<SELF>> extends Restrict<SELF>
{

    default SELF type(ProposalType... proposalTypes)
    {
        return restrict("type", (Object[]) proposalTypes);
    }

    default SELF types(Collection<ProposalType> proposalTypes)
    {
        return type(proposalTypes.toArray(new ProposalType[proposalTypes.size()]));
    }

}
