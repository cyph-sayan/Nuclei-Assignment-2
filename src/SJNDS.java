import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SJNDS {
    public static void main(String[] args) throws IOException {
        List<StudentInfo> results = new ArrayList<>();
        FileInputStream fis = new FileInputStream("file.txt");
        try {
            while (true) {
                ObjectInputStream ois=new ObjectInputStream(fis);
                results.add((StudentInfo) ois.readObject());
            }
        }catch (ClassNotFoundException ce){
            System.out.println("error");
        } catch (EOFException e) {
            System.out.println("Error");
        } finally {
            fis.close();
        }
        for(int i=0;i<results.size();i++)
        {
            System.out.println(results.get(i));
        }
    }
}

