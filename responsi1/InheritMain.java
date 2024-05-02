package responsi1;

import java.util.*;

public class InheritMain {
    public static void main(String[] args) {
        Scanner inp = new Scanner(System.in);
        String name, address, nim, modul, hobi;

        int sks, spp;

        System.out.print("Masukkan Nama: ");
        name = inp.next();
        System.out.print("Masukkan NIM: ");
        nim = inp.next();
        System.out.print("Masukkan Alamat: ");
        address = inp.next();
        System.out.print("Masukkan Modul: ");
        modul = inp.next();
        System.out.print("Masukkan SKS: ");
        sks = inp.nextInt();
        System.out.print("Masukkan SPP: ");
        spp = inp.nextInt();
        System.out.print("Masukkan Hobi: ");
        hobi = inp.next();

        Person ps1 = new Person(name, nim, address, modul, sks, spp, hobi);
        System.out.println("\nData Mahasiswa\n");
        ps1.getStudent();
        ps1.Hobi();
    }
}
