package Client;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ClientQt extends JFrame {
    private JTextField textField1;
    private JTextField textField2;
    private JCheckBox newClientCheckBox;
    private JButton loginButton;
    private JButton logoutButton;
    private JPanel panelPrincipale;
    private JButton button1;
    private JButton button2;
    private JTextField textField3;
    private JTextField textField5;
    private JPanel PanelArticle;
    private JTable tableArticle;
    private JButton supprimerLArticleButton;
    private JButton supprimerLePanierButton;
    private JButton confirmerAchatButton;
    private JTextField textField6;
    private JButton acheterButton;
    private JSpinner spinner1;
    private JTextField textField4;
    private DefaultTableModel model;

    public ClientQt(String maraicherEnLigne) {
        setContentPane(panelPrincipale);
        this.setVisible(true);
        this.pack();
        this.setSize(500,400);
        DefaultTableModel tableModel = new DefaultTableModel(new Object[]{"Article", "Prix à l'unité", "Quantité"}, 0);
        tableModel.addRow(new String[]{"Artcicle","Prix à l'unité","Quantité"});


        // Créez la JTable avec le modèle de tableau
        model= new DefaultTableModel();
        tableArticle.setModel(model);
        this.setContentPane(panelPrincipale);
    }


    public static void main(String[] args) {

        JFrame frame= new ClientQt("Maraicher en ligne");
        frame.setSize(1280, 720);
        frame.setMinimumSize(new Dimension(960, 540));
        frame.setVisible(true);


    }
}
