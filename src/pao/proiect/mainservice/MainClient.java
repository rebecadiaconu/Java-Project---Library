package pao.proiect.mainservice;

import pao.proiect.beings.Client;
import pao.proiect.beings.Persoana;
import pao.proiect.library.Comanda;;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainClient extends MainPersoana {

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

        return new Client(p.getNume(), p.getPrenume(), cnp, card, comenzi, 0);
    }

    // functie folosita pentru lucrul cu fisiere (Etapa 2)
    public void afiseaza(Client c) {
        super.afiseaza(c);
        System.out.println("Clientul " + c.getNume().toUpperCase() + " " + c.getPrenume().toUpperCase() + " are CNP-ul: " + c.getCNP());
        if (c.getAreCard() == 0) {
            System.out.println("Acesta nu are card al librariei. ");
        }
        else {
            System.out.println("Detine un card al librariei. ");
        }
        if (c.getComenzi().size() > 0) {
            System.out.println("Acesta a efectuat " + c.getComenzi().size() + " comenzi. ");
        }
        else {
            System.out.println("Nicio comanda efectuata! ");
        }

    }
}
