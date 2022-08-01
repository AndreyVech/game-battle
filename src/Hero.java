public class Hero extends Fighters {
    private String name;
    //Статы персонажа
    private int healthPoints;
    private int strength;
    private int dexterity;
    //Опыт и золото
    private int xp;
    private int gold;

    public Hero(String name, int healthPoints, int strength, int dexterity, int xp, int gold) {
        super(name, healthPoints, strength, dexterity, xp, gold);
    }
}