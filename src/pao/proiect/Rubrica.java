package pao.proiect;

import pao.proiect.books.*;

import java.util.ArrayList;
import java.util.List;

public class Rubrica {
    private Integer nrCarti;
    private String numeRubrica;
    private List<Epic> CartiE;
    private List<Liric> CartiL;
    private List<Dramatic> CartiD;
    private List<Nonliterar> CartiN;

    public Rubrica(String numeRubrica, Integer nr, List<Epic> e, List<Liric> l, List<Dramatic> d, List<Nonliterar> n) {
        this.numeRubrica = numeRubrica;
        this.nrCarti = nr;
        this.CartiE = e;
        this.CartiD = d;
        this.CartiL = l;
        this.CartiN = n;
    }

    public Rubrica() {
        CartiE = new ArrayList<>();
        CartiD = new ArrayList<>();
        CartiL = new ArrayList<>();
        CartiN = new ArrayList<>();
        this.numeRubrica = "Necunoscut";
    }

    Integer getNrCarti() {
        return nrCarti;
    }

    void setNrCarti(Integer nrCarti) {
        this.nrCarti = nrCarti;
    }

    String getNumeRubrica() {
        return numeRubrica;
    }

    void setNumeRubrica(String numeRubrica) {
        this.numeRubrica = numeRubrica;
    }

    public List<Epic> getCartiE() {
        return CartiE;
    }

    public void setCartiE(List<Epic> cartiE) {
        CartiE = cartiE;
    }

    public List<Liric> getCartiL() {
        return CartiL;
    }

    public void setCartiL(List<Liric> cartiL) {
        CartiL = cartiL;
    }

    public List<Dramatic> getCartiD() {
        return CartiD;
    }

    public void setCartiD(List<Dramatic> cartiD) {
        CartiD = cartiD;
    }

    public List<Nonliterar> getCartiN() {
        return CartiN;
    }

    public void setCartiN(List<Nonliterar> cartiN) {
        CartiN = cartiN;
    }

}
