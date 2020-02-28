package objects;

/**
 * Created by Rukshan
 */
public class Probability {

    private int numberProbability;
    private int symbolProbability;
    private int spaceProbability;

    public Probability(int numberProbability, int symbolProbability, int spaceProbability) {
        this.numberProbability = numberProbability;
        this.symbolProbability = symbolProbability;
        this.spaceProbability = spaceProbability;
    }

    public int getNumberProbability() {
        return numberProbability;
    }

    public void setNumberProbability(int numberProbability) {
        this.numberProbability = numberProbability;
    }

    public int getSymbolProbability() {
        return symbolProbability;
    }

    public void setSymbolProbability(int symbolProbability) {
        this.symbolProbability = symbolProbability;
    }

    public int getSpaceProbability() {
        return spaceProbability;
    }

    public void setSpaceProbability(int spaceProbability) {
        this.spaceProbability = spaceProbability;
    }
}