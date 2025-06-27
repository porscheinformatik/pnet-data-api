package pnet.data.api.person;

/**
 * Enumerates the possible reasons for locking a person's data record.
 * <ul>
 * <li>{@link #RECERTIFICATION_EXPIRED}: The person's data was locked because their recertification period has
 * expired.</li>
 * <li>{@link #SAP_INACTIVE_STATE}: The person's data was locked due to their status being inactive in the SAP
 * system.</li>
 * <li>{@link #MANUAL}: The person's data was locked manually</li>
 * </ul>
 */
public enum PersonLockType
{
    RECERTIFICATION_EXPIRED,

    SAP_INACTIVE_STATE,

    MANUAL
}
