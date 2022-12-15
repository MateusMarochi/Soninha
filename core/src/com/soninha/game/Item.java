package com.soninha.game;

public class Item {
    public String nome;
    public int mana;
    public int vida;
    public float armadura;
    public float resistencia;
    public float danoFisico;
    public float danoMagico;

    public Item(String nome,int mana, int vida, float armadura, float resistencia, float danoFisico, float danoMagico) {
        this.nome = nome;
        this.mana = mana;
        this.vida = vida;
        this.armadura = armadura;
        this.resistencia = resistencia;
        this.danoFisico = danoFisico;
        this.danoMagico = danoMagico;
    }
}
