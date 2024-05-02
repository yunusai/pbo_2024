package week4;

import week1.Mahasiswa;
import java.util.*;

public class Akademik {
    public static void main(String[] args) {
        int n;
        System.out.println("Masukkan jumlah data:");
        // buat object class scanner untuk input data
        Scanner inp = new Scanner(System.in);
        n = inp.nextInt();

        // buat variabel array tipe mahasiswa
        Mahasiswa[] mhs = new Mahasiswa[n];

        String nim;
        String nama;
        double ipk;

        for (int i = 0; i < n; i++) {
            System.out.println("\nData ke-" + (i + 1));
            System.out.print("Masukkan Nama: ");
            nama = inp.next();
            System.out.print("Masukkan NIM: ");
            nim = inp.next();
            System.out.print("Masukkan IPK: ");
            ipk = inp.nextDouble();

            mhs[i] = new Mahasiswa(nim, nama, ipk);
        }

        // variabel yang dibutuhkan harus dipublic
        // for(int i = 0; i < n; i++)
        // {

        // System.out.println("\nData ke-"+(i+1));
        // System.out.println("Nama: " + mhs[i].nama);
        // System.out.println("NIM: "+ mhs[i].nim);
        // System.out.println("IPK: "+ mhs[i].ipk);
        // }

        for (int i = 0; i < n; i++) {
            System.out.println("\nData ke-" + (i + 1));
            mhs[i].getIdentitas();
        }
    }
}