package BinaryFactory;

import java.util.Random;

/**
 * Created by Le Pham Minh Duc on 2/11/2017.
 */
public class BinaryFactory {
    public int binaryLength;
    public double bottomBound;
    public double upperBound;

    public BinaryFactory(int _binaryLength, double _bottomBound, double _upperBound) {
        binaryLength = _binaryLength;
        bottomBound = _bottomBound;
        upperBound = _upperBound;
    }

    public BinaryVariable generateNewBinary() {
        return new BinaryVariable(binaryLength, bottomBound, upperBound);
    }

    public BinaryVariable stringToBinary(String _binaryString) {
        return new BinaryVariable(binaryLength, bottomBound, upperBound, _binaryString);
    }

    public static BinaryVariable[] doTwoPointCrossover(BinaryVariable[] b1, BinaryVariable[] b2) {
        String combinedBinary_1 = "";
        String combinedBinary_2 = "";
        int binaryLength = b1[0].binaryLength;
        double bottomBound = b1[0].bottomBound;
        double upperBound = b1[0].upperBound;
        int numberOfVariables = b1.length;
        for (int i = 0; i < b1.length; i ++) {
            combinedBinary_1 += b1[i].toString();
            combinedBinary_2 += b2[i].toString();
        }
        int combinedLength = numberOfVariables * binaryLength;
        Random r = new Random();
        int r1 = r.nextInt(combinedLength);
        int r2 = r.nextInt(combinedLength);
        while (r1 == r2) {
            r2 = r.nextInt(combinedLength);
        }
        //swap function
        if (r2 < r1) {
            int t = r2;
            r2 = r1;
            r1 = t;
        }
        char[] b_1 = combinedBinary_1.toCharArray();
        char[] b_2 = combinedBinary_2.toCharArray();
        for (int i = r1; i < r2; i ++) {
            if (b_1[i] != b_2[i]) {
                char temp = b_1[i];
                b_1[i] = b_2[i];
                b_2[i] = temp;
            }
        }
        BinaryVariable[] returnBinary = new BinaryVariable[numberOfVariables * 2];
        for (int i = 0; i < numberOfVariables; i ++) {
            String s_1 = "";
            String s_2 = "";
            for (int j = 0; j < binaryLength; j ++) {
                s_1 += b_1[i * binaryLength + j];
                s_2 += b_2[i * binaryLength + j];
            }
            returnBinary[i] = new BinaryVariable(binaryLength, bottomBound, upperBound, s_1);
            returnBinary[i + numberOfVariables] = new BinaryVariable(binaryLength, bottomBound, upperBound, s_2);
        }

        return returnBinary;
    }
}
