package com.jdbc.java;

import java.sql.*;

public class JdbcPrepareStatementDemo {

    public static void main(String[] args){

        try {
            Class.forName("org.h2.Driver");
            Connection connection = DriverManager.getConnection("jdbc:h2:mem:testJdbc","sa", "");
            Statement statement = connection.createStatement();
            statement.execute("create table user(id int AUTO_INCREMENT primary key, `name` varchar(20))");
            PreparedStatement preparedStatement = connection.prepareStatement("insert into user (`name`) values('lili')");
            int i = 0;
            while (i < 20){
                preparedStatement.execute();
                i++;
            }

            ResultSet resultSet = statement.executeQuery("select * from user");
            while (resultSet.next()){
                System.out.println(resultSet.getInt("id") + "-----" + resultSet.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
