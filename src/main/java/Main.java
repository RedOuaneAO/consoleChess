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

        Board board =new Board(8,8);
        System.out.print("   a   b   c   d   e   f   g   h\n");
        for (int row = 0; row <board.getRows(); row++) {
            System.out.print(row + " ");
            for (int col = 0; col < board.getColumns(); col++) {
                boolean pieceFound = false;
                char pieceName = ' ';
                for (Piece piece : pieces) {
                    if (piece.getRow() == row && piece.getCol() == col) {
                        pieceName = piece.getName();
                        pieceFound = true;
                        break;
                    }
                }

                String backgroundColor = ((row + col) % 2 == 0) ? "\u001B[44m" : "\u001B[47m";

                // Print the square with or without a piece
                if (pieceFound) {
                System.out.print(backgroundColor + "  "+ pieceName +  " \u001B[0m");
                } else {
                    System.out.print(backgroundColor +"    "+ "\u001B[0m");
                }
            }
            System.out.println();
        }

        System.out.print("   a   b   c   d   e   f   g   h\n");
        Scanner scanner =new Scanner(System.in);
        System.out.println("Enter Piece Coordinates");
        String piecePlace = scanner.nextLine();
        Scanner scanner2 =new Scanner(System.in);
        System.out.println("Enter Piece Destination Coordinates");
        String pieceDest =scanner2.nextLine();
        if(isValid(piecePlace)){
            int pieceColumn = columnToIndex(piecePlace.charAt(0));
            int pieceRow = rowToIndex(piecePlace.charAt(1));
            int pieceColumnDest = columnToIndex(pieceDest.charAt(0));
            int pieceRowDest = rowToIndex(pieceDest.charAt(1));
            System.out.println(pieceColumn + " " +pieceRow);
            for(Piece piece : pieces){
                if (piece.getCol()== pieceColumn && piece.getRow() == pieceRow) {
                    System.out.println(piece);
                    piece.setCol(pieceColumnDest);
                    piece.setRow(pieceRowDest);
                    printBoard(pieces);
                }
            }
//            pieces.forEach(System.out::println);
        }else {
            System.out.println("Please enter a valid cell Coordinates");
        }
    }
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
            pieces.add(new Pawn(col, 1, WHITE,'P'));
            pieces.add(new Pawn(col, 6, BLACK,'P'));
        }
        pieces.add(new Rook(0, 0, WHITE,'R'));
        pieces.add(new Rook(7, 0, WHITE,'R'));
        pieces.add(new Rook(0, 7, BLACK,'R'));
        pieces.add(new Rook(7, 7, BLACK,'R'));
        pieces.add(new Knight(1, 0,WHITE,'N'));
        pieces.add(new Knight(6, 0,WHITE,'N'));
        pieces.add(new Knight(1, 7,BLACK,'N'));
        pieces.add(new Knight(6, 7,BLACK,'N'));
        pieces.add(new Bishop(2, 0,WHITE,'B'));
        pieces.add(new Bishop(5, 0,WHITE,'B'));
        pieces.add(new Bishop(2, 7,BLACK,'B'));
        pieces.add(new Bishop(5, 7,BLACK,'B'));
        pieces.add(new Queen(3, 0,WHITE,'Q'));
        pieces.add(new Queen(3, 7,BLACK,'Q'));
        pieces.add(new King(4, 0,WHITE,'K'));
        pieces.add(new King(4, 7,BLACK,'K'));
        return pieces;
    }
}

