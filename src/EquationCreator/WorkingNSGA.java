package EquationCreator;

import BinaryFactory.BinaryFactory;
import BinaryFactory.BinaryVariable;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Le Pham Minh Duc on 3/10/2017.
 */
public class WorkingNSGA {
    int loopCount = 2000;
    double edgeNodeDistance = 9999;
    public int numberOfObjectives = 3;
    public int numberOfVariables = 3;
    public double tournamentSize = 0.3;
    public double mutationChance = 0.05;


    public int numberOfEquations = 200;
    public ArrayList<Equation> myEquations;
    public VariableFactory variableFactory;

    public WorkingNSGA() {
        variableFactory = new VariableFactory(numberOfVariables);
        myEquations = new ArrayList<>();
        for (int i = 0; i < numberOfEquations ; i ++) {
            myEquations.add(new Equation(i, variableFactory.generateNewVariables()));
        }

        //first, calculate the tier
        calculateEquationTier();
        //then calculate the crown distance for each of the objectives
        calculateCrownDistance();

        for (int a = 0; a < loopCount ; a ++) {
            //tournament selection
            ArrayList<Equation> tournamentResult = tournamentSelection();
            if (a == loopCount - 1) {
                myEquations = tournamentResult;
                break;
            }
            ArrayList<BinaryVariable[]> newSolutions = new ArrayList<>();
            //two point crossover
            for (int i = 0; i < numberOfEquations / 2; i ++) {
                BinaryVariable[] solution_1 = new BinaryVariable[numberOfVariables];
                BinaryVariable[] solution_2 = new BinaryVariable[numberOfVariables];
                BinaryVariable[] result = BinaryFactory.doTwoPointCrossover(tournamentResult.get(i).variables, tournamentResult.get(i + 1).variables);
                for (int j = 0; j < numberOfVariables; j ++) {
                    solution_1[j] = result[j];
                    solution_2[j] = result[j + numberOfVariables];
                }
                newSolutions.add(solution_1);
                newSolutions.add(solution_2);
            }

            //mutation
            for (int i = 0; i < numberOfEquations; i ++) {
                for (int j = 0; j < numberOfVariables; j ++) {
                    newSolutions.get(i)[j].doBitWiseMutation(mutationChance);
                }
            }

            //new set of solutions
            myEquations = new ArrayList<>();
            for (int i = 0; i < numberOfEquations ; i ++) {
                myEquations.add(new Equation(i, newSolutions.get(i)));
            }
            calculateEquationTier();
            calculateCrownDistance();
        }

        ArrayList<Equation> displayEquation = new ArrayList<>();
        ArrayList<Integer> indexList = new ArrayList<>();
        for (int i = 0; i < myEquations.size(); i ++) {
            if (!indexList.contains(myEquations.get(i).index)) {
                displayEquation.add(myEquations.get(i));
                indexList.add(myEquations.get(i).index);
            }
        }


        System.out.println("\n----TIER " + (1) + " ----\n");
        for (int i = 0; i < 10; i ++) {

            boolean shouldDisplay = false;
            for (int j = 0; j < displayEquation.size(); j ++) {
                if (displayEquation.get(j).tier == i + 1) {
                    System.out.println(displayEquation.get(j).toString());
                    shouldDisplay = true;
                }
            }
            if (shouldDisplay) {
                System.out.println("\n----TIER " + ( i + 2) + " ----\n");
            }
        }

    }

    private void calculateEquationTier() {
        //the full list
        ArrayList<Equation> fullList = new ArrayList<>();
        for (int i = 0; i < numberOfEquations; i ++) {
            fullList.add(myEquations.get(i));
        }
        //sort the list into tiers
        ArrayList<ArrayList<Equation>> listOfNonDominatedList = new ArrayList<ArrayList<Equation>>();
        while (fullList.size() != 0) {

            ArrayList<Equation> nonDominatedList = getNonDominatedEquations(fullList);
            listOfNonDominatedList.add(nonDominatedList);
            for (int i = 0; i < nonDominatedList.size(); i ++) {
                nonDominatedList.get(i).tier = listOfNonDominatedList.size();

                for (int j = 0; j < fullList.size(); j ++) {
                    if (fullList.get(j).index == nonDominatedList.get(i).index) {
                        fullList.remove(j);
                        continue;
                    }
                }
            }
        }

//        for (int i = 0; i < listOfNonDominatedList.size(); i ++) {
//            System.out.print("\n__TIERS " + (i + 1) + " ______\n");
//            for (int j = 0; j < listOfNonDominatedList.get(i).size(); j ++) {
//                System.out.println(listOfNonDominatedList.get(i).get(j).toString());
//            }
//        }
    }

    private ArrayList<ArrayList<Equation>> calculateCrownDistance() {
        ArrayList<ArrayList<Equation>> returnList = new ArrayList<>();
        //sort solution by each of the objectives
        for (int a = 0; a < numberOfObjectives; a ++) {
            //generate a new list
            ArrayList<Equation> myList = new ArrayList<>();
            for (int i = 0; i < numberOfEquations; i ++) {
                myList.add(myEquations.get(i));
            }
            //bubble sort
            for (int i = 0; i < numberOfEquations; i ++) {
                for (int j = 0; j < numberOfEquations; j ++) {
                    if (i == j) {
                        continue;
                    }
                    if (myList.get(i).baseObjectives[a].getFitness() > myList.get(j).baseObjectives[a].getFitness()) {
                        Equation tempEquation = myList.get(i);
                        myList.set(i, myList.get(j));
                        myList.set(j, tempEquation);
                    }
                }
            }
            //done bubble sorting

            //setting the number to infinity might cause some problem :/
//            myList.get(0).crownDistance[a] = edgeNodeDistance;
//            myList.get(numberOfEquations - 1).crownDistance[a] = edgeNodeDistance;

            double fMin = myList.get(0).baseObjectives[a].getFitness();
            double fMax = myList.get(numberOfEquations - 2).baseObjectives[a].getFitness();
            double biggestDistance = -1;
            for (int i = 1; i < numberOfEquations - 1; i ++) {
                double d = (myList.get(i + 1).baseObjectives[a].getFitness() - myList.get(i - 1).baseObjectives[a].getFitness())
                        / (fMax - fMin);
                myList.get(i).crownDistance[a] = d;
                if (d > biggestDistance) {
                     biggestDistance = d;
                }
            myList.get(0).crownDistance[a] = 2 * biggestDistance;
            myList.get(numberOfEquations - 1).crownDistance[a] = 2 * biggestDistance;

            }

            returnList.add(myList);
        }
//        for (int i = 0; i < returnList.size(); i ++) {
//            System.out.println("\n__OBJECTIVES " + (i + 1) + " ______\n");
//            for (int j = 0; j < numberOfEquations; j ++) {
//                System.out.println(returnList.get(i).get(j).toString() + " , d = " + returnList.get(i).get(j).crownDistance[i]);
//            }
//
//        }

        return returnList;
    }

    public ArrayList<Equation> tournamentSelection() {
        Random r = new Random();
        ArrayList<Equation> returnList = new ArrayList<>();
        for (int i = 0; i < numberOfEquations; i ++) {
            //create a mini tournament
            ArrayList<Equation> miniTournament = new ArrayList<>();
            ArrayList<Equation> myList = new ArrayList<>();
            for (int j = 0; j < numberOfEquations; j ++) {
                myList.add(myEquations.get(j));
            }
            for (int j = 0; j < tournamentSize * numberOfEquations; j ++) {
                int randomIndex = r.nextInt(myList.size());
                miniTournament.add(myList.get(randomIndex));
                myList.remove(randomIndex);
            }
            returnList.add(DoMiniTournament(miniTournament));
        }

//        for (int i = 0; i < numberOfEquations; i ++) {
//            System.out.println(returnList.get(i).toString());
//        }

        return returnList;
    }

    //Find 1 best solution in a set of 3-4-5 solutions
    public Equation DoMiniTournament(ArrayList<Equation> miniTournament) {
//        System.out.println("\nDo mini tournament\n");
//        for (int i = 0; i < miniTournament.size(); i ++) {
//            System.out.println(miniTournament.get(i).toString());
//        }
        Equation returnEquation = miniTournament.get(0);
        for (int i = 0; i < miniTournament.size(); i ++) {
            if (returnEquation == miniTournament.get(i)) {
                continue;
            }
            if (!returnEquation.isWinAgainst(miniTournament.get(i))) {
                returnEquation = miniTournament.get(i);
            }
        }
//        System.out.println("\nWinner\n");
//        System.out.println(returnEquation.toString());
        return returnEquation;
    }

    //find all the non-dominated equation
    public ArrayList<Equation> getNonDominatedEquations(ArrayList<Equation> _equations) {
        ArrayList<Equation> returnList = new ArrayList<Equation>();
        for (int i = 0; i < _equations.size(); i ++) {
            boolean isDominated = false;
            for (int j = 0; j < _equations.size(); j ++) {
                if (i == j) {
                    continue;
                }
                if (_equations.get(i).isDominatedBy(_equations.get(j))) {
                    isDominated = true;
                    break;
                }

            }
            if (!isDominated) {
                returnList.add(_equations.get(i));
            }
        }
        return returnList;
    }

}
