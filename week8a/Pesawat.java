package week8a;

public class Pesawat extends Udara
{
    String maskapai;

    public Pesawat(String nama, String tipe_mesin, String bahan_bakar, int kapasitas, int kecepatan, int ketinggian_maksimal, String maskapai)
    {
        super(nama, tipe_mesin, bahan_bakar, kapasitas, kecepatan, ketinggian_maksimal);
        setMaskapai(maskapai);
    }

    public void setMaskapai(String maskapai)
    {
        this.maskapai = maskapai;
    }

    public String getMaskapai()
    {
        return maskapai;
    }

    public void infoPesawat()
    {
        System.out.println("Nama: " + super.getNama());
        System.out.println("Tipe Mesin: " + super.getMesin());
        System.out.println("Bahan Bakar: " + super.getBahanBakar());
        System.out.println("Kapasitas: " + super.getKapasitas() +" Orang");
        System.out.println("Kecepatan: " + super.getKecepatan()+ " kmph");
        System.out.println("Ketinggian Maksimal: " + super.getTinggi() + " km");
        System.out.println("Maskapai: " + getMaskapai());
    }
}