package Controlers;

import Entities.Consultation;
import Tools.ConnexionBDD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CtrlConsultation
{
    private Connection cnx;
    private PreparedStatement ps;
    private ResultSet rs;
    private int idConsult;

    public CtrlConsultation() {
        cnx = ConnexionBDD.getCnx();
    }

    public ArrayList<Consultation> GetAllConsultations() throws SQLException {
        ArrayList<Consultation> lesConsultations = new ArrayList<>();
        ps = cnx.prepareStatement("select " +
                "consultation.idConsult," +
                "consultation.dateConsult," +
                "patient.nomPatient," +
                "medecin.nomMedecin," +
                "round(Sum(prescrire.quantite * medicament.prixMedoc)) as montant\n" +
                "from consultation, patient, medecin, prescrire, medicament\n" +
                "Where patient.idPatient = consultation.numPatient\n" +
                "and medecin.idMedecin = consultation.numMedecin\n" +
                "and consultation.idConsult = prescrire.numConsult\n" +
                "and prescrire.numMedoc = medicament.idMedoc\n" +
                "group by consultation.dateConsult \n" +
                "order by consultation.idConsult ASC"
                );
        rs = ps.executeQuery();
        while (rs.next()) {
            Consultation consultation = new Consultation(rs.getInt("idConsult"), rs.getString("dateConsult"),rs.getString("nomPatient"),rs.getString("nomMedecin"), rs.getFloat("montant"));
            lesConsultations.add(consultation);
        }
        ps.close();
        rs.close();
        return lesConsultations;
    }
    public int getLastNumberOfConsultation() throws SQLException {

        ps = cnx.prepareStatement("select MAX(idConsult)+1 as idConsult from consultation");
        rs = ps.executeQuery();
        while ( rs.next() )
        {

            idConsult = rs.getInt("idConsult");

        }

        ps.close();
        rs.close();
        return idConsult;
    }
    public void InsertConsultation(int idConsult, String dateConsultation, int numPatient,int numMedecin)
    {

    }
}
