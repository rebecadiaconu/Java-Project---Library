package pao.proiect.books;

import pao.proiect.beings.Autor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Epic extends Carte implements Digital, Fizic{
    private double pretF;
    private double pretD;
    private String specie;
    private List<String> personaje;

    public Epic(String titlu, Autor autor, List<String> limba, List<String> cuvCheie, Integer nrExemplare, Integer nrPagini, Integer cumparate, String specie, List<String> personaje) {
        super(titlu, autor, limba, cuvCheie, nrExemplare, nrPagini, cumparate);
        this.specie = specie;
        this.personaje = personaje;
        this.pretD = pretDigital();
        this.pretF = pretFizic();
    }

    public Epic() {
        super();
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
        double pret = 1.07 * super.getPret();
        return 1.07 * pret;
    }

    @Override
    public double pretFizic() {
        double pret = 1.07 * super.getPret();
        return 1.1 * pret;
    }

    public Epic citeste() {
        Carte c = super.citeste();
        int alegere;
        String sp, s;
        Scanner sc = new Scanner(System.in);

        System.out.println("Opera este: \n1. Roman\n2. Nuvela\n3. Basm\n");
        alegere = sc.nextInt();
        sc.nextLine();
        if (alegere == 1) {
            sp = "Roman";
        }
        else if (alegere == 2) {
            sp = "Nuvela";
        }
        else {
            sp = "Basm";
        }
        System.out.println("Enumerati cateva personaje importante (separate prin virgula): ");
        s = sc.nextLine();
        String[] arr = s.split(",");
        List<String> persoanej = new ArrayList<>(Arrays.asList(arr));

        return new Epic(c.getTitlu(), c.getAutor(), c.getLimba(), c.getCuvCheie(), c.getNrExemplare(), c.getNrPagini(), c.getExemCumparate(), sp, persoanej);
    }

    public void afiseaza() {
        super.afiseaza();
        System.out.println("Specie: " + this.specie);
        System.out.println("Cateva personaje importante: " + this.personaje);
        System.out.println("Pret carte varianta digitala: " + this.pretD);
        System.out.println("Pret carte varianta fizica: " + this.pretF);
    }

}
