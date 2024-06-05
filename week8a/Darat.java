package week8a;

public class Darat extends Kendaraan
{
    int jumlah_roda;

    public Darat(String nama, String tipe_mesin, String bahan_bakar, int kapasitas, int kecepatan, int jumlah_roda)
    {
        super(nama, tipe_mesin, bahan_bakar, kapasitas, kecepatan);
        setRoda(jumlah_roda);
    }

    public void setRoda(int jumlah_roda)
    {
        this.jumlah_roda = jumlah_roda;
    }

    public int getRoda()
    {
        return jumlah_roda;
    }
}