package week7;
import week1.Mahasiswa;

public class MahasiswaAktif extends Mahasiswa
{
    public int usia;
    public int semester;

    public MahasiswaAktif(String nama, String nim, String tanggal_lahir, int semester)
    {
        super(nama, nim, tanggal_lahir);
        this.semester = semester;
    }


    public void getAktif()
    {
        System.out.println("Nama: " + super.getName());
        System.out.println("NIM: " + getNIM());
        System.out.println("Tanggal Lahir: " + getBirth());
        System.out.println("Semester: " + semester);
    }
}