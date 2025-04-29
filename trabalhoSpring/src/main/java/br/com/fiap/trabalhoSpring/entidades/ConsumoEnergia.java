package br.com.fiap.trabalhoSpring.entidades;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.time.LocalDateTime;

@Entity
@Table(name = "consumo_energia")
public class ConsumoEnergia {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "consumo_seq")
    @SequenceGenerator(name = "consumo_seq", sequenceName = "consumo_energia_seq", allocationSize = 1)
    private Long id;

    @NotNull(message = "A área é obrigatória")
    private String area;

    @NotNull(message = "O timestamp é obrigatório")
    private LocalDateTime timestamp;

    @NotNull(message = "O valor do consumo é obrigatório")
    @Positive(message = "O valor do consumo deve ser positivo")
    private Double valor;

    @NotNull(message = "A unidade é obrigatória")
    private String unidade;

    // Construtores
    public ConsumoEnergia() {
    }

    public ConsumoEnergia(String area, LocalDateTime timestamp, Double valor, String unidade) {
        this.area = area;
        this.timestamp = timestamp;
        this.valor = valor;
        this.unidade = unidade;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public String getUnidade() {
        return unidade;
    }

    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }
}