package pao.proiect.beings;

import pao.proiect.books.Carte;
import pao.proiect.library.Comanda;

import java.util.ArrayList;
import java.util.List;

public class Client extends Persoana {
    private String CNP;
    private List<Comanda> comenzi;
    private Integer areCard;
    private Integer nrComenzi;

    public Client(String nume, String prenume, String CNP, Integer areCard, List<Comanda> comenzi, Integer nrComenzi) {
        super(nume, prenume);
        this.CNP = CNP;
        this.areCard = areCard;
        this.comenzi = comenzi;
        this.nrComenzi = nrComenzi;
    }

    public Client() {
        this.CNP = "Necunoscut";
        this.areCard = 0;
        this.comenzi = new ArrayList<>();
        this.nrComenzi = 0;
    }

    public String getCNP() {
        return CNP;
    }

    public void setCNP(String CNP) {
        this.CNP = CNP;
    }

    public Integer getAreCard() {
        return areCard;
    }

    public void setAreCard(Integer areCard) {
        this.areCard = areCard;
    }

    public List<Comanda> getComenzi() {
        return comenzi;
    }

    public void setComenzi(List<Comanda> comenzi) {
        this.comenzi = comenzi;
    }

    public Integer getNrComenzi() {
        return nrComenzi;
    }

    public void setNrComenzi(Integer nrComenzi) {
        this.nrComenzi = nrComenzi;
    }

    public Integer getNumarCartiCump() {
        Integer total = 0;
        if (getComenzi().size() > 0) {
            for (Comanda com : getComenzi()) {
                total += com.getCartiComandate().size();
            }
        }
        else {
            System.out.println("Clientul nu a efectual nicio comanda!");
        }

        return total;
    }
}
