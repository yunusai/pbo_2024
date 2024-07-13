package RpG;

public class Boss extends Karakter {
    public Boss() {
        super("Satan", 6666, 666); // Boss: Satan HP=10000, ATK=666
        // this.nama = "Satan";
        // this.hp = 6666;
        // this.atk = 666;
    }

    @Override
    public void status() {
        System.out.println(getNama() + " Status - HP: " + getHP() + ", ATK: " + atk);
    }

    @Override
    public int serang() {
        double batasBawah = atk - (0.1 * atk);
        double batasAtas = atk + (0.1 * atk);
        return (int) (batasBawah + Math.random() * (batasAtas - batasBawah));
    }
}
