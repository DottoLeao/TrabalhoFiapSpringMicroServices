package br.com.fiap.trabalhoSpring.repositorios;

import br.com.fiap.trabalhoSpring.entidades.ConsumoEnergia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ConsumoEnergiaRepository extends JpaRepository<ConsumoEnergia, Long> {

    List<ConsumoEnergia> findByTimestampBetween(LocalDateTime dataInicio, LocalDateTime dataFim);

    List<ConsumoEnergia> findByAreaAndTimestampBetween(String area, LocalDateTime dataInicio, LocalDateTime dataFim);

    List<ConsumoEnergia> findByArea(String area);

    @Query("SELECT AVG(c.valor) FROM ConsumoEnergia c WHERE c.timestamp BETWEEN :dataInicio AND :dataFim")
    Double calcularMediaConsumoNoPeriodo(@Param("dataInicio") LocalDateTime dataInicio, @Param("dataFim") LocalDateTime dataFim);

    @Query("SELECT SUM(c.valor) FROM ConsumoEnergia c WHERE c.timestamp BETWEEN :dataInicio AND :dataFim")
    Double calcularTotalConsumoNoPeriodo(@Param("dataInicio") LocalDateTime dataInicio, @Param("dataFim") LocalDateTime dataFim);
}