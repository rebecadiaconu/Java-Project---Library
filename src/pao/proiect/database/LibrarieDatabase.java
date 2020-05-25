package pao.proiect.database;

import pao.proiect.beings.Client;
import pao.proiect.books.Carte;
import pao.proiect.books.Literar;
import pao.proiect.books.Nonliterar;
import pao.proiect.connection.DatabaseConnection;
import pao.proiect.mainservice.MainClient;
import pao.proiect.mainservice.MainLiterar;
import pao.proiect.mainservice.MainNonliterar;
import pao.proiect.library.Librarie;
import pao.proiect.library.Rubrica;
import pao.proiect.sortari.AlfTitluComparator;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class LibrarieDatabase {
    MainLiterar literar = new MainLiterar();
    MainNonliterar nonliterar = new MainNonliterar();
    MainClient client = new MainClient();

    ClientDatabase clientD = new ClientDatabase();
    ComandaDatabase comanda = new ComandaDatabase();
    RubricaDatabase rubrica = new RubricaDatabase();
    CarteDatabase carte = new CarteDatabase();

    private static final String SHOW_LIBRARY = "SELECT * FROM librarie";
    private static final String GET_LIBRARIE = "SELECT * FROM librarie WHERE id_lib = ?";
    private static final String GET_RUB = "SELECT * FROM rubrica WHERE id_lib = ?";

    private static final String UPDATE_NUME = "UPDATE librarie SET nume = ? WHERE id_lib = ?";

    private static final String DELETE_LIB = "DELETE FROM librarie WHERE id_lib = ?";

    private static final String INSERT_STATEMENT_LIBRARIE = "INSERT INTO librarie (id_lib, nume, adresa) " + "VALUES (?, ?, ?)";

    public int nrLibrarii() {
        int count = 0;

        try (PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement(SHOW_LIBRARY)) {
            try (ResultSet result = statement.executeQuery()) {
                while (result.next()) {
                    count += 1;
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        return count;
    }

    public void afisareLibrarii() {
        if (nrLibrarii() != 0) {
            System.out.println("\nIn baza noastra de date se regasesc urmatoarele:");
            try (PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement(SHOW_LIBRARY)) {
                try (ResultSet result = statement.executeQuery()) {
                    while (result.next()) {
                        System.out.println(result.getString("nume") + ", " + result.getString("adresa") + " --> " + result.getInt("id_lib"));
                    }
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        } else {
            System.out.println("Nu avem nicio librarie in baza de date!");
        }
    }

    public void citesteLibrarie(Integer indexLib) {
        int nrRub;
        String nume;
        String adresa;
        List<Rubrica> rub = new ArrayList<>();
        List<Client> cl = new ArrayList<>();
        Scanner sc = new Scanner(System.in);

        System.out.println("Numele librariei: ");
        nume = sc.nextLine();
        System.out.println("Adresa acesteia (format: numele strazii + numar): ");
        adresa = sc.nextLine();
        System.out.println("In libraria dumneavoastra putem regasi carti din cate domenii? ");
        nrRub = sc.nextInt();
        sc.nextLine();
        for (int i = 0; i < nrRub; i++) {
            String indexRub = indexLib + Integer.toString(i);

            System.out.println("RUBRICA NOUA");
            String numeRubr;
            System.out.println("Introduceti numele rubricii: ");
            numeRubr = sc.nextLine();
            int nrCarti;
            System.out.println("Cate carti vreti sa introduceti? ");
            nrCarti = sc.nextInt();
            sc.nextLine();
            List<Carte> cc = new ArrayList<>();

            for (int j = 0; j < nrCarti; j++) {
                System.out.println("Carte noua!");
                Integer alegere;
                System.out.println("Cartea este text: 1 - literar, 2 - nonliterar ");
                alegere = sc.nextInt();
                sc.nextLine();
                String indexCarte = indexRub + j;
                if (alegere == 1) {
                    Literar newLit = literar.citeste(alegere);
                    indexCarte = indexCarte + "L";
                    carte.scrieLiterar(newLit, indexLib, indexRub, indexCarte);

                    cc.add(newLit);
                } else if (alegere == 2) {
                    Nonliterar newNonlit = nonliterar.citeste(alegere);
                    indexCarte = indexCarte + "N";
                    carte.scrieNonliterar(newNonlit, indexLib, indexRub, indexCarte);

                    cc.add(newNonlit);
                }
            }
            AlfTitluComparator alfC = new AlfTitluComparator<Carte>();
            cc.sort(alfC);

            Rubrica newRub = new Rubrica(numeRubr, nrCarti, cc);
            rubrica.scrieRubrica(newRub, indexLib, indexRub);

            rub.add(newRub);
        }

        audit("citesteLibrarie", new Date());
        Librarie newLib = new Librarie(nume, adresa, rub, cl);
        scrieLibrarie(newLib, indexLib);

    }

    public <T extends Carte> void adaugaCarte(Integer indexLib, T book, int alegere) {
        Scanner sc = new Scanner(System.in);

        List<String> opere = carte.foundBook(book.getTitlu(), book.getAutor().getNume(), book.getAutor().getPrenume(), indexLib, alegere);

        if (opere.size() != 0) {
            System.out.println("Aceasta carte exista deja in libraria noastra.");
        } else {
            rubrica.afisareRubrici(indexLib);
            int choice;
            String r;

            System.out.println("Doriti sa adaugati rubrica noua? 0 - Nu, 1 - Da");
            choice = sc.nextInt();
            sc.nextLine();

            if (choice == 1) {
                String numeRubr;
                System.out.println("Introduceti numele rubricii: ");
                numeRubr = sc.nextLine();
                String indexRub = Integer.toString(indexLib) + Integer.toString(rubrica.nrRubrici(indexLib));

                List<Carte> carti = new ArrayList<>();
                carti.add(book);
                Rubrica newRub = new Rubrica(numeRubr, 1, carti);
                rubrica.scrieRubrica(newRub, indexLib, indexRub);

                if (book.getTip() == 1) {
                    String indexCarte = indexRub + Integer.toString(carte.nrLiterar(indexLib, indexRub)) + "l";
                    carte.scrieLiterar((Literar) book, indexLib, indexRub, indexCarte);
                } else {
                    String indexCarte = indexRub + Integer.toString(carte.nrNonliterar(indexLib, indexRub)) + "n";
                    carte.scrieNonliterar((Nonliterar) book, indexLib, indexRub, indexCarte);
                }
            } else {
                System.out.println("In care dintre rubrici ati dori sa o adaugati?");
                System.out.println("Introduceti id-ul corespunzator!");

                String index = sc.nextLine();

                if (book.getTip() == 1) {
                    String indexCarte = index + Integer.toString(carte.nrLiterar(indexLib, index) + carte.nrNonliterar(indexLib, index)) + "L";
                    carte.scrieLiterar((Literar) book, indexLib, index, indexCarte);
                } else {
                    String indexCarte = index + Integer.toString(carte.nrLiterar(indexLib, index) + carte.nrNonliterar(indexLib, index)) + "N";
                    carte.scrieNonliterar((Nonliterar) book, indexLib, index, indexCarte);
                }
            }
        }

        audit("adaugaCarte", new Date());
    }

    public void comanda(Integer m, Integer indexLib) {
        int nrComanda;
        Integer numOfBooks = m;
        Integer metodaPlata = -1;
        String metodaPlt = null;
        double pretComanda = 0;
        Random rand = new Random();
        Scanner sc = new Scanner(System.in);

        Client cNou = client.citeste();
        String idClient = Integer.toString(indexLib) + clientD.nrClienti(indexLib);
        if (clientD.existOrNot(cNou.getCNP()) == 0) {
            clientD.scrieClient(cNou, idClient, indexLib);
        }
        else {
            clientD.updateClient(cNou.getCNP());
            idClient = clientD.getId(cNou.getCNP());
        }

        nrComanda = rand.nextInt(1000000);

        while (numOfBooks > 0) {
            String titlu;
            String nume;
            String prenume;

            System.out.println("Titlul cartii: ");
            titlu = sc.nextLine();
            System.out.println("Numele de familie al autorului: ");
            nume = sc.nextLine();
            System.out.println("Prenumele autorului: ");
            prenume = sc.nextLine();

            String idCarte = carte.getCodCarte(titlu, nume, prenume, indexLib);
            String indexRub = idCarte.substring(0, 2);
            String fel = idCarte.substring(3, 4);


            if (idCarte.equals("lala")) {
                System.out.println("Carte indisponibila momentan!");
            } else {
                int ok;
                int howMany = 0;
                System.out.println("Doriti varianta digitala a cartii sau cea fizica? 1 - Digitala, 0 - Fizica");
                ok = sc.nextInt();
                sc.nextLine();
                System.out.println("Cate astfel de carti doriti? Din totalul de " + m + " mai aveti: " + numOfBooks);
                howMany = sc.nextInt();
                sc.nextLine();

                if (ok == 1) {
                    // varianta digitala
                    pretComanda += howMany * carte.getPretCarte(titlu, nume, prenume, indexLib, 1);
                } else {
                    // varianta fizica
                    pretComanda += howMany * carte.getPretCarte(titlu, nume, prenume, indexLib, 2);
                    carte.setNrExempl(indexLib, indexRub, idCarte, howMany, fel);
                }

                numOfBooks -= howMany;
                comanda.scrieCarteComandata(indexLib, idClient, idCarte, Integer.toString(nrComanda), howMany);
                carte.setExemplCump(indexLib, indexRub, idCarte, howMany, fel);
            }
        }

        System.out.println("Metoda de plata: 1 - card, 2 - ramburs la livrare");
        metodaPlata = sc.nextInt();
        sc.nextLine();
        if (metodaPlata == 1) {
            metodaPlt = "Card";
        } else if (metodaPlata == 2) {
            metodaPlt = "Ramburs la livrare";
        }
        if (clientD.getCard(cNou.getCNP()) == 1) {
            pretComanda -= 0.1 * pretComanda;
        }

        comanda.scrieComanda(indexLib, idClient, Integer.toString(nrComanda), metodaPlt, pretComanda);

        comanda.detaliiComanda(Integer.toString(nrComanda));
        System.out.println("Multumim ca ati cumparat de la noi!");
        audit("comandaCarti", new Date());
    }

    public void afisareLibrarie(Integer indexLib) {
        if (rubrica.nrRubrici(indexLib) != 0) {

            try (PreparedStatement statementLib = DatabaseConnection.getInstance().getConnection().prepareStatement(GET_LIBRARIE)) {
                statementLib.setInt(1, indexLib);

                try (ResultSet resultLib = statementLib.executeQuery()) {
                    while (resultLib.next()) {
                        System.out.println("Nume librarie: " + resultLib.getString("nume"));
                        System.out.println("Adresa librarie: " + resultLib.getString("adresa"));
                        System.out.println("La noi gasiti carti din urmatoarele categorii: ");

                        try (PreparedStatement statementRub = DatabaseConnection.getInstance().getConnection().prepareStatement(GET_RUB)) {
                            statementRub.setInt(1, indexLib);

                            try (ResultSet resultRub = statementRub.executeQuery()) {
                                while (resultRub.next()) {
                                    System.out.println("\nIn rubrica " + resultRub.getString("nume").toUpperCase() + " avem: ");
                                    String indexRub = resultRub.getString("id_rub");

                                    int nrCarti = carte.nrLiterar(indexLib, indexRub) + carte.nrNonliterar(indexLib, indexRub);
                                    int index = 0;

                                    while (index < nrCarti) {
                                        int ok = 0;
                                        String indexCarte = indexRub + Integer.toString(index) + "L";
                                        if (carte.afiseaza(indexLib, indexRub, indexCarte, 1)) {
                                            ok = 1;
                                        }

                                        if (ok == 0) {
                                            indexCarte = indexRub + Integer.toString(index) + "N";
                                            if (carte.afiseaza(indexLib, indexRub, indexCarte, 2)) {
                                                ok = 1;
                                            }
                                        }

                                        index += 1;
                                    }

                                }
                            }
                        } catch (Exception e) {
                            System.out.println(e);
                        }
                    }
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        } else {
            System.out.println("Nu este nimic inregistrat in aceasta librarie!");
        }

        audit("afisareLibrarie", new Date());
    }


    public void scrieLibrarie(Librarie library, Integer indexLib) {
        try (PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement(INSERT_STATEMENT_LIBRARIE)) {
            statement.setInt(1, indexLib);
            statement.setString(2, library.getNume());
            statement.setString(3, library.getAdresa());

            int rowInserted = statement.executeUpdate();
            if (rowInserted > 0) {
                System.out.println("Librarie adaugata cu succes!");
                audit("adaugareLibrarie", new Date());

            }
        } catch (SQLException e) {
            System.out.println("Nu am putut adauga libraria: " + e.getMessage());
        }
    }

    public void updateNume(String nume, Integer indexLib) {
        try (PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement(UPDATE_NUME)) {
            statement.setString(1, nume);
            statement.setInt(2, indexLib);

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Librarie modificata cu succes!");
                audit("modificareNumeLibrarie", new Date());
            }
        } catch (SQLException e) {
            System.out.println("Ceva nu a mers cand am incercat sa modificam libraria: " + e.getMessage());
        }
    }

    public boolean deleteLib(Integer indexLib) {
        try (PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement(DELETE_LIB)) {
            statement.setInt(1, indexLib);

            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                audit("stergereLibrarie", new Date());
                return true;

            }
        } catch (SQLException e) {
            System.out.println("Ceva nu a mers in incercarea de stergere a librariei: " + e.getMessage());
            return false;
        }

        System.out.println("Nu am gasit libraria cautata!");
        return false;
    }

    public void audit(String nume, Date data) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("audit.csv", true))) {
            bufferedWriter.write(nume + ',' + data + '\n');
        } catch (IOException e) {
            System.out.println("Could not write data to file: " + e.getMessage());
        }
    }
}



