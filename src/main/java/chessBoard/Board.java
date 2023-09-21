package chessBoard;

public class Board {
   private int rows;
    private int columns;
//    private Piece[][] pieces;

    public Board(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
//        this.pieces = pieces;
    }

    public Board(int rows, Integer columns) {
        this.rows = rows;
        this.columns = columns;
    }

    public Integer getRows() {
        return rows;
    }
    public Integer getColumns() {
        return columns;
    }
//    public Piece[][] getPieces() {
//        return pieces;
//    }
}
