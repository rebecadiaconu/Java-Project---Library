package pao.proiect.beings;

public class Autor extends Persoana {
    private int nrCartiScrise;

    public Autor(String nume, String prenume, int nrCartiScrise) {
        super(nume, prenume);
        this.nrCartiScrise = nrCartiScrise;
    }

    public int getNrCartiScrise() {
        return nrCartiScrise;
    }

    public void setNrCartiScrise(int nrCartiScrise) {
        this.nrCartiScrise = nrCartiScrise;
    }

    public Autor() {
        super();
        this.nrCartiScrise = 0;
    }

}
