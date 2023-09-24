package chessBoard;

public abstract  class Piece {
    private Color color;
    private char name ;
    private  int row ;
    private  int col ;

    public Piece( int col ,int row,Color color ,char name) {
        this.color = color;
        this.row = row;
        this.col = col;
        this.name =name;
    }

    public Color getColor() {
        return color;
    }

    public char getName() {
        return name;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setName(char name) {
        this.name = name;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setCol(int col) {
        this.col = col;
    }


    public enum Color{
        BLACK , WHITE
    }

    @Override
    public String toString() {
        return "Piece{" +
                "color=" + color +
                ", name=" + name +
                ", row=" + row +
                ", col=" + col +
                '}';
    }
}
