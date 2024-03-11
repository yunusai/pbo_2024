package latihan1;

public class nomor2
{
    public static void main (String[] args)
    {
        // Menghitung keliling lingkaran
        double diameterLingkaran = 10;
        double kelilingLingkaran = Math.PI*diameterLingkaran;
        System.out.println("Keliling lingkaran dengan diameter 10 adalah "+kelilingLingkaran);

        // Menghitung luas segitiga siku-siku
        double alasSegitiga = 6;
        double tinggiSegitiga = 8;
        double luasSegitiga = 0.5*alasSegitiga*tinggiSegitiga;
        System.out.println("Luas segitiga siku-siku dengan alas 6 dan tinggi 8 adalah "+luasSegitiga);

        // Menghitung volume tabung
        diameterLingkaran = 5;
        double tinggiTabung = 10;
        double volumeTabung = Math.PI*(diameterLingkaran/2)*(diameterLingkaran/2)*tinggiTabung;
        System.out.println("Volume tabung dengan diameter 5 dan tinggi 10 adalah "+volumeTabung);
    }
}