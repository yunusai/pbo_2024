package week10;

public class BuatBangun
{
    public static void main(String[] args)
    {
        PersegiPanjang pp1 = new PersegiPanjang(5,3);
        pp1.luas();
        pp1.keliling();
        pp1.jumlahRusuk();
        pp1.jumlahSudut();
        
        Lingkaran lk1 = new Lingkaran(5);
        lk1.luas();
        lk1.keliling();
        lk1.jumlahRusuk();
        lk1.jumlahSudut();
    }
}