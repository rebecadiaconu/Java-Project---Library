package pao.proiect.books;

import pao.proiect.beings.Autor;

import java.util.List;
import java.util.Scanner;

public class Liric extends Carte implements Digital, Fizic{
    private double pretF;
    private double pretD;
    private String specie;

    public Liric(String titlu, Autor autor, List<String> limba, List<String> cuvCheie, Integer nrExemplare, Integer nrPagini, Integer cump, String specie) {
        super(titlu, autor, limba, cuvCheie, nrExemplare, nrPagini, cump);
        this.specie = specie;
        this.pretD = pretDigital();
        this.pretF = pretFizic();
    }

    public Liric() {
        this.specie = "Necunoscuta";
        this.pretD = 0;
        this.pretF = 0;
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

    @Override
    public double pretDigital() {
        double pret = 1.02 * super.getPret();
        return 1.07 * pret;
    }

    @Override
    public double pretFizic() {
        double pret = 1.02 * super.getPret();
        return 1.1 * pret;
    }

    public Liric citeste() {
        Carte c = super.citeste();
        int alegere;
        String sp;
        Scanner sc = new Scanner(System.in);

        System.out.println("Opera este: \n1. Poezie \n2. Balada \n3. Imn \n4. Diverse");
        alegere = sc.nextInt();
        sc.nextLine();
        if (alegere == 1) {
            sp = "Poezie";
        }
        else if (alegere == 2) {
            sp = "Balada";
        }
        else if (alegere == 3) {
            sp = "Imn";
        }
        else {
            sp = "Volum";
        }

        return new Liric(c.getTitlu(), c.getAutor(), c.getLimba(), c.getCuvCheie(), c.getNrExemplare(), c.getNrPagini(), c.getExemCumparate(), sp);
    }

    public void afiseaza() {
        super.afiseaza();
        System.out.println("Specie: " + specie);
        System.out.println("Pret carte varianta digitala: " + pretD);
        System.out.println("Pret carte varianta fizica: " + pretF);
    }
}
