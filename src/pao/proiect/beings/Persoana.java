package pao.proiect.beings;

import java.util.Scanner;

public class Persoana {
    private String nume;
    private String prenume;
    private String sex;

    public Persoana(String nume, String prenume, String sex) {
        this.nume = nume;
        this.prenume = prenume;
        this.sex = sex;
    }

    public Persoana() {
        this.nume = "Necunoscut";
        this.prenume = "Necunoscut";
        this.sex = "Necunoscut";
    }

    public String getNume() {
        return nume;
    }

    public String getPrenume() {
        return prenume;
    }

    public String getSex() {
        return sex;
    }

    Persoana citeste() {
        String str, nume, prenume, sex;
        Integer ok;
        Scanner sc = new Scanner(System.in);

        System.out.println("Introduceti numele (doar numele de familie): ");
        str = sc.nextLine();
        nume = str.substring(0, 1).toLowerCase() + str.substring(1).toLowerCase();
        System.out.println("Introduceti prenumele: ");
        str = sc.nextLine();
        prenume = str.substring(0, 1).toLowerCase() + str.substring(1).toLowerCase();
        System.out.println("Sexul persoanei este (M/F): ");
        sex = sc.nextLine().toUpperCase();

        return new Persoana(nume, prenume, sex);
    }

    void afiseaza() {
        System.out.print("Numele complet: " + this.nume + " " + this.prenume + " .\n");
        System.out.println("Sex: " + this.sex + "\n");

    }
}
