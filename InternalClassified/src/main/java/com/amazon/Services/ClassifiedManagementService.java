package com.amazon.Services;

import com.amazon.DB.CategoryDAO;
import com.amazon.DB.ClassifiedDAO;
import com.amazon.DB.PicturesDAO;
import com.amazon.Model.Category;
import com.amazon.Model.Classified;
import com.amazon.Model.Pictures;
import com.amazon.Session.ICSession;

import java.lang.ref.SoftReference;
import java.util.List;
import java.util.Scanner;

public class ClassifiedManagementService {
    private ClassifiedManagementService(){}
    private static ClassifiedManagementService classifiedManagementService = new ClassifiedManagementService();
    public static ClassifiedManagementService getInstance()
    {
        return classifiedManagementService;
    }
    Scanner scanner = new Scanner(System.in);

    PicturesDAO picturesDAO = new PicturesDAO();
    ClassifiedDAO classifiedDAO = new ClassifiedDAO();
    CategoryDAO categoryDAO = new CategoryDAO();

    public void postClassified(){
        Classified classified = new Classified();
        Pictures image = new Pictures();
        System.out.println("Please Enter the head line of the classified[Limit:100 character]:");
        classified.headLine = scanner.nextLine();
        System.out.println("Please Enter the Name of the product[Limit:50 character]:");
        classified.productName = scanner.nextLine();
        System.out.println("Please Enter Brand of the product(if any)[Limit:25 character]:");
        classified.brand = scanner.nextLine();
        System.out.println("Please select the condition of the product::");
        System.out.println("1. Brand New(Seal packed)");
        System.out.println("2. Lightly Used");
        System.out.println("3. Moderately Used");
        System.out.println("4. Heavily Used");
        System.out.println("5. Damaged/Dented");
        System.out.println("6. Not working");
        switch(Integer.parseInt(scanner.nextLine()))
        {
            case 1: classified.condition = "Brand New(Seal packed)";
                break;
            case 2:classified.condition = "Lightly Used";
                break;
            case 3:classified.condition = "Moderately Used";
                break;
            case 4:classified.condition = "Heavily Used";
                break;
                case 5:classified.condition = "Damaged/Dented";
            break;
            case 6:classified.condition = "Not working";
                break;
            default:
                System.err.println("Invalid choice..");
                break;
        }
        System.out.println("Enter the detail description of the product[Limit:500 character]:");
        classified.description = scanner.nextLine();
        System.out.println("Enter the price you're offering at:");
        classified.price = scanner.nextLine();
        System.out.println("Enter the URL of the image:");
        image.imageUrl = scanner.nextLine();
        classified.status=0;
        classified.categoryId=1; // default category  = 'unknown' : Admin is responsible to set this to correct category
        classified.availability=1;
        classified.userId = ICSession.user.id;
        if(picturesDAO.insert(image)>0){
            image = picturesDAO.retrieve("SELECT * FROM [Pictures] WHERE imageUrl='"+image.imageUrl+"'").get(0);
            classified.imageId = image.id;
        }else {
            System.err.println("Something went wrong setting up image.");
        }

       if(classifiedDAO.insert(classified)>0) {
           System.out.println("Classified posted Successfully.");
       }else {
           System.err.println("Something went wrong while posting classified.");
       }
    }

    public void viewAllClassified(boolean buyable)
    {
        List<Classified> classifiedList = null;
        if(buyable){
            classifiedList  = classifiedDAO.retrieve("SELECT * FROM [Classified] WHERE status=1 AND availability=1 AND userId<>"+ICSession.user.id);
        }else {
            classifiedList = classifiedDAO.retrieve("SELECT * FROM [Classified] WHERE status=1 AND availability=1");
        }
        for(Classified classified:classifiedList)
        {
            classified.prettyPrint();
        }
        if(classifiedList.size()==0)
        {
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            System.out.println("No classified available for sell!");
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        }


    }

    public Classified viewClassifiedById(int id)
    {
        return classifiedDAO.retrieve("SELECT * FROM [Classified] WHERE id="+id).get(0);
    }

    public void ApproveRejectClassified()
    {
        List<Classified> classifiedList = classifiedDAO.retrieve("SELECT * FROM [Classified] WHERE status=0");
        if(classifiedList.size()==0)
        {
            System.out.println("No classified pending for Approval");
        }else{
            for(Classified classified:classifiedList)
            {
                classified.prettyPrint();
            }
            System.out.println("Enter the classified to Approve/Reject:");
            int CID = Integer.parseInt(scanner.nextLine());
            System.out.println("1. Approved");
            System.out.println("2. Reject");
            int status = Integer.parseInt(scanner.nextLine());

            Classified newClassifiedObject = new Classified();
            newClassifiedObject = viewClassifiedById(CID);
            newClassifiedObject.status=status;
            if(classifiedDAO.update(newClassifiedObject) >0)
            {
                System.out.println("Successfully updated the classified status");
            }else{
                System.err.println("Something went wrong while updating classified status!");
            }
        }
    }

    public void AssignCategory()
    {
        List<Classified> classifiedList = classifiedDAO.retrieve("SELECT * FROM [Classified] WHERE categoryId=1");
        if(classifiedList.size()==0)
        {
            System.out.println("No classified to categorize");
        }else {
            for(Classified classified:classifiedList)
            {
                classified.prettyPrint();
            }
            System.out.println("Enter the Classified id to be categorized:");
            int CID = Integer.parseInt(scanner.nextLine());
            List<Category> categoryList = categoryDAO.retrieve();
            System.out.println("List of All the available categories::");
            for(Category category:categoryList)
            {
                category.prettyPrint();
            }
            System.out.println("Enter the category Id to be assigned:");
            int catId = Integer.parseInt(scanner.nextLine());

            Classified newClassified  = new Classified();
            newClassified = viewClassifiedById(CID);
            newClassified.categoryId = catId;
            if(classifiedDAO.update(newClassified)>0)
            {
                System.out.println("Categorization successful");
            }else{
                System.err.println("Something went wrong while categorizing!");
            }
        }
    }

    public void AddRemoveClassified()
    {
        System.out.println("1. Add a classified");
        System.out.println("2. Remove a classified");
        System.out.println("3. Cancel");
        System.out.println("Enter Your choice");
        int ch = Integer.parseInt(scanner.nextLine());
        switch (ch)
        {
            case 1: postClassified();
                break;
            case 2: viewAllClassified(false);
                System.out.println("Enter Classified Id to be removed:");
                int id = Integer.parseInt(scanner.nextLine());
                Classified object = new Classified();
                object = viewClassifiedById(id);
                if(classifiedDAO.delete(object)>0)
                {
                    System.out.println("Successfully removed classified");
                }else{
                    System.err.println("Something went wrong, while deleting classified!");
                }
                break;
            case 3: return;
            default:
                System.err.println("Invalid choice ...");
                break;
        }
    }


}
