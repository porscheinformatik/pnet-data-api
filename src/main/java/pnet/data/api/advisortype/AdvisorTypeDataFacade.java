package pnet.data.api.advisortype;

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
public interface AdvisorTypeDataFacade extends ByMatchcode<AdvisorTypeMatchcode, AdvisorTypeDataDTO>
{

    // intentionally left blank

}
