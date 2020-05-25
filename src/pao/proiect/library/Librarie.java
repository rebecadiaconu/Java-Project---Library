package pao.proiect.library;

import java.util.*;
import pao.proiect.beings.Client;

public class Librarie {
    private String nume;
    private String adresa;
    private List<Rubrica> rubrici;
    private List<Client> clienti;

    public Librarie(String nume, String adresa, List<Rubrica> rubrici, List<Client> clienti) {
        this.nume = nume;
        this.adresa = adresa;
        this.rubrici = rubrici;
        this.clienti = clienti;
    }

    public Librarie() {
        this.nume = "Necunoscut";
        this.adresa= "Necunoscuta";
        this.rubrici = new ArrayList<>();
        this.clienti = new ArrayList<>();
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public List<Rubrica> getRubrici() {
        return this.rubrici;
    }

    public void setRubrici(List<Rubrica> rubrici) {
        this.rubrici = rubrici;
    }

    public List<Client> getClienti() {
        return this.clienti;
    }

    public void setClienti(List<Client> clienti) {
        this.clienti = clienti;
    }

}
