package pao.proiect.mainservice;

import javafx.util.Pair;
import pao.proiect.books.Carte;
import pao.proiect.library.Comanda;

public class MainComanda {

    // functie folosita pentru lucrul cu fisiere (Etapa 2)
    public void afisareComanda(Comanda c) {
        System.out.println("Numar comanda: " + c.getNrComanda());
        System.out.println("Numar total carti comandate: " + c.cartiComanda());
        System.out.println("Continut comanda: ");
        for (Pair<Carte, Integer> cc : c.getCartiComandate()) {
            System.out.println("Cartea " + cc.getKey().getTitlu() + " este scrisa de " + cc.getKey().getAutor().getNume() + " " + cc.getKey().getAutor().getPrenume() + ".");
            System.out.println("Disponibila in: " + cc.getKey().getLimba());
            System.out.println("Pret varianta digitala: " + cc.getKey().getPretD());
            System.out.println("Pret varianta fizica: " + cc.getKey().getPretF());
            System.out.println("Numar exemplare carte: " + cc.getValue());
        }
        System.out.println("Sumar comanda: " + c.getPretComanda());
        System.out.println("Metoda de plata: " +  c.getMetodaPlata());
    }
}
