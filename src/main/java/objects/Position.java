package objects;

/**
 * Created by Rukshan
 */
public class Position {
    private String unicode;
    private float xCoordinate;
    private float yCoordinate;
    private String word;
    private int isUsed;
    private float test;
    private String fullWord;
    private int isFullWord;
    private float endValue;

    public Position(String unicode, float xCoordinate, float yCoordinate) {
        this.unicode = unicode;
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        isUsed = 0;
        isFullWord = 0;
    }

    public String getUnicode() {
        return unicode;
    }

    public float getxCoordinate() {
        return xCoordinate;
    }

    public float getyCoordinate() {
        return yCoordinate;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public int getIsUsed() {
        return isUsed;
    }

    public void setIsUsed() {
        this.isUsed = 1;
    }

    public float getTest() {
        return test;
    }

    public void setTest(float test) {
        this.test = test;
    }

    public String getFullWord() {
        return fullWord;
    }

    public void setFullWord(String fullWord) {
        this.fullWord = fullWord;
    }

    public int getIsFullWord() {
        return isFullWord;
    }

    public float getEndValue() {
        return endValue;
    }

    public void setEndValue(float endValue) {
        this.endValue = endValue;
    }

    public void setIsFullWord() {
        this.isFullWord = 1;
    }
}
