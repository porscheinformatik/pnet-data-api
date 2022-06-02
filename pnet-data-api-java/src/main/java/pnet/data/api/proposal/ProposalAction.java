package pnet.data.api.proposal;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Defines the action of a maintenance proposal
 *
 * @author scar
 */
public enum ProposalAction
{
    CREATE,
    UPDATE,
    DELETE,
    OTHER;

    public List<ProposalType> getTypes()
    {
        return Arrays
            .stream(ProposalType.values())
            .filter(type -> type.getAction() == this)
            .collect(Collectors.toList());
    }
}
