import java.util.Scanner;

class TicTocToeApp {
    
   
   public int[][] board = new int[3][3];
   public Boolean GameOver;
   public int activePlayer;            
    
   public TicTocToeApp() {
         for (int i = 0; i < 3; i++){
              for (int j = 0; j < 3; j++){
                   board[i][j] = 0;
              }
         } 
         GameOver = false;
         activePlayer = 1;
    }

    public static void main (String[] args) {
         String Answer;
         Boolean Valid = false;
         
         TicTocToeApp game = new TicTocToeApp();    
         game.printBoard();
         System.out.println("Do you want to go first? (yes/no)");
         Scanner scanIn = new Scanner(System.in);
         while (Valid == false){
           Answer = scanIn.nextLine();
           switch(Answer) {
             case "yes" : {
                 game.activePlayer = 1;
                 System.out.println("You will go first.");
                 Valid = true;
                 break;
             }
             case "no" : {
                  game.activePlayer = 2;
                  System.out.println("Computer will go first.");
                  Valid = true;
                  break;
             }
             default : System.out.println("Invalid answer");
                       break;
           } 
           
         }
         
         game.gameLoop();
         scanIn.close();
         
    }

    public int printBoard () {
         String newLine = System.getProperty("line.separator");
         String line = "";
         for (int i = 0; i < 3; i++){
             line = "";
             for (int j = 0; j < 3; j++){
                 if (board[j][i] == 2){
                     if ( j < 2) {
                       line = line + "X | ";
                     }
                     else {
                       line = line + "X" + newLine;
                       if ( i < 2) {
                         line = line + "--|---|--";
                       }
                     }
                 }
                 else if (board[j][i] == 1){
                     if ( j < 2) {
                       line = line + "O | ";
                     }
                     else {
                       line = line + "O " + newLine;
                       if (i < 2) {
                         line = line + "--|---|--";
                       }
                     }
                  }
                 else {
                     if ( j < 2 ) {
                       int k;
                       k = 3 * i;
                       k = k + j; 
                       line = line + k + " | ";
                     }
                     else {
                       int k;
                       k = 3 * i;
                       k = k + j;
                       line = line + k + newLine;
                       if ( i < 2){
                         line = line + "--|---|--";
                       }
                     }
                 }    
              }
              System.out.println(line);
         }
                    
         return (0) ;
    }

    public int doMove (int x, int player ){
         if ( x == 0 && board[0][0] == 0){
            board[0][0] = player;
            return (0);
         }
         if ( x == 1 && board[1][0] == 0){
            board[1][0] = player;
            return (0);
         }
         if ( x == 2 && board[2][0] == 0){
            board[2][0] = player;
            return (0);
         }
         if ( x == 3 && board[0][1] == 0){
            board[0][1] = player;
            return (0);
         }
         if ( x == 4 && board[1][1] == 0){
            board[1][1] = player;
            return (0);
         }
         if ( x == 5 && board[2][1] == 0){
            board[2][1] = player;
            return (0);
         }
         if ( x == 6 && board[0][2] == 0){
            board[0][2] = player;
            return (0);
         }
         if ( x == 7 && board[1][2] == 0){
            board[1][2] = player;
            return (0);
         }
         if ( x == 8 && board[2][2] == 0){
            board[2][2] = player;
            return (0);
         }
         return (1);
    }
    
    public int makeMove () {
         int Cell = 0;
         if ( board[1][1] == 0 ) {return (4);}
         Cell = checkAlmost (2); //Check to see if can win
         if (Cell != 9) {
             return (Cell);
         }
         Cell = checkAlmost (1); //Check to see if about to lose
         if (Cell != 9) {
             return (Cell);
         }
         if ( board[0][0] == 2 && board[2][2] == 2 ){
             if ( board[1][0] == 0 ){return (1);}
             if ( board[0][1] == 0 ){return (3);}
             if ( board[1][2] == 0 ){return (7);}
             if ( board[2][1] == 0 ){return (5);}
         }
         if ( board[0][2] == 2 && board[2][0] == 2 ){
             if ( board[1][0] == 0 ){return (1);}
             if ( board[0][1] == 0 ){return (3);}
             if ( board[1][2] == 0 ){return (7);}
             if ( board[2][1] == 0 ){return (5);}
         }  
         if ( board[0][0] == 0 ){return (0);}
         if ( board[0][2] == 0 ){return (6);}
         if ( board[2][0] == 0 ){return (2);}
         if ( board[2][2] == 0 ){return (8);}
         if ( board[1][0] == 0 ){return (1);}
         if ( board[0][1] == 0 ){return (3);}
         if ( board[1][2] == 0 ){return (7);}
         if ( board[2][1] == 0 ){return (5);} 
         return (9);
    }

    public int checkAlmost (int player){ // Needs to be fixed, no diagonal
        int Cell = 0;
        for (int x = 0; x < 3; x++){
            int sum = 0;
            for (int y = 0; y < 3; y++){
              if ( board[x][y] == player ){
                 sum++;
              } 
            }
            if ( sum == 2 ) {
              for (int y = 0; y < 3; y++) {
                  if ( board[x][y] != player && board[x][y] == 0) {
                     Cell = (3 * y);
                     Cell = Cell + x;
                     return (Cell); // first threatened cell
                  }
              }
            }
        }
        for (int y = 0; y < 3; y++){
            int sum = 0;
            for (int x = 0; x < 3; x++){
              if ( board[x][y] == player ){
                 sum++;
              } 
            }
            if ( sum == 2 ) {
              for (int x = 0; x < 3; x++) {
                  if ( board[x][y] != player && board[x][y] == 0) {
                     Cell = (3 * y);
                     Cell = Cell + x;
                     return (Cell); // first threatened cell
                  }
              }
            }
        }
        int Sum = 0;
        for (int i = 0; i < 3; i++){
           if (board[i][i] == player ){
              Sum++;
           }
        }
        if (Sum == 2) {
          if ( board[0][0] != player && board[0][0] == 0 ){return(0);}
          else if ( board[1][1] != player && board[1][1] == 0 ){return(4);}
          else if ( board[2][2] != player && board[2][2] == 0 ){return(8);}
        }
        Sum = 0;
        for (int i = 0; i < 3; i++){
           int j;
           j = 2 - i;
           if (board[i][j] == player ){
              Sum++;
           }
        }
        if (Sum == 2) {
          if ( board[0][2] != player && board[0][2] == 0 ){return(6);}
          else if ( board[1][1] != player && board[1][1] == 0 ){return(4);}
          else if ( board[2][0] != player && board[2][0] == 0 ){return(2);}
        }  
        return (9); // no cells are threatened
    }
    public int checkWin (){
         Boolean isWin = false;
         for (int x = 0; x < 3; x++){
            if ( board[x][0] == 1 && board[x][1] == 1 && board[x][2] == 1) {
               isWin = true;
            }
            if ( board[x][0] == 2 && board[x][1] == 2 && board[x][2] == 2) {
               isWin = true;
            }  
         }
         for (int y = 0; y < 3; y++){
            if ( board[0][y] == 1 && board[1][y] == 1 && board[2][y] == 1) {
               isWin = true;
            }
            if ( board[0][y] == 2 && board[1][y] == 2 && board[2][y] == 2) {
               isWin = true;
            }  
         }     
         for (int i = 1; i < 3; i++){
            if ( board[0][0] == i && board[1][1] == i && board[2][2] == i ) {
               isWin = true;
            }
            if ( board[0][2] == i && board[1][1] == i && board[2][0] == i ) {
               isWin = true;
            }
         }
         if (isWin == true) {return (1);}
         return (0);
    }
    public int checkStaleMate (){
        Boolean StaleMate = false;
        int zeros = 0;
        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                 if ( board[i][j] == 0 ){
                   zeros++;
                 }
             }
         }
        if (zeros == 0) {return (1);}
        return (0);
    }
    public int gameLoop (){
        int move = 9;
        String newLine = System.getProperty("line.separator");
        String Answer;
        Scanner scanIn2 = new Scanner(System.in);
        while (GameOver != true){
          if (activePlayer == 1) {
            System.out.println("which move do you want to make (0-8)?" + newLine);
            
            Answer = scanIn2.nextLine();
            
            switch(Answer){
              case "0" : {move = 0;
                         break;}
              case "1" : {move = 1;
                         break;}
              case "2" : {move = 2;
                         break;}
              case "3" : {move = 3;
                         break;}
              case "4" : {move = 4;
                         break;}
              case "5" : {move = 5;
                         break;}
              case "6" : {move = 6;
                         break;}
              case "7" : {move = 7;
                         break;}
              case "8" : {move = 8;
                         break;}
              default : {move = makeMove();
                         System.out.println("Invalid move");
                        break;}
            }
           
              activePlayer = 2;
          }
          else if (activePlayer == 2) {
             move = makeMove();
             System.out.println("Computer makes move " + move + newLine);
             activePlayer = 1;
          }
          doMove(move, activePlayer);
          printBoard();
          if (checkWin() != 0) {
           GameOver = true;
           if (activePlayer == 2) {System.out.println("Player won");}
           else {System.out.println("Computer won");}
           }
          if (checkStaleMate() == 1){
           GameOver = true;
           System.out.println("Stalemate");
          }
        }
        scanIn2.close();
        return (0);
         
    }

}