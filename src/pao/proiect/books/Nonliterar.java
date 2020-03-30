package pao.proiect.books;

import pao.proiect.beings.Autor;

import java.util.List;
import java.util.Scanner;

public class Nonliterar extends Carte implements Digital, Fizic{
    private double pretF;
    private double pretD;
    private String specie;
    private String tipText;

    public Nonliterar(String titlu, Autor autor, List<String> limba, List<String> cuvCheie, Integer nrExemplare, Integer nrPagini, Integer cump, String specie, String gen) {
        super(titlu, autor, limba, cuvCheie, nrExemplare, nrPagini, cump);
        this.specie = specie;
        this.tipText = gen;
        this.pretD = pretDigital();
        this.pretF = pretFizic();
    }

    public Nonliterar() {
        this.specie = "Necunoscuta";
        this.tipText = "Necunoscut";
    }
    public double getPretF() {
        return pretF;
    }

    public void setPretF(double pretF) {
        this.pretF = pretF;
    }

    public double getPretD() {
        return pretD;
    }

    public void setPretD(double pretD) {
        this.pretD = pretD;
    }

    public String getSpecie() {
        return specie;
    }

    public void setSpecie(String specie) {
        this.specie = specie;
    }

    public String getGen() {
        return tipText;
    }

    public void setGen(String gen) {
        this.tipText = gen;
    }

    @Override
    public double pretDigital() {
        double pret = 1.015 * super.getPret();
        return 1.07 * pret;
    }

    @Override
    public double pretFizic() {
        double pret = 1.015 * super.getPret();
        return 1.1 * pret;
    }

    public Nonliterar citeste() {
        Carte c = super.citeste();
        int alegere;
        String sp, gen;
        Scanner sc = new Scanner(System.in);

        System.out.println("Opera introdusa face parte din urmatoarea specie: \n1. Jurnal si memorii \n2. Enciclopedie \n3. Articol");
        alegere = sc.nextInt();
        sc.nextLine();
        if (alegere == 1) {
            sp = "Jurnal si memorii";
            gen = "Autobiografic";
        }
        else if (alegere == 2) {
            sp = "Enciclopedie";
            gen = "Informativ";
        }
        else {
            sp = "Articol";
            gen = "Informativ";
        }

        return new Nonliterar(c.getTitlu(), c.getAutor(), c.getLimba(), c.getCuvCheie(), c.getNrExemplare(), c.getNrPagini(), c.getExemCumparate(), sp, gen);
    }

    public void afisare() {
        super.afiseaza();
        System.out.println("Opera face parte din specia: " + specie + " si scopul acesteia este unul " + tipText.toLowerCase());
        System.out.println("Pret carte varianta digitala: " + pretD);
        System.out.println("Pret carte varianta fizica: " + pretF);
    }
}
