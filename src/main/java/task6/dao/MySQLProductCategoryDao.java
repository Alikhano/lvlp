package task6.dao;

import task6.ConnectionFactory;
import task6.domain.ResultsProductCategory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class MySQLProductCategoryDao implements ProductCategoryDao {
    private final ConnectionFactory connectionFactory;

    public MySQLProductCategoryDao(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    @Override
    public ResultsProductCategory create(String name) {
        ResultSet set1;
        ResultsProductCategory result;

        try (Connection connection = connectionFactory.getConnection("onlineshop", "root", "Mikkeli9586")) {
            Statement statement = connection.createStatement();
            int rowUpdated = statement.executeUpdate("insert into productCategory(name) value (\"" + name + "\")");
            if (rowUpdated == 0) {
                return null;
            }
            System.out.println("row(s) updated" + rowUpdated);
            set1 = statement.executeQuery("select * from productCategory where name = \"" + name + "\"");
            set1.next();
            result = new ResultsProductCategory(set1.getInt(1), set1.getString(2));
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ResultsProductCategory update(int iD, String name) {
        ResultSet set2;
        ResultsProductCategory result;

        try (Connection connection = connectionFactory.getConnection("onlineshop", "root", "Mikkeli9586")) {
            Statement statement = connection.createStatement();
            int rowUpdated = statement.executeUpdate("update productCategory set name = \"" + name + "\" where productCategoryId = \"" + iD + "\"");
            set2 = statement.getResultSet();
            result = new ResultsProductCategory(set2.getInt(1), set2.getString(2));
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ResultsProductCategory delete(int id) {
        ResultSet set3;
        ResultsProductCategory result;

        try (Connection connection = connectionFactory.getConnection("onlineshop", "root", "Mikkeli9586")) {
            Statement statement = connection.createStatement();
            int rowUpdated = statement.executeUpdate("delete from productCategory where productCategoryId =" + id);
            set3 = statement.getResultSet();
            result = new ResultsProductCategory(set3.getInt(1), set3.getString(2));
            return result;
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ResultsProductCategory getById(int id) {
        ResultSet set4;
        ResultsProductCategory result;

        try (Connection connection = connectionFactory.getConnection("onlineshop", "root", "Mikkeli9586")) {
            Statement statement = connection.createStatement();
            int rowUpdated = statement.executeUpdate("select * from productCategory where productCategoryId =" + id);
            set4 = statement.getResultSet();
            result = new ResultsProductCategory(set4.getInt(1), set4.getString(2));
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public ArrayList<ResultsProductCategory> getAll() {
        ResultsProductCategory result;
        ArrayList<ResultsProductCategory> list = new ArrayList<>();

        try (Connection connection = connectionFactory.getConnection("onlineshop", "root", "Mikkeli9586")) {
            Statement statement = connection.createStatement();
            ResultSet set = statement.executeQuery("select * from productCategory order by categoryId desc");
            while (set.next()) {
                System.out.println("ID " + set.getInt(1) + " name " + set.getString(2));
                result = new ResultsProductCategory(set.getInt(1), set.getString(2));
                list.add(result);
            }
            return list;
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
