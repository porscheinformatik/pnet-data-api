package pnet.data.api.util;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import java.util.List;
import org.junit.jupiter.api.Test;

class RestrictTest {

    @Test
    void convertsNullEmptyAndPopulatedCollectionsToArrays() {
        assertThat(Restrict.toArray(null, new String[0]), emptyArray());
        assertThat(Restrict.toArray(List.of(), new String[0]), emptyArray());
        assertThat(Restrict.toArray(List.of("first", "second"), new String[0]), arrayContaining("first", "second"));
        assertThat(Restrict.toArray(null), emptyArray());
    }
}
