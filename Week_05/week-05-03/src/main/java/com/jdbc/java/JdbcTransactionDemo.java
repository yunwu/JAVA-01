package com.jdbc.java;

import java.sql.*;

/**
 * 使用事务方式处理sql
 */
public class JdbcTransactionDemo {

    public static void main(String[] args) throws SQLException {

        Statement statement = null;
        Connection connection = null;
        try {
            Class.forName("org.h2.Driver");
            connection = DriverManager.getConnection("jdbc:h2:mem:testJdbc","sa", "");
            connection.setAutoCommit(false);
            statement = connection.createStatement();
            statement.execute("create table user(id int AUTO_INCREMENT primary key, `name` varchar(20))");
            PreparedStatement preparedStatement = connection.prepareStatement("insert into user (`name`) values('lili')");
            int i = 0;
            while (i < 20){

                preparedStatement.execute();
                i++;
                if (i== 10){
                    throw new Exception();
                }
            }
            connection.commit();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            connection.rollback();
        } catch (SQLException e) {
            connection.rollback();
            e.printStackTrace();
        } catch (Exception e) {
            connection.rollback();
            e.printStackTrace();
        }

        System.out.println("开始读取数据");
        ResultSet resultSet = null;

        try {
            resultSet = statement.executeQuery("select * from user");
            while (resultSet.next()){
                System.out.println(resultSet.getInt("id") + "-----" + resultSet.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}
