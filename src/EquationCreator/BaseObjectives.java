package EquationCreator;

import BinaryFactory.BinaryVariable;

/**
 * Created by Le Pham Minh Duc on 3/10/2017.
 */
public class BaseObjectives {
    //cached manager
    Equation equation;

    public double myFitness;

    public BaseObjectives(Equation _equation, BinaryVariable[] _solutions) {
        equation = _equation;
    }

    public double getFitness() {
        return myFitness;
    }
}
