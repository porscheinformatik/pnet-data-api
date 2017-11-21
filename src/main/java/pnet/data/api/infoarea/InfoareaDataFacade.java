package pnet.data.api.infoarea;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pnet.data.api.util.ByFuzzySearch;
import pnet.data.api.util.ByMatchcode;

/**
 * API for infoareas.
 *
 * @author ham
 */
@RestController
@RequestMapping("/api/v1/infoareas")
public interface InfoareaDataFacade
    extends ByMatchcode<InfoareaMatchcode, InfoareaDataDTO>, ByFuzzySearch<InfoareaItemDTO>
{

    // intentionally left blank

}
