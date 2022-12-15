package com.soninha.game;

public class Minion {
    public String nome;
    public int level;
    public int mana;
    public int vida;
    public float armadura;
    public float resistencia;
    public float danoFisico;
    public float danoMagico;
    public Magia magias[];
    public Item itens[];

    public Minion(String nome){

    }
    public int getMana() {
        int mana = this.mana;
        for (Item item : itens) mana += item.mana;
        return mana;
    }
    public int getVida() {
        int vida = this.vida;
        for (Item item : itens) mana += item.vida;
        return vida;
    }
    public float getArmadura() {
        float armadura = this.armadura;
        for (Item item : itens) armadura += item.armadura;
        return armadura;
    }

    public float getResistencia() {
        float resistencia = this.resistencia;
        for (Item item : itens) resistencia += item.resistencia;
        return resistencia;
    }

    public float getDanoFisico() {
        float danoFisico = this.danoFisico;
        for (Item item : itens) danoFisico += item.danoFisico;
        return danoFisico;
    }
    public float getDanoMagico() {
        float danoMagico = this.danoMagico;
        for (Item item : itens) danoMagico += item.danoMagico;
        return danoMagico;
    }
    public void recebeDano(float danoFisico, float danoMagico) {
        this.vida -= (danoFisico * (1 - this.armadura / (this.armadura + 100))) + (danoMagico * (1 - this.resistencia / (this.resistencia + 100)));
    }

    public void recebeCura(float cura) {
        this.vida += cura;
    }
}
