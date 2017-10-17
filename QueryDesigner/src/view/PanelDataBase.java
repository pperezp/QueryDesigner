package view;

import java.util.List;
import javax.swing.JPanel;
import model.Table;

/**
 *
 * @author prez
 */
public class PanelDataBase extends JPanel {

    public PanelDataBase(List<Table> tables) {
        for (Table table : tables) {
            super.add(new PanelTable(table));
        }
    }

}
