package pnet.data.api.proposal;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Defines the group of a maintenance proposal
 *
 * @author scar
 */
public enum ProposalGroup
{
    MENU_ITEM,
    FUNCTION,
    ACTIVITY,
    OTHER;

    public List<ProposalType> getTypes()
    {
        return Arrays
            .stream(ProposalType.values())
            .filter(type -> type.getGroup() == this)
            .collect(Collectors.toList());
    }
}
