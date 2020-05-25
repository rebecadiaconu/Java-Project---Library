package pao.proiect.mainservice;

import pao.proiect.books.Carte;
import pao.proiect.books.Literar;

import java.util.Scanner;

public class MainLiterar extends MainCarte {


    public Literar citeste(Integer index) {
        int alegere;
        String specie = "";
        String genLit = "";
        Scanner sc = new Scanner(System.in);

        Carte c = super.citeste(index);

        System.out.println("Opera introdusa face parte din urmatoarea specie: \n1. Roman \n2. Basm \n3. Nuvela \n4. Teatru si schite \n5. Poezie \n6. Balada \n7. Imn");
        alegere = sc.nextInt();
        sc.nextLine();
        if (alegere == 1) {
            specie = "Roman";
            genLit = "Epic";
        }
        else if (alegere == 2) {
            specie = "Basm";
            genLit = "Epic";
        }
        else if (alegere == 3) {
            specie = "Nuvela";
            genLit = "Epic";
        }
        else if (alegere == 4) {
            specie = "Teatru si schite";
            genLit = "Dramatic";
        }
        else if (alegere == 5) {
            specie = "Poezie";
            genLit = "Liric";
        }
        else if (alegere == 6) {
            specie = "Balada";
            genLit = "Liric";
        }
        else if (alegere == 7) {
            specie = "Imn";
            genLit = "Liric";
        }

        return new Literar(c.getTitlu(), c.getAutor(), c.getLimba(), c.getCuvCheie(), c.getTip(), c.getNrExemplare(), c.getNrPagini(), c.getExemCumparate(), 0, 0, specie, genLit);
    }

    // functie folosita pentru lucrul cu fisiere (Etapa 2)
    public void afisare(Literar l) {
        super.afiseaza(l);
        System.out.println("Opera face parte din specia: " + l.getSpecie().toUpperCase() + " si apartine genului " + l.getGenLit().toLowerCase());
    }
}
