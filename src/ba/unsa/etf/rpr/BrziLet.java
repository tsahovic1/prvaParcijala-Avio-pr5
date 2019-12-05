package ba.unsa.etf.rpr;

import java.time.LocalTime;

public class BrziLet extends Let {
    public BrziLet(Aerodrom polazniAerodrom, Aerodrom dolazniAerodrom, LocalTime vrijemePolaska, LocalTime vrijemeDolaska) {
        super(polazniAerodrom, dolazniAerodrom, vrijemePolaska, vrijemeDolaska);
    }

    @Override
    public double duzinaLeta() {
        return super.duzinaLeta() / 2;
    }
}

