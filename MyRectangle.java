package Java程序设计实验;

public class MyRectangle extends Shape {
    private double length;
    private double width;
    public MyRectangle(double length, double width) {
        super();
        this.length = length;
        this.width = width;
    }
    @Override
    double area() {
        return length*width;
    }
    /*
     * 覆盖了父类Shape的displayArea函数
     */
    @Override
    void displayArea() {
        System.out.println("Rectangle's area:"+area());
    }
}
