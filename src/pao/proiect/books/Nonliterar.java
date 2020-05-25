package pao.proiect.books;

import pao.proiect.beings.Autor;
import java.util.List;

public class Nonliterar extends Carte {
    private String specie;
    private String tipText;

    public Nonliterar(String titlu, Autor autor, List<String> limba, List<String> cuvCheie, Integer tip, Integer nrExemplare, Integer nrPagini, Integer cump, double pD, double pF, String specie, String gen) {
        super(titlu, autor, limba, cuvCheie, tip, nrExemplare, nrPagini, cump, pD, pF);
        this.specie = specie;
        this.tipText = gen;
    }

    public Nonliterar() {
        super();
        this.specie = "Necunoscuta";
        this.tipText = "Necunoscut";
    }

    public String getSpecie() {
        return specie;
    }

    public String getTiptext() {
        return tipText;
    }
}
