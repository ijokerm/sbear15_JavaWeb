package Java程序设计实验;

public class Employee extends Person {
    private String office;
    private double wage;
    public Employee(String name, String address, String telephone, String office, double wage) {
        super(name, address, telephone);
        this.office = office;
        this.wage = wage;
    }
    @Override
    public String toString() {
        return  super.toString()+", office=" + office + ", wage=" + wage   ;
    }
}
