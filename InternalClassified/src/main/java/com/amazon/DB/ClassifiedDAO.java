package com.amazon.DB;

import com.amazon.Model.Classified;
import com.amazon.Model.Pictures;
import com.amazon.Session.ICSession;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClassifiedDAO implements DAO<Classified> {
    DB db = DB.getInstance();

    @Override
    public int insert(Classified object) {
        String SQL ="INSERT INTO [Classified](categoryId,headLine,productName,brand,condition,description,price,imageId,availability,userId,status) " +
                "VALUES("+object.categoryId+",'"+object.headLine+"','"+object.productName+"','"+object.brand+"','"+object.condition+"','"+object.description+"','"+object.price+"',"+object.imageId+","+object.availability+","+object.userId+","+object.status+")";
            return db.executeSQL(SQL);
    }

    @Override
    public int update(Classified object) {
        String SQL ="UPDATE [Classified] set categoryId="+object.categoryId+",headLine='"+object.headLine+"',productName='"+object.productName+"',brand='"+object.brand+"',condition='"+object.condition+"',description='"
                +object.description+"',price='"+object.price+"',imageId="+object.imageId+",availability="+object.availability+",userId="+object.userId+",status="+object.status+" WHERE id="+object.id;
        return db.executeSQL(SQL);
    }

    @Override
    public int delete(Classified object) {
        String SQL = "DELETE FROM [Classified] WHERE id="+object.id;
        return db.executeSQL(SQL);
    }

    @Override
    public List<Classified> retrieve() {
        String SQL = "SELECT * FROM [Classified]";
        ResultSet set = db.executeQuery(SQL);
        ArrayList<Classified> classifieds= new ArrayList<>();
        try{
            while(set.next())
            {
                Classified classified = new Classified();
                classified.id=set.getInt("id");
                classified.categoryId = set.getInt("categoryId");
                classified.headLine = set.getString("headLine");
                classified.productName = set.getString("productName");
                classified.brand = set.getString("brand");
                classified.condition = set.getString("condition");
                classified.description = set.getString("description");
                classified.price = set.getString("price");
                classified.imageId = set.getInt("imageId");
                classified.availability = set.getInt("availability");
                classified.userId = set.getInt("userId");
                classified.status = set.getInt("status");
                classified.createdOn = set.getString("createdOn");

                classifieds.add(classified);
            }

        }catch (Exception e)
        {
            System.err.println("Something went wrong..."+e);
        }
        return classifieds;
    }

    @Override
    public List<Classified> retrieve(String sql) {
        ResultSet set = db.executeQuery(sql);
        ArrayList<Classified> classifieds= new ArrayList<>();
        try{
            while(set.next())
            {
                Classified classified = new Classified();
                classified.id=set.getInt("id");
                classified.categoryId = set.getInt("categoryId");
                classified.headLine = set.getString("headLine");
                classified.productName = set.getString("productName");
                classified.brand = set.getString("brand");
                classified.condition = set.getString("condition");
                classified.description = set.getString("description");
                classified.price = set.getString("price");
                classified.imageId = set.getInt("imageId");
                classified.availability = set.getInt("availability");
                classified.userId = set.getInt("userId");
                classified.status = set.getInt("status");
                classified.createdOn = set.getString("createdOn");

                classifieds.add(classified);
            }

        }catch (Exception e)
        {
            System.err.println("Something went wrong..."+e);
        }
        return classifieds;
    }
}
