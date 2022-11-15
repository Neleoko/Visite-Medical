package Controlers;

import Entities.Consultation;
import Entities.Medicament;
import Tools.ConnexionBDD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CtrlMedicament
{
    private Connection cnx;
    private PreparedStatement ps;
    private ResultSet rs;

    public CtrlMedicament() {
        cnx = ConnexionBDD.getCnx();
    }

    public ArrayList<Medicament> GetAllMedicamentsByIdConsultations(int idConsultation) throws SQLException {
        ArrayList<Medicament> lesMedicament = new ArrayList<>();
        ps = cnx.prepareStatement("Select " +
                "medicament.idMedoc, " +
                "medicament.nomMedoc, " +
                "medicament.prixMedoc as Prix " +
                "from medicament, prescrire, consultation " +
                "Where consultation.idConsult = prescrire.numConsult " +
                "and prescrire.numMedoc = medicament.idMedoc " +
                "and consultation.idConsult = ?"
        );
        ps.setInt(1, idConsultation);
        rs = ps.executeQuery();
        while (rs.next()) {
            Medicament medicament = new Medicament(rs.getInt("idMedoc"),rs.getString("nomMedoc"),rs.getFloat("Prix"));
            lesMedicament.add(medicament);
        }
        ps.close();
        rs.close();
        return lesMedicament;
    }

    public ArrayList<Medicament> getAllMedicaments()
    {

        return null;
    }
}
