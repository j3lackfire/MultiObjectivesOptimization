package EquationCreator;

import BinaryFactory.BinaryVariable;

/**
 * Created by Le Pham Minh Duc on 3/10/2017.
 */
public class G_X extends BaseObjectives {
    //cached manager
    Equation equation;


    public G_X(Equation _equation, BinaryVariable[] _solutions) {
        super(_equation, _solutions);
        myFitness = calculateG(_solutions);
    }

    public static double calculateG(BinaryVariable[] _solutions) {
        double sum = 0;
        for (int i = 1; i < _solutions.length; i ++) {
            sum += _solutions[i].getValue();
        }
        return (1 + 9/(_solutions.length - 1) * sum);
    }
}
