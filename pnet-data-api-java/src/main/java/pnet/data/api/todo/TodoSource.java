package pnet.data.api.todo;

/**
 * The source of the to-do entry.
 *
 * @author HAM
 */
public enum TodoSource
{

    /**
     * The item was caused by some user interaction. It cannot be recovered.
     */
    INTERACTION,

    /**
     * The item was caused by a Caretaker check and will be recovered by each check performed on the reference object.
     */
    CARETAKER_CHECK,

}
