package br.com.fiap.trabalhoSpring.controllers;


import br.com.fiap.trabalhoSpring.entidades.ConsumoEnergia;
import br.com.fiap.trabalhoSpring.repositorios.ConsumoEnergiaRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/consumo-energia")
public class ConsumoEnergiaController {

    @Autowired
    private ConsumoEnergiaRepository consumoEnergiaRepository;

    @PostMapping
    public ResponseEntity<ConsumoEnergia> registrarConsumo(@Valid @RequestBody ConsumoEnergia consumoEnergia) {
        ConsumoEnergia novoConsumo = consumoEnergiaRepository.save(consumoEnergia);
        return new ResponseEntity<>(novoConsumo, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ConsumoEnergia>> listarConsumo(
            @RequestParam(required = false) String dataInicio,
            @RequestParam(required = false) String dataFim,
            @RequestParam(required = false) String area) {
        List<ConsumoEnergia> consumos;
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

        if (dataInicio != null && dataFim != null && area != null) {
            LocalDateTime inicio = LocalDateTime.parse(dataInicio, formatter);
            LocalDateTime fim = LocalDateTime.parse(dataFim, formatter);
            consumos = consumoEnergiaRepository.findByAreaAndTimestampBetween(area, inicio, fim);
        } else if (dataInicio != null && dataFim != null) {
            LocalDateTime inicio = LocalDateTime.parse(dataInicio, formatter);
            LocalDateTime fim = LocalDateTime.parse(dataFim, formatter);
            consumos = consumoEnergiaRepository.findByTimestampBetween(inicio, fim);
        } else if (area != null) {
            consumos = consumoEnergiaRepository.findByArea(area);
        } else {
            consumos = consumoEnergiaRepository.findAll();
        }
        return new ResponseEntity<>(consumos, HttpStatus.OK);
    }

    @GetMapping("/estatisticas")
    public ResponseEntity<?> obterEstatisticas(
            @RequestParam(required = false) String dataInicio,
            @RequestParam(required = false) String dataFim) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        Double totalConsumo = null;
        Double mediaConsumo = null;

        if (dataInicio != null && dataFim != null) {
            LocalDateTime inicio = LocalDateTime.parse(dataInicio, dateFormatter);
            LocalDateTime fim = LocalDateTime.parse(dataFim, dateFormatter);
            totalConsumo = consumoEnergiaRepository.calcularTotalConsumoNoPeriodo(inicio, fim);
            mediaConsumo = consumoEnergiaRepository.calcularMediaConsumoNoPeriodo(inicio, fim);
        } else {
            totalConsumo = consumoEnergiaRepository.calcularTotalConsumoNoPeriodo(LocalDateTime.MIN, LocalDateTime.MAX);
            mediaConsumo = consumoEnergiaRepository.calcularMediaConsumoNoPeriodo(LocalDateTime.MIN, LocalDateTime.MAX);
        }

        return new ResponseEntity<>(Map.of("totalConsumo", Optional.ofNullable(totalConsumo).orElse(0.0),
                "mediaConsumo", Optional.ofNullable(mediaConsumo).orElse(0.0)), HttpStatus.OK);
    }
}