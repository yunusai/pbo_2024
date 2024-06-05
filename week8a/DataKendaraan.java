package week8a;

public class DataKendaraan
{
    public static void main(String[] args)
    {
        Mobil spr4 = new Mobil("Supra Mk 4", "Diesel", "Solar", 2, 270, 4, "Toyota", "Manual");
        spr4.infoMobil();

        Motor sprx = new Motor("Supra X 125", "SOHC", "Bensin", 2, 118, 2, "Honda", "Manual");
        sprx.infoMotor();

        Pesawat b2 = new Pesawat("Northrop Grumman B-2 Spirit", "Turbofans", "", 2, 760, 17, "USA Air Force");
        b2.infoPesawat();
    }
}