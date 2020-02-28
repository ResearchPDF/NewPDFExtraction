package controllers;

import objects.Node;

import java.util.Map;

class ValueChanger {
    private Map<Float, Node> hashMapWords = WordObjectMaker.hashMapWords;
    private Map<String, String> hashMapRelations = WordObjectMaker.hashMapRelations;

    int valChanger(float id) {
        Node node = hashMapWords.get(id);
        if (node.getRightId() != 0 & node.getBottomId() == 0) {
            if (node.getTopId() != 0) {
                hashMapWords.get(node.getTopId()).setTotalPro(hashMapWords.get(node.getTopId()).getTotalPro() + 1);
                if (hashMapWords.get(node.getRightId()).getTopId() != 0) {
                    node.setUsed();
                    node.setKeyWord();
                    hashMapWords.get(node.getRightId()).setUsed();
                    hashMapWords.get(node.getRightId()).setNotKeyWord();
                    hashMapRelations.put(node.getValue(), hashMapWords.get(node.getRightId()).getValue());
                    hashMapWords.get(hashMapWords.get(node.getRightId()).getTopId()).setTotalPro(hashMapWords.get
                            (hashMapWords.get(node.getRightId()).getTopId()).getTotalPro() - 1);
                    return 0;
                }
            } else {
                node.setUsed();
                node.setKeyWord();
                hashMapRelations.put(node.getValue(), "");
                hashMapWords.get(node.getRightId()).setTotalPro(hashMapWords.get(node.getRightId()).getTotalPro() + 1);
                return valChanger(node.getRightId());
            }
            return 0;
        } else if (node.getRightId() == 0 & node.getBottomId() != 0) {
            node.setUsed();
            node.setKeyWord();
            hashMapWords.get(node.getBottomId()).setUsed();
            hashMapWords.get(node.getBottomId()).setNotKeyWord();
            hashMapRelations.put(node.getValue(), hashMapWords.get(node.getBottomId()).getValue());
            return 0;
        }
        if ((!node.getUsed()) & node.getRightId() != 0 & node.getBottomId() != 0) {
            if (node.getTotalPro() < hashMapWords.get(node.getRightId()).getTotalPro()) {
                return 0;
            }
            if (hashMapWords.get(node.getRightId()).getTotalPro() < hashMapWords.get(node.getBottomId()).getTotalPro()) {
                //System.out.println("Keyword : " + node.val+ "\tValue :"+ node.totalPro);
                if (node.getTotalPro() > hashMapWords.get(node.getRightId()).getTotalPro()) {
                    node.setUsed();
                    node.setKeyWord();
                    hashMapWords.get(node.getRightId()).setUsed();
                    hashMapWords.get(node.getRightId()).setNotKeyWord();
                    hashMapRelations.put(node.getValue(), hashMapWords.get(node.getRightId()).getValue());
                    hashMapWords.get(node.getBottomId()).setTotalPro(hashMapWords.get(node.getBottomId()).getTotalPro() + 1);
                    if (hashMapWords.get(node.getRightId()).getBottomId() != 0) {
                        hashMapWords.get(hashMapWords.get(node.getRightId()).getBottomId()).setTotalPro(hashMapWords.get
                                (hashMapWords.get(node.getRightId()).getBottomId()).getTotalPro() - 1);
                    }
                    return valChanger(node.getBottomId());
                } else {
                    return 0;
                }
            } else if (hashMapWords.get(node.getRightId()).getTotalPro() == hashMapWords.get(node.getBottomId()).getTotalPro()) {
                return valChanger(node.getBottomId());
            } else {
                if (node.getTotalPro() > hashMapWords.get(node.getBottomId()).getTotalPro()) {
                    node.setUsed();
                    node.setKeyWord();
                    hashMapWords.get(node.getBottomId()).setUsed();
                    hashMapWords.get(node.getBottomId()).setNotKeyWord();
                    hashMapRelations.put(node.getValue(), hashMapWords.get(node.getBottomId()).getValue());
                    hashMapWords.get(node.getRightId()).setTotalPro(hashMapWords.get(node.getRightId()).getTotalPro() + 1);
                    if (hashMapWords.get(node.getBottomId()).getRightId() != 0) {
                        hashMapWords.get(hashMapWords.get(node.getBottomId()).getRightId()).setTotalPro(hashMapWords.get
                                (hashMapWords.get(node.getBottomId()).getRightId()).getTotalPro() - 1);
                    }
                    return valChanger(node.getRightId());
                } else {
                    return 0;
                }
            }
        }
        return 0;
    }
}
