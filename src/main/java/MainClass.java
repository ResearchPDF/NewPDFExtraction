import controllers.ReadPdf;
import controllers.WordObjectMaker;

import java.io.IOException;

public class MainClass {
    public static void main(String[] args) {
        int dividerNanoSeconds = 1000000000;

        try {
            //Read the pdf file
            ReadPdf readpdf = new ReadPdf();
            readpdf.ReadData();
        } catch (IOException e) {
            e.printStackTrace();
        }
        long startTime = System.nanoTime();
        // Get the connections between sections
        WordObjectMaker wordObjectMaker = new WordObjectMaker();
        wordObjectMaker.getText();
        long endTime = System.nanoTime();
        double totalTime = endTime - startTime;
        System.out.println(totalTime / dividerNanoSeconds + " Sec");
    }

}
