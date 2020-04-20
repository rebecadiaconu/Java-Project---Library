package pao.proiect.library;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import javafx.util.Pair;
import pao.proiect.beings.Client;
import pao.proiect.books.*;
import pao.proiect.sortari.AlfTitluComparator;
import pao.proiect.sortari.BookSort;

public class Librarie {
    private String nume;
    private String adresa;
    private List<Rubrica> rubrici;
    private List<Client> clienti;

    public Librarie(String nume, String adresa, List<Rubrica> rubrici, List<Client> clienti) {
        this.nume = nume;
        this.adresa = adresa;
        this.rubrici = rubrici;
        this.clienti = clienti;
    }

    public Librarie() {
        this.nume = "Necunoscut";
        this.adresa= "Necunoscuta";
        this.rubrici = new ArrayList<>();
        this.clienti = new ArrayList<>();
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public List<Rubrica> getRubrici() {
        return this.rubrici;
    }

    public void setRubrici(List<Rubrica> rubrici) {
        this.rubrici = rubrici;
    }

    public List<Client> getClienti() {
        return this.clienti;
    }

    public void setClienti(List<Client> clienti) {
        this.clienti = clienti;
    }

    public void afisareRubrici() {
        System.out.println("In libraria noastra puteti gasi carti din urmatoarele domenii: ");
       for (Rubrica r : rubrici) {
           System.out.println(r.getNumeRubrica());
       }
        audit("afisareRubrici", new Date());
    }

    public void afisareLibrarie() {
        System.out.println("Numele librariei este " + this.nume);
        System.out.println("Adresa: " + this.adresa);
        System.out.println("La noi puteti gasi carti din urmatoarele categorii: ");
        for (Rubrica r : this.getRubrici()) {
            if (r.getCarti().size() > 0) {
                System.out.println("In rubrica " + r.getNumeRubrica().toUpperCase() + " avem urmatoarele carti:");
                for (Carte cc : r.getCarti()) {
                    if (cc.getTipText() == 1) {
                        ((Literar)cc).afisare();
                    }
                    else if(cc.getTipText() == 2) {
                        ((Nonliterar)cc).afisare();
                    }
                }
            }
        }
        audit("afisareLibrarie", new Date());
    }

    public Librarie citesteLibrarie() {
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
                if (alegere == 1) {
                    Literar e = new Literar();
                    cc.add(e.citeste(alegere));
                }
                else if (alegere == 2) {
                    Nonliterar l = new Nonliterar();
                    cc.add(l.citeste(alegere));
                }
            }
            AlfTitluComparator alfC = new AlfTitluComparator<Carte>();
            cc.sort(alfC);

            rub.add(new Rubrica(numeRubr, nrCarti, cc));
        }

        audit("citesteLibrarie", new Date());
        return new Librarie(nume, adresa, rub, cl);
    }


    public <T extends Carte> void adaugaCarte(Integer index, T book) {
        Scanner sc = new Scanner(System.in);
        int found = 0;
        int pozC = -1;
        int pozR = -1;

        // parcurgem toate cartile pe care le avem in librarie si verificam daca
        // cartea pe care dorim sa o adaugam este deja in stoc sau nu
        if (rubrici.size() > 0) {
            for (Rubrica r : rubrici) {
                if (r.getCarti().size() > 0 && found == 0) {
                    for (Carte c : r.getCarti()) {
                        if (c.getTipText() == index && found == 0) {
                            if (c.getTitlu().toLowerCase().equals(book.getTitlu().toLowerCase()) && c.getAutor().getNume().toLowerCase().equals(book.getAutor().getNume().toLowerCase()) && c.getAutor().getPrenume().toLowerCase().equals(book.getAutor().getPrenume().toLowerCase()) && c.getAutor().getSex().toUpperCase().equals(book.getAutor().getSex().toUpperCase()) && c.getNrPagini().equals(book.getNrPagini())) {
                                found = 1;
                                pozC = r.getCarti().indexOf(c);
                                pozR = rubrici.indexOf(r);
                            }
                        }
                    }
                }
            }
        }

        if (found == 1) {
            System.out.println("Aceasta carte exista deja in libraria noastra. Ea este disponibila in limbile: " + rubrici.get(pozR).getCarti().get(pozC).getLimba());
            System.out.println("Aveti disponibila o varianta intr-o limba noua? 0 - Nu, 1 - Da");
            int ok = sc.nextInt();
            sc.nextLine();
            if (ok == 1) {
                String limba = sc.nextLine().toLowerCase();
                List<String> l = rubrici.get(pozR).getCarti().get(pozC).getLimba();
                l.add(limba);
                rubrici.get(pozR).getCarti().get(pozC).setLimba(l);
            }

            List <String> cuv = rubrici.get(pozR).getCarti().get(pozC).getCuvCheie();
            for (String s : book.getCuvCheie()) {
                if (!cuv.contains(s)) {
                    cuv.add(s);
                }
            }
            rubrici.get(pozR).getCarti().get(pozC).setCuvCheie(cuv);
            rubrici.get(pozR).getCarti().get(pozC).setNrExemplare(rubrici.get(pozR).getCarti().get(pozC).getNrExemplare() + book.getNrExemplare());
        }
        else {
            afisareRubrici();
            int alegere;
            String r;

            System.out.println("Doriti sa adaugati rubrica noua? 0 - Nu, 1 - Da");
            alegere = sc.nextInt();
            sc.nextLine();

            if (alegere == 1) {
                String numeRubr;
                System.out.println("Introduceti numele rubricii: ");
                numeRubr = sc.nextLine();
                List<Carte> carti = new ArrayList<>();
                carti.add(book);
                AlfTitluComparator alfC = new AlfTitluComparator<Carte>();
                carti.sort(alfC);
                List<Rubrica> rub = getRubrici();
                rub.add(new Rubrica(numeRubr, 1, carti));
                setRubrici(rub);
            }
            else if (alegere == 0) {
                int ok = 0;
                System.out.println("In care dintre rubrici ati dori sa o adaugati?");
                r = sc.nextLine();
                for (Rubrica rr : getRubrici()) {
                    if (ok == 0) {
                        if (r.toLowerCase().equals(rr.getNumeRubrica().toLowerCase())) {
                            ok = 1;
                            List<Carte> lista = rr.getCarti();
                            lista.add(book);
                            rr.setCarti(lista);

                            AlfTitluComparator alfC = new AlfTitluComparator<Carte>();
                            rr.getCarti().sort(alfC);
                        }
                    }
                }
                if (ok == 0) {
                    System.out.println("Ne pare rau, nu am putut sa iti adaugam cartea in rubrica ceruta!");
                    System.out.println("Aceasta nu exista la noi in sistem!");
                    System.out.println("Te rugam sa mai incerci o data!");
                }
            }
        }
        audit("adaugaCarte", new Date());
    }

    public void getBestSeller() {
        String nume = null, prenume = null, titlu = null;
        Integer max = 0;
        if (getRubrici().size() > 0) {
            for (Rubrica r : getRubrici()) {
                BookSort e = new BookSort<>();
                r.getCarti().sort(e);
                if (r.getCarti().size() > 0) {
                    if (max < r.getCarti().get(0).getExemCumparate()) {
                        max = r.getCarti().get(0).getExemCumparate();
                        titlu = r.getCarti().get(0).getTitlu();
                        nume = r.getCarti().get(0).getAutor().getNume();
                        prenume = r.getCarti().get(0).getAutor().getPrenume();
                    }
                }
            }
            if (max != 0) {
                System.out.println("Bestseller-ul momentului: ");
                System.out.println(titlu + ", " + nume + " " + prenume);
            }
            else {
                System.out.println("Nu am gasit niciun BestSeller!");
            }
        }
        else {
            System.out.println("Nu avem nicio carte in stoc momentan!");
        }
        audit("getBestseller", new Date());
    }

    public void cautaDupaTitlu(String s) {
        int found = 0;
        if (getRubrici().size() > 0) {
            for (Rubrica r : getRubrici()) {
                if (r.getCarti().size() > 0) {
                    for (Carte e : r.getCarti()) {
                        if (e.getTitlu().toLowerCase().equals(s.toLowerCase())) {
                            found = 1;
                            if (e.getTipText() == 1) {
                                ((Literar)e).afisare();
                            }
                            else {
                                ((Nonliterar)e).afisare();
                            }
                        }
                    }
                }
            }
            if (found == 0) {
                System.out.println("Nicio carte cu aceste titlu nu se afla la noi in librarie!");
            }
        }
        else {
            System.out.println("Inca nu avem nicio carte in stoc!");
        }
        audit("cautaCarteDupatitlu", new Date());
    }

    public void cautaDupaCuvCheie(String s) {
        int found = 0;
        String[] cuv = s.toLowerCase().split(",");
        for (String c : cuv) {
            for (Rubrica r : getRubrici()) {
                for (Carte e : r.getCarti()) {
                    if (e.getCuvCheie().contains(c)) {
                        found = 1;
                        System.out.println(e.getTitlu() + ", " + e.getAutor().getNume() + " " + e.getAutor().getPrenume());
                    }
                }
            }
        }
        if (found == 0) {
            System.out.println("Nu am gasit nicio carte potrivita cautarii dumneavoastra!");
        }
    }

    public Set<String> opereAutor(String s) {
        Set<String> carti = new HashSet<>();
        if (getRubrici().size() > 0) {
            for (Rubrica r : getRubrici()) {
                if (r.getCarti().size() > 0) {
                    for (Carte e : r.getCarti()) {
                        String name = e.getAutor().getNume().toLowerCase() + " " + e.getAutor().getPrenume().toLowerCase();
                        if (name.equals(s.toLowerCase())) {
                            carti.add(e.getTitlu());
                        }
                    }
                }
            }
        }

        audit("cautaCartiDupaCuvCheie", new Date());
        return carti;
    }

    public void opereDisponLimba(String s) {
        for (Rubrica r : getRubrici()) {
            for (Carte e: r.getCarti()) {
                if (e.getLimba().contains(s.toLowerCase())) {
                    System.out.println(e.getTitlu() + ", " + e.getAutor().getNume() + " " + e.getAutor().getPrenume());
                }
            }
        }
        audit("cautaOpereDupaLimba", new Date());
    }

    public void celMaiDevotatClient() {
        if (getClienti().size() > 0) {
            getClienti().sort((cl1, cl2) -> cl2.getNumarCartiCump() - cl1.getNumarCartiCump());
            System.out.println("Cel mai devotat client in momentul actual: ");
            getClienti().get(0).afiseaza();
        }
        else {
            System.out.println("Nu avem niciun client pana in momentul actual!");
        }
        audit("celMaiDevotatClient", new Date());
    }

    public void Comanda(Integer m) {
        Integer nr = m;
        Integer met = 0;
        double pretComanda = 0;
        String metodaPlata = "";
        int nrComanda;
        Random rand = new Random();
        List<Pair<Carte, Integer>> cartiCom = new ArrayList<>();
        Scanner sc = new Scanner(System.in);

        while (nr > 0) {
            String titlu, numeAutor, prenumeAutor;
            System.out.println("Titlul cartii: ");
            titlu = sc.nextLine();
            System.out.println("Numele de familie al autorului: ");
            numeAutor = sc.nextLine();
            System.out.println("Prenumele autorului: ");
            prenumeAutor = sc.nextLine();
            if (getRubrici().size() > 0) {
                for (Rubrica r : getRubrici()) {
                    if (r.getCarti().size() > 0) {
                        for (Carte ob : r.getCarti()) {
                            if (ob.getTitlu().toLowerCase().equals(titlu.toLowerCase()) && ob.getAutor().getNume().toLowerCase().equals(numeAutor.toLowerCase()) && ob.getAutor().getPrenume().toLowerCase().equals(prenumeAutor.toLowerCase())) {
                                if (ob.getNrExemplare() == 0) {
                                    System.out.println("Carte indisponibila momentan!");
                                }
                                else {
                                    Integer howMany = 0;
                                    int ok;
                                    System.out.println("Doriti varianta digitala a cartii sau cea fizica? 1 - Digitala, 0 - Fizica");
                                    ok = sc.nextInt();
                                    sc.nextLine();
                                    System.out.println("Cate astfel de carti doriti? Din totalul de " + m + " mai aveti: " + nr);
                                    howMany = sc.nextInt();
                                    sc.nextLine();

                                    if (ok == 1) {
                                        pretComanda += howMany * ob.getPretD();
                                    }
                                    else {
                                        pretComanda += howMany * ob.getPretF();
                                        ob.setNrExemplare(ob.getNrExemplare() - howMany);
                                    }

                                    ob.setExemCumparate(ob.getExemCumparate() + howMany);
                                    nr -= howMany;
                                    cartiCom.add(new Pair<Carte, Integer>(ob, howMany));
                                }
                            }
                        }
                    }
                    else {
                        System.out.println("Momentan nu avem nicio carte in aceasta rubrica!");
                    }
                }
            }
            else {
                System.out.println("Nu avem nicio carte in stocul librariei!");
            }
        }

        System.out.println("Metoda de plata: 1 - card, 2 - ramburs la livrare");
        met = sc.nextInt();
        sc.nextLine();
        if (met == 1) {
            metodaPlata = "Card";
        }
        else if (met == 2) {
            metodaPlata = "Ramburs la livrare";
        }
        nrComanda = rand.nextInt(1000000);

        Client cNou = new Client().citeste();
        if (clienti.contains(cNou)) {
            for (Client cl : clienti) {
                if (cl.getCNP().equals(cNou.getCNP())) {
                    if (cl.getAreCard() == 1) {
                        pretComanda -= 0.1 * pretComanda;
                    }
                    List<Comanda> c = cl.getComenzi();
                    Comanda comNoua = new Comanda(Integer.toString(nrComanda), metodaPlata, pretComanda, cartiCom);
                    comNoua.afisareComanda();
                    c.add(comNoua);
                    cl.setComenzi(c);
                    break;
                }
            }
        }
        else {
            if (cNou.getAreCard() == 1) {
                pretComanda -= 0.1 * pretComanda;
            }
            List<Comanda> c = cNou.getComenzi();
            Comanda comNoua = new Comanda(Integer.toString(nrComanda), metodaPlata, pretComanda, cartiCom);
            comNoua.afisareComanda();
            c.add(comNoua);
            cNou.setComenzi(c);
            clienti.add(cNou);
        }

        System.out.println("Multumim ca ati cumparat de la noi!");
        audit("comandaCarti", new Date());
    }

    public void modificarePret(String titlu, String numeAut) {
        int alegere, ok = 0;
        Scanner sc =  new Scanner(System.in);
        if (getRubrici().size() > 0 ) {
            System.out.println("1 - Reducere pret, 2 - Marire pret");
            alegere = sc.nextInt();
            for (Rubrica r : getRubrici()) {
                for (Carte e : r.getCarti()) {
                    if (pret(sc, titlu, numeAut, e.getPretF(), e.getPretD(), e, alegere) == 1) {
                        ok = 1;
                        System.out.println("Preturile cartii dupa aplicarea reducerii: ");
                        System.out.println("Pret fizic: " + e.getPretF());
                        System.out.println("Pret digital: " + e.getPretD());
                        break;
                    }
                }
            }
            if (ok == 0) {
                System.out.println("Cartea cautata nu se afla la noi in stoc!");
            }
        }
        else {
            System.out.println("Nu avem nicio carte in stoc momentan!");
        }
        audit("modificaPretulUneiCarti", new Date());
    }

    private <T extends Carte>  int pret(Scanner sc, String titlu1, String nume1, double pret1, double pret2, T e, int alegere) {
        if (e.getTitlu().toLowerCase().equals(titlu1.toLowerCase()) && nume1.toLowerCase().contains(e.getAutor().getNume().toLowerCase()) && nume1.toLowerCase().contains(e.getAutor().getPrenume().toLowerCase())) {
            double procent;
            String varianta;
            System.out.println("Cartea are in momentul de fata urmatoarele preturi: ");
            System.out.println("Pret fizic: " + pret1);
            System.out.println("Pret digital: " + pret2);
            System.out.println("Ce procent doriti sa aiba reducerea?");
            procent = sc.nextInt();
            sc.nextLine();
            System.out.println("Doriti sa mariti pretul ambelor variante? d - doar digital, f - doar fizic, df - ambele");
            varianta = sc.nextLine();
            if (varianta.toLowerCase().equals("f")) {
                if (alegere == 1) {
                    pret1 -= procent/100 * pret1;
                }
                else if (alegere == 2) {
                    pret1 += procent/100 * pret1;
                }
            }
            else if (varianta.toLowerCase().equals("d")) {
                if (alegere == 1) {
                    pret2 -= procent/100 * pret2;
                }
                else if (alegere == 2) {
                    pret2 += procent/100 * pret2;
                }
            }
            else {
                if (alegere == 1) {
                    pret2 -= procent/100 * pret2;
                    pret1 -= procent/100 * pret1;
                }
                else if (alegere == 2) {
                    pret2 += procent/100 * pret2;
                    pret1 += procent/100 * pret1;
                }
            }
            e.setPretD(pret2);
            e.setPretF(pret1);

            return 1;
        }
        return 0;
    }

    public void numarComenziDate(String s) {
        if (getClienti().size() > 0) {
            int  ok = 0;
            for (Client cl : clienti) {
                if (cl.getCNP().equals(s)) {
                    ok = 1;
                    System.out.println("Numar comenzi realizate la libraria noastra: " + cl.getComenzi().size());
                    break;
                }
            }
            if (ok == 0) {
                System.out.println("Nu va aflati in baza noastra de date!");
            }
        }
        else {
            System.out.println("Nu avem niciun client pana in momentul actual!");
        }
        audit("cateComenziAreUnClient", new Date());
    }

    public void detaliiCarte(String nume, String autor) {
        int found = 0;
        if (getRubrici().size() > 0) {
            for (Rubrica r: getRubrici()) {
                if (r.getCarti().size() > 0) {
                    for (Carte e : r.getCarti()) {
                        String numeAutor = e.getAutor().getNume().toLowerCase() + " " + e.getAutor().getPrenume().toLowerCase();
                        if (e.getTitlu().toLowerCase().equals(nume.toLowerCase()) && autor.toLowerCase().equals(numeAutor)) {
                            if (e.getTipText() == 1) {
                                ((Literar) e).afisare();
                                found = 1;
                                break;
                            }
                            else {
                                ((Nonliterar) e).afisare();
                                found = 1;
                                break;
                            }
                        }
                    }
                }
            }
            if (found == 0) {
                System.out.println("Cartea nu se afla la noi in stoc!");
            }
        }
        audit("afisareDetaliiCarte", new Date());
    }

    public void detaliiComanda(String s) {
        if (getClienti().size() > 0) {
            for (Client c : getClienti()) {
                if (c.getComenzi().size() > 0) {
                    for (Comanda com : c.getComenzi()) {
                        if (com.getNrComanda().equals(s)) {
                            com.afisareComanda();
                        }
                    }
                }
            }
        }
        audit("afisareDetalliComanda", new Date());
    }

    public void audit(String nume, Date data){
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("audit.csv", true))) {
            bufferedWriter.write(nume + ',' + data + '\n');
        } catch (IOException e) {
            System.out.println("Could not write data to file: " + e.getMessage());
        }
    }

}
