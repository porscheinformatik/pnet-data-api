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
    PENDING,

    /**
     * The item is waiting for it's execution.
     */
    PLANNED,

    /**
     * The item get's executed right now.
     */
    EXECUTING,

    /**
     * The item was executed and has been finished.
     */
    DONE,

    /**
     * The item has been acknowledged, which is a special form of done.
     */
    ACKNOWLEDGED,

    /**
     * The execution has failed.
     */
    FAILED,

    /**
     * The item has been rejected.
     */
    REJECTED,

    /**
     * The item has been ignored, which is a special form of rejected.
     */
    IGNORED;

    public static boolean isRejected(TodoState state)
    {
        return state == REJECTED || state == TodoState.IGNORED;
    }

    public static boolean isFinished(TodoState state)
    {
        return state == DONE || state == IGNORED || state == ACKNOWLEDGED || state == FAILED || state == REJECTED;
    }

}
