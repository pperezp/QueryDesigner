package test;

import java.awt.Color;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import model.Field;
import model.MySQL;
import model.Table;

/**
 *
 * @author prez
 */
public class Test extends javax.swing.JFrame {

    private MySQL mysql;

    public Test() {
        try {
            initComponents();

            mysql = new MySQL("uber", "123456");

            List<String> tableNames = mysql.getTablesName();

//            for (Table table : mysql.getTables(tableNames)) {
//                System.out.println();
//                System.out.println(table);
//                System.out.println();
//                for (Field field : table.getFields()) {
//                    System.out.println(field);
//                    if(field.isFK()){
//                        System.out.println(table.getReference(field));
//                    }
//                }
//            }
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        pnlGeneral = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton1.setText("jButton1");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jScrollPane1.setViewportView(pnlGeneral);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 807, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addGap(0, 435, Short.MAX_VALUE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            
            List<Table> tables = mysql.getTables(mysql.getTablesName());

            for (Table table : tables) {
                JPanel pnlTabla = new JPanel();
                int cantCampos = table.getFields().size();

                pnlTabla.setBorder(javax.swing.BorderFactory.createTitledBorder(table.getName()));
                pnlTabla.setLayout(new java.awt.GridLayout(cantCampos, 1));

                for (Field field : table.getFields()) {
                    JCheckBox chk = new JCheckBox();
                    
                    if(field.isPK()){
                        chk.setForeground(Color.red);
                        chk.setText(field.getName()+" (PK)");
                    }else if(field.isFK()){
                        chk.setForeground(Color.blue);
                        chk.setText(field.getName()+" (FK)");
                    }else{
                        chk.setText(field.getName());
                    }
                    
                    pnlTabla.add(chk);
                }

                pnlGeneral.add(pnlTabla);
            }

            pnlGeneral.updateUI();
        } catch (SQLException ex) {
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Test().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel pnlGeneral;
    // End of variables declaration//GEN-END:variables
}
