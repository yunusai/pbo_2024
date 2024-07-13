package RpG;

public abstract class Karakter {
    protected String nama;
    protected int hp;
    protected int atk;

    public abstract int serang();

    public abstract void status();
    
    public Karakter(String nama, int hp, int atk) {
        this.nama = nama;
        this.hp = hp;
        this.atk = atk;
    }

    public boolean hidup() {
        return hp > 0;
    }

    public void kenaDamage(int damage) {
        hp -= damage;
        if (hp < 0) {
            hp = 0;
        }
    }

    public String getNama() {
        return nama;
    }

    public int getHP() {
        return hp;
    }

    public int getAtk() {
        return atk;
    }
}

