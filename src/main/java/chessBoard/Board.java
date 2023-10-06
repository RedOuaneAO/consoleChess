package chessBoard;

public class Board {
   private int rows;
    private int columns;
    private Piece pieces;

    public Board(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
    }

    public Board(int rows, int columns, Piece pieces) {
        this.rows = rows;
        this.columns = columns;
        this.pieces = pieces;
    }


    public Integer getRows() {
        return rows;
    }
    public Integer getColumns() {
        return columns;
    }

    public Piece getPieces() {
        return pieces;
    }
    public Board(int rows, Integer columns) {
        this.rows = rows;
        this.columns = columns;
    }

    public void setPieces(Piece pieces) {
        this.pieces = pieces;
    }
}
