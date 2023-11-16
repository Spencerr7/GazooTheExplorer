import java.util.ArrayList;

public class LivingThing {
    private String name;
    private int health;
    private String pieceColor;

    public LivingThing() {

    }

    public LivingThing(String name, int health) {
        this.name = name;
        this.health = health;
        pieceColor = ConsoleColors.YELLOW;
    }

    public LivingThing(String name, int health, String color) {
        this.name = name;
        this.health = health;
        this.pieceColor = color;
    }

    public void setName(String name) {
        if (name.trim() == "") {
            this.name = "undefined";
        } else {
            this.name = name.trim();
        }
    }

    public void setHealth(int health) {
        if (health < 0) {
            this.health = 0;
        } else {
            this.health = health;
        }
    }

    public void setPieceColor(String color) {
        this.pieceColor = color;
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public String getPieceColor() {
        return pieceColor;
    }

    public String getConsoleStr() {
        String gcs = pieceColor + name.charAt(0) + ConsoleColors.RESET;
        return gcs;
    }
}