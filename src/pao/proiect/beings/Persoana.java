package pao.proiect.beings;

public class Persoana {
    private String nume;
    private String prenume;

    public Persoana(String nume, String prenume) {
        this.nume = nume;
        this.prenume = prenume;
    }

    public Persoana() {
        this.nume = "Necunoscut";
        this.prenume = "Necunoscut";
    }

    public String getNume() {
        return nume;
    }

    public String getPrenume() {
        return prenume;
    }

}
