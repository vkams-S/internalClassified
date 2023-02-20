package com.amazon.Model;

/*
CREATE TABLE Pictures(
id INT IDENTITY(1,1),
imageUrl VARCHAR(1024),
PRIMARY KEY(id)
)
 */
public class Pictures {
    public int id;
    public String imageUrl;

    public Pictures() {
    }

    public Pictures(int id, String imageUrl) {
        this.id = id;
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString() {
        return "Pictures{" +
                "id=" + id +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }
}
