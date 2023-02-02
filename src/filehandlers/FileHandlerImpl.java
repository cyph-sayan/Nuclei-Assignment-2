package filehandlers;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileHandlerImpl implements FileHandler{
    Scanner sc;
    public FileHandlerImpl(String pathname){
        try{
            sc=new Scanner(new File(pathname));
        }catch (FileNotFoundException fe)
        {
            fe.printStackTrace();
            System.out.println(fe.getMessage());
        }
    }
    @Override
    public String getQueryStatement() {
        return sc.nextLine();
    }
}

