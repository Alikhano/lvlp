package task6.dao;

import task6.domain.ResultsProduct;

import java.util.ArrayList;

public interface ProductDao {
    ResultsProduct create(int productId, String name, double weight, int categoryId);

    ResultsProduct update(int productId, String name, double weight, int categoryId);

    ResultsProduct delete(int id);

    ResultsProduct getById(int id);

    ArrayList<ResultsProduct> getAll();
}
