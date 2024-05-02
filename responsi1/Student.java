package responsi1;

public class Student extends Person {
    String nim;
    int sks, spp, modul;

    public Student(String name, String nim, String address, int modul, int sks, int spp, String hobi) {
        super(name, address, hobi);
        this.nim = nim;
        this.modul = modul;
        this.sks = sks;
        this.spp = spp;

    }

    public String getNim() {
        return nim;
    }

    public int hitungPembayaran() {
        int biayaSKS = 250000;
        return (sks * biayaSKS) + spp + modul;
    }

    public void identity() {
        super.identity();
        System.out.println("NIM : " + nim);
        super.Hobi();
        System.out.println("SKS : " + sks);
        System.out.println("SPP : " + spp);
        System.out.println("Modul : " + modul);
        System.out.println("Tagihan Pembayaran : " + hitungPembayaran());
    }
}
