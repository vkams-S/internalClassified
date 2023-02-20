package com.amazon.Model;

/*
    CREATE TABLE Classified(
    id INT IDENTITY(1,1),
    categoryId INT,
    headLine VARCHAR(256),
    productName VARCHAR(256),
    brand VARCHAR(256),
    condition VARCHAR(256),
    description VARCHAR(1024),
    price VARCHAR(256),
    imageId INT,
    availability INT,
    userId INT,
    status INT,
    createdOn DATETIME DEFAULT CURRENT_TIMESTAMP,
	PRIMARY KEY(id),
	FOREIGN KEY (userId) REFERENCES [User](id) ON DELETE CASCADE,
	FOREIGN KEY (imageId) REFERENCES Pictures(id) ON DELETE CASCADE,
	FOREIGN KEY (categoryId) REFERENCES [Category](id) ON DELETE CASCADE
	);
 */
public class Classified {
    public int id;
    public int categoryId;
    public String headLine;
    public String productName;
    public String brand;
    public String condition; // Brand New, Lightly used, Moderately Used , Heavily Used, Damaged/Dented, Not working
    public String description;
    public String price;
    public int imageId;
    public int availability; //1. Available 2. sold out
    public int userId;

    public int status; // 0.Pending 1.Approved 2.Cancelled
    public String createdOn;

    public Classified() {
    }

    public Classified(int id, int categoryId, String headLine, String productName, String brand, String condition, String description, String price, int imageId, int availability, int userId, int status, String createdOn) {
        this.id = id;
        this.categoryId = categoryId;
        this.headLine = headLine;
        this.productName = productName;
        this.brand = brand;
        this.condition = condition;
        this.description = description;
        this.price = price;
        this.imageId = imageId;
        this.availability = availability;
        this.userId = userId;
        this.status = status;
        this.createdOn = createdOn;
    }

    @Override
    public String toString() {
        return "Classified{" +
                "id=" + id +
                ", categoryId='" + categoryId + '\'' +
                ", headLine='" + headLine + '\'' +
                ", productName='" + productName + '\'' +
                ", brand='" + brand + '\'' +
                ", condition=" + condition +
                ", description='" + description + '\'' +
                ", price='" + price + '\'' +
                ", imageId='" + imageId + '\'' +
                ", availability=" + availability +
                ", userId=" + userId +
                ", status=" + status +
                ", createdOn='" + createdOn + '\'' +
                '}';
    }

    public void prettyPrint() {
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("id:\t\t"+id);
        System.out.println("categoryId:\t\t"+categoryId);
        System.out.println("headLine:\t\t"+headLine);
        System.out.println("productName:\t\t"+productName);
        System.out.println("brand:\t"+brand);
        System.out.println("condition:\t"+condition);
        System.out.println("description:\t"+description);
        System.out.println("price:\t"+price);
        System.out.println("imageId:\t"+imageId);
        System.out.println("availability:\t"+((availability==1)?"AVAILABLE":"SOLD OUT"));
        switch (status)
        {
            case 0:System.out.println("status:\t"+"Pending");
                break;
            case 1:System.out.println("status:\t"+"Approved");
                break;
            case 2:System.out.println("status:\t"+"Cancelled");
                break;
            default:break;
        }
        System.out.println("status:\t"+status);
        System.out.println("createdOn:\t"+createdOn);
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }
}
