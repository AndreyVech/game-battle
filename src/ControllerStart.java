import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.ArrayList;

public class ControllerStart {

    public static int xp;
    public static int gold;
    public static boolean t = true;
    public static ArrayList text = new ArrayList();
    public int health;
    public int strength;
    public int dexterity;
    String name;
    @FXML
    TextArea fightHistory;
    @FXML
    Button exit;
    @FXML
    Button startButton;
    @FXML
    TextField nameHero;
    @FXML
    TextArea levelArea;
    @FXML
    TextArea nameArea;
    @FXML
    DialogPane attentionStartBoard;
    @FXML
    RadioButton level1;
    @FXML
    RadioButton level2;
    @FXML
    RadioButton level3;
    @FXML
    DialogPane resourceBoard;
    @FXML
    Button backToStartMenu;
    @FXML
    Button goToFight;
    @FXML
    Button goToSeller;
    @FXML
    Button goToMain;
    @FXML
    Button buyHealth;
    @FXML
    Button buyStrength;
    @FXML
    Button buyDexterity;

    public static void setXp(int inXp) {
        xp = inXp;
    }

    public static void setGold(int inGold) {
        gold = inGold;
    }


    public void setFightHistory() {
        Runnable runnable = () -> {
            while (!t) {
                int y = 0;
                try {
                    int x = text.size();
                    if (x > y) {
                        y++;
                        String str = fightHistory.getText();
                        fightHistory.setText(str + "\n" + String.valueOf(text.get(text.size()-1)));
                    }
                    Thread.sleep(1500);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        };
        Thread thread1 = new Thread(runnable);
        thread1.start();
    }

    public void startGame() {

        boolean l1 = level1.isSelected();
        boolean l2 = level2.isSelected();
        boolean l3 = level3.isSelected();
        attentionStartBoard.setVisible(true);

        if (l1 && nameHero.getLength() != 0) {
            this.name = nameHero.getText();
            this.health = 30;
            this.strength = 20;
            this.dexterity = 20;
            xp = 20;
            gold = 100;
            startMenuOff();
            MainMenuOn();
        } else if (l2 && nameHero.getLength() != 0) {
            this.name = nameHero.getText();
            this.health = 30;
            this.strength = 20;
            this.dexterity = 20;
            xp = 20;
            gold = 50;
            startMenuOff();
            MainMenuOn();
        } else if (l3 && nameHero.getLength() != 0) {
            this.name = nameHero.getText();
            this.health = 30;
            this.strength = 20;
            this.dexterity = 20;
            xp = 20;
            gold = 0;
            startMenuOff();
            MainMenuOn();
        } else attentionStartBoard.setContentText("Введи имя и выбери уровень!");
    }

    public void startMenuOff() {
        level1.setVisible(false);
        level2.setVisible(false);
        level3.setVisible(false);
        startButton.setVisible(false);
        nameHero.setVisible(false);
        levelArea.setVisible(false);
        nameArea.setVisible(false);
        attentionStartBoard.setVisible(false);
    }

    public void StartMenuOn() {
        level1.setVisible(true);
        level2.setVisible(true);
        level3.setVisible(true);
        startButton.setVisible(true);
        nameHero.setVisible(true);
        levelArea.setVisible(true);
        nameArea.setVisible(true);
        backToStartMenu.setVisible(false);
        goToFight.setVisible(false);
        goToSeller.setVisible(false);
        resourceBoardOff();
    }

    public void MainMenuOn() {
        startMenuOff();
        backToStartMenu.setVisible(true);
        goToFight.setVisible(true);
        goToSeller.setVisible(true);
        resourceBoardOn();
        fightOff();
        goToMain.setVisible(false);
        sellerOff();
    }

    public void MainMenuOff() {
        StartMenuOn();
    }

    public void resourceBoardOn() {
        resourceBoard.setVisible(true);
        resourceBoard.setContentText("Воин: " + this.name
                + "\nздоровье: " + this.health
                + "\nловкость: " + this.dexterity
                + "\nсила: " + this.strength
                + "\nзолото: " + gold
                + "\nопыт: " + xp
        );
    }

    public void resourceBoardOff() {
        resourceBoard.setVisible(false);
    }

    public void fightOn() {
        resourceBoardOff();
        fightHistory.setVisible(true);
        fightHistory.setWrapText(true);
        backToStartMenu.setVisible(false);
        goToFight.setVisible(false);
        goToSeller.setVisible(false);
        if (randomOpponent() == 1) {
            new Fight(new Hero(name, health, strength, dexterity, xp, gold),
                    new Skeleton("Скелет", 50, 10, 10, 100, 20));
        } else {
            new Fight(new Hero(name, health, strength, dexterity, xp, gold),
                    new Goblin("Гоблин", 25, 20, 20, 100, 10));
        }
        goToMain.setVisible(true);
        setFightHistory();
    }

    public void fightOff() {
        t = false;
//        fightHistory.clear();
        fightHistory.setVisible(false);
        System.out.println(text);
    }

    public void sellerOff() {
        buyDexterity.setVisible(false);
        buyHealth.setVisible(false);
        buyStrength.setVisible(false);
        attentionStartBoard.setVisible(false);
    }

    public void sellerOn() {
        backToStartMenu.setVisible(false);
        goToFight.setVisible(false);
        goToSeller.setVisible(false);
        goToMain.setVisible(true);
        buyDexterity.setVisible(true);
        buyHealth.setVisible(true);
        buyStrength.setVisible(true);
    }

    public void buyResourcesDexterity() {
        new Seller("Dexterity");
    }

    public void buyResourcesHealth() {
        new Seller("Health");
    }

    public void buyResourcesStrength() {
        new Seller("Strength");
    }

    public int randomOpponent() {
        return 1 + (int) (Math.random() * 2);
    }

    public void exit() {
        System.exit(1);
    }

    public class Seller {
        public String goods;

        public Seller(String goods) {
            this.goods = goods;
            buyResources(goods);
        }

        void buyResources(String goods) {
            switch (goods) {
                case "Dexterity":
                    if (gold >= 10) {
                        dexterity += 10;
                        gold -= 10;
                        resourceBoardOn();
                        break;
                    } else {
                        attentionStartBoard.setVisible(true);
                        attentionStartBoard.setContentText("Недостаточно золота!");
                    }
                case "Health":
                    if (gold >= 10) {
                        health += 10;
                        gold -= 10;
                        resourceBoardOn();
                        break;
                    } else {
                        attentionStartBoard.setVisible(true);
                        attentionStartBoard.setContentText("Недостаточно золота!");
                    }
                case "Strength":
                    if (gold >= 10) {
                        health += 10;
                        gold -= 10;
                        resourceBoardOn();
                        break;
                    } else {
                        attentionStartBoard.setVisible(true);
                        attentionStartBoard.setContentText("Недостаточно золота!");
                    }
            }
        }
    }

//    public void init(){
//        String[] passArray = new String[10000];
//        Arrays.fill(passArray, "a");
//        fightHistory.setContentText(String.valueOf(passArray));
//    }
}



