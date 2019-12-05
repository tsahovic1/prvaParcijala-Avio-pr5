package ba.unsa.etf.rpr;

import java.time.LocalTime;

public class Let implements Comparable<Let>{
    private Aerodrom polazniAerodrom, dolazniAerodrom;
    private LocalTime vrijemePolaska, vrijemeDolaska;

    public Let() {
    }

    public Let(Aerodrom polazniAerodrom, Aerodrom dolazniAerodrom, LocalTime vrijemePolaska, LocalTime vrijemeDolaska) {
        this.polazniAerodrom = polazniAerodrom;
        this.dolazniAerodrom = dolazniAerodrom;
        this.vrijemePolaska = vrijemePolaska;
        this.vrijemeDolaska = vrijemeDolaska;
    }

    public Aerodrom getPolazniAerodrom() {
        return polazniAerodrom;
    }

    public void setPolazniAerodrom(Aerodrom polazniAerodrom) {
        this.polazniAerodrom = polazniAerodrom;
    }

    public Aerodrom getDolazniAerodrom() {
        return dolazniAerodrom;
    }

    public void setDolazniAerodrom(Aerodrom dolazniAerodrom) {
        this.dolazniAerodrom = dolazniAerodrom;
    }

    public LocalTime getVrijemePolaska() {
        return vrijemePolaska;
    }

    public void setVrijemePolaska(LocalTime vrijemePolaska) {
        this.vrijemePolaska = vrijemePolaska;
    }

    public LocalTime getVrijemeDolaska() {
        return vrijemeDolaska;
    }

    public void setVrijemeDolaska(LocalTime vrijemeDolaska) {
        this.vrijemeDolaska = vrijemeDolaska;
    }
    public int trajanje(){
        if(vrijemePolaska.getMinute() > vrijemeDolaska.getMinute()){
            return vrijemeDolaska.getMinute() + 60 - vrijemePolaska.getMinute() + (vrijemeDolaska.getHour() - 1 - vrijemePolaska.getHour())*60;
        }
        return vrijemeDolaska.getMinute() - vrijemePolaska.getMinute() + (vrijemeDolaska.getHour() - vrijemePolaska.getHour())*24;
    }
    //Zemlja je ravna ploca!!!!!
    public double duzinaLeta(){
        return Math.sqrt(Math.pow(polazniAerodrom.getDuzina() - dolazniAerodrom.getDuzina(), 2) + Math.pow(polazniAerodrom.getSirina() - dolazniAerodrom.getSirina(), 2));
    }
    //In other news ja sam multimilioner


    @Override
    public int compareTo(Let o) {
        return vrijemePolaska.compareTo(o.vrijemePolaska);
    }
}
