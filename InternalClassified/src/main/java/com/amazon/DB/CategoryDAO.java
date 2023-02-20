package com.amazon.DB;

import com.amazon.Model.Category;
import com.amazon.Model.Pictures;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAO implements DAO<Category>{
    DB db = DB.getInstance();

    @Override
    public int insert(Category object) {
        String SQL = "INSERT INTO [Category](title) VALUES('"+object.title+"')";
        return db.executeSQL(SQL);
    }

    @Override
    public int update(Category object) {
        String SQL = "UPDATE [Category] set title='"+object.title+"' WHERE id="+object.id;
        return db.executeSQL(SQL);
    }

    @Override
    public int delete(Category object) {
        String SQL = "DELETE FROM [Category] WHERE id="+object.id;
        return db.executeSQL(SQL);
    }

    @Override
    public List<Category> retrieve() {
        String SQL = "SELECT * FROM [Category]";
        ResultSet set = db.executeQuery(SQL);
        ArrayList<Category> categories= new ArrayList<>();
        try{
            while(set.next())
            {
                Category category = new Category();
                category.id=set.getInt("id");
                category.title = set.getString("title");
                category.createdOn = set.getString("createdOn");

                categories.add(category);
            }

        }catch (Exception e)
        {
            System.err.println("Something went wrong..."+e);
        }
        return categories;
    }

    @Override
    public List<Category> retrieve(String sql) {
        ResultSet set = db.executeQuery(sql);
        ArrayList<Category> categories= new ArrayList<>();
        try{
            while(set.next())
            {
                Category category = new Category();
                category.id=set.getInt("id");
                category.title = set.getString("title");
                category.createdOn = set.getString("createdOn");

                categories.add(category);
            }

        }catch (Exception e)
        {
            System.err.println("Something went wrong..."+e);
        }
        return categories;
    }
}
