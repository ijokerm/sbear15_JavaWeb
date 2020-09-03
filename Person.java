package Java程序设计实验;

public class Person {
    private String name;
    private String address;
    private String telephone;
    public Person(String name, String address, String telephone) {
        super();
        this.name = name;
        this.address = address;
        this.telephone = telephone;
    }
    @Override
    public String toString() {
        return "name=" + name + ", address=" + address + ", telephone=" + telephone ;
    }
}
