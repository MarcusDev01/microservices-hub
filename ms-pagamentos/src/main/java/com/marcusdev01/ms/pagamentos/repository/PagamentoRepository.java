package com.marcusdev01.ms.pagamentos.repository;

import com.marcusdev01.ms.pagamentos.entities.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PagamentoRepository extends JpaRepository<Pagamento, Long> {
}
