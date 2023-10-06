import  chessBoard.*;
import chessService.ChessService;
import pieces.*;
import java.util.ArrayList;
import java.util.List;
import static chessBoard.Piece.Color.*;
import static chessService.ChessService.printBoard;

public class Main {

    public static void main(String[] args) {
        String block = "█████   ███   █████          ████\n" +
                "░░███   ░███  ░░███          ░░███\n" +
                " ░███   ░███   ░███   ██████  ░███   ██████   ██████  █████████████    ██████\n" +
                " ░███   ░███   ░███  ███░░███ ░███  ███░░███ ███░░███░░███░░███░░███  ███░░███\n" +
                " ░░███  █████  ███  ░███████  ░███ ░███ ░░░ ░███ ░███ ░███ ░███ ░███ ░███████\n" +
                "  ░░░█████░█████░   ░███░░░   ░███ ░███  ███░███ ░███ ░███ ░███ ░███ ░███░░░\n" +
                "    ░░███ ░░███     ░░██████  █████░░██████ ░░██████  █████░███ █████░░██████\n" +
                "     ░░░   ░░░       ░░░░░░  ░░░░░  ░░░░░░   ░░░░░░  ░░░░░ ░░░ ░░░░░  ░░░░░░\n";

        System.out.println(block);
        printBoard(setPieces());
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
}





