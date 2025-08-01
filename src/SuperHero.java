import java.util.Random;
public class SuperHero {

    private String name;
    private int baseAttack;
    private int baseDefense;
    private int health = 100;
    private String specialAbility;
    private Random random = new Random();

    private int attackBuff = 0;
    private int defenseBuff = 0;

    public SuperHero(String name, int attackPower, int defensePower, String specialAbility) {
        this.name = name;
        this.baseAttack = attackPower;
        this.baseDefense = defensePower;
    }

    public void applyRandomBuff() {
        attackBuff = 0;
        defenseBuff = 0;
        int chance = random.nextInt(100);
        if (chance < 10) {
            baseAttack += 5;
            System.out.println(" " + name + " отримує бонус до атаки (+5) !");
        } else if (chance < 20) {
            baseDefense += 5;
            System.out.println(" " + name + " отримує бонус до захисту (+5) !");
        }
    }

    public void attack(SuperHero opponent) {
        int attackPower = baseAttack + attackBuff;
        if (random.nextInt(100) < 20) {
            attackPower += 10;
            System.out.println(" " + name + " використовує суперздібність: " + specialAbility + "!");
        }

        System.out.println(name + " атакує " + opponent.name + " і завдає " + " з силою " + attackPower + " .");
        opponent.takeDamage(attackPower);
    }

    public void takeDamage(int incomingDamage) {
        int totalDefense = baseDefense + defenseBuff ;
        int effectiveDamage = Math.max(0, incomingDamage - baseDefense);
        this.health -= effectiveDamage;
        if (this.health < 0) this.health = 0;

        System.out.println(name + " отримує " + effectiveDamage + " ушкоджень після захисту.");
        System.out.println(name + " має залишок здоров'я: " + health + "\n");
    }

    public boolean isAlive() {
        return this.health > 0;
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }
    public void printStatus() {
        System.out.println(name + " — HP: " + health + ", Атака: " + baseAttack + ", Захист: " + baseDefense);
    }
}