import java.util.Objects;

public class State {
    private int row;
    private int column;


    public State(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public boolean isCorrect(Map map){
        if ( row < 0 || column < 0 || row >= map.getRows() || column >= map.getColumns() || map.getMap()[row][column] == -1 )
            return false;
        else return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        State state = (State) o;
        return row == state.row &&
                column == state.column;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, column);
    }
}
