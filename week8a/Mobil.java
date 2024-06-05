package week8a;

public class Mobil extends Darat
{
    String pabrik, transmisi;

    public Mobil(String nama, String tipe_mesin, String bahan_bakar, int kapasitas, int kecepatan,
                     int jumlah_roda, String pabrik, String transmisi)
    {
        super(nama, tipe_mesin, bahan_bakar, kapasitas, kecepatan, jumlah_roda);
        setPabrik(pabrik);
        setTransmisi(transmisi);
    }

    public void setPabrik(String pabrik)
    {
        this.pabrik = pabrik;
    }

    public String getPabrik()
    {
        return pabrik;
    }

    public void setTransmisi(String transmisi)
    {
        this.transmisi = transmisi;
    }

    public String getTransmisi()
    {
        return transmisi;
    }

    public void infoMobil()
    {
        System.out.println("Nama: " + super.getNama());
        System.out.println("Tipe Mesin: " + super.getMesin());
        System.out.println("Bahan Bakar: " + super.getBahanBakar());
        System.out.println("Kapasitas: " + super.getKapasitas() +" Orang");
        System.out.println("Kecepatan: " + super.getKecepatan()+ " kmph");
        System.out.println("Jumlah Roda: " + super.getRoda());
        System.out.println("Pabrik: " + getPabrik());
        System.out.println("Transmisi: " + getTransmisi());
    }
}