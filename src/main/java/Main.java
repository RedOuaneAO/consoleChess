import  chessBoard.*;
import pieces.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static chessBoard.Piece.Color.BLACK;
import static chessBoard.Piece.Color.WHITE;

public class Main {

    public static void main(String[] args) {
    printBoard(setPieces());
}
    static void printBoard(List<Piece> pieces) {
//        String block = "█████   ███   █████          ████\n" +
//                "░░███   ░███  ░░███          ░░███\n" +
//                " ░███   ░███   ░███   ██████  ░███   ██████   ██████  █████████████    ██████\n" +
//                " ░███   ░███   ░███  ███░░███ ░███  ███░░███ ███░░███░░███░░███░░███  ███░░███\n" +
//                " ░░███  █████  ███  ░███████  ░███ ░███ ░░░ ░███ ░███ ░███ ░███ ░███ ░███████\n" +
//                "  ░░░█████░█████░   ░███░░░   ░███ ░███  ███░███ ░███ ░███ ░███ ░███ ░███░░░\n" +
//                "    ░░███ ░░███     ░░██████  █████░░██████ ░░██████  █████░███ █████░░██████\n" +
//                "     ░░░   ░░░       ░░░░░░  ░░░░░  ░░░░░░   ░░░░░░  ░░░░░ ░░░ ░░░░░  ░░░░░░\n";
//
//        System.out.println(block);
//        System.out.println(pieces);
        Board board =new Board(8,8);
        System.out.print("   a   b   c   d   e   f   g   h\n");
        for (int row = 0; row <board.getRows(); row++) {
            System.out.print(row + " ");
            for (int col = 0; col < board.getColumns(); col++) {
                boolean pieceFound = false;
                char pieceName = ' ';
                Piece.Color pieceColor = null;
                for (Piece piece : pieces) {
                    if (piece.getRow() == row && piece.getCol() == col) {
                        pieceName = piece.getName();
                        pieceColor =piece.getColor();
                        pieceFound = true;
                        break;
                    }
                }
                String backgroundColor = ((row + col) % 2 == 0) ? "\u001B[44m" : "\u001B[47m";
                // Determine the text color for the piece name
                String textColor = (pieceFound && pieceName != ' ') ? "\u001B[30m" : "\u001B[0m";
                // Print the square with or without a piece
                if (pieceFound) {
                    if (pieceColor.equals(BLACK)){
                    System.out.print(backgroundColor +  textColor + "  "+ pieceName +  " \u001B[0m");
                    }else {
                    System.out.print(backgroundColor +  "  "+ pieceName +  " \u001B[0m");
                    }
                } else {
                    System.out.print(backgroundColor +"    "+ "\u001B[0m");
                }
            }
            System.out.println();
        }
        System.out.print("   a   b   c   d   e   f   g   h\n");
        startGame(pieces);
    }

    static void startGame(List<Piece> pieces){
        Scanner scanner =new Scanner(System.in);
        System.out.println("Enter Piece Coordinates");
        String pieceCoord = scanner.nextLine().trim();
        if(isValid(pieceCoord)){
            int currentCol = columnToIndex(pieceCoord.charAt(0));
            int currentRow = rowToIndex(pieceCoord.charAt(1));
            int pieceColumnDest = -1;
            int pieceRowDest = -1;
            for(Piece piece : pieces){
                if (piece.getCol()== currentCol && piece.getRow() == currentRow) {
                    Scanner scanner2 =new Scanner(System.in);
                    System.out.println("Enter Piece Destination Coordinates");
                    String pieceDest =scanner2.nextLine().trim();
                    pieceColumnDest = columnToIndex(pieceDest.charAt(0));
                    pieceRowDest = rowToIndex(pieceDest.charAt(1));
//                    System.out.println(piece);
                    boolean movement = move(pieces,piece.getName(),currentCol,currentRow,pieceColumnDest,pieceRowDest);
                    if (movement){
                        for (Piece pieceR : pieces ) {
                            if (pieceR.getCol()== pieceColumnDest && pieceR.getRow() == pieceRowDest){
                                pieces.remove(pieceR);
                                break;
                            }
                        }
//                        System.out.println("you can move");
//                        System.out.println(piece);
                        piece.setCol(pieceColumnDest);
                        piece.setRow(pieceRowDest);
                        printBoard(pieces);
                    }else {
                        System.out.println("you can't move to this cell");
                        printBoard(pieces);
                    }
                }
            }
//            System.out.println("This cell is empty");
        }else {
            System.out.println("Please enter a valid cell Coordinates");
        }
    }

//    static void pieceCaptured (List<Piece> pieces ,int pieceColumnDest, int pieceRowDest){
//        for (Piece piece : pieces ) {
//            if (piece.getCol()== pieceColumnDest && piece.getRow() == pieceRowDest){
////                System.out.println("this is a piece that should be removed" +rmvPiece);
//                pieces.remove(piece);
//                break;
//            }
//        }
//    }
    static int rowToIndex(char rowChar) {
        return Integer.parseInt(String.valueOf(rowChar));
    }
    static int columnToIndex(char columnChar) {
            return columnChar - 'a';
        }
    static boolean isValid(String piecePlace){
        return piecePlace.matches("[a-h][0-7]");
    }
    static List<Piece> setPieces(){
        List<Piece> pieces = new ArrayList<>();
        for (int col = 0; col < 8; col++) {
            pieces.add(new Pawn(col, 1, BLACK,'P'));
            pieces.add(new Pawn(col, 6, WHITE,'P'));
        }
        pieces.add(new Rook(0, 0, BLACK,'R'));
        pieces.add(new Rook(7, 0, BLACK,'R'));
        pieces.add(new Rook(0, 7, WHITE,'R'));
        pieces.add(new Rook(7, 7, WHITE,'R'));
        pieces.add(new Knight(1, 0,BLACK,'N'));
        pieces.add(new Knight(6, 0,BLACK,'N'));
        pieces.add(new Knight(1, 7,WHITE,'N'));
        pieces.add(new Knight(6, 7,WHITE,'N'));
        pieces.add(new Bishop(2, 0,BLACK,'B'));
        pieces.add(new Bishop(5, 0,BLACK,'B'));
        pieces.add(new Bishop(2, 7,WHITE,'B'));
        pieces.add(new Bishop(5, 7,WHITE,'B'));
        pieces.add(new Queen(3, 0,BLACK,'Q'));
        pieces.add(new Queen(3, 7,WHITE,'Q'));
        pieces.add(new King(4, 0,BLACK,'K'));
        pieces.add(new King(4, 7,WHITE,'K'));
        return pieces;
    }

    static boolean move(List<Piece> pieces,char pieceName, int currentCol, int currentRow, int destCol, int destRow){
        int diffCol= Math.abs(destCol-currentCol);
        int diffRow=Math.abs(destRow-currentRow);
      if(pieceName=='R'){
          if(currentCol ==destCol||currentRow==destRow){
                return !PieceInPath( pieces,pieceName,  currentCol,  currentRow,  destCol,  destRow);
          }
      }else if(pieceName=='B'){
          if(currentCol -currentRow ==destCol-destRow || currentCol+currentRow==destCol+destRow){
              return !PieceInPath( pieces,pieceName,  currentCol,  currentRow,  destCol,  destRow);
          }
      }else if(pieceName=='Q'){
          if(currentCol -currentRow ==destCol-destRow || currentCol+currentRow==destCol+destRow || currentCol ==destCol||currentRow==destRow){
              return !PieceInPath( pieces,pieceName,  currentCol,  currentRow,  destCol,  destRow);
          }
       }else if(pieceName=='K'){
          return diffCol<=1 && diffRow<=1;
      }else if(pieceName=='N'){
          return diffCol==1 && diffRow==2 ||  diffCol==2 && diffRow==1;
      } else if (pieceName=='P') {
          return true;
      }
        return false;
    }

    static boolean PieceInPath(List<Piece> pieces ,char pieceName,int currentCol, int currentRow, int destCol, int destRow ){
//        if(pieceName=='R' || pieceName=='Q' || pieceName=='B'){
            int rowIncrement = (currentRow == destRow) ? 0 : (currentRow < destRow) ? 1 : -1;
            int colIncrement = (currentCol == destCol) ? 0 : (currentCol < destCol) ? 1 : -1;
            int row = currentRow + rowIncrement;
            int col = currentCol + colIncrement;
            while (row != destRow || col != destCol  || row != destRow && col != destCol) {
                for (Piece piece : pieces) {
                    if (piece.getRow() == row && piece.getCol() == col) {
                        return true;
                    }
                }
                row += rowIncrement;
                col += colIncrement;
            }
//        }
        return false;
    }
}

