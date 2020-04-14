/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.bane.alati.dao.ucinci;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import rs.bane.alati.dao.ConnectionManager;
import rs.bane.alati.model.ucinci.dnevni.Worker;

/**
 *
 * @author bunjac
 */
public class WorkerDAO {

    public static boolean add(Worker newWorker) throws Exception {
        PreparedStatement stmt = null;
        try {
            stmt = ConnectionManager.getUcinciConnection().prepareStatement("insert into radnici values( ? );");
            stmt.setString(1, newWorker.getNameFull());
            int updated = stmt.executeUpdate();
            return updated > 0;
        } finally {
            try {
                stmt.close();
            } catch (SQLException ex) {
                Logger.getLogger(Worker.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static List<Worker> getAll() throws Exception {
        List<Worker> workers = new ArrayList<Worker>();
        Statement stmt = null;
        try {
            stmt = ConnectionManager.getUcinciConnection().createStatement();
            ResultSet rs = stmt.executeQuery("select * from radnici"
                    + " order by ime_radnika;");
            while (rs.next()) {
                String name = rs.getString("ime_radnika");
                workers.add(new Worker(name));
            }
            return workers;
        } finally {
            try {
                stmt.close();
            } catch (SQLException ex) {
                Logger.getLogger(Worker.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public static boolean delete(String worker) throws Exception {
        PreparedStatement stmt = null;
        try {
            stmt = ConnectionManager.getUcinciConnection().prepareStatement("delete from radnici where ime_radnika = ? ;");
            stmt.setString(1, worker);
            return 0 < stmt.executeUpdate();
        } finally {
            try {
                stmt.close();
            } catch (SQLException ex) {
                Logger.getLogger(Worker.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
