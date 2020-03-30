package pao.proiect.books;

import pao.proiect.beings.Autor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Dramatic extends Carte implements Digital, Fizic{
    private double pretF;
    private double pretD;
    private String specie;
    private List<String> personaje;

    public Dramatic(String titlu, Autor autor, List<String> limba, List<String> cuvCheie, Integer nrExemplare, Integer nrPagini, Integer cump, String specie, List<String> personaje) {
        super(titlu, autor, limba, cuvCheie, nrExemplare, nrPagini, cump);
        this.specie = specie;
        this.personaje = personaje;
        this.pretD = pretDigital();
        this.pretF = pretFizic();
    }

    public Dramatic() {
        this.specie = "Necunoscuta";
        this.personaje = new ArrayList<>();
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

    public List<String> getPersonaje() {
        return personaje;
    }

    public void setPersonaje(List<String> personaje) {
        this.personaje = personaje;
    }

    @Override
    public double pretDigital() {
        double pret = 1.05 * super.getPret();
        return 1.07 * pret;
    }

    @Override
    public double pretFizic() {
        double pret  = 1.05 * super.getPret();
        return 1.1 * pret;
    }

    public Dramatic citeste() {
        Carte c = super.citeste();
        int alegere;
        String sp, l;
        Scanner sc =  new Scanner(System.in);

        System.out.println("Opera este: \n1. Teatru \n2. Schita");
        alegere = sc.nextInt();
        sc.nextLine();
        if (alegere == 1) {
            sp = "Teatru";
        }
        else {
            sp = "Schita";
        }
        System.out.println("Enumerati cateva personaje importante (separate prin virgula): ");
        l = sc.nextLine();
        String[] arr = l.split(",");
        List<String> pers = new ArrayList<>(Arrays.asList(arr));

        return new Dramatic(c.getTitlu(), c.getAutor(), c.getLimba(), c.getCuvCheie(), c.getNrExemplare(), c.getNrPagini(), c.getExemCumparate(), sp, pers);
    }

    public void afiseaza() {
        super.afiseaza();
        System.out.println("Specie: " + specie);
        System.out.println("Cateva personaje importante: " + personaje);
        System.out.println("Pret carte varianta digitala: " + pretD);
        System.out.println("Pret carte varianta fizica: " + pretF);
    }
}
