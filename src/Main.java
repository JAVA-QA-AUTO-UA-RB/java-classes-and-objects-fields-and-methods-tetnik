import java.util.Random;
public class Main {
    public static void main(String[] args) {

        SuperHero flash = new SuperHero("Флеш", 30, 10, "Швидкісний удар");
        SuperHero ironMan = new SuperHero("Залізна Людина", 35, 20, "Ракетний залп");
        SuperHero cap = new SuperHero("Капітан Америка", 25, 15, "Щитовий Блок");

        SuperHero[] heroes = {flash, ironMan, cap};
        int round = 1;
        int maxRounds = 9;

        System.out.println("Початок битви між " + heroes.length + " супергероями! Максимум раундів: " + maxRounds + ":\n");

        while (countAlive(heroes) > 1 && round <= maxRounds) {
            System.out.println("➡️ Починається Раунд " + round + ":");

            // Choosing current attacker and his opponent
            SuperHero currentAttacker = null;
            SuperHero currentOpponent = null;

            for (SuperHero h : heroes) {
                if (h.isAlive()) {
                    currentAttacker = h;
                    currentOpponent = chooseRandomOpponent(h, heroes);
                    break;
                }
            }

            currentAttacker.applyRandomBuff();
            currentOpponent.applyRandomBuff();

            currentAttacker.attack(currentOpponent);
            if (currentOpponent.isAlive()) {
                currentOpponent.attack(currentAttacker);
            }


            round++;
        }

        if (countAlive(heroes) == 1) {
            for (SuperHero hero : heroes) {
                if (hero.isAlive()) {
                    System.out.println("🏆 Переможець: " + hero.getName() + "!");
                    break;
                }
            }
        } else {
            System.out.println("Битва завершилась нічиєю після " + maxRounds + " раундів!");
            System.out.println("Вижили такі герої!");
            for (SuperHero hero : heroes) {
                hero.printStatus();
            }
        }
    }
    private static int countAlive(SuperHero[] heroes) {
        int count = 0;
        for (SuperHero hero : heroes) {
            if (hero.isAlive()) {
                count++;
            }
        }
        return count;
    }
    private static SuperHero chooseRandomOpponent(SuperHero attacker, SuperHero[] heroes) {
        Random rand = new Random();
        SuperHero opponent;
        do {
            opponent = heroes[rand.nextInt(heroes.length)];
        } while (!opponent.isAlive() || opponent == attacker);
        return opponent;
    }
}