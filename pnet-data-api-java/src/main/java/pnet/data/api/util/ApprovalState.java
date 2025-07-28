package pnet.data.api.util;

/**
 * The current state of the approval of a person or an authority. As always, the order of this enum is of importance.
 *
 * @author ham
 */
public enum ApprovalState {
    /**
     * Temporal state after the creation of an item, until the Caretaker (or whoever) sets the corrent state. Can be
     * used when creating items directly in the database.
     */
    DRAFT(true, true),

    /**
     * Waits for approval. The auditors may be determined by looking into the FPNFA_AUDITORs table. If an entity enters
     * this state, a Proposal will be generated (if not yet available) with a link to the change reason and some more
     * information.
     */
    PENDING(true, true),

    /**
     * Approval has been granted. Still waiting for another approval, like a function waits for the approval of the user
     * or the activity waits for the approval of the function. This state will also be used, when there is no approval
     * needed or if the activity (with no approval needed) waits for the approval of the function. It's the
     * responsibility of the interpreter (or Mr. Brain) to use and advance this state at the appropriate time.
     */
    APPROVED(true, false),

    /**
     * The approval has been rejected, the authority will not be available.<br>
     * <br>
     * May only be entered via PENDING, so a proposal is already available.<br>
     * <br>
     * Activity: if the activity depends on a function, the entry will never leave the state REJECTED, unless the
     * function gets deleted. It the activity gets directly request, it may enter the state PENDING.<br>
     * <br>
     * Function: The function will never leave the state REJECTED, unless the function gets requested once more.<br>
     * <br>
     * Person: The person may never leave this state and will eventually be deleted.
     */
    REJECTED(false, false),

    /**
     * The approval process has been finished, the authority is available, the user is valid. It's the responsibility of
     * the interpreter (or Mr. Brain) to set this state at the appropriate time.
     */
    DONE(false, false);

    private final boolean open;
    private final boolean inWork;

    ApprovalState(boolean open, boolean inWork) {
        this.open = open;
        this.inWork = inWork;
    }

    public boolean isOpen() {
        return open;
    }

    public boolean isInWork() {
        return inWork;
    }

    public ApprovalState higher(ApprovalState otherState) {
        if (otherState == null) {
            return this;
        }

        return isHigherThan(otherState) ? this : otherState;
    }

    public boolean isHigherThan(ApprovalState otherState) {
        if (otherState == null) {
            return false;
        }

        return ordinal() > otherState.ordinal();
    }

    public boolean isSameOrHigherThan(ApprovalState otherState) {
        if (otherState == null) {
            return false;
        }

        return ordinal() >= otherState.ordinal();
    }

    public ApprovalState covering(ApprovalState otherState) {
        if (otherState == null) {
            return this;
        }

        return isCovering(otherState) ? this : otherState;
    }

    /**
     * A state covers another state when it's ordinal is the same or higher than the other ones ordinal.
     * {@link ApprovalState#REJECTED} never covers any other state unless it's REJECTED, too.
     *
     * @param otherState the other state, may be null
     * @return true if this state is covering the other state
     */
    public boolean isCovering(ApprovalState otherState) {
        if (otherState == null) {
            return false;
        }

        if (otherState == this) {
            return true;
        }

        if (this == REJECTED) {
            return otherState == REJECTED;
        }

        if (otherState == REJECTED) {
            return true;
        }

        return isSameOrHigherThan(otherState);
    }
}
