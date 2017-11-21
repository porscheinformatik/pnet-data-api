package pnet.data.api.companygrouptype;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pnet.data.api.util.ByMatchcode;

/**
 * API for company group types.
 *
 * @author ham
 */
@RestController
@RequestMapping("/api/v1/companygrouptypes")
public interface CompanyGroupTypeDataFacade extends ByMatchcode<CompanyGroupTypeMatchcode, CompanyGroupTypeDataDTO>
{

    // intentionally left blank

}
