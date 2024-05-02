package week6;
import java.util.*;

public class DaftarMahasiswa
{
    public static void main(String[] args)
    {
        int n;
        System.out.println("Masukkan jumlah mahasiswa:");
        //buat object class scanner untuk input data
        Scanner inp = new Scanner(System.in);
        n = inp.nextInt();

        //buat variabel array tipe mahasiswa
        Mahasiswa[] mhs = new Mahasiswa[n];

        String nama;
        String nim;
        int semester;
        int sks;

        for(int i = 0; i < n; i++)
        {
            System.out.println("\nMahasiswa "+(i+1));
            System.out.print("Masukkan Nama: ");
            nama = inp.next();
            System.out.print("Masukkan NIM: ");
            nim = inp.next();
            System.out.print("Masukkan Semester: ");
            semester = inp.nextInt();
            System.out.print("Masukkan SKS: ");
            sks = inp.nextInt();

            mhs[i] = new Mahasiswa(nim,nama, semester, sks);
        }
        System.out.println("\n- - - Daftar Mahasiswa - - -\n");
        for(int i = 0; i < n; i++)
        {    
            System.out.println("\nMahasiswa "+(i+1));
            mhs[i].getResult();
        }
    }
}