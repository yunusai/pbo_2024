package week9;

public class BangunDatar
{
    private int panjang;
    private int lebar;
    private int diameter;
    private int sisi;
    private static final double pi = 3.14;

    public BangunDatar(int sisi)
    {
        this.sisi = sisi;
    }

    public BangunDatar()
    {
        this.diameter = diameter;
    }

    public BangunDatar(int panjang, int lebar)
    {
        this.panjang = panjang;
        this.lebar = lebar;
    }

    public void setDiameter(int x)
    {
        this.diameter = x;
    }
    
    public int getDiameter()
    {
        return this.diameter;
    }

    public int getPanjang()
    {
        return this.panjang;
    }
    public int getLebar()
    {
        return this.lebar;
    }

    public int luas(int x)
    {
        return x*x;
    }

    public double luas(int x, double pi)
    {
        return (x*x*pi)/4;
    }

    public int luas(int x, int y)
    {
        return x*y;
    }

    public void getInfo()
    {
        if(sisi > 0)
        {
            System.out.println("Nama: Persegi");
            System.out.println("Sisi: " + this.sisi);
            System.out.println("Luas: " + luas(sisi));
        }

        if((panjang > 0) && (lebar > 0))
        {
            System.out.println("Nama: Persegi Panjang");
            System.out.println("Panjang: " + this.panjang);
            System.out.println("Lebar: " + this.lebar);
            System.out.println("Luas: " + luas(panjang, lebar));
        }

        if(diameter > 0)
        {
            System.out.println("Nama: Lingkaran");
            System.out.println("Diameter: " + getDiameter());
            System.out.println("Luas: " + luas(getDiameter(), pi));
        }
    }

}