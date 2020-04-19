package pao.proiect.books;

import pao.proiect.beings.Autor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static java.lang.Math.round;

public class Carte implements Digital, Fizic{
    private String titlu;
    private Autor autor;
    private List<String> limba;
    private List<String> cuvCheie;
    private Integer tipText;
    private Integer nrExemplare;
    private Integer nrPagini;
    private Integer exemCumparate;
    private double pretF;
    private double pretD;

    public Carte(String titlu, Autor autor, List<String> limba, List<String> cuvCheie, Integer tip, Integer nrExemplare, Integer nrPagini, Integer exemCumparate, double pD, double pF) {
        this.titlu = titlu;
        this.autor = autor;
        this.limba = limba;
        this.cuvCheie = cuvCheie;
        this.tipText = tip;
        this.nrExemplare = nrExemplare;
        this.nrPagini = nrPagini;
        this.exemCumparate = exemCumparate;
        if (pD == 0) {
            this.pretD = pretDigital();
        }
        else {
            this.pretD = pD;
        }
        if (pF == 0) {
            this.pretF = pretFizic();
        }
        else {
            this.pretF = pF;
        }
    }

    public Carte() {
        this.titlu = "Necunoscut";
        this.autor = new Autor();
        this.limba = new ArrayList<>();
        this.cuvCheie = new ArrayList<>();
        this.tipText = -1;
        this.nrExemplare = 0;
        this.nrPagini = 0;
        this.exemCumparate = 0;
        this.pretF = 0;
        this.pretD = 0;
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

    public Integer getTipText() {
        return tipText;
    }

    public void setTipText(Integer tipText) {
        this.tipText = tipText;
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

        return round(pret * 100) / 100.00;
    }

    public Carte citeste(Integer index) {
        int nrPag, nrExempl;
        String str, str1, str2, titlu;
        Autor a = new Autor();
        Scanner sc = new Scanner(System.in);

        System.out.println("Introduceti titlul cartii: ");
        str = sc.nextLine();
        titlu = str.substring(0, 1).toLowerCase() + str.substring(1).toLowerCase();
        System.out.println("Detalii autor: ");
        Autor nou = a.citeste();
        System.out.println("Numar pagini carte: ");
        nrPag = sc.nextInt();
        System.out.println("Numar exemplare: ");
        nrExempl = sc.nextInt();
        sc.nextLine();
        System.out.println("Cartea este disponibila in limbile (separate prin VIRGULA): ");
        str1 = sc.nextLine();
        String[] arrr = str1.split(",");
        List<String> limba = new ArrayList<>(Arrays.asList(arrr));
        System.out.println("Introduceti cateva cuvinte cheie ce descriu opera (separate prin VIRGULA): ");
        str2 = sc.nextLine();
        String[] arr = str2.split(",");
        List<String> cuvCheie = new ArrayList<>(Arrays.asList(arr));

        Carte c = new Carte(titlu, nou, limba, cuvCheie, index, nrExempl, nrPag, 0, 0, 0);

        return c;
    }

    public void afiseaza() {
        System.out.println("Cartea " + this.titlu + " este scrisa de " + this.autor.getNume() + " " + this.autor.getPrenume() + ".");
        System.out.println("Disponibila in: " + this.limba);
        System.out.println("Numar pagini: " + this.nrPagini);
        System.out.println("Numar de exemplare: " + this.nrExemplare);
        System.out.println("Numar exemplare cumparate: " + this.exemCumparate);
        System.out.println("Pret varianta digitala: " + this.pretD);
        System.out.println("Pret varianta fizica: " + this.pretF);
    }

    @Override
    public double pretDigital() {
        if (this.getTipText() == 1) {
            return 1.15 * getPret() + 0.25 * getNrPagini() % 10;
        }
        else if (getTipText() == 2) {
            return 1.07 * getPret() + 0.25 * getNrPagini() % 10;
        }
        else {
            System.out.println("Nu a fost inregistrat corespunzator tipul de text al operei.");
            System.out.println(tipText);
        }

        return 0;
    }

    @Override
    public double pretFizic() {
        if (this.tipText == 1) {
            return 1.2 * getPret() + 0.45 * getNrPagini() % 10;
        }
        else if (this.tipText == 2) {
            return 1.1 * getPret() + 0.45 * getNrPagini() % 10;
        }
        else {
            System.out.println("Nu a fost inregistrat corespunzator tipul de text al operei.");
        }

        return 0;
    }
}
