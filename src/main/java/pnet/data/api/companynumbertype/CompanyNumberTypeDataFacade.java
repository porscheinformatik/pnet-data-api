package pnet.data.api.companynumbertype;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pnet.data.api.util.ByMatchcode;

/**
 * API for company number types.
 *
 * @author ham
 */
@RestController
@RequestMapping("/api/v1/companynumbertypes")
public interface CompanyNumberTypeDataFacade extends ByMatchcode<CompanyNumberTypeMatchcode, CompanyNumberTypeDataDTO>
{

    // intentionally left blank

}
