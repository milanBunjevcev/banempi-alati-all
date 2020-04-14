package rs.bane.alati.model.ucinci.dnevni;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class WorkerOutputItem {

    private Long id;
    private int brrn;
    private int broj;
    private String nazivOperacije;
    private double norma;
    private String nazivArtikla;
    private String kataloskiBroj;
    private double proizvedenoKolicina;
    private double utrosenoVreme;
    private String napomena;
    private Date datumUcinka;

    private List<Worker> workers = new ArrayList<Worker>();

    private Location location;

    private Timestamp vremeUnosaUcinka;
    private String uneoKorisnik;

}
