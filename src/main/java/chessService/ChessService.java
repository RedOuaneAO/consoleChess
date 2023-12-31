package chessService;

import chessBoard.Board;
import chessBoard.Piece;
import pieces.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import static chessBoard.Piece.Color.*;

public class ChessService {

    public static void printBoard(List<Piece> pieces) {
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
                    boolean movement = move(pieces,piece.getName(),currentCol,currentRow,pieceColumnDest,pieceRowDest);
                    if (movement){
                        boolean sameColor =true;
                        Piece piece1 = null;
                        for (Piece pieceR : pieces ) {
                            if (pieceR.getCol()== pieceColumnDest && pieceR.getRow() == pieceRowDest){
                                if (!pieceR.getColor().equals(piece.getColor())){
                                    piece1 =pieceR;
                                    pieces.remove(pieceR);
                                    break;
                                }else{
                                    System.out.println("you cant capture this piece");
                                    sameColor=false;
                                    break;
                                }
                            }
                        }
                        if(sameColor){
                            piece.setCol(pieceColumnDest);
                            piece.setRow(pieceRowDest);
                        }
                        if(isCheck(pieces,piece.getColor() , piece.getName(), pieceRowDest,pieceColumnDest )){
                            System.out.println("king is in danger");
                            piece.setCol(currentCol);
                            piece.setRow(currentRow);
                            Optional.ofNullable(piece1).ifPresent(x ->pieces.add(x));
                        }
                        printBoard(pieces);
                    }else {
                        System.out.println("you can't move to this cell");
                        printBoard(pieces);
                    }
                }
            }
            System.out.println("This cell is empty");
            printBoard(pieces);
        }else {
            System.out.println("Please enter a valid cell Coordinates");
            printBoard(pieces);
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
        }
        else if (pieceName=='P') {
            if(currentRow ==6 || currentRow == 1){
                if(destRow == (currentRow+2) ||destRow == (currentRow-2) ||destRow == (currentRow+1) ||destRow == (currentRow-1)){
                    return !PieceInPath( pieces,pieceName,  currentCol,  currentRow,  destCol,  destRow);
                }
            }else {
                if(destRow == (currentRow+1) ||destRow == (currentRow-1)  ){
                    return !PieceInPath( pieces,pieceName,  currentCol,  currentRow,  destCol,  destRow);
                }
            }
            return false;
        }
        return false;
    }

    static boolean PieceInPath(List<Piece> pieces ,char pieceName,int currentCol, int currentRow, int destCol, int destRow ){
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
        return false;
    }

    static boolean isCheck(List<Piece> pieces, Piece.Color pieceColor ,char pieceName,int pieceRowDest  , int  pieceColumnDest){
        int kingCol = 0;
        int kingRow = 0;
        Piece.Color kingColor = null;
        for (Piece piece : pieces){
            if (piece.getName()== 'K' && piece.getColor().equals(pieceColor)){
                kingCol = piece.getCol();
                kingRow = piece.getRow();
                kingColor = piece.getColor();
                for (Piece opponentPiece : pieces){
                    if (!opponentPiece.getColor().equals(pieceColor)){
//                        System.out.println(opponentPiece.getName() +" "+ opponentPiece.getCol() +" "+ opponentPiece.getRow());
                        if(move( pieces, opponentPiece.getName(), opponentPiece.getCol(), opponentPiece.getRow(),  kingCol,  kingRow)){
                            return true;
                        }
                    }
                }
                return false;
            }
        }
        return false;
    }
}
