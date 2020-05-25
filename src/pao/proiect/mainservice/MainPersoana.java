package pao.proiect.mainservice;

import pao.proiect.beings.Persoana;
import java.util.Scanner;

public class MainPersoana {

    public Persoana citeste() {
        String str;
        String nume;
        String prenume;
        String sex;
        Integer ok;
        Scanner sc = new Scanner(System.in);

        System.out.println("Introduceti numele (doar numele de familie): ");
        str = sc.nextLine();
        nume = str.substring(0, 1).toLowerCase() + str.substring(1).toLowerCase();
        System.out.println("Introduceti prenumele: ");
        str = sc.nextLine();
        prenume = str.substring(0, 1).toLowerCase() + str.substring(1).toLowerCase();

        Persoana newPerson =  new Persoana(nume, prenume);

        return newPerson;
    }

    void afiseaza(Persoana p) {
        System.out.print("Numele complet: " + p.getNume() + " " + p.getPrenume() + " .\n");
    }
}
