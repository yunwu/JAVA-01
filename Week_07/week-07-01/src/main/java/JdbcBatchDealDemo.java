import java.sql.*;

/**
 * 批处理方式，插入100万数据， 8个字段 39s
 */
public class JdbcBatchDealDemo {

    public static void main(String[] args){

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection= DriverManager.getConnection("jdbc:mysql://localhost:3316/mysql_demo?&useUnicode=true&allowMultiQueries=true&useSSL=false&serverTimezone=Asia/Shanghai","root", "");
            Statement statement = connection.createStatement();
            int i = 0;
            int j=0;
            int count=1;
            long endDate1 = System.currentTimeMillis();
            //方法一 插入100万数据， 8个字段 39s
            while (j < 200){
                StringBuffer stringBuffer = new StringBuffer("insert into `order` (id,goods_snap_shot_id,price, goods_name, status, create_time, update_time, deleted) values");
                while (i < 5000){
                    if (i==0){
                        stringBuffer.append("(" + count + ", 1, 25.12,'黑人牙膏', 1,  '2021-03-05 17:33:20', '2021-03-05 17:33:20',0)");
                    }else {
                        stringBuffer.append(",(" + count + ", 1, 25.12,'黑人牙膏', 1,  '2021-03-05 17:33:20', '2021-03-05 17:33:20',0)");
                    }
                    i++;
                    count++;
                }
                statement.execute(stringBuffer.toString());
                i=0;
                j++;
            }

            long endDate2 = System.currentTimeMillis();
            System.out.println("方法二插入100万数据耗费时长：" + (endDate2 - endDate1));

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
