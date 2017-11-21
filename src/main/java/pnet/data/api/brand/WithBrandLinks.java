package pnet.data.api.brand;

import java.util.Collection;

/**
 * Used for items that contains lists for brand links.
 *
 * @author ham
 */
public interface WithBrandLinks
{

    Collection<? extends BrandLink> getBrands();
    
}
