package com.example.douglas.myapplication.Classes;

/**
 * Created by Douglas on 04/09/2017.
 */

public class Propriedade {

    private String nome;
    private Double extensao;
    private String proprietario;
    private String cpfProprietario;
    private String municipio;
    private String localidade;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getExtensao() {
        return extensao;
    }

    public void setExtensao(Double extensao) {
        this.extensao = extensao;
    }

    public String getProprietario() {
        return proprietario;
    }

    public void setProprietario(String proprietario) {
        this.proprietario = proprietario;
    }

    public String getCpfProprietario() {
        return cpfProprietario;
    }

    public void setCpfProprietario(String cpfProprietario) {
        this.cpfProprietario = cpfProprietario;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getLocalidade() {
        return localidade;
    }

    public void setLocalidade(String localidade) {
        this.localidade = localidade;
    }
}
