package s5_systemecommerciale_api.model.vals;


import org.apache.pdfbox.pdmodel.PDDocument;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Comparator;

public class ArticleDTO implements Serializable {
private String id;
private int quantite;


@Override
public String toString() {
return "ArticleDTO{" +
"id=" + id +
", quantite=" + quantite +
'}';
}

public ArticleDTO() {
}

public String getId() {
return id;
}

public void setId(String id) {
this.id = id;
}

public int getQuantite() {
return quantite;
}

public void setQuantite(int quantite) {
this.quantite = quantite;
}

//public static void main(String[] args) {
//String directoryPath = "/Users/valisoa/Pictures/signature"; // Replace with the path to your directory
//String outputPath="/Users/valisoa/Pictures/signature/output.pdf";
//File lastDrawingFile = getLastDrawingFile(directoryPath);
//
//if (lastDrawingFile != null) {
//try {
//createPdfWithImage(lastDrawingFile,outputPath);
//System.out.println("PDF created successfully.");
//} catch (IOException e) {
//e.printStackTrace();
//}
//} else {
//System.out.println("No matching files found.");
//}
//}

//public static void createPdfWithImage(File imageFile, String outputPath) throws IOException {
//try (PDDocument document = new PDDocument()) {
//
//PDPage page = new PDPage();
//document.addPage(page);
//
//PDImageXObject pdImage = PDImageXObject.createFromFileByContent(imageFile, document);
//float scale = 0.5f; // Adjust the scale as needed
//float x = page.getMediaBox().getWidth() - pdImage.getWidth() * scale;
//float y = 0;
//
//try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
//contentStream.drawImage(pdImage, x, y, pdImage.getWidth() * scale, pdImage.getHeight() * scale);
//}
//
//document.save(outputPath); // Replace with the desired output path
//}
//}

public static File getLastDrawingFile(String directoryPath) {
File directory = new File(directoryPath);

if (directory.isDirectory()) {
File[] matchingFiles = directory.listFiles((dir, name) -> name.contains("drawing") && name.endsWith(".png"));

if (matchingFiles != null && matchingFiles.length > 0) {
return Arrays.stream(matchingFiles)
.max(Comparator.comparingLong(File::lastModified))
.orElse(null);
} else {
System.out.println("No files found in the directory matching the pattern.");
}
} else {
System.out.println("The specified path is not a directory.");
}

return null;
}

private static FilenameFilter getFileNameFilter(String pattern) {
return (dir, name) -> name.matches(".*" + pattern + ".*\\.png");
}
}
