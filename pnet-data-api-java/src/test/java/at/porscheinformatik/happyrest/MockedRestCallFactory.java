package at.porscheinformatik.happyrest;

import at.porscheinformatik.happyrest.apache.MockedApacheRestCallFactory;
import at.porscheinformatik.happyrest.java.MockedJavaRestCallFactory;
import at.porscheinformatik.happyrest.spring.MockedSpringRestCallFactory;

public enum MockedRestCallFactory implements RestCallFactory
{
    APACHE(MockedApacheRestCallFactory.createMock()),
    JAVA(MockedJavaRestCallFactory.createMock()),
    SPRING(MockedSpringRestCallFactory.createMock());

    private final RestCallFactory factory;

    MockedRestCallFactory(RestCallFactory factory)
    {
        this.factory = factory;
    }

    @Override
    public RestCall url(String url)
    {
        return factory.url(url);
    }
}
