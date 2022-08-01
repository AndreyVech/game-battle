import javafx.scene.control.TextArea;

public class Fight {
    static String string;

    public static void fight(Fighters hero, Fighters monster) {
        ControllerStart cs = new ControllerStart();
        Runnable runnable = () -> {
            int turn = 1;
            boolean isFightEnded = false;
            while (!isFightEnded) {
                string = String.format("----Ход: " + turn + "----");
                cs.setFightHistory(new TextArea(), string);
                cs.addTextToTextArea(string);
                if (turn++ % 2 != 0) {
                    isFightEnded = makeHit(monster, hero);
                } else {
                    isFightEnded = makeHit(hero, monster);
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

    private static Boolean makeHit(Fighters defender, Fighters attacker) {
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
            cs.setHealth(0);
            return true;
        } else if (defenderHealth <= 0) {
            string = String.format("Враг повержен! Вы получаете %d опыт и %d золота", defender.getXp(), defender.getGold());
            cs.addTextToTextArea(string);
            cs.setGold(attacker.getGold() + defender.getGold());
            cs.setXp(attacker.getXp() + defender.getXp());
            attacker.setXp(attacker.getXp() + defender.getXp());
            attacker.setGold(attacker.getGold() + defender.getGold());
            return true;
        } else {
            defender.setHealthPoints(defenderHealth);
            return false;
        }
    }


}