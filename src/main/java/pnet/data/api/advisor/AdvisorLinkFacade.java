package pnet.data.api.advisor;

import java.util.Collection;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import pnet.data.api.advisordivision.AdvisorDivisionMatchcode;
import pnet.data.api.advisortype.AdvisorTypeMatchcode;

/**
 * Provides access to advisors.
 *
 * @author ham
 */
@RestController
@RequestMapping("/api/v1/advisors")
public interface AdvisorLinkFacade
{

    @RequestMapping(value = "/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    Collection<AdvisorLinkDTO> getAll(@RequestParam(value = "companyId", required = false) Integer companyId,
        @RequestParam(value = "personId", required = false) Integer personId,
        @RequestParam(value = "advisorTypeMatchcode", required = false) AdvisorTypeMatchcode advisorTypeMatchcode,
        @RequestParam(value = "advisorDivisionMatchcode",
            required = false) AdvisorDivisionMatchcode advisorDivisionMatchcode);

}
