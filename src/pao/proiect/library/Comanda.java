package pao.proiect.library;

import javafx.util.Pair;
import pao.proiect.books.Carte;

import java.util.ArrayList;
import java.util.List;

public class Comanda {
    private String nrComanda;
    private String metodaPlata;
    private double pretComanda;
    private List<Pair<Carte, Integer>> cartiComandate;

    public Comanda(String nrComanda, String metodaPlata, double pretComanda, List<Pair<Carte, Integer>> carti) {
        this.nrComanda = nrComanda;
        this.metodaPlata = metodaPlata;
        this.pretComanda = pretComanda;
        this.cartiComandate = carti;
    }

    public Comanda() {
        this.nrComanda = "Necunoscut";
        this.metodaPlata = "Necunoscuta";
        this.pretComanda = 0;
        this.cartiComandate = new ArrayList<>();
    }

    public String getNrComanda() {
        return nrComanda;
    }

    public void setNrComanda(String nrComanda) {
        this.nrComanda = nrComanda;
    }

    public String getMetodaPlata() {
        return metodaPlata;
    }

    public void setMetodaPlata(String metodaPlata) {
        this.metodaPlata = metodaPlata;
    }

    public double getPretComanda() {
        return pretComanda;
    }

    public void setPretComanda(double pretComanda) {
        this.pretComanda = pretComanda;
    }

    public List<Pair<Carte, Integer>> getCartiComandate() {
        return cartiComandate;
    }

    public void setCartiComandate(List<Pair<Carte, Integer>> cartiComandate) {
        this.cartiComandate = cartiComandate;
    }

    public Integer cartiComanda() {
        Integer total = 0;
        for (Pair<Carte, Integer> c : cartiComandate) {
            total += c.getValue();
        }

        return total;
    }


}
