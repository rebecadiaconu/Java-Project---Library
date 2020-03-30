package pao.proiect.beings;

import java.util.Scanner;

public class Client extends Persoana {
    private String CNP;
    private Integer cartiCumparate;
    private Integer areCard;

    public Client(String nume, String prenume, String anNastere, String anDeces, String sex, String CNP, Integer areCard, Integer cump) {
        super(nume, prenume, anNastere, anDeces, sex);
        this.CNP = CNP;
        this.areCard = areCard;
        this.cartiCumparate = cump;
    }

    public Client() {
        this.CNP = "Necunoscut";
        this.areCard = 0;
        this.cartiCumparate = 0;
    }

    public String getCNP() {
        return CNP;
    }

    public void setCNP(String CNP) {
        this.CNP = CNP;
    }

    public Integer getCartiCumparate() {
        return cartiCumparate;
    }

    public void setCartiCumparate(Integer cartiCumparate) {
        this.cartiCumparate = cartiCumparate;
    }

    public Integer getAreCard() {
        return areCard;
    }

    public void setAreCard(Integer areCard) {
        this.areCard = areCard;
    }

    public Client citeste() {
        Persoana p = super.citeste();
        String cnp;
        int card;
        Scanner sc = new Scanner(System.in);

        System.out.println("CNP-ul clientului este: ");
        cnp = sc.nextLine();
        System.out.println(p.getNume() + " " + p.getPrenume() + " are card Spectrum Library? 0 - Nu, 1 - Da");
        card = sc.nextInt();

        return new Client(p.getNume(), p.getPrenume(), p.getAnNastere(), p.getAnDeces(), p.getSex(), cnp, card, 0);
    }

    public void afiseaza(){
        super.afiseaza();
        System.out.println("Clientul " + this.getNume() + " " + this.getPrenume() + " are CNP-ul: " + this.getCNP());
        if (this.getAreCard() == 0) {
            System.out.println("Acesta nu are card al librariei. ");
        }
        else {
            System.out.println("Aceste detine un card al librariei. ");
        }

    }

}
