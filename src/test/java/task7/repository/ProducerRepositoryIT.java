package task7.repository;


import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import task7.config.TestHibernateConfiguration;
import task7.domain.Producer;
import task7.repository.impl.ProducerRepositoryImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class ProducerRepositoryIT {

    private static ProducerRepository repository;

    @BeforeAll
    public static void setupOnce() {repository = new ProducerRepositoryImpl(TestHibernateConfiguration.getFactory());}

    @Test
    public void testCreate() {
        int id = 6;
        String name = "Apple";
        String address = "NY";

        final Producer result = repository.createProducer(id, name, address);
        assertEquals(id, result.getId());
        assertEquals(name, result.getName());
        assertEquals(address, result.getAddress());
    }

    @Test
    public void testGetById() {
        int id = 7;
        String name = "Dell";
        String address = "Tokyo";

        repository.createProducer(id, name, address);
        final Producer result = repository.findById(id);
        assertEquals(id, result.getId());
        assertEquals(name, result.getName());
        assertEquals(address, result.getAddress());
    }

    @Test
    public void testRemove() {
        int id = 8;
        repository.createProducer(id, "Lenovo", "London");
        int result = repository.remove(id);
        assertEquals(1, result);
        Producer producer = repository.findById(id);
        assertNull(producer);
    }

    @Test
    public void testRemove_noEntityWithId_returnZero() {
        int id = 9;
        int result = repository.remove(9);
        assertEquals(0, result);
    }



}
