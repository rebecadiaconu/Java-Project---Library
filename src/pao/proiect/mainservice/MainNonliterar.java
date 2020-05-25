package pao.proiect.mainservice;

import pao.proiect.books.Carte;
import pao.proiect.books.Nonliterar;
import java.util.Scanner;

public class MainNonliterar extends MainCarte {

    public Nonliterar citeste(Integer index) {
        int alegere;
        String specie;
        String tipLit;
        Scanner sc = new Scanner(System.in);

        Carte c = super.citeste(index);

        System.out.println("Opera introdusa face parte din urmatoarea specie: \n1. Jurnal si memorii \n2. Enciclopedie \n3. Articol");
        alegere = sc.nextInt();
        sc.nextLine();
        if (alegere == 1) {
            specie = "Jurnal si memorii";
            tipLit = "Autobiografic";
        }
        else if (alegere == 2) {
            specie = "Enciclopedie";
            tipLit = "Informativ";
        }
        else {
            specie = "Articol";
            tipLit = "Informativ";
        }

        return new Nonliterar(c.getTitlu(), c.getAutor(), c.getLimba(), c.getCuvCheie(), c.getTip(), c.getNrExemplare(), c.getNrPagini(), c.getExemCumparate(), 0, 0, specie, tipLit);
    }

    // functie folosita pentru lucrul cu fisiere (Etapa 2)
    public void afisare(Nonliterar n) {
        super.afiseaza(n);
        System.out.println("Opera face parte din specia: " + n.getSpecie().toLowerCase() + " si scopul acesteia este unul " + n.getTiptext().toLowerCase());
    }
}
