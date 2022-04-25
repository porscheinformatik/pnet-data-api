package pnet.data.api.proposal;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum ProposalType
{
    MENU_ITEM_CREATE(ProposalGroup.MENU_ITEM),

    MENU_ITEM_UPDATE(ProposalGroup.MENU_ITEM),

    MENU_ITEM_DELETE(ProposalGroup.MENU_ITEM),

    ACTIVITY_CREATE(ProposalGroup.ACTIVITY),

    ACTIVITY_UPDATE(ProposalGroup.ACTIVITY),

    ACTIVITY_DELETE(ProposalGroup.ACTIVITY),

    FUNCTION_CREATE(ProposalGroup.FUNCTION),

    FUNCTION_UPDATE(ProposalGroup.FUNCTION),

    FUNCTION_DELETE(ProposalGroup.FUNCTION),

    OTHER(ProposalGroup.OTHER);

    private final ProposalGroup group;

    ProposalType(ProposalGroup group)
    {
        this.group = group;
    }

    public ProposalGroup getGroup()
    {
        return group;
    }

    @JsonProperty("group")
    public String getGroupName()
    {
        return group.name();
    }
}
