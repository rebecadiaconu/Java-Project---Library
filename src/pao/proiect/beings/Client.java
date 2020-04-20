package pao.proiect.beings;

import pao.proiect.library.Comanda;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Client extends Persoana {
    private String CNP;
    private List<Comanda> comenzi;
    private Integer areCard;

    public Client(String nume, String prenume, String sex, String CNP, Integer areCard, List<Comanda> comenzi) {
        super(nume, prenume, sex);
        this.CNP = CNP;
        this.areCard = areCard;
        this.comenzi = comenzi;
    }

    public Client() {
        this.CNP = "Necunoscut";
        this.areCard = 0;
        this.comenzi = new ArrayList<>();
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

    public Client citeste() {
        Persoana p = super.citeste();
        String cnp;
        int card;
        boolean ok;
        List<Comanda> comenzi = new ArrayList<>();
        Scanner sc = new Scanner(System.in);

        System.out.println("CNP-ul clientului este: ");
        cnp = sc.nextLine();
        ok = cnp.matches("[0-9]{13}");
        while (!ok) {
            System.out.println("CNP-ul introdus nu este in formatul corect! Va rugam sa incercati din nou: ");
            cnp = sc.nextLine();
            ok = cnp.matches("[0-9]{13}");
        }
        System.out.println(p.getNume() + " " + p.getPrenume() + " doreste card de membru al librariei noastre? 0 - Nu, 1 - Da");
        card = sc.nextInt();

        return new Client(p.getNume(), p.getPrenume(), p.getSex(), cnp, card, comenzi);
    }

    public void afiseaza() {
        super.afiseaza();
        System.out.println("Clientul " + this.getNume().toUpperCase() + " " + this.getPrenume().toUpperCase() + " are CNP-ul: " + this.getCNP());
        if (this.getAreCard() == 0) {
            System.out.println("Acesta nu are card al librariei. ");
        }
        else {
            System.out.println("Detine un card al librariei. ");
        }
        if (this.comenzi.size() > 0) {
            System.out.println("Acesta a efectuat " + this.comenzi.size() + " comenzi. ");
        }
        else {
            System.out.println("Nicio comanda efectuata! ");
        }

    }

}
