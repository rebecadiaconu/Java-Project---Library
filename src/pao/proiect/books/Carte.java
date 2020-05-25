package pao.proiect.books;

import pao.proiect.beings.Autor;
import java.util.ArrayList;
import java.util.List;
import static java.lang.Math.round;

public class Carte implements Digital, Fizic{
    private String titlu;
    private Autor autor;
    private List<String> limba;
    private List<String> cuvCheie;
    private Integer tip;
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
        this.tip = tip;
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
        this.tip = -1;
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

    public Integer getTip() {
        return tip;
    }

    public void setTip(Integer tipText) {
        this.tip = tipText;
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

    @Override
    public double pretDigital() {
        if (getTip() == 1) {
            return 1.15 * getPret() + 0.25 * getNrPagini() % 10;
        }
        else if (getTip() == 2) {
            return 1.07 * getPret() + 0.25 * getNrPagini() % 10;
        }
        else {
            System.out.println("Nu a fost inregistrat corespunzator tipul de text al operei.");
            System.out.println(tip);
        }

        return 0;
    }

    @Override
    public double pretFizic() {
        if (this.tip == 1) {
            return 1.2 * getPret() + 0.45 * getNrPagini() % 10;
        }
        else if (this.tip == 2) {
            return 1.1 * getPret() + 0.45 * getNrPagini() % 10;
        }
        else {
            System.out.println("Nu a fost inregistrat corespunzator tipul de text al operei.");
        }

        return 0;
    }

}
