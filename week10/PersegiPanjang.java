package week10;

public class PersegiPanjang extends BangunDatar implements Karakteristik
{
    private int panjang;
    private int lebar;

    public PersegiPanjang(int panjang, int lebar)
    {
        this.panjang = panjang;
        this.lebar = lebar;
    }

    public int getPanjang()
    {
        return panjang;
    }

    public int getLebar()
    {
        return lebar;
    }

    public int hitungLuas()
    {
        return panjang*lebar;
    }

    @Override
    public void jumlahSudut()
    {
        System.out.println("Jumlah Sudut = 8");
    }
    @Override
    public void jumlahRusuk()
    {
        System.out.println("Jumlah Sudut = 6");
    }

    @Override
    public void luas()
    {
        System.out.println("Luas persegi panjang dengan panjang = "+panjang+" dan lebar = "+lebar+" adalah "+hitungLuas());
    }

    public int hitungKeliling()
    {
        return (2*panjang)+(2*lebar);
    }

    @Override
    public void keliling()
    {
        System.out.println("Luas persegi panjang dengan panjang = "+panjang+" dan lebar = "+lebar+" adalah "+hitungKeliling());
    }

}