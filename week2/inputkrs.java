package week2;

public class inputkrs {
    public static void main(String[] args) {
        krs krs1 = new krs();
        /*
         
krs1.matakuliah[0] = "Pemograman Web";
krs1.matakuliah[1] = "PBO";
krs1.matakuliah[2] = "Dasar Pemograman";
krs1.sks[0] = 3;
krs1.sks[1] = 4;
krs1.sks[2] = 2;
*/

        krs1.setMataKuliah("Pemograman Web", 0);
        krs1.setMataKuliah("PBO", 1);
        krs1.setMataKuliah("Dasar Pemograman", 2);
        // krs1.setMataKuliah("Analisa dan Perancangan", 3);

        krs1.setSks(3, 0);
        krs1.setSks(4, 1);
        krs1.setSks(2, 2);
        // krs1.setSks(2, 3);

        krs1.infoKrs();
    }
}