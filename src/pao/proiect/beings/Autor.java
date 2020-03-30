package pao.proiect.beings;

import pao.proiect.books.Carte;

import java.util.Scanner;

public class Autor extends Persoana {
    private int nrCartiScrise;

    public Autor(String nume, String prenume, String anNastere, String anDeces, String sex, int nrCartiScrise) {
        super(nume, prenume, anNastere, anDeces, sex);
        this.nrCartiScrise = nrCartiScrise;
    }

    public Autor() {
        super();
        this.nrCartiScrise = 0;
    }

    public int getNrCartiScrise() {
        return nrCartiScrise;
    }

    public void setNrCartiScrise(int nrCartiScrise) {
        this.nrCartiScrise = nrCartiScrise;
    }

    public void afiseaza(){
        super.afiseaza();

        if (this.nrCartiScrise > 0) {
            System.out.print("Autorul are " + this.nrCartiScrise + " carti scrise.\n");

        }
        else {
            System.out.print("Numarul de carti scrise de autor este necunoscut. \n");
        }

    }

    public Autor citeste() {
        Persoana p = super.citeste();
        int nr;
        Scanner sc = new Scanner(System.in);

        System.out.println("Cate opere a conceput autorul? ");
        nr = sc.nextInt();
        sc.nextLine();

        return new Autor(p.getNume(), p.getPrenume(), p.getAnNastere(), p.getAnDeces(), p.getSex(), nr);
    }

}
