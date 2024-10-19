package src.ShoppingCart;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ShoppingCartDB 
{
    // private static final String DEFAULTDIR = "cartdb"; 
    private String baseDir;
    private String userFile;

    // Constructors 
    public ShoppingCartDB(String[] args)
    {
        if (args.length > 0)        // check args to make default/input directory
        {
            baseDir = args[0];     // input directory
            makeDir(baseDir);
        }
        else
        {
            baseDir = "cartdb";
            makeDir(baseDir);    // default directory
        }
  
    }

    // Getters Setters
    public String getBaseDir() {
        return baseDir;
    }

    public void setBaseDir(String baseDir) {
        this.baseDir = baseDir;
    }

    public String getUserFile() {
        return userFile;
    }

    public void setUserFile(String userFile) {
        this.userFile = userFile;
    }

    // Methods
    public void makeDir(String dirPath)
    {
        Path p = Paths.get(dirPath);
        String absDirPath = p.toFile().getAbsolutePath(); // convert path to file obj to use file.getAbs

        try
        {
            if (!Files.exists(p))
            {
                Files.createDirectories(p);
                System.out.println("Directory created at: " + absDirPath);
            } 

            else
            {
                System.out.printf("%s already created. \n", absDirPath);
            } 
        }
        
        catch (IOException e)
        {
            System.out.println("Unable to create directory at: " + absDirPath);
        }
       
    }

    public void makeFile(String filePath)
    {
        File f = new File(filePath);
        String absFilePath = f.getAbsolutePath();

        try
        {
            if(!f.exists())
            {
                f.createNewFile();
                System.out.println("File created at: " + absFilePath);
            }

            else
            {
                System.out.printf("%s already created. \n", absFilePath);
            }
        }

        catch (IOException e)
        {
            System.out.println("Unable to create file at: " + absFilePath);
        }

    }

}
