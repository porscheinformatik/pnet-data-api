package at.porscheinformatik.happyrest;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

class RestCallTest
{
    @ParameterizedTest
    @EnumSource(MockedRestCallFactory.class)
    void testVariable(MockedRestCallFactory factory) throws RestException
    {
        MockedRestResponse<Void> response = (MockedRestResponse<Void>) factory
            .url("https://example.com/data/{variable}/list")
            .variable("variable", "oldValue")
            .variable("variable", "value")
            .invoke(RestMethod.GET, Void.class);

        assertThat(response.getRequestMethod(), equalTo("GET"));
        assertThat(response.getRequestUrl(), equalTo("https://example.com/data/value/list"));
    }

    @ParameterizedTest
    @EnumSource(MockedRestCallFactory.class)
    void testVariableWithSpaces(MockedRestCallFactory factory) throws RestException
    {
        MockedRestResponse<Void> response = (MockedRestResponse<Void>) factory
            .url("https://example.com/data/{variable}/list")
            .variable("variable", "variable with spaces")
            .invoke(RestMethod.GET, Void.class);

        assertThat(response.getRequestMethod(), equalTo("GET"));
        assertThat(response.getRequestUrl(), equalTo("https://example.com/data/variable%20with%20spaces/list"));
    }

    @ParameterizedTest
    @EnumSource(MockedRestCallFactory.class)
    void testVariableWithSlashes(MockedRestCallFactory factory) throws RestException
    {
        MockedRestResponse<Void> response = (MockedRestResponse<Void>) factory
            .url("https://example.com/data/{variable}/list?key=value")
            .variable("variable", "variable/slash")
            .invoke(RestMethod.GET, Void.class);

        assertThat(response.getRequestMethod(), equalTo("GET"));
        assertThat(response.getRequestUrl(), equalTo("https://example.com/data/variable%2Fslash/list?key=value"));
    }

    @ParameterizedTest
    @EnumSource(MockedRestCallFactory.class)
    void testVariableWithApostrophes(MockedRestCallFactory factory) throws RestException
    {
        MockedRestResponse<Void> response = (MockedRestResponse<Void>) factory
            .url("https://example.com/data/{variable}")
            .variable("variable", "\"value\"")
            .invoke(RestMethod.GET, Void.class);

        assertThat(response.getRequestMethod(), equalTo("GET"));
        assertThat(response.getRequestUrl(), equalTo("https://example.com/data/%22value%22"));
    }

    @ParameterizedTest
    @EnumSource(MockedRestCallFactory.class)
    void testVariableWithCurlies(MockedRestCallFactory factory) throws RestException
    {
        MockedRestResponse<Void> response = (MockedRestResponse<Void>) factory
            .url("https://example.com/data/{variable}/list")
            .variable("variable", "{value}")
            .variable("value", "{anotherValue}")
            .invoke(RestMethod.GET, Void.class);

        assertThat(response.getRequestMethod(), equalTo("GET"));
        assertThat(response.getRequestUrl(), equalTo("https://example.com/data/%7Bvalue%7D/list"));
    }

    @ParameterizedTest
    @EnumSource(MockedRestCallFactory.class)
    void testVariableWithUmlaut(MockedRestCallFactory factory) throws RestException
    {
        MockedRestResponse<Void> response = (MockedRestResponse<Void>) factory
            .url("https://example.com/data/{variable}/list")
            .variable("variable", "äöüÄÖÜß")
            .invoke(RestMethod.GET, Void.class);

        assertThat(response.getRequestMethod(), equalTo("GET"));
        assertThat(response.getRequestUrl(),
            equalTo("https://example.com/data/%C3%A4%C3%B6%C3%BC%C3%84%C3%96%C3%9C%C3%9F/list"));
    }

    @ParameterizedTest
    @EnumSource(MockedRestCallFactory.class)
    void testMissingVariable(MockedRestCallFactory factory) throws RestException
    {
        assertThrows(RestException.class,
            () -> factory.url("https://example.com/data/{variable}").invoke(RestMethod.GET, Void.class));
    }

    @ParameterizedTest
    @EnumSource(MockedRestCallFactory.class)
    void testParameter(MockedRestCallFactory factory) throws RestException
    {
        MockedRestResponse<Void> response = (MockedRestResponse<Void>) factory
            .url("https://example.com")
            .parameter("key", "value")
            .invoke(RestMethod.GET, Void.class);

        assertThat(response.getRequestMethod(), equalTo("GET"));

        if (factory == MockedRestCallFactory.APACHE_5)
        {
            assertThat(response.getRequestUrl(), equalTo("https://example.com/?key=value"));
        }
        else
        {
            assertThat(response.getRequestUrl(), equalTo("https://example.com?key=value"));
        }
    }

    @ParameterizedTest
    @EnumSource(MockedRestCallFactory.class)
    void testReplaceParameter(MockedRestCallFactory factory) throws RestException
    {
        MockedRestResponse<Void> response = (MockedRestResponse<Void>) factory
            .url("https://example.com")
            .parameter("key", "value")
            .replaceParameter("key", "otherValue")
            .invoke(RestMethod.GET, Void.class);

        assertThat(response.getRequestMethod(), equalTo("GET"));

        if (factory == MockedRestCallFactory.APACHE_5)
        {
            assertThat(response.getRequestUrl(), equalTo("https://example.com/?key=otherValue"));
        }
        else
        {
            assertThat(response.getRequestUrl(), equalTo("https://example.com?key=otherValue"));
        }
    }

    @ParameterizedTest
    @EnumSource(MockedRestCallFactory.class)
    void testRemoveParameter(MockedRestCallFactory factory) throws RestException
    {
        MockedRestResponse<Void> response = (MockedRestResponse<Void>) factory
            .url("https://example.com")
            .parameter("key", "value")
            .removeParameters("key")
            .invoke(RestMethod.GET, Void.class);

        assertThat(response.getRequestMethod(), equalTo("GET"));

        if (factory == MockedRestCallFactory.APACHE_5)
        {
            assertThat(response.getRequestUrl(), equalTo("https://example.com/"));
        }
        else
        {
            assertThat(response.getRequestUrl(), equalTo("https://example.com"));
        }
    }

    @ParameterizedTest
    @EnumSource(MockedRestCallFactory.class)
    void testParameterWithSpaces(MockedRestCallFactory factory) throws RestException
    {
        MockedRestResponse<Void> response = (MockedRestResponse<Void>) factory
            .url("https://example.com/")
            .parameter("key with spaces", "value with spaces")
            .invoke(RestMethod.GET, Void.class);

        assertThat(response.getRequestMethod(), equalTo("GET"));

        switch (factory)
        {
            case APACHE:
            case JAVA:
                // Apache and Java use Java's URLEncodes, which uses + for spaces
                assertThat(response.getRequestUrl(), equalTo("https://example.com/?key+with+spaces=value+with+spaces"));
                break;

            case APACHE_5:
            case SPRING:
                assertThat(response.getRequestUrl(),
                    equalTo("https://example.com/?key%20with%20spaces=value%20with%20spaces"));
                break;

            default:
                throw new UnsupportedOperationException("Factory not supported: " + factory.name());
        }
    }

    @ParameterizedTest
    @EnumSource(MockedRestCallFactory.class)
    void testParameterWithSlashes(MockedRestCallFactory factory) throws RestException
    {
        MockedRestResponse<Void> response = (MockedRestResponse<Void>) factory
            .url("https://example.com/list")
            .parameter("key/key", "value/value")
            .invoke(RestMethod.GET, Void.class);

        assertThat(response.getRequestMethod(), equalTo("GET"));

        switch (factory)
        {
            case APACHE:
            case APACHE_5:
            case JAVA:
                assertThat(response.getRequestUrl(), equalTo("https://example.com/list?key%2Fkey=value%2Fvalue"));
                break;

            case SPRING:
                // Spring does not encode / for unknown reasons - a quick Google search shows some controversy about
                // this
                assertThat(response.getRequestUrl(), equalTo("https://example.com/list?key/key=value/value"));
                break;

            default:
                throw new UnsupportedOperationException("Factory not supported: " + factory.name());
        }
    }

    @ParameterizedTest
    @EnumSource(MockedRestCallFactory.class)
    void testParameterWithApostrophes(MockedRestCallFactory factory) throws RestException
    {
        MockedRestResponse<Void> response = (MockedRestResponse<Void>) factory
            .url("https://example.com/list/")
            .parameter("\"key\"", "'value'")
            .invoke(RestMethod.GET, Void.class);

        assertThat(response.getRequestMethod(), equalTo("GET"));

        switch (factory)
        {
            case APACHE:
            case APACHE_5:
            case JAVA:
                // Uses Java's URLEncoder
                assertThat(response.getRequestUrl(), equalTo("https://example.com/list/?%22key%22=%27value%27"));
                break;

            case SPRING:
                // Uses Spring's own encoder
                // Spring does not encode / for unknown reasons - a quick Google search shows some controversy about
                // this
                assertThat(response.getRequestUrl(), equalTo("https://example.com/list/?%22key%22='value'"));
                break;

            default:
                throw new UnsupportedOperationException("Factory not supported: " + factory.name());
        }
    }

    @ParameterizedTest
    @EnumSource(MockedRestCallFactory.class)
    void testParameterWithCurlies(MockedRestCallFactory factory) throws RestException
    {
        MockedRestResponse<Void> response = (MockedRestResponse<Void>) factory
            .url("https://example.com")
            .parameter("{key}", "{value}")
            .variable("key", "anotherKey")
            .variable("value", "anotherValue")
            .invoke(RestMethod.GET, Void.class);

        assertThat(response.getRequestMethod(), equalTo("GET"));

        switch (factory)
        {
            case APACHE_5:
                assertThat(response.getRequestUrl(), equalTo("https://example.com/?%7Bkey%7D=%7Bvalue%7D"));
                break;

            case APACHE:
            case JAVA:
                assertThat(response.getRequestUrl(), equalTo("https://example.com?%7Bkey%7D=%7Bvalue%7D"));
                break;

            case SPRING:
                // Spring does not encode the parameter key and thus replaces the placeholder. Strange behavior :/
                assertThat(response.getRequestUrl(), equalTo("https://example.com?anotherKey=%7Bvalue%7D"));
                break;

            default:
                throw new UnsupportedOperationException("Factory not supported: " + factory.name());
        }
    }

    @ParameterizedTest
    @EnumSource(MockedRestCallFactory.class)
    void testParameterWithUmlaut(MockedRestCallFactory factory) throws RestException
    {
        MockedRestResponse<Void> response = (MockedRestResponse<Void>) factory
            .url("https://example.com")
            .parameter("äöüß", "ÄÖÜß")
            .invoke(RestMethod.GET, Void.class);

        assertThat(response.getRequestMethod(), equalTo("GET"));

        if (factory == MockedRestCallFactory.APACHE_5)
        {
            assertThat(response.getRequestUrl(),
                equalTo("https://example.com/?%C3%A4%C3%B6%C3%BC%C3%9F=%C3%84%C3%96%C3%9C%C3%9F"));
        }
        else
        {
            assertThat(response.getRequestUrl(),
                equalTo("https://example.com?%C3%A4%C3%B6%C3%BC%C3%9F=%C3%84%C3%96%C3%9C%C3%9F"));
        }
    }

    @ParameterizedTest
    @EnumSource(MockedRestCallFactory.class)
    void testParameterWithParametersInUrl(MockedRestCallFactory factory) throws RestException
    {
        MockedRestResponse<Void> response = (MockedRestResponse<Void>) factory
            .url("https://example.com?key=value")
            .parameter("anotherKey", "anotherValue")
            .invoke(RestMethod.GET, Void.class);

        assertThat(response.getRequestMethod(), equalTo("GET"));

        if (factory == MockedRestCallFactory.APACHE_5)
        {
            assertThat(response.getRequestUrl(), equalTo("https://example.com/?key=value&anotherKey=anotherValue"));
        }
        else
        {
            assertThat(response.getRequestUrl(), equalTo("https://example.com?key=value&anotherKey=anotherValue"));
        }
    }

    @ParameterizedTest
    @EnumSource(MockedRestCallFactory.class)
    void testFormParameter(MockedRestCallFactory factory) throws RestException
    {
        MockedRestResponse<Void> response = (MockedRestResponse<Void>) factory
            .url("https://example.com")
            .contentTypeForm()
            .parameter("key", "value")
            .invoke(RestMethod.POST, Void.class);

        assertThat(response.getRequestMethod(), equalTo("POST"));

        if (factory == MockedRestCallFactory.APACHE_5)
        {
            assertThat(response.getRequestUrl(), equalTo("https://example.com/"));
        }
        else
        {
            assertThat(response.getRequestUrl(), equalTo("https://example.com"));
        }

        assertThat(response.getRequestBody(), equalTo("key=value"));
    }

    @ParameterizedTest
    @EnumSource(MockedRestCallFactory.class)
    void testFormParameterWithSpaces(MockedRestCallFactory factory) throws RestException
    {
        MockedRestResponse<Void> response = (MockedRestResponse<Void>) factory
            .url("https://example.com/")
            .contentTypeForm()
            .parameter("key with spaces", "value with spaces")
            .invoke(RestMethod.POST, Void.class);

        assertThat(response.getRequestMethod(), equalTo("POST"));
        assertThat(response.getRequestUrl(), equalTo("https://example.com/"));
        assertThat(response.getRequestBody(), equalTo("key+with+spaces=value+with+spaces"));
    }

    @ParameterizedTest
    @EnumSource(MockedRestCallFactory.class)
    void testFormParameterWithSlashes(MockedRestCallFactory factory) throws RestException
    {
        MockedRestResponse<Void> response = (MockedRestResponse<Void>) factory
            .url("https://example.com/list")
            .contentTypeForm()
            .parameter("key/key", "value/value")
            .invoke(RestMethod.POST, Void.class);

        assertThat(response.getRequestMethod(), equalTo("POST"));
        assertThat(response.getRequestUrl(), equalTo("https://example.com/list"));
        assertThat(response.getRequestBody(), equalTo("key%2Fkey=value%2Fvalue"));
    }

    @ParameterizedTest
    @EnumSource(MockedRestCallFactory.class)
    void testFormParameterWithApostrophes(MockedRestCallFactory factory) throws RestException
    {
        MockedRestResponse<Void> response = (MockedRestResponse<Void>) factory
            .url("https://example.com/list/")
            .contentTypeForm()
            .parameter("\"key\"", "'value'")
            .invoke(RestMethod.POST, Void.class);

        assertThat(response.getRequestMethod(), equalTo("POST"));
        assertThat(response.getRequestUrl(), equalTo("https://example.com/list/"));
        assertThat(response.getRequestBody(), equalTo("%22key%22=%27value%27"));
    }

    @ParameterizedTest
    @EnumSource(MockedRestCallFactory.class)
    void testFormParameterWithCurlies(MockedRestCallFactory factory) throws RestException
    {
        MockedRestResponse<Void> response = (MockedRestResponse<Void>) factory
            .url("https://example.com")
            .contentTypeForm()
            .parameter("{key}", "{value}")
            .variable("key", "anotherKey")
            .variable("value", "anotherValue")
            .invoke(RestMethod.POST, Void.class);

        assertThat(response.getRequestMethod(), equalTo("POST"));

        if (factory == MockedRestCallFactory.APACHE_5)
        {
            assertThat(response.getRequestUrl(), equalTo("https://example.com/"));
        }
        else
        {
            assertThat(response.getRequestUrl(), equalTo("https://example.com"));
        }

        assertThat(response.getRequestBody(), equalTo("%7Bkey%7D=%7Bvalue%7D"));
    }

    @ParameterizedTest
    @EnumSource(MockedRestCallFactory.class)
    void testFormParameterWithUmlaut(MockedRestCallFactory factory) throws RestException
    {
        MockedRestResponse<Void> response = (MockedRestResponse<Void>) factory
            .url("https://example.com")
            .contentTypeForm()
            .parameter("äöüß", "ÄÖÜß")
            .invoke(RestMethod.POST, Void.class);

        assertThat(response.getRequestMethod(), equalTo("POST"));

        if (factory == MockedRestCallFactory.APACHE_5)
        {
            assertThat(response.getRequestUrl(), equalTo("https://example.com/"));
        }
        else
        {
            assertThat(response.getRequestUrl(), equalTo("https://example.com"));
        }

        assertThat(response.getRequestBody(), equalTo("%C3%A4%C3%B6%C3%BC%C3%9F=%C3%84%C3%96%C3%9C%C3%9F"));
    }

    @ParameterizedTest
    @EnumSource(MockedRestCallFactory.class)
    void testFormParameterWithParametersInUrl(MockedRestCallFactory factory) throws RestException
    {
        MockedRestResponse<Void> response = (MockedRestResponse<Void>) factory
            .url("https://example.com?key=value")
            .contentTypeForm()
            .parameter("anotherKey", "anotherValue")
            .invoke(RestMethod.POST, Void.class);

        assertThat(response.getRequestMethod(), equalTo("POST"));

        if (factory == MockedRestCallFactory.APACHE_5)
        {
            assertThat(response.getRequestUrl(), equalTo("https://example.com/?key=value"));
        }
        else
        {
            assertThat(response.getRequestUrl(), equalTo("https://example.com?key=value"));
        }

        assertThat(response.getRequestBody(), equalTo("anotherKey=anotherValue"));
    }

    @ParameterizedTest
    @EnumSource(MockedRestCallFactory.class)
    void testHeader(MockedRestCallFactory factory) throws RestException
    {
        MockedRestResponse<Void> response = (MockedRestResponse<Void>) factory
            .url("https://example.com")
            .header("key", "value")
            .invoke(RestMethod.GET, Void.class);

        assertThat(response.getRequestMethod(), equalTo("GET"));

        if (factory == MockedRestCallFactory.APACHE_5)
        {
            assertThat(response.getRequestUrl(), equalTo("https://example.com/"));
        }
        else
        {
            assertThat(response.getRequestUrl(), equalTo("https://example.com"));
        }

        assertThat(response.getRequestHeader("key"), contains("value"));
    }

    @ParameterizedTest
    @EnumSource(MockedRestCallFactory.class)
    void testHeaderWithSpaces(MockedRestCallFactory factory) throws RestException
    {
        MockedRestResponse<Void> response = (MockedRestResponse<Void>) factory
            .url("https://example.com")
            .header("key", "value with spaces")
            .invoke(RestMethod.GET, Void.class);

        assertThat(response.getRequestMethod(), equalTo("GET"));

        if (factory == MockedRestCallFactory.APACHE_5)
        {
            assertThat(response.getRequestUrl(), equalTo("https://example.com/"));
        }
        else
        {
            assertThat(response.getRequestUrl(), equalTo("https://example.com"));
        }

        assertThat(response.getRequestHeader("key"), contains("value with spaces"));
    }

    @ParameterizedTest
    @EnumSource(MockedRestCallFactory.class)
    void testDuplicateHeader(MockedRestCallFactory factory) throws RestException
    {
        MockedRestResponse<Void> response = (MockedRestResponse<Void>) factory
            .url("https://example.com")
            .header("key", "value1")
            .header("key", "value2")
            .invoke(RestMethod.GET, Void.class);

        assertThat(response.getRequestMethod(), equalTo("GET"));

        if (factory == MockedRestCallFactory.APACHE_5)
        {
            assertThat(response.getRequestUrl(), equalTo("https://example.com/"));
        }
        else
        {
            assertThat(response.getRequestUrl(), equalTo("https://example.com"));
        }

        assertThat(response.getRequestHeader("key"), contains("value1", "value2"));
    }

    @ParameterizedTest
    @EnumSource(MockedRestCallFactory.class)
    void testJsonStringBody(MockedRestCallFactory factory) throws RestException
    {
        MockedRestResponse<Void> response = (MockedRestResponse<Void>) factory
            .url("https://example.com")
            .body("Foobar")
            .invoke(RestMethod.POST, Void.class);

        assertThat(response.getRequestMethod(), equalTo("POST"));
        assertThat(response.getRequestBody(), equalTo("\"Foobar\""));
    }

    @ParameterizedTest
    @EnumSource(MockedRestCallFactory.class)
    void testJsonObjectBody(MockedRestCallFactory factory) throws RestException
    {
        TestObject object = TestObject.of("key", "value");

        MockedRestResponse<Void> response = (MockedRestResponse<Void>) factory
            .url("https://example.com")
            .body(object)
            .invoke(RestMethod.POST, Void.class);

        assertThat(response.getRequestMethod(), equalTo("POST"));
        assertThat(response.getRequestBody(), equalTo(object.toJsonString()));
    }

}
