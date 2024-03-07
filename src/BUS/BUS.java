package BUS;

import java.sql.*;
public class BUS{
public static Connection getConnection(){
    Connection conn = null;
    try{
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String dbUrl = "jdbc:sqlserver://localhost:1433;DatabaseName=QuanLySinhVien;encrypt=false";
        String username = "sa";
        String password= "123456aA@$";
        conn=DriverManager.getConnection(dbUrl, username, password);
        
    } catch(Exception e){
        e.printStackTrace();
    }
    return conn;
}
    
    public static void closeConnection(Connection c){
        try {
            if(c!=null){
                c.close();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
