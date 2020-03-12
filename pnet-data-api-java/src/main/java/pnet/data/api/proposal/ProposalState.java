package pnet.data.api.proposal;

/**
 * The state of a proposal
 *
 * @author ham
 */
public enum ProposalState
{

    MAIDEN(false, ProposalPersonType.PROPOSER),

    ASSIGNED(false, ProposalPersonType.IMPLEMENTOR),

    TESTABLE(true, ProposalPersonType.PROPOSER),

    TESTED(false, ProposalPersonType.IMPLEMENTOR),

    DONE(true, ProposalPersonType.PROPOSER, ProposalPersonType.IMPLEMENTOR, ProposalPersonType.FUNCTIONALLY_RESPONSIBLE,
        ProposalPersonType.TECHNICALLY_RESPONSIBLE);

    private final boolean restricted;
    private final ProposalPersonType[] notificationPersonTypes;

    ProposalState(boolean restricted, ProposalPersonType... notificationPersonTypes)
    {
        this.restricted = restricted;
        this.notificationPersonTypes = notificationPersonTypes;
    }

    public boolean isRestricted()
    {
        return restricted;
    }

    public ProposalPersonType[] getNotificationPersonTypes()
    {
        return notificationPersonTypes;
    }

}
