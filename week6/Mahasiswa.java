package week6;
import java.util.*;


public class Mahasiswa{
    private String nama, nim;
    private int semester, sks, biaya_kuliah, spp;

    public Mahasiswa(String nama, String nim, int semester, int sks)
    {
        this.nama = nama;
        this.nim = nim;
        this.semester = semester;
        this.sks = sks;
        getSPP();
        getBiaya();
    }

    public int getBiaya()
    {
        if(semester<=2)
        {
            biaya_kuliah = (300000*this.sks);
            
        }
        if((semester<=6) && (semester>=3))
        {
            biaya_kuliah = (275000*this.sks);
        }
        if(semester>=7)
        {
            biaya_kuliah = (250000*this.sks);
        }
        return biaya_kuliah;
    }

    public int getSPP()
    {
        if(semester<=2)
        {
            spp = 3000000;
        }
        if((semester<=6) && (semester>=3))
        {
            spp = 2900000;
        }
        if(semester>=7)
        {
            spp = 2750000;
        }
        return spp;
    }

    public int getTagihan(){
        return spp+biaya_kuliah;
    }

    public void getResult(){
        System.out.println("Nama: " + nama);
        System.out.println("NIM: " + nim);
        System.out.println("Semester: " + semester);
        System.out.println("SKS: " + sks);
        System.out.println("SPP: " + spp);
        System.out.println("Biaya SKS: " + biaya_kuliah);
        System.out.println("Tagihan: " + getTagihan());
    }
}