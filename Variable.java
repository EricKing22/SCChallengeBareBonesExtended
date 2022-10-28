package week3;

public class Variable {
    String id;
    int num;
    public Variable(String name){
        id = name;
    }
    public void Setnumber(){
        num = 0;}
    public int Getnumber(){
        return num;
    }
    public void Incr(){
        num++;
    }
    public void Decr(){
        num--;
    }

    public void Plus(int a, int b){
        num = a + b;
    }

    public void Minus(int a, int b){
        num = a - b;
    }

    public void Multiply(int a, int b){
        num = a * b;
    }

    public void Divide(int a, int b){
        num = a / b;
    }





}
