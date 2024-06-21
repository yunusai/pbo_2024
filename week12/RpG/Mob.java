package RpG;

public class Mob extends Karakter {
    public Mob(int stage) {
        super(stage == 1 ? "Goblin" : "Orc", stage == 1 ? 50 : 500, stage == 1 ? 50 : 100); // Stage 1: Goblin HP=50, ATK=50; Stage 2: Orc HP=500, ATK=100
    }
}
