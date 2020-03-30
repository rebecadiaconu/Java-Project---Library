package pao.proiect.beings;

import java.util.Scanner;

public class Persoana {
    private String nume;
    private String prenume;
    private String anNastere;
    private String anDeces;
    private String sex;

    public Persoana(String nume, String prenume, String anNastere, String anDeces, String sex) {
        this.anDeces = anDeces;
        this.anNastere = anNastere;
        this.nume = nume;
        this.prenume = prenume;
        this.sex = sex;
    }

    public Persoana() {
        this.anDeces = "Necunoscut";
        this.anNastere = "Necunoscut";
        this.nume = "Necunoscut";
        this.prenume = "Necunoscut";
        this.sex = "Necunoscut";
    }

    public String getNume() {
        return nume;
    }

    void setNume(String nume) {
        this.nume = nume;
    }

    public String getPrenume() {
        return prenume;
    }

    void setPrenume(String prenume) {
        this.prenume = prenume;
    }

    public String getAnNastere() {
        return anNastere;
    }

    void setAnNastere(String anNastere) {
        this.anNastere = anNastere;
    }

    public String getAnDeces() {
        return anDeces;
    }

    void setAnDeces(String anDeces) {
        this.anDeces = anDeces;
    }

    public String getSex() {
        return sex;
    }

    void setSex(String sex) {
        this.sex = sex;
    }

    Persoana citeste() {
        String anNastere, anDeces, nume, prenume, sex;
        Integer ok;
        Scanner sc = new Scanner(System.in);

        System.out.println("Introduceti numele (doar numele de familie): ");
        nume = sc.nextLine();
        System.out.println("Introduceti prenumele: ");
        prenume = sc.nextLine();
        System.out.println("Sexul persoanei este (M/F): ");
        sex = sc.nextLine();
        System.out.println("Introduceti data, luna si anul nasterii in urmatorul format: DD/MM/YYYY: ");
        anNastere = sc.nextLine();
        System.out.println("Persoana este decedata?  0 - Nu, 1 - Da");
        ok = sc.nextInt();
        sc.nextLine();
        if (ok == 1) {
            System.out.println("Introduceti data, luna si anul decesului in urmatorul format: DD/MM/YYYY: ");
            anDeces = sc.nextLine();
        }
        else {
            anDeces = "Persoana inca in viata.";
        }

        return new Persoana(nume, prenume, anNastere, anDeces, sex);
    }

    void afiseaza() {
        System.out.print("Numele complet: " + this.nume + " " + this.prenume + " .\n");
        System.out.println("Sex: " + this.sex + "\n");
        System.out.print("Data nasterii este: " +  this.anNastere + "\n");
        if (this.anDeces != "Persoana inca in viata.") {
            System.out.print("Data deces: " + this.anDeces + "\n");
        }

    }
}
