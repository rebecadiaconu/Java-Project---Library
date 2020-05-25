package pao.proiect.mainservice;

import pao.proiect.beings.Autor;
import pao.proiect.books.Carte;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class MainCarte {

    public Carte citeste(Integer index) {
        int nrPag, nrExempl;
        String aux1;
        String aux2;
        String aux3;
        String titlu;
        MainAutor a = new MainAutor();
        Scanner sc = new Scanner(System.in);

        System.out.println("Introduceti titlul cartii: ");
        aux1 = sc.nextLine();
        titlu = aux1.substring(0, 1).toLowerCase() + aux1.substring(1).toLowerCase();
        System.out.println("Detalii autor: ");
        Autor nou = a.citeste();
        System.out.println("Numar pagini carte: ");
        nrPag = sc.nextInt();
        System.out.println("Numar exemplare: ");
        nrExempl = sc.nextInt();
        sc.nextLine();
        System.out.println("Cartea este disponibila in limbile (separate prin VIRGULA): ");
        aux2 = sc.nextLine();
        String[] arrr = aux2.split(",");
        List<String> limba = new ArrayList<>(Arrays.asList(arrr));
        System.out.println("Introduceti cateva cuvinte cheie ce descriu opera (separate prin VIRGULA): ");
        aux3 = sc.nextLine();
        String[] arr = aux3.split(",");
        List<String> cuvCheie = new ArrayList<>(Arrays.asList(arr));

        return new Carte(titlu, nou, limba, cuvCheie, index, nrExempl, nrPag, 0, 0, 0);
    }

    // functie folosita pentru lucrul cu fisiere (Etapa 2)
    public void afiseaza(Carte c) {
        System.out.println("Cartea " + c.getTitlu() + " este scrisa de " + c.getAutor().getNume() + " " + c.getAutor().getPrenume() + ".");
        System.out.println("Disponibila in: " + c.getLimba());
        System.out.println("Numar pagini: " + c.getNrPagini());
        System.out.println("Numar de exemplare: " + c.getNrExemplare());
        System.out.println("Numar exemplare cumparate: " + c.getExemCumparate());
        System.out.println("Pret varianta digitala: " + c.getPretD());
        System.out.println("Pret varianta fizica: " + c.getPretF());
    }
}
