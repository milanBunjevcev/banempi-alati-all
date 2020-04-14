/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.bane.alati.dao.ucinci;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import rs.bane.alati.dao.ConnectionManager;
import rs.bane.alati.model.ucinci.dnevni.Location;
import rs.bane.alati.model.ucinci.dnevni.RadniNalog;
import rs.bane.alati.model.ucinci.dnevni.Worker;
import rs.bane.alati.model.ucinci.dnevni.WorkerOutputItem;

public class ProductionDAO {

    public static boolean add(WorkerOutputItem p) throws Exception {
        PreparedStatement stmt = null;
        try {
            stmt = ConnectionManager.getUcinciConnection().prepareStatement("insert into dnevni_ucinak (brrn, broj, operacija, norma, naziv_artikla, kat_broj, "
                    + "ime_radnika, proizvedeno, rad_na_vreme, napomena, datum, pogon) "
                    + "values( ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? );");
            stmt.setInt(1, p.getRadniNalog().getBrojRadnogNaloga());
            stmt.setInt(2, p.getRadniNalog().getBrojOperacije());
            stmt.setString(3, p.getRadniNalog().getNazivOperacije());
            stmt.setDouble(4, p.getRadniNalog().getSatniNormativ());
            stmt.setString(5, p.getRadniNalog().getNazivProizvoda());
            stmt.setString(6, p.getRadniNalog().getKatBrojProizvoda());
            stmt.setString(7, p.getRadnik().getNameFull());
            stmt.setDouble(8, p.getKomProizvedeno());
            stmt.setDouble(9, p.getRadPoVremenu());
            stmt.setString(10, p.getNapomena());
            stmt.setDate(11, new java.sql.Date(p.getDatum().getTime()));
            stmt.setString(12, p.getPogon().getNaziv());
            return 0 < stmt.executeUpdate();
        } finally {
            try {
                stmt.close();
            } catch (SQLException ex) {
                Logger.getLogger(WorkerOutputItem.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static boolean update(WorkerOutputItem p, int id) throws Exception {
        return false;
    }

    public static boolean delete(int id) throws Exception {
        PreparedStatement stmt = null;
        try {
            stmt = ConnectionManager.getUcinciConnection().prepareStatement("delete from dnevni_ucinak where id = ? ;");
            stmt.setInt(1, id);
            return 0 < stmt.executeUpdate();
        } finally {
            try {
                stmt.close();
            } catch (SQLException ex) {
                Logger.getLogger(WorkerOutputItem.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static List<WorkerOutputItem> findProductionByDate(String date) throws Exception {
        List<WorkerOutputItem> tempList = new ArrayList<WorkerOutputItem>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = ConnectionManager.getUcinciConnection().prepareStatement("select ime_radnika, operacija, naziv_artikla, kat_broj, napomena, proizvedeno, "
                    + "rad_na_vreme, norma, BRRN, BROJ, Datum, id, pogon from DNEVNI_UCINAK where datum = ? order by pogon, ime_radnika;");
            stmt.setDate(1, new java.sql.Date(new Date(date).getTime()));
            rs = stmt.executeQuery();
            while (rs.next()) {
                String ime_radnika = rs.getString("ime_radnika");
                String operacija = rs.getString("operacija");
                String naziv_artikla = rs.getString("naziv_artikla");
                String kat_broj = rs.getString("kat_broj");
                String napomena = rs.getString("napomena");
                double proizvedeno = Double.parseDouble(rs.getString("proizvedeno"));
                double rv = Double.parseDouble(rs.getString("rad_na_vreme"));
                double rezija = Double.parseDouble(rs.getString("rezija"));
                double norma = Double.parseDouble(rs.getString("norma"));
                int brrn = Integer.parseInt(rs.getString("brrn"));
                int broj = Integer.parseInt(rs.getString("broj"));
                Date datum = rs.getDate("datum");
                int id = Integer.parseInt(rs.getString("id"));

                Location pogon = new Location(rs.getString("pogon"));
                RadniNalog radniNalog = new RadniNalog(brrn, broj, operacija, norma, naziv_artikla, kat_broj, 0);
                Worker radnik = new Worker(ime_radnika);

                WorkerOutputItem p = new WorkerOutputItem(pogon, id, radniNalog, radnik, datum, napomena, proizvedeno, rv, rezija);
                return tempList;
            }
        } finally {
            try {
                stmt.close();
            } catch (SQLException ex) {
                Logger.getLogger(ProductionDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(ProductionDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static ArrayList<WorkerOutputItem> findProductionByDatePeriod(String d1, String d2) throws Exception {
        ArrayList<WorkerOutputItem> tempList = new ArrayList<WorkerOutputItem>();
        PreparedStatement stmt = null;
        ResultSet rs;
        try {
            stmt = ConnectionManager.getUcinciConnection().prepareStatement("select pogon, datum, ime_radnika, operacija, naziv_artikla, "
                    + "kat_broj, proizvedeno, norma, rad_na_vreme, napomena "
                    + "from dnevni_ucinak "
                    + "where datum between ? and ? "
                    + "order by ime_radnika, datum asc;");
            //+ "order by pogon, ime_radnika, datum asc;");
            stmt.setDate(1, new java.sql.Date(new Date(d1).getTime()));
            stmt.setDate(2, new java.sql.Date(new Date(d2).getTime()));
            rs = stmt.executeQuery();
            while (rs.next()) {
                String ime_radnika = rs.getString("ime_radnika");
                String operacija = rs.getString("operacija");
                String naziv_artikla = rs.getString("naziv_artikla");
                String kat_broj = rs.getString("kat_broj");
                String napomena = rs.getString("napomena");
                double proizvedeno = Double.parseDouble(rs.getString("proizvedeno"));
                double rv = Double.parseDouble(rs.getString("rad_na_vreme"));
                double rezija = 0;
                double norma = Double.parseDouble(rs.getString("norma"));
                double cena_komada = 0;
                double zarada = 0;
                int brrn = 0;
                int broj = 0;
                String datum = rs.getString("datum").substring(0, 10).replace('-', '/');
                int id = 0;
                
                Location pogon = new Location(rs.getString("pogon"));
                WorkerOutputItem p = new WorkerOutputItem(brrn, broj, operacija, norma, naziv_artikla, kat_broj, ime_radnika,
                        new Date(datum), napomena, proizvedeno, rv, rezija, zarada, id, cena_komada, pogon);
                tempList.add(p);
            }
        } catch (IllegalArgumentException iae) {
            Logger.getLogger(WorkerOutputItem.class.getName()).log(Level.SEVERE, null, iae);
            throw new IllegalArgumentException();
        } catch (SQLException ex) {
            Logger.getLogger(WorkerOutputItem.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                stmt.close();
            } catch (SQLException ex) {
                Logger.getLogger(WorkerOutputItem.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return tempList;
    }
}
