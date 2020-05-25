package pao.proiect.database;

import pao.proiect.connection.DatabaseConnection;
import pao.proiect.library.Rubrica;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class RubricaDatabase {

    private static final String SELECT_RUB = "SELECT * FROM rubrica WHERE id_lib = ?";

    private static final String DELETE_STATEMENT = "DELETE FROM rubrica WHERE id_rub = ?";
    private static final String DELETE_LIB = "DELETE FROM rubrica WHERE id_lib = ?";

    private static final String UPDATE_NUME = "UPDATE rubrica SET nume = ? WHERE id_rub = ?";

    private static final String INSERT_RUBRICA = "INSERT INTO rubrica (id_lib, id_rub, nume)" +
            "VALUES(?, ?, ?)";

    public int nrRubrici(Integer indexLib) {
        int count = 0;
        try (PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement(SELECT_RUB)) {
            statement.setInt(1, indexLib);

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

    public void updateNume(String nume, String indexRub) {
        try (PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement(UPDATE_NUME)) {
            statement.setString(1, nume);
            statement.setString(2, indexRub);

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Rubrica modificata cu succes!");
                audit("modificareNumeRubrica", new Date());
                return;
            }
        } catch (SQLException e) {
            System.out.println("Ceva nu a mers cand am incercat sa modificam rubrica: " + e.getMessage());
            return;
        }

        System.out.println("Nu am gasit rubrica respectiva! Ne pare rau...");
    }

    public void afisareRubrici(Integer indexLib) {
        if (nrRubrici(indexLib) != 0) {
            System.out.println("In libraria noastra puteti gasi carti din urmatoarele domenii: ");

            try (PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement(SELECT_RUB)) {
                statement.setInt(1, indexLib);
                try (ResultSet result = statement.executeQuery()) {
                    while (result.next()) {
                        System.out.println(result.getString("nume") + " -- > " + result.getString("id_rub"));
                    }
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        } else {
            System.out.println("Momentan nu este adaugata nicio rubrica!");
        }
    }

    public void scrieRubrica(Rubrica r, Integer indexLib, String indexRub) {
        try (PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement(INSERT_RUBRICA)) {
            statement.setInt(1, indexLib);
            statement.setString(2, indexRub);
            statement.setString(3, r.getNumeRubrica());

            int rowInserted = statement.executeUpdate();
            if (rowInserted > 0) {
                System.out.println("Rubrica adaugata cu succes!");
                audit("adaugareRubrica", new Date());
            }
        } catch (SQLException e) {
            System.out.println("Something went wrong when trying to insert a new rubric: " + e.getMessage());
        }
    }

    public boolean deleteRubrica(String indexrub) {
        try (PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement(DELETE_STATEMENT)) {
            statement.setString(1, indexrub);

            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                audit("stergereRubrica", new Date());
                return true;

            }
        } catch (SQLException e) {
            System.out.println("Ceva nu a mers in incercarea de stergere a rubricii: " + e.getMessage());
            return false;
        }

        System.out.println("Nu am gasit rubrica cautata!");
        return false;
    }

    public boolean deleteLib(Integer indexLib) {
        try (PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement(DELETE_LIB)) {
            statement.setInt(1, indexLib);

            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                audit("stergereRubrica", new Date());
                return true;

            }
        } catch (SQLException e) {
            System.out.println("Ceva nu a mers in incercarea de stergere a rubricii: " + e.getMessage());
            return false;
        }

        System.out.println("Nu am gasit rubrica cautata!");
        return false;
    }

    public void audit(String nume, Date data) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("audit.csv", true))) {
            bufferedWriter.write(nume + ',' + data + '\n');
        } catch (IOException e) {
            System.out.println("Could not write data to file: " + e.getMessage());
        }
    }
}
