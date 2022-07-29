import javafx.fxml.FXML;
import javafx.scene.control.*;

public class ControllerStart{
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
    String name;
    int health;
    int strength;
    int dexterity;
    int xp;
    int gold;

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
            new Hero(nameHero.getText(), 100, 100, 100, 100, 50);
            startMenuOff();
            MainMenuOn();
        } else if (l3 && nameHero.getLength() != 0) {
            new Hero(nameHero.getText(), 50, 50, 50, 50, 0);
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
    }

    public void MainMenuOn() {
        startMenuOff();
        backToStartMenu.setVisible(true);
        goToFight.setVisible(true);
        goToSeller.setVisible(true);
        resourceBoardOn();
    }

    public void MainMenuOff() {
        StartMenuOn();
        backToStartMenu.setVisible(false);
        goToFight.setVisible(false);
        goToSeller.setVisible(false);
        resourceBoardOff();
    }

    public void resourceBoardOn() {
        resourceBoard.setVisible(true);
//        resourceBoard.setContentText("Воин: "+ Fighters().getName()
//                + "\nздоровье: " + Hero.getXp()
//                + "\nловкость: " + Hero.getDexterity()
//                + "\nсила: " + Hero.getStrength()
//                + "\nзолото: " + Hero.getGold()
//        );
    }

    public void resourceBoardOff() {
        resourceBoard.setVisible(false);
    }

    public void fightOn() {
//            MainMenuOff();
        fightHistory.setText("GOO");
        Fight.fight(new Hero(name, health, strength, dexterity, xp, gold),
                new Skeleton("22", 22, 33, 44, 44, 44));
    }


    public void fightOff() {
    }

}



