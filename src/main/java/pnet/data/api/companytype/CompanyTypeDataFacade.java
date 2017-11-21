package pnet.data.api.companytype;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pnet.data.api.util.ByMatchcode;

/**
 * API for company types.
 *
 * @author ham
 */
@RestController
@RequestMapping("/api/v1/companytypes")
public interface CompanyTypeDataFacade extends ByMatchcode<CompanyTypeMatchcode, CompanyTypeDataDTO>
{

    // intentionally left blank

}
