package pnet.data.api.companygroup;

import java.util.Collection;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import pnet.data.api.advisor.AdvisorLinkDTO;
import pnet.data.api.companygrouptype.CompanyGroupTypeMatchcode;

/**
 * API for company groups.
 *
 * @author ham
 */
@RestController
@RequestMapping("/api/v1/companygroups")
public interface CompanyGroupLinkFacade
{

    @RequestMapping(value = "/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    Collection<AdvisorLinkDTO> getAll(
        @RequestParam(value = "leadingCompanyId", required = false) Integer leadingCompanyId,
        @RequestParam(value = "type", required = false) CompanyGroupTypeMatchcode type);

}
