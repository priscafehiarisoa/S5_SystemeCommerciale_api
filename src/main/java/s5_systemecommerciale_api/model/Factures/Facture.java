package s5_systemecommerciale_api.model.Factures;

import s5_systemecommerciale_api.model.entreprise.EntrepriseInformation;

import java.sql.Timestamp;

public class Facture {
    EntrepriseInformation entrepriseInformation;
    Timestamp dateFacturation ;
    double totalHT ;
    double totalTTC;
    double tva;

}
