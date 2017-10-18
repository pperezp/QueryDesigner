package view;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
/**
 * Clase para dibujar los checkbox de las tablas de una base de datos en especifico
 * @author prez
 */
public class PanelTablesOfDatabase extends JPanel {

    private List<String> tables;
    private List<JCheckBox> chkTablesName;

    public PanelTablesOfDatabase(String dbName, List<String> tables) {
        this.tables = tables;

        super.setBorder(BorderFactory.createTitledBorder(dbName));
        super.setLayout(new java.awt.GridLayout(tables.size(), 1));

        chkTablesName = new ArrayList<>();

        generateCheckBoxes();
    }

    private void generateCheckBoxes() {
        JCheckBox chk;
        for (String tableName : tables) {
            chk = new JCheckBox();

            chk.setText(tableName);
            
            chkTablesName.add(chk);
            super.add(chk);
        }
    }
    
    public List<String> getSelectedTablesName(){
        List<String> names = new ArrayList<>();
        
        for (JCheckBox chkField : chkTablesName) {
            if(chkField.isSelected()){
                names.add(chkField.getText());
            }
        }
        
        return names;
    }

}
