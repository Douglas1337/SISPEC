package com.example.douglas.myapplication.Classes;

public class Pesagem {
    private int id;
    private double peso;
    private String dataPesagem;
    Animal animal;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public String getDataPesagem() {
        return dataPesagem;
    }

    public void setDataPesagem(String dataPesagem) {
        this.dataPesagem = dataPesagem;
    }

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }
}
