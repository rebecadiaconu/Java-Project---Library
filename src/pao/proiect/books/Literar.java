package pao.proiect.books;

import pao.proiect.beings.Autor;

import java.util.List;
import java.util.Scanner;

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
        String specie = "";
        String genLit = "";
        Scanner sc = new Scanner(System.in);

        Carte c = super.citeste(index);

        System.out.println("Opera introdusa face parte din urmatoarea specie: \n1. Roman \n2. Basm \n3. Nuvela \n4. Teatru si schite \n5. Poezie \n6. Balada \n7. Imn");
        alegere = sc.nextInt();
        sc.nextLine();
        if (alegere == 1) {
            specie = "Roman";
            genLit = "Epic";
        }
        else if (alegere == 2) {
            specie = "Basm";
            genLit = "Epic";
        }
        else if (alegere == 3) {
            specie = "Nuvela";
            genLit = "Epic";
        }
        else if (alegere == 4) {
            specie = "Teatru si schite";
            genLit = "Dramatic";
        }
        else if (alegere == 5) {
            specie = "Poezie";
            genLit = "Liric";
        }
        else if (alegere == 6) {
            specie = "Balada";
            genLit = "Liric";
        }
        else if (alegere == 7) {
            specie = "Imn";
            genLit = "Liric";
        }

        return new Literar(c.getTitlu(), c.getAutor(), c.getLimba(), c.getCuvCheie(), c.getTipText(), c.getNrExemplare(), c.getNrPagini(), c.getExemCumparate(), 0, 0, specie, genLit);
    }

    public void afisare() {
        super.afiseaza();
        System.out.println("Opera face parte din specia: " + specie.toUpperCase() + " si apartine genului " + genLit.toLowerCase());
    }
}
