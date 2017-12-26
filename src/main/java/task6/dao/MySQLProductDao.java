package task6.dao;

import task6.ConnectionFactory;
import task6.domain.ResultsProduct;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class MySQLProductDao implements ProductDao {
    private final ConnectionFactory connectionFactory;

    public MySQLProductDao(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    @Override
    public ResultsProduct create(int productId, String name, double weight, int categoryId) {
        ResultSet set1;
        int id;
        String nameProd;
        double weightProd;
        int catId;
        ResultsProduct result;

        try (Connection connection = connectionFactory.getConnection("onlineshop", "root", "Mikkeli9586")) {
            Statement statement = connection.createStatement();
            int rowUpdated = statement.executeUpdate("insert into product value (\"" + productId + "\", \"" + name + "\", \"" + weight + "\", \"" + categoryId + "\")");
            System.out.println("row(s) updated " + rowUpdated);
            set1 = statement.getResultSet();
            id = set1.getInt(1);
            nameProd = set1.getString(2);
            weightProd = set1.getDouble(3);
            catId = set1.getInt(4);

            result = new ResultsProduct(id, nameProd, weightProd, catId);
            return result;

        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ResultsProduct update(int productId, String name,double weight, int categoryId) {
        ResultSet set2;
        int id;
        String nameProd;
        double weightProd;
        int catId;
        ResultsProduct result;

        try (Connection connection = connectionFactory.getConnection("onlineshop", "root", "Mikkeli9586")) {
            Statement statement = connection.createStatement();
            int rowUpdated = statement.executeUpdate("update product set weight = \"" + weight + "\", productId = \"" + productId + "\" where name = \"" + name + "\", categoryId = \"" + categoryId + "\"");
            set2 = statement.getResultSet();
            id = set2.getInt(1);
            nameProd = set2.getString(2);
            weightProd = set2.getDouble(3);
            catId = set2.getInt(4);

            result = new ResultsProduct(id, nameProd, weightProd, catId);
            return result;
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ResultsProduct delete(int id) {
        ResultSet set3;
        int prodId;
        String nameProd;
        double weightProd;
        int catId;
        ResultsProduct result;

        try (Connection connection = connectionFactory.getConnection("onlineshop", "root", "Mikkeli9586")) {
            Statement statement = connection.createStatement();
            int rowUpdated = statement.executeUpdate("delete from product where productId =" + id);
            set3 = statement.getResultSet();
            prodId = set3.getInt(1);
            nameProd = set3.getString(2);
            weightProd = set3.getDouble(3);
            catId = set3.getInt(4);

            result = new ResultsProduct(prodId, nameProd, weightProd, catId);
            return result;

        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ResultsProduct getById(int id) {
        ResultSet set4;
        int prodId;
        String nameProd;
        double weightProd;
        int catId;
        ResultsProduct result;

        try (Connection connection = connectionFactory.getConnection("onlineshop", "root", "Mikkeli9586")) {
            Statement statement = connection.createStatement();
            int rowUpdated = statement.executeUpdate("select * from product where productId =" + id);
            set4 = statement.getResultSet();
            prodId = set4.getInt(1);
            nameProd = set4.getString(2);
            weightProd = set4.getDouble(3);
            catId = set4.getInt(4);
            result = new ResultsProduct(prodId, nameProd, weightProd, catId);
            return result;

        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ArrayList<ResultsProduct> getAll() {
        ResultsProduct result;
        ArrayList<ResultsProduct> list = new ArrayList<>();
        int prodId;
        String nameProd;
        double weightProd;
        int catId;

        try (Connection connection = connectionFactory.getConnection("onlineshop", "root", "Mikkeli9586")) {
            Statement statement = connection.createStatement();
            ResultSet set = statement.executeQuery("select * from product");
            while (set.next()) {
                System.out.println("ID " + set.getInt(1) + " name " + set.getString(2) + " weight " + set.getDouble(3) + " categoryId " + set.getInt(4));
                prodId = set.getInt(1);
                nameProd = set.getString(2);
                weightProd = set.getDouble(3);
                catId = set.getInt(4);
                result = new ResultsProduct(prodId, nameProd, weightProd, catId);
                list.add(result);
            }
            return list;
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
