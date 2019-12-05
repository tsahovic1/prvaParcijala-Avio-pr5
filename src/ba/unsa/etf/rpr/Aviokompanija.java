package ba.unsa.etf.rpr;

import javax.naming.SizeLimitExceededException;
import java.time.LocalTime;
import java.util.*;
import java.util.function.Function;

public class Aviokompanija {
    private Let[] letovi;
    private int brojLetova = 0, maksimum;
    private double dajDuzinu(List<Let> l){
        double duz = 0;
        for(Let temp : l){
            duz = duz + temp.duzinaLeta();
        }return duz;
    }
    private double fun(List<Let> l, Function<Let, Double> f){
        double ret = 0;
        for(Let temp : l){
            ret = ret + f.apply(temp);
        }return ret;
    }
    private boolean uporediVrijeme(LocalTime t1, LocalTime t2){
        if(t1.getHour() == t2.getHour()){
            if(t1.getMinute() == t2.getMinute()){
                return t1.getSecond() > t2.getSecond();
            }return t1.getMinute() > t2.getMinute();
        }return t1.getHour() > t2.getHour();
    }

    public Aviokompanija(int maksimum) {
        letovi = new Let[maksimum];
        this.maksimum = maksimum;
    }
    public int brojLetova(){
        return brojLetova;
    }
    public void registrujLet(Aerodrom polazniAerodrom, Aerodrom dolazniAerodrom, LocalTime vrijemePolaska, LocalTime vrijemeDolaska, boolean brzi) throws SizeLimitExceededException {
        if(brojLetova + 1 > maksimum) throw new SizeLimitExceededException("Ne moze vise stat");
        if(brzi) letovi[brojLetova++] = new BrziLet(polazniAerodrom, dolazniAerodrom, vrijemePolaska, vrijemeDolaska);
        else letovi[brojLetova++] = new Let(polazniAerodrom, dolazniAerodrom, vrijemePolaska, vrijemeDolaska);
    }
    public Map<String, List<Let>> dolazniLetovi(){
        Map<String, List<Let>> ret = new HashMap<>();
        for(int i = 0; i < brojLetova; i++){
            if(ret.containsKey(letovi[i].getDolazniAerodrom().getGrad())){
                ret.get(letovi[i].getDolazniAerodrom().getGrad()).add(letovi[i]);
            }else{
                List<Let> temp = new ArrayList<>();
                temp.add(letovi[i]);
                ret.put(letovi[i].getDolazniAerodrom().getGrad(), temp);
            }
        }return ret;
    }
    public Set<Let> uZraku(LocalTime vrijeme){
        Set<Let> ret = new TreeSet<>();
        for(int i = 0; i < brojLetova; i++){
            if(vrijeme.compareTo(letovi[i].getVrijemePolaska()) > 0 && vrijeme.compareTo(letovi[i].getVrijemeDolaska()) < 0){
                ret.add(letovi[i]);
            }
        }return ret;
    }
    public ArrayList<Let> nadjiNajkraci(String polazni, String odredisni){  //ko uradi u jednoj liniji skidam kapu
        ArrayList<Let> l = null, temp = new ArrayList<>();
        for(int i = 0; i < brojLetova; i++){
            if(letovi[i].getPolazniAerodrom().getGrad().equals(polazni)){
                temp.add(letovi[i]);
                String grad = letovi[i].getDolazniAerodrom().getGrad();
                LocalTime tmp = letovi[i].getVrijemeDolaska();
                int j = 0;
                while(j < brojLetova && grad != odredisni){
                    if(letovi[j].getPolazniAerodrom().getGrad().equals(grad) && uporediVrijeme(letovi[j].getVrijemePolaska(), tmp)){
                        temp.add(letovi[j]);
                        grad = letovi[j].getDolazniAerodrom().getGrad();
                        tmp = letovi[i].getVrijemeDolaska();
                        j = 0;
                    }j++;
                }
                if(grad.equals(odredisni)) {
                    if (l != null) {
                        if (dajDuzinu(temp) < dajDuzinu(l)) {
                            l = temp;
                        }
                    } else {
                        l = temp;
                    }
                }temp = new ArrayList<>();
            }
        }return l;
    }
    public ArrayList<Let> nadjiNajbolji(String polazni, String odredisni, Function<Let, Double> func){  //nemam zivaca vise radit lp
        ArrayList<Let> l = null, temp = new ArrayList<>();
        for(int i = 0; i < brojLetova; i++){
            if(letovi[i].getPolazniAerodrom().getGrad().equals(polazni)){
                temp.add(letovi[i]);
                String grad = letovi[i].getDolazniAerodrom().getGrad();
                LocalTime tmp = letovi[i].getVrijemeDolaska();
                int j = 0;
                while(j < brojLetova && grad != odredisni){
                    if(letovi[j].getPolazniAerodrom().getGrad().equals(grad) && uporediVrijeme(letovi[j].getVrijemePolaska(), tmp)){
                        temp.add(letovi[j]);
                        grad = letovi[j].getDolazniAerodrom().getGrad();
                        tmp = letovi[i].getVrijemePolaska();
                        j = 0;
                    }j++;
                }if(grad.equals(odredisni)) {
                    if (l != null) {
                        if (fun(temp, func) < fun(l, func)) { // u zadatku pise da se vece uzima, a u testovima obrnuto???
                            l = temp;
                        }
                    } else {
                        l = temp;
                    }
                }temp = new ArrayList<>();
            }
        }return l;
    }
}
