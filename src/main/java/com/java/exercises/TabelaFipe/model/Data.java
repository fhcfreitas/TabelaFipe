package com.java.exercises.TabelaFipe.model;

public record Data(String codigo, String nome) {

    @Override
    public String toString() {
        return nome.toUpperCase() + " (CÓD. " + codigo + ")";
    }
}
