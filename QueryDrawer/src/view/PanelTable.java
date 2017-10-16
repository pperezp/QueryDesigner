package view;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import model.Field;
import model.Table;

/**
 *
 * @author prez
 */
public class PanelTable extends JPanel {

    private Table table;
    private List<JCheckBox> chkFields;

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
            
            chkFields.add(chk);
            super.add(chk);
        }
    }

}
