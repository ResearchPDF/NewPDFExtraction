package controllers;

import objects.Node;
import objects.Position;
import objects.XYObjectNode;

import java.util.List;

class GetXYMembers {
    private static float pageWidth = ReadPdf.pageWidth;
    private static List<Position> positionList;

    static {
        positionList = ReadPdf.getPositionList();
    }
    XYObjectNode getXYValues(int wordIndex) {
        XYObjectNode xyObjectNode = new XYObjectNode();
        int wordLocatorX;
        float xCoordinate = positionList.get(wordIndex).getxCoordinate();
        float yCoordinate = positionList.get(wordIndex).getyCoordinate();
        float endValue = positionList.get(wordIndex).getEndValue();
        //System.out.print(" "+xCoordinate+" A "+endValue+" ");
        xyObjectNode.setMainObject(new Node());
        xyObjectNode.getMainObject().setValue(positionList.get(wordIndex).getFullWord());
        xyObjectNode.getMainObject().setXCoordinate(positionList.get(wordIndex).getxCoordinate());
        xyObjectNode.getMainObject().setYCoordinate(positionList.get(wordIndex).getyCoordinate());
        for (wordLocatorX = wordIndex + 1; wordLocatorX < positionList.size() - 1; wordLocatorX++) {
            if (positionList.get(wordLocatorX).getIsFullWord() == 1) {
                float xXCoordinate = positionList.get(wordLocatorX).getxCoordinate();
                float xYCoordinate = positionList.get(wordLocatorX).getyCoordinate();
                if (yCoordinate == xYCoordinate & (xXCoordinate - xCoordinate) < pageWidth / 2.5) {
                    xyObjectNode.setRightObject(new Node());
                    xyObjectNode.getRightObject().setValue(positionList.get(wordLocatorX).getFullWord());
                    xyObjectNode.getRightObject().setXCoordinate(positionList.get(wordLocatorX).getxCoordinate());
                    xyObjectNode.getRightObject().setYCoordinate(positionList.get(wordLocatorX).getyCoordinate());
                    break;
                } else {
                    break;
                }
            }
        }

        int wordLocatorY;
        for (wordLocatorY = wordIndex + 1; wordLocatorY < positionList.size() - 1; wordLocatorY++) {
            if (positionList.get(wordLocatorY).getIsFullWord() == 1) {
                float yYCoordinate = positionList.get(wordLocatorY).getyCoordinate();
                float yXCoordinate = positionList.get(wordLocatorY).getxCoordinate();
                if (xCoordinate <=yXCoordinate & yXCoordinate < endValue & (yYCoordinate - yCoordinate) < 40) {
                    xyObjectNode.setBottomObject(new Node());
                    xyObjectNode.getBottomObject().setValue(positionList.get(wordLocatorY).getFullWord());
                    xyObjectNode.getBottomObject().setXCoordinate(positionList.get(wordLocatorY).getxCoordinate());
                    xyObjectNode.getBottomObject().setYCoordinate(positionList.get(wordLocatorY).getyCoordinate());
                    break;
                }
            }

        }
        return xyObjectNode;
    }
}
