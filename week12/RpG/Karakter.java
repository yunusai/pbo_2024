package RpG;

public abstract class Karakter {
    protected String name;
    protected int hp;
    protected int atk;

    public Karakter(String name, int hp, int atk) {
        this.name = name;
        this.hp = hp;
        this.atk = atk;
    }

    public boolean isAlive() {
        return hp > 0;
    }

    public void takeDamage(int damage) {
        hp -= damage;
        if (hp < 0) {
            hp = 0;
        }
    }

    public int attack() {
        double lowerLimit = atk - (0.1 * atk);
        double upperLimit = atk + (0.1 * atk);
        return (int) (lowerLimit + Math.random() * (upperLimit - lowerLimit))+ 1;
    }

    public void displayStats() {
        System.out.println(name + " Stats - HP: " + hp + ", ATK: " + atk);
    }

    public String getName() {
        return name;
    }

    public int getHp() {
        return hp;
    }
}

