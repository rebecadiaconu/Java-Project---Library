package pao.proiect.library;

import pao.proiect.books.*;

import java.util.ArrayList;
import java.util.List;

public class Rubrica {
    private Integer nrCarti;
    private String numeRubrica;
    private List<Carte> carti;

    public Rubrica(String numeRubrica, Integer nr, List<Carte> carti) {
        this.numeRubrica = numeRubrica;
        this.nrCarti = nr;
        this.carti = carti;
    }

    public Rubrica() {
        carti = new ArrayList<>();
        this.numeRubrica = "Necunoscut";
    }

    public List<Carte> getCarti() {
        return carti;
    }

    public void setCarti(List<Carte> carti) {
        this.carti = carti;
    }

    public Integer getNrCarti() {
        return nrCarti;
    }

    public void setNrCarti(Integer nrCarti) {
        this.nrCarti = nrCarti;
    }

    public String getNumeRubrica() {
        return numeRubrica;
    }

    public void setNumeRubrica(String numeRubrica) {
        this.numeRubrica = numeRubrica;
    }

}
