package rs.bane.alati.model.ucinci.dnevni;

public class RadniNalog {

    private int brojRadnogNaloga;
    private int brojOperacije;
    private String nazivOperacije;
    private double satniNormativ;
    private String nazivProizvoda;
    private String katBrojProizvoda;
    private double lansirano;

    public RadniNalog(int brojRadnogNaloga, int brojOperacije, String nazivOperacije, double satniNormativ, String nazivProizvoda, String katBrojProizvoda, double lansirano) {
        this.brojRadnogNaloga = brojRadnogNaloga;
        this.brojOperacije = brojOperacije;
        this.nazivOperacije = nazivOperacije;
        this.satniNormativ = satniNormativ;
        this.nazivProizvoda = nazivProizvoda;
        this.katBrojProizvoda = katBrojProizvoda;
        this.lansirano = lansirano;
    }

    @Override
    public String toString() {
        return "workOrderID=" + brojRadnogNaloga + ", techOperationID=" + brojOperacije + ", techOperationName="
                + nazivOperacije + ", techOutturnPerHour=" + satniNormativ + ", productName="
                + nazivProizvoda + ", productCatalogNumber=" + katBrojProizvoda;
    }

    public int getBrojRadnogNaloga() {
        return brojRadnogNaloga;
    }

    public void setBrojRadnogNaloga(int brojRadnogNaloga) {
        this.brojRadnogNaloga = brojRadnogNaloga;
    }

    public int getBrojOperacije() {
        return brojOperacije;
    }

    public void setBrojOperacije(int brojOperacije) {
        this.brojOperacije = brojOperacije;
    }

    public double getLansirano() {
        return lansirano;
    }

    public String getNazivOperacije() {
        return nazivOperacije;
    }

    public void setNazivOperacije(String nazivOperacije) {
        this.nazivOperacije = nazivOperacije;
    }

    public double getSatniNormativ() {
        return satniNormativ;
    }

    public void setSatniNormativ(double thechOutturnPerHour) {
        this.satniNormativ = thechOutturnPerHour;
    }

    public String getNazivProizvoda() {
        return nazivProizvoda;
    }

    public void setNazivProizvoda(String nazivProizvoda) {
        this.nazivProizvoda = nazivProizvoda;
    }

    public String getKatBrojProizvoda() {
        return katBrojProizvoda;
    }

    public void setKatBrojProizvoda(String katBrojProizvoda) {
        this.katBrojProizvoda = katBrojProizvoda;
    }

}
