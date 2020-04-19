package pao.proiect;

import pao.proiect.beings.Client;
import pao.proiect.books.*;
import pao.proiect.library.Comanda;
import pao.proiect.library.Librarie;
import pao.proiect.library.Rubrica;
import pao.proiect.persistence.Persistance;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        Persistance service = Persistance.getInstance();
        Scanner sc = new Scanner(System.in);

        Client cl = new Client();
        Comanda com = new Comanda();
        Literar lit = new Literar();
        Nonliterar nonlit = new Nonliterar();
        Rubrica r = new Rubrica();
        Librarie ob = new Librarie();
        List<Librarie> l = new ArrayList<>();

        BufferedReader verifLib = new BufferedReader(new FileReader("librarie.csv"));
        if (verifLib.readLine() != null) {
            l = Persistance.citireDinFisier(ob);
            ob = l.get(0);
        }


        while(true) {
            System.out.println();
            System.out.println("Doriti sa realizati actiuni ca? \n1. Manager librarie \n2. Client");
            System.out.println("3. Doriti sa iesiti din meniu.");
            int alegere = sc.nextInt();
            if (alegere == 1) {
                System.out.println("Actiuni posibile:");
                System.out.println("1. Adaugare carte in stoc");
                System.out.println("2. Modificare pret carte ");
                System.out.println("3. Afla cine este cel mai devotat client la momentul actual! ");
                System.out.println("4. Afisare detalii comanda!");
                System.out.println("5. Doriti sa iesiti din modul manager");
                int choise = sc.nextInt();
                sc.nextLine();
                switch(choise) {
                    case 1:
                        Integer index;
                        Carte c = new Carte();
                        System.out.println("Cartea este un text: 1 - literar, 2 - nonliterar");
                        index = sc.nextInt();
                        sc.nextLine();
                        if (index == 1) {
                            c = new Literar().citeste(index);
                        }
                        else if (index == 2) {
                            c = new Nonliterar().citeste(index);
                        }
                        ob.adaugaCarte(index, c);
                        break;
                    case 2:
                        String nume, autor;
                        System.out.println("Titlul cartii al carei pret doriti sa-l modificati: ");
                        nume = sc.nextLine();
                        System.out.println("Numele autorului (numele si prenumele separat printr-un spatiu):");
                        autor = sc.nextLine();
                        ob.modificarePret(nume, autor);
                        break;
                    case 3:
                        ob.celMaiDevotatClient();
                        break;
                    case 4:
                        String numarComanda;
                        System.out.println("Introduceti numarul comenzii (maximum 6 cifre): ");
                        numarComanda = sc.nextLine();
                        ob.detaliiComanda(numarComanda);
                        break;
                    case 5:
                        break;
                }
            }
            if (alegere == 2) {
                System.out.println("Actiuni posibile: ");
                System.out.println("1. Cautare carte dupa titlu");
                System.out.println("2. Cautare carte dupa cuvinte cheie");
                System.out.println("3. Afisare a continutului librariei");
                System.out.println("4. Afisare creatii autor");
                System.out.println("5. Afisare opere disponibile intr-o anumita limba");
                System.out.println("6. Afisare detalii carte");
                System.out.println("7. Comanda!");
                System.out.println("8. Cate comenzi am de la aceasta librarie? ");
                System.out.println("9. Afisare Bestseller!");
                System.out.println("10. Afisare detalii comanda!");
                System.out.println("11. Doriti sa iesiti din modul client");
                int choise = sc.nextInt();
                sc.nextLine();
                switch(choise) {
                    case 1:
                        String titlu;
                        System.out.println("Introduceti titlul cartii: ");
                        titlu = sc.nextLine();
                        ob.cautaDupaTitlu(titlu);
                        break;
                    case 2:
                        String cuv;
                        System.out.println("Introduceti cuvintele dupa care doriti sa faceti cautarea (separate prin VIRGULA)");
                        cuv = sc.nextLine();
                        ob.cautaDupaCuvCheie(cuv);
                        break;
                    case 3:
                        ob.afisareLibrarie();
                        break;
                    case 4:
                        String autor;
                        Set<String> result = new HashSet<>();
                        System.out.println("Introduceti numele si prenumele autorului (separate prin spatiu): ");
                        autor = sc.nextLine();
                        result = ob.opereAutor(autor);
                        if (result.size() > 0) {
                            for (String s : result) {
                                System.out.println(s);
                            }
                        }
                        else {
                            System.out.println("Nu am gasit nicio carte scrisa de autorul dat!");
                        }

                        break;
                    case 5:
                        String limba;
                        System.out.println("In functie de ce limba doriti sa faceti cautarea? ");
                        limba = sc.nextLine();
                        ob.opereDisponLimba(limba);
                        break;
                    case 6:
                        String name1, name2;
                        System.out.println("Titlul cartii: ");
                        name1 = sc.nextLine();
                        System.out.println("Numele autorului (numele si prenumele separat printr-un spatiu):");
                        name2 = sc.nextLine();
                        ob.detaliiCarte(name1, name2);
                        break;
                    case 7:
                        Integer carti;
                        System.out.println("Cate carti doriti sa comandati? ");
                        carti = sc.nextInt();
                        sc.nextLine();
                        ob.Comanda(carti);
                        break;
                    case 8:
                        String CNP;
                        System.out.println("Va rog introduceti-va CNP-ul in formatul corect: ");
                        CNP = sc.nextLine();
                        ob.numarComenziDate(CNP);
                        break;
                    case 9:
                        ob.getBestSeller();
                        break;
                    case 10:
                        String numarComanda;
                        System.out.println("Introduceti numarul comenzii (maximum 6 cifre): ");
                        numarComanda = sc.nextLine();
                        ob.detaliiComanda(numarComanda);
                        break;
                    case 11:
                        break;
                }
            }
            if (alegere == 3) {
                l.set(0, ob);
                Persistance.scriereInFisier(cl, l);
                Persistance.scriereInFisier(lit, l);
                Persistance.scriereInFisier(nonlit, l);
                Persistance.scriereInFisier(com, l);
                Persistance.scriereInFisier(r, l);
                Persistance.scriereInFisier(ob, l);
                break;
            }
        }

    }
}
