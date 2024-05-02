package responsi1;

public class Student {
    String name, nim, address, modul;
    int sks, spp;

    public Student(String name, String nim, String address, String modul, int sks, int spp) {
        this.name = name;
        this.nim = nim;
        this.address = address;
        this.modul = modul;
        this.sks = sks;
        this.spp = spp;
    }

    public int tagihanPembayaran() {
        return sks * spp;
    }

    public void getStudent() {
        System.out.println("Nama : " + name);
        System.out.println("NIM : " + nim);
        System.out.println("Alamat : " + address);
        System.out.println("Modul : " + modul);
        System.out.println("SKS : " + sks);
        System.out.println("SPP : " + spp);
        System.out.println("Tagihan Pembayaran : " + tagihanPembayaran());
    }
}
