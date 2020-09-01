package Java程序设计实验;

import java.util.Scanner;
public class BankAccount {
    int account_number;
    double leftmoney;

    public double getleftmoney () {  //查询余额
         return leftmoney;
           }
           public void savemoney(double money) { //存款
          leftmoney+=money;
 }
    public void getmoney (double money){ //取款
          leftmoney-=money;
  }

    public BankAccount (int number, double money){ //构造方法，用来初始化变量
          account_number=number;
          leftmoney=money;
          System.out.println("账户 : "+account_number+"    余额 : "+leftmoney);
   }
    public static void main(String args[]) {
          BankAccount ba = new BankAccount(123456,500);
          Scanner input = new Scanner(System.in);
          System.out.println("请输入您存款的金额： ");
          int save = input.nextInt();
          ba.savemoney(save);
          System.out.println("存入"+save+"元后，您的余额为："+ba.getleftmoney());
          double qian=ba.getleftmoney();
          System.out.println("请输入您取款的金额： ");
          int out = input.nextInt();
          ba.getmoney(out);
          if(out<qian)
          System.out.println("取款"+out+"元后，您的余额为："+ba.getleftmoney());
          else
              System.out.println("您的余额只有"+qian+"所以您不能取更多的钱");
          }
      }
