package views;

import models.entities.User;
import java.util.List;

public class DisplayStudents {
    public void display(List<User> user)
    {
        for (int i=0;i< user.size();i++)
            System.out.print(user.get(i).fullName+" "+user.get(i).address+" "+user.get(i).age+" "+user.get(i).rollNo+" "+user.get(i).courses);
    }
}
