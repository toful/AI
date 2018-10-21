import java.util.function.Function;

enum Operator{

    UP {
        public Function<State, State> operate(){
            return ( x )-> new State(x.getRow()-1, x.getColumn() );

            /*return ( x )-> {
                ArrayList<Operator> path;
                path = x.copyPath();
                path.add(UP);
                return new State(x.getRow()-1, x.getColumn(), path); };*/
        }
    },
    DOWN {
        public Function<State, State> operate(){
            return ( x )-> new State(x.getRow()+1, x.getColumn() );
            /*return ( x )-> {
                ArrayList<Operator> path;
                path = x.copyPath();
                path.add(DOWN);
                return new State(x.getRow()+1, x.getColumn(), path); };*/
        }
    },
    LEFT {
        public Function<State, State> operate(){
            return ( x )-> new State(x.getRow(), x.getColumn()-1 );
            /*return ( x )-> {
                ArrayList<Operator> path;
                path = x.copyPath();
                path.add(LEFT);
                return new State(x.getRow(), x.getColumn()-1, path); };*/
        }
    },
    RIGHT {
        public Function<State, State> operate(){
            return ( x )-> new State(x.getRow(), x.getColumn()+1 );
            /*return ( x )-> {
                ArrayList<Operator> path;
                path = x.copyPath();
                path.add(RIGHT);
                return new State(x.getRow(), x.getColumn()+1, path); };*/
        }
    };

    public abstract Function<State, State> operate();


}
