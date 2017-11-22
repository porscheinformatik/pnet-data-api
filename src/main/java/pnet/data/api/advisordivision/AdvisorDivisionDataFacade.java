package pnet.data.api.advisordivision;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pnet.data.api.util.ByMatchcode;

/**
 * API for advisor types.
 *
 * @author ham
 */
@RestController
@RequestMapping("/api/v1/advisortypes")
public interface AdvisorDivisionDataFacade extends ByMatchcode<AdvisorDivisionMatchcode, AdvisorDivisonDataDTO>
{

    // intentionally left blank

}
