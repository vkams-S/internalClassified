package com.amazon.DB;

import com.amazon.Model.Order;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO implements DAO<Order> {
    DB db = DB.getInstance();
    @Override
    public int insert(Order object) {
        String SQL ="INSERT INTO [Order](classifiedId,fromUserId,toUserId,proposedPrice,status) VALUES ("+object.classifiedId+","+object.fromUserId+","+object.toUserId+",'"+object.proposedPrice+"',"+object.status+")";
        return db.executeSQL(SQL);
    }

    @Override
    public int update(Order object) {
        String SQL ="UPDATE [Order] set classifiedId="+object.classifiedId+",fromUserId="+object.fromUserId+",toUserId="+object.toUserId+",proposedPrice='"+object.proposedPrice+"',status="+object.status+" WHERE id="+object.id;
        return db.executeSQL(SQL);
    }

    @Override
    public int delete(Order object) {
        String SQL = "DELETE FROM [Order] WHERE id="+object.id;
        return db.executeSQL(SQL);
    }

    @Override
    public List<Order> retrieve() {
        String SQL = "SELECT * FROM [Order]";
        ResultSet set = db.executeQuery(SQL);
        ArrayList<Order> orders= new ArrayList<>();
        try{
            while(set.next())
            {
                Order order = new Order();
                order.id=set.getInt("id");
                order.classifiedId =set.getInt("classifiedId");
                order.fromUserId =set.getInt("fromUserId");
                order.toUserId =set.getInt("toUserId");
                order.proposedPrice =set.getString("proposedPrice");
                order.status=set.getInt("status");
                order.lastUpdatedOn =set.getString("lastUpdatedOn");
                orders.add(order);
            }

        }catch (Exception e)
        {
            System.err.println("Something went wrong..."+e);
        }
        return orders;
    }

    @Override
    public List<Order> retrieve(String sql) {
        ResultSet set = db.executeQuery(sql);
        ArrayList<Order> orders= new ArrayList<>();
        try{
            while(set.next())
            {
                Order order = new Order();
                order.id=set.getInt("id");
                order.classifiedId =set.getInt("classifiedId");
                order.fromUserId =set.getInt("fromUserId");
                order.toUserId =set.getInt("toUserId");
                order.proposedPrice =set.getString("proposedPrice");
                order.status=set.getInt("status");
                order.lastUpdatedOn =set.getString("lastUpdatedOn");
                orders.add(order);
            }

        }catch (Exception e)
        {
            System.err.println("Something went wrong..."+e);
        }
        return orders;
    }
}
