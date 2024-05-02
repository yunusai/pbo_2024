package week2;

public class krs {

    String[] matakuliah = new String[5];
    int[] sks = new int[5];
    int totalsks = 0;

    public void setMataKuliah(String matkul, int index) {
        matakuliah[index] = matkul;
    }

    public void setSks(int kredit, int index) {
        sks[index] = kredit;
    }

    public void sum0fSks() {
        for (int i = 0; i < sks.length; i++) {
            totalsks = totalsks + sks[i];
        }

    }

    public void infoKrs() {
        for (int i = 0; i < getArrySize(matakuliah); i++) {
            System.out.println("Nama mata kuliah : " + matakuliah[i] + " SKS: " + sks[i]);
        }
        sum0fSks();
        System.out.println("Total SKS : " + totalsks);
    }

    public int getArrySize(String[] arr) {
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != null) {
                count++;
            }
        }
        return count;
    }
}