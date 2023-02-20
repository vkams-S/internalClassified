package com.amazon.Model;

/*
CREATE TABLE [Category](
id INT IDENTITY(1,1),
title VARCHAR(256),
createdOn DATETIME DEFAULT CURRENT_TIMESTAMP,
PRIMARY KEY(id)
);
 */
public class Category {
    public int id;
    public String title;
    public String createdOn;

    public Category() {
    }

    public Category(int id, String title, String createdOn) {
        this.id = id;
        this.title = title;
        this.createdOn = createdOn;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", createdOn='" + createdOn + '\'' +
                '}';
    }

    public void prettyPrint() {
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("id:\t\t"+id);
        System.out.println("title:\t\t"+title);
        System.out.println("createdOn:\t\t"+createdOn);
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }
}
