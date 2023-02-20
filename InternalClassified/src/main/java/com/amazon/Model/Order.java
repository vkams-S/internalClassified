package com.amazon.Model;
/*\

CREATE TABLE [Order](
	id INT IDENTITY(1,1),
	classifiedId INT,
	fromUserId INT,
	toUserId INT,
	proposedPrice VARCHAR(256),
	status INT,
	lastUpdatedOn DATETIME DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY(id),
	FOREIGN KEY (classifiedId) REFERENCES Classified (id) ON DELETE CASCADE,
	FOREIGN KEY (fromUserId) REFERENCES [User](id) ON DELETE CASCADE,
	FOREIGN KEY (toUserId) REFERENCES [User](id) ON DELETE CASCADE
	)
 */
public class Order {
    public int id;
    public int classifiedId;
    public int fromUserId;
    public int toUserId;
    public String proposedPrice;
    public int  status; // 0 -> requested for purchase,1 -> Approved.. Agreed to sell
    // 2 -> rejected : not interested to sell, 3-> payment processed : product sold.
    public String lastUpdatedOn;

    public Order() {
    }

    public Order(int id, int classifiedId, int fromUserId, int toUserId, String proposedPrice, int status, String lastUpdatedOn) {
        this.id = id;
        this.classifiedId = classifiedId;
        this.fromUserId = fromUserId;
        this.toUserId = toUserId;
        this.proposedPrice = proposedPrice;
        this.status = status; // 0. Proposed 1. Accept 2. Reject
        this.lastUpdatedOn = lastUpdatedOn;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", classifiedId=" + classifiedId +
                ", fromUserId=" + fromUserId +
                ", toUserId=" + toUserId +
                ", proposedPrice='" + proposedPrice + '\'' +
                ", status=" + status +
                ", lastUpdatedOn='" + lastUpdatedOn + '\'' +
                '}';
    }

    public void prettyPrint() {
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("id:\t\t"+id);
        System.out.println("classifiedId:\t\t"+classifiedId);
        System.out.println("fromUserId:\t\t"+fromUserId);
        System.out.println("toUserId:\t\t"+toUserId);
        System.out.println("proposedPrice:\t"+proposedPrice);
        switch (status)
        {
            case 0:System.out.println("status:\t"+"Proposed");
                break;
            case 1:System.out.println("status:\t"+"Accepted");
                break;
                case 2:System.out.println("status:\t"+"Rejected");
            break;
            default:break;
        }
        System.out.println("status:\t"+status);
        System.out.println("lastUpdatedOn:\t"+lastUpdatedOn);
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }
}
