package task7.repository;


import task7.domain.Producer;

public interface ProducerRepository {

    Producer createProducer(int id, String name, String address);

    Producer findById(int id);

    int remove(int id);
}
