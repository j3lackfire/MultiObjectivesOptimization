package EquationCreator;

import BinaryFactory.BinaryVariable;

/**
 * Created by Le Pham Minh Duc on 3/10/2017.
 */
public class Equation {
    public int numberOfObjectives = 3;
    //id for sorting in tier
    public int index;
    public int tier;
    public double[] crownDistance;
    public BinaryVariable[] variables;
    public BaseObjectives[] baseObjectives;
    public F_1 f1;
    public F_2 f2;
    public G_X gx;

    public Equation(int _index, BinaryVariable[] _val) {
        index = _index;
        variables = _val;
        baseObjectives = new BaseObjectives[numberOfObjectives];
        crownDistance = new double[numberOfObjectives];

        f1 = new F_1(this, variables);
        gx = new G_X(this, variables);
        f2 = new F_2(this, variables);

        baseObjectives[0] = f1;
        baseObjectives[1] = f2;
        baseObjectives[2] = gx;
    }

    public Equation clone() {
        return new Equation(index, variables);
    }

    public boolean isDominatedBy(Equation other) {
        if (other.f1.getFitness() == f1.getFitness()
                && other.f2.getFitness() == f2.getFitness()
                && other.gx.getFitness() == gx.getFitness()) {
            return false;
        }

        if (other.f1.getFitness() <= f1.getFitness()
            && other.f2.getFitness() <= f2.getFitness()
            && other.gx.getFitness() <= gx.getFitness()) {
            return true;
        } else {
            return false;
        }
    }

    public double getCrownDistances() {
        double sum = 0;
        for (int i = 0; i < crownDistance.length; i ++) {
            sum += crownDistance[i];
        }
        return sum;
    }

    public String toString() {
        String returnString = index + " - tier " + tier //+ " - D " + getCrownDistances()
                + " - f1 = " + f1.getFitness() + " , "
                + "f2 = " + f2.getFitness() + " , "
                + "gx = " + gx.getFitness();

        returnString += "||||";
        for (int i = 0; i < variables.length; i ++) {
            returnString += ", " + variables[i].getValue();
        }
        return returnString;
    }

    public boolean isWinAgainst(Equation other) {
        if (tier < other.tier) {
            return true;
        }
        if (tier > other.tier) {
            return false;
        }
        return getCrownDistances() > other.getCrownDistances();

    }

}
