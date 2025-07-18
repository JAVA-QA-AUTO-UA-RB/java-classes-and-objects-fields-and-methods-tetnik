import java.util.Random;
public class Main {
    public static void main(String[] args) {

        SuperHero flash = new SuperHero("Флеш", 30, 10, "Швидкісний удар");
        SuperHero ironMan = new SuperHero("Залізна Людина", 35, 20, "Ракетний залп");
        SuperHero cap = new SuperHero("Капітан Америка", 25, 15, "Щитовий Блок");

        SuperHero[] heroes = { flash, ironMan, cap };
        int round = 1;
        int maxRounds = 9;

        System.out.println("Початок битви між 3 супергероями! Максимум раундів: " + maxRounds + "\n");

        while (countAlive(heroes) > 1 && round <= maxRounds) {
            System.out.println("➡️ Раунд " + round + ":");

            for (SuperHero attacker : heroes) {
                if (!attacker.isAlive()) continue;

                SuperHero target = chooseRandomOpponent(attacker, heroes);
                if (target == null) continue;

                attacker.applyRandomBuff();
                attacker.attack(target);
                System.out.println(target.getName() + " має залишок здоров'я: " + target.getHealth());
                System.out.println();
            }

            round++;
        }


        int aliveCount = countAlive(heroes);
        if (aliveCount == 1) {
            for (SuperHero hero : heroes) {
                if (hero.isAlive()) {
                    System.out.println("🏆 Переможець: " + hero.getName() + "!");
                    break;
                }
            }
        } else {
            System.out.println("Битва завершилась нічиєю після " + maxRounds + " раундів!");
        }
    }

    private static int countAlive(SuperHero[] heroes) {
        int count = 0;
        for (SuperHero h : heroes) {
            if (h.isAlive()) count++;
        }
        return count;
    }

    private static SuperHero chooseRandomOpponent(SuperHero attacker, SuperHero[] heroes) {
        java.util.List<SuperHero> opponents = new java.util.ArrayList<>();
        for (SuperHero h : heroes) {
            if (h != attacker && h.isAlive()) {
                opponents.add(h);
            }
        }

        if (opponents.isEmpty()) return null;

        int index = (int) (Math.random() * opponents.size());
        return opponents.get(index);
    }
}