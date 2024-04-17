package minesweeper;

import java.sql.Array;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Arrays;

public class Minefield {

    public ArrayList<ArrayList<String>> minefield;
    public ArrayList<ArrayList<String>> playerMinefield;
    private String border;

    private final int nbOfColumn;
    private final int nbOfRow;
    private int nbOfMine;

    private ArrayList<String> hiddingMines;
    public ArrayList<String> numbers;
    public ArrayList<String> cellExplored;



    public Minefield(int nbOfColumn, int nbOfRow){
        this.nbOfColumn = nbOfColumn;
        this.nbOfRow = nbOfRow;
        this.minefield = new ArrayList<>();
        this.numbers = new ArrayList<>();
        this.playerMinefield = new ArrayList<>();
        this.cellExplored = new ArrayList<>();
        this.hiddingMines = new ArrayList<>();
        this.border = "-|---------|";
        populatedMinefield();

    }

    private void populatedMinefield(){
        for(int i = 0; i < getNbOfRow(); i++){
            this.minefield.add(i,new ArrayList<>());
            this.playerMinefield.add(i,new ArrayList<>());
            for(int j = 0; j < getNbOfColumn(); j++){
                this.minefield.get(i).add(".");
                this.playerMinefield.get(i).add(".");
            }

        }
    }
    private void setNbOfMine(int nbOfMine){
        this.nbOfMine = nbOfMine;
    }
    public int getNbOfMine(){
        return this.nbOfMine;
    }
    private int getNbOfColumn() {
        return nbOfColumn;
    }

    private int getNbOfRow() {
        return nbOfRow;
    }

    public void printMinefield(){

        System.out.println(" |123456789|");
        System.out.println(this.border);
        for(int i = 1; i < getNbOfRow() ; i++){
            System.out.print((i) + "|");
            for(int j = 1; j < getNbOfColumn(); j++){
                //     System.out.print(this.minefield.get(i).get(j));
                    System.out.print(this.playerMinefield.get(i).get(j));
            }
            System.out.println("|");
        }
        System.out.println(this.border);
    }

    public void placeMine(int numberOfMine){
        setNbOfMine(numberOfMine);
        ArrayList<Integer> freeCells = new ArrayList<>();
        for(int i = 11; i < 99; i++){

            if( i % 10 != 0){
                freeCells.add(i);
            }
        }
        while(numberOfMine > 0){

            int randomCoor = (int) (Math.random() * freeCells.size()) ;

            this.hiddingMines.add(String.valueOf( freeCells.get(randomCoor)));
            freeCells.remove(randomCoor);
            numberOfMine--;
        }

        int count = 0;
        while(count < this.nbOfMine){
            String[] index = this.hiddingMines.get(count).split("");
            placeNumberOfMineAroundTheCell(Integer.parseInt(index[0]) ,Integer.parseInt(index[1]));
            count++;
        }
    }

    public void placeNumberOfMineAroundTheCell(int randomX , int randomY){
        // Take the information of the mine
        // There 5 place around the mine, take care of the up row by checking first if we are in the minefield then checking if
        // they have already a mine around
        // Do the same step for other place
        if(randomY - 1  > 0){
            //-- Top

            if(randomX -1 > 0 ){
                // Top left corner
                this.numbers.add(String.valueOf(randomX - 1 + String.valueOf(randomY - 1) ));
                this.minefield.get(randomY - 1 ).set(randomX - 1, String.valueOf(howManyMineAround(randomX - 1, randomY - 1)));

            }
                // Top middle
            this.numbers.add(String.valueOf(randomX  + String.valueOf(randomY - 1) ));
            this.minefield.get(randomY - 1 ).set(randomX, String.valueOf(howManyMineAround(randomX, randomY - 1)));


            if(randomX + 1  < getNbOfColumn()){
                // Top right corner
                this.numbers.add(String.valueOf((randomX  + 1) + String.valueOf(randomY - 1) ));
                this.minefield.get(randomY - 1 ).set(randomX + 1 , String.valueOf(howManyMineAround(randomX + 1, randomY - 1)));


            }
        }
        // -------
        // -- Bot

        if(randomY + 1 < getNbOfRow()){
            if(randomX - 1  > 0){
                // Bottom left corner
                this.numbers.add(String.valueOf((randomX  - 1) + String.valueOf(randomY + 1) ));
                this.minefield.get(randomY + 1 ).set(randomX - 1 , String.valueOf(howManyMineAround(randomX - 1, randomY + 1)));

            }
                // Bottom middle
            this.numbers.add(String.valueOf((randomX ) + String.valueOf(randomY + 1) ));
            this.minefield.get(randomY + 1 ).set(randomX , String.valueOf(howManyMineAround(randomX , randomY + 1)));


            if(randomX + 1  < getNbOfColumn() ){
                // Bottom right corner
                this.numbers.add(String.valueOf((randomX + 1) + String.valueOf(randomY + 1) ));
                this.minefield.get(randomY + 1 ).set(randomX + 1 , String.valueOf(howManyMineAround(randomX + 1, randomY + 1)));

            }
        }
        // ---
        // -- Middle

        if(randomX - 1 >0){
            this.numbers.add(String.valueOf((randomX -1) + String.valueOf(randomY ) ));
            this.minefield.get(randomY  ).set(randomX - 1 , String.valueOf(howManyMineAround(randomX - 1, randomY )));

        }
        if(randomX + 1  < getNbOfColumn()){

            this.numbers.add(String.valueOf((randomX +1) + String.valueOf(randomY ) ));
            this.minefield.get(randomY ).set(randomX + 1 , String.valueOf(howManyMineAround(randomX + 1, randomY )));

        }
    }

    public void placeNumber(int randomX , int randomY){

        if( this.hiddingMines.contains(String.valueOf(randomX) + randomY) ){

            return;
        }
        if( this.minefield.get(randomY ).get(randomX ).equals(".")){
            this.minefield.get(randomY ).set(randomX , "1");
            this.numbers.add(String.valueOf(randomX) + String.valueOf(randomY) );
        } else {
            int nb = Integer.parseInt(this.minefield.get(randomY ).get(randomX ));
            String str = String.valueOf(nb);
            this.minefield.get(randomY ).set(randomX , str);
        }
    }

    public boolean isMine(int x, int y){
        if(this.hiddingMines.contains(String.valueOf(x) + y)){
            return true;
        } else {
            return false;
        }
    }

    public void exploring(int x, int y){

        if(x <= 0 || x >= getNbOfColumn() || y <= 0 || y >= getNbOfRow()){
            return;
        }

        if(this.playerMinefield.get(y).get(x).equals("*") && isMine(x,y) || this.playerMinefield.get(y).get(x).equals("/")){
            return;
        }
        if(this.numbers.contains(String.valueOf(x) + String.valueOf(y))){

            this.playerMinefield.get(y).set(x, this.minefield.get(y).get(x));
        } else if(this.hiddingMines.contains(String.valueOf(x) + y)){
            int nbOfMine = howManyMineAround(x, y);
            this.playerMinefield.get(y).set(x, String.valueOf(nbOfMine));
        }
        String initialCoordinate = String.valueOf(x) + y;


        if(this.cellExplored.contains(initialCoordinate)){
            return;
        }

        this.cellExplored.add(initialCoordinate);



        if(this.numbers.contains(String.valueOf(x) + y)){

            System.out.println(this.minefield.get(y).get(x));
            this.playerMinefield.get(y).set(x, this.minefield.get(y).get(x));
        } else {
            this.playerMinefield.get(y).set(x, "/");
            exploring(x - 1, y - 1 );
            exploring(x, y - 1);
            exploring(x + 1 , y - 1);

            exploring(x - 1, y );
            exploring(x + 1 , y );

            exploring(x - 1, y + 1 );
            exploring(x, y + 1);
            exploring(x + 1 , y + 1);
        }


    }

    public int howManyMineAround(int x, int y){

        if(x <= 0 || x >= getNbOfColumn() || y <= 0 || y >= getNbOfRow()){
            return 0;
        }
        if(this.minefield.get(y).get(x).equals(".") && (this.hiddingMines.contains(String.valueOf(x) + String.valueOf(y))
        || this.numbers.contains(String.valueOf(x) + String.valueOf(y)))){

            return 1;
        } else if(this.hiddingMines.contains(String.valueOf(x) + String.valueOf(y))
                || this.numbers.contains(String.valueOf(x) + String.valueOf(y))){
            return Integer.parseInt(this.minefield.get(y).get(x)) + 1;
        }

        return 0;
    }

    public void lose(){

        showAllMine();
        printMinefield();
        System.out.println("You stepped on a mine and failed!");
    }

    private void showAllMine(){

        for(int i = 0; i < getNbOfMine(); i ++){
            int x = Integer.parseInt(hiddingMines.get(i))  / 10;
            int y = Integer.parseInt(hiddingMines.get(i))  % 10;

            playerMinefield.get(y).set(x, "X");
        }
    }


}
