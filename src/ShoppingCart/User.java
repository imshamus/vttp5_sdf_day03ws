package src.ShoppingCart;

import java.util.ArrayList;
import java.util.List;

public class User 
{
    private String username;

    // Methods
    public static String userLogin (String userInput)
    {
        if (userInput.startsWith("login"))
        {
            String username = userInput.substring(5).trim();

            if (!username.isEmpty())
            {
                System.out.printf("%s logged on to system. \n", username);
                return username;
            }
            else
            {
                System.out.println("Username cannot be empty.");
                return null;  
            }
        }
        else
        {
            return null;
        }
        
    }

    public static void listUser(List<String> userList)
    {   
        int i = 1; 

        for (String user : userList)
        {
            System.out.printf("%d. %s \n", i, user);
            i++;
        }
    }

    // Constructor
    public User(String username) {
        this.username = username;
    }

    // Getter Setter
    public String getUsername() {
        return this.username;
    }

    // public void setUsername(String username) {
    //     this.username = username;
    // }
    
     
}
