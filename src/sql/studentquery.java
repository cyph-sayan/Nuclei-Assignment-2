package sql;

public class studentquery {
    public static String insertIntoStudent(String name, String roll,String age, String address) {
        return String.format("INSERT INTO studentinfo Values (\"%s\",%s,%s,\"%s\");",name,roll,age,address);
    }
    public static String insertIntoCourse(String course, String roll){
        return String.format("INSERT INTO COURSEINFO VALUES (\"%s\",%s)",course,roll);
    }
    public static String getStudentDetails(String roll){
        return String.format("SELECT * FROM studentinfo WHERE roll=%s",roll);
    }
    public static String getCourseDetails(String roll)
    {
        return String.format("SELECT COURSEID FROM COURSEINFO WHERE ROLL=%s ORDER BY COURSEID ASC",roll);
    }
    public static String delStudentDetails(String roll)
    {
        return String.format("DELETE FROM STUDENTINFO WHERE ROLL=%s",roll);
    }
    public static String listStudents()
    {
        return String.format("SELECT * FROM studentinfo");
    }
}
