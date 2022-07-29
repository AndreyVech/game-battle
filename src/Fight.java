import java.util.ArrayList;

public class Fight {
    static String string;

    public static void fight(Fighters hero, Fighters monster/*, Realm.FightCallback fightCallback*/) {
        ControllerStart cs = new ControllerStart();
        Runnable runnable = () -> {
            int turn = 1;
            boolean isFightEnded = false;
            while (!isFightEnded) {
                string = "----Ход: " + turn + "----";
                cs.addTextToTextArea(string);
                if (turn++ % 2 != 0) {
                    isFightEnded = makeHit(monster, hero/*, fightCallback*/);
                } else {
                    isFightEnded = makeHit(hero, monster/*, fightCallback*/);
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
    }
    private static Boolean makeHit(Fighters defender, Fighters attacker/*, Realm.FightCallback fightCallback*/) {
        ControllerStart cs = new ControllerStart();

        int hit = attacker.attack();
        int defenderHealth = defender.getHealthPoints() - hit;
        if (hit != 0) {
            string = String.format("%s Нанес удар в %d единиц!", attacker.getName(), hit);
//            ControllerStart.addTextToTextArea(string);
            cs.addTextToTextArea(string);

            string = String.format("У %s осталось %d единиц здоровья...", defender.getName(), defenderHealth);
//            ControllerStart.addTextToTextArea(string);
            cs.addTextToTextArea(string);

        } else {
            string = String.format("%s промахнулся!", attacker.getName());
//            ControllerStart.addTextToTextArea(string);
            cs.addTextToTextArea(string);

        }
        if (defenderHealth <= 0 && defender instanceof Hero) {
            string = "Извините, вы пали в бою...";
//            ControllerStart.addTextToTextArea(string);
            cs.addTextToTextArea(string);

            return true;
        } else if(defenderHealth <= 0) {
            string = String.format("Враг повержен! Вы получаете %d опыт и %d золота", defender.getXp(), defender.getGold());
//            ControllerStart.addTextToTextArea(string);
            cs.addTextToTextArea(string);
            attacker.setXp(attacker.getXp() + defender.getXp());
            attacker.setGold(attacker.getGold() + defender.getGold());
            return true;
        } else {
            defender.setHealthPoints(defenderHealth);
            return false;
        }
    }


}