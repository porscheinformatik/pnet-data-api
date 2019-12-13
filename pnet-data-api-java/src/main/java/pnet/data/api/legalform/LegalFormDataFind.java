package pnet.data.api.legalform;

import java.util.List;

import pnet.data.api.util.AbstractFind;
import pnet.data.api.util.FindFunction;
import pnet.data.api.util.Pair;
import pnet.data.api.util.RestrictMatchcode;
import pnet.data.api.util.RestrictUpdatedAfter;

/**
 * @author ham
 */
public class LegalFormDataFind extends AbstractFind<LegalFormItemDTO, LegalFormDataFind>
    implements RestrictMatchcode<LegalFormDataFind>, RestrictUpdatedAfter<LegalFormDataFind>
{

    public LegalFormDataFind(FindFunction<LegalFormItemDTO> findFunction,
        List<Pair<String, Object>> restricts)
    {
        super(findFunction, restricts);
    }
}
