package task7.repository.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import task7.domain.Producer;
import task7.repository.ProducerRepository;
import task7.repository.TransactionOperation;

public class ProducerRepositoryImpl implements ProducerRepository {

    private final SessionFactory factory;

    public ProducerRepositoryImpl(SessionFactory factory) {
        this.factory = factory;
    }

    @Override
    public Producer createProducer(int id, String name, String address) {

        final Producer producer = new Producer();
        producer.setId(id);
        producer.setName(name);
        producer.setAddress(address);

        final Session session = factory.openSession();
        doInTransaction(session, () -> {
            session.persist(producer);
            return producer;
        });
        return producer;
    }

    @Override
    public Producer findById(int id) {
        final  Session session = factory.openSession();
        return (Producer)doInTransaction(session, () -> session.find(Producer.class, id));
    }

    @Override
    public int remove(int id) {
        final Session session = factory.openSession();
        return (Integer) doInTransaction(session, () -> {
        return session.createQuery("delete from Producer where id = :id")
                .setParameter("id", id)
                .executeUpdate();});
    }

    private Object doInTransaction(Session session, TransactionOperation transactionOperation) {
        final Transaction transaction = session.beginTransaction();

        Object result = transactionOperation.doInTransaction();
        transaction.commit();
        session.close();

        return result;
    }
}
