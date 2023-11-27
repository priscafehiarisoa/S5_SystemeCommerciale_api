package s5_systemecommerciale_api.service.files;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.stereotype.Service;
import s5_systemecommerciale_api.model.entreprise.EntrepriseInformation;
import s5_systemecommerciale_api.model.fournisseur.Fournisseur;
import s5_systemecommerciale_api.model.produit.Produit;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class PdfService {
    String text = "Bonjour , vous avez ci dessous la liste des produits pour le proforma ";
    public String saveObjectsAsPdf(List<Produit> objectList,Fournisseur fournisseur, EntrepriseInformation entrepriseInformation, String filePath) throws IOException {
        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage();
            document.addPage(page);

            try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
                // Add Fournisseur information
                contentStream.setFont(PDType1Font.HELVETICA, 8);
                float headerYPosition = page.getMediaBox().getHeight() - 50;
                contentStream.beginText();
                contentStream.newLineAtOffset(50, headerYPosition);
                contentStream.setFont(PDType1Font.HELVETICA_BOLD, 9);
                contentStream.showText("Entreprise: ");
                contentStream.setFont(PDType1Font.HELVETICA, 8);
                contentStream.showText(entrepriseInformation.getNomEntreprise());

                contentStream.newLineAtOffset(0, -20);
                contentStream.setFont(PDType1Font.HELVETICA_BOLD, 8);
                contentStream.showText("Adresse: ");
                contentStream.setFont(PDType1Font.HELVETICA, 8);
                contentStream.showText( entrepriseInformation.getAdresse());

                contentStream.newLineAtOffset(0, -20);
                contentStream.setFont(PDType1Font.HELVETICA_BOLD, 8);
                contentStream.showText("Telephone: ");
                contentStream.setFont(PDType1Font.HELVETICA, 8);
                contentStream.showText( entrepriseInformation.getPhone());

                contentStream.newLineAtOffset(0, -20);
                contentStream.setFont(PDType1Font.HELVETICA_BOLD, 8);
                contentStream.showText("email: ");
                contentStream.setFont(PDType1Font.HELVETICA, 8);
                contentStream.showText( entrepriseInformation.getEmail());

                contentStream.newLineAtOffset(0, -20);
                contentStream.setFont(PDType1Font.HELVETICA_BOLD, 8);
                contentStream.showText("id du fournisseur: ");
                contentStream.setFont(PDType1Font.HELVETICA, 8);
                contentStream.showText(String.valueOf(fournisseur.getId()));

                contentStream.newLineAtOffset(0, -40);
                contentStream.setFont(PDType1Font.HELVETICA, 8);
                contentStream.showText( text);

                contentStream.endText();
                // Calculate the position for the table (30% from the top)
                float tableYPosition = headerYPosition * 0.8f;

                // Convert the list of objects to tabular PDF content
                createTable(page, contentStream, objectList, tableYPosition);
            }

            // Save the PDF to the specified file path
            document.save(filePath);
        }

        // Return the resource (file) reference
        return filePath;
    }

    private void createTable(PDPage page, PDPageContentStream contentStream, List<Produit> objectList, float tableYPosition) throws IOException {
        float margin = 50;
        float tableWidth = page.getMediaBox().getWidth() - 2 * margin;
        float yPosition = tableYPosition;
        float rowHeight = 20;

        // Draw table header
        drawTableHeader(contentStream, margin, yPosition, tableWidth, rowHeight);

        // Draw table content with borders
        drawTableContent(contentStream, margin, yPosition - rowHeight, tableWidth, rowHeight, objectList);
    }

    private void drawTableHeader(PDPageContentStream contentStream, float margin, float yPosition, float tableWidth, float rowHeight) throws IOException {
        contentStream.setFont(PDType1Font.HELVETICA_OBLIQUE, 10);

        // Draw borders for the header
        contentStream.setLineWidth(0.3f);
        contentStream.moveTo(margin, yPosition);
        contentStream.lineTo(margin + tableWidth, yPosition);
        contentStream.stroke();

        contentStream.setLineWidth(0.3f);

        // Draw the table header content
        contentStream.beginText();
        contentStream.newLineAtOffset(margin + 5, yPosition - 15);
        contentStream.showText("ID");
        contentStream.newLineAtOffset(100, 0);
        contentStream.showText("Nom du produit");
        contentStream.endText();
    }

    private void drawTableContent(PDPageContentStream contentStream, float margin, float yPosition, float tableWidth, float rowHeight, List<Produit> objectList) throws IOException {
        contentStream.setFont(PDType1Font.HELVETICA, 9);

        for (Produit obj : objectList) {
            // Draw borders for each row
            contentStream.setLineWidth(0.1f);
            contentStream.moveTo(margin, yPosition);
            contentStream.lineTo(margin + tableWidth, yPosition);
            contentStream.stroke();

            // Draw the table content
            contentStream.beginText();
            contentStream.newLineAtOffset(margin + 5, yPosition - 15);
            contentStream.showText(obj.getId().toString());
            contentStream.newLineAtOffset(100, 0);
            contentStream.showText(obj.getNomProduit());
            contentStream.endText();

            yPosition -= rowHeight;
        }
    }


    public static void main(String[] args) throws IOException {
        String filePath = "./produit.pdf"; // Replace with the actual path
        List<Produit> produits=new ArrayList<>();
        produits.add(new Produit(1L,"biscuit", 0));
        produits.add(new Produit(2L,"fromage", 0));
        produits.add(new Produit(3L,"THB", 0));
        PdfService pdfService=new PdfService();
        Fournisseur fournisseur= new Fournisseur(1L,"prisca", "lot III E 132 CT Fort Voyron","tel","prisca",200,"mytyrealy@gmail.com");
        EntrepriseInformation entrepriseInformation= new EntrepriseInformation(1L,"Myty","mytyrealy@gmail.com","0347067949","Andoharanofotsy");
        String pdfResource = pdfService.saveObjectsAsPdf(produits,fournisseur,entrepriseInformation, filePath);
        System.out.println("=> "+pdfResource);

    }
}
