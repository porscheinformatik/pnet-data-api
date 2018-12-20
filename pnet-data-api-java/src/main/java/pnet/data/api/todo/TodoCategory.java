package pnet.data.api.todo;

/**
 * Categorizes todos. The category specifies the meaning of the reference id. All todos with the same category and
 * reference id can be collected to a group.
 *
 * @author HAM
 */
public enum TodoCategory
{

    FUNCTION,

    ACTIVITY,

    INFOAREA,

    ROLE,

    MENUITEM,

    MODULE,

    APPLICATION,

    PERSON,

    COMPANY,

    SYSTEMUSER,

    NOTIFY,

    INVALID

}
