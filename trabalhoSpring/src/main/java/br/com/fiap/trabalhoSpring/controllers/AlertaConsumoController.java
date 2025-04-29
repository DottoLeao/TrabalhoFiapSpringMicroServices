package br.com.fiap.trabalhoSpring.controllers;

import br.com.fiap.trabalhoSpring.entidades.AlertaConsumo;
import br.com.fiap.trabalhoSpring.repositorios.AlertaConsumoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/alertas-consumo")
public class AlertaConsumoController {

    @Autowired
    private AlertaConsumoRepository alertaConsumoRepository;

    @GetMapping
    public ResponseEntity<List<AlertaConsumo>> listarAlertas() {
        List<AlertaConsumo> alertas = alertaConsumoRepository.findAllByOrderByTimestampDesc();
        return new ResponseEntity<>(alertas, HttpStatus.OK);
    }
}