import java.util.ArrayList;
import java.util.Objects;
import java.util.function.Function;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class Node {
    private State state;
    private ArrayList<Operator> path;
    private float cost;

    //Some variables for the heuristics
    private float value;

    public Node(State state, ArrayList<Operator> path ) {
        this.state = state;
        this.path = path;
    }

    public Node(State state, ArrayList<Operator> path, float cost) {
        this.state = state;
        this.path = path;
        this.cost = cost;
    }

    public Node(State state, ArrayList<Operator> path, float cost, float value) {
        this.state = state;
        this.path = path;
        this.cost = cost;
        this.cost = value;
    }

    public ArrayList<Operator> copyPath() {
        ArrayList<Operator> newPath = new ArrayList<>();
        for (Operator o: path) newPath.add( o );
        return newPath;
    }

    public State getState() {
        return state;
    }

    public ArrayList<Operator> getPath() {
        return path;
    }

    public float getCost() {
        return cost;
    }

    public void setCost( float cost){
        this.cost = cost;
    }

    public void updateCost( Map map, Node lastNode){
        this.cost = lastNode.getCost() + movementCost( map, lastNode.getState() );
    }

    public float movementCost( Map map, State lastState ){
        int[][] m = map.getMap();
        if( m[lastState.getRow()][lastState.getColumn()] > m[state.getRow()][state.getColumn()] ){
            return (float) 0.5;
        }
        else return 1 + m[state.getRow()][state.getColumn()] - m[lastState.getRow()][lastState.getColumn()] ;
    }

    public void setValue( float value ){
        this.value = value;
    }

    public float getValue() {
        return value;
    }

    public Function<State, Float> getHeuristic( int heuristic_num, State finalState, State lastState, Map map ){
        switch ( heuristic_num ) {
            case 0:
                return heuristic1();
            case 1:
                return heuristic2( finalState );
            case 2:
                return heuristic3( finalState );
            case 3:
                return heuristic4();
            case 4:
                return heuristic5();
            case 5:
                return heuristic6( finalState );
            case 6:
                return heuristic7( finalState );
            case 7:
                return heuristic8( lastState, map );
            case 8:
                return heuristic9( lastState, map );
            default:
                return heuristic3( finalState );
        }
    }

    /**First heuristic for the informed search algorithm
     * The value of this node is equal to cost medium of all the nodes until
     * this node.
     */
    public Function<State, Float>  heuristic1() {
        return( x ) -> cost / path.size();
    }

    /**Second heuristic for the informed search algorithm
     * The value of this node is equal to the distance of
     * this node to the final state.
     */
    public Function<State, Float> heuristic2(State finalState ) {
        return( x ) -> (float) sqrt( pow( finalState.getColumn() - x.getColumn(), 2 ) + pow( finalState.getRow() - x.getRow(), 2 ) );
    }

    /**Third heuristic for the informed search algorithm
     * The value of this node is equal to cost medium of all the nodes until
     * this node plus the distance of this node to the final state.
     */
    public Function<State, Float> heuristic3(State finalState ) {
        return( x ) -> (float) (cost / path.size()
                + sqrt( pow( finalState.getColumn() - x.getColumn(), 2 ) + pow( finalState.getRow() - x.getRow(), 2 ) ) );
    }

    /**Forth heuristic for the informed search algorithm
     * The value of this node is equal to cost
     */
    public Function<State, Float> heuristic4() {
        return( x ) -> cost;
    }

    /**Fifth heuristic for the informed search algorithm
     * The value of this node is equal to the number of nodes for arriving here
     */
    public Function<State, Float> heuristic5() {
        return( x ) -> (float) path.size();
    }

    /**Sixth heuristic for the informed search algorithm
     * The value of this node is equal to cost of
     * this node plus the distance of this node to the final state.
     */
    public Function<State, Float> heuristic6(State finalState ) {
        return( x ) -> (float) ( cost + sqrt( pow( finalState.getColumn() - x.getColumn(), 2 ) + pow( finalState.getRow() - x.getRow(), 2 ) ) );
    }

    public Function<State, Float> heuristic7(State finalState ) {
        return( x ) -> (float) ( cost +  path.size() +
                sqrt( pow( finalState.getColumn() - x.getColumn(), 2 ) + pow( finalState.getRow() - x.getRow(), 2 ) ) );
    }

    public Function<State, Float> heuristic8(State lastState, Map map ) {
        int[][] m = map.getMap();
        if( m[lastState.getRow()][lastState.getColumn()] > m[state.getRow()][state.getColumn()] ){
            return( x ) -> (float) 0.5;
        }
        else return( x ) -> (float) m[state.getRow()][state.getColumn()] - m[lastState.getRow()][lastState.getColumn()] ;
    }

    public Function<State, Float> heuristic9(State lastState, Map map ) {
        int[][] m = map.getMap();
        if( m[lastState.getRow()][lastState.getColumn()] > m[state.getRow()][state.getColumn()] ){
            return( x ) -> (float) 0.5 + path.size();
        }
        else return( x ) -> (float) m[state.getRow()][state.getColumn()] - m[lastState.getRow()][lastState.getColumn()] + path.size();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return Objects.equals(state, node.state);
    }

    @Override
    public int hashCode() {

        return Objects.hash(state);
    }

}
