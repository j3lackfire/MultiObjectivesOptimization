package EquationCreator;

import BinaryFactory.BinaryFactory;
import BinaryFactory.BinaryVariable;

import java.util.Random;

/**
 * Created by Le Pham Minh Duc on 3/10/2017.
 */
public class VariableFactory {
    public BinaryFactory binaryFactory;
    int numberOfVariables;

    public VariableFactory(int _numberOfVariables) {
        binaryFactory = new BinaryFactory(8,0,1);
        numberOfVariables = _numberOfVariables;
    }

    public BinaryVariable[] generateNewVariables() {
        BinaryVariable[] returnVal = new BinaryVariable[numberOfVariables];
        for (int i = 0; i < numberOfVariables; i ++) {
            returnVal[i] = binaryFactory.generateNewBinary();
        }
        return returnVal;
    }

}
