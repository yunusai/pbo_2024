package week9;

public class BuatBangunDatar
{
    public static void main(String[] args)
    {
        BangunDatar psg1 = new BangunDatar(12);
        psg1.getInfo();

        BangunDatar pp1 = new BangunDatar(12, 30);
        pp1.getInfo();

        BangunDatar lk = new BangunDatar();
        lk.setDiameter(10);
        lk.getInfo();

        BangunRuang blk1 = new BangunRuang(10, 5, 400);
        blk1.getInfo();
    }
}