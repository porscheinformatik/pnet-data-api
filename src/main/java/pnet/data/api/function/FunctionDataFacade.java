package pnet.data.api.function;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pnet.data.api.util.ByFuzzySearch;
import pnet.data.api.util.ByMatchcode;

/**
 * API for Functions.
 *
 * @author ham
 */
@RestController
@RequestMapping("/api/v1/functions")
public interface FunctionDataFacade
    extends ByMatchcode<FunctionMatchcode, FunctionDataDTO>, ByFuzzySearch<FunctionItemDTO>
{

    // intentionally left blank

}
