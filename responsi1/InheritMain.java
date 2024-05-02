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

        Student mhs1 = new Student(name, nim, address, modul, sks, spp);
        Person ps1 = new Person(name, nim, address, modul, sks, spp, hobi);
        System.out.println("\nData Mahasiswa\n");
        mhs1.getStudent();
        System.out.println("\nData Orang\n");
        ps1.getPerson();
    }
}
