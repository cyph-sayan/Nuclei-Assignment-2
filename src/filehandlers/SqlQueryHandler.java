package filehandlers;

public class SqlQueryHandler {
    public static String insertIntoCourse()
    {
        FileHandler fh=new FileHandlerImpl("InsertIntoCourse.sql");
        return fh.getInsertIntoCourse();
    }
    public static String delStudentDetails()
    {
        FileHandler fh=new FileHandlerImpl("DeleteFromStudent.sql");
        return fh.deleteStudent();
    }
    public static String insertIntoStudent()
    {
        FileHandler fh=new FileHandlerImpl("InsertToStudent.sql");
        return fh.getInsertIntoStudent();
    }
    public static String listStudents(){
        FileHandler fh=new FileHandlerImpl("ListStudents.sql");
        return fh.listStudents();
    }
    public static String getCourseDetails()
    {
        FileHandler fh=new FileHandlerImpl("SelectFromCourse.sql");
        return fh.listStudents();
    }
    public static String getStudentDetails(){
        FileHandler fh=new FileHandlerImpl("SelectFromStudent.sql");
        return fh.listStudents();
    }
}
