package pnet.data.api.proposal;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum ProposalType
{

    MENU_ITEM_CREATE(ProposalGroup.MENUITEMS),

    MENU_ITEM_UPDATE(ProposalGroup.MENUITEMS),

    MENU_ITEM_DELETE(ProposalGroup.MENUITEMS),

    ACTIVITY_CREATE(ProposalGroup.ACTIVITIES),

    ACTIVITY_UPDATE(ProposalGroup.ACTIVITIES),

    ACTIVITY_DELETE(ProposalGroup.ACTIVITIES),

    FUNCTION_CREATE(ProposalGroup.FUNCTIONS),

    FUNCTION_UPDATE(ProposalGroup.FUNCTIONS),

    FUNCTION_DELETE(ProposalGroup.FUNCTIONS),

    OTHER(ProposalGroup.OTHERS);

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
