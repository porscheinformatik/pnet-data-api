package pnet.data.api.activity;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pnet.data.api.util.ByFuzzySearch;
import pnet.data.api.util.ByMatchcode;

/**
 * API for Activities.
 *
 * @author ham
 */
@RestController
@RequestMapping("/api/v1/activities")
public interface ActivityDataFacade
    extends ByMatchcode<ActivityMatchcode, ActivityDataDTO>, ByFuzzySearch<ActivityItemDTO>
{

    // intentionally left blank

}
