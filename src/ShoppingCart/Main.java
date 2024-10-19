package src.ShoppingCart;

import java.io.Console;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main 
{
    public static void main(String[] args) 
    {
        List<String> userList = new ArrayList<>();
        Cart cart = new Cart();
        ShoppingCartDB db = new ShoppingCartDB(args);
        String filePath = "";
        String username = "";

        Console cons = System.console();

        System.out.println("Welcome to your shopping cart!");
        System.out.println("==============================");

        displayMenu();

        while(true)
        {           
            String userInput = cons.readLine("> ").toLowerCase();
            String command = parseCommand(userInput);

            switch (command)
            {
                case "login":
                    username = User.userLogin(userInput);
                    userList.add(username);
                    
                    if (username != null)
                    {
                        // User user = new User(username);
                        filePath = db.getBaseDir() + "/" + username + ".txt";
                        db.makeFile(filePath);
                        
                        try
                        {
                            cart.loadCart(filePath);
                            System.out.printf("%s's cart loaded from %s. \n", username, filePath);
                        }
                        catch (IOException e)
                        {
                            System.out.printf("Unable to load %s's cart.", username);
                        }
                    }
                    else
                    {
                        System.out.println("Login failed. Please try again.");
                    }
                    break;

                case "users":
                    User.listUser(userList);
                    break;    

                case "quit":
                    System.out.println("Thank you! See you again..");
                    return;
                    
                case "list":
                    cart.listItems();
                    break;
                
                case "add":
                    handleAddItem(userInput, cart);
                    break;

                case "delete":
                    handleDeleteItem(userInput, cart);
                    break;

                case "menu":
                    displayMenu();
                    break;

                // case "load":

                case "save":
                    try
                    {
                        cart.saveCart(filePath);
                        System.out.printf("%s's cart saved at %s. \n", username, filePath);
                        
                        break;
                    }

                    catch (IOException e)
                    {
                        System.out.println("Unable to save cart at " + filePath);
                    }
                    


                default:
                    System.out.println("Please enter a valid input.");
            }      
        }
    }

    public static void displayMenu() // Method 1 - Printing Instructions
    {
        // Create and print menu instructions
         System.out.println("To login/load other user's cart:   Enter 'login'<SPACE><USERNAME>");
         System.out.println("To display list of user(s):        Enter 'users'");
         System.out.println("To display current user's cart:    Enter 'list'");
         System.out.println("To add items to cart:              Enter 'add'<SPACE><ITEM>");
         System.out.println("To delete items from cart:         Enter 'delete'<SPACE><S/N>");
         System.out.println("To save current user's cart:       Enter 'save'");
         System.out.println("To terminate program:              Enter 'quit'");
         System.out.println("To display instructions again:     Enter 'menu'"); 
    }

    public static String parseCommand(String userInput)
    {
        String command = userInput.split(" ")[0];
        return command;
    }

    public static void handleAddItem(String userInput, Cart cart)
    {
        String[] items = userInput.substring(4).split(",");

        for (int i = 0; i < items.length; i++) // length for Array, size for collections e.g. ArrayList
        {
            items[i] = items[i].trim();
        }

        for (String item : items)
        {
            cart.addItem(item);
        }
    }

    public static void handleDeleteItem(String userInput, Cart cart)
    {
        try
        {
            String[] indexes = userInput.substring(7).split(",");
            List<Integer> indexList = new ArrayList<>();

            for (int i = 0; i < indexes.length; i++)
            {
                indexes[i] = indexes[i].trim();
            }

            for (String index : indexes)
            {
                int i = Integer.parseInt(index);
                indexList.add(i);
            }

            Collections.sort(indexList, Collections.reverseOrder()); // Sort in descending order

            for (int index : indexList)
            {
                cart.deleteItem(index);
            }
        }
        catch (NumberFormatException e)
        {
            System.out.println("Please enter an index.");
        }
        
    }

}
