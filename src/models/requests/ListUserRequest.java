package models.requests;


public class ListUserRequest {
    public int pageSize;
    public int sortField;
    public int sortWays;
    public ListUserRequest(int pageSize,int sortField,int sortWays){
        this.pageSize=pageSize;
        this.sortField=sortField;
        this.sortWays=sortWays;
    }
}
