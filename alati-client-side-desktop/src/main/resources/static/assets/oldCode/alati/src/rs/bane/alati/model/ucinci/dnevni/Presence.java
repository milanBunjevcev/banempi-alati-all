package rs.bane.alati.model.ucinci.dnevni;

import java.util.Date;

public class Presence {

    private Long id;
    private Date date;
    private double hours;
    private Worker worker;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getHours() {
        return hours;
    }

    public void setHours(double hours) {
        this.hours = hours;
    }

    public Worker getWorker() {
        return worker;
    }

    public void setWorker(Worker worker) {
        this.worker = worker;
        if (!worker.getPresences().contains(this)) {
            worker.getPresences().add(this);
        }
    }

}
