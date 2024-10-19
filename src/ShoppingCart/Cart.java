package src.ShoppingCart;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Cart 
{
    private List<String> items;

    // CONSTRUCTOR
    public Cart() {
        this.items = new ArrayList<>();
    }

    // METHODS
    public void addItem(String item)
    {
        if(!items.contains(item))
        {
            items.add(item);
            System.out.printf("%s added to cart. \n", item);
        }
        else 
        {
            System.out.printf("%s already in cart. \n", item);
        }
    }

    public void deleteItem(int index)
    {
        if(index > 0 && index <= items.size())
        {
            String removedItem = items.remove(index-1);
            System.out.printf("%s removed from cart. \n", removedItem);
        }
        else if (items.isEmpty())
        {
            System.out.println("Cart is empty, please add some items first.\n ");
        }
        else
        {
            System.out.println("Invalid index. Unable to remove item.");
        }
    }

    public void listItems()
    {
        if (items.isEmpty())
        {
            System.out.println("Cart is empty, please add some items first.\n ");
        }
        else
        {
            System.out.println("Item(s) currently in list:");
            for (int i = 0; i < items.size(); i++)
            {
                System.out.printf("%d. %s \n", i+1, items.get(i));
            }
        }
    }

    public void clearCart()
    {
        items.clear();
    }

    public void saveCart(String filePath) throws IOException
    {
        FileWriter fw = new FileWriter(filePath, false); // Don't append, overwrite the file.
        BufferedWriter bw = new BufferedWriter(fw);

        for (String item : items)
        {
            bw.write(item);
            bw.newLine();
        }

        bw.flush();
        bw.close();
        fw.close();
    }

    public void loadCart(String filePath) throws IOException
    {
        clearCart();
        
        FileReader fr = new FileReader(filePath);
        BufferedReader br = new BufferedReader(fr);

        String line = "";

        while ((line = br.readLine()) != null)
        {
            addItem(line);
        }

        br.close();
    }

    // GETTERS
    public List<String> getItems() {
        return items;
    }

    // public void setItems(List<String> items) {             // no need for setter
    //     this.items = items;
    // }  
           
} 