package com.amazon.Services;

import com.amazon.DB.ClassifiedDAO;
import com.amazon.DB.OrderDAO;
import com.amazon.Model.Classified;
import com.amazon.Model.Order;
import com.amazon.Session.ICSession;

import java.util.List;
import java.util.Scanner;

public class OrderManagementService {
    private OrderManagementService(){}
    private static OrderManagementService orderManagementService = new OrderManagementService();
    public static OrderManagementService getInstance()
    {
        return orderManagementService;
    }
    ClassifiedDAO classifiedDAO = new ClassifiedDAO();
    ClassifiedManagementService classifiedManagementService = ClassifiedManagementService.getInstance();
    OrderDAO orderDAO = new OrderDAO();
    Scanner scanner = new Scanner(System.in);
    public void Buy()
    {
        classifiedManagementService.viewAllClassified(true);
        Order order = new Order();
        System.out.println("Enter the Classified Id to buy::");
        order.classifiedId=Integer.parseInt(scanner.nextLine());
        order.fromUserId = ICSession.user.id;
        Classified classified = classifiedDAO.retrieve("SELECT * FROM [Classified] WHERE id="+order.classifiedId).get(0);
        order.toUserId = classified.userId;
        System.out.println("Enter the proposed price:");
        order.proposedPrice = scanner.nextLine();
       if(orderDAO.insert(order)>0){
           System.out.println("Successfully placed your order proposal");
       }else {
           System.out.println("Something went wrong!");
       }
    }

    public void Sell()
    {
        List<Order> orderList = orderDAO.retrieve("SELECT * FROM [Order] WHERE toUserId="+ICSession.user.id+" AND status=0");
        int totalOrder = orderList.size();
        if(totalOrder==0)
        {
            System.out.println("Sorry you do not have any order proposal pending!");
        }else{
            System.out.println("You have Total "+totalOrder+" order proposals::");
            for(Order order:orderList)
            {
                order.prettyPrint();
                System.out.println("Classified Details::");
                classifiedManagementService.viewClassifiedById(order.classifiedId).prettyPrint();
            }
            System.out.println("Enter Order Which you want to accept/reject::");
            int orderID = Integer.parseInt(scanner.nextLine());
            System.out.println("1. Accept");
            System.out.println("2. Reject");
            int status = Integer.parseInt(scanner.nextLine());
            Order newOrder = new Order();
            newOrder = orderManagementService.getOrderById(orderID);
            newOrder.status=status;
            if(orderDAO.update(newOrder)>0)
            {
                System.out.println("Sell Order is Successful");
                Classified classified = classifiedManagementService.viewClassifiedById(newOrder.classifiedId);
                classified.availability=2;
                classifiedDAO.update(classified);
            }else{
                System.err.println("Something went wrong!");
            }
        }
    }

    public Order getOrderById(int orderId)
    {
        return orderDAO.retrieve("SELECT * FROM [Order] where id="+orderId).get(0);
    }

    public void ViewOrderReport()
    {
        System.out.println("1. View All the place order report");
        System.out.println("2. View All sold order report");
        System.out.println("Enter your choice:");
        int choice = Integer.parseInt(scanner.nextLine());
        switch (choice)
        {
            case 1:  List<Order> orderList = orderDAO.retrieve();
                    if(orderList.size()==0)
                    {
                        System.out.println("No order placed so far!");
                    }else{
                        for(Order order:orderList)
                        {
                            order.prettyPrint();
                        }
                    }
                    break;
            case 2: orderList = orderDAO.retrieve("SELECT * FROM [Order] WHERE status=1");
                if(orderList.size()==0)
                {
                    System.out.println("No order sold so far!");
                }else{
                    for(Order order:orderList)
                    {
                        order.prettyPrint();
                    }
                }
                break;
            default:
                System.err.println("Invalid choice...");
                break;

        }
    }
}
