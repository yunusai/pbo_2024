package week5;
import week1.Mahasiswa;
import java.util.*;

public class MahasiswaArrayList
{
    public static void main(String[] args)
    {
        Mahasiswa mhs = new Mahasiswa("Yunus", "14341");
        mhs.InsertNilai(10);
        mhs.InsertNilai(70.);
        mhs.InsertNilai(80.05);
        mhs.InsertNilai(90.05);
        mhs.InsertNilai(100.05);
        mhs.InsertMatkul("Kalkulus", "A11.4330", 4);
        mhs.InsertMatkul("SBD", "A11.4220", 3);
        mhs.InsertMatkul("Fisika", "A11.4440", 2);
        mhs.getIdentitas();
    }
}