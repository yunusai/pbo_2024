package week8a;

public class Kendaraan {

        String bahan_bakar, tipe_mesin, nama;
        int kapasitas, kecepatan;

        public Kendaraan(String nama, String tipe_mesin, String bahan_bakar, int kapasitas, int kecepatan)
        {
            setNama(nama);
            setMesin(tipe_mesin);
            setBahanBakar(bahan_bakar);
            setKapasitas(kapasitas);
            setKecepatan(kecepatan);
            
        }

        public void setNama(String nama)
        {
            this.nama = nama;
        }

        public String getNama()
        {
            return nama;
        }

        public void setBahanBakar(String bahan_bakar)
        {
            this.bahan_bakar = bahan_bakar;
        }

        public String getBahanBakar()
        {
            return bahan_bakar;
        }
        
        public void setMesin(String tipe_mesin)
        {
            this.tipe_mesin = tipe_mesin;
        }

        public String getMesin()
        {
            return tipe_mesin;
        }

        public void setKapasitas(int kapasitas)
        {
            this.kapasitas = kapasitas;
        }

        public int getKapasitas()
        {
            return kapasitas;
        }

        public void setKecepatan(int kecepatan)
        {
            this.kecepatan = kecepatan;
        }

        public int getKecepatan()
        {
            return kecepatan;
        }


}