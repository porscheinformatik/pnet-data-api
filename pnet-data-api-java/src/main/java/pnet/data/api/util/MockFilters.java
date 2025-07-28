package pnet.data.api.util;

import static pnet.data.api.PnetDataConstants.*;

import java.util.Collection;
import java.util.Objects;
import java.util.function.BiPredicate;
import java.util.function.Function;
import pnet.data.api.brand.BrandLinkDTO;
import pnet.data.api.brand.WithBrandLinks;

/**
 * A collection of filter handlers for the {@link MockStore}
 *
 * @author HAM
 */
public interface MockFilters {
    static <EntryT, ValueT> BiPredicate<EntryT, ValueT> ignore() {
        return null;
    }

    static <ContainerT, ValueT> BiPredicate<ContainerT, ValueT> whenEquals(Function<ContainerT, ValueT> getter) {
        return (container, value) -> Objects.equals(getter.apply(container), value);
    }

    static <ContainerT, CollectionT extends Collection<ValueT>, ValueT> BiPredicate<
        ContainerT,
        ValueT
    > whenCollectionContains(Function<ContainerT, CollectionT> getter) {
        return withCollection(getter, whenEquals(Function.identity()));
    }

    @SafeVarargs
    static <ContainerT> BiPredicate<ContainerT, Object> whenMatches(Function<ContainerT, ?>... getters) {
        return (container, value) -> {
            String stringValue = String.valueOf(value);

            for (Function<ContainerT, ?> getter : getters) {
                String property = String.valueOf(getter.apply(container));

                if (property.contains(stringValue)) {
                    return true;
                }
            }

            return false;
        };
    }

    static <ContainerT, ValueT extends Comparable<ValueT>> BiPredicate<ContainerT, ValueT> whenAfter(
        Function<ContainerT, ValueT> getter
    ) {
        return (container, value) -> getter.apply(container).compareTo(value) > 0;
    }

    static <ContainerT, InnerContainerT, CollectionT extends Collection<InnerContainerT>, ValueT> BiPredicate<
        ContainerT,
        ValueT
    > withCollection(
        Function<ContainerT, CollectionT> collectionGetter,
        BiPredicate<InnerContainerT, ValueT> predicate
    ) {
        return (container, value) -> {
            CollectionT collection = collectionGetter.apply(container);

            return collection != null && collection.stream().anyMatch(item -> predicate.test(item, value));
        };
    }

    static <T extends WithLabel> void addDefaultLabelQueryFilter(MockStore<T> store) {
        store.addFilter(QUERY_KEY, whenMatches(WithLabel::getLabel));
    }

    static <T extends WithDescription> void addDefaultDescriptionQueryFilter(MockStore<T> store) {
        store.addFilter(QUERY_KEY, whenMatches(WithDescription::getDescription));
    }

    static <T extends WithMatchcode> void addDefaultMatchcodeFilter(MockStore<T> store) {
        store.addFilter(MATCHCODE_KEY, whenEquals(WithMatchcode::getMatchcode));
    }

    static <T extends WithTenants> void addDefaultTenantsFilter(MockStore<T> store) {
        store.addFilter(TENANT_KEY, whenCollectionContains(WithTenants::getTenants));
    }

    static <T extends WithBrandLinks> void addDefaultBrandFilter(MockStore<T> store) {
        store.addFilter(BRAND_KEY, withCollection(WithBrandLinks::getBrands, whenEquals(BrandLinkDTO::getMatchcode)));
    }

    static <T extends WithLastUpdate> void addDefaultLastUpdateFilter(MockStore<T> store) {
        store.addFilter(UPDATED_AFTER_KEY, whenAfter(WithLastUpdate::getLastUpdate));
    }

    static <T> void addDefaultScrollDummy(MockStore<T> store) {
        store.addFilter(SCROLL_KEY, ignore());
    }
}
