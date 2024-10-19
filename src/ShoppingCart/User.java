package src.ShoppingCart;

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
