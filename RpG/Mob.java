package RpG;

public class Mob extends Karakter {
    public Mob(int stage) {
        super(stage == 1 ? "Goblin" : "Orc", stage == 1 ? 50 : 500, stage == 1 ? 50 : 100);
        // if (stage == 1) {
        //     this.nama = "Goblin";
        //     this.hp = 50;
        //     this.atk = 50;
        // } else {
        //     this.nama = "Orc";
        //     this.hp = 500;
        //     this.atk = 100;
        // }
    }

    @Override
    public void status() {
        System.out.println(getNama() + " Status - HP: " + getHP() + ", ATK: " + atk);
    }

    @Override
    public int serang() {
        double batasBawah = atk - (0.05 * atk);
        double batasAtas = atk + (0.05 * atk);
        return (int) (batasBawah + Math.random() * (batasAtas - batasBawah));
    }
}
