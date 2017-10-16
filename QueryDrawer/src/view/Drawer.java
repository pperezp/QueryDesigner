package view;

import java.sql.SQLException;
import javax.swing.JScrollPane;
import model.MySQL;

/**
 *
 * @author prez
 */
public class Drawer {

    private MySQL mysql;

    public Drawer(MySQL mysql) throws SQLException, ClassNotFoundException {
        this.mysql = mysql;
    }

    public void draw(JScrollPane scrollPane) throws SQLException {
        PanelDataBase pdb = new PanelDataBase(mysql.getTables(mysql.getTablesName()));
        scrollPane.setViewportView(pdb);
//        pdb.updateUI();
    }
}
