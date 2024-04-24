package org.example;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.Scanner;

public class Operations {


    //case 1 fetching dat
    public static void fetchData(Statement statement) {
        try {
            ResultSet resultSet= statement.executeQuery("select * from students");
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();

            //printing column names
            for (int i = 1; i <= columnCount; i++) {
                System.out.printf("%-15s", metaData.getColumnName(i));//15s takes 15 char space ,"-" for left alignment
            }
            System.out.println();
            //printing data
            while (resultSet.next()) {
                for (int i = 1; i <= columnCount; i++) {
                    System.out.printf("%-15s", resultSet.getString(i));
                }
                System.out.println();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //case 2 insert data (create)
    public static void insertData(Statement statement) {
       try {
           //knowing the last index
           ResultSet maxSet = statement.executeQuery("select Max(st_id) as max_id from students");
           maxSet.next();
           int maxIdx = maxSet.getInt("max_id");
           maxIdx++;
           //taking input data to insert
           Scanner sc = new Scanner(System.in);

           System.out.println("enter your name:");
           String name = sc.nextLine();

           System.out.println("enter your mobile number:");
           String mobileNO = sc.nextLine();

           int rowsAffected = statement.executeUpdate("Insert into students values(" + maxIdx + ",'" + name + "','" + mobileNO + "')");

           if (rowsAffected > 0) {
               System.out.println("data properly inserted");
           } else {
               System.out.println("no data inserted");
           }
       }catch (Exception e)
       {
           e.printStackTrace();
       }
    }
    //case 3 deleting row
    public static void deleteRow(Statement statement)
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("enter the row id to delete:");
        int id=sc.nextInt();
        try{
            int row = statement.executeUpdate("delete from students where st_id="+id);
            if(row>0)
            {
                System.out.println("Data Deleted:"+id);
            }
            else {
                System.out.println("Data Deletion failed:");
            }
        }catch(Exception e)
        {
            e.printStackTrace();
        }

    }
    //case 4 batch update
    public static void batchUpdate(Statement statement)
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("enter the 4 batch details:");
        String [] bulkQueries =new String[10];

        for(int i=0 ;i<4;i++)
        {
            System.out.printf("enter %d query: ",i+1);
            bulkQueries[i] = sc.nextLine();
            try{
                statement.addBatch(bulkQueries[i]);
            }catch(Exception e)
            {
                e.printStackTrace();
            }
            System.out.println();
        }

        try{
            int [] rowCount= statement.executeBatch();
            int count;
            for (int i = 0; i < rowCount.length; i++) {
                count= rowCount[i];
                if (count > 0) {
                    System.out.println("Statement " + (i+1) + " executed successfully. Rows affected: " + count);
                } else if (count == Statement.SUCCESS_NO_INFO) {
                    System.out.println("Statement " + (i+1) + " executed successfully, but the number of affected rows is not available.");
                } else if (count == Statement.EXECUTE_FAILED) {
                    System.out.println("Statement " + (i+1) + " failed to execute.");
                }
            }


        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}