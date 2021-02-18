package com.jdbc.java;

import java.sql.*;

public class JdbcOriginalDemo {

    public static void main(String[] args){

        Connection connection = null;
        try {
            Class.forName("org.h2.Driver");
            connection= DriverManager.getConnection("jdbc:h2:mem:testJdbc","sa", "");
            Statement statement = connection.createStatement();
            statement.execute("create table user(id int primary key, `name` varchar(20))");
            statement.execute("insert into user (id, `name`) values( 1, 'lili')");
            ResultSet resultSet = statement.executeQuery("select * from user");
            while (resultSet.next()){
                System.out.println(resultSet.getInt("id") + "-----" + resultSet.getString("name"));
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }


}
