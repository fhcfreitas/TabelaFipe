package com.java.exercises.TabelaFipe.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Vehicle (@JsonAlias("Marca") String marca,
                       @JsonAlias("Modelo") String modelo,
                       @JsonAlias ("Valor") String valor,
                       @JsonAlias("MesReferencia") String mesReferencia) {
    @Override
    public String toString() {
        return "Vehicle{" +
                "marca='" + marca + '\'' +
                ", modelo='" + modelo + '\'' +
                ", valor='" + valor + '\'' +
                ", mesReferencia='" + mesReferencia + '\'' +
                '}';
    }
}
