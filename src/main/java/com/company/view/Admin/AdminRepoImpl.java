package com.company.view.Admin;

import com.company.entity.Admin;
import com.company.util.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class AdminRepoImpl implements AdminRepo{
    @Override
    public boolean isEmpty() {
        String sql = "SELECT * FROM admin";
        try (Connection connection = ConnectionUtil.getDataSource().getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)){

            return !resultSet.next();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean login(Admin admin) {
        String sql = "Select * from admin where username = ? AND password = ?";
        try (Connection connection = ConnectionUtil.getDataSource().getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)){

            statement.setString(1, admin.getUserName());
            statement.setString(2, admin.getPassword());

            try (ResultSet resultSet = statement.executeQuery()){
                return resultSet.next();
            }

        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean add(Admin admin) {
        String sql = "INSERT INTO admin(username, password, id_level) VALUES( ?, ? ,?);";
        try (Connection connection = ConnectionUtil.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)){

            statement.setString(1, admin.getUserName());
            statement.setString(2, admin.getPassword());
            statement.setString(3, admin.getLevel());

            statement.executeUpdate();
            return true;
        }catch (SQLException e){
            return false;
        }
    }

    @Override
    public boolean delete(String username) {
        if (isExist(username)){
            String sql = "DELETE FROM admin where username = ?;";
            try (Connection connection = ConnectionUtil.getDataSource().getConnection();
                 PreparedStatement statement = connection.prepareStatement(sql)){

                statement.setString(1, username);

                statement.executeUpdate();

                return true;
            }catch (SQLException e){
                throw new RuntimeException(e);
            }
        }else return false;
    }

    @Override
    public Admin[] getAll() {
        int num = 1;
        String number = "" + num + " ";
        String sql = "SELECT * from admin join levelAdmin on(admin.id_level = levelAdmin.id) order by was_added";
        try (Connection connection = ConnectionUtil.getDataSource().getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)){

            List<Admin> list = new ArrayList<>();

            while (resultSet.next()){
                list.add(new Admin(number, resultSet.getString("username"),
                        resultSet.getString("password"),
                        resultSet.getString("name"),
                        resultSet.getTimestamp("was_added")));
                num++;
                number = "" + num + " ";
            }
            return list.toArray(new Admin[]{});
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getStatus(String level) {
        if (level.equals("lA00")) return "OWNER";
        else if (level.equals("lA01")) return "EMPLOYEE";
        else return "GUEST";
    }

    private boolean isExist(String username){
        String sql = "Select * from admin where username = ?";
        try (Connection connection = ConnectionUtil.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)){

            statement.setString(1, username);

            try (ResultSet resultSet = statement.executeQuery()){
                return resultSet.next();
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public boolean isInvalidIdLevel(String level){
        String sql = "Select * from admin where id_level = 'lA00'";
        try (Connection connection = ConnectionUtil.getDataSource().getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)){

            if (resultSet.next() && (level.equals("lA00"))) return true;
            else return !(level.equals("lA01") || level.equals("lA02"));

        }catch (SQLException e){
            return true;
        }
    }
}

