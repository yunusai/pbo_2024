package RpG;

public class Hero extends Karakter {
    private int level;
    private int maxHp;
    private int exp;
    private int butuhExp;
    private int skor;
    private double batasAtas;
    private double batasBawah;

    public Hero(String nama) {
        super(nama, 500, 10);
        // this.nama = nama;
        // this.maxHp = 500;
        // this.atk = 10;
        if (nama == null) {
            throw new IllegalArgumentException("Nama tidak boleh kosong.");
        }
        this.level = 1;
        this.maxHp = hp;
        this.atk = 10;
        this.exp = 0;
        this.butuhExp = hitungbutuhExp();
        this.skor = 0;
    }

    public void naikLevel() {
        level++;
        maxHp += 100;
        hp = maxHp; 
        atk += 10; 
        System.out.println(nama + " Naik level!!! Level: " + level + " HP: " + hp + " ATK: " + atk);
        butuhExp = hitungbutuhExp(); 
    }

    public void dapatExp(int expDidapat) {
        exp += expDidapat;
        skor += expDidapat; 
        System.out.println(nama + " mendapat " + expDidapat + " exp.");
        while (exp >= butuhExp) {
            int sisaExp = exp - butuhExp;
            naikLevel();
            exp = sisaExp;
        }
    }

    @Override
    public int serang() {
        batasBawah = atk - (0.2 * atk);
        batasAtas = atk + (0.2 * atk);
        return (int) (batasBawah + Math.random() * (batasAtas - batasBawah));
    }

    public void heal() {
        int jumlahHeal = (int) ((maxHp * 0.2) + Math.random() * (40 - 20));
        hp = Math.min(hp + jumlahHeal, maxHp); 
        System.out.println(nama + " menyembuhkan sebanyak " + jumlahHeal + " HP.");
    }

    public void block() {
        System.out.println(nama + " berhasil menangkis serangan!");
    }

    public boolean run() {
        boolean chance = Math.random() < 0.5;
        if (chance) {
            System.out.println(nama + " berhasil kabur!");
        } else {
            System.out.println(nama + " gagal kabur.");
        }
        return chance;
    }

    public void rest() {
        hp = maxHp; // Restore HP to max HP
        System.out.println(nama + " menjadi segar bugar dan HP kembali penuh.");
    }

    private int hitungbutuhExp() {
        return level * 20;
    }
    @Override
    public void status() {
        System.out.println("Nama Hero: " + nama);
        System.out.println("Level: " + level);
        System.out.println("HP: " + hp + "/" + maxHp);
        System.out.println("ATK: " + atk);
        System.out.println("EXP: " + exp + "/" + butuhExp);
        System.out.println("Skor sekarang: " + skor);
    }

    
    public int getLevel() {
        return level;
    }

    public int getMaxHp() {
        return maxHp;
    }


    public int getExp() {
        return exp;
    }

    public int getbutuhExp() {
        return butuhExp;
    }

    public int getSkor() {
        return skor;
    }
}
