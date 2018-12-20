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
     * The execution has failed.
     */
    FAILED
}
