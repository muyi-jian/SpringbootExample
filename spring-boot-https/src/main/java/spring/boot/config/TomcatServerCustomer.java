package spring.boot.config;

/**
 * @author yangjian
 * @date 2022/11/25 10:51
 */
/*@Component
public class TomcatServerCustomer implements WebServerFactoryCustomizer<TomcatServletWebServerFactory> {

    @Override
    public void customize(TomcatServletWebServerFactory factory) {
//      final Connector connector = new Connector("HTTP/1.1");
        Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
        connector.setScheme("http");
        connector.setPort(8081);
        factory.addAdditionalTomcatConnectors(connector);
    }
}*/
