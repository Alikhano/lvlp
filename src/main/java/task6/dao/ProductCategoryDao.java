package task6.dao;

import task6.domain.ResultsProductCategory;

import java.util.ArrayList;

public interface ProductCategoryDao {
    ResultsProductCategory create(String name);

    ResultsProductCategory update(int id, String name);

    ResultsProductCategory delete (int id);

    ResultsProductCategory getById (int id);

    ArrayList<ResultsProductCategory> getAll();
}
