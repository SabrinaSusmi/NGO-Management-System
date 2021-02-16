package Accounts.Designation;

public class Designation {
    int salary,numOfEmployee;
    String desigName, desigType, desigID;

    public int getNumOfEmployee() {
        return numOfEmployee;
    }

    public void setNumOfEmployee(int numOfEmployee) {
        this.numOfEmployee = numOfEmployee;
    }

    public Designation(int salary, String desigName, String desigType, String desigID, int numOfEmployee) {
        this.salary = salary;
        this.desigName = desigName;
        this.desigType = desigType;
        this.desigID = desigID;
        this.numOfEmployee=numOfEmployee;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public String getDesigName() {
        return desigName;
    }

    public void setDesigName(String desigName) {
        this.desigName = desigName;
    }

    public String getDesigType() {
        return desigType;
    }

    public void setDesigType(String desigType) {
        this.desigType = desigType;
    }

    public String getDesigID() {
        return desigID;
    }

    public void setDesigID(String desigID) {
        this.desigID = desigID;
    }
}
