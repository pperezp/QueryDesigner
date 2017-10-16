/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.main;

import java.awt.Component;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import model.MySQL;
import view.Drawer;

/**
 *
 * @author prez
 */
public class QueryDrawer extends javax.swing.JFrame {

    private Drawer drawer;

    public QueryDrawer() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSplitPane1 = new javax.swing.JSplitPane();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        pnlRootPass = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtRootPass = new javax.swing.JPasswordField();
        btnEnter = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        pnlRootPass.setLayout(new java.awt.GridLayout(15, 0));

        jLabel1.setText("Root password:");
        pnlRootPass.add(jLabel1);

        txtRootPass.setText("123456");
        pnlRootPass.add(txtRootPass);

        btnEnter.setText("Entrar");
        btnEnter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEnterActionPerformed(evt);
            }
        });
        pnlRootPass.add(btnEnter);

        jTabbedPane1.addTab("Databases", pnlRootPass);

        jSplitPane1.setLeftComponent(jTabbedPane1);

        getContentPane().add(jSplitPane1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnEnterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEnterActionPerformed
        try {
            MySQL mysql = new MySQL(new String(txtRootPass.getPassword()));

            drawer = new Drawer(mysql);
            
            List<String> databasesName = mysql.getDatabasesName();
            
            DefaultMutableTreeNode root = new DefaultMutableTreeNode("Databases");
            
            for (String dbName : databasesName) {
                root.add(new DefaultMutableTreeNode(dbName));
            }
            
            
            DefaultTreeModel tmodel = new DefaultTreeModel(root);
            
            jTabbedPane1.removeTabAt(0);
            JTree tree = new JTree(tmodel);
            
            JScrollPane scroll = new JScrollPane();
            scroll.setViewportView(tree);
            jTabbedPane1.addTab("Databases", scroll);
        } catch (SQLException | ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(this, "Contraseña de root errónea", "Error", JOptionPane.ERROR_MESSAGE);
        }


    }//GEN-LAST:event_btnEnterActionPerformed

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new QueryDrawer().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEnter;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JPanel pnlRootPass;
    private javax.swing.JPasswordField txtRootPass;
    // End of variables declaration//GEN-END:variables
}
