import java.util.*;
public class BattleShip {
    public static int Matrix_OceanMap [][]= new int [12][14];
    public static int Matrix_OceanMap_User [][]= new int [12][14];
    public static int Matrix_OceanMap_Pc [][]= new int [12][14];
    public static void main(String[] args) {
        Deco ();
        Intro(Matrix_OceanMap);
        PrintMaps(Matrix_OceanMap);
        Coordinates_UsersShips();
        Coordinates_PcShips();
        do {
            User_Turn();
            Pc_Turn();
            System.out.println("Your ships: " + (5 - GameOver(Matrix_OceanMap_User)) + " | Computer ships: " + (5 - GameOver(Matrix_OceanMap_Pc)));
            if (GameOver(Matrix_OceanMap_Pc) == 5) {
                System.out.println("Hooray! You win the battle :)");
            } else if (GameOver(Matrix_OceanMap_User) == 5) {
                System.out.println("Sorry! Computer win the battle :(");
            }
        } while (GameOver(Matrix_OceanMap_User) !=5  ||  GameOver(Matrix_OceanMap_User) != 5);
    }
    //Deco
    public static void Deco (){
        for (int i=0; i<=39; i++){
            System.out.print("*");
            if (i == 39){
                System.out.println("*");
                System.out.println("****  Welcome to Battle Ships Game!  ****");
            }
            for (int j=i; (j>=39) && (j<=79) ; j++) {System.out.print("*");}
        }
        System.out.println("\nRight now, the sea is empty."); // End cycle For Deco
    }
    //Building Matrix
    public static void Intro (int Matrix_OceanMap [][]){
        int i, j;
        for(i=0;i<12;i++) {     //the row
            if (i == 0 || i == 11) {
                Matrix_OceanMap[i][0] = ' ';     //the corners
                Matrix_OceanMap[i][1] = ' ';
                Matrix_OceanMap[i][12] = ' ';
                Matrix_OceanMap[i][13] = ' ';
                for (j = 2; j < 12; j++) {
                    Matrix_OceanMap[i][j] = j - 2 + '0';        //the columns
                }
            }
        }
        for(i=0;i<12;i++) {     //the row
            if (i > 0 && i < 11 ) {
                for (j = 0; j < 14; j++) {
                    if (j==0 || j==Matrix_OceanMap.length+1){
                        Matrix_OceanMap[i][j] = i-1 + '0';      //the rows
                    } else if (j==1 || j==Matrix_OceanMap.length){
                        Matrix_OceanMap[i][j] = '|';
                    }else{                                      //empty ocean
                        Matrix_OceanMap[i][j] = ' ';
                    }
                }
            }
        }
    }
    //Matrix printer
    public static void PrintMaps (int Matrix [][]){
        //Matrix print
        int i, j;
        for (i = 0; i < Matrix.length; i++){
            for(j = 0; j < Matrix[i].length; j++){
                System.out.print((char)Matrix[i][j] + "  ");
            }
            System.out.println();
        }
    }
    //List printer
    public static void PrintList_Col (ArrayList<String> List){
        //List print to Col
        System.out.println("*****************************************");
        Iterator<String> Ships_Iterator = List.iterator();
        while(Ships_Iterator.hasNext()){
            String elemento =Ships_Iterator.next();
            System.out.println(elemento);
        }
        System.out.println("-----------------------------------------");
    }

    public static void Coordinates_UsersShips() {
        Intro(Matrix_OceanMap_User);        //Build and Call to the Matrix for the User
        ArrayList<String> Ships = new ArrayList<String>();
        Scanner input = new Scanner(System.in);
        int x, y;
        for (int i = 0; i < 5; i++) {
            System.out.print("Enter X coordinate for your " + (i + 1) + ". ship: ");
            x = input.nextInt();
            System.out.print("Enter Y coordinate for your "+(i + 1) + ". ship: ");
            y = input.nextInt();
            if (x < 0 || x > 9 || y < 0 || y > 9 || Matrix_OceanMap_User[x + 1][y + 2] == '@') {
                System.out.println("There was an mistake, please try it again!\nThe range of the coordinates is from 0 to 9");
                i--; // very important for this for.
            }else {
                Ships.add("\t" + (i + 1) + ". ship deployed. (" + x + ", " + y + ")");
                Matrix_OceanMap_User[x + 1][y + 2] = '@';
            }
        }
        //Print the list to Col
        PrintList_Col(Ships);
        PrintMaps(Matrix_OceanMap_User);
    }

    public static void Coordinates_PcShips () {
        Intro(Matrix_OceanMap_Pc);
        ArrayList<String> Ships_Pc = new ArrayList<String>();
        //randomly Coordinates for the PC
        Random Random = new Random();
        int x, y;
        for (int i = 0; i < 5; i++) {
            x = Random.nextInt(10);
            y = Random.nextInt(10);
            if (x < 0 || x > 9 || y < 0 || y > 9 || Matrix_OceanMap_User[x + 1][y + 2] == '@' || (Matrix_OceanMap_Pc[x + 1][y + 2] == '2')) {
                i--; // very important for this for.
            } else {
                Ships_Pc.add("\t" + (i + 1) + ". ship deployed. (" + x + ", " + y + ")");
                Matrix_OceanMap_Pc[x + 1][y + 2] = '2';
            }
        }
        //PrintList_Col(Ships_Pc);
        //PrintMaps(Matrix_OceanMap_Pc);
    }
    //Battle
    public static void User_Turn() {
        Scanner input = new Scanner(System.in);
        int x, y;
        int No;
        boolean Verdadero = true;
        System.out.println("YOUR TURN");
        do {
            System.out.print("Enter X coordinate: ");
            x = input.nextInt();
            System.out.print("Enter Y coordinate: ");
            y = input.nextInt();
            if (x < 0 || x > 9 || y < 0 || y > 9) {
                System.out.println("There was an mistake, please try it again!\nThe range of the coordinates is from 0 to 9");
                Verdadero = false;
            } else {
                Verdadero = true;
            }
        } while (!Verdadero);
        //three possible outcomes:
        if (Matrix_OceanMap_Pc[x + 1][y + 2] == '2') {
            System.out.println("Boom! You sunk the ship!");
            Matrix_OceanMap_User[x + 1][y + 2] = '!';
            Matrix_OceanMap_Pc[x + 1][y + 2] = 'x';
        } else if (Matrix_OceanMap_User[x + 1][y + 2] == '@') {
            System.out.println("Oh no, you sunk your own ship :(");
            Matrix_OceanMap_User[x + 1][y + 2] = 'x';
        } else { // No ships Pc on the entered coordinate
            System.out.println("Sorry, you missed");
            Matrix_OceanMap_User[x + 1][y + 2] = '-';
        }
        PrintMaps(Matrix_OceanMap_User);
    }

    public static void Pc_Turn() {
        Random Random = new Random();
        int x, y;
        boolean Verdadero = true;
        do {
            x = Random.nextInt(10);
            y = Random.nextInt(10);
            if (x < 0 || x > 9 || y < 0 || y > 9) {
                Verdadero = false;
            } else {
                Verdadero = true;
            }
        } while (!Verdadero);
        //three possible outcomes:
        if (Matrix_OceanMap_User[x + 1][y + 2] == '@') {
            System.out.println("The Computer sunk one of your ships!");
            Matrix_OceanMap_User[x + 1][y + 2] = 'X';
            Matrix_OceanMap_Pc[x + 1][y + 2] = '!';
        } else if (Matrix_OceanMap_Pc[x + 1][y + 2] == '2') {
            System.out.println("The Computer sunk one of its own ships");
            Matrix_OceanMap_User[x + 1][y + 2] = '!';
        } else { // No ships User on the entered coordinate
            System.out.println("Computer missed");
            Matrix_OceanMap_User[x + 1][y + 2] = '-';
        }
    }
    //Build the Matrix bat return one only number
    public static int GameOver(int Matrix [][]) {
        int i, j, Count = 0;
        for(i=0;i<12;i++){
            for(j=0;j<14;j++){
                if(Matrix[i][j] == 'x'){
                    Count++;
                }
            }
        }
        return Count;
    }
}

