package pao.proiect.books;

import pao.proiect.beings.Autor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static java.lang.Math.round;

public class Carte {
    private String titlu;
    private Autor autor;
    private List<String> limba;
    private Integer nrExemplare;
    private Integer nrPagini;
    private List<String> cuvCheie;
    private Integer exemCumparate;

    public Carte(String titlu, Autor autor, List<String> limba, List<String> cuvCheie, Integer nrExemplare, Integer nrPagini, Integer cump) {
        this.titlu = titlu;
        this.autor = autor;
        this.limba = limba;
        this.cuvCheie = cuvCheie;
        this.nrExemplare = nrExemplare;
        this.nrPagini = nrPagini;
        this.exemCumparate = cump;
    }

    public Carte() {
        this.titlu = "Necunoscut";
        this.autor = new Autor();
        this.limba = new ArrayList<>();
        this.cuvCheie = new ArrayList<>();
        this.nrExemplare = 0;
        this.exemCumparate = 0;
    }

    public String getTitlu() {
        return titlu;
    }

    public void setTitlu(String titlu) {
        this.titlu = titlu;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public List<String> getLimba() {
        return limba;
    }

    public void setLimba(List<String> limba) {
        this.limba = limba;
    }

    public Integer getNrPagini() {
        return nrPagini;
    }

    public void setNrPagini(Integer nrPagini) {
        this.nrPagini = nrPagini;
    }

    public Integer getNrExemplare() {
        return nrExemplare;
    }

    public void setNrExemplare(Integer nrExemplare) {
        this.nrExemplare = nrExemplare;
    }

    public List<String> getCuvCheie() {
        return cuvCheie;
    }

    public void setCuvCheie(List<String> cuvCheie) {
        this.cuvCheie = cuvCheie;
    }

    public Integer getExemCumparate() {
        return exemCumparate;
    }

    public void setExemCumparate(Integer exemCumparate) {
        this.exemCumparate = exemCumparate;
    }

    double getPret() {
        int x = this.nrPagini;
        double pret = 0;

        if (x < 70) {
            pret += 20 + x % 10;
        }
        else if (x < 150) {
            pret += 30 + x % 10;
        }
        else if (x < 200) {
            pret += 45 + x % 30;
        }
        else if (x < 500) {
            pret += 70 + x % 60;
        }
        else if (x < 1000) {
            pret += 100 + x % 10;
        }
        else {
            pret += 150 + x % 100;
        }

        return round(pret * 100.0) / 100.0;
    }

    public Carte citeste() {
        int nr, nrExempl;
        String titlu, s, l;
        Autor a = new Autor();
        Scanner sc = new Scanner(System.in);

        System.out.println("Introduceti titlul cartii: ");
        titlu = sc.nextLine();
        System.out.println("Numar pagini carte: ");
        nr = sc.nextInt();
        System.out.println("Numar exemplare: ");
        nrExempl = sc.nextInt();
        sc.nextLine();
        System.out.println("Cartea este disponibila in limbile (separate prin spatiu): ");
        l = sc.nextLine();
        String[] arrr = l.split(" ");
        List<String> limba = new ArrayList<>(Arrays.asList(arrr));
        System.out.println("Introduceti cateva cuvinte cheie ce descriu opera (separate prin VIRGULA): ");
        s = sc.nextLine();
        String[] arr = s.split(",");
        List<String> cuvCheie = new ArrayList<>(Arrays.asList(arr));
        System.out.println("Detalii autor: ");
        Autor nou = a.citeste();

        return new Carte(titlu, nou, limba, cuvCheie, nrExempl, nr, 0);
    }

    public void afiseaza() {
        System.out.println("Cartea " + this.titlu + ", creatia lui " + this.autor.getNume() + " " + this.autor.getPrenume() + ".");
        System.out.println("Disponibila in  " + this.limba);
        System.out.println("Numar de exemplare:  " + this.nrExemplare);
        System.out.println("Numar exemplare cumparate: " + this.exemCumparate);
    }

}
