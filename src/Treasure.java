public class Treasure {
    private int value;
    private String description;

    public Treasure(){
        this.value = 5;
        this.description = "gold";
    }

    public Treasure(String description, int value){
        this.value = value;
        this.description = description;
    }

    public void setValue(int value){
        this.value = value;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public int getValue(){
        return value;
    }

    public String getDescription(){
        return description;
    }

    public String getConsoleStr(){
        String gcs = ConsoleColors.YELLOW + description.charAt(0) + ConsoleColors.RESET;
        return gcs;
    }
}