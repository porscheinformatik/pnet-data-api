package pnet.data.api.proposal;

/**
 * Defines the action of a maintenance proposal
 * 
 * @author scar
 */
public enum ProposalAction
{
    CREATE(ProposalType.MENU_ITEM_CREATE, ProposalType.FUNCTION_CREATE, ProposalType.ACTIVITY_CREATE),

    UPDATE(ProposalType.MENU_ITEM_UPDATE, ProposalType.FUNCTION_UPDATE, ProposalType.ACTIVITY_UPDATE),

    DELETE(ProposalType.MENU_ITEM_DELETE, ProposalType.FUNCTION_DELETE, ProposalType.ACTIVITY_DELETE);

    private final ProposalType[] types;

    ProposalAction(ProposalType... types)
    {
        this.types = types;
    }

    public ProposalType[] getTypes()
    {
        return types;
    }
}
