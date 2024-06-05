package week8a;

public class Udara extends Kendaraan
{
    int ketinggian_maksimal;

    public Udara(String nama, String tipe_mesin, String bahan_bakar, int kapasitas, int kecepatan, int ketinggian_maksimal)
    {
        super(nama, tipe_mesin, bahan_bakar, kapasitas, kecepatan);
        setTinggi(ketinggian_maksimal);
    }

    public void setTinggi(int ketinggian_maksimal)
    {
        this.ketinggian_maksimal = ketinggian_maksimal;
    }

    public int getTinggi()
    {
        return ketinggian_maksimal;
    }
}