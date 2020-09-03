package Java程序设计实验;

public class MyTriangle extends Shape {
    private double length;
    private double height;
    public MyTriangle(double length, double height) {
        super();
        this.length = length;
        this.height = height;
    }
    @Override
    double area() {
        return 0.5*length*height;
    }
    /*
     * 覆盖了父类Shape的displayArea函数
     */
    @Override
    void displayArea() {
        System.out.println("Triangle's area:"+area());
    }
}