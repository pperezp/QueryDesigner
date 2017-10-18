package view;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import model.Field;
import model.Query;
import model.Table;

/**
 *
 * @author prez
 */
public class PanelTable extends JPanel {

    private final Table table;
    private final List<JCheckBox> chkFields;

    public PanelTable(Table table) {
        this.table = table;

        super.setBorder(BorderFactory.createTitledBorder(table.getName()));
        super.setLayout(new java.awt.GridLayout(table.getFields().size(), 1));

        chkFields = new ArrayList<>();

        generateCheckBoxes();
    }

    private void generateCheckBoxes() {
        JCheckBox chk;
        for (Field field : table.getFields()) {
            chk = new JCheckBox();

            if (field.isPK()) {
                chk.setForeground(Color.red);
                chk.setText(field.getName() + " (PK)");
            } else if (field.isFK()) {
                chk.setForeground(Color.blue);
                chk.setText(field.getName() + " (FK)");
            } else {
                chk.setText(field.getName());
            }
            
            chk.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                }

                @Override
                public void mousePressed(MouseEvent e) {
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    // acá voy
//                    Query.SELECT += table.getName()+"."+field.getName()+",";
//                    
//                    System.out.println("SELECT "+Query.SELECT+
//                                    " FROM "+Query.FROM + 
//                                    " WHERE "+Query.WHERE+";");
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                }

                @Override
                public void mouseExited(MouseEvent e) {
                }
            });
            
            chkFields.add(chk);
            super.add(chk);
        }
    }

}
