package pnet.data.api.contractstate;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pnet.data.api.util.ByMatchcode;

/**
 * API for contract states.
 *
 * @author ham
 */
@RestController
@RequestMapping("/api/v1/contractstates")
public interface ContractStateDataFacade extends ByMatchcode<ContractStateMatchcode, ContractStateDataDTO>
{

    // intentionally left blank

}
