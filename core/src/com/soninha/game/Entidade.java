package com.soninha.game;

public class Entidade {
    public String nome;
    public int level;
    public float xp;
    public float mana;
    public float manaMax;
    public float manaRegen;
    public float vida;
    public float vidaMax;
    public float vidaRegen;
    public float armadura;
    public float resistencia;
    public float danoFisico;
    public float danoMagico;
    public Magia magias[];
    public Item itens[];

    public Entidade(String nome, int level) {
        this.nome = nome;

        if (nome.equals("Sona")) {
            this.level = level;
            xp = 0;
            mana = 340 + 45 * level;
            manaMax = mana;
            manaRegen = 11.5f + 0.4f * level;
            vida = 550 + 91 * level;
            vidaMax = vida;
            vidaRegen = 5.5f + 0.55f * level;
            armadura = 26 + 4.2f * level;
            resistencia = 30 + 1.3f * level;
            danoFisico = 49 + 3 * level;
            danoMagico = 0;
            magias = new Magia[]{
                    new Magia("Ataque Fisico", "dano", 0, 0, 1.0f, 0, 0),
                    new Magia("Ataque Magico", "dano", 10, 0, 0, 0, 1.0f),
                    new Magia("Som da Dor", "dano", 50, 0, 0, 50, 0.4f),
                    new Magia("Curar Feridas", "cura", 80, 0, 0, 20, 0.15f),
                    new Magia("Crescendo", "ultimate", 100, 0, 0, 120, 0.5f),
            };
            itens = new Item[]{
                    new Item("Anel de Doran", 60, 0, 0, 0, 0, 15)
            };
        }
        if (nome.equals("Mago")) {
            this.level = level;
            if (level <= 5) {
                vida = 290 + 6 * level;
                danoFisico = 22.5f + 1.5f * level;
            } else {
                vida = 320 + 8.25f * (level - 5);
                danoFisico = 30f + 4.5f * (level - 5);
            }
            vidaMax = vida;
            mana = 0;
            manaMax = mana;
            manaRegen = 0;
            vidaRegen = 0;
            armadura = 0;
            resistencia = 0;
            danoMagico = 0;
            magias = new Magia[]{
                    new Magia("Ataque", "dano", 0, 0, 1.0f, 0, 0)
            };
            itens = new Item[0];
        }
        if (nome.equals("Guerreiro")) {
            this.level = level;
            if (level <= 5) {
                vida = 455 + 22 * level + 0.3f * (level - 1) / 2 * level;
                danoFisico = 12;
                armadura = 0;
            } else {
                vida = 568 + 32.25f * (level - 5) + 0.3f * (level - 1) / 2 * level;
                danoFisico = 12 + 3.41f * level;
                armadura = 0.085f * (level - 6) / 2 * (level - 5);
            }
            vidaMax = vida;
            mana = 0;
            manaMax = mana;
            manaRegen = 0;
            vidaRegen = 0;

            resistencia = 0;
            danoMagico = 0;
            magias = new Magia[]{
                    new Magia("Ataque", "dano", 0, 0, 1.0f, 0, 0)
            };
            itens = new Item[0];
        }
    }

    public float getMana() {
        float mana = this.mana;
        for (Item item : itens) mana += item.mana;
        return mana;
    }

    public float getVida() {
        float vida = this.vida;
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

    public void gastaMana(float mana) {
        this.mana -= mana;
    }

}
