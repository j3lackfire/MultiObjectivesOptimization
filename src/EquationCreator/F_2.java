package EquationCreator;

import BinaryFactory.BinaryVariable;

/**
 * Created by Le Pham Minh Duc on 3/10/2017.
 */
public class F_2 extends BaseObjectives {
    //cached manager
    Equation equation;


    public F_2 (Equation _equation, BinaryVariable[] _solutions) {
        super(_equation, _solutions);

        double f1 = _solutions[0].getValue();
        double g = G_X.calculateG(_solutions);
        myFitness = g * (1 - Math.sqrt(f1 / g));
    }
}
