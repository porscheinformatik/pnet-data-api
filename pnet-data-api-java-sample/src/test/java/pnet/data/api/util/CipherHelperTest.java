package pnet.data.api.util;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

class CipherHelperTest {

    @Test
    void testAes() {
        CipherHelper helper = CipherHelper.ofAesKey("paWM8OiEMHOGPWMHIah70geeLpEcJqerwB1OpH8s7Ws=");

        MatcherAssert.assertThat(helper.decode(helper.encode("this is a test")), Matchers.is("this is a test"));
    }
}
