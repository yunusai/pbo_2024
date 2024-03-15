package latihan1;

public class nomor2
{
    int diameter, alas, tinggi;

    public void kelilingLingkaran(int diameter)
    {
        double keliling = Math.PI*diameter;
        System.out.println("Keliling lingkaran dengan diameter "+diameter+" adalah "+keliling);
    }

    public void luasSegitiga(int alas, int tinggi)
    {
        double luas = 0.5*alas*tinggi;
        System.out.println("Luas segitiga siku-siku dengan alas "+alas+" dan tinggi "+tinggi+ " adalah "+luas);
    }

    public void volumeTabung(int diameter, int tinggi)
    {
        double volume = Math.PI*(diameter/2)*(diameter/2)*tinggi;
        System.out.println("Volume tabung dengan diameter "+diameter+" dan tinggi "+tinggi+" adalah "+volume);
    }

    public static void main (String[] args)
    {
        nomor2 lingkaran = new nomor2();
        lingkaran.diameter= 10;
        lingkaran.kelilingLingkaran(lingkaran.diameter);

        nomor2 segitiga = new nomor2();
        segitiga.alas = 6;
        segitiga.tinggi = 8;
        segitiga.luasSegitiga(segitiga.alas, segitiga.tinggi);

        nomor2 tabung = new nomor2();
        tabung.diameter = 5;
        tabung.tinggi = 10;
        tabung.volumeTabung(tabung.diameter, tabung.tinggi);
    }
}