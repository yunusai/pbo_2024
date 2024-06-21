package RpG;

public class Hero extends Karakter {
    private int level;
    private int maxHp;
    private int atk;
    private int exp;
    private int sisaExp;
    private int expNeeded; // Experience needed to level up
    private int score; // Total accumulated experience

    public Hero(String name) {
        super(name, 500, 10); // Initial HP = 500, ATK = 10
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty.");
        }
        this.level = 1;
        this.maxHp = 500;
        this.hp = this.maxHp; // Start with full HP
        this.atk = 10;
        this.exp = 0;
        this.expNeeded = calculateExpNeeded();
        this.score = 0;
    }

    public void levelUp() {
        level++;
        maxHp += 100; // Increase max HP by 100 on level up
        hp = maxHp; // Restore HP to max HP on level up
        atk += 10; // Increase ATK by 10 on level up
        System.out.println(name + " leveled up! Level: " + level + " HP: " + hp + " ATK: " + atk);
        expNeeded = calculateExpNeeded(); // Recalculate exp needed for next level
    }

    public void gainExp(int amount) {
        exp += amount;
        score += amount; // Update total score with gained exp
        System.out.println(name + " gained " + amount + " exp.");
        while (exp >= expNeeded) {
            sisaExp = exp - expNeeded;
            levelUp();
            exp = 0 + sisaExp;
        }
    }

    @Override
    public int attack() {
        double lowerLimit = atk - (0.1 * atk);
        double upperLimit = atk + (0.1 * atk);
        return (int) (lowerLimit + Math.random() * (upperLimit - lowerLimit)) + 1;
    }

    public void heal() {
        int healAmount = (int) (maxHp * 0.2 + Math.random() * 40 - 20);
        hp = Math.min(hp + healAmount, maxHp); // Ensure HP does not exceed max HP
        System.out.println(name + " healed for " + healAmount + " HP.");
    }

    public void block() {
        System.out.println(name + " blocks the attack!");
    }

    public boolean run() {
        boolean success = Math.random() < 0.5;
        if (success) {
            System.out.println(name + " successfully ran away!");
        } else {
            System.out.println(name + " failed to run away.");
        }
        return success;
    }

    public void rest() {
        hp = maxHp; // Restore HP to max HP
        System.out.println(name + " rested and is back to full HP.");
    }

    private int calculateExpNeeded() {
        return level * 20; // Example: Exp needed increases linearly with level
    }
    @Override
    public void displayStats() {
        System.out.println("Hero Name: " + name);
        System.out.println("Level: " + level);
        System.out.println("HP: " + hp + "/" + maxHp);
        System.out.println("ATK: " + atk);
        System.out.println("EXP: " + exp + "/" + expNeeded);
        System.out.println("Score (Total Exp Accumulated): " + score);
    }

    // Getters for individual attributes
    public int getLevel() {
        return level;
    }

    public int getMaxHp() {
        return maxHp;
    }

    public int getAtk() {
        return atk;
    }

    public int getExp() {
        return exp;
    }

    public int getExpNeeded() {
        return expNeeded;
    }

    public int getScore() {
        return score;
    }
}
