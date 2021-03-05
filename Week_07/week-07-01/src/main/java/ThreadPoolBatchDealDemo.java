import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.ExecutorService;

/**
 * @author wangdan
 * @date 2021/3/5
 *
 * 方法二 多线程+批处理 插入100万数据， 8个字段 9s
 */
public class ThreadPoolBatchDealDemo {
    public static void main(String[] args){


        ExecutorService pool = ThreadPoolFactory.createPool("insert-order-thread");
        long endDate1 = System.currentTimeMillis();
        int count = 1000001;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection= DriverManager.getConnection("jdbc:mysql://localhost:3316/mysql_demo?&useUnicode=true&allowMultiQueries=true&useSSL=false&serverTimezone=Asia/Shanghai","root", "");
            Statement statement = connection.createStatement();
            int j=0;
            int i=0;
            while (j < 200){
                StringBuffer stringBuffer = new StringBuffer("insert into `order` (id,goods_snap_shot_id,price, goods_name, status, create_time, " +
                        "update_time, deleted) values");
                while (i < 5000){
                    if (i==0){
                        stringBuffer.append("(" + count + ", 1, 25.12,'黑人牙膏', 1,  '2021-03-05 17:33:20', '2021-03-05 17:33:20',0)");
                    }else {
                        stringBuffer.append(",(" + count + ", 1, 25.12,'黑人牙膏', 1,  '2021-03-05 17:33:20', '2021-03-05 17:33:20',0)");
                    }
                    i++;
                    count++;
                }
                pool.submit(new OrderDealTask(statement, stringBuffer.toString()));
                i=0;
                j++;
            }

        }catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        long endDate2 = System.currentTimeMillis();
        System.out.println("方法二插入100万数据耗费时长：" + (endDate2 - endDate1));

    }
     static class OrderDealTask implements Runnable{

        private Statement statement;
        private String sql;
        public OrderDealTask(Statement statement, String sql){
            this.statement = statement;
            this.sql = sql;
        }

         public void run() {
             try {
                 statement.execute(sql);
             } catch (SQLException e) {
                 e.printStackTrace();
             }
         }
     }
}
