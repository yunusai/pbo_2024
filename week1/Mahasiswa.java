package week1;
public class Mahasiswa
{
    String nim, nama;
    int usia;

    public void belajar()
    {
        System.out.println(nama + " belajar");
    }//Method

    public void mainGame()
    {
        System.out.println(nama + " main game");
    }//Method
    
    public void endline()
    {
        System.out.println(" ");
    }//Method

    public void getIdentitas()
    {
        System.out.println("Nama Mahasiswa: " + nama);
        System.out.println("Nim Mahasiswa: " + nim);
        System.out.println("Usia: " + usia);
    }//Method
}//Nama class sama dengan nama folder
//Compile = javac [nama folder]\[namafile.java]
//Run = java [namafolder].[namaclass]