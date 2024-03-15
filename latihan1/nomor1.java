package latihan1;

public class nomor1 {
    String nama;
    public void Perkenalan() {
        System.out.println("Halo, nama saya adalah " + nama);
        System.out.println("Saya adalah mahasiswa Teknik Informatika Universitas Dian Nuswantoro");
        System.out.println("Saya sedang menulis program java");

    }

    public static void main(String[] args)
    {
        nomor1 saya= new nomor1();
        saya.nama="Muhammad Yunus Saifullah";
        saya.Perkenalan();
    }
        
}