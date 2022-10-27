package week3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class C2 {
    public static void main(String[] args) throws Exception{
        // Create a String[] to hold all the lines from the text file
        ArrayList<String> instructions = new ArrayList<String>();
        getInstruction(instructions);

        int ins_len = getInsLen(instructions);
        String[] ins_list = getInsList(ins_len);

        // Create an int[] to specify the position of the line which contains "while"
        int while_number = getWhilenum(ins_len, ins_list);
        int[] while_list = new int[while_number];


        // OOP

        ArrayList<Variable> Variables= new ArrayList<Variable>();

        Variable x = new Variable("X");
        Variable y = new Variable("Y");
        Variable z = new Variable("Z");
        Variable w = new Variable("W");

        for (int a = 0; a < instructions.size(); a++){
            System.out.println(instructions.get(a));

            if (instructions.get(a).contains("clear")){
                String line = instructions.get(a);
                Scanner scanner = new Scanner(line);
                scanner.next();
                String vid = scanner.next();
                System.out.println(vid);
                Variable variable = new Variable(vid);
                Variables.add(variable);
            }

        }

        System.out.println(Variables);

        /*
        for(int a = 0; a < ins_len; a++){ // Carry out instructions one by one
            // If a line contains "while", note the position which is the starting point of th loop
            if (ins_list[a].contains("while")){
                if(ins_list[a].contains("X")){
                    while_list[0] = a;
                }
                else if (ins_list[a].contains("Y")){
                    while_list[1] = a;
                }
                else if (ins_list[a].contains("W")){
                    while_list[2] = a;
                }
            }
            // If a line contains "end", then need to separate if it's indented or not
            else if (ins_list[a].contains("end") && !ins_list[a].contains("  end") && x.Getnumber() != 0){
                a = while_list[0];
            }
            else if (ins_list[a].contains("   end")){
                // If indented then read the instructions backwards to find the closet while command
                for (int c = a; c > 0; c--){
                    if (ins_list[c].contains("while")){
                        if (ins_list[c].contains("Y") && y.Getnumber() != 0){
                            a = while_list[1];
                        }
                        else if (ins_list[c]. contains("W") && w.Getnumber() != 0){
                            a = while_list[2];
                        }
                        break;
                    }
                }
            }
            // Carry out the line if no special word is contained in the line
            else {
                Execution(ins_list[a], x, y, z, w);
            }
        }

         */

    }


    public static ArrayList<String> getInstruction(ArrayList<String> instructions) throws Exception{
        File file = new File("/Users/dingsongyang/IdeaProjects/SpaceCadets/src/week3/BareBones.txt");
        FileReader freader = new FileReader(file);
        BufferedReader br = new BufferedReader(freader);
        while(br.readLine() != null){
            System.out.println(br.readLine());
            instructions.add(br.readLine());
        }
        return instructions;
    }
    public static int getInsLen(ArrayList<String> instructions) throws Exception{
        File file = new File("/Users/dingsongyang/IdeaProjects/SpaceCadets/src/week3/BareBones.txt");
        FileReader freader = new FileReader(file);
        BufferedReader br = new BufferedReader(freader);
        int num_ins = 0;
        while(br.readLine() != null){
            num_ins++;
        }
        return num_ins;
    }
    public static String[] getInsList(int num_ins) throws Exception{
        File file = new File("/Users/dingsongyang/IdeaProjects/SpaceCadets/src/week3/BareBones.txt");
        FileReader freader = new FileReader(file);
        BufferedReader br = new BufferedReader(freader);

        String[] instruction = new String[num_ins];
        for (int i = 0; i < num_ins; i++){

            instruction[i] = br.readLine();
        }
        return instruction;
    }
    public static int getWhilenum(int ins_len, String[] ins_list){
        int count = 0;
        for (int i = 0; i < ins_len; i++){
            if (ins_list[i].contains("while")){
                count++;
            }
        }
        return count;
    }
    public static void Execution(String line, Variable x, Variable y, Variable z, Variable w){
        Scanner scanner = new Scanner(line);
        String opcode = scanner.next();
        String operand = scanner.next();

        if(opcode.contains("clear")){
            Clear(operand, x, y, z, w);
        }
        else if(opcode.contains(("incr"))){
            Incr(operand, x, y, z, w);
        }
        else if(opcode.contains(("decr"))){
            Decr(operand, x, y, z, w);
        }

        Report(x, y, z, w);

    }
    public static void Clear(String operand, Variable x, Variable y, Variable z, Variable w){
        if (operand.contains("X")) {
            x.Setnumber();
        }
        else if (operand.contains("Y")){
            y.Setnumber();
        }
        else if (operand.contains("Z")){
            z.Setnumber();
        }
        else if (operand.contains("W")){
            w.Setnumber();
        }
    }
    public static void Incr(String operand, Variable x, Variable y, Variable z, Variable w){
        if (operand.contains("X")) {
            x.Incr();
        }
        else if (operand.contains("Y")){
            y.Incr();
        }
        else if (operand.contains("Z")){
            z.Incr();
        }
        else if (operand.contains("W")) {
            w.Incr();
        }
    }
    public static void Decr(String operand, Variable x, Variable y, Variable z, Variable w){
        if (operand.contains("X")) {
            x.Decr();
        }
        else if (operand.contains("Y")){
            y.Decr();
        }
        else if (operand.contains("Z")){
            z.Decr();
        }
        else if (operand.contains("W")) {
            w.Decr();
        }
    }

    public static void Report(Variable x, Variable y, Variable z, Variable w){
        System.out.println("*********************");
        System.out.println("Variable X = " + x.Getnumber());
        System.out.println("Variable Y = " + y.Getnumber());
        System.out.println("Variable Z = " + z.Getnumber());
        System.out.println("Variable W = " + w.Getnumber());
    }
}
