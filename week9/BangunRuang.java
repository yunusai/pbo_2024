package week9;

public class BangunRuang extends BangunDatar
{
    private int tinggi;

    public BangunRuang(int panjang, int lebar, int tinggi)
    {
        super(panjang, lebar);
        this.tinggi = tinggi;
    }

    public int volume()
    {
        return this.tinggi*super.getPanjang()*super.getLebar();
    }

    public void getInfo()
    {
        System.out.println("Nama: Balok");
        System.out.println("Panjang: " + super.getPanjang());
        System.out.println("Lebar: " + super.getLebar());
        System.out.println("Lebar: " + this.tinggi);
        System.out.println("Volume: " + volume());
    }
}