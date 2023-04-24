import com.mysql.cj.jdbc.Driver;

import java.sql.*;
import java.util.Scanner;

public class JDBConnection implements DriverAction {

    public void deregister() {
        System.out.println("Driver deregistered");
    }

    public static void main(String args[]) throws SQLException {
        Scanner scan = new Scanner(System.in);
        String input = scan.nextLine();


        Driver driver = new com.mysql.cj.jdbc.Driver();
        DriverAction da = new JDBConnection();
        DriverManager.registerDriver(driver,da);
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/practice", "root","");
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("select * from `bookpractice` where `author` = '"+ input +"'");
        while(rs.next()){
            System.out.println(rs.getString(2));
        }
        con.close();
        DriverManager.deregisterDriver(driver);
    }
}
