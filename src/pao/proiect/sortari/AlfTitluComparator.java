package pao.proiect.sortari;

import pao.proiect.books.Carte;

import java.util.Comparator;


public class AlfTitluComparator <T extends Carte>  implements Comparator<T> {
    @Override
    public int compare(T t, T t1) {
        return t.getTitlu().toUpperCase().compareTo(t1.getTitlu().toUpperCase());
    }
}
