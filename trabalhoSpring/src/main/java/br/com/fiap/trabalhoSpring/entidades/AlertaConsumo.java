package br.com.fiap.trabalhoSpring.entidades;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "alerta_consumo")
public class AlertaConsumo {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "alerta_seq")
    @SequenceGenerator(name = "alerta_seq", sequenceName = "alerta_consumo_seq", allocationSize = 1)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "consumo_energia_id", nullable = false)
    private ConsumoEnergia consumoEnergia;

    @NotNull(message = "O timestamp do alerta é obrigatório")
    private LocalDateTime timestamp;

    @NotNull(message = "A mensagem do alerta é obrigatória")
    private String mensagem;

    // Construtores
    public AlertaConsumo() {
    }

    public AlertaConsumo(ConsumoEnergia consumoEnergia, LocalDateTime timestamp, String mensagem) {
        this.consumoEnergia = consumoEnergia;
        this.timestamp = timestamp;
        this.mensagem = mensagem;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ConsumoEnergia getConsumoEnergia() {
        return consumoEnergia;
    }

    public void setConsumoEnergia(ConsumoEnergia consumoEnergia) {
        this.consumoEnergia = consumoEnergia;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
}