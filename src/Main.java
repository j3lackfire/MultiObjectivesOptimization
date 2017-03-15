import BinaryFactory.BinaryFactory;
import BinaryFactory.BinaryVariable;
import EquationCreator.Equation;
import EquationCreator.WorkingNSGA;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        System.out.println("\n\n\n-------------\n\n\n");

        WorkingNSGA workingNSGA = new WorkingNSGA();



    }

    public static void TestCrossover() {
        BinaryFactory bf = new BinaryFactory(10, -10, 10);
        BinaryVariable[] b_1 = new BinaryVariable[3];
        BinaryVariable[] b_2 = new BinaryVariable[3];

        String s1 = "";
        String s2 = "";
        for (int i = 0; i < 3; i ++) {
            b_1[i] = bf.generateNewBinary();
            b_2[i] = bf.generateNewBinary();
            s1 += b_1[i].toString() + " , ";
            s2 += b_2[i].toString() + " , ";
        }
        System.out.println(s1);
        System.out.println(s2);
        System.out.println("\n\n\n-------------\n\n\n");
        BinaryVariable[] c = BinaryFactory.doTwoPointCrossover(b_1, b_2);

        BinaryVariable[] c_1 = new BinaryVariable[3];
        BinaryVariable[] c_2 = new BinaryVariable[3];

        s1 = "";
        s2 = "";
        for (int i = 0; i < 3; i ++) {
            c_1[i] = c[i];
            c_2[i] = c[i + 3];
            s1 += c_1[i].toString() + " , ";
            s2 += c_2[i].toString() + " , ";
        }
        System.out.println(s1);
        System.out.println(s2);
    }
}
