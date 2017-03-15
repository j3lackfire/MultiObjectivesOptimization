package EquationCreator;

import BinaryFactory.BinaryVariable;

/**
 * Created by Le Pham Minh Duc on 3/10/2017.
 */
public class F_1 extends BaseObjectives {

    public F_1(Equation _equation, BinaryVariable[] _solutions) {
        super(_equation, _solutions);
        myFitness = _solutions[0].getValue();
    }

}
