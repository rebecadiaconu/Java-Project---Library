package pao.proiect.books;

import pao.proiect.beings.Autor;
import java.util.List;

public class Literar extends Carte {
    private String specie;
    private String genLit;

    public Literar(String titlu, Autor autor, List<String> limba, List<String> cuvCheie, Integer tip, Integer nrExemplare, Integer nrPagini, Integer cump, double pD, double pF, String specie, String gen) {
        super(titlu, autor, limba, cuvCheie, tip, nrExemplare, nrPagini, cump, pD, pF);
        this.specie = specie;
        this.genLit = gen;
    }

    public Literar() {
        super();
        this.specie = "Necunoscuta";
        this.genLit = "Necunoscut";
    }

    public String getSpecie() {
        return specie;
    }

    public String getGenLit() {
        return genLit;
    }
}
