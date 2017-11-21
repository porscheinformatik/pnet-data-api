package pnet.data.api.numbertype;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pnet.data.api.util.ByMatchcode;

/**
 * API for number types.
 *
 * @author ham
 */
@RestController
@RequestMapping("/api/v1/numbertypes")
public interface NumberTypeDataFacade extends ByMatchcode<NumberTypeMatchcode, NumberTypeDataDTO>
{

    // intentionally left blank

}
