package com.java.exercises.TabelaFipe.main;

import com.java.exercises.TabelaFipe.model.Data;
import com.java.exercises.TabelaFipe.model.Modelos;
import com.java.exercises.TabelaFipe.model.Vehicle;
import com.java.exercises.TabelaFipe.service.ConsumingAPI;
import com.java.exercises.TabelaFipe.service.ConvertData;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    private Scanner input = new Scanner(System.in);
    private ConsumingAPI consume = new ConsumingAPI();
    private ConvertData converter = new ConvertData();
    private final String WEBSITE = "https://parallelum.com.br/fipe/api/v1/";
    public void menu() {
        var menu = """
                *********************
                CONSULTA TABELA FILPE
                *********************
                                
                ** OPÇÕES **
                Carro
                Moto
                Caminhão
                                
                Digite uma das opções para consultar:
                """;

        System.out.println(menu);
        var option = input.nextLine();
        String address;

        if (option.toLowerCase().contains("carr")) {
            address = WEBSITE + "carros/marcas/";
        } else if (option.toLowerCase().contains("mot")) {
            address = WEBSITE + "motos/marcas/";
        } else {
            address = WEBSITE + "caminhoes/marcas/";
        }

        System.out.println("********** LISTA DE MARCAS: **********\n");

        var json = consume.obtainData(address);
        var brands = converter.obtainList(json, Data.class);
        brands.stream()
                .sorted(Comparator.comparing(Data::nome))
                .forEach(System.out::println);

        System.out.println("\nDigite o código da marca:");
        var brandCode = input.nextLine();
        address = address + brandCode + "/modelos/";
        json = consume.obtainData(address);
        var modelList = converter.obtainData(json, Modelos.class);

        System.out.println("\nModelos dessa marca:");
        modelList.modelos().stream()
                .sorted(Comparator.comparing(Data::nome))
                .forEach(System.out::println);

        System.out.println("\nPesquise o nome do modelo desejado:");
        var vehicleName = input.nextLine();

        List<Data> searchedModel = modelList.modelos().stream()
                .filter(m -> m.nome().toLowerCase().contains(vehicleName.toLowerCase()))
                .collect(Collectors.toList());

        System.out.println("\nModelos filtrados:\n");
        searchedModel.forEach(System.out::println);

        System.out.println("Digite o código do modelo:");
        var modelCode = input.nextLine();

        address = address + modelCode + "/anos/";
        json = consume.obtainData(address);
        List<Data> years = converter.obtainList(json, Data.class);
        List<Vehicle> vehicles = new ArrayList<>();

        for (int i = 0; i < years.size(); i++) {
            var yearsAddress = address + years.get(i).codigo();
            json = consume.obtainData(yearsAddress);
            Vehicle vehicle = converter.obtainData(json, Vehicle.class);
            vehicles.add(vehicle);
        }

        System.out.println("\nLançamentos por ano modelo procurado:");
        vehicles.forEach(System.out::println);
    }
}
