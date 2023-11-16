import java.util.ArrayList;
import java.util.Random;

public class Board {
    private ArrayList<ArrayList<Space>> board;
    private int rows = 0;
    private int columns = 0;

    private int currentRow;
    private int currentCol;
    Explorer Gazoo = new Explorer("Gazoo", 20, ConsoleColors.GREEN);
    Random rand = new Random();

    int treasuresFound = 0;
    int totalTreasureValue = 0;

    ArrayList<Treasure> remainingTreasures;

    ArrayList<Monster> remainingMonsters;

    public Board() {
        this(4, 4);
    }

    public Board(int rows, int columns) {
        this.columns = columns;
        this.rows = rows;
        buildBoard();
        currentCol = 0;
        currentRow = 0;
        board.get(currentRow).get(currentCol).setOccupant(Gazoo);
        placeTreasures();
        placeMonsters();
        placeHealer();
    }

    private void placeTreasures() {
        //instantiate the ramainingTreasures list
        remainingTreasures = new ArrayList<>();
        //populate the list with 5 treasures
        for (int i = 0; i < 5; i++) {
            remainingTreasures.add(new Treasure());
        }

        //for each treasure,
        for (Treasure i : remainingTreasures) {
            int targetrow;
            int targetcol;
            //  use a do while loop to pick a random row and col until you have coords that are for an empty space (no occupant, no cache)
            do {
                targetrow = rand.nextInt(rows);
                targetcol = rand.nextInt(columns);
            } while (emptySpace(targetrow, targetcol) == false);
            //once you have valid coords, place the treasure in the spaces cache
            board.get(targetrow).get(targetcol).setCache(i);
        }


    }

    private void placeMonsters() {
        //instantiate the remainingMonsters list
        remainingMonsters = new ArrayList<>();
        //populate the list with 5 monsters
        Monster m1 = new Monster("Razorclaw", 3, ConsoleColors.RED, 9);
        Monster m2 = new Monster("Gloomfury", 3, ConsoleColors.RED, 8);
        Monster m3 = new Monster("Fangsnarl", 3, ConsoleColors.RED, 7);
        Monster m4 = new Monster("Vilespike", 3, ConsoleColors.RED, 6);
        Monster m5 = new Monster("Grimscowl", 3, ConsoleColors.RED, 5);
        remainingMonsters.add(m1);
        remainingMonsters.add(m2);
        remainingMonsters.add(m3);
        remainingMonsters.add(m4);
        remainingMonsters.add(m5);
        //for each monster,
        for (Monster i : remainingMonsters) {
            int targetrow;
            int targetcol;
            //  use a do while loop to pick a random row and col until you have coords that are for an empty space (no occupant, no cache)
            do {
                targetrow = rand.nextInt(rows);
                targetcol = rand.nextInt(columns);
            } while (emptySpace(targetrow, targetcol) == false);
            //once you have valid coords, place the monster in the spaces occupant
            board.get(targetrow).get(targetcol).setOccupant(i);
        }
    }

    private void placeHealer() {
        Healer h = new Healer("Healer", 1, ConsoleColors.BLUE, 5);
        int targetrow;
        int targetcol;
        //  use a do while loop to pick a random row and col until you have coords that are for an empty space (no occupant, no cache)
        do {
            targetrow = rand.nextInt(rows);
            targetcol = rand.nextInt(columns);
        } while (emptySpace(targetrow, targetcol) == false);
        //once you have valid coords, place the monster in the spaces occupant
        board.get(targetrow).get(targetcol).setOccupant(h);
    }

    private void buildBoard() {
        board = new ArrayList<>();
        for (int i = 0; i < rows; i++) {
            {
                ArrayList<Space> row = new ArrayList<>();
                for (int n = 0; n < columns; n++) {
                    Space space = new Space();
                    row.add(space);
                }
                board.add(row);
            }
        }
    }

    public void printBoard(Boolean showContents) {
        if (showContents == true) {
            String result = "";
            for (int i = 0; i < board.size(); i++) {
                for (int j = 0; j < board.get(i).size(); j++) {
                    result += board.get(i).get(j).getConsoleStr(true);
                }
                result += System.lineSeparator();
            }
            System.out.println(result);
        } else {
            String result = "";
            for (int i = 0; i < board.size(); i++) {
                for (int j = 0; j < board.get(i).size(); j++) {
                    result += board.get(i).get(j).getConsoleStr(false);
                }
                result += System.lineSeparator();
            }
            System.out.println(result);
        }
    }

    public boolean move(char m) {
        //make sure health isn't 0 or below
        if (board.get(currentRow).get(currentCol).getOccupant().getHealth() <= 0) {
            return false;
            //user entered w
        } else if (m == 'w') {

            if(validSpace(currentRow -1, currentCol)){
                findOccupantOrTreasure(currentRow - 1, currentCol);
                board.get(currentRow).get(currentCol).setOccupant(null);
                board.get(currentRow - 1).get(currentCol).setOccupant(Gazoo);
                currentRow--;
            }
            /*if (findOccupantOrTreasure() == true){
                checkValidSpaceMinus(1, 0, currentRow);
            }*/
            //user entered a
        } else if (m == 'a') {
            if(validSpace(currentRow, currentCol-1)){
                findOccupantOrTreasure(currentRow , currentCol -1);
                board.get(currentRow).get(currentCol).setOccupant(null);
                board.get(currentRow).get(currentCol -1).setOccupant(Gazoo);
                currentCol--;
            }

            //user entered s
        } else if (m == 's') {
            if(validSpace(currentRow +1, currentCol)){
                findOccupantOrTreasure(currentRow + 1, currentCol);
                board.get(currentRow).get(currentCol).setOccupant(null);
                board.get(currentRow + 1).get(currentCol).setOccupant(Gazoo);
                currentRow = currentRow + 1;
            }
            //user entered d
        } else if (m == 'd') {
            if(validSpace(currentRow, currentCol +1)){
                findOccupantOrTreasure(currentRow, currentCol +1);
                board.get(currentRow).get(currentCol).setOccupant(null);
                board.get(currentRow).get(currentCol +1).setOccupant(Gazoo);
                currentCol = currentCol + 1;
            }
            //user entered something not listed in menu
        } else if (m == 'r'){
            printBoard(true);
        }else if(m == 'i'){
            System.out.println("Treasures found: " + treasuresFound);
            System.out.println("Total value of treasures: " + totalTreasureValue);
        }else {
            System.out.println("invalid input.");
            return false;
        }
        return true;
    }

    private boolean findOccupantOrTreasure(int row, int col){
        //There is no occupant or treasure in that space
        if (board.get(row).get(col).getOccupant() == null
                && board.get(row).get(col).getCache() == null) {
            return true;
            //Occupant or treasure was found
        } else {
            //occupant is a monster. hurt Gazoo
            if (board.get(row).get(col).getOccupant() instanceof Monster) {
                ((Monster) board.get(row).get(col).getOccupant()).Hurt(Gazoo);
                System.out.println(Gazoo.getName() + " was attacked by " + board.get(row).get(col).getOccupant().getName() +
                        " for a loss of " + ((Monster) board.get(row).get(col).getOccupant()).getDamage() + " health.");
                System.out.println(Gazoo.getName() + " has " + Gazoo.getHealth() + " health remaining.");
                //make sure game isn't over
                if(checkForLoss(Gazoo.getHealth(), treasuresFound, totalTreasureValue) == false){
                    return true;
                };
                //Occupant is a healer. Heal Gazoo
            }else if(board.get(row).get(col).getOccupant() instanceof Healer){
                ((Healer) board.get(row).get(col).getOccupant()).Heal(Gazoo);
                System.out.println(Gazoo.getName() + " discovered a heal! " +Gazoo.getName() + " now has " + Gazoo.getHealth() + " health.");
                return true;
                //no occupant so must be a treasure
            }else{
                Gazoo.addTreasure(board.get(row).get(col).getCache());
                remainingTreasures.remove(board.get(row).get(col).getCache());
                System.out.println(Gazoo.getName() + " found " + board.get(row).get(col).getCache().getDescription() +
                        " worth " + board.get(row).get(col).getCache().getValue() + ". There are "
                        //LEFT OFF HERE. FIX THIS
                        + (remainingTreasures.size()) + " treasures left on the board.");
                treasuresFound = treasuresFound + 1;
                totalTreasureValue = totalTreasureValue + board.get(row).get(col).getCache().getValue();
                board.get(row).get(col).setCache(null);
                checkForWin();
            }
        }
        return false;
    }

    private boolean checkValidSpaceMinus(int row, int col, int currentSpace){
        if (validSpace(currentRow - row, currentCol - col)) {
            board.get(currentRow).get(currentCol).setOccupant(null);
            currentSpace--;
            board.get(currentRow).get(currentCol).setOccupant(Gazoo);
            return true;
            //space is invalid
        } else {
            System.out.println("Move was invalid.");
            return false;
        }
    }

    private boolean checkValidSpacePlus(int rowNum, int colNum, int currentRowOrCol){
        if (validSpace(currentRow + rowNum, currentCol + colNum)) {
            board.get(currentRow).get(currentCol).setOccupant(null);
            currentRowOrCol++;
            board.get(currentRow).get(currentCol).setOccupant(Gazoo);
            return true;
            //space is invalid
        } else {
            System.out.println("Move was invalid.");
            return false;
        }
    }

    private boolean validSpace(int row, int col) {
        return row >= 0 && row < board.size() && col >= 0 && col <= board.get(row).size();
    }

    private boolean emptySpace(int row, int col) {
        return row >= 0 && row < board.size() && col >= 0 && col <= board.get(row).size()
                && board.get(row).get(col).getOccupant() == null
                && board.get(row).get(col).getCache() == null;
    }

    public boolean isGameOver(){
        if (Gazoo.getHealth() <= 0 || remainingTreasures.size() == 0){
            return true;
        }
        return false;
    }
    private boolean checkForLoss(int health, int treasuresFound, int totalTreasureValue) {
        if (health <= 0) {
            System.out.println("The explorer has died.");
            System.out.println("Total treasures found: " + treasuresFound);
            System.out.println("Total value of treasures: "  + totalTreasureValue);
            return true;
        } else {
            return false;
        }
    }

    private boolean checkForWin(){
        if (remainingTreasures.size() == 0){
            System.out.println("You win!");
            System.out.println("Treasures found: " + treasuresFound);
            System.out.println("Total value of treasures: " + totalTreasureValue);
            return true;
        }
        return false;
    }






}