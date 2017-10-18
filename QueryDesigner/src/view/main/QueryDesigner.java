/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.main;

import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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
public class QueryDesigner extends javax.swing.JFrame {

    private Drawer drawer;

    public QueryDesigner() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        formDataBase = new javax.swing.JFrame();
        jScrollPane2 = new javax.swing.JScrollPane();
        jButton2 = new javax.swing.JButton();
        jSplitPane1 = new javax.swing.JSplitPane();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        pnlRootPass = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtRootPass = new javax.swing.JPasswordField();
        btnEnter = new javax.swing.JButton();
        jSplitPane2 = new javax.swing.JSplitPane();
        jScrollPane3 = new javax.swing.JScrollPane();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();

        jButton2.setText("Dibujar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout formDataBaseLayout = new javax.swing.GroupLayout(formDataBase.getContentPane());
        formDataBase.getContentPane().setLayout(formDataBaseLayout);
        formDataBaseLayout.setHorizontalGroup(
            formDataBaseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2)
            .addComponent(jButton2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );
        formDataBaseLayout.setVerticalGroup(
            formDataBaseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(formDataBaseLayout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

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

        jSplitPane2.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);
        jSplitPane2.setLeftComponent(jScrollPane3);

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane4.setViewportView(jTextArea1);

        jSplitPane2.setRightComponent(jScrollPane4);

        jSplitPane1.setRightComponent(jSplitPane2);

        getContentPane().add(jSplitPane1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnEnterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEnterActionPerformed
        try {
            MySQL mysql = new MySQL(new String(txtRootPass.getPassword()));

            drawer = new Drawer(mysql);

            List<String> databasesName = mysql.getDatabasesName();

            DefaultMutableTreeNode root = new DefaultMutableTreeNode("Databases");

            DefaultMutableTreeNode node;
            for (String dbName : databasesName) {
                node = new DefaultMutableTreeNode(dbName);

                root.add(node);
            }

            DefaultTreeModel tmodel = new DefaultTreeModel(root);

            jTabbedPane1.removeTabAt(0);
            JTree tree = new JTree(tmodel);

            tree.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                }

                @Override
                public void mousePressed(MouseEvent e) {
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    if (e.getClickCount() == 2) {
                        try {
                            JTree t = (JTree) e.getComponent();

                            int selectedIndex = t.getSelectionRows()[0];
                            DefaultTreeModel dtm = (DefaultTreeModel) t.getModel();
                            String dbName = dtm.getChild(dtm.getRoot(), selectedIndex - 1).toString();

                            String pass = new String(txtRootPass.getPassword());
                            MySQL m = new MySQL(dbName, pass);

                            drawer = new Drawer(m);

                            drawer.drawTablesName(jScrollPane2);
//                            d.drawAllTables(jScrollPane1);

                            int cantCampos = m.getTablesName().size();

                            formDataBase.setBounds(0, 0, 300, (cantCampos*65 > 600?600:cantCampos*65));
                            formDataBase.setVisible(true);

                        } catch (SQLException | ClassNotFoundException ex) {
                            Logger.getLogger(QueryDesigner.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                }

                @Override
                public void mouseExited(MouseEvent e) {
                }
            });

            JScrollPane scroll = new JScrollPane();
            scroll.setViewportView(tree);
            jTabbedPane1.addTab("Databases", scroll);
        } catch (SQLException | ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(this, "Contraseña de root errónea", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnEnterActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try {
            drawer.drawSelectedTables(jScrollPane3);
        } catch (SQLException ex) {
            Logger.getLogger(QueryDesigner.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new QueryDesigner().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEnter;
    private javax.swing.JFrame formDataBase;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JSplitPane jSplitPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JPanel pnlRootPass;
    private javax.swing.JPasswordField txtRootPass;
    // End of variables declaration//GEN-END:variables
}
