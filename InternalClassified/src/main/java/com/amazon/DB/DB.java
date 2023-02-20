package com.amazon.DB;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.*;

public class DB {
    private static final String FILEPATH = "C:/Users/vkams/Desktop/DeskTop/ATLAS/Final project/Project-1/InternalClassified/src/main/java/com/amazon/dbconfig.txt";
    private static  String URL = "";
    private static  String USER ="";
    private static  String PASSWORD = "";

    Connection connection;
    Statement statement;

    private static DB db = new DB();
    private DB(){
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            System.out.println("[DB] Driver loaded successfully..");
            createConnection();

        }catch (Exception e)
        {
            System.err.println("[DB] Something went wrong..."+e);
        }
    }
    public static DB getInstance()
    {
        return db;
    }

    private void createConnection()
    {
        try {
            File file = new File(FILEPATH);
            if(file.exists())
            {
                FileReader reader = new FileReader(file);
                BufferedReader buffer = new BufferedReader(reader);
                URL= buffer.readLine();
                USER= buffer.readLine();
                PASSWORD= buffer.readLine();
                buffer.close();
                reader.close();
                System.out.println("DB configured successfully using config File..");

            }else{
                System.err.println("Cannot read the DB config file...");
            }
            connection = DriverManager.getConnection(URL,USER,PASSWORD);
            System.out.println("Connection created Successfully..");

        }catch (Exception e)
        {
            System.err.println("[CreateConnection]Something went wrong.."+e);

        }
    }

    public int executeSQL(String  SQL)
    {
        int result=0;
        try{
            System.out.println("Executing SQL Query | "+SQL);
            statement = connection.createStatement();
            result = statement.executeUpdate(SQL);
        }catch (Exception e)
        {
            System.err.println("[executeSQL] Something went wrong.."+e);
        }
        return result;
    }

    public ResultSet executeQuery(String sql)
    {
        ResultSet result=null;
        try{
            System.out.println("Executing SQL Query | "+sql);
            statement = connection.createStatement();
            result = statement.executeQuery(sql);
        }catch (Exception e)
        {
            System.err.println("[executeSQL] Something went wrong.."+e);
        }
        return result;

    }

    public void closeConnection()
    {
        try{
            connection.close();
            System.out.println("[closeConnection] Connection Closed..");
        }catch (Exception e){
            System.err.println("[closeConnection] Something went wrong.."+e);
        }
    }


}
