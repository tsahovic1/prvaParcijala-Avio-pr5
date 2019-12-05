package ba.unsa.etf.rpr;

public class Aerodrom{
    private String nazivAerodroma = null;
    private String grad = null;
    private String sifra = null;
    private Double sirina, duzina;

    private void test(String naziv , String grad) throws IlegalnaSifraAerodroma {
        String temp = new String();
        for(int i = 0; i < grad.length(); i++){
            if(grad.charAt(i) >= 'a' && grad.charAt(i) <='z' || grad.charAt(i) >='A' && grad.charAt(i) <= 'Z'){
                temp = temp + grad.charAt(i);
            }if(temp.length() == 3) break;
        }
        temp = temp.toUpperCase();
        boolean malaSlova = false;
        if(naziv.length() != 3) throw new IlegalnaSifraAerodroma("Ilegalna sifra " + naziv + ", probajte " + temp);
        for(int i = 0; i < naziv.length(); i++){
            if(naziv.charAt(i) >= 'a' && naziv.charAt(i) <= 'z'){
                malaSlova = true;
            }else if(naziv.charAt(i) < 'A' || naziv.charAt(i) > 'Z') {
                throw new IlegalnaSifraAerodroma("Ilegalna sifra " + naziv + ", probajte " + temp);
            }
        }
        if(malaSlova){
            String ret = "Ilegalna sifra " + naziv + ", probajte ";
            naziv = naziv.toUpperCase();
            ret = ret + naziv;
            throw new IlegalnaSifraAerodroma(ret);
        }
    }

    public Aerodrom() {
    }

    public Aerodrom(String nazivAerodroma, String grad, String sifra, Double sirina, Double duzina) throws IlegalnaSifraAerodroma {
        test(sifra, grad);
        this.nazivAerodroma = nazivAerodroma;
        this.grad = grad;
        this.sifra = sifra;
        this.sirina = sirina;
        this.duzina = duzina;
    }

    public String getNazivAerodroma() {
        return nazivAerodroma;
    }

    public void setNazivAerodroma(String nazivAerodroma) {
        this.nazivAerodroma = nazivAerodroma;
    }

    public String getGrad() {
        return grad;
    }

    public void setGrad(String grad) {
        this.grad = grad;
    }

    public String getSifra() {
        return sifra;
    }

    public void setSifra(String sifra) throws IlegalnaSifraAerodroma {
        test(sifra, grad);
        this.sifra = sifra;
    }

    public Double getSirina() {
        return sirina;
    }

    public void setSirina(Double sirina) {
        this.sirina = sirina;
    }

    public Double getDuzina() {
        return duzina;
    }

    public void setDuzina(Double duzina) {
        this.duzina = duzina;
    }
}
