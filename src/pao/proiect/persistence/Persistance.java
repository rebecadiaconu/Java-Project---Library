package pao.proiect.persistence;

import javafx.util.Pair;
import pao.proiect.beings.Autor;
import pao.proiect.beings.Client;
import pao.proiect.books.Carte;
import pao.proiect.books.Literar;
import pao.proiect.books.Nonliterar;
import pao.proiect.library.Comanda;
import pao.proiect.library.Librarie;
import pao.proiect.library.Rubrica;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Persistance {
    private static Persistance libraryService = null;
    private Persistance() {
    }

    public static Persistance getInstance() {
        if (libraryService == null) {
            libraryService = new Persistance();
        }

        return libraryService;
    }

    public static <T> List<T> citireDinFisier(T ob) {
        List<T> listObj = new ArrayList<>();

        if (ob instanceof Librarie) {
            try (BufferedReader bufferedReader = new BufferedReader(new FileReader("librarie.csv"))) {
                String currLine;

                while ((currLine = bufferedReader.readLine()) != null) {
                    String[] dataFields = currLine.split(",");
                    List<Rubrica> rubrici = new ArrayList<>();
                    List<Client> clienti = new ArrayList<>();

                    try (BufferedReader bufferedReaderRub = new BufferedReader(new FileReader("rubrici.csv"))) {
                        String currLineRub;

                        while ((currLineRub = bufferedReaderRub.readLine()) != null) {
                            String[] dataFieldsRub = currLineRub.split(",");

                            if (Integer.parseInt(dataFields[0]) == Integer.parseInt(dataFieldsRub[0])) {
                                List<Carte> carti = new ArrayList<>();

                                try (BufferedReader bufferedReaderLit = new BufferedReader(new FileReader("literar.csv"))) {
                                    String currLineLit;

                                    while((currLineLit = bufferedReaderLit.readLine()) != null) {
                                        String[] dataFieldsLit = currLineLit.split(",");

                                        if (Integer.parseInt(dataFieldsLit[1]) == Integer.parseInt(dataFieldsRub[1]) && Integer.parseInt(dataFieldsLit[0]) == Integer.parseInt(dataFieldsRub[0])) {
                                            Autor a = new Autor(dataFieldsLit[3], dataFieldsLit[4], dataFieldsLit[5], Integer.parseInt(dataFieldsLit[6]));

                                            String[] limba = dataFieldsLit[13].split(" ");
                                            String[] cuvCheie = dataFieldsLit[14].split(" ");
                                            List<String> l = new ArrayList<>(Arrays.asList(limba));
                                            List<String> cuv = new ArrayList<>(Arrays.asList(cuvCheie));

                                            Literar carte = new Literar(dataFieldsLit[2], a, l, cuv, Integer.parseInt(dataFieldsLit[9]), Integer.parseInt(dataFieldsLit[7]), Integer.parseInt(dataFieldsLit[8]), Integer.parseInt(dataFieldsLit[10]), Double.parseDouble(dataFieldsLit[11]), Double.parseDouble(dataFieldsLit[12]), dataFieldsLit[15], dataFieldsLit[16]);
                                            carti.add(carte);
                                        }
                                    }
                                } catch (IOException e) {
                                    System.out.println("Could not read data from file: " + e.getMessage());
                                    return null;
                                }

                                try (BufferedReader bufferedReaderNonlit = new BufferedReader(new FileReader("nonliterar.csv"))) {
                                  String currLineNonlit;

                                  while ((currLineNonlit = bufferedReaderNonlit.readLine()) != null) {
                                      String[] dataFieldsNonlit = currLineNonlit.split(",");

                                      if (Integer.parseInt(dataFieldsNonlit[1]) == Integer.parseInt(dataFieldsRub[1]) && Integer.parseInt(dataFieldsNonlit[0]) == Integer.parseInt(dataFieldsRub[0])) {
                                          Autor a = new Autor(dataFieldsNonlit[3], dataFieldsNonlit[4], dataFieldsNonlit[5], Integer.parseInt(dataFieldsNonlit[6]));

                                          String[] limba = dataFieldsNonlit[13].split(" ");
                                          String[] cuvCheie = dataFieldsNonlit[14].split(" ");
                                          List<String> l = new ArrayList<>(Arrays.asList(limba));
                                          List<String> cuv = new ArrayList<>(Arrays.asList(cuvCheie));

                                          Nonliterar carte = new Nonliterar(dataFieldsNonlit[2], a, l, cuv, Integer.parseInt(dataFieldsNonlit[9]), Integer.parseInt(dataFieldsNonlit[7]), Integer.parseInt(dataFieldsNonlit[8]), Integer.parseInt(dataFieldsNonlit[10]), Double.parseDouble(dataFieldsNonlit[11]), Double.parseDouble(dataFieldsNonlit[12]), dataFieldsNonlit[15], dataFieldsNonlit[16]);
                                          carti.add(carte);
                                      }
                                  }
                                } catch (IOException e) {
                                    System.out.println("Could not read data from file: " + e.getMessage());
                                    return null;
                                }

                                Rubrica rub = new Rubrica(dataFieldsRub[2], carti.size(), carti);
                                rubrici.add(rub);
                            }
                        }
                    } catch (IOException e) {
                        System.out.println("Could not read data from file: " + e.getMessage());
                        return null;
                    }

                    try (BufferedReader bufferedReaderCl = new BufferedReader(new FileReader("client.csv"))) {
                        String currLineCl;

                        while ((currLineCl = bufferedReaderCl.readLine()) != null) {
                            String[] dataFieldsCl = currLineCl.split(",");
                            List<Comanda> comenzi = new ArrayList<>();

                            if (Integer.parseInt(dataFieldsCl[0]) == Integer.parseInt(dataFields[0])) {

                                try (BufferedReader bufferedReaderCom = new BufferedReader(new FileReader("comenzi.csv"))) {
                                    String currLineLCom;

                                    while ((currLineLCom = bufferedReaderCom.readLine()) != null) {
                                        String[] dataFieldsCom = currLineLCom.split(",");

                                        if (Integer.parseInt(dataFieldsCl[1]) == Integer.parseInt(dataFieldsCom[1]) && Integer.parseInt(dataFieldsCl[0]) == Integer.parseInt(dataFieldsCom[0])) {
                                            List<Pair<Carte, Integer>> cartiCump = new ArrayList<>();

                                            try (BufferedReader bufferedReaderCart = new BufferedReader(new FileReader("cartiComanda.csv"))) {
                                                String currLineCart;

                                                while((currLineCart = bufferedReaderCart.readLine()) != null) {
                                                    String[] dataFieldsCarti = currLineCart.split(",");

                                                    if (Integer.parseInt(dataFieldsCarti[0]) == Integer.parseInt(dataFieldsCom[0]) && Integer.parseInt(dataFieldsCarti[1]) == Integer.parseInt(dataFieldsCom[1]) && Integer.parseInt(dataFieldsCarti[2]) == Integer.parseInt(dataFieldsCom[2])) {
                                                        Integer nr = Integer.parseInt(dataFieldsCarti[18]);
                                                        Autor a = new Autor(dataFieldsCarti[4], dataFieldsCarti[5], dataFieldsCarti[6], Integer.parseInt(dataFieldsCarti[7]));

                                                        String[] limba = dataFieldsCarti[14].split(" ");
                                                        String[] cuvCheie = dataFieldsCarti[15].split(" ");
                                                        List<String> l = new ArrayList<>(Arrays.asList(limba));
                                                        List<String> cuv = new ArrayList<>(Arrays.asList(cuvCheie));

                                                        if (Integer.parseInt(dataFieldsCarti[10]) == 1) {
                                                            Literar carte = new Literar(dataFieldsCarti[2], a, l, cuv, Integer.parseInt(dataFieldsCarti[10]), Integer.parseInt(dataFieldsCarti[8]), Integer.parseInt(dataFieldsCarti[9]), Integer.parseInt(dataFieldsCarti[11]), Double.parseDouble(dataFieldsCarti[12]), Double.parseDouble(dataFieldsCarti[13]), dataFieldsCarti[16], dataFieldsCarti[17]);
                                                            cartiCump.add(new Pair<>(carte, nr));
                                                        }
                                                        else if (Integer.parseInt(dataFieldsCarti[10]) == 2) {
                                                            Nonliterar carte = new Nonliterar(dataFieldsCarti[2], a, l, cuv, Integer.parseInt(dataFieldsCarti[10]), Integer.parseInt(dataFieldsCarti[8]), Integer.parseInt(dataFieldsCarti[9]), Integer.parseInt(dataFieldsCarti[11]), Double.parseDouble(dataFieldsCarti[12]), Double.parseDouble(dataFieldsCarti[13]), dataFieldsCarti[16], dataFieldsCarti[17]);
                                                            cartiCump.add(new Pair<>(carte, nr));
                                                        }
                                                    }
                                                }
                                            } catch (IOException e) {
                                                System.out.println("Could not read data from file: " + e.getMessage());
                                                return null;
                                            }

                                            Comanda c = new Comanda(dataFieldsCom[3], dataFieldsCom[4], Double.parseDouble(dataFieldsCom[5]), cartiCump);
                                            comenzi.add(c);
                                        }
                                    }
                                } catch (IOException e) {
                                    System.out.println("Could not read data from file: " + e.getMessage());
                                    return null;
                                }
                                Client cl = new Client(dataFieldsCl[2], dataFieldsCl[3], dataFieldsCl[4], dataFieldsCl[5], Integer.parseInt(dataFieldsCl[6]), comenzi);
                                clienti.add(cl);
                            }
                        }
                    } catch (IOException e) {
                        System.out.println("Could not read data from file: " + e.getMessage());
                        return null;
                    }
                    Librarie lib = new Librarie(dataFields[1], dataFields[2], rubrici, clienti);
                    listObj.add((T)lib);
                }
            } catch (IOException e) {
                System.out.println("Could not read data from file: " + e.getMessage());
                return null;
            }
        }

        return listObj;
    }

    public static<T> void scriereInFisier(T ob, List<Librarie> library) {
        if (ob instanceof Librarie) {
            try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("librarie.csv"))) {
                for (Librarie l : library) {
                    bufferedWriter.write(library.indexOf(l) + "," + l.getNume() + "," + l.getAdresa());
                    bufferedWriter.newLine();
                }
            } catch (IOException e) {
                System.out.println("Could not write data to file: " + e.getMessage());
            }
        } else if (ob instanceof Rubrica) {
            try (BufferedWriter bufferedWriterRub = new BufferedWriter(new FileWriter("rubrici.csv"))) {
                for (Librarie l : library) {
                    for (Rubrica r : l.getRubrici()) {
                        bufferedWriterRub.write(library.indexOf(l) + "," + l.getRubrici().indexOf(r) + "," + r.getNumeRubrica());
                        bufferedWriterRub.newLine();
                    }
                }
            } catch (IOException e) {
                System.out.println("Could not write data to file: " + e.getMessage());
            }
        } else if (ob instanceof Literar) {
            try (BufferedWriter bufferedWriterLit = new BufferedWriter(new FileWriter("literar.csv"))) {
                for (Librarie l : library) {
                    for (Rubrica r : l.getRubrici()) {
                        for (Carte c : r.getCarti()) {
                            if (c.getTipText() == 1) {
                                String limba = String.join(" ", c.getLimba());
                                String cuvinte = String.join(" ", c.getCuvCheie());
                                bufferedWriterLit.write(library.indexOf(l) + "," + l.getRubrici().indexOf(r) + "," + c.getTitlu() + "," + c.getAutor().getNume() + "," +
                                        c.getAutor().getPrenume() + "," + c.getAutor().getSex() + "," + c.getAutor().getNrCartiScrise() + "," + c.getNrExemplare() + "," +
                                        c.getNrPagini() + "," + c.getTipText() + "," + c.getExemCumparate() + "," + c.getPretD() + "," + c.getPretF() + "," + limba + "," + cuvinte + "," + ((Literar) c).getSpecie() + "," + ((Literar) c).getGenLit());
                                bufferedWriterLit.newLine();
                            }
                        }
                    }
                }
            } catch (IOException e) {
                System.out.println("Could not write data to file: " + e.getMessage());
            }
        }
        else if (ob instanceof Nonliterar) {
            try (BufferedWriter bufferedWriterNonlit = new BufferedWriter(new FileWriter("nonliterar.csv"))) {
                for (Librarie l : library) {
                    for (Rubrica r : l.getRubrici()) {
                        for (Carte c : r.getCarti()) {
                            if (c.getTipText() == 2) {
                                String limba = String.join(" ", c.getLimba());
                                String cuvinte = String.join(" ", c.getCuvCheie());
                                bufferedWriterNonlit.write(library.indexOf(l) + "," + l.getRubrici().indexOf(r) + "," + c.getTitlu() + "," + c.getAutor().getNume() + "," +
                                        c.getAutor().getPrenume() + "," + c.getAutor().getSex() + "," + c.getAutor().getNrCartiScrise() + "," + c.getNrExemplare() + "," +
                                        c.getNrPagini() + "," + c.getTipText() + "," + c.getExemCumparate() + "," + c.getPretD() + "," + c.getPretF() + "," + limba + "," + cuvinte + "," + ((Nonliterar) c).getSpecie() + "," + ((Nonliterar) c).getTiptext());
                                bufferedWriterNonlit.newLine();
                            }
                        }
                    }
                }
            } catch (IOException e) {
                System.out.println("Could not write data to file: " + e.getMessage());
            }
        } else if (ob instanceof Client) {
            try (BufferedWriter bufferedWriterCl = new BufferedWriter(new FileWriter("client.csv"))) {
                for (Librarie l : library) {
                    for (Client cl : l.getClienti()) {
                        bufferedWriterCl.write(library.indexOf(l) + "," + l.getClienti().indexOf(cl) + "," + cl.getNume() + "," + cl.getPrenume() + "," + cl.getSex() + "," + cl.getCNP() + "," + cl.getAreCard());
                        bufferedWriterCl.newLine();
                    }
                }
            } catch (IOException e) {
                System.out.println("Could not write data to file: " + e.getMessage());
            }
        }
        else if (ob instanceof Comanda) {
            try (BufferedWriter bufferedWriterCom = new BufferedWriter(new FileWriter("comenzi.csv"))) {
                for (Librarie l : library) {
                    for (Client cl : l.getClienti()) {
                        for (Comanda c : cl.getComenzi()) {
                            bufferedWriterCom.write(library.indexOf(l) + "," + l.getClienti().indexOf(cl) + "," + cl.getComenzi().indexOf(c) + "," + c.getNrComanda() + "," + c.getMetodaPlata() + "," + c.getPretComanda());
                            bufferedWriterCom.newLine();

                            try (BufferedWriter bufferedWriterCarti = new BufferedWriter(new FileWriter("cartiComanda.csv"))) {
                                for (Pair <Carte, Integer> p : c.getCartiComandate()) {
                                    String limba = String.join(" ", p.getKey().getLimba());
                                    String cuvinte = String.join(" ", p.getKey().getCuvCheie());
                                    if (p.getKey().getTipText() == 1) {
                                        bufferedWriterCarti.write(library.indexOf(l) + "," + l.getClienti().indexOf(cl) + "," + cl.getComenzi().indexOf(c) + "," + p.getKey().getTitlu() + "," + p.getKey().getAutor().getNume() + "," +
                                                p.getKey().getAutor().getPrenume() + "," + p.getKey().getAutor().getSex() + "," + p.getKey().getAutor().getNrCartiScrise() + "," + p.getKey().getNrExemplare() + "," +
                                                p.getKey().getNrPagini() + "," + p.getKey().getTipText() + "," + p.getKey().getExemCumparate() + "," + p.getKey().getPretD() + "," + p.getKey().getPretF() + "," + limba + "," + cuvinte + "," + ((Literar) p.getKey()).getSpecie() + "," + ((Literar) p.getKey()).getGenLit() + "," + p.getValue());
                                        bufferedWriterCarti.newLine();
                                    }
                                    else if (p.getKey().getTipText() == 2) {
                                        bufferedWriterCarti.write(library.indexOf(l) + "," + l.getClienti().indexOf(cl) + "," + cl.getComenzi().indexOf(c) + "," + p.getKey().getTitlu() + "," + p.getKey().getAutor().getNume() + "," +
                                                p.getKey().getAutor().getPrenume() + "," + p.getKey().getAutor().getSex() + "," + p.getKey().getAutor().getNrCartiScrise() + "," + p.getKey().getNrExemplare() + "," +
                                                p.getKey().getNrPagini() + "," + p.getKey().getTipText() + "," + p.getKey().getExemCumparate() + "," + p.getKey().getPretD() + "," + p.getKey().getPretF() + "," + limba + "," + cuvinte + "," + ((Nonliterar) p.getKey()).getSpecie() + "," + ((Nonliterar) p.getKey()).getTiptext() + "," + p.getValue());
                                        bufferedWriterCarti.newLine();
                                    }
                                }
                            }
                        }
                    }
                }
            } catch (IOException e) {
                System.out.println("Could not write data to file: " + e.getMessage());
            }
        }
    }
}
