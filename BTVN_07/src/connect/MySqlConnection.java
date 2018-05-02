package connect;

import java.sql.Connection;
import java.sql.DriverManager;

public class MySqlConnection {

    /**
     * ket noi den Mysql, database "qlnv"
     * user="root"
     * pass="mysql"
     * port="3306"
     * tra ve connection
     * @return
     */
    public static Connection getConnection() {

        Connection cnn = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            cnn = DriverManager.getConnection("jdbc:mysql://localhost:3306/qlnv", "root", "mysql");
        }catch (Exception e) {
            e.printStackTrace();
        }

        return cnn;
    }

    public static void main(String args[]) {

        System.out.print(getConnection());
    }
}
