package Vues;

import Controlers.CtrlConsultation;
import Controlers.CtrlMedicament;
import Tools.ModelJTable;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

public class FrmConsulter extends JFrame
{
    private JPanel pnlRoot;
    private JLabel lblTitre;
    private JLabel lblConsultations;
    private JTable tblConsultations;
    private JLabel lblMedicaments;
    private JTable tblMedicaments;
    private ModelJTable modelJTable;
    private CtrlConsultation ctrlConsultation;
    private CtrlMedicament ctrlMedicament;

    public FrmConsulter()
    {
        this.setTitle("Consulter");
        this.setContentPane(pnlRoot);
        this.pack();
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);

        ctrlConsultation = new CtrlConsultation();
        ctrlMedicament = new CtrlMedicament();
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                super.windowOpened(e);
                // A vous de jouer
                modelJTable = new ModelJTable();
                try {
                    modelJTable.loadDatasConsultation(ctrlConsultation.GetAllConsultations());
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                tblConsultations.setModel(modelJTable);

            }
        });
        tblConsultations.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                // A vous de jouer
                int idConsult = Integer.parseInt(tblConsultations.getValueAt(tblConsultations.getSelectedRow(),0).toString());
                modelJTable = new ModelJTable();
                try {
                    modelJTable.loadDatasMedPrescrit(ctrlMedicament.GetAllMedicamentsByIdConsultations(idConsult));
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                tblMedicaments.setModel(modelJTable);
            }
        });
    }
}
