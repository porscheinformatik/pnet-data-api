package pnet.data.api.util;

/**
 * A item, that uses a {@link Matchcode} as universal identifier.
 *
 * @author ham
 * @param <MatchcodeT> the type of {@link Matchcode}
 */
public interface WithMatchcode<MatchcodeT extends Matchcode>
{

    /**
     * @return The unique, alpha-numeric key of the item. This key is the same in all environments.
     */
    MatchcodeT getMatchcode();

}
