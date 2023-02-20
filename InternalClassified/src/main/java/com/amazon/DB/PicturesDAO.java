package com.amazon.DB;

import com.amazon.Model.Pictures;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PicturesDAO implements DAO<Pictures>{
    DB db = DB.getInstance();
    @Override
    public int insert(Pictures object) {
        String SQL ="INSERT INTO [Pictures](imageUrl) VALUES('"+object.imageUrl+"')";
        return db.executeSQL(SQL);

    }

    @Override
    public int update(Pictures object) {
        String SQL = "UPDATE [Pictures] set imageUrl='"+object.imageUrl+"' WHERE id="+object.id;
        return db.executeSQL(SQL);
    }

    @Override
    public int delete(Pictures object) {
        String SQL = "DELETE FROM [Pictures] WHERE id="+object.id;
        return db.executeSQL(SQL);
    }

    @Override
    public List<Pictures> retrieve() {
        String SQL = "SELECT * FROM [Pictures]";
        ResultSet set = db.executeQuery(SQL);
        ArrayList<Pictures> pictures= new ArrayList<>();
        try{
            while(set.next())
            {
                Pictures pic = new Pictures();
                pic.id=set.getInt("id");
                pic.imageUrl = set.getString("imageUrl");

                pictures.add(pic);
            }

        }catch (Exception e)
        {
            System.err.println("Something went wrong..."+e);
        }
        return pictures;
    }

    @Override
    public List<Pictures> retrieve(String sql) {
        ResultSet set = db.executeQuery(sql);
        ArrayList<Pictures> pictures= new ArrayList<>();
        try{
            while(set.next())
            {
                Pictures pic = new Pictures();
                pic.id=set.getInt("id");
                pic.imageUrl = set.getString("imageUrl");

                pictures.add(pic);
            }

        }catch (Exception e)
        {
            System.err.println("Something went wrong..."+e);
        }
        return pictures;
    }
}
