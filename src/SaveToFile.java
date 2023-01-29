import io.github.cdimascio.dotenv.Dotenv;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SaveToFile {
    void save()throws Exception{
        Dotenv dotenv=Dotenv.load();
        List<StudentInfo> studentInfoList=new ArrayList<>();
        FileOutputStream fout=null;
        ObjectOutputStream oos=null;
        try(Connection conn= DriverManager.getConnection(dotenv.get("URL"), dotenv.get("USERNAME"), dotenv.get("PASS"));
            Statement stmt=conn.createStatement();)
        {
            String sql="USE nucleiassignment";
            stmt.execute(sql);
            String query="SELECT * from studentinfo order by name asc";
            ResultSet rs=stmt.executeQuery(query);
            while(rs.next())
            {
                List<String> courses=new ArrayList<String>();
                String name=rs.getString("Name");
                int age=rs.getInt("Age");
                int roll=rs.getInt("Roll");
                String address=rs.getString("Address");
                PreparedStatement preparedStatement=conn.prepareStatement("Select courseid from courseinfo where roll=(?) ORDER BY COURSEID ASC");
                preparedStatement.setString(1,String.valueOf(roll));
                ResultSet rs1=preparedStatement.executeQuery();
                while(rs1.next())
                {
                    courses.add(rs1.getString(1));
                }
                StudentInfo studentInfo=new StudentInfo(name,age,address,roll,courses);
                studentInfoList.add(studentInfo);
            }
            try{
                fout= new FileOutputStream("file.txt",true);
                oos=new ObjectOutputStream(fout);
                for(int i=0;i<studentInfoList.size();i++)
                {
                    oos.writeObject(studentInfoList.get(i));
                }
            }catch (Exception e)
            {
                e.printStackTrace();
            }
        } catch(SQLException e){
            e.printStackTrace();
        }finally {
            if (oos!=null){
                oos.close();
            }
        }
    }
}
