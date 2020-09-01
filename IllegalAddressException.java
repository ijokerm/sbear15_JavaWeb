package Java程序设计实验;

public class IllegalAddressException extends Exception {
    public IllegalAddressException(){
        super();
    }
    public IllegalAddressException(String s){
        super(s);
    }
}
