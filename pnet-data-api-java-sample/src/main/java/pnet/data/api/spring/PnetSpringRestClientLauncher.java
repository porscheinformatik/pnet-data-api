package pnet.data.api.spring;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class PnetSpringRestClientLauncher
{
    public static void main(String[] args)
    {
        try (AnnotationConfigApplicationContext context =
            new AnnotationConfigApplicationContext(PnetSpringRestClientConfig.class))
        {
            context.getBean(PnetSpringRestClient.class).consume();
        }
    }

}
