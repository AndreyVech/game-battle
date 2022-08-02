public class Fight {
    String string;
    Fighters hero;
    Fighters monster;

    public Fight(Fighters hero, Fighters monster) {
        this.hero = hero;
        this.monster = monster;
        fight(hero, monster);
    }

    public void fight(Fighters hero, Fighters monster) {
        Runnable runnable = () -> {
            int turn = 1;
            boolean isFightEnded = false;
            while (!isFightEnded) {
                ControllerStart.text.add(String.format("----Ход: " + turn + "----"));
                if (turn++ % 2 != 0) {
                    isFightEnded = makeHit(monster, hero);
                } else {
                    isFightEnded = makeHit(hero, monster);
                }
                try {
                    Thread.sleep(1500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
    }

    private Boolean makeHit(Fighters defender, Fighters attacker) {
        int hit = attacker.attack();
        int defenderHealth = defender.getHealthPoints() - hit;
        if (hit != 0) {
            ControllerStart.text.add(String.format("%s Нанес удар в %d единиц! У %s осталось %d единиц здоровья...", attacker.getName(), hit, defender.getName(), defenderHealth));

        } else {
            ControllerStart.text.add(String.format("%s промахнулся!", attacker.getName()));
        }
        if (defenderHealth <= 0 && defender instanceof Hero) {
            ControllerStart.text.add("Извините, вы пали в бою...");
            return true;
        } else if (defenderHealth <= 0) {
            ControllerStart.text.add(String.format("Враг повержен! Вы получаете %d опыт и %d золота", defender.getXp(), defender.getGold()));
            attacker.setXp(attacker.getXp() + defender.getXp());
            attacker.setGold(attacker.getGold() + defender.getGold());
            return true;
        } else {
            defender.setHealthPoints(defenderHealth);
            return false;
        }
    }


}