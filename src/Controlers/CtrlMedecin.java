package Controlers;

import Tools.ConnexionBDD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CtrlMedecin
{
    private Connection cnx;
    private PreparedStatement ps;
    private ResultSet rs;

    public CtrlMedecin() {
        cnx = ConnexionBDD.getCnx();
    }

    public ArrayList<String> getAllMedecins() throws SQLException {
        ArrayList<String> lesMedecins = new ArrayList<>();
        ps = cnx.prepareStatement("select medecin.nomMedecin from medecin");
        rs = ps.executeQuery();
        while (rs.next()) {
            lesMedecins.add(rs.getString(1));
        }
        return lesMedecins;
    }

    public int getIdMedecinByName(String nomMed)
    {

        return 0;
    }
}
