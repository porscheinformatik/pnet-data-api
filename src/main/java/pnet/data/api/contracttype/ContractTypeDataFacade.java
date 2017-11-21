package pnet.data.api.contracttype;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pnet.data.api.util.ByMatchcode;

/**
 * API for contract types.
 *
 * @author ham
 */
@RestController
@RequestMapping("/api/v1/contracttypes")
public interface ContractTypeDataFacade extends ByMatchcode<ContractTypeMatchcode, ContractTypeDataDTO>
{

    // intentionally left blank

}
