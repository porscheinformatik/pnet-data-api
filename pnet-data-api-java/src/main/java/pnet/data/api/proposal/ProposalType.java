package pnet.data.api.proposal;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum ProposalType
{
    MENU_ITEM_CREATE(ProposalGroup.MENU_ITEM, ProposalAction.CREATE),

    MENU_ITEM_UPDATE(ProposalGroup.MENU_ITEM, ProposalAction.UPDATE),

    MENU_ITEM_DELETE(ProposalGroup.MENU_ITEM, ProposalAction.DELETE),

    ACTIVITY_CREATE(ProposalGroup.ACTIVITY, ProposalAction.CREATE),

    ACTIVITY_UPDATE(ProposalGroup.ACTIVITY, ProposalAction.UPDATE),

    ACTIVITY_DELETE(ProposalGroup.ACTIVITY, ProposalAction.DELETE),

    FUNCTION_CREATE(ProposalGroup.FUNCTION, ProposalAction.CREATE),

    FUNCTION_UPDATE(ProposalGroup.FUNCTION, ProposalAction.UPDATE),

    FUNCTION_DELETE(ProposalGroup.FUNCTION, ProposalAction.DELETE),

    OTHER(ProposalGroup.OTHER, ProposalAction.OTHER);

    private final ProposalGroup group;
    private final ProposalAction action;

    ProposalType(ProposalGroup group, ProposalAction action)
    {
        this.group = group;
        this.action = action;
    }

    public ProposalGroup getGroup()
    {
        return group;
    }

    public ProposalAction getAction()
    {
        return action;
    }

    @JsonProperty("group")
    public String getGroupName()
    {
        return group.name();
    }
}
