package models.requests;
import enums.SortFieldOptions;
import enums.SortOption;
public class ListUserRequest {
    private int rollNo;
    public ListUserRequest(int rollNo)
    {
        this.rollNo=rollNo;
    }
    public int getRollNo() {
        return rollNo;
    }
}
