package models.requests;

public class DeleteUserRequest {
    private int rollNo;
    public DeleteUserRequest(int rollNo){
        this.rollNo=rollNo;
    }
    public int getRollNo(){
        return this.rollNo;
    }
}
