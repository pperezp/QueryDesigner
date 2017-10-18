package view;

import java.sql.SQLException;
import javax.swing.JScrollPane;
import model.MySQL;

/**
 *
 * @author prez
 */
public class Drawer {

    private final MySQL mysql;
    private PanelTablesOfDatabase ptodb;

    public Drawer(MySQL mysql) throws SQLException, ClassNotFoundException {
        this.mysql = mysql;
    }

    /**
     * Dibuja todas las tablas con todos sus campos
     *
     * @param scrollPane
     * @throws SQLException
     */
    public void drawAllTables(JScrollPane scrollPane) throws SQLException {
        PanelDataBase pdb = new PanelDataBase(mysql.getTables(mysql.getTablesName()));
        scrollPane.setViewportView(pdb);
//        pdb.updateUI();
    }

    /**
     * Dibuja en el scroll las tablas (cuando hago doble
     * click en el tree de las base de datos)
     *
     * @param scrollPane
     * @throws java.sql.SQLException
     */
    public void drawTablesName(JScrollPane scrollPane) throws SQLException {
        ptodb = new PanelTablesOfDatabase(mysql.getDbName(), mysql.getTablesName());
        scrollPane.setViewportView(ptodb);
    }

    /**
     * Dibuja las tablas seleccionadas (en ventana de tablas de una base de datos)
     * @param scrollPane
     * @throws SQLException 
     */
    public void drawSelectedTables(JScrollPane scrollPane) throws SQLException {
        PanelDataBase pdb
                = new PanelDataBase(
                    mysql.getTables(
                        ptodb.getSelectedTablesName()
                    )
                );
        scrollPane.setViewportView(pdb);
//        pdb.updateUI();
    }
}
