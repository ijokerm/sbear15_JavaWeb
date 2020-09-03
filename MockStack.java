package Java程序设计实验;
import java.util.LinkedList;

public class  MockStack {

        private LinkedList data = new LinkedList();
        public boolean idEmpty(){
            return data.isEmpty();
        }
        public Object top(){
            //取栈顶元素相当于取LinkedList容器的头位置的元素
            return data.getFirst();
        }
        public void push(Object element){
            //压入元素相当于在LinkedList容器的头位置插入元素
            data.addFirst(element);
        }
        public void pop(){
            //弹出元素相当于删除LinkedList容器的头位置的元素
            data.removeFirst();
        }
        public String toString(){
            return data.toString();
        }
        public static void main(String[] args){
            MockStack stack = new MockStack();
            //下面的语句使用LinkedList实现的堆栈的用法
            stack.push("Shandong");
            stack.push("University Of Technology");
            stack.push("StephenLi");
            while (!stack.idEmpty()){
                System.out.println("即将弹出的成员是：" + stack.top());
                stack.pop();
                System.out.println("当前栈中剩余的成员为:" + stack);
            }
        }
    }

