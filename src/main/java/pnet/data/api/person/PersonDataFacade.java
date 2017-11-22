package pnet.data.api.person;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pnet.data.api.util.ByFuzzySearch;
import pnet.data.api.util.ById;

/**
 * API for contract states.
 *
 * @author ham
 */
@RestController
@RequestMapping("/api/v1/persons")
public interface PersonDataFacade extends ById<PersonDataDTO>, ByFuzzySearch<PersonItemDTO>
{

    // intentionally left blank

}
