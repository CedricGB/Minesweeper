package minesweeper;


import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // write your code here


        Scanner scanner = new Scanner(System.in);

        Minefield minefield = new Minefield(10,10);
        System.out.println("How many mines do you want on the field?");
        int playerChoice = Integer.parseInt(scanner.nextLine());
        int mineFound = 0;
        minefield.placeMine(playerChoice);
        minefield.printMinefield();
        System.out.println(minefield.numbers);
        while(minefield.getNbOfMine() > 0){

            System.out.println("Set/unset mine marks or claim a cell as free:");
            String[] strArray = scanner.nextLine().split(" ");
            int x = Integer.parseInt(strArray[0]);
            int y = Integer.parseInt(strArray[1]);

            if("free".equals(strArray[2]) && minefield.isMine(x , y) ){

                minefield.lose();
                break;

            } else if("mine".equals(strArray[2]) && "*".equals(minefield.playerMinefield.get(y).get(x)))  {
                minefield.playerMinefield.get(y ).set(x , ".");
            } else if("mine".equals(strArray[2])) {
                if(minefield.isMine(x,y)){
                    mineFound++;
                    if(mineFound >= minefield.getNbOfMine()){
                        minefield.printMinefield();
                        System.out.println("Congratulations! You found all the mines!\n");
                        break;
                    }
                }
                minefield.playerMinefield.get(y ).set(x , "*");
            } else {
                minefield.exploring(x, y);
            }
            minefield.printMinefield();
        }



    }
}
