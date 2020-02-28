package controllers;

import objects.Position;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.TextPosition;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ReadPdf extends PDFTextStripper {

    static float pageWidth;
    private static List<Position> positionList = new ArrayList<>();

    public ReadPdf() throws IOException {
    }

    static List<Position> getPositionList() {
        return positionList;
    }

    public void ReadData() throws IOException {

        String basePath = new File("").getAbsolutePath();
        File file = new File(basePath + "/TestAA.pdf");
        PDDocument document = PDDocument.load(file);
        PDFTextStripper stripper = new ReadPdf();
        stripper.setSortByPosition(true);
        stripper.setStartPage(0);
        stripper.setEndPage(document.getNumberOfPages());
        Writer dummy = new OutputStreamWriter(new ByteArrayOutputStream());
        stripper.writeText(document, dummy);
    }

    /**
     * Override the default functionality of PDFTextStripper.
     */
    @Override
    protected void writeString(String string, List<TextPosition> textPositions) throws IOException {

        for (TextPosition text1 : textPositions) {
            getPositionList().add(new Position(text1.getUnicode(), text1.getXDirAdj(), text1.getYDirAdj()));
            pageWidth = text1.getPageWidth();
        }
    }
}