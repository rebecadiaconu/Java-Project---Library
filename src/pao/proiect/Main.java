package pao.proiect;

import pao.proiect.books.*;
import pao.proiect.database.*;
import pao.proiect.library.Rubrica;
import pao.proiect.mainservice.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException, SQLException {

        LibrarieDatabase serviceLibrarie = new LibrarieDatabase();
        ClientDatabase serviceClient = new ClientDatabase();
        ComandaDatabase serviceComanda = new ComandaDatabase();
        CarteDatabase serviceCarte = new CarteDatabase();
        RubricaDatabase serviceRub = new RubricaDatabase();

        MainNonliterar nonlit = new MainNonliterar();
        MainLiterar lit = new MainLiterar();

        int choose = 0;
        int indexLib = 0;
        Scanner sc = new Scanner(System.in);

        while(true) {
            if (choose == 0) {
                if (serviceLibrarie.nrLibrarii() == 0) {
                    System.out.println("Nicio librarie nu este inregistrata in baza de date!");
                    System.out.println("Va rugam sa introduceti una!");
                    indexLib = 0;
                    serviceLibrarie.citesteLibrarie(0);
                }
                else {
                    System.out.println("Lista librariilor din baza noastra de date!");
                    serviceLibrarie.afisareLibrarii();
                    System.out.println("Daca doriti sa adaugati librarie noua --> tastati -1");

                    System.out.println("\nVa rog sa introduceti numarul corespunzator optiunii alese!");
                    indexLib = sc.nextInt();
                    sc.nextLine();

                    if (indexLib == -1) {
                        indexLib = serviceLibrarie.nrLibrarii();
                        serviceLibrarie.citesteLibrarie(indexLib);
                    }
                }
                choose = 1;
            }

            System.out.println();
            System.out.println("Doriti sa realizati actiuni ca? \n1. Manager librarie \n2. Client");
            System.out.println("3. Doriti sa iesiti din meniu.");
            int alegere = sc.nextInt();
            if (alegere == 1) {
                System.out.println("Actiuni posibile:");
                System.out.println("1. Adaugare carte in stoc");
                System.out.println("2. Afla cine este cel mai devotat client la momentul actual! ");
                System.out.println("3. Afisare detalii comanda!");
                System.out.println("4. Modificati numele librariei.");
                System.out.println("5. Modificati numele unei rubrici.");
                System.out.println("6. Modificati numele unui client.");
                System.out.println("7. Modificati pretul unei carti.");
                System.out.println("8. Stergeti o rubrica.");
                System.out.println("9. Stergeti o carte.");
                System.out.println("10. Stergeti un client.");
                System.out.println("11. Stergeti libraria!");
                System.out.println("12. Doriti sa iesiti din modul manager");
                int choise = sc.nextInt();
                sc.nextLine();
                switch(choise) {
                    case 1:
                        int index;
                        Carte c = new Carte();
                        System.out.println("Cartea este un text: 1 - literar, 2 - nonliterar");
                        index = sc.nextInt();
                        sc.nextLine();
                        if (index == 1) {
                            c = lit.citeste(index);
                        }
                        else if (index == 2) {
                            c = nonlit.citeste(index);
                        }
                        serviceLibrarie.adaugaCarte(indexLib, c, c.getTip());
                        break;
                    case 2:
                        serviceClient.clientDevotat(indexLib);
                        break;
                    case 3:
                        String numarComanda;
                        System.out.println("Introduceti numarul comenzii (maximum 6 cifre): ");
                        numarComanda = sc.nextLine();
                        serviceComanda.detaliiComanda(numarComanda);
                        break;
                    case 4:
                        int opt;
                        System.out.println("Daca v-ati razgandit, tastati -1. Daca nu, orice altceva.");
                        opt = sc.nextInt();
                        sc.nextLine();
                        if (opt != -1) {
                            System.out.println("Introduceti noul nume: ");
                            String nume = sc.nextLine();
                            serviceLibrarie.updateNume(nume, indexLib);
                        }
                        break;
                    case 5:
                        serviceRub.afisareRubrici(indexLib);
                        System.out.println("Introduceti id-ul corespunzator rubricii pe care doriti sa o modificati!");
                        System.out.println("Daca v-ati razgandit, tastati -1.");
                        String option = sc.nextLine();
                        if (!option.equals("-1")) {
                            System.out.println("Introduceti noul nume: ");
                            String nume = sc.nextLine();
                            serviceRub.updateNume(nume, option);
                        }
                        break;
                    case 6:
                        serviceClient.afisareClienti(indexLib);
                        System.out.println("Introduceti id-ul corespunzator clientului pe care doriti sa-l modificati!");
                        System.out.println("Daca v-ati razgandit, tastati -1.");
                        String optionCln = sc.nextLine();
                        if (!optionCln.equals("-1")) {
                            String indexClient = optionCln;
                            System.out.println("Nume nou (daca nu il modificati, introduceti-l pe cel vechi): ");
                            String numeCln = sc.nextLine();
                            System.out.println("Prenume nume (daca nu il modificati, introduceti-l pe cel vechi): ");
                            String prenumeCln = sc.nextLine();
                            serviceClient.updateNume(numeCln, prenumeCln, indexClient);
                        }
                        break;
                    case 7:
                        serviceCarte.afiseazaCarti(indexLib);
                        System.out.println("Introduceti id-ul corespunzator cartii al carei pret doriti sa-l modificati!");
                        System.out.println("Daca v-ati razgandit, tastati -1.");
                        String optionBook = sc.nextLine();
                        if (!optionBook.equals("-1")) {
                            String indexCarte = optionBook;
                            serviceCarte.setPret(indexCarte);
                        }
                        break;
                    case 8:
                        serviceRub.afisareRubrici(indexLib);
                        System.out.println("Introduceti id-ul corespunzator rubricii dorite!");
                        String indexRub = sc.nextLine();
                        System.out.println("Stergeti continutul intregii rubrici? 1 - Da, 0 - Nu");
                        int optRub = sc.nextInt();
                        sc.nextLine();
                        if (optRub == 1) {
                            if (serviceRub.deleteRubrica(indexRub)) {
                                serviceCarte.deleteRub(indexRub);
                                System.out.println("Rubrica stearsa in totalitate!");
                            }
                        }
                        break;
                    case 9:
                        System.out.println("TITLU, NUME AUTOR --> INDEX CARTE");
                        serviceCarte.afiseazaCarti(indexLib);
                        System.out.println("Introduceti titlul cartii: ");
                        String titlu = sc.nextLine();
                        System.out.println("Nume autor (DOAR NUME DE FAMILIE): ");
                        String nume = sc.nextLine();
                        System.out.println("Prenume autor: ");
                        String prenume = sc.nextLine();
                        if (serviceCarte.deleteBook(titlu, nume, prenume)) {
                            break;
                        }
                        break;
                    case 10:
                        serviceClient.afisareClienti(indexLib);
                        System.out.println("Introduceti id-ul corespunzator clientului pe care doriti sa-l stergeti.");
                        System.out.println("Daca v-ati razgandit, tastati -1.");
                        String optCln = sc.nextLine();
                        if (!optCln.equals("-1")) {
                            if (serviceClient.deleteClient(optCln)) {
                                if (serviceComanda.deleteComanda(optCln)) {
                                    serviceComanda.deleteCarte(optCln);
                                }
                                System.out.println("Client sters cu succes!");
                            }
                        }
                        break;
                    case 11:
                        System.out.println("Sigur stergeti intregul continut al librariei? 0 - Nu, 1 - Da");
                        int optLib = sc.nextInt();

                        if (optLib == 1) {
                            if (serviceLibrarie.deleteLib(indexLib)) {
                                if (serviceRub.deleteLib(indexLib)) {
                                    if (serviceCarte.deleteLib(indexLib)) {
                                        System.out.println("Rubrici sterse cu succes!");
                                    }
                                }
                                if (serviceClient.deleteLib(indexLib)) {
                                    if (serviceComanda.deleteLib(indexLib)) {
                                        serviceComanda.deleteCarteLib(indexLib);
                                    }
                                    System.out.println("Clienti stersi cu succes!");
                                }
                                System.out.println("Librarie stearsa cu succes!");
                                choose = 0;
                            }
                        }

                    case 12:
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
                        serviceCarte.cautaDupaTitlu(titlu, indexLib);
                        break;
                    case 2:
                        String cuv;
                        System.out.println("Introduceti cuvintele dupa care doriti sa faceti cautarea (separate prin VIRGULA)");
                        cuv = sc.nextLine();
                        serviceCarte.cuvCheie(cuv, indexLib);
                        break;
                    case 3:
                        serviceLibrarie.afisareLibrarie(indexLib);
                        break;
                    case 4:
                        String nume;
                        String prenume;
                        Set<String> result = new HashSet<>();
                        System.out.println("Numele autorului (DOAR numele DE FAMILIE):");
                        nume = sc.nextLine();
                        System.out.println("Prenumele autorului: ");
                        prenume = sc.nextLine();

                        serviceCarte.opereAutor(nume, prenume, indexLib);

                        break;
                    case 5:
                        String limba;
                        System.out.println("In functie de ce limba doriti sa faceti cautarea? ");
                        limba = sc.nextLine();
                        serviceCarte.limbDispon(limba, indexLib);
                        break;
                    case 6:
                        String titluCarte;
                        String numeAut;
                        String prenumeAut;
                        System.out.println("Titlul cartii: ");
                        titluCarte = sc.nextLine();
                        System.out.println("Numele autorului (DOAR numele DE FAMILIE):");
                        numeAut = sc.nextLine();
                        System.out.println("Prenumele autorului: ");
                        prenumeAut = sc.nextLine();

                        serviceCarte.detaliiCarte(titluCarte, numeAut, prenumeAut, indexLib, 1);
                        serviceCarte.detaliiCarte(titluCarte, numeAut, prenumeAut, indexLib, 2);
                        break;
                    case 7:
                        int carti;
                        System.out.println("Cate carti doriti sa comandati? ");
                        carti = sc.nextInt();
                        sc.nextLine();
                        serviceLibrarie.comanda(carti, indexLib);
                        break;
                    case 8:
                        String CNP;
                        System.out.println("Va rog introduceti-va CNP-ul in formatul corect: ");
                        CNP = sc.nextLine();
                        int nr = serviceClient.nrComenziDate(CNP);
                        if (nr != 0) {
                            System.out.println("Numar comenzi date: " + nr);
                        }
                        break;
                    case 9:
                        serviceCarte.getBestseller(indexLib);
                        break;
                    case 10:
                        String numarComanda;
                        System.out.println("Introduceti numarul comenzii (maximum 6 cifre): ");
                        numarComanda = sc.nextLine();
                        serviceComanda.detaliiComanda(numarComanda);
                        break;
                    case 11:
                        break;
                }
            }
            if (alegere == 3) {
                break;
            }
        }

    }
}
