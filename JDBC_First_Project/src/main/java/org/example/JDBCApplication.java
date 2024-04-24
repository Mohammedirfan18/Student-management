package org.example;

import java.sql.*;
//it is concept code
public class JDBCApplication {
    public static void main(String[] args)
    {
        String url = "jdbc:mysql://localhost:3306/spark";
        String username = "root";
        String password = "Mi@mySql01";

        try{
            //load and reg
            //Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url,username,password);
           // System.out.println("printing : "+connection);
            Statement statement = connection.createStatement();
            //System.out.println("printing : "+statement);
           ResultSet resultSet = statement.executeQuery("select * from students");
           // System.out.println("printing : "+resultSet);
            // Get ResultSet metadata
//            ResultSetMetaData metaData = resultSet.getMetaData();
//           // System.out.println(metaData);
//            int columnCount = metaData.getColumnCount();
//
//// Print column names
//            for (int i = 1; i <= columnCount; i++) {
//                System.out.printf("%-15s",metaData.getColumnName(i));
//            }
//            System.out.println();
//
//// Print rows
//            while (resultSet.next()) {
//                for (int i = 1; i <= columnCount; i++) {
//                    System.out.printf("%-15s",resultSet.getString(i));
//                }
//                System.out.println();
//            }



//            ResultSet rmax= statement.executeQuery("select MAX(st_id) as max_id from students");
//            rmax.next();
//            System.out.println(rmax.getInt("max_id"));



//            System.out.println("*********Students info*********");
//            while(resultSet.next())
//           {
//                System.out.println(resultSet.getString("st_name"));
//                System.out.println(resultSet.getString("phoneNo"));
//                System.out.println(resultSet.getInt("st_id"));
//                System.out.println("***************************");
//            }
            resultSet.close();
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
