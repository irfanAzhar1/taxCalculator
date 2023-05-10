package id.co.notes.taxcalculator.model;

public class Employee{
    private String name;
    private String sex;
    private String maritalStatus;
    private int childs;
    private String country;

    // setter and getter
    public String getName(){
        return name;
    }

    public String getSex() {
        return sex;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public boolean isMarried() {
        return maritalStatus.equals("married");
    }

    public int getChilds() {
        return childs;
    }

    public String getCountry() {
        return country;
    }


}
