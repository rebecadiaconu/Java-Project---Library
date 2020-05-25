package pao.proiect.database;

import pao.proiect.books.Literar;
import pao.proiect.books.Nonliterar;
import pao.proiect.connection.DatabaseConnection;

import javax.swing.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class CarteDatabase {

    private static final String COUNT_LITERAR = "SELECT * FROM literar WHERE id_lib = ? AND id_rub = ?";
    private static final String COUNT_NONLITERAR = "SELECT * FROM nonliterar WHERE id_lib = ? AND id_rub = ?";
    private static final String DETALII_CARTE_LIT = "SELECT * FROM literar WHERE id_lib = ? AND id_rub = ? AND id_carte = ?";
    private static final String DETALII_CARTE_NONLIT = "SELECT * FROM nonliterar WHERE id_lib = ? AND id_rub = ? AND id_carte = ?";
    private static final String GET_BY_ID_LIT = "SELECT * FROM literar WHERE id_carte = ?";
    private static final String GET_BY_ID_NONLIT = "SELECT * FROM nonliterar WHERE id_carte = ?";
    private static final String GET_LIT = "SELECT * FROM literar WHERE id_lib = ?";
    private static final String GET_NONLIT = "SELECT * FROM nonliterar WHERE id_lib = ?";
    private static final String GET_TITLU_LIT = "SELECT * FROM literar WHERE titlu = ? AND id_lib = ?";
    private static final String GET_TITLU_NONLIT = "SELECT * FROM nonliterar WHERE titlu = ? AND id_lib = ?";
    private static final String GET_OPERE_LIT = "SELECT * FROM literar WHERE nume_autor = ? AND prenume_autor = ? AND id_lib = ?";
    private static final String GET_OPERE_NONLIT = "SELECT * FROM nonliterar WHERE nume_autor = ? AND prenume_autor = ? AND id_lib = ?";
    private static final String GET_LIT_TITLU = "SELECT * FROM literar WHERE id_lib = ? AND titlu = ? AND nume_autor = ? AND prenume_autor = ?";
    private static final String GET_NONLIT_TITLU = "SELECT * FROM nonliterar WHERE id_lib = ? AND titlu = ? AND nume_autor = ? AND prenume_autor = ?";

    private static final String UPDATE_EXEMPL_LIT = "UPDATE literar SET nr_exempl = ? WHERE id_lib = ? AND id_rub = ? AND id_carte = ?";
    private static final String UPDATE_EXEMPL_NONLIT = "UPDATE nonliterar SET nr_exempl = ? WHERE id_lib = ? AND id_rub = ? AND id_carte = ?";
    private static final String UPDATE_CUMP_LIT = "UPDATE literar SET exempl_cump = ? WHERE id_lib = ? AND id_rub = ? AND id_carte = ?";
    private static final String UPDATE_CUMP_NONLIT = "UPDATE nonliterar SET exempl_cump = ? WHERE id_lib = ? AND id_rub = ? AND id_carte = ?";
    private static final String UPDATE_PRET_LIT = "UPDATE literar SET pret_digital = ?, pret_fizic = ? WHERE id_carte = ?";
    private static final String UPDATE_PRET_NONLIT = "UPDATE nonliterar SET pret_digital = ?, pret_fizic = ? WHERE id_carte = ?";

    private static final String DELETE_LIB_LIT = "DELETE FROM literar WHERE id_lib = ?";
    private static final String DELETE_LIB_NONLIT = "DELETE FROM nonliterar WHERE id_lib = ?";
    private static final String DELETE_RUB_LIT = "DELETE FROM literar WHERE id_rub = ?";
    private static final String DELETE_RUB_NONLIT = "DELETE FROM nonliterar WHERE id_rub = ?";
    private static final String DELETE_LIT = "DELETE FROM literar WHERE titlu = ? AND nume_autor = ? AND prenume_autor = ?";
    private static final String DELETE_NONLIT = "DELETE FROM nonliterar WHERE titlu = ? AND nume_autor = ? AND prenume_autor = ?";

    private static final String INSERT_LITERAR = "INSERT INTO literar (id_lib, id_rub, id_carte, titlu, nume_autor, prenume_autor, carti_scrise, nr_pagini, tip_text, nr_exempl, exempl_cump, pret_digital, pret_fizic, limb_disp, cuv_cheie, specie, gen)" +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String INSERT_NONLITERAR = "INSERT INTO nonliterar (id_lib, id_rub, id_carte, titlu, nume_autor, prenume_autor, carti_scrise, nr_pagini, tip_text, nr_exempl, exempl_cump, pret_digital, pret_fizic, limb_disp, cuv_cheie, specie, gen)" +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    public int nrLiterar(Integer indexLib, String indexRub) {
        int count = 0;

        try (PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement(COUNT_LITERAR)) {
            statement.setInt(1, indexLib);
            statement.setString(2, indexRub);

            try (ResultSet result = statement.executeQuery()) {
                while (result.next()) {
                    count += 1;
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return count;
    }

    public int nrNonliterar(Integer indexLib, String indexRub) {
        int count = 0;

        try (PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement(COUNT_NONLITERAR)) {
            statement.setInt(1, indexLib);
            statement.setString(2, indexRub);
            try (ResultSet result = statement.executeQuery()) {
                while (result.next()) {
                    count += 1;
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return count;
    }

    public void afiseazaCarti(Integer indexlib) {
        int nr = 0;
        String select = GET_LIT;

        while (nr < 2) {
            try (PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement(select)) {
                statement.setInt(1, indexlib);
                try (ResultSet result = statement.executeQuery()) {
                    while (result.next()) {
                        System.out.println(result.getString("titlu") + ", " + result.getString("nume_autor") + " " + result.getString("prenume_autor") + " -- > " + result.getString("id_carte"));
                    }
                }
            } catch (Exception e) {
                System.out.println(e);
            }

            nr += 1;
            select = GET_NONLIT;
        }
    }

    public void cautaDupaTitlu(String titlu, Integer indexLib) {
        int ok = 0;
        if (cautaTitlu(titlu, indexLib, 1)) {
            ok = 1;
        };
        if (cautaTitlu(titlu, indexLib, 2)) {
            ok = 1;
        };

        if (ok == 0) {
            System.out.println("Nicio carte potrivita cautarii!");
        }
        audit("cautaCarteDupatitlu", new Date());
    }

    private boolean cautaTitlu(String titlu, Integer indexLib, int alegere) {
        int ok = 0;
        String select;
        if (alegere == 1) {
            select = GET_TITLU_LIT;
        }
        else {
            select = GET_TITLU_NONLIT;
        }

        try (PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement(select)) {
            statement.setString(1, titlu);
            statement.setInt(2, indexLib);

            try (ResultSet result = statement.executeQuery()) {
                while (result.next()) {
                    System.out.println(result.getString("titlu") + ", " + result.getString("nume_autor") + "  " + result.getString("prenume_autor"));
                    ok = 1;
                }
            }
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }

        return ok == 1;
    }

    public void cuvCheie(String str, Integer indexLib) {
        int ok = 0;
        if (lookIn(str, indexLib, 1, 1)) {
            ok = 1;
        };
        if (lookIn(str, indexLib, 2, 1)) {
            ok = 1;
        };

        if (ok == 0) {
            System.out.println("Niciun rezultat potrivit cautarii!");
        }
        audit("cautaCarteDupaCuvCheie", new Date());
    }

    public void limbDispon(String str, Integer indexLib) {
        int ok = 0;
        if (lookIn(str, indexLib, 1, 2)) {
            ok = 1;
        };
        if (lookIn(str, indexLib, 2, 2)) {
            ok = 1;
        };

        if (ok == 0) {
            System.out.println("Niciun rezultat potrivit cautarii!");
        }

        audit("cautaOpereDupaLimba", new Date());
    }

    private boolean lookIn(String cuv, Integer indexLib, int alegere, int cautaDupa) {
        int ok = 0;
        String select;
        if (alegere == 1) {
            select = GET_LIT;
        }
        else {
            select = GET_NONLIT;
        }

        try (PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement(select)) {
            statement.setInt(1, indexLib);

            try (ResultSet result = statement.executeQuery()) {
                while (result.next()) {
                    String[] cuvinte = null;
                    if (cautaDupa == 1) {
                        cuvinte = result.getString("cuv_cheie").split(" ");
                    }
                    else {
                        cuvinte = result.getString("limb_disp").split(" ");
                    }
                    for (String c : cuvinte) {
                        if (cuv.contains(c.toLowerCase())) {
                            ok = 1;
                            System.out.println(result.getString("titlu") + ", " + result.getString("nume_autor") + "  " + result.getString("prenume_autor"));
                            break;
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }

        return ok == 1;
    }

    public String getCodCarte(String titlu, String nume, String prenume, Integer indexLib) {
        String cod = "lala";

        cod = getCod(titlu, nume, prenume, indexLib, 1);
        if (cod.equals("lala")) {
            cod = getCod(titlu, nume, prenume, indexLib, 2);
        }

        return cod;
    }

    private String getCod(String titlu, String nume, String prenume, Integer indexLib, int alegere) {
        String select;
        String cod = "lala";

        if (alegere == 1) {
            select = GET_LIT_TITLU;
        }
        else {
            select = GET_NONLIT_TITLU;
        }

        try (PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement(select)) {
            statement.setInt(1, indexLib);
            statement.setString(2, titlu);
            statement.setString(3, nume);
            statement.setString(4, prenume);

            try (ResultSet result = statement.executeQuery()) {
                while (result.next()) {
                    if (result.getInt("nr_exempl") != 0) {
                        cod = result.getString("id_carte");
                        return cod;
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        return cod;
    }

    public double getModifPret(String indexCarte, int alegere) {
        int nr = 0;
        double pret = 0;
        String select = GET_BY_ID_LIT;
        while (nr < 2) {
            try (PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement(select)) {
                statement.setString(1, indexCarte);

                try (ResultSet result = statement.executeQuery()) {
                    if (result.next()) {
                        if (alegere == 1) pret = result.getDouble("pret_digital");
                        else pret = result.getDouble("pret_fizic");
                    }
                }
            } catch (Exception e) {
                System.out.println(e);
            }
            nr += 1;
            select = GET_BY_ID_NONLIT;
        }

        if (pret == 0) {
            System.out.println("Carte negasita!");
        }
        return pret;
    }

    public double getPretCarte(String titlu, String nume, String prenume, Integer indexLib, int alegere) {
        double pret = 0;

        pret = getPret(titlu, nume, prenume, indexLib, alegere, 1);
        if (pret == 0) {
            pret = getPret(titlu, nume, prenume, indexLib, alegere, 2);
        }

        return pret;
    }

    private double getPret(String titlu, String nume, String prenume, Integer indexLib, int alegere, int fisier) {
        double pret = 0;

        String select;
        if (fisier == 1) {
            select = GET_LIT_TITLU;
        }
        else {
            select = GET_NONLIT_TITLU;
        }

        try (PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement(select)) {
            statement.setInt(1, indexLib);
            statement.setString(2, titlu);
            statement.setString(3, nume);
            statement.setString(4, prenume);

            try (ResultSet result = statement.executeQuery()) {
                if (result.next()) {
                    if (alegere == 1) return result.getDouble("pret_digital");
                    else return result.getDouble("pret_fizic");
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        return pret;
    }

    public void opereAutor(String nume, String prenume, Integer indexLib) {
        int ok = 0;
        if (getOpere(nume, prenume, indexLib, 1)) {
            ok = 1;
        }
        if (getOpere(nume, prenume, indexLib, 2)) {
            ok = 1;
        }

        if (ok == 0) {
            System.out.println("Niciun rezultat potrivit cautarii!");
        }
        audit("opereAutor", new Date());
    }

    private boolean getOpere(String nume, String prenume, Integer indexLib, int alegere) {
        String select;
        int ok = 0;
        if (alegere == 1) {
            select = GET_OPERE_LIT;
        }
        else {
            select = GET_OPERE_NONLIT;
        }

        try (PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement(select)) {
            statement.setString(1, nume);
            statement.setString(2, prenume);
            statement.setInt(3, indexLib);

            try (ResultSet result = statement.executeQuery()) {
                while (result.next()) {
                    System.out.println(result.getString("titlu"));
                    ok = 1;
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        return ok == 1;
    }

    public List<String> foundBook(String titlu, String nume, String prenume, Integer indexLib, int alegere) {
        String select;
        List<String> list = new ArrayList<>();
        if (alegere == 1) {
            select = GET_LIT_TITLU;
        }
        else {
            select = GET_NONLIT_TITLU;
        }

        try (PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement(select)) {
            statement.setInt(1, indexLib);
            statement.setString(2, titlu);
            statement.setString(3, nume);
            statement.setString(4, prenume);
            try (ResultSet result = statement.executeQuery()) {
                if (result.next()) {
                    list.add(result.getString("titlu"));
                    return list;
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        return list;
    }

    public void getBestseller(Integer indexLib) {
        int max = 0;
        String autor = null;
        String bestslr = "niciuna";

        try (PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement(GET_LIT)) {
            statement.setInt(1, indexLib);

            try (ResultSet result = statement.executeQuery()) {
                while(result.next()) {
                    if (result.getInt("exempl_cump") > max) {
                        max = result.getInt("exempl_cump");
                        bestslr = result.getString("titlu");
                        autor = result.getString("nume_autor") + " " + result.getString("prenume_autor");
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        try (PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement(GET_NONLIT)) {
            statement.setInt(1, indexLib);

            try (ResultSet result = statement.executeQuery()) {
                while(result.next()) {
                    if (result.getInt("exempl_cump") > max) {
                        max = result.getInt("exempl_cump");
                        bestslr = result.getString("titlu");
                        autor = result.getString("nume_autor") + result.getString("prenume_autor");
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        System.out.println(max);
        if (max == 0) {
            System.out.println("Nu avem un bestseller momentan!");
        }
        else {
            System.out.println("Bestseller: " + bestslr.toUpperCase() + ", " + autor);
        }

        audit("getBestseller", new Date());
    }

    public void setPret(String idCarte) {
        double pretD = getModifPret(idCarte, 1);
        double pretF = getModifPret(idCarte, 2);
        double procent = 0;
        Scanner sc = new Scanner(System.in);

        System.out.println("1 - Reducere pret, 2 - Marire pret");
        int alegere = sc.nextInt();
        sc.nextLine();

        System.out.println("Cartea are in momentul de fata urmatoarele preturi: ");
        System.out.println("Pret fizic: " + pretF);
        System.out.println("Pret digital: " + pretD);
        System.out.println("Ce procent doriti sa aiba reducerea?");
        procent = sc.nextDouble();
        sc.nextLine();
        System.out.println("Doriti sa mariti pretul ambelor variante? d - doar digital, f - doar fizic, df - ambele");
        String varianta = sc.nextLine();

        if (varianta.toLowerCase().equals("f")) {
            if (alegere == 1) {
                pretF -= procent/100 * pretF;
            }
            else if (alegere == 2) {
                pretF += procent/100 * pretF;
            }
        }
        else if (varianta.toLowerCase().equals("d")) {
            if (alegere == 1) {
                pretD -= procent/100 * pretD;
            }
            else if (alegere == 2) {
                pretD += procent/100 * pretD;
            }
        }
        else {
            if (alegere == 1) {
                pretD -= procent/100 * pretD;
                pretF -= procent/100 * pretF;
            }
            else if (alegere == 2) {
                pretD += procent/100 * pretD;
                pretF += procent/100 * pretF;
            }
        }

        System.out.println("Preturile cartii dupa modificare: ");
        System.out.println("Pret fizic: " + pretF);
        System.out.println("Pret digital: " + pretD);

        int nr = 0;
        String select = UPDATE_PRET_LIT;

        while (nr < 2) {
            try (PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement(select)) {
                statement.setDouble(1, pretD);
                statement.setDouble(2, pretF);
                statement.setString(3, idCarte);

                int rowsUpdated = statement.executeUpdate();
                if (rowsUpdated > 0) {
                    System.out.println("Pret carte modificat cu succes!");
                    audit("modificarePretCarte", new Date());
                    return;
                }
            } catch (SQLException e) {
                System.out.println("Ceva nu a mers cand am incercat sa modificam pretul cartii: " + e.getMessage());
                return;
            }
            nr += 1;
            select = UPDATE_PRET_NONLIT;
        }

        System.out.println("Nu am gasit cartea respectiva! Ne pare rau...");
    }

    public void setNrExempl(Integer indexLib, String indexRub, String indexCarte, int howMany, String tip) {
        String select;
        int noOfbooks = 0;
        if (tip.equals("L")) {
            select = UPDATE_EXEMPL_LIT;
            noOfbooks = getNoOfBooks(indexLib, indexRub, indexCarte, 1, 0) - howMany;
        }
        else {
            select = UPDATE_EXEMPL_NONLIT;
            noOfbooks = getNoOfBooks(indexLib, indexRub, indexCarte, 2, 0) - howMany;
        }

        try (PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement(select)) {
            statement.setInt(1, noOfbooks);
            statement.setInt(2, indexLib);
            statement.setString(3, indexRub);
            statement.setString(4, indexCarte);

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Carte modificata cu succes!");
            }
        } catch (SQLException e) {
            System.out.println("Something went wrong when trying to update user: " + e.getMessage());
        }
    }

    public void setExemplCump(Integer indexLib, String indexRub, String indexCarte, int howMany, String tip) {
        int noOfBooks = 0;
        String select;
        if (tip.equals("L")) {
            select = UPDATE_CUMP_LIT;
            noOfBooks = getNoOfBooks(indexLib, indexRub, indexCarte, 1, 1) + howMany;
        }
        else {
            select = UPDATE_CUMP_NONLIT;
            noOfBooks = getNoOfBooks(indexLib, indexRub, indexCarte, 2, 1) + howMany;
        }
        try (PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement(select)) {
            statement.setInt(1, noOfBooks);
            statement.setInt(2, indexLib);
            statement.setString(3, indexRub);
            statement.setString(4, indexCarte);

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Carte modificata cu succes!");
            }
        } catch (SQLException e) {
            System.out.println("Something went wrong when trying to update user: " + e.getMessage());
        }
    }

    private int getNoOfBooks(Integer indexLib, String indexRub, String indexCarte, int alegere, int cump) {
        int count = 0;
        String select;
        if (alegere == 1) {
            select = DETALII_CARTE_LIT;
        }
        else {
            select = DETALII_CARTE_NONLIT;
        }

        try (PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement(select)) {
            statement.setInt(1, indexLib);
            statement.setString(2, indexRub);
            statement.setString(3, indexCarte);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    if (cump == 0) {
                        count = resultSet.getInt("nr_exempl");
                    }
                    else {
                        count = resultSet.getInt("exempl_cump");
                    }

                    return count;
                }

            }
        } catch (Exception e) {
            System.out.println(e);
        }

        return count;
    }

    public void detaliiCarte(String titlu, String nume, String prenume, Integer indexlib, int alegere) {
        String select;
        if (alegere == 1) {
            select = GET_LIT_TITLU;
        }
        else {
            select = GET_NONLIT_TITLU;
        }

        try (PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement(select)) {
            statement.setInt(1, indexlib);
            statement.setString(2, titlu);
            statement.setString(3, nume);
            statement.setString(4, prenume);
            try (ResultSet result = statement.executeQuery()) {
                if (result.next()) {
                    afiseaza(result.getInt("id_lib"), result.getString("id_rub"), result.getString("id_carte"), alegere);
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        audit("afisareDetaliiCarte", new Date());
    }

    public void detaliiCom(Integer indexLib, String indexRub, String indexCarte, int alegere) {
        String select;
        if (alegere == 1) {
            select = DETALII_CARTE_LIT;
        } else {
            select = DETALII_CARTE_NONLIT;
        }
        try (PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement(select)) {
            statement.setInt(1, indexLib);
            statement.setString(2, indexRub);
            statement.setString(3, indexCarte);

            try (ResultSet result = statement.executeQuery()) {
                while (result.next()) {
                    System.out.println("Titlu:" + result.getString("titlu"));
                    System.out.println("Autor: " + result.getString("nume_autor") + " " + result.getString("prenume_autor"));
                    System.out.println("Pret digital: " + result.getDouble("pret_digital"));
                    System.out.println("Pret fizic: " + result.getDouble("pret_fizic"));
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void scrieNonliterar(Nonliterar c, Integer indexLib, String indexRub, String indexCarte) {
        try (PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement(INSERT_NONLITERAR)) {
            String limba = String.join(" ", c.getLimba());
            String cuvinte = String.join(" ", c.getCuvCheie());
            statement.setInt(1, indexLib);
            statement.setString(2, indexRub);
            statement.setString(3, indexCarte);
            statement.setString(4, c.getTitlu());
            statement.setString(5, c.getAutor().getNume());
            statement.setString(6, c.getAutor().getPrenume());
            statement.setInt(7, c.getAutor().getNrCartiScrise());
            statement.setInt(8, c.getNrPagini());
            statement.setInt(9, c.getTip());
            statement.setInt(10, c.getNrExemplare());
            statement.setInt(11, c.getExemCumparate());
            statement.setDouble(12, c.getPretD());
            statement.setDouble(13, c.getPretF());
            statement.setString(14, limba);
            statement.setString(15, cuvinte);
            statement.setString(16, c.getSpecie());
            statement.setString(17, c.getTiptext());

            int rowInserted = statement.executeUpdate();
            if (rowInserted > 0) {
                System.out.println("Carte nonliterara adaugata cu succes!");
            }

        } catch (SQLException e) {
            System.out.println("Something went wrong when trying to insert a new literary book: " + e.getMessage());
        }
    }

    public void scrieLiterar(Literar c, Integer indexLib, String indexRub, String indexCarte) {
        try (PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement(INSERT_LITERAR)) {
            String limba = String.join(" ", c.getLimba());
            String cuvinte = String.join(" ", c.getCuvCheie());
            statement.setInt(1, indexLib);
            statement.setString(2, indexRub);
            statement.setString(3, indexCarte);
            statement.setString(4, c.getTitlu());
            statement.setString(5, c.getAutor().getNume());
            statement.setString(6, c.getAutor().getPrenume());
            statement.setInt(7, c.getAutor().getNrCartiScrise());
            statement.setInt(8, c.getNrPagini());
            statement.setInt(9, c.getTip());
            statement.setInt(10, c.getNrExemplare());
            statement.setInt(11, c.getExemCumparate());
            statement.setDouble(12, c.getPretD());
            statement.setDouble(13, c.getPretF());
            statement.setString(14, limba);
            statement.setString(15, cuvinte);
            statement.setString(16, c.getSpecie());
            statement.setString(17, c.getGenLit());

            int rowInserted = statement.executeUpdate();
            if (rowInserted > 0) {
                System.out.println("Carte literara adaugata cu succes!");
            }

        } catch (SQLException e) {
            System.out.println("Nu am putut adauga cartea: " + e.getMessage());
        }
    }

    public boolean afiseaza(Integer indexLib, String indexRub, String indexCarte, int alegere) {
        String select;
        if (alegere == 1) {
            select = DETALII_CARTE_LIT;
        } else {
            select = DETALII_CARTE_NONLIT;
        }
        try (PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement(select)) {
            statement.setInt(1, indexLib);
            statement.setString(2, indexRub);
            statement.setString(3, indexCarte);
            try (ResultSet result = statement.executeQuery()) {
                if (result.next()) {
                    System.out.println("Cartea " + result.getString("titlu").toUpperCase() + " este scrisa de " + result.getString("nume_autor").toUpperCase() + " " + result.getString("prenume_autor").toUpperCase() + ".");
                    System.out.println("Disponibila in: " + result.getString("limb_disp"));
                    System.out.println("Numar pagini: " + result.getInt("nr_pagini"));
                    System.out.println("Numar exemplare cumparate: " + result.getInt("exempl_cump"));
                    System.out.println("Pret varianta digitala: " + result.getDouble("pret_digital"));
                    System.out.println("Pret varianta fizica: " + result.getDouble("pret_fizic"));
                    System.out.println("Opera face parte din specia: " + result.getString("specie").toUpperCase() + " si apartine genului " + result.getString("gen").toLowerCase());
                    return true;
                }
            }

        } catch (Exception e) {
            System.out.println("carteee");
            System.out.println(e);
            return false;
        }

        return false;
    }

    public boolean deleteBook(String titlu, String nume, String prenume) {
        boolean done = false;
        int nr = 0;
        String select = DELETE_LIT;

        while (nr < 2) {
            try (PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement(select)) {
                statement.setString(1, titlu);
                statement.setString(2, nume);
                statement.setString(3, prenume);

                int rowsDeleted = statement.executeUpdate();
                if (rowsDeleted > 0) {
                    System.out.println("Carte stearsa cu succes!");
                    done = true;
                }

            } catch (SQLException e) {
                System.out.println("Stergere nefinalizata: " + e.getMessage());
                done = false;
            }
            nr += 1;
            select = DELETE_NONLIT;
        }

        return done;
    }

    public boolean deleteLib(Integer indexLib) {
        boolean done = false;
        int nr = 0;
        String select = DELETE_LIB_LIT;

        while (nr < 2) {
            try (PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement(select)) {
                statement.setInt(1, indexLib);

                int rowsDeleted = statement.executeUpdate();
                if (rowsDeleted > 0) {
                    done = true;
                }
            } catch (SQLException e) {
                System.out.println("Stergere nefinalizata: " + e.getMessage());
                done = false;
            }
            nr += 1;
            select = DELETE_LIB_NONLIT;
        }

        return done;
    }

    public boolean deleteRub(String indexRub) {
        boolean done = false;
        int nr = 0;
        String select = DELETE_RUB_LIT;

        while (nr < 2) {
            try (PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement(select)) {
                statement.setString(1, indexRub);

                int rowsDeleted = statement.executeUpdate();
                if (rowsDeleted > 0) {
                    System.out.println("Continut rubrica sters cu succes!");
                    done = true;
                }
            } catch (SQLException e) {
                System.out.println("Stergere nefinalizata: " + e.getMessage());
                done = false;
            }
            nr += 1;
            select = DELETE_RUB_NONLIT;
        }

        return done;
    }

    public void audit(String nume, Date data){
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("audit.csv", true))) {
            bufferedWriter.write(nume + ',' + data + '\n');
        } catch (IOException e) {
            System.out.println("Could not write data to file: " + e.getMessage());
        }
    }
}
