package l;

public class Course {

    private final String cid;
    private final String cName;
    private final String tName;
    private final String clsroom;
    private final int timeOfWeek;

    public Course(String cid, String cName, String tName, String clsroom, int timeOfWeek) {
        this.cid = cid;
        this.cName = cName;
        this.tName = tName;
        this.clsroom = clsroom;
        this.timeOfWeek = timeOfWeek;
    }

    public String getCourseID() {
        return cid;
    }

    public String getCourseName() {
        return cName;
    }

    public String getTeacherName() {
        return tName;
    }

    public String getPlace() {
        return clsroom;
    }

    public int getTimeOfWeek() {
        return timeOfWeek;
    }
}