package models.requests;
import enums.SortFieldOptions;
import enums.SortOption;
public class ListUserRequest {
    private SortOption sortOption;
    private SortFieldOptions sortFieldOptions;
    public ListUserRequest(SortOption sortOption, SortFieldOptions sortFieldOptions)
    {
        this.sortOption=sortOption;
        this.sortFieldOptions=sortFieldOptions;
    }
    public SortOption getSortOption(){
        return this.sortOption;
    }
    public SortFieldOptions getSortFieldOptions()
    {
        return this.sortFieldOptions;
    }
}
