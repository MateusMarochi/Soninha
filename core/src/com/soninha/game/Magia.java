package com.soninha.game;

public class Magia {
    public String nome;
    public String tipo;
    public int custoMana;
    public float danoFisico;
    public float bonusFisico;
    public float danoMagico;
    public float bonusMagico;

    public Magia(String nome, String tipo, int custoMana, float danoFisico, float bonusFisico, float danoMagico, float bonusMagico) {
        this.nome = nome;
        this.tipo = tipo;
        this.custoMana = custoMana;
        this.danoFisico = danoFisico;
        this.bonusFisico = bonusFisico;
        this.danoMagico = danoMagico;
        this.bonusMagico = bonusMagico;
    }

    public void conjurar(Entidade origem, Entidade alvo) {
        if (origem.getMana() >= this.custoMana) {
            if (this.tipo == "dano")
                alvo.recebeDano(danoFisico + origem.getDanoFisico() * bonusFisico, danoMagico + origem.getDanoMagico() * bonusMagico);
            if (this.tipo == "cura")
                alvo.recebeCura(danoFisico + origem.getDanoFisico() * bonusFisico + danoMagico + origem.getDanoMagico() * bonusMagico);
            origem.gastaMana(this.custoMana);
        } else {
            System.out.println(origem.nome + " sem mana para conjurar '" + this.nome + "'");
        }
    }
}

