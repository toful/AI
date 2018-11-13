public class Movement {
    public Position currentPosition;
    public Position nextPosition;

    public Movement(Position currentPosition, Position nextPosition) {
        this.currentPosition = currentPosition;
        this.nextPosition = nextPosition;
    }

    public Movement(){}
}
