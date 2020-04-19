package pao.proiect.books;

import pao.proiect.beings.Autor;

import java.util.List;
import java.util.Scanner;

public class Literar extends Carte{
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

    public void setSpecie(String specie) {
        this.specie = specie;
    }

    public String getGenLit() {
        return genLit;
    }

    public void setGenLit(String genLit) {
        this.genLit = genLit;
    }

    public Literar citeste(Integer index) {
        int alegere;
        String sp = "", gen = "";
        Scanner sc = new Scanner(System.in);

        Carte c = super.citeste(index);

        System.out.println("Opera introdusa face parte din urmatoarea specie: \n1. Roman \n2. Basm \n3. Nuvela \n4. Teatru si schite \n5. Poezie \n6. Balada \n7. Imn");
        alegere = sc.nextInt();
        sc.nextLine();
        if (alegere == 1) {
            sp = "Roman";
            gen = "Epic";
        }
        else if (alegere == 2) {
            sp = "Basm";
            gen = "Epic";
        }
        else if (alegere == 3) {
            sp = "Nuvela";
            gen = "Epic";
        }
        else if (alegere == 4) {
            sp = "Teatru si schite";
            gen = "Dramatic";
        }
        else if (alegere == 5) {
            sp = "Poezie";
            gen = "Liric";
        }
        else if (alegere == 6) {
            sp = "Balada";
            gen = "Liric";
        }
        else if (alegere == 7) {
            sp = "Imn";
            gen = "Liric";
        }

        return new Literar(c.getTitlu(), c.getAutor(), c.getLimba(), c.getCuvCheie(), c.getTipText(), c.getNrExemplare(), c.getNrPagini(), c.getExemCumparate(), 0, 0, sp, gen);
    }

    public void afisare() {
        super.afiseaza();
        System.out.println("Opera face parte din specia: " + specie.toUpperCase() + " si apartine genului " + genLit.toLowerCase());
    }
}
