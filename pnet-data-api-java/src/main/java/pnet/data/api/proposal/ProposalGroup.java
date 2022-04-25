package pnet.data.api.proposal;

/**
 * Defines the group of a maintenance proposal
 *
 * @author scar
 */
public enum ProposalGroup
{
    MENU_ITEM(ProposalType.MENU_ITEM_CREATE, ProposalType.MENU_ITEM_UPDATE, ProposalType.MENU_ITEM_DELETE),

    FUNCTION(ProposalType.FUNCTION_CREATE, ProposalType.FUNCTION_UPDATE, ProposalType.FUNCTION_DELETE),

    ACTIVITY(ProposalType.ACTIVITY_CREATE, ProposalType.ACTIVITY_UPDATE, ProposalType.ACTIVITY_DELETE),

    OTHER(ProposalType.OTHER);

    private final ProposalType[] types;

    ProposalGroup(ProposalType... types)
    {
        this.types = types;
    }

    public ProposalType[] getTypes()
    {
        return types;
    }
}
