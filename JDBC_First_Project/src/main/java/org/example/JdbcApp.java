package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class JdbcApp {
    public static void main(String [] args)
    {
        String url="jdbc:mysql://localhost:3306/spark";
        String userName="root";
        String password = "Mi@mySql01";

        try{
            //establishing connection
            Connection connection=DriverManager.getConnection(url,userName,password);
            //statement object created to execute db queries
            Statement statement=connection.createStatement();

            Scanner sc=new Scanner(System.in);
            System.out.println("Enter 1 for fetch data \nEnter 2 for insert data\nEnter 3 for delete data \nEnter 4 for batch update");
            int choice = sc.nextInt();
            sc.nextLine();// Consume newline character left in the input buffer
            switch(choice)
            {
                case 1:
                    Operations.fetchData(statement);
                    break;
                case 2:
                   Operations.insertData(statement);
                    break;
                case 3:
                    Operations.deleteRow(statement);
                    break;
                case 4:
                    Operations.batchUpdate(statement);
                    break;
                default:
                    System.out.println("choose valid input");
                    break;
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
