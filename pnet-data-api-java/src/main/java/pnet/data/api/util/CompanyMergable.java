package pnet.data.api.util;

import pnet.data.api.company.CompanyMerge;

/**
 * Enabled merging for companies.
 *
 * @param <SELF> the type of the restrict for chaining
 * @author ham
 */
public interface CompanyMergable<SELF extends Restrict<SELF>> extends Restrict<SELF>
{

    default SELF mergeInternetGroups()
    {
        return merge(CompanyMerge.INTERNET_GROUP);
    }

    default SELF merge(CompanyMerge companyMerge)
    {
        return restrict("merge", companyMerge);
    }

}
