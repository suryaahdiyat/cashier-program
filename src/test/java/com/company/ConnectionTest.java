package com.company;

import com.company.util.ConnectionUtil;
import org.junit.jupiter.api.Test;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionTest {

    @Test
    void testGetConnection() {
        String sql =" Select admin.username,  levelAdmin.name as level, was_added from Admin join levelAdmin on(admin.id_level = levelAdmin.id) order by was_added";
        try (Connection connection = ConnectionUtil.getDataSource().getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()){
                System.out.println(resultSet.getString("username") + " as "
                        + resultSet.getString("level") + " at "
                        + resultSet.getTimestamp("was_added")
                );
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}
