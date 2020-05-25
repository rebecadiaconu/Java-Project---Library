package pao.proiect.mainservice;

import pao.proiect.library.Librarie;
import pao.proiect.library.Rubrica;

public class MainRubrica {

    public void afisareRubrici(Librarie l) {
        System.out.println("In libraria noastra puteti gasi carti din urmatoarele domenii: ");
        for (Rubrica r : l.getRubrici()) {
            System.out.println(r.getNumeRubrica());
        }
    }


}
