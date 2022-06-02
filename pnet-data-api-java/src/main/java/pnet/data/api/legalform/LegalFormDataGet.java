package pnet.data.api.legalform;

import java.util.List;

import pnet.data.api.util.AbstractGet;
import pnet.data.api.util.ByMatchcode;
import pnet.data.api.util.GetFunction;
import pnet.data.api.util.Pair;

/**
 * @author ham
 */
public class LegalFormDataGet extends AbstractGet<LegalFormDataDTO, LegalFormDataGet>
    implements ByMatchcode<LegalFormDataDTO, LegalFormDataGet>
{

    public LegalFormDataGet(GetFunction<LegalFormDataDTO> getFunction, List<Pair<String, Object>> restricts)
    {
        super(getFunction, restricts);
    }

}
