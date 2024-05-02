package week6;
import java.util.*;


public class Mahasiswa{

    private ArrayList<String> nama = new ArrayList<String>();
    private ArrayList<String> nim = new ArrayList<String>();
    private ArrayList<int> semester = new ArrayList<int>();
    private ArrayList<int> sks = new ArrayList<int>();
    private int biaya_kuliah, spp;

    public void InsertIdentitas(int n1)
    {
        nim.add(n1);
    }
    
    public void InsertIdentitas(int n1)
    {
        nim.add(n1);
    }

    public void InsertIdentitas(int n1)
    {
        semester.add(n1);
    }

    public int getBiaya()
    {
        if(semester<=2)
        {
            biaya_kuliah = (300000*this.sks)+this.spp;
            
        }
        if((semester<=6) && (semester>=3))
        {
            biaya_kuliah = (275000*this.sks)+this.spp;
        }
        if(semester>=7)
        {
            biaya_kuliah = (250000*this.sks)+this.spp;
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

     public void getResult()
    {
        System.out.println("Daftar Mahasiswa");
        Iterator<String> nm = nama.iterator();
        Iterator<String> n = nim.iterator();
        Iterator<int> smt = semester.iterator();
        Iterator<int> sk = sks.iterator();
        while(n.hasNext())
        {
            System.out.println("Nama: ");
            System.out.println(nm.next());
            System.out.println("NIM: ");
            System.out.println(n.next());
            System.out.println("Semester: ");
            System.out.println(smt.next());
            System.out.println("SKS: ");
            System.out.println(sk.next());
            System.out.println("SPP: ");
            System.out.println(getSPP());
            System.out.println("Biaya Kuliah");
            System.out.println(getBiaya());
        }
    }
}