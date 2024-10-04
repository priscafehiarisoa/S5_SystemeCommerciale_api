package s5_systemecommerciale_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import s5_systemecommerciale_api.model.stock.VStock;

import java.util.List;

public interface VStockRepository extends JpaRepository<VStock, Long> {

    @Query(value = "select v_stock.id, id_produit, quantite_produit-coalesce(sum(mouvement_sortie.quantite_mouvement_sortie),0) as quantite_produit , prix_unitaire, date_reception from v_stock left join mouvement_sortie on v_stock.id=mouvement_sortie.id_stock group by quantite_produit, id_produit, v_stock.id, prix_unitaire, date_reception",nativeQuery = true)
    List<VStock> getResteStock();

    @Query(value = "select v_stock.id, id_produit, quantite_produit-coalesce(sum(mouvement_sortie.quantite_mouvement_sortie),0) as quantite_produit , prix_unitaire, date_reception from v_stock left join mouvement_sortie on v_stock.id=mouvement_sortie.id_stock where id_produit=:idProduit group by quantite_produit, id_produit, v_stock.id, prix_unitaire, date_reception order by case when :order = 'ASC' THEN date_reception END ASC, CASE WHEN :order = 'DESC' THEN date_reception END DESC  ",nativeQuery = true)
    List<VStock> getResteStockProduitOrdered(@Param("idProduit") Long idProduit,@Param("order") String order);

    @Query(value = "select sum(quantite_produit*prix_unitaire)/sum(quantite_produit) from reste where id_produit=:id",nativeQuery = true)
    double getCUMP(@Param("id") Long id);
}