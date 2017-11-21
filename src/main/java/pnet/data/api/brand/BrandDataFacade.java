package pnet.data.api.brand;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pnet.data.api.util.ByMatchcode;

/**
 * API for brands.
 *
 * @author ham
 */
@RestController
@RequestMapping("/api/v1/brands")
public interface BrandDataFacade extends ByMatchcode<BrandMatchcode, BrandDataDTO>
{

    // intentionally left blank

}
