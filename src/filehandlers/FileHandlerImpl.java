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
            System.out.println(fe.getMessage());
        }
    }
    @Override
    public String getInsertIntoStudent() {
        return sc.nextLine();
    }

    @Override
    public String listStudents() {
        return sc.nextLine();
    }

    @Override
    public String getStudent() {
        return sc.nextLine();
    }

    @Override
    public String deleteStudent() {
        return sc.nextLine();
    }

    @Override
    public String listCourses() {
        return sc.nextLine();
    }

    @Override
    public String getInsertIntoCourse() {
        return sc.nextLine();
    }
}
