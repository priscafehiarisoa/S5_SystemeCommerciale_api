package s5_systemecommerciale_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import s5_systemecommerciale_api.model.stock.SortieStock;

public interface SortieStockRepository extends JpaRepository<SortieStock, Long> {
}