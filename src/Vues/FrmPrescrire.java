package Vues;

import Controlers.*;
import Tools.ModelJTable;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FrmPrescrire extends JFrame
{
    private JPanel pnlRoot;
    private JLabel lblTitre;
    private JLabel lblNumero;
    private JLabel lblDate;
    private JLabel lblNomMedecin;
    private JTextField txtNumeroConsultation;
    private JComboBox cboPatients;
    private JComboBox cboMedecins;
    private JButton btnInserer;
    private JTable tblMedicaments;
    private JPanel pnlDate;
    private JLabel lblNomPatient;
    private JLabel lblMedicaments;
    private JDateChooser dcDateConsultation;
    private CtrlPatient ctrlPatient;
    private CtrlConsultation ctrlConsultation;
    private int idConsult;
    private CtrlMedecin ctrlMedecin;

    public FrmPrescrire()
    {
        this.setTitle("Prescrire");
        this.setContentPane(pnlRoot);
        this.pack();
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                super.windowOpened(e);
                dcDateConsultation = new JDateChooser();
                dcDateConsultation.setDateFormatString("yyyy-MM-dd");
                pnlDate.add(dcDateConsultation);

                // A vous de jouer
                ctrlConsultation = new CtrlConsultation();
                ctrlPatient = new CtrlPatient();
                ctrlMedecin = new CtrlMedecin();

                try {
                    idConsult = ctrlConsultation.getLastNumberOfConsultation();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                    txtNumeroConsultation.setText(String.valueOf(idConsult));


                try {
                    for (String patient : ctrlPatient.getAllPatients())
                    {
                        cboPatients.addItem(patient);
                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                try {
                    for (String patient : ctrlMedecin.getAllMedecins())
                    {
                        cboMedecins.addItem(patient);
                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }


            }
        });
        btnInserer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // A vous de jouer


            }
        });
    }
}
