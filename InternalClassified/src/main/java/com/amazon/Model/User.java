package com.amazon.Model;

/*
   CREATE TABLE [User](
	id INT IDENTITY(1,1),
    name VARCHAR(256),
    email VARCHAR(256) NOT NULL UNIQUE,
	password VARCHAR(256) NOT NULL,
    phone VARCHAR(256),
    gender VARCHAR(50),
    address VARCHAR(512),
   department VARCHAR(256),
   type INT,
   status INT,
   createdOn DATETIME DEFAULT CURRENT_TIMESTAMP,
   PRIMARY KEY(id)
   );
 */
public class User {
   public int id;
   public String name;
   public String email;
   public String password;
   public String phone;
   public String gender;
   public String address;
   public String department;
   public int type; // 1. Admin 2.Normal User i.e.  Employees
   public int status; // 1. Active 2. Deactivate ::Default:active
   public String createdOn;

   public User() {
   }

   public User(int id, String name, String email, String password, String phone, String gender, String address, String department, int type, int status, String createdOn) {
      this.id = id;
      this.name = name;
      this.email = email;
      this.password = password;
      this.phone = phone;
      this.gender = gender;
      this.address = address;
      this.department = department;
      this.type = type;
      this.status = status;
      this.createdOn = createdOn;
   }

   @Override
   public String toString() {
      return "User{" +
              "id=" + id +
              ", name='" + name + '\'' +
              ", email='" + email + '\'' +
              ", password='" + password + '\'' +
              ", phone='" + phone + '\'' +
              ", gender='" + gender + '\'' +
              ", address='" + address + '\'' +
              ", department='" + department + '\'' +
              ", type=" + type +
              ", status=" + status +
              ", createdOn='" + createdOn + '\'' +
              '}';
   }

   public void prettyPrint() {
      System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
      System.out.println("User Id:\t\t"+id);
      System.out.println("Name:\t\t"+name);
      System.out.println("Phone:\t\t"+phone);
      System.out.println("Email:\t\t"+email);
      System.out.println("Gender:\t\t"+gender);
      System.out.println("Address:\t"+address);
      System.out.println("Department:\t"+department);
      System.out.println("Status:\t"+((status==1)?"ACTIVE":"INACTIVE"));
      System.out.println("createdOn:\t"+createdOn);
      System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
   }
}
