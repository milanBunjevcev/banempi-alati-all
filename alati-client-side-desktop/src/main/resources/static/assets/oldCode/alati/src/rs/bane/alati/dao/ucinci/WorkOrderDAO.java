/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.bane.alati.dao.ucinci;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import rs.bane.alati.dao.ConnectionManager;
import rs.bane.alati.model.ucinci.dnevni.RadniNalog;

/**
 *
 * @author bunjac
 */
public class WorkOrderDAO {

    public static RadniNalog getOneByBrrnAndByBroj(int workOrderID, int techOperationID) throws Exception {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        RadniNalog temp = null;
        try {
            stmt = ConnectionManager.getIssupConnection().prepareStatement("select orn.naziv, tp.kol_norma, a.naziv, a.kat_broj, orn.kol_lan "
                    + "from pp_operacije_r_n orn "
                    + "inner join pp_radni_nalozi rn on orn.brrn = rn.brrn "
                    + "inner join np_teh_operacije tp on ((rn.brtp = tp.brtp)and(orn.broj = tp.broj)) "
                    + "inner join ma_artikli a on rn.ident = a.ident "
                    + "where orn.brrn = ? and orn.broj = ? ;");
            stmt.setInt(1, workOrderID);
            stmt.setInt(2, techOperationID);
            rs = stmt.executeQuery();
            while (rs.next()) {
                String techOperationName = rs.getString(1);
                double techOutturnPerHour = Double.parseDouble(rs.getString(2));
                String productName = rs.getString(3);
                String productCatalogNumber = rs.getString(4);
                double lansirano = rs.getDouble(5);
                temp = new RadniNalog(workOrderID, techOperationID, techOperationName, techOutturnPerHour,
                        productName, productCatalogNumber, lansirano);
            }
            return temp;
        } finally {
            stmt.close();
            rs.close();
        }
    }

    public static List<RadniNalog> getAllByBrrn(int workOrderID) throws Exception {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<RadniNalog> lista = new ArrayList<>();
        try {
            stmt = ConnectionManager.getIssupConnection().prepareStatement("select orn.naziv, tp.kol_norma, a.naziv, a.kat_broj, orn.broj, orn.kol_lan "
                    + "from pp_operacije_r_n orn "
                    + "inner join pp_radni_nalozi rn on orn.brrn = rn.brrn "
                    + "inner join np_teh_operacije tp on ((rn.brtp = tp.brtp)and(orn.broj = tp.broj)) "
                    + "inner join ma_artikli a on rn.ident = a.ident "
                    + "where orn.brrn = ? ;");
            stmt.setInt(1, workOrderID);
            rs = stmt.executeQuery();
            while (rs.next()) {
                String techOperationName = rs.getString(1);
                double techOutturnPerHour = Double.parseDouble(rs.getString(2));
                String productName = rs.getString(3);
                String productCatalogNumber = rs.getString(4);
                int techOperationID = rs.getInt(5);
                double lansirano = rs.getInt(6);
                RadniNalog temp = new RadniNalog(workOrderID, techOperationID, techOperationName, techOutturnPerHour,
                        productName, productCatalogNumber, lansirano);
                lista.add(temp);
            }
            return lista;
        } finally {
            stmt.close();
            rs.close();
        }
    }
}
