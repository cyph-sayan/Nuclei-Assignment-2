package models.requests;

public class GetUserRequest {
    private int rollNo;
    public GetUserRequest(int rollNo)
    {
        this.rollNo=rollNo;
    }
    public int getRollNo() {
        return rollNo;
    }
}
