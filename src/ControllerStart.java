import javafx.fxml.FXML;
import javafx.scene.control.*;

public class ControllerStart {
    int health;
    int strength;
    int dexterity;
    int xp;
    int gold;
    String name;
    @FXML
    TextArea fightHistory;
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


    public void addTextToTextArea(String str) {
        this.fightHistory.appendText(str);
    }

    public void startGame() {

        boolean l1 = level1.isSelected();
        boolean l2 = level2.isSelected();
        boolean l3 = level3.isSelected();
        attentionStartBoard.setVisible(true);

        if (l1 && nameHero.getLength() != 0) {
            this.name = nameHero.getText();
            this.health = 100;
            this.strength = 100;
            this.dexterity = 100;
            this.xp = 100;
            this.gold = 100;
            startMenuOff();
            MainMenuOn();
        } else if (l2 && nameHero.getLength() != 0) {
            this.name = nameHero.getText();
            this.health = 70;
            this.strength = 70;
            this.dexterity = 70;
            this.xp = 70;
            this.gold = 70;
            startMenuOff();
            MainMenuOn();
        } else if (l3 && nameHero.getLength() != 0) {
            this.name = nameHero.getText();
            this.health = 50;
            this.strength = 50;
            this.dexterity = 50;
            this.xp = 50;
            this.gold = 0;
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
                + "\nзолото: " + this.gold
        );
    }

    public void resourceBoardOff() {
        resourceBoard.setVisible(false);
    }

    public void setFightHistory(TextArea fightHistory, String str) {
        this.fightHistory = fightHistory;
        addTextToTextArea(str);
    }

    public void setHealth(int health) {
        this.health = health;
        resourceBoardOn();
    }

    public void setStrength(int strength) {
        this.strength = strength;
        resourceBoardOn();
    }


    public void setDexterity(int dexterity) {
        this.dexterity = dexterity;
        resourceBoardOn();
    }

    public void setXp(int xp) {
        this.xp = xp;
        resourceBoardOn();
    }

    public void setGold(int gold) {
        this.gold = gold;
        resourceBoardOn();
    }

    public void fightOn() {
        backToStartMenu.setVisible(false);
        fightHistory.setVisible(true);
        goToFight.setVisible(false);
        goToSeller.setVisible(false);

        if (randomOpponent() == 1) {
            Fight.fight(new Hero(name, health, strength, dexterity, xp, gold),
                    new Skeleton("Скелет", 50, 10, 10, 100, 20));
        } else {
            Fight.fight(new Hero(name, health, strength, dexterity, xp, gold),
                    new Goblin("Гоблин", 25, 20, 20, 100, 10));
        }
        goToMain.setVisible(true);
    }

    public void fightOff() {
        fightHistory.setVisible(false);
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
        Seller seller = new Seller("Dexterity");
    }

    public void buyResourcesHealth() {
        Seller seller = new Seller("Health");
    }

    public void buyResourcesStrength() {
        Seller seller = new Seller("Strength");
    }

    public int randomOpponent() {
        return 1 + (int) (Math.random() * 2);
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

}



