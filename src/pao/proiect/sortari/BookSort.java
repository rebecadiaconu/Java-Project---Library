package pao.proiect.sortari;

import pao.proiect.books.Carte;

import java.util.Comparator;

public class BookSort <T extends Carte>  implements Comparator<T> {
    @Override
    public int compare(T t, T t1) {
        return t1.getExemCumparate() - t.getExemCumparate();
    }
}
