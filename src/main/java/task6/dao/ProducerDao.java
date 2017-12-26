package task6.dao;

import task6.domain.ResultsProducer;

import java.util.ArrayList;

public interface ProducerDao {
    ResultsProducer create(String name, String address);

    ResultsProducer update(String name, String address);

    ResultsProducer delete (int id);

    ResultsProducer getById (int id);

    ArrayList<ResultsProducer> getAll();
}
