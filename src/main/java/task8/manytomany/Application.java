package task8.manytomany;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import task8.manytomany.domain.Producer;
import task8.manytomany.domain.Product;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Application {

    public static void main(String[] args) {
        Map<String, String> properties = new HashMap<>();
        properties.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
        properties.put("hibernate.connection.driver_class", "org.h2.Driver");
        properties.put("hibernate.connection.url", "jdbc:h2:mem:onlineshop;INIT=create schema if not exists onlineshop");
        properties.put("hibernate.connection.username", "sa");
        properties.put("hibernate.connection.password", "");
        properties.put("hibernate.hbm2ddl.auto", "create-drop");
        properties.put("hibernate.show_sql", "true");
        properties.put("hibernate.format_sql", "true");

        final ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(properties).build();
        SessionFactory factory = new Configuration()
                .addAnnotatedClass(Producer.class)
                .addAnnotatedClass(task8.manytomany.domain.Product.class)
                .buildSessionFactory(serviceRegistry);

        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();

        Set<Product> products = new HashSet<>();

        Product product = new Product();
        product.setName("phone");
        product.setWeight(0.3d);
        product.setProductId(1);
        Product product1 = new Product();
        product1.setName("smart watch");
        product1.setWeight(0.2d);
        product1.setProductId(2);

        products.add(product);
        products.add(product1);

        Producer producer = new Producer("Apple", "NY", products);
        Producer producer1 = new Producer("Samsung", "Beijing", products);
        Producer producer2 = new Producer();
        producer2.setName("Ford");
        producer2.setAddress("US");
        Producer producer3 = new Producer();
        producer3.setName("Renault");
        producer3.setAddress("FR");

        session.persist(producer);
        session.persist(producer1);

        transaction.commit();
        session.close();

        System.out.println("after commit");
        Session s = factory.openSession();
        Transaction t = s.beginTransaction();

        Producer savedProducer = s.find(Producer.class,1);
        Producer savedProducer1 = s.find(Producer.class,2);
        Product savedProduct = s.find(Product.class, 1);

        System.out.println(savedProducer.getName());
        System.out.println(savedProducer.getProductSet().size());
        savedProducer.getProductSet().forEach(pr -> {
            System.out.println(pr.getProductId() + " " + pr.getName());
        });

        System.out.println(savedProducer1.getName());
        System.out.println(savedProducer1.getProductSet().size());
        savedProducer1.getProductSet().forEach(product3 -> {
            System.out.println(product3.getProductId() + " " + product3.getName());
        });

        System.out.println(savedProduct.getName());
        System.out.println(savedProduct.getProducerSet().size());
        savedProduct.getProducerSet().forEach(p -> {
            System.out.println(p.getProducerId() + " " + p.getName() + " " + p.getAddress());
        });

        t.commit();
        s.close();

    }
}
