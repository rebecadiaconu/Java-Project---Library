package pao.proiect.database;

import pao.proiect.connection.DatabaseConnection;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class ComandaDatabase {
    ClientDatabase client = new ClientDatabase();
    CarteDatabase carte = new CarteDatabase();

    private static final String CONTINUT_COMANDA = "SELECT * FROM carte_comandata WHERE nr_comanda = ? AND id_client = ?";
    private static final String GET_COM = "SELECT * FROM comanda WHERE nr_comanda = ?";

    private static final String DELETE_CARTE_CLN = "DELETE FROM carte_comandata WHERE id_client = ?";
    private static final String DELETE_COMANDA_CLN = "DELETE FROM comanda WHERE id_client = ?";
    private static final String DELETE_COMANDA_LIB = "DELETE FROM comanda WHERE id_lib = ?";
    private static final String DELETE_CARTE_LIB = "DELETE FROM carte_comandata WHERE id_lib = ?";

    private static final String INSERT_COMANDA = "INSERT INTO comanda (id_lib, id_client, nr_comanda, metoda_plata, pret_comanda)" +
            "VALUES (?, ?, ? ,? ,?)";
    private static final String INSERT_CARTI_COMANDATE = "INSERT INTO carte_comandata (id_lib, id_client, id_carte, nr_comanda, bucati_comanda) " +
            "VALUES (?, ?, ?, ?, ?)";

    public void detaliiComanda(String nrComanda) {
        try (PreparedStatement statementCom = DatabaseConnection.getInstance().getConnection().prepareStatement(GET_COM)) {
            statementCom.setString(1, nrComanda);

            try (ResultSet result = statementCom.executeQuery()) {
                if (result.next()) {
                    System.out.println("Comanda gasita!");
                    System.out.println("Numar comanda: " + nrComanda);
                    System.out.println("Metoda de plata: " + result.getString("metoda_plata"));
                    System.out.println("Total comanda: " + result.getDouble("pret_comanda"));
                    System.out.println("DETALII CLIENT: ");
                    client.getClient(result.getString("id_client"));
                    System.out.println("CONTINUT: ");
                    cartiComanda(nrComanda, result.getString("id_client"));
                    return;
                }
            }
        } catch (Exception e) {
            System.out.println(e);
            return;
        }

        System.out.println("Comanda negasita!");
    }

    public void cartiComanda(String nrComanda, String indexClient) {
        try (PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement(CONTINUT_COMANDA)) {
            statement.setString(1, nrComanda);
            statement.setString(2, indexClient);

            try (ResultSet result = statement.executeQuery()) {
                while (result.next()) {
                    String indexCarte = result.getString("id_carte");

                    Integer indexLib = Integer.parseInt(indexCarte.substring(0, 1));
                    String indexRub = indexCarte.substring(0, 2);
                    String option = indexCarte.substring(3, 4);
                    if (option.equals("L")) {
                        carte.detaliiCom(indexLib, indexRub, indexCarte, 1);
                    }
                    else {
                        carte.detaliiCom(indexLib, indexRub, indexCarte, 2);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        audit("afisareDetalliComanda", new Date());
    }

    public void scrieComanda(Integer indexLib, String indexCl, String nrComanda, String metPlata, double pret) {
        try (PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement(INSERT_COMANDA)) {
            statement.setInt(1, indexLib);
            statement.setString(2, indexCl);
            statement.setString(3, nrComanda);
            statement.setString(4, metPlata);
            statement.setDouble(5, pret);
            int rowInserted = statement.executeUpdate();

            if (rowInserted > 0) {
                System.out.println("Comanda adaugata cu succes!");
                audit("adaugareComandaNoua", new Date());
            }
        } catch (SQLException e) {
            System.out.println("Something went wrong when trying to insert a new order: " + e.getMessage());
        }
    }

    public void scrieCarteComandata(Integer indexLib, String indexClient, String indexCarte, String nrComanda, int howMany) {

        try (PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement(INSERT_CARTI_COMANDATE)) {
            statement.setInt(1, indexLib);
            statement.setString(2, indexClient);
            statement.setString(3, indexCarte);
            statement.setString(4, nrComanda);
            statement.setInt(5, howMany);
            int rowInserted = statement.executeUpdate();

            if (rowInserted > 0) {
                System.out.println("Comanda adaugata cu succes!");
                audit("adaugareCarteComandata", new Date());
            }
        } catch (SQLException e) {
            System.out.println("Something went wrong when trying to insert a new order: " + e.getMessage());
        }
    }

    public boolean deleteLib(Integer indexLib) {
        try (PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement(DELETE_COMANDA_LIB)) {
            statement.setInt(1, indexLib);

            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                audit("stergereComanda", new Date());
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Ceva nu a mers in incercarea de stergere a comenzii: " + e.getMessage());
            return false;
        }

        return false;
    }

    public boolean deleteCarteLib(Integer indexLib) {
        try (PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement(DELETE_CARTE_LIB)) {
            statement.setInt(1, indexLib);

            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                audit("stergereCarteComandata", new Date());
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Ceva nu a mers in incercarea de stergere a cartii: " + e.getMessage());
            return false;
        }

        return false;
    }

    public boolean deleteCarte(String indexClient) {
        try (PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement(DELETE_CARTE_CLN)) {
            statement.setString(1, indexClient);

            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                audit("stergereCarteComandata", new Date());
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Ceva nu a mers in incercarea de stergere a cartii: " + e.getMessage());
            return false;
        }

        return false;
    }

    public boolean deleteComanda(String indexClient) {
        try (PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement(DELETE_COMANDA_CLN)) {
            statement.setString(1, indexClient);

            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                audit("stergereComanda", new Date());
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Ceva nu a mers in incercarea de stergere a comenzii: " + e.getMessage());
            return false;
        }

        return false;
    }

    public void audit(String nume, Date data){
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("audit.csv", true))) {
            bufferedWriter.write(nume + ',' + data + '\n');
        } catch (IOException e) {
            System.out.println("Could not write data to file: " + e.getMessage());
        }
    }
}
