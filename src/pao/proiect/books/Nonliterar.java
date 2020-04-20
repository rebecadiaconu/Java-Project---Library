package pao.proiect.books;

import pao.proiect.beings.Autor;

import java.util.List;
import java.util.Scanner;

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

    public void setSpecie(String specie) {
        this.specie = specie;
    }

    public String getTiptext() {
        return tipText;
    }

    public void setTiptext(String tip) {
        this.tipText = tip;
    }


    public Nonliterar citeste(Integer index) {
        int alegere;
        String specie;
        String tipLit;
        Scanner sc = new Scanner(System.in);

        Carte c = super.citeste(index);

        System.out.println("Opera introdusa face parte din urmatoarea specie: \n1. Jurnal si memorii \n2. Enciclopedie \n3. Articol");
        alegere = sc.nextInt();
        sc.nextLine();
        if (alegere == 1) {
            specie = "Jurnal si memorii";
            tipLit = "Autobiografic";
        }
        else if (alegere == 2) {
            specie = "Enciclopedie";
            tipLit = "Informativ";
        }
        else {
            specie = "Articol";
            tipLit = "Informativ";
        }

        return new Nonliterar(c.getTitlu(), c.getAutor(), c.getLimba(), c.getCuvCheie(), c.getTipText(), c.getNrExemplare(), c.getNrPagini(), c.getExemCumparate(), 0, 0, specie, tipLit);
    }

    public void afisare() {
        super.afiseaza();
        System.out.println("Opera face parte din specia: " + specie.toLowerCase() + " si scopul acesteia este unul " + tipText.toLowerCase());
    }
}
