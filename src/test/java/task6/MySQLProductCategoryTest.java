package task6;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import task6.dao.MySQLProductCategoryDao;
import task6.dao.ProductCategoryDao;
import task6.domain.ResultsProductCategory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

public class MySQLProductCategoryTest {
    @Mock
    private ConnectionFactory connectionFactory;
    @Mock
    private Connection connection;
    @Mock
    private Statement statement;
    @Mock
    private ResultSet resultSet;

    private ProductCategoryDao dao;

    @BeforeEach
    public void setup() throws SQLException {
        MockitoAnnotations.initMocks(this);

        when(connectionFactory.getConnection("onlineshop", "root", "Mikkeli9586")).thenReturn(connection);
        when(connection.createStatement()).thenReturn(statement);

        dao = new MySQLProductCategoryDao(connectionFactory);
    }


    @Test
    public void testCreate() throws SQLException {
        int categoryId = 5;
        String name = "games";
        int rowUpdated = 1;

        when(statement.executeUpdate(anyString())).thenReturn(rowUpdated);
        when(statement.executeQuery(anyString())).thenReturn(resultSet);
        when(resultSet.getInt(1)).thenReturn(categoryId);
        when(resultSet.getString(2)).thenReturn(name);
        System.out.println(statement.getClass().getName());
        ResultsProductCategory category = dao.create(name);
        Assertions.assertEquals(categoryId, category.getId());
        Assertions.assertEquals(name, category.getName());
    }

    @Test
    public void testCreate_noUpdates() throws SQLException {
        when(statement.executeUpdate(anyString())).thenReturn(0);

        ResultsProductCategory category = dao.create("");
        Assertions.assertNull(category);
        verify(statement, times(0)).executeQuery(anyString());
    }

    @Test
    public void testCreate_returnException() throws SQLException{
        when(dao.create(null)).thenThrow(new SQLException());
        assertThrows(RuntimeException.class, () -> dao.create(null));
    }
}
