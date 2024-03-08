package week1;
public class AksiMahasiswa
{
    public static void main(String[] args)
    {
        Mahasiswa mhs1 = new Mahasiswa();
        mhs1.nim = "A11.2022.14341";
        mhs1.nama = "Yunus";
        mhs1.usia = 3;
        mhs1.getIdentitas();
        mhs1.mainGame();
        mhs1.belajar();
        mhs1.endline();
        
        Mahasiswa mhs2 = new Mahasiswa();
        mhs2.nim = "A11.2022.14355";
        mhs2.nama = "Agus";
        mhs2.usia = 70;
        mhs2.getIdentitas();
        mhs2.mainGame();
        mhs2.belajar();
        mhs2.endline();
    }
}