package pnet.data.api.util;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import org.junit.jupiter.api.Test;

class CipherHelperTest {

    @Test
    @Deprecated
    void testBlowfish() {
        CipherHelper helper = CipherHelper.ofBlowfishKey("0123456789abcdefghijkl==");

        assertThat(helper.decode(helper.encode("this is a test")), is("this is a test"));
    }

    @Test
    void testAes() {
        CipherHelper helper = CipherHelper.ofAesKey("paWM8OiEMHOGPWMHIah70geeLpEcJqerwB1OpH8s7Ws=");

        assertThat(helper.decode(helper.encode("this is a test")), is("this is a test"));
    }
}
