package controllers;

import objects.Node;

/**
 * Created by Rukshan
 */
class NodeObjectMaker {
    private Node node = new Node();

    NodeObjectMaker() {
    }

    Node objCreate(String[] values, float key) {

        node.setId(key);
        node.setXCoordinate(Float.parseFloat(values[1]));
        node.setYCoordinate(Float.parseFloat(values[2]));
        node.setValue(values[0]);
        ProbabilityCalculator obj = new ProbabilityCalculator();
        node.setProbability(obj.probabilitySetter(values[0]));
        node.setTotalPro(node.getProbability().getNumberProbability() + node.getProbability().getSpaceProbability()
                + node.getProbability().getSymbolProbability());

        return node;
    }

    Node objMakeTwo(String[] values, float key, String[] val) {

        node.setId(key);
        node.setXCoordinate(Float.parseFloat(values[1]));
        node.setYCoordinate(Float.parseFloat(values[2]));
        node.setValue(values[0]);
        ProbabilityCalculator obj = new ProbabilityCalculator();
        node.setProbability(obj.probabilitySetterTwo(values[0], val));
        node.setTotalPro(node.getProbability().getNumberProbability() + node.getProbability().getSpaceProbability()
                + node.getProbability().getSymbolProbability());

        return node;
    }

    int spaceProbabilityMaker(String[] val) {

        ProbabilityCalculator obj = new ProbabilityCalculator();
        return obj.spaceCheck(val);
    }
}
