package pnet.data.api;

import pnet.data.api.util.Restrict;

/**
 * Interface for domain-specific CLI helper modules of {@link PnetRestClient}. Each module handles its own restriction
 * logic via {@link #applyRestrictions(Restrict)}.
 */
public interface PnetRestClientModule {
    default <T extends Restrict<T>> T applyRestrictions(T request) {
        return request;
    }

    default void clearRestrictions() {
        // no-op by default
    }
}
