package task6.dao;

import task6.ConnectionFactory;
import task6.domain.ResultsProducer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class MySQLProducerDao implements ProducerDao {
    private final ConnectionFactory connectionFactory;

    public MySQLProducerDao(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    @Override
    public ResultsProducer create(String name, String address) {
        ResultSet set1;
        ResultsProducer result;

        try (Connection connection = connectionFactory.getConnection("onlineshop", "root", "Mikkeli9586")) {
            Statement statement = connection.createStatement();
            int rowUpdated = statement.executeUpdate("insert into producer(name, address) value (\"" + name + "\", \"" + address + "\")");
            if (rowUpdated == 0) {
                return null;
            }
            set1 = statement.executeQuery("select * from producer where name = \"" + name + "\"");
            set1.next();
            result = new ResultsProducer(set1.getInt(1), set1.getString(2), set1.getString(3));
            return result;
        }
        catch (SQLException e) {
            throw  new RuntimeException(e);
        }

    }

    @Override
    public ResultsProducer update(String name, String address) {
        ResultSet set2;
        ResultsProducer result;

        try (Connection connection = connectionFactory.getConnection("onlineshop", "root", "Mikkeli9586")) {
            Statement statement = connection.createStatement();
            int rowUpdated = statement.executeUpdate("update producer set address = \"" + address +  "\" where name = \"" + name + "\"");
            if (rowUpdated == 0) {
                return null;
            }
            set2 = statement.executeQuery("select * from producer where address = \"" + address + "\"");
            set2.next();
            result = new ResultsProducer(set2.getInt(1), set2.getString(2), set2.getString(3));
            return result;
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ResultsProducer delete(int id) {
        ResultSet set3;
        ResultsProducer result;

        try (Connection connection = connectionFactory.getConnection("onlineshop", "root", "Mikkeli9586")) {
            Statement statement = connection.createStatement();
            int rowUpdated = statement.executeUpdate("delete from producer where producerId =" + id);
            if (rowUpdated == 0) {
                return null;
            }
            set3 = statement.getResultSet();
            result = new ResultsProducer(set3.getInt(1), set3.getString(2), set3.getString(3));
            return result;
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ResultsProducer getById(int id) {
        ResultSet set4;
        ResultsProducer result;

        try (Connection connection = connectionFactory.getConnection("onlineshop", "root", "Mikkeli9586")) {
            Statement statement = connection.createStatement();
            int rowUpdated = statement.executeUpdate("select * from producer where producerId =" + id);
            if (rowUpdated == 0) {
                return null;
            }
            set4 = statement.getResultSet();
            result = new ResultsProducer(set4.getInt(1),set4.getString(2), set4.getString(3));
            return result;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ArrayList<ResultsProducer> getAll() {
        ResultsProducer result;
        ArrayList<ResultsProducer> list = new ArrayList<>();

        try (Connection connection = connectionFactory.getConnection("onlineshop", "root", "Mikkeli9586")) {
            Statement statement = connection.createStatement();
            ResultSet set = statement.executeQuery("select * from producer");
            while (set.next()) {
                System.out.println("ID " + set.getInt(1) + " name " + set.getString(2) + " address " + set.getString(3));
                result = new ResultsProducer(set.getInt(1), set.getString(2), set.getString(3));
                list.add(result);
            }
            return list;
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
