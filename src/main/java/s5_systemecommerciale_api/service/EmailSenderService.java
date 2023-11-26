package s5_systemecommerciale_api.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import s5_systemecommerciale_api.model.entreprise.EntrepriseInformation;
import s5_systemecommerciale_api.model.fournisseur.Fournisseur;
import s5_systemecommerciale_api.model.mail.MailStructure;
import s5_systemecommerciale_api.model.produit.Produit;
import s5_systemecommerciale_api.repository.FournisseurRepository;
import s5_systemecommerciale_api.service.files.PdfService;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.util.List;

@Service
public class EmailSenderService {
    @Autowired
    public final JavaMailSender javaMailSender;
    @Autowired
    private EntreprseService entreprseService;
    @Autowired
    private PdfService pdfService;
    @Autowired
    private FournisseurRepository fournisseurRepository;
    @Autowired
    private FournisseurService fournisseurService;
    @Autowired
    private ProduitService produitService;

    public EmailSenderService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Value("$(Spring.mail.username)")
    private String fromMail;
    private String pdfPAth="./produit.pdf";

    public void sendMailWithAttachement(String toemail, MailStructure mailStructure, String attachement) throws MessagingException, UnsupportedEncodingException {
        MimeMessage mimeMessage= javaMailSender.createMimeMessage();
        MimeMessageHelper messageHelper= new MimeMessageHelper(mimeMessage,true);
        messageHelper.setFrom(mailStructure.getSender(),mailStructure.getUsername());
        messageHelper.setTo(toemail);
        messageHelper.setText(mailStructure.getBody());
        messageHelper.setSubject(mailStructure.getSubject());

//        messageHelper.setCc("jeddyranivo@gmail.com");
        if(attachement!=null){
            FileSystemResource fileSystemResource=new FileSystemResource(new File(attachement));
            messageHelper.addAttachment(fileSystemResource.getFilename(),fileSystemResource);
        }
        javaMailSender.send(mimeMessage);

     }

     public void sendMailToFournisseur(List<Fournisseur> fournisseurs, List<Produit> produits) throws Exception {
         EntrepriseInformation entrepriseInformation= entreprseService.getEntrepriseInformation();
         String pdfFile=pdfService.saveObjectsAsPdf(produits,entrepriseInformation,pdfPAth);
         MailStructure mailStructure=new MailStructure().getMailStructureForDemandeProforma(null,entrepriseInformation);
         for (int i = 0; i < fournisseurs.size(); i++) {
             sendMailWithAttachement(fournisseurs.get(i).getEmail(),mailStructure ,pdfFile);
         }
     }

     public void sendMailToAllFournisseurs(List<Long> idFourisseurs, List<Long> idProduits) throws Exception {
        List<Fournisseur> fournisseurs= fournisseurService.getAllFournisseursByIDs(idFourisseurs);
        List<Produit> produits= produitService.getListProduitByIDs(idProduits);
        sendMailToFournisseur(fournisseurs,produits);
     }
}
