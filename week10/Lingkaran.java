package week10;

public class Lingkaran extends BangunDatar implements Karakteristik
{
    private int diameter;
    private double pi =3.14;

    public Lingkaran(int diameter)
    {
        this.diameter = diameter;
    }

    public int getDiameter()
    {
        return diameter;
    }

    public double hitungLuas()
    {
        return (diameter*diameter*pi)/4;
    }

    @Override
    public void jumlahSudut()
    {
        System.out.println("Jumlah Sudut = 0");
    }
    @Override
    public void jumlahRusuk()
    {
        System.out.println("Jumlah Sudut = 1");
    }

    @Override
    public void luas()
    {
        System.out.println("Luas lingkaran dengan diameter = "+diameter+" adalah "+hitungLuas());
    }

    public double hitungKeliling()
    {
        return pi*diameter;
    }

    @Override
    public void keliling()
    {
        System.out.println("Luas lingkaran dengan diamater = "+diameter+" adalah "+hitungKeliling());
    }

}