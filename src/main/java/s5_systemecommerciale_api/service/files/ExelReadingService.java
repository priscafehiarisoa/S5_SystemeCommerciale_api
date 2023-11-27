package s5_systemecommerciale_api.service.files;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import s5_systemecommerciale_api.model.fournisseur.Fournisseur;
import s5_systemecommerciale_api.model.fournisseur.Produit_Fournisseur;
import s5_systemecommerciale_api.model.produit.Produit;
import s5_systemecommerciale_api.repository.FournisseurRepository;
import s5_systemecommerciale_api.repository.ProduitRepository;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class ExelReadingService {

    private final ProduitRepository produitRepository;
    private final FournisseurRepository fournisseurRepository;

    @Autowired
    public ExelReadingService(ProduitRepository produitRepository, FournisseurRepository fournisseurRepository) {
        this.produitRepository = produitRepository;
        this.fournisseurRepository = fournisseurRepository;
    }

    private Object getCellValue(Cell cell) {
        switch(cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();

            case BOOLEAN:
                return cell.getBooleanCellValue();
            case NUMERIC:
                return cell.getNumericCellValue();
        }
        return null;
    }
    public List<Produit_Fournisseur> readBooksFromExcelFile(String excelFilePath,Long idFournisseur) throws Exception {
        List<Produit_Fournisseur> listPF = new ArrayList<>();
        FileInputStream inputStream = new FileInputStream(new File(excelFilePath));

        Workbook workbook = new XSSFWorkbook(inputStream);
        Sheet firstSheet = workbook.getSheetAt(0);
        Iterator<Row> iterator = firstSheet.iterator();

        while (iterator.hasNext()) {

            Row nextRow = iterator.next();
            if (nextRow.getRowNum() == 0) {
                continue; // Passe à la prochaine itération (ignore la première ligne)
            }
            Iterator<Cell> cellIterator = nextRow.cellIterator();
            Produit_Fournisseur fournisseurP = new Produit_Fournisseur();
            boolean add=true;
            while (cellIterator.hasNext()) {
                Cell nextCell = cellIterator.next();
                int columnIndex = nextCell.getColumnIndex();
                if(getCellValue(nextCell)==null){
                    break;
                }
                Optional<Fournisseur> fournisseur= fournisseurRepository.findById(idFournisseur);
                if(fournisseur.isEmpty()){
                    throw new Exception("le fournisseur n'existe pas ");
                }
                fournisseurP.setFournisseur(fournisseur.get());
                switch (columnIndex) {
                    case 0:
                        System.out.println(getCellValue(nextCell));
                        Long idProduit =  Math.round((Double)getCellValue(nextCell));
                        Optional<Produit> p = produitRepository.findById(idProduit);
                        if (p.isPresent()) {
                            fournisseurP.setProduit(p.get());
                        } else {
                            throw new Exception("produit " + idProduit + " introuvable");
                        }
                        System.out.println("--");

                        break;
                    case 2:
                        System.out.println(getCellValue(nextCell));
                        fournisseurP.setTaxe((Double) getCellValue(nextCell));
                        System.out.println("--");
                        break;

                    case 3:
                        System.out.println(getCellValue(nextCell));
                        fournisseurP.setDelaiDeLivraison((Double) getCellValue(nextCell));
                        System.out.println("--");
                        break;

                    case 4:
                        System.out.println(getCellValue(nextCell));
                        fournisseurP.setPrixHorsTaxe((Double) getCellValue(nextCell));
                        System.out.println("--");
                        break;

                    case 5:
                        System.out.println(getCellValue(nextCell));
                        fournisseurP.setQuantiteStock((Double) getCellValue(nextCell));
                        System.out.println("--");
                        break;

// in case where it would be usefull
//                    case 6:
//                        System.out.println(getCellValue(nextCell));

//                        System.out.println("--");
//                        break;
                }
                fournisseurP.setDate(Timestamp.from(Instant.now()));

            }
            listPF.add(fournisseurP);
        }

        workbook.close();
        inputStream.close();

        return listPF;
    }



    public static void main(String[] args) throws Exception {
        String excelFilePath = "/Users/priscafehiarisoadama/Desktop/test.xlsx";
        FileInputStream inputStream = new FileInputStream(new File(excelFilePath));

        Workbook workbook = new XSSFWorkbook(inputStream);
        Sheet firstSheet = workbook.getSheetAt(0);
        Iterator<Row> iterator = firstSheet.iterator();

        while (iterator.hasNext()) {
            Row nextRow = iterator.next();
            Iterator<Cell> cellIterator = nextRow.cellIterator();

            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();

                switch (cell.getCellType()) {
                    case STRING:
                        System.out.print(cell.getStringCellValue());
                        break;
                    case BOOLEAN:
                        System.out.print(cell.getBooleanCellValue());
                        break;
                    case NUMERIC:
                        System.out.print(cell.getNumericCellValue());
                        break;
                }
                System.out.print(" - ");
            }
            System.out.println();
        }

        workbook.close();
        inputStream.close();
    }
}
