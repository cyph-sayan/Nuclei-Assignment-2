package filehandlers;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileHandlerImpl implements FileHandler{

    @Override
    public String getQueryStatement(String pathname) {
        try {
            Scanner sc=new Scanner(new File(pathname));
            String queryStatement=sc.nextLine();
            sc.close();
            return queryStatement;
        }catch (FileNotFoundException fileNotFoundException)
        {
            System.out.println(fileNotFoundException.getMessage());
        }
        return null;
    }
}

