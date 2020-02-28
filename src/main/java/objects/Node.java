package objects;

/**
 * Created by Rukshan
 */
public class Node {

    private float id;
    private float leftId;
    private float rightId;
    private float bottomId;
    private float topId;
    private String value;
    private float xCoordinate;
    private float yCoordinate;
    private Probability probability;
    private int totalPro;
    private Boolean isUsed;
    private Boolean isKeyWord;
    private Boolean isNotKeyWord;

    public Node() {
        id = 0;
        leftId = 0;
        bottomId = 0;
        rightId = 0;
        topId = 0;
        totalPro = 0;
        isUsed = false;
        isNotKeyWord = false;
        isKeyWord = false;
    }

    public float getId() {
        return id;
    }

    public void setId(float id) {
        this.id = id;
    }

    public float getLeftId() {
        return leftId;
    }

    public void setLeftId(float leftId) {
        this.leftId = leftId;
    }

    public float getRightId() {
        return rightId;
    }

    public void setRightId(float rightId) {
        this.rightId = rightId;
    }

    public float getBottomId() {
        return bottomId;
    }

    public void setBottomId(float bottomId) {
        this.bottomId = bottomId;
    }

    public float getTopId() {
        return topId;
    }

    public void setTopId(float topId) {
        this.topId = topId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public float getXCoordinate() {
        return xCoordinate;
    }

    public void setXCoordinate(float xCoordinate) {
        this.xCoordinate = xCoordinate;
    }

    public float getYCoordinate() {
        return yCoordinate;
    }

    public void setYCoordinate(float yCoordinate) {
        this.yCoordinate = yCoordinate;
    }

    public Probability getProbability() {
        return probability;
    }

    public void setProbability(Probability probability) {
        this.probability = probability;
    }

    public int getTotalPro() {
        return totalPro;
    }

    public void setTotalPro(int totalPro) {
        this.totalPro = totalPro;
    }

    public Boolean getUsed() {
        return isUsed;
    }

    public void setUsed() {
        isUsed = true;
    }

    public Boolean getKeyWord() {
        return isKeyWord;
    }

    public void setKeyWord() {
        isKeyWord = true;
    }

    public Boolean getNotKeyWord() {
        return isNotKeyWord;
    }

    public void setNotKeyWord() {
        isNotKeyWord = true;
    }
}
