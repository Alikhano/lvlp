package task7.config;


import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import task7.domain.Producer;


import java.util.HashMap;
import java.util.Map;

public class TestHibernateConfiguration {

    private static SessionFactory factory;

    static {
        Map<String, String> properties = new HashMap<>();
        properties.put("hibernate.dialect","org.hibernate.dialect.H2Dialect");
        properties.put("hibernate.connection.driver_class", "org.h2.Driver");
        properties.put("hibernate.connection.url", "jdbc:h2:mem:onlineshop2;INIT=create schema if not exists onlineshop2");
        properties.put("hibernate.connection.username", "sa");
        properties.put("hibernate.connection.password", "");
        properties.put("hibernate.hbm2ddl.auto", "create-drop");
        properties.put("hibernate.show_sql", "true");
        properties.put("hibernate.format_sql", "true");

        final ServiceRegistry serviceRegistry =  new StandardServiceRegistryBuilder().applySettings(properties).build();
        factory = new Configuration().addAnnotatedClass(Producer.class).buildSessionFactory(serviceRegistry);

    }

    public static SessionFactory getFactory() {
        return factory;
    }
}
