package controllers;

import objects.Node;
import objects.Position;
import objects.XYObjectNode;

import java.util.*;

public class WordObjectMaker {

    private static float pageWidth = ReadPdf.pageWidth;
    private static List<Position> positionList;
    private GetXYMembers getXYMembers = new GetXYMembers();
    private ValueChanger valChanger = new ValueChanger();

    static {
        positionList = ReadPdf.getPositionList();
    }

    static Map<Float, Node> hashMapWords = new HashMap<>();
    static Map<String, String> hashMapRelations = new HashMap<>();
    static  float flag,width =0;

    public void getText() {

        List<String> arrayEscapeCharacters = new ArrayList<>(5);
        arrayEscapeCharacters.add(" ");
        arrayEscapeCharacters.add("\n");
        arrayEscapeCharacters.add("\t");
        arrayEscapeCharacters.add("x0B");
        arrayEscapeCharacters.add("\f");
        arrayEscapeCharacters.add("\r");

        //System.out.println(positionList.size() - 1);
        //Create words within the loop
        for (int a = 0; a < positionList.size(); a++) {
            Position position = positionList.get(a);
            if (position.getUnicode() == null) {
                break;
            }
            if(flag ==0 & !arrayEscapeCharacters.contains(positionList.get(a +1).getUnicode())){
                width=  positionList.get(a+1).getxCoordinate() -  positionList.get(a).getxCoordinate();
                flag = 1;
            }
            if (!arrayEscapeCharacters.contains(position.getUnicode())) {
                String word = position.getUnicode();
                int b = 1;
                while (!arrayEscapeCharacters.contains(positionList.get(a + b).getUnicode())) {
                    float f1 = positionList.get(a + b - 1).getyCoordinate();
                    float f2 = positionList.get(a + b).getyCoordinate();
                    float f3 = positionList.get(a + b - 1).getxCoordinate();
                    float f4 = positionList.get(a + b).getxCoordinate();
                    float coordinateDifference = f4 - f3;
                    if (f1 == f2) {
                        if (0 < coordinateDifference & coordinateDifference < 2*width) {
                            word = word + positionList.get(a + b).getUnicode();
                            if ((a + b) >= positionList.size() - 1) {
                                break;
                            }
                            b++;
                        } else {
                            b--;
                            break;
                        }
                    } else {
                        b--;
                        break;
                    }
                }
                positionList.get(a).setWord(word);
                positionList.get(a).setIsUsed();
                positionList.get(a).setTest(positionList.get(a + b).getxCoordinate());
                positionList.get(a).setEndValue(positionList.get(a + b).getxCoordinate());
                //System.out.println(word+" "+ positionList.get(a).getxCoordinate()+" A "+positionList.get(a + b).getxCoordinate()+" ");
                a = a + b;
            }
        }
        for (int a = 0; a < positionList.size(); a++) {
            if (positionList.get(a).getIsUsed() == 1) {
                String fullWord = positionList.get(a).getWord();
                int flag=1;int  b = 1;
                int c = a;
                float endValue = 0;
                while (b < 50) {
                    if ((a + b) >= positionList.size() - 1) {
                        break;
                    }
                    if (positionList.get(a + b).getIsUsed() == 1) {
                        float f1 = positionList.get(a + b).getxCoordinate();
                        float f2 = positionList.get(c).getTest();
                        if (1 < f1 - f2 & f1 - f2 < 5) {
                            fullWord = fullWord + " " + positionList.get(a + b).getWord();
                            c = a + b;
                            endValue = positionList.get(a + b).getEndValue();
                            flag =0;
                            //System.out.println(fullWord);
                        } else {
                            break;
                        }
                    }
                    b++;
                }

                positionList.get(a).setIsFullWord();
                positionList.get(a).setFullWord(fullWord);
                //System.out.println(fullWord +" "+ positionList.get(a).getxCoordinate()+" A "+positionList.get(a).getEndValue()+" ");
                a = a + b - 1;
                if (flag ==1){
                    positionList.get(a).setEndValue(positionList.get(a).getEndValue());
                    }
                else
                    positionList.get(a).setEndValue(endValue);
                }
        }
        for(Position position:positionList) {
            if (position.getIsFullWord() == 1) {
                XYObjectNode xyObjectNode = getXYMembers.getXYValues(positionList.indexOf(position));
                if (xyObjectNode.getRightObject()!=null & xyObjectNode.getBottomObject()!=null) {
                    float idKey = objIdMaker(xyObjectNode.getMainObject().getXCoordinate(), xyObjectNode.
                            getMainObject().getYCoordinate());
                    float idXValue = objIdMaker(xyObjectNode.getRightObject().getXCoordinate(), xyObjectNode.
                            getRightObject().getYCoordinate());
                    float idYValue = objIdMaker(xyObjectNode.getBottomObject().getXCoordinate(), xyObjectNode.
                            getBottomObject().getYCoordinate());
                    Node node;
                    String[] probabilityValues = {""+xyObjectNode.getMainObject().getXCoordinate(), ""+xyObjectNode.getMainObject().
                            getYCoordinate(), ""+xyObjectNode.getRightObject().getXCoordinate(), ""+xyObjectNode.getBottomObject().getYCoordinate()};
                    if (!hashMapWords.containsKey(idKey)) {
                        NodeObjectMaker obj = new NodeObjectMaker();
                        String[] objVal = {xyObjectNode.getMainObject().getValue(), ""+xyObjectNode.getMainObject().
                                getXCoordinate(), ""+xyObjectNode.getMainObject().getYCoordinate()};

                        node = obj.objMakeTwo(objVal, idKey, probabilityValues);
                        node.setRightId(idXValue);
                        node.setBottomId(idYValue);
                        hashMapWords.put(idKey, node);
                    } else {
                        NodeObjectMaker obj = new NodeObjectMaker();
                        node = hashMapWords.get(idKey);
                        node.getProbability().setSpaceProbability(obj.spaceProbabilityMaker(probabilityValues));
                        node.setTotalPro(node.getProbability().getSpaceProbability() + node.getTotalPro());
                        node.setRightId(idXValue);
                        node.setBottomId(idYValue);
                    }
                    if (!hashMapWords.containsKey(idXValue)) {
                        NodeObjectMaker obj = new NodeObjectMaker();
                        String[] objectValues = {xyObjectNode.getRightObject().getValue(),""+xyObjectNode.getRightObject()
                                .getYCoordinate() , ""+xyObjectNode.getRightObject().getXCoordinate()};
                        node = obj.objCreate(objectValues, idXValue);
                        node.setLeftId(idKey);
                        hashMapWords.put(idXValue, node);
                    } else {
                        node = hashMapWords.get(idXValue);
                        node.setLeftId(idKey);
                    }
                    if (!hashMapWords.containsKey(idYValue)) {
                        NodeObjectMaker obj = new NodeObjectMaker();
                        String[] objectValues = {xyObjectNode.getBottomObject().getValue(),""+xyObjectNode.getBottomObject()
                                .getYCoordinate() , ""+xyObjectNode.getBottomObject().getXCoordinate()};
                        node = obj.objCreate(objectValues, idYValue);
                        node.setTopId(idKey);
                        hashMapWords.put(idYValue, node);
                    } else {
                        node = hashMapWords.get(idYValue);
                        node.setTopId(idKey);
                    }

                } else {
                    if (xyObjectNode.getRightObject()!=null | xyObjectNode.getBottomObject()!=null) {
                        if (xyObjectNode.getRightObject()!=null) {
                            float idKey = objIdMaker(xyObjectNode.getMainObject().getXCoordinate(),xyObjectNode.
                                    getMainObject().getYCoordinate());
                            float idXValue = objIdMaker(xyObjectNode.getRightObject().getXCoordinate(),xyObjectNode.
                                    getRightObject().getYCoordinate());
                            if (!hashMapWords.containsKey(idKey)) {
                                NodeObjectMaker obj = new NodeObjectMaker();
                                String[] objectValues = {xyObjectNode.getMainObject().getValue(), ""+xyObjectNode.
                                        getMainObject().getXCoordinate(), ""+xyObjectNode.getMainObject().getYCoordinate()};
                                Node node = obj.objCreate(objectValues, idKey);
                                node.setRightId(idXValue);
                                hashMapWords.put(idKey, node);
                            } else {
                                Node node = hashMapWords.get(idKey);
                                node.setRightId(idXValue);
                            }
                            if (!hashMapWords.containsKey(idXValue)) {
                                NodeObjectMaker obj = new NodeObjectMaker();
                                String[] objectValues = {xyObjectNode.getRightObject().getValue(), ""+xyObjectNode.
                                        getRightObject().getYCoordinate(), ""+xyObjectNode.getRightObject().getXCoordinate()};
                                Node node = obj.objCreate(objectValues, idXValue);
                                node.setLeftId(idKey);
                                hashMapWords.put(idXValue, node);
                            } else {
                                Node node = hashMapWords.get(idXValue);
                                node.setLeftId(idKey);
                            }
                        } else if (xyObjectNode.getBottomObject()!=null) {
                            float idKey = objIdMaker(xyObjectNode.getMainObject().getXCoordinate(),xyObjectNode.
                                    getMainObject().getYCoordinate());
                            float idYValue = objIdMaker(xyObjectNode.getBottomObject().getXCoordinate(), xyObjectNode.
                                    getBottomObject().getYCoordinate());
                            if (!hashMapWords.containsKey(idKey)) {
                                NodeObjectMaker obj = new NodeObjectMaker();
                                String[] objectValues = {xyObjectNode.getMainObject().getValue(), ""+xyObjectNode.
                                        getMainObject().getXCoordinate(), ""+xyObjectNode.getMainObject().getYCoordinate()};
                                Node node = obj.objCreate(objectValues, idKey);
                                node.setBottomId(idYValue);
                                hashMapWords.put(idKey, node);
                            } else {
                                Node node = hashMapWords.get(idKey);
                                node.setBottomId(idYValue);
                            }
                            if (!hashMapWords.containsKey(idYValue)) {
                                NodeObjectMaker obj = new NodeObjectMaker();
                                String[] objectValues = {xyObjectNode.getBottomObject().getValue(), ""+xyObjectNode.
                                        getBottomObject().getYCoordinate(),""+xyObjectNode.getBottomObject().getXCoordinate()};
                                Node node = obj.objCreate(objectValues, idYValue);
                                node.setTopId(idKey);
                                hashMapWords.put(idYValue, node);
                            } else {
                                Node node = hashMapWords.get(idYValue);
                                node.setTopId(idKey);
                            }
                        }

                    }
                }
            }
        }
        for (Object key : hashMapWords.keySet()) {
            Node node = hashMapWords.get(key);
            if ((!node.getUsed()) & (node.getRightId() != 0 & node.getBottomId() != 0)) {
                valChanger.valChanger(node.getId());
            }
        }
        for (Object key : hashMapWords.keySet()) {
            Node node = hashMapWords.get(key);
            if ((!node.getUsed()) & (node.getRightId() != 0 & node.getBottomId() == 0) & node.getProbability().getNumberProbability() == 2) {
                node.setUsed();
                node.setKeyWord();
                hashMapWords.get(node.getRightId()).setUsed();
                hashMapWords.get(node.getRightId()).setNotKeyWord();
                hashMapRelations.put(node.getValue(), hashMapWords.get(node.getRightId()).getValue());
            } else if ((!node.getUsed()) & (node.getRightId() == 0 & node.getBottomId() != 0) & node.getProbability().getNumberProbability() == 2) {
                node.setUsed();
                node.setKeyWord();
                hashMapWords.get(node.getBottomId()).setUsed();
                hashMapWords.get(node.getBottomId()).setNotKeyWord();
                hashMapRelations.put(node.getValue(), hashMapWords.get(node.getBottomId()).getValue());
            }
        }
        //checking the conditions
        for (String key : hashMapRelations.keySet()) {
            System.out.println("Keyword : " + key + "\tValue :" + hashMapRelations.get(key));
        }
        for (Object key : hashMapWords.keySet()) {
            Node node = hashMapWords.get(key);
            if (!node.getUsed()) {
                if (node.getRightId() == 0 & node.getBottomId() != 0) {
                    if (node.getKeyWord()) {
                        hashMapWords.get(node.getBottomId()).setNotKeyWord();
                    }
                }
                if (node.getRightId() != 0 & node.getBottomId() == 0) {
                    if (node.getKeyWord()) {
                        hashMapWords.get(node.getRightId()).setNotKeyWord();
                    }
                }
            }
        }
        for (Object key : hashMapWords.keySet()) {
            Node node = hashMapWords.get(key);
            if (node != null) {
                if (node.getRightId() != 0 & node.getBottomId() != 0) {
                    ////System.out.println("Keyword : " + node.val + "\tValue :" +hashMapWords.get(node.getRightId()).val + "\t Next " +hashMapWords.get(node.getBottomId()).val);
                    if (hashMapWords.get(node.getRightId()).getNotKeyWord() & !node.getNotKeyWord()) {
                        node.setKeyWord();
                        node.setUsed();
                        //System.out.println("Keyword : " + node.val + "\tValue :" +hashMapWords.get(node.getRightId()).val);
                    }
                    if (hashMapWords.get(node.getBottomId()).getKeyWord()) {
                        hashMapWords.get(node.getRightId()).setNotKeyWord();
                        node.setUsed();
                        //System.out.println("Keyword : " + node.val + "\tValue :" +hashMapWords.get(node.getBottomId()).val);
                    }
                    if (hashMapWords.get(node.getRightId()).getKeyWord()) {
                        hashMapWords.get(node.getBottomId()).setNotKeyWord();
                        node.setUsed();
                        // System.out.println("Keyword : " + node.val + "\tValue :" +hashMapWords.get(node.getRightId()).val);
                    }
                }
                if (node.getRightId() == 0 & node.getBottomId() != 0) {
                    if (hashMapWords.get(node.getBottomId()).getNotKeyWord() & !node.getNotKeyWord()) {
                        node.setKeyWord();
                        node.setUsed();
                    }
                }

                if (node.getRightId() != 0 & node.getBottomId() == 0) {
                    if (hashMapWords.get(node.getRightId()).getNotKeyWord() & !node.getNotKeyWord()) {
                        node.setKeyWord();
                        node.setUsed();
                    }
                }
            }
        }

    }

    private float objIdMaker(float x, float y) {
        return x + (y / 1000);
    }
}
