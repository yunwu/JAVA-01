package com.jdbc.java;

import java.sql.*;

/**
 * 批处理方式，
 */
public class JdbcBatchDealDemo {

    public static void main(String[] args){

        try {
            Class.forName("org.h2.Driver");
            Connection connection= DriverManager.getConnection("jdbc:h2:mem:testJdbc","sa", "");
            Statement statement = connection.createStatement();
            int i = 0;
            while (i < 20){
                statement.addBatch("insert into user (`name`) values('lili')");
            }
            statement.executeBatch();
            statement.clearBatch();
            ResultSet resultSet = statement.executeQuery("select * from user");
            while (resultSet.next()){
                System.out.println(resultSet.getInt("id") + "-----" + resultSet.getString("name"));
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
