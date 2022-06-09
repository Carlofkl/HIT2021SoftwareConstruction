package l;

public class Employee {

    private final String name;
    private final String job;
    private final String phoneNum;

    public Employee(String name, String job, String phoneNum) {
        this.name = name;
        this.job = job;
        this.phoneNum = phoneNum;
    }

    public String getName(){
        return name;
    }

    public String getJob(){
        return job;
    }

    public String getPhoneNum(){
        return phoneNum;
    }
}