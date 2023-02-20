package com.amazon.DB;

import com.amazon.Model.User;
import com.amazon.Session.ICSession;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/*

 */

public class UserDAO implements DAO<User>{
    DB db = DB.getInstance();

    @Override
    public int insert(User object) {
        String SQL = "INSERT INTO [User] (name, email, password, phone, gender, address,department,type,status) " +
                "VALUES('"+object.name+"', '"+object.email+"', '"+object.password+"','"+object.phone+"','"+object.gender+"','"+object.address+"','"+object.department+"',"+object.type+","+object.status+")";

          return  db.executeSQL(SQL);

    }

    @Override
    public int update(User object) {
        String SQL = "UPDATE [User] set name='"+object.name+"',email='"+object.email+"',password='"+object.password+"',phone='"+ object.phone+"',gender='"+object.gender+"',address='"+object.address+"',department='"+object.department+"',type="+object.type+",status="+object.status+" WHERE id="+object.id;

        return db.executeSQL(SQL);

    }

    @Override
    public int delete(User object) {

        String SQL = "DELETE FROM [User] WHERE id="+ICSession.user.id;
        return db.executeSQL(SQL);
    }

    @Override
    public List<User> retrieve() {
        String sql="SELECT * FROM [User]";
        ResultSet set = db.executeQuery(sql);
        ArrayList<User> users= new ArrayList<>();
        try{
            while(set.next())
            {
                User user = new User();
                user.id=set.getInt("id");
                user.name = set.getString("name");
                user.phone = set.getString("phone");
                user.email = set.getString("email");
                user.gender = set.getString("gender");
                user.password = set.getString("password");
                user.address = set.getString("address");
                user.department = set.getString("department");
                user.type = set.getInt("type");
                user.status = set.getInt("status");
                user.createdOn = set.getString("createdOn");
                users.add(user);
            }

        }catch (Exception e)
        {
            System.err.println("Something went wrong..."+e);
        }
        return users;
    }

    @Override
    public List<User> retrieve(String sql) {
        ResultSet set = db.executeQuery(sql);
        ArrayList<User> users= new ArrayList<>();
        try{
            while(set.next())
            {
                User user = new User();
                user.id=set.getInt("id");
                user.name = set.getString("name");
                user.phone = set.getString("phone");
                user.email = set.getString("email");
                user.gender = set.getString("gender");
                user.password = set.getString("password");
                user.address = set.getString("address");
                user.department = set.getString("department");
                user.type = set.getInt("type");
                user.status = set.getInt("status");
                user.createdOn = set.getString("createdOn");
                users.add(user);
            }

        }catch (Exception e)
        {
            System.err.println("Something went wrong..."+e);
        }
        return users;
    }
}
