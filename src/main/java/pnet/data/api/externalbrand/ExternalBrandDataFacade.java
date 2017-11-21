package pnet.data.api.externalbrand;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pnet.data.api.util.ByMatchcode;

/**
 * API for external brands.
 *
 * @author ham
 */
@RestController
@RequestMapping("/api/v1/externalbrands")
public interface ExternalBrandDataFacade extends ByMatchcode<ExternalBrandMatchcode, ExternalBrandDataDTO>
{

    // intentionally left blank

}
