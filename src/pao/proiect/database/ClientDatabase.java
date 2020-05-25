package pao.proiect.database;

import pao.proiect.beings.Client;
import pao.proiect.connection.DatabaseConnection;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class ClientDatabase {

    private static final String COUNT_CLIENT = "SELECT * FROM client WHERE id_lib = ?";
    private static final String SELECT_CLIENT = "SELECT * FROM client WHERE id_client = ?";
    private static final String GET_BY_CNP = "SELECT * FROM client WHERE cnp = ?";

    private static final String DELETE_STATEMENT = "DELETE FROM client WHERE id_client = ?";
    private static final String DELETE_LIB = "DELETE FROM client WHERE id_lib = ?";

    private static final String UPDATE_NRCOMENZI = "UPDATE client SET comenzi_date = ? WHERE cnp = ?";
    private static final String UPDATE_NUME = "UPDATE client SET nume = ?, prenume = ? WHERE id_client = ?";

    private static final String INSERT_CLIENT = "INSERT INTO client (id_lib, id_client, nume, prenume, cnp, card, comenzi_date)" +
            "VALUES(?, ?, ?, ?, ?, ?, ?)";

    public int nrClienti(Integer indexLib) {
        int count = 0;

        try (PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement(COUNT_CLIENT)) {
            statement.setInt(1, indexLib);

            try (ResultSet result = statement.executeQuery()) {
                while (result.next()) {
                    count += 1;
                }
            }
        }
        catch (Exception e) {
            System.out.println(e);
        }
        return count;
    }

    public void afisareClienti(String indexClient) {

        try (PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement(COUNT_CLIENT)) {
            statement.setString(1, indexClient);
            try (ResultSet result = statement.executeQuery()) {
                while (result.next()) {
                    System.out.println(result.getString("nume") + " " + result.getString("prenume") + " -- > " + result.getString("id_client"));
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void updateNume(String nume, String prenume, String indexClient) {
        try (PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement(UPDATE_NUME)) {
            statement.setString(1, nume);
            statement.setString(2, prenume);
            statement.setString(3, indexClient);

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Nume client modificat cu succes!");
                audit("modificareNumeClient", new Date());
                return;
            }
        } catch (SQLException e) {
            System.out.println("Ceva nu a mers cand am incercat sa modificam numele clientului: " + e.getMessage());
            return;
        }

        System.out.println("Nu am gasit clientul respectiv! Ne pare rau...");
    }

    public int existOrNot(String cnp) {
        try (PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement(GET_BY_CNP)) {
            statement.setString(1, cnp);

            try (ResultSet result = statement.executeQuery()) {
                if (result.next()) {
                    return 1;
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        System.out.println("Nu va aflati in baza noastra de date!");
        return 0;
    }

    public int getCard(String cnp) {
        try (PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement(GET_BY_CNP)) {
            statement.setString(1, cnp);

            try (ResultSet result = statement.executeQuery()) {
                if (result.next()) {
                    return result.getInt("card");
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        return -1;
    }

    public int nrComenziDate(String cnp) {
        try (PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement(GET_BY_CNP)) {
            statement.setString(1, cnp);

            try (ResultSet result = statement.executeQuery()) {
                if (result.next()) {
                    return result.getInt("comenzi_date");
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        System.out.println("Nu va aflati in baza noastra de date!");
        audit("cateComenziAreUnClient", new Date());

        return 0;
    }

    public void clientDevotat(Integer indexLib) {
        if (nrClienti(indexLib) != 0) {
            String nume = null;
            String prenume = null;
            int max = 0;

            try (PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement(COUNT_CLIENT)) {
                statement.setInt(1, indexLib);

                try (ResultSet result = statement.executeQuery()) {
                    while (result.next()) {
                        if (result.getInt("comenzi_date") > max) {
                            max = result.getInt("comenzi_date");
                            nume = result.getString("nume");
                            prenume = result.getString("prenume");
                        }
                    }
                }
            } catch (Exception e) {
                System.out.println(e);
            }

            if (max != 0) {
                System.out.println("Cel mai devotat client: " + nume.toUpperCase() + " " + prenume.toUpperCase());
            }
            else {
                System.out.println("Niciun client inregistrat momentan...");
            }
        }
        else {
            System.out.println("Niciun client inregistrat momentan...");
        }

        audit("celMaiDevotatClient", new Date());
    }

    public String getId(String CNP) {
        System.out.println("In libraria noastra puteti gasi carti din urmatoarele domenii: ");

        try (PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement(GET_BY_CNP)) {
            statement.setString(1, CNP);
            try (ResultSet result = statement.executeQuery()) {
                if (result.next()) {
                    return result.getString("id_client");
                }
            }
        } catch (Exception e) {
            System.out.println(e);
            return "llll";
        }

        System.out.println("Negasit!");
        return "llll";
    }

    public void getClient(String indexClient) {
        try (PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement(SELECT_CLIENT)) {
            statement.setString(1, indexClient);

            try (ResultSet result = statement.executeQuery()) {
                if (result.next()) {
                    System.out.println("Nume: " + result.getString("nume"));
                    System.out.println("Prenume: " + result.getString("prenume"));
                    return;
                }
            }
        } catch(Exception e) {
            System.out.println(e);
            return;
        }

        System.out.println("Cautare esuata!");
    }

    public void afisareClienti(Integer indexLib) {
        if (nrClienti(indexLib) != 0) {
            System.out.println("In libraria noastra puteti gasi carti din urmatoarele domenii: ");

            try (PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement(COUNT_CLIENT)) {
                statement.setInt(1, indexLib);
                try (ResultSet result = statement.executeQuery()) {
                    while (result.next()) {
                        System.out.println(result.getString("nume") + result.getString("prenume") + " " + result.getString("cnp") + " -- > " + result.getString("id_client"));
                    }
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        else {
            System.out.println("Momentan nu avem niciun client!");
        }
    }

    public void scrieClient(Client cl, String indexCl, Integer indexlib) {
        try (PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement(INSERT_CLIENT)) {
            statement.setInt(1, indexlib);
            statement.setString(2, indexCl);
            statement.setString(3, cl.getNume());
            statement.setString(4, cl.getPrenume());
            statement.setString(5, cl.getCNP());
            statement.setInt(6, cl.getAreCard());
            statement.setInt(7, 1);

            int rowInserted = statement.executeUpdate();
            if (rowInserted > 0) {
                System.out.println("Client adaugat cu succes!");
                audit("adaugareClient", new Date());
            }
        } catch (SQLException e) {
            System.out.println("Something went wrong when trying to insert a new client: " + e.getMessage());
        }
    }

    public boolean deleteLib(Integer indexLib) {
        try (PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement(DELETE_LIB)) {
            statement.setInt(1, indexLib);

            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                audit("stergereClient", new Date());
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Ceva nu a mers in incercarea de stergere a clientului: " + e.getMessage());
            return false;
        }

        return false;
    }

    public boolean deleteClient(String indexClient) {
        try (PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement(DELETE_STATEMENT)) {
            statement.setString(1, indexClient);

            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                audit("stergereClient", new Date());
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Ceva nu a mers in incercarea de stergere a clientului: " + e.getMessage());
            return false;
        }

        return false;
    }

    public void updateClient(String cnp) {
        int nrComenzi = nrComenziDate(cnp);
        try (PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement(UPDATE_NRCOMENZI)) {
            statement.setInt(1, nrComenzi);
            statement.setString(2, cnp);

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Client modificat cu succes!");
                audit("modificareClient", new Date());
            }
        } catch (SQLException e) {
            System.out.println("Ceva nu a mers cand am incercat sa modificam clientul: " + e.getMessage());
        }
    }

    public void afiseaza(Integer indexLib, String indexClient) {
        if (nrClienti(indexLib) != 0) {
            try (PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement(SELECT_CLIENT)) {
                statement.setString(1, indexClient);
                statement.setInt(2, indexLib);

                try (ResultSet result = statement.executeQuery()) {
                    while (result.next()) {
                        System.out.println("Numele complet: " + result.getString("nume") + " " + result.getString("prenume") + " .\n");
                        System.out.println("Clientul " + result.getString("nume") + " " + result.getString("prenume") + " are CNP-ul: " + result.getString("cnp"));
                        if (result.getInt("are_card") == 0) {
                            System.out.println("Acesta nu are card al librariei. ");
                        } else {
                            System.out.println("Detine un card al librariei. ");
                        }
                    }
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        else {
            System.out.println("Niciun client inregistrat momentan!");
        }
    }

    public void audit(String nume, Date data){
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("audit.csv", true))) {
            bufferedWriter.write(nume + ',' + data + '\n');
        } catch (IOException e) {
            System.out.println("Could not write data to file: " + e.getMessage());
        }
    }
}
