package br.com.fiap.trabalhoSpring.repositorios;

import br.com.fiap.trabalhoSpring.entidades.AlertaConsumo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlertaConsumoRepository extends JpaRepository<AlertaConsumo, Long> {

    List<AlertaConsumo> findAllByOrderByTimestampDesc();
}