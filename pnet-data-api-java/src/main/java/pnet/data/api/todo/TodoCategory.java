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

    PERSON_LOCK,

    COMPANY,

    SYSTEMUSER,

    AUDITOR,

    NOTIFY,

    INVALID,

    // Only needed for translation

    TEXT,

    SCOPE,

    CONTRACTTYPE,

    CONTACTTYPE,

    COMPANYTYPE,

    BRAND,

    NUMBERSTYPE

}
