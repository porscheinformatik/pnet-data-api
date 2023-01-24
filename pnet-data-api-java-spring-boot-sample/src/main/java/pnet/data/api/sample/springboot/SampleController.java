package pnet.data.api.sample.springboot;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import pnet.data.api.PnetDataClientException;
import pnet.data.api.function.FunctionDataClient;

@RestController
public class SampleController
{
    @Autowired
    private FunctionDataClient client;

    @GetMapping("/")
    public String index() throws PnetDataClientException
    {
        return client.get().byMatchcode("FU_NW_VK").getLabel(Locale.GERMAN);
    }
}