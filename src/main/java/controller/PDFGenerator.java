package controller;

import entites.Vehicule;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

import java.awt.*;
import java.io.IOException;

public class PDFGenerator {

    public void generateVehiculePDF(Vehicule vehicule, String filePath, String logoPath) {
        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage();
            document.addPage(page);

            try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
                // Dessiner le logo
                PDImageXObject logo = PDImageXObject.createFromFile(logoPath, document);
                contentStream.drawImage(logo, 50, 700);

                // Dessiner les informations du véhicule
                contentStream.beginText();
                contentStream.setFont(PDType1Font.HELVETICA_BOLD, 20);
                contentStream.setLeading(20);
                contentStream.setNonStrokingColor(Color.BLUE);
                contentStream.newLineAtOffset(100, 700);
                contentStream.showText("Informations sur le véhicule :");
                contentStream.newLine();
                contentStream.setFont(PDType1Font.HELVETICA, 12);
                contentStream.setNonStrokingColor(Color.BLACK);

                // Affichage des informations du véhicule avec différentes couleurs
                contentStream.showText("ID : ");
                contentStream.setNonStrokingColor(Color.RED);
                contentStream.showText(String.valueOf(vehicule.getNum_v()));
                contentStream.setNonStrokingColor(Color.BLACK);
                contentStream.newLine();

                contentStream.showText("Type : ");
                contentStream.setNonStrokingColor(Color.GREEN);
                contentStream.showText(vehicule.getType());
                contentStream.setNonStrokingColor(Color.BLACK);
                contentStream.newLine();

                contentStream.showText("Prix : ");
                contentStream.setNonStrokingColor(Color.ORANGE);
                contentStream.showText(String.valueOf(vehicule.getPrixuni()));
                contentStream.setNonStrokingColor(Color.BLACK);
                contentStream.newLine();

                contentStream.showText("Capacité : ");
                contentStream.setNonStrokingColor(Color.BLUE);
                contentStream.showText(String.valueOf(vehicule.getCapacite()));
                contentStream.setNonStrokingColor(Color.BLACK);
                contentStream.newLine();

                contentStream.endText();

                contentStream.addRect(50, 300, 1200, 1120);
                contentStream.stroke();
            }

            document.save(filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}