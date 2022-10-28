package week3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class C3 {
    public static void main(String[] args) throws Exception{
        // Import all instructions to an arraylist line by line
        ArrayList<String> instructions = new ArrayList<String>();
        getInstruction(instructions);


        ArrayList<Variable> Variables= new ArrayList<Variable>();

        // Go through each line in the arraylist
        for (int a = 0; a < instructions.size(); a++){
            String line = instructions.get(a);
            if (line.contains("//")){
                Comments(line);
            }
            if (!line.contains(";")){
                System.out.println("Invalid instruction detected");
                break;
            }
            if (line.contains("clear")){
                Clear(line, Variables);
            }
            else if (line.contains("+") || line.contains("-") || line.contains("*") || line.contains("/")){
                Operator(line, Variables);
            }
            else if (line.contains("incr")){
                Incr(line, Variables);
            }
            else if (line.contains("decr")){
                Decr(line, Variables);
            }
            else if (line.contains("end")) {
                int index = line.indexOf("end");
                // Go backwards to find the nearest while that has the same indentation with the end
                for (int i = a; i > 0; i--){
                    if (instructions.get(i).contains("while") && instructions.get(i).indexOf("while") == index){
                        String line1 = instructions.get(i);
                        Scanner scanner = new Scanner(line1);
                        scanner.next();
                        String operand = scanner.next();
                        for (Variable variable : Variables){
                            // Set the pointer back to the while position if the variable isn't 0
                            if (variable.id.contains(operand) && variable.num != 0){
                                a = i;
                            }
                        }
                        break;
                    }
                }
            }
            Report(Variables);
        }
    }


    public static ArrayList<String> getInstruction(ArrayList<String> instructions) throws Exception{
        File file = new File("...\\BareBones.txt");
        FileReader freader = new FileReader(file);
        BufferedReader br = new BufferedReader(freader);
        String line = br.readLine();
        while(line != null){
            instructions.add(line);
            line = br.readLine();
        }
        return instructions;
    }

    public static void Clear(String line, ArrayList<Variable> Variables){
        Scanner scanner = new Scanner(line);
        scanner.next();
        String vid = scanner.next();
        Variable variable = new Variable(vid.replace(";", ""));
        variable.Setnumber();
        boolean flag = true;
        for (Variable variable1 : Variables){
            if (variable1.id.contains(vid.replace(";", ""))){
                flag = false;
            }
        }
        if (flag) {
            Variables.add(variable);
        }

    }
    public static void Incr(String line, ArrayList<Variable> Variables){
        Scanner scanner = new Scanner(line);
        scanner.next();
        String vid = scanner.next().replace(";", "");
        for (Variable variable : Variables){
            if (variable.id.contains(vid)){
                variable.Incr();
            }
        }
    }
    public static void Decr(String line, ArrayList<Variable> Variables){
        Scanner scanner = new Scanner(line);
        scanner.next();
        String vid = scanner.next().replace(";", "");
        for (Variable variable : Variables){
            if (variable.id.contains(vid)){
                variable.Decr();
            }
        }
    }

    public static void Comments(String line){
        int index = line.indexOf("//");
        String rest_line = line.substring(index+2);
        System.out.println("Comment: " + rest_line.trim());
    }

    public static void Operator(String line, ArrayList<Variable> Variables){
        Scanner scanner = new Scanner(line.trim().replace(";", ""));
        String variable1 = scanner.next();
        scanner.next();
        String variable2 = scanner.next();
        String operator = scanner.next();
        String variable3 = scanner.next();
        Variable v1 = new Variable("");
        int v2 = 0;
        int v3 = 0;
        // Go through the Variablie list to give values
        for (Variable variable : Variables){
            if ( variable.id.contains(variable1)){
                v1 = variable;
            }
            if (variable.id.contains(variable2)){
                v2 = variable.Getnumber();
            }
            if ( variable.id.contains(variable3)){
                v3 = variable.Getnumber();
            }
        }
        // Change value in Variable 1
        if (operator.contains("+")){
            v1.Plus(v2, v3);
            for (Variable variable : Variables) {
                if (variable.id.contains(variable1)) {
                    variable.num = v1.num;
                }
            }
        }
        else if (operator.contains("-")){
            v1.Minus(v2, v3);
            for (Variable variable : Variables) {
                if (variable.id.contains(variable1)) {
                    variable.num = v1.num;
                }
            }
        }
        else if (operator.contains("*")){
            v1.Multiply(v2, v3);
            for (Variable variable : Variables) {
                if (variable.id.contains(variable1)) {
                    variable.num = v1.num;
                }
            }
        }
        // Only does integer division
        else if (operator.contains("/")){
            v1.Divide(v2, v3);
            for (Variable variable : Variables) {
                if (variable.id.contains(variable1)) {
                    variable.num = v1.num;
                }
            }
        }



    }


    // Go thorugh the variable list to print out value for each
    public static void Report(ArrayList<Variable> Variables){
        System.out.println("*********************");
        for (Variable variable : Variables){
            System.out.println(variable.id + " = " + variable.num);
        }
    }
}
