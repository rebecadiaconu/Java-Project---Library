package pao.proiect.mainservice;

import pao.proiect.beings.*;
import java.util.Scanner;

public class MainAutor extends MainPersoana {

    // functie folosita pentru lucrul cu fisiere (Etapa 2)
    public void afiseaza(Autor a) {
        super.afiseaza(a);

        if (a.getNrCartiScrise() > 0) {
            System.out.print("Autorul are " + a.getNrCartiScrise() + " carti scrise.\n");

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

        return new Autor(p.getNume(), p.getPrenume(), nr);
    }
}
