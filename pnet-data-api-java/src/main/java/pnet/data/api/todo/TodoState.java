package pnet.data.api.todo;

/**
 * The state of a to-do entry.
 *
 * @author HAM
 */
public enum TodoState
{
    /**
     * The item waits to be approved.
     */
    PENDING(TodoPhase.IN_APPROVAL),

    /**
     * The item is waiting for it's execution.
     */
    PLANNED(TodoPhase.IN_PROGRESS),

    /**
     * The item get's executed right now.
     */
    EXECUTING(TodoPhase.IN_PROGRESS),

    /**
     * The item was executed and has been finished.
     */
    DONE(TodoPhase.COMPLETED),

    /**
     * The item has been acknowledged, which is a special form of done.
     */
    ACKNOWLEDGED(TodoPhase.COMPLETED),

    /**
     * The execution has failed.
     */
    FAILED(TodoPhase.COMPLETED),

    /**
     * The item has been rejected.
     */
    REJECTED(TodoPhase.COMPLETED),

    /**
     * The item has been ignored, which is a special form of rejected.
     */
    IGNORED(TodoPhase.COMPLETED);

    private final TodoPhase phase;

    TodoState(TodoPhase phase)
    {
        this.phase = phase;
    }

    public TodoPhase getPhase()
    {
        return phase;
    }

    public static boolean isRejected(TodoState state)
    {
        return state == REJECTED || state == TodoState.IGNORED;
    }

    public static boolean isFinished(TodoState state)
    {
        return state == DONE || state == IGNORED || state == ACKNOWLEDGED || state == FAILED || state == REJECTED;
    }

}
