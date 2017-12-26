package task6;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import task6.dao.MySQLProducerDao;
import task6.dao.ProducerDao;
import task6.domain.ResultsProducer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class MySQLProducerTest {
    @Mock
    private ConnectionFactory connectionFactory;
    @Mock
    private Connection connection;
    @Mock
    private Statement statement;
    @Mock
    private ResultSet resultSet;

    private ProducerDao dao;

    @BeforeEach
    public void setup() throws SQLException {
        MockitoAnnotations.initMocks(this);

        when(connectionFactory.getConnection("onlineshop", "root", "Mikkeli9586")).thenReturn(connection);
        when(connection.createStatement()).thenReturn(statement);

        dao = new MySQLProducerDao(connectionFactory);
    }
    @Test
    public void testCreate() throws SQLException {
        int categoryId = 5;
        String name = "games";
        String address = "spb";
        int rowUpdated = 1;

        when(statement.executeUpdate(anyString())).thenReturn(rowUpdated);
        when(statement.executeQuery(anyString())).thenReturn(resultSet);
        when(resultSet.getInt(1)).thenReturn(categoryId);
        when(resultSet.getString(2)).thenReturn(name);
        when(resultSet.getString(3)).thenReturn(address);
        System.out.println(statement.getClass().getName());
        ResultsProducer producer = dao.create(name, address);
        Assertions.assertEquals(categoryId, producer.getId());
        Assertions.assertEquals(name, producer.getName());
        Assertions.assertEquals(address, producer.getAddress());
    }
    @Test
    public void testCreate_noUpdates() throws SQLException {
        when(statement.executeUpdate(anyString())).thenReturn(0);

        ResultsProducer producer = dao.create("", "");
        Assertions.assertNull(producer);
        verify(statement, times(0)).executeQuery(anyString());
    }

    @Test
    public void testCreate_returnException() throws SQLException{
        when(dao.create(null, null)).thenThrow(new SQLException());
        assertThrows(RuntimeException.class, () -> dao.create(null,null));
    }

    @Test
    public void testUpdate() throws SQLException{
        int categoryId = 5;
        String name = "games";
        String address = "moscow";
        int rowUpdated = 1;

        when(statement.executeUpdate(anyString())).thenReturn(rowUpdated);
        when(statement.executeQuery("select * from producer where address = \"" + address + "\"")).thenReturn(resultSet);
        when(resultSet.getInt(1)).thenReturn(categoryId);
        when(resultSet.getString(2)).thenReturn(name);
        when(resultSet.getString(3)).thenReturn(address);
        System.out.println(statement.getClass().getName());
        ResultsProducer producer = dao.update(name, address);
        Assertions.assertEquals(categoryId, producer.getId());
        Assertions.assertEquals(name, producer.getName());
        Assertions.assertEquals(address, producer.getAddress());
    }

    @Test
    public void testUpdate_noUpdates() throws SQLException {
        when(statement.executeUpdate(anyString())).thenReturn(0);
        String name = "cars";

        ResultsProducer producer = dao.update(name, "");
        Assertions.assertNull(producer);
        verify(statement, times(0)).executeQuery(anyString());
    }

    @Test
    public void testUpdate_returnException() throws SQLException {
        when(dao.update(null, null)).thenThrow(new SQLException());
        assertThrows(RuntimeException.class, () -> dao.update(null,null));

    }

    @Test
    public void testDelete() throws SQLException {
        int categoryId = 5;
        int rowUpdated = 1;

        when(statement.executeUpdate("delete from producer where producerId =" + categoryId)).thenReturn(rowUpdated);
        when(statement.getResultSet()).thenReturn(resultSet);
        when(resultSet.getInt(1)).thenReturn(categoryId);
        ResultsProducer producer = dao.delete(categoryId);
        Assertions.assertEquals(categoryId, producer.getId());
    }

    @Test
    public void testDelete_noUpdates() throws SQLException {
        when(statement.executeUpdate(anyString())).thenReturn(0);
        int categoryId = 0;

        ResultsProducer producer = dao.delete(categoryId);
        Assertions.assertNull(producer);
        verify(statement, times(0)).executeQuery(anyString());
    }

    @Test
    public void testDelete_returnException() throws SQLException {
        when(dao.delete(-1)).thenThrow(new SQLException());
        assertThrows(RuntimeException.class, () -> dao.update(null,null));
    }

    @Test
    public void testgetById() throws SQLException {
        int categoryId = 5;
        int rowUpdated = 1;

        when(statement.executeUpdate("select * from producer where producerId =" + categoryId)).thenReturn(rowUpdated);
        when(statement.getResultSet()).thenReturn(resultSet);
        when(resultSet.getInt(1)).thenReturn(categoryId);
        ResultsProducer producer = dao.getById(categoryId);
        Assertions.assertEquals(categoryId, producer.getId());
    }

    @Test
    public void testGetById_noUpdates() throws SQLException {
        when(statement.executeUpdate(anyString())).thenReturn(0);
        int categoryId = 0;

        ResultsProducer producer = dao.getById(categoryId);
        Assertions.assertNull(producer);
        verify(statement, times(0)).executeQuery(anyString());
    }

    @Test
    public void testGetById_returnException() throws SQLException {
        when(dao.getById(-1)).thenThrow(new SQLException());
        assertThrows(RuntimeException.class, () -> dao.update(null,null));
    }

    @Test
    public void testGetAll() throws SQLException {
        int categoryId = 5;
        String name = "games";
        String address = "moscow";
        int rowUpdated = 1;
        ArrayList<ResultsProducer> list = new ArrayList();

        when(statement.executeUpdate(anyString())).thenReturn(rowUpdated);
        when(statement.executeQuery("select * from producer")).thenReturn(resultSet);
        System.out.println(statement.getClass().getName());
        list = dao.getAll();
        for (int i = 0; i < list.size(); i++) {
            when(resultSet.getInt(1)).thenReturn(categoryId);
            when(resultSet.getString(2)).thenReturn(name);
            when(resultSet.getString(3)).thenReturn(address);
            Assertions.assertEquals(categoryId, list.get(i).getId());
            Assertions.assertEquals(name, list.get(i).getName());
            Assertions.assertEquals(address, list.get(i).getAddress());
        }
    }

    @Test
    public void testGetAll_returnException() throws SQLException {
        String name = "any other table";
        when(statement.executeQuery("select * from " + name)).thenThrow(new SQLException());
        assertThrows(RuntimeException.class, () -> dao.getAll());

    }





}
