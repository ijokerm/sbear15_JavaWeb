package Java程序设计实验;

import java.util.Scanner;

public class BOX {

    int length;
    int width;
    int height;
    int V;

    public void setBox(int l, int w, int h) {

        length = l;
        width = w;
        height = h;
    }

    public int volume() {

        V = length * width * height;
        return V;

    }

    public static void main(String[] args) {

        BOX b = new BOX();
        Scanner input = new Scanner(System.in);
        System.out.println("请输入长方体的长，宽，高: ");
        int l = input.nextInt();
        int w = input.nextInt();
        int h = input.nextInt();
        b.setBox(l, w, h);
        System.out.println("长方体的体积： " + b.volume());

    }
}