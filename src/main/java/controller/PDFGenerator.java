package controller;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import entities.Centre;

import java.io.IOException;

public class PDFGenerator {

    public void generateCentrePDF(Centre centre, String filePath) {
        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage();
            document.addPage(page);

            try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
                contentStream.beginText();
                contentStream.setFont(PDType1Font.HELVETICA, 12);
                contentStream.newLineAtOffset(100, 700);
                contentStream.showText("Informations sur le centre :");
                contentStream.newLineAtOffset(0, -20);
                contentStream.showText("ID : " + centre.getId_centre());
                contentStream.newLineAtOffset(0, -20);
                contentStream.showText("Nom : " + centre.getNom_centre());
                contentStream.newLineAtOffset(0, -20);
                contentStream.showText("Ville : " + centre.getVille());
                contentStream.newLineAtOffset(0, -20);
                contentStream.showText("Mail : " + centre.getMail());
                contentStream.newLineAtOffset(0, -20);
                contentStream.showText("Numéro de téléphone : " + centre.getNum_tel());
                contentStream.newLineAtOffset(0, -20);
                contentStream.showText("Nombre d'activités : " + centre.getNbre_activite());
                contentStream.endText();
            }

            document.save(filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
