package pao.proiect;

import java.util.*;

import pao.proiect.beings.Autor;
import pao.proiect.beings.Client;
import pao.proiect.books.*;
import pao.proiect.sortari.AlfTitluComparator;
import pao.proiect.sortari.BookSort;

public class Librarie {
    private String nume;
    private String adresa;
    private List<Rubrica> Rubrici;
    private List<Client> Clienti;

    public Librarie(String nume, String adresa, List<Rubrica> rubrici, List<Client> clienti) {
        this.nume = nume;
        this.adresa = adresa;
        Rubrici = rubrici;
        Clienti = clienti;
    }

    public Librarie() {
        this.nume = "Necunoscut";
        this.adresa= "Necunoscuta";
        this.Rubrici = new ArrayList<>();
        this.Clienti = new ArrayList<>();
    }

    String getNume() {
        return nume;
    }

    void setNume(String nume) {
        this.nume = nume;
    }

    String getAdresa() {
        return adresa;
    }

    void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    List<Rubrica> getRubrici() {
        return Rubrici;
    }

    void setRubrici(List<Rubrica> rubrici) {
        Rubrici = rubrici;
    }

    List<Client> getClienti() {
        return Clienti;
    }

    void setClienti(List<Client> clienti) {
        Clienti = clienti;
    }

    void afisareRubrici() {
        System.out.println("In libraria noastra puteti gasi carti din urmatoarele domenii: ");
       for (Rubrica r : Rubrici) {
           System.out.println(r.getNumeRubrica());
       }
    }

    void afisareLibrarie() {
        System.out.println("Numele librariei este " + this.nume);
        System.out.println("Adresa: " + this.adresa);
        System.out.println("La noi puteti carti din urmatoarele categorii: ");
        for (Rubrica r : this.getRubrici()) {
            System.out.println("\n");
            System.out.println("In rubrica " + r.getNumeRubrica().toUpperCase() + " avem urmatoarele carti:");
            if (r.getCartiE().size() > 0) {
                System.out.println("Gen EPIC: ");
                for (Epic ob : r.getCartiE()) {
                    System.out.println(ob.getTitlu() + ", scrisa de  " + ob.getAutor().getNume() + " " + ob.getAutor().getPrenume());
                    System.out.println("Numar exemplare: " + ob.getNrExemplare());
                    System.out.println("Disponibila in limbile: " + ob.getLimba());
                    System.out.println("Cuvinte cheie: " + ob.getCuvCheie());
                }
            }
            if (r.getCartiL().size() > 0) {
                System.out.println("Gen LIRIC: ");
                for (Liric ob : r.getCartiL()) {
                    System.out.println(ob.getTitlu() + ", scrisa de  " + ob.getAutor().getNume() + " " + ob.getAutor().getPrenume());
                    System.out.println("Numar exemplare: " + ob.getNrExemplare());
                    System.out.println("Disponibila in limbile: " + ob.getLimba());
                    System.out.println("Cuvinte cheie: " + ob.getCuvCheie());
                }
            }
            if (r.getCartiD().size() > 0) {
                System.out.println("Gen DRAMATIC: ");
                for (Dramatic ob : r.getCartiD()) {
                    System.out.println(ob.getTitlu() + ", scrisa de  " + ob.getAutor().getNume() + " " + ob.getAutor().getPrenume());
                    System.out.println("Numar exemplare: " + ob.getNrExemplare());
                    System.out.println("Disponibila in limbile: " + ob.getLimba());
                    System.out.println("Cuvinte cheie: " + ob.getCuvCheie());

                }
            }
            if (r.getCartiN().size() > 0) {
                System.out.println("Text NONLITERAR: ");
                for (Nonliterar ob : r.getCartiN()) {
                    System.out.println(ob.getTitlu() + ", scrisa de  " + ob.getAutor().getNume() + " " + ob.getAutor().getPrenume());
                    System.out.println("Numar exemplare: " + ob.getNrExemplare());
                    System.out.println("Disponibila in limbile: " + ob.getLimba());
                    System.out.println("Cuvinte cheie: " + ob.getCuvCheie());

                }
            }
        }
    }

    Librarie citesteLibrarie() {
        int nrRub;
        String nume, adresa;
        List<Rubrica> rub = new ArrayList<>();
        List<Client> cl = new ArrayList<>();
        Scanner sc = new Scanner(System.in);

        System.out.println("Numele librariei: ");
        nume = sc.nextLine();
        System.out.println("Adresa acesteia: ");
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
            List<Epic> eC = new ArrayList<>();
            List<Liric> lC = new ArrayList<>();
            List<Dramatic> dC = new ArrayList<>();
            List<Nonliterar> nC = new ArrayList<>();

            for (int j = 0; j < nrCarti; j++) {
                System.out.println("Carte noua!");
                int alegere;
                System.out.println("Cartea apartine genului: \n1. Epic \n2. Liric \n3. Dramatic \n4. Este text nonliterar");
                alegere = sc.nextInt();
                sc.nextLine();
                if (alegere == 1) {
                    Epic e = new Epic();
                    eC.add(e.citeste());
                }
                else if (alegere == 2) {
                    Liric l = new Liric();
                    lC.add(l.citeste());
                }
                else if (alegere == 3) {
                    Dramatic d = new Dramatic();
                    dC.add(d.citeste());
                }
                else {
                    Nonliterar n = new Nonliterar();
                    nC.add(n.citeste());
                }
            }
            AlfTitluComparator alfE = new AlfTitluComparator<Epic>();
            AlfTitluComparator alfL = new AlfTitluComparator<Liric>();
            AlfTitluComparator alfD = new AlfTitluComparator<Dramatic>();
            AlfTitluComparator alfN = new AlfTitluComparator<Nonliterar>();

            nC.sort(alfN);
            dC.sort(alfD);
            lC.sort(alfL);
            eC.sort(alfE);

            rub.add(new Rubrica(numeRubr, nrCarti, eC, lC, dC, nC));
        }

        return new Librarie(nume, adresa, rub, cl);
    }

    private <T extends Carte> T cauta(String t, String n, String p, T book) {
        for (Rubrica rub : this.getRubrici()) {

            // Verific daca noua carte are acelasi titlu, acelasi autor (cu aceeasi data de nastere, deces si acelasi sex)
            if (book instanceof Epic) {
                for (Epic ob: rub.getCartiE()) {
                    if (ob.getTitlu().toLowerCase().equals(t.toLowerCase()) && ob.getAutor().getNume().toLowerCase().equals(n.toLowerCase()) && ob.getAutor().getPrenume().toLowerCase().equals(p.toLowerCase()) && ob.getAutor().getAnNastere().equals(book.getAutor().getAnNastere()) && ob.getAutor().getAnDeces().equals(book.getAutor().getAnDeces()) && ob.getAutor().getSex().toLowerCase().equals(book.getAutor().getSex().toLowerCase())) {
                        return (T) ob;
                    }
                }
            }
            if (book instanceof Liric) {
                for (Liric ob: rub.getCartiL()) {
                    if (ob.getTitlu().toLowerCase().equals(t.toLowerCase()) && ob.getAutor().getNume().toLowerCase().equals(n.toLowerCase()) && ob.getAutor().getPrenume().toLowerCase().equals(p.toLowerCase()) && ob.getAutor().getAnNastere().equals(book.getAutor().getAnNastere()) && ob.getAutor().getAnDeces().equals(book.getAutor().getAnDeces()) && ob.getAutor().getSex().toLowerCase().equals(book.getAutor().getSex().toLowerCase())) {
                        return (T) ob;
                    }
                }
            }
            if (book instanceof Dramatic) {
                for (Dramatic ob: rub.getCartiD()) {
                    if (ob.getTitlu().toLowerCase().equals(t.toLowerCase()) && ob.getAutor().getNume().toLowerCase().equals(n.toLowerCase()) && ob.getAutor().getPrenume().toLowerCase().equals(p.toLowerCase()) && ob.getAutor().getAnNastere().equals(book.getAutor().getAnNastere()) && ob.getAutor().getAnDeces().equals(book.getAutor().getAnDeces()) && ob.getAutor().getSex().toLowerCase().equals(book.getAutor().getSex().toLowerCase())) {
                        return (T) ob;
                    }
                }
            }
            if (book instanceof Nonliterar) {
                for (Nonliterar ob: rub.getCartiN()) {
                    if (ob.getTitlu().toLowerCase().equals(t.toLowerCase()) && ob.getAutor().getNume().toLowerCase().equals(n.toLowerCase()) && ob.getAutor().getPrenume().toLowerCase().equals(p.toLowerCase()) && ob.getAutor().getAnNastere().equals(book.getAutor().getAnNastere()) && ob.getAutor().getAnDeces().equals(book.getAutor().getAnDeces()) && ob.getAutor().getSex().toLowerCase().equals(book.getAutor().getSex().toLowerCase())) {
                        return (T) ob;
                    }
                }
            }
        }
        return null;
    }

    <T extends Carte> void adaugaCarte(T book) {
        Scanner sc = new Scanner(System.in);
        if (cauta(book.getTitlu(), book.getAutor().getNume(), book.getAutor().getPrenume(), book) != null) {
            System.out.println("Aceasta carte exista deja in libraria noastra. Ea este disponibila in limbile: " + Objects.requireNonNull(cauta(book.getTitlu(), book.getAutor().getNume(), book.getAutor().getPrenume(), book)).getLimba());
            System.out.println("Aveti disponibila o varianta intr-o limba noua? 0 - Nu, 1 - Da");
            int ok = sc.nextInt();
            sc.nextLine();
            if (ok == 1) {
                String limba = sc.nextLine();
                List<String> lala = Objects.requireNonNull(cauta(book.getTitlu(), book.getAutor().getNume(), book.getAutor().getPrenume(), book)).getLimba();
                lala.add(limba.toLowerCase());
                Objects.requireNonNull(cauta(book.getTitlu(), book.getAutor().getNume(), book.getAutor().getPrenume(), book)).setLimba(lala);
            }
            Objects.requireNonNull(cauta(book.getTitlu(), book.getAutor().getNume(), book.getAutor().getPrenume(), book)).setNrExemplare(Objects.requireNonNull(cauta(book.getTitlu(), book.getAutor().getNume(), book.getAutor().getPrenume(), book)).getNrExemplare() + book.getNrExemplare());
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
                List<Epic> eC = new ArrayList<>();
                List<Liric> lC = new ArrayList<>();
                List<Dramatic> dC = new ArrayList<>();
                List<Nonliterar> nC = new ArrayList<>();
                if (book instanceof Epic) {
                    int choise;
                    String sp, s;

                    System.out.println("Opera este: \n1. Roman\n2. Nuvela\n3. Basm\n");
                    choise = sc.nextInt();
                    sc.nextLine();
                    if (choise == 1) {
                        sp = "Roman";
                    }
                    else if (choise == 2) {
                        sp = "Nuvela";
                    }
                    else {
                        sp = "Basm";
                    }
                    System.out.println("Enumerati cateva personaje importante (separate prin virgula): ");
                    s = sc.nextLine();
                    String[] arr = s.split(",");
                    List<String> persoanej = new ArrayList<>(Arrays.asList(arr));
                    Epic e = new Epic(book.getTitlu(), book.getAutor(), book.getLimba(), book.getCuvCheie(), book.getNrExemplare(), book.getNrPagini(), book.getExemCumparate(),sp,persoanej);
                    eC.add(e);
                }
                else if (book instanceof Liric) {
                    int choise;
                    String sp;

                    System.out.println("Opera este: \n1. Poezie \n2. Balada \n3. Imn \n4. Diverse");
                    choise = sc.nextInt();
                    sc.nextLine();
                    if (choise == 1) {
                        sp = "Poezie";
                    }
                    else if (choise == 2) {
                        sp = "Balada";
                    }
                    else if (choise == 3) {
                        sp = "Imn";
                    }
                    else {
                        sp = "Volum";
                    }
                    Liric l = new Liric(book.getTitlu(), book.getAutor(), book.getLimba(), book.getCuvCheie(), book.getNrExemplare(), book.getNrPagini(), book.getExemCumparate(), sp);
                    lC.add(l);
                }
                else if (book instanceof Dramatic) {
                    int choise;
                    String sp, l;

                    System.out.println("Opera este: \n1. Teatru \n2. Schita");
                    choise = sc.nextInt();
                    sc.nextLine();
                    if (choise == 1) {
                        sp = "Teatru";
                    }
                    else {
                        sp = "Schita";
                    }
                    System.out.println("Enumerati cateva personaje importante (separate prin virgula): ");
                    l = sc.nextLine();
                    String[] arr = l.split(",");
                    List<String> pers = new ArrayList<>(Arrays.asList(arr));
                    Dramatic d = new Dramatic(book.getTitlu(), book.getAutor(), book.getLimba(), book.getCuvCheie(), book.getNrExemplare(), book.getNrPagini(), book.getExemCumparate(), sp, pers);
                    dC.add(d);
                }
                else {
                    int choise;
                    String sp, gen;

                    System.out.println("Opera introdusa face parte din urmatoarea specie: \n1. Jurnal si memorii \n2. Enciclopedie \n3. Articol");
                    choise = sc.nextInt();
                    sc.nextLine();
                    if (choise == 1) {
                        sp = "Jurnal si memorii";
                        gen = "Autobiografic";
                    }
                    else if (choise == 2) {
                        sp = "Enciclopedie";
                        gen = "Informativ";
                    }
                    else {
                        sp = "Articol";
                        gen = "Informativ";
                    }
                    Nonliterar n = new Nonliterar(book.getTitlu(), book.getAutor(), book.getLimba(), book.getCuvCheie(), book.getNrExemplare(), book.getNrPagini(), book.getExemCumparate(), sp, gen);
                    nC.add(n);
                }
                AlfTitluComparator alfE = new AlfTitluComparator<Epic>();
                AlfTitluComparator alfL = new AlfTitluComparator<Liric>();
                AlfTitluComparator alfD = new AlfTitluComparator<Dramatic>();
                AlfTitluComparator alfN = new AlfTitluComparator<Nonliterar>();

                nC.sort(alfN);
                dC.sort(alfD);
                lC.sort(alfL);
                eC.sort(alfE);

                getRubrici().add(new Rubrica(numeRubr, 1, eC, lC, dC, nC));
            }
            else {
                int found = 0;
                System.out.println("In care dintre rubrici ati dori sa o adaugati?");
                r = sc.nextLine();
                for (Rubrica rr : getRubrici()) {
                    if (r.toLowerCase().equals(rr.getNumeRubrica().toLowerCase())) {
                        found = 1;
                        if (book instanceof Epic) {
                            List<Epic> lalalala = rr.getCartiE();
                            lalalala.add((Epic)book);
                            rr.setCartiE(lalalala);
                        }
                        else if (book instanceof Liric) {
                            List<Liric> lalalala = rr.getCartiL();
                            lalalala.add((Liric)book);
                            rr.setCartiL(lalalala);
                        }
                        else if (book instanceof Dramatic) {
                            List<Dramatic> lalalala = rr.getCartiD();
                            lalalala.add((Dramatic) book);
                            rr.setCartiD(lalalala);
                        }
                        else {
                            List<Nonliterar> lalalala = rr.getCartiN();
                            lalalala.add((Nonliterar)book);
                            rr.setCartiN(lalalala);
                        }
                        AlfTitluComparator alfE = new AlfTitluComparator<Epic>();
                        AlfTitluComparator alfL = new AlfTitluComparator<Liric>();
                        AlfTitluComparator alfD = new AlfTitluComparator<Dramatic>();
                        AlfTitluComparator alfN = new AlfTitluComparator<Nonliterar>();

                        rr.getCartiN().sort(alfN);
                        rr.getCartiD().sort(alfD);
                        rr.getCartiL().sort(alfL);
                        rr.getCartiE().sort(alfE);
                        break;
                    }
                }
                if (found == 0) {
                    System.out.println("Ne pare rau, nu am putut sa iti adaugam cartea in rubrica ceruta!");
                    System.out.println("Aceasta nu exista la noi in sistem!");
                    System.out.println("Te rugam sa mai incerci o data!");
                }
            }
        }
    }

    void getBestSeller() {
        String nume = null, prenume = null, titlu = null;
        Integer max = 0;
        if (getRubrici().size() > 0) {
            for (Rubrica r : getRubrici()) {
                BookSort e = new BookSort<Epic>();
                BookSort l = new BookSort<Liric>();
                BookSort d = new BookSort<Dramatic>();
                BookSort n = new BookSort<Nonliterar>();
                r.getCartiE().sort(e);
                r.getCartiL().sort(l);
                r.getCartiD().sort(d);
                r.getCartiN().sort(n);
                if (r.getCartiE().size() > 0) {
                    if (max < r.getCartiE().get(0).getExemCumparate()) {
                        max = r.getCartiE().get(0).getExemCumparate();
                        titlu = r.getCartiE().get(0).getTitlu();
                        nume = r.getCartiE().get(0).getAutor().getNume();
                        prenume = r.getCartiE().get(0).getAutor().getPrenume();
                    }
                }
                if (r.getCartiD().size() > 0) {
                    if (max < r.getCartiD().get(0).getExemCumparate()) {
                        max = r.getCartiD().get(0).getExemCumparate();
                        titlu = r.getCartiD().get(0).getTitlu();
                        nume = r.getCartiD().get(0).getAutor().getNume();
                        prenume = r.getCartiD().get(0).getAutor().getPrenume();
                    }
                }
                if (r.getCartiL().size() > 0) {
                    if (max < r.getCartiL().get(0).getExemCumparate()) {
                        max = r.getCartiL().get(0).getExemCumparate();
                        titlu = r.getCartiL().get(0).getTitlu();
                        nume = r.getCartiL().get(0).getAutor().getNume();
                        prenume = r.getCartiL().get(0).getAutor().getPrenume();
                    }
                }
                if (r.getCartiN().size() > 0) {
                    if (max < r.getCartiN().get(0).getExemCumparate()) {
                        max = r.getCartiN().get(0).getExemCumparate();
                        titlu = r.getCartiN().get(0).getTitlu();
                        nume = r.getCartiN().get(0).getAutor().getNume();
                        prenume = r.getCartiN().get(0).getAutor().getPrenume();
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
    }

    void cautaDupaTitlu(String s) {
        int found = 0;
        if (getRubrici().size() > 0) {
            for (Rubrica r : getRubrici()) {
                if (r.getCartiE().size() > 0) {
                    for (Epic e : r.getCartiE()) {
                        if (e.getTitlu().toLowerCase().equals(s.toLowerCase())) {
                            found = 1;
                            e.afiseaza();
                        }
                    }
                }
                if (r.getCartiL().size() > 0) {
                    for (Liric l : r.getCartiL()) {
                        if (l.getTitlu().toLowerCase().equals(s.toLowerCase())) {
                            l.afiseaza();
                            found = 1;
                        }
                    }
                }
                if (r.getCartiD().size() > 0) {
                    for (Dramatic d : r.getCartiD()) {
                        if (d.getTitlu().toLowerCase().equals(s.toLowerCase())) {
                            d.afiseaza();
                            found = 1;
                        }
                    }
                }
                if (r.getCartiN().size() > 0) {
                    for (Nonliterar n : r.getCartiN()) {
                        if (n.getTitlu().toLowerCase().equals(s.toLowerCase())) {
                            n.afiseaza();
                            found = 1;
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
    }

    void cautaDupaCuvCheie(String s) {
        int found = 0;
        String[] cuv = s.toLowerCase().split(",");
        for (String c : cuv) {
            for (Rubrica r : getRubrici()) {
                for (Epic e : r.getCartiE()) {
                    if (e.getCuvCheie().contains(c)) {
                        found = 1;
                        System.out.println(e.getTitlu() + ", " + e.getAutor().getNume() + " " + e.getAutor().getPrenume());
                    }
                }
                for (Liric l : r.getCartiL()) {
                    if (l.getCuvCheie().contains(c)) {
                        found = 0;
                        System.out.println(l.getTitlu() + ", " + l.getAutor().getNume() + " " + l.getAutor().getPrenume());
                    }
                }
                for (Dramatic d : r.getCartiD()) {
                    if (d.getCuvCheie().contains(c)) {
                        found = 1;
                        System.out.println(d.getTitlu() + ", " + d.getAutor().getNume() + " " + d.getAutor().getPrenume());
                    }
                }
                for (Nonliterar n : r.getCartiN()) {
                    if (n.getCuvCheie().contains(c)) {
                        found = 0;
                        System.out.println(n.getTitlu() + ", " + n.getAutor().getNume() + " " + n.getAutor().getPrenume());
                    }
                }
            }
        }
        if (found == 0) {
            System.out.println("Nu am gasit nicio carte potrivita cautarii dumneavoastra!");
        }
    }

    Set<String> opereAutor(String s) {
        Set<String> carti = new HashSet<>();
        if (getRubrici().size() > 0) {
            for (Rubrica r : getRubrici()) {
                if (r.getCartiE().size() > 0) {
                    for (Epic e : r.getCartiE()) {
                        String name = e.getAutor().getNume().toLowerCase() + " " + e.getAutor().getPrenume().toLowerCase();
                        if (name.equals(s.toLowerCase())) {
                            carti.add(e.getTitlu());
                        }
                    }
                }
                if (r.getCartiL().size() > 0) {
                    for (Liric l : r.getCartiL()) {
                        String name = l.getAutor().getNume().toLowerCase() + " " + l.getAutor().getPrenume().toLowerCase();
                        if (name.equals(s.toLowerCase())) {
                            carti.add(l.getTitlu());
                        }
                    }
                }
                if (r.getCartiD().size() > 0 ) {
                    for (Dramatic d : r.getCartiD()) {
                        String name = d.getAutor().getNume().toLowerCase() + " " + d.getAutor().getPrenume().toLowerCase();
                        if (name.equals(s.toLowerCase())) {
                            carti.add(d.getTitlu());
                        }
                    }
                }
                if (r.getCartiN().size() > 0) {
                    for (Nonliterar n : r.getCartiN()) {
                        String name = n.getAutor().getNume().toLowerCase() + " " + n.getAutor().getPrenume().toLowerCase();
                        if (name.equals(s.toLowerCase())) {
                            carti.add(n.getTitlu());
                        }
                    }
                }
            }
        }

        return carti;
    }

    void opereDisponLimba(String s) {
        for (Rubrica r : getRubrici()) {
            for (Epic e: r.getCartiE()) {
                if (e.getLimba().contains(s.toLowerCase())) {
                    System.out.println(e.getTitlu() + ", " + e.getAutor().getNume() + " " + e.getAutor().getPrenume());
                }
            }
            for (Liric l : r.getCartiL()) {
                if (l.getLimba().contains(s.toLowerCase())) {
                    System.out.println(l.getTitlu() + ", " + l.getAutor().getNume() + " " + l.getAutor().getPrenume());
                }
            }
            for (Dramatic d : r.getCartiD()) {
                if (d.getLimba().contains(s.toLowerCase())) {
                    System.out.println(d.getTitlu() + ", " + d.getAutor().getNume() + " " + d.getAutor().getPrenume());
                }
            }
            for (Nonliterar n : r.getCartiN()) {
                if (n.getLimba().contains(s.toLowerCase())) {
                    System.out.println(n.getTitlu() + ", " + n.getAutor().getNume() + " " + n.getAutor().getPrenume());
                }
            }
        }
    }

    void Comanda(Integer m) {
        Integer areCard = 0;
        Scanner sc = new Scanner(System.in);
        Client cNou = new Client().citeste();

        if (Clienti.contains(cNou)) {
            for (Client cl : Clienti) {
                if (cl.getCNP().equals(cNou.getCNP())) {
                    cl.setCartiCumparate(cl.getCartiCumparate() + m);
                    areCard = cl.getAreCard();
                    break;
                }
            }
        }
        else {
            cNou.setCartiCumparate(m);
            if (cNou.getAreCard() == 0) {
                System.out.println("Doriti sa achizitionati un card specific librariei noastre? Va aduce o reducere de 10% la fiecare comanda. 0 - Nu, 1 - Da");
                areCard = sc.nextInt();
                sc.nextLine();
                cNou.setAreCard(areCard);
            }
            Clienti.add(cNou);
        }
        double pretComanda = 0;
        int i = m;
        while (i > 0) {
            String titlu, numeAutor, prenumeAutor, tipText;
            System.out.println("Titlul cartii: ");
            titlu = sc.nextLine();
            System.out.println("Numele de familie al autorului: ");
            numeAutor = sc.nextLine();
            System.out.println("Prenumele autorului: ");
            prenumeAutor = sc.nextLine();
            System.out.println("Cartea apartine genului: \n1. Epic \n2. Liric \n3. Dramatic \n4. Este text nonliterar");
            tipText = sc.nextLine();

            for (Rubrica r : getRubrici()) {
                if (tipText.equals("1")) {
                    for (Epic e : r.getCartiE()) {
                        double[] result = getPretComanda(i, sc, pretComanda, titlu, numeAutor, prenumeAutor, e.getTitlu(), e.getAutor(), e.getPretD(), e.getPretF(), e);
                        pretComanda += result[0];
                        i = i - (int) Math.round(result[1]);
                    }
                }
                if (tipText.equals("2")) {
                    for (Liric e : r.getCartiL()) {
                        double[] result = getPretComanda(i, sc, pretComanda, titlu, numeAutor, prenumeAutor, e.getTitlu(), e.getAutor(), e.getPretD(), e.getPretF(), e);
                        pretComanda += result[0];
                        i = i - (int) Math.round(result[1]);
                    }
                }
                if (tipText.equals("3")) {
                    for (Dramatic e : r.getCartiD()) {
                        double[] result = getPretComanda(i, sc, pretComanda, titlu, numeAutor, prenumeAutor, e.getTitlu(), e.getAutor(), e.getPretD(), e.getPretF(), e);
                        pretComanda += result[0];
                        i = i - (int) Math.round(result[1]);
                    }
                }
                if (tipText.equals("4")) {
                    for (Nonliterar e : r.getCartiN()) {
                        double[] result = getPretComanda(i, sc, pretComanda, titlu, numeAutor, prenumeAutor, e.getTitlu(), e.getAutor(), e.getPretD(), e.getPretF(), e);
                        pretComanda += result[0];
                        i = i - (int) Math.round(result[1]);
                    }
                }
            }
        }
        if (areCard.equals(1)) {
            pretComanda -= 0.1*pretComanda;
        }
        System.out.println("Pret final comanda: " + pretComanda);
    }

    private <T extends Carte> double[] getPretComanda(Integer m, Scanner sc, double pretComanda, String titlu, String numeAutor, String prenumeAutor, String titlu2, Autor autor, double pretD, double pretF, T e) {

        double[] result = new double[2];
        if (titlu2.toLowerCase().equals(titlu.toLowerCase()) && autor.getNume().toLowerCase().equals(numeAutor.toLowerCase()) && autor.getPrenume().toLowerCase().equals(prenumeAutor.toLowerCase())) {
            Integer howMany;
            int ok;
            System.out.println("Doriti varianta digitala a cartii sau cea fizica? 1 - Digitala, 0 - Fizica");
            ok = sc.nextInt();
            sc.nextLine();
            System.out.println("Cate astfel de carti doriti? ");
            howMany = sc.nextInt();
            sc.nextLine();
            if (ok == 1) {
                pretComanda += howMany * pretD;
            }
            else {
                pretComanda += howMany * pretF;
            }
            e.setExemCumparate(e.getExemCumparate() + howMany);
            e.setNrExemplare(e.getNrExemplare() - howMany);
            result[0] = pretComanda;
            result[1] = howMany;
        }
        else {
            result[0] = result[1] = 0;
        }
        return result;
    }

    void modificarePret(String titlu, String numeAut) {
        int alegere, ok = 0;
        Scanner sc =  new Scanner(System.in);
        if (getRubrici().size() > 0 ) {
            System.out.println("1 - Reducere pret, 2 - Marire pret");
            alegere = sc.nextInt();
            for (Rubrica r : getRubrici()) {
                for (Epic e : r.getCartiE()) {
                    if (pret(sc, titlu, numeAut, e.getPretF(), e.getPretD(), e, alegere) == 1) {
                        ok = 1;
                        System.out.println("Preturile cartii dupa aplicarea reducerii: ");
                        System.out.println("Pret fizic: " + e.getPretF());
                        System.out.println("Pret digital: " + e.getPretD());
                        break;
                    }
                }
                for (Liric l : r.getCartiL()) {
                    if (pret(sc, titlu, numeAut, l.getPretF(), l.getPretD(), l, alegere) == 1) {
                        ok = 1;
                        System.out.println("Preturile cartii dupa aplicarea reducerii: ");
                        System.out.println("Pret fizic: " + l.getPretF());
                        System.out.println("Pret digital: " + l.getPretD());
                        break;
                    }
                }
                for (Dramatic d : r.getCartiD()) {
                    if (pret(sc, titlu, numeAut, d.getPretF(), d.getPretD(), d, alegere) == 1) {
                        ok = 1;
                        System.out.println("Preturile cartii dupa aplicarea reducerii: ");
                        System.out.println("Pret fizic: " + d.getPretF());
                        System.out.println("Pret digital: " + d.getPretD());
                        break;
                    }
                }
                for (Nonliterar n : r.getCartiN()) {
                    if (pret(sc, titlu, numeAut, n.getPretF(), n.getPretD(), n, alegere) == 1) {
                        ok = 1;
                        System.out.println("Preturile cartii dupa aplicarea reducerii: ");
                        System.out.println("Pret fizic: " + n.getPretF());
                        System.out.println("Pret digital: " + n.getPretD());
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
            if (e instanceof Epic) {
                ((Epic) e).setPretD(pret2);
                ((Epic) e).setPretF(pret1);
            }
            if (e instanceof Liric) {
                ((Liric) e).setPretD(pret2);
                ((Liric) e).setPretF(pret1);
            }
            if (e instanceof Dramatic) {
                ((Dramatic) e).setPretD(pret2);
                ((Dramatic) e).setPretF(pret1);
            }
            if (e instanceof Nonliterar) {
                ((Nonliterar) e).setPretF(pret1);
                ((Nonliterar) e).setPretD(pret2);
            }
            return 1;
        }
        return 0;
    }

    void celMaiDevotatClient() {
        if (getClienti().size() > 0){
            getClienti().sort((cl1, cl2) -> cl2.getCartiCumparate() - cl1.getCartiCumparate());
            System.out.println("Cel mai devotat client in momentul actual: ");
            getClienti().get(0).afiseaza();
        }
        else {
            System.out.println("Nu avem niciun client pana in momentul actual!");
        }
    }

    void cateCartiAmCumparat(String s) {
        if (getClienti().size() > 0){
            int  ok = 0;
            for (Client cl : Clienti) {
                if (cl.getCNP().equals(s)) {
                    ok = 1;
                    System.out.println("Ai cumparat: " + cl.getCartiCumparate());
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
    }

    void detaliiCarte(String nume, String autor) {
        int found = 0;
        if (getRubrici().size() > 0) {
            for (Rubrica r: getRubrici()) {
                if (r.getCartiE().size() > 0) {
                    for (Epic e : r.getCartiE()) {
                        String numeAutor = e.getAutor().getNume().toLowerCase() + " " + e.getAutor().getPrenume().toLowerCase();
                        if (e.getTitlu().toLowerCase().equals(nume.toLowerCase()) && autor.toLowerCase().equals(numeAutor)) {
                            e.afiseaza();
                            found = 1;
                            break;
                        }
                    }
                }
                if (r.getCartiL().size() > 0) {
                    for (Liric e : r.getCartiL()) {
                        String numeAutor = e.getAutor().getNume().toLowerCase() + " " + e.getAutor().getPrenume().toLowerCase();
                        if (e.getTitlu().toLowerCase().equals(nume.toLowerCase()) && autor.toLowerCase().equals(numeAutor)) {
                            e.afiseaza();
                            found = 1;
                            break;
                        }
                    }
                }
                if (r.getCartiD().size() > 0) {
                    for (Dramatic e : r.getCartiD()) {
                        String numeAutor = e.getAutor().getNume().toLowerCase() + " " + e.getAutor().getPrenume().toLowerCase();
                        if (e.getTitlu().toLowerCase().equals(nume.toLowerCase()) && autor.toLowerCase().equals(numeAutor)) {
                            e.afiseaza();
                            found = 1;
                            break;
                        }
                    }
                }
                if (r.getCartiN().size() > 0) {
                    for (Nonliterar e : r.getCartiN()) {
                        String numeAutor = e.getAutor().getNume().toLowerCase() + " " + e.getAutor().getPrenume().toLowerCase();
                        if (e.getTitlu().toLowerCase().equals(nume.toLowerCase()) && autor.toLowerCase().equals(numeAutor)) {
                            e.afiseaza();
                            found = 1;
                            break;
                        }
                    }
                }
            }
            if (found == 0) {
                System.out.println("Cartea nu se afla la noi in stoc!");
            }
        }
    }







}
