import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.*;

public class InformedSearch {

    public static void main(String[] args) {
        String file = "map2.txt";
        Map map = new Map( file );
        ArrayList<Operator> solution;
        State finalState ;

        try{
            PrintWriter writer = new PrintWriter("result.txt", "UTF-8");
            if( file.equals("map.txt") ) finalState  = new State(9,9);
            else finalState  = new State(11,0 );

            map.printMap();
            writer.println("Breadth");
            System.out.println( "Solution with the breadth Uninformed Search" );
            solution = breadthFirstUninformedSearch( new State(0,0), finalState , map );
            System.out.println( "Steps: " + solution.size() );
            for ( Operator o: solution ){
                System.out.println( o );
                writer.print( o + " - " );
            }

            map.printMap();
            writer.println("Depth");
            System.out.println( "Solution with the depth Uninformed Search" );
            solution = depthFirstUninformedSearch( new State(0,0), finalState , map );
            System.out.println( "Steps: " + solution.size() );
            for ( Operator o: solution ){
                System.out.println( o );
                writer.print( o + " - " );
            }

            writer.println("Best First");
            for( int i = 0; i < 9; i++ ){
                map.printMap();
                System.out.println( "Informed Search Best First with the heuristic number: " + (i+1) );
                solution = informedSearchBestFirst( new State(0,0), finalState , map, i );
                System.out.println( "Steps: " + solution.size() );
                writer.println("\nHeuristic " + (i+1) );
                for ( Operator o: solution ){
                    System.out.println( o );
                    writer.print( o + " - " );
                }
            }

             writer.println("A*");
            for( int i = 0; i < 9; i++ ){
                map.printMap();
                System.out.println( "Informed Search A* with the heuristic number: " + (i+1) );
                solution = informedSearchAStar( new State(0,0), finalState , map, i );
                System.out.println( "Steps: " + solution.size() );
                writer.println("\nHeuristic " + (i+1) );
                for ( Operator o: solution ){
                    System.out.println( o );
                    writer.print( o + " - " );
                }
            }

            writer.close();

        }catch (FileNotFoundException e) {
             e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
             e.printStackTrace();
        };

    }

    public static ArrayList<Operator> breadthFirstUninformedSearch( State initialState, State finalState, Map map){
        ArrayList<Node> notTreatedStatesList = new ArrayList<>();
        HashMap<Node, Integer> treatedStatesList = new HashMap<>();
        ArrayList<Operator> path = new ArrayList<>();
        Boolean found = false;
        Node actual;
        Node next;

        notTreatedStatesList.add( new Node( initialState, path ) );

        while ( !found && !notTreatedStatesList.isEmpty() ){
            actual = notTreatedStatesList.get(0);
            notTreatedStatesList.remove(0);

            if ( actual.getState().equals( finalState ) ){
                found = true;
                path = actual.getPath();
                System.out.println("Cost: " + actual.getCost() );
            }
            else {
                for ( Operator o: Operator.values() ){
                    ArrayList<Operator> newPath;
                    newPath = actual.copyPath();
                    newPath.add( o );
                    next = new Node( o.operate().apply( actual.getState() ), newPath );
                    if( next.getState().isCorrect(map) && !notTreatedStatesList.contains(next) && !treatedStatesList.containsKey(next)){
                        next.updateCost(map, actual);
                        notTreatedStatesList.add(next);
                    }
                }
                treatedStatesList.put(actual, 0);
            }
        }
        System.out.println( "Treated nodes: " + treatedStatesList.size() );
        return path;
    }

    public static ArrayList<Operator> depthFirstUninformedSearch( State initialState, State finalState, Map map ){
        Stack<Node> notTreatedStatesList = new Stack<>();
        HashMap<Node, Integer> treatedStatesList = new HashMap<>();
        ArrayList<Operator> path = new ArrayList<>();
        Boolean found = false;
        Node actual;
        Node next;

        notTreatedStatesList.add( new Node( initialState, path ) );

        while ( !found && !notTreatedStatesList.isEmpty() ){
            actual = notTreatedStatesList.pop();

            if ( actual.getState().equals( finalState ) ){
                found = true;
                path = actual.getPath();
                System.out.println("Cost: " + actual.getCost() );
            }
            else {
                for ( Operator o: Operator.values() ){
                    ArrayList<Operator> newPath;
                    newPath = actual.copyPath();
                    newPath.add( o );
                    next = new Node( o.operate().apply( actual.getState() ), newPath );
                    if( next.getState().isCorrect(map) && !notTreatedStatesList.contains(next) && !treatedStatesList.containsKey(next)) {
                        next.updateCost(map, actual);
                        notTreatedStatesList.push(next);
                    }
                }
                treatedStatesList.put(actual, 0);
            }
        }
        System.out.println( "Treated nodes: " + treatedStatesList.size() );
        return path;
    }

    public static ArrayList<Operator> informedSearchBestFirst( State initialState, State finalState, Map map, int heuristic_num ){
        ArrayList<Node> notTreatedStatesList = new ArrayList<>();
        HashMap<Node, Integer> treatedStatesList = new HashMap<>();
        ArrayList<Operator> path = new ArrayList<>();
        Boolean found = false;
        Node actual;
        Node next;

        notTreatedStatesList.add( new Node( initialState, path, 0, 0 ) );

        while ( !found && !notTreatedStatesList.isEmpty() ){
            actual = notTreatedStatesList.get(0);
            notTreatedStatesList.remove(0);

            if ( actual.getState().equals( finalState ) ){
                found = true;
                path = actual.getPath();
                System.out.println("Cost: " + actual.getCost() );
            }
            else {
                for ( Operator o: Operator.values() ){

                    //Creating the next nodes
                    ArrayList<Operator> newPath = actual.copyPath();
                    newPath.add( o );
                    next = new Node( o.operate().apply( actual.getState() ), newPath );

                    if( next.getState().isCorrect(map) && !notTreatedStatesList.contains(next) && !treatedStatesList.containsKey(next)){
                        next.updateCost( map, actual );
                        //next.setValue( next.heuristic( finalState ).apply( next.getState() ) );
                        next.setValue( next.getHeuristic(heuristic_num, finalState, actual.getState(), map ).apply( next.getState() ) );
                        notTreatedStatesList.add( next );
                    }

                }
                notTreatedStatesList.sort( Comparator.comparing( Node::getValue ) );
                /*for ( Node node: notTreatedStatesList ) System.out.println( node.getValue() );
                System.out.println( "Next step");*/
                treatedStatesList.put(actual, 0);
            }
        }
        System.out.println( "Treated nodes: " + treatedStatesList.size() );
        return path;
    }

    public static ArrayList<Operator> informedSearchAStar( State initialState, State finalState, Map map, int heuristic_num ){
        ArrayList<Node> notTreatedStatesList = new ArrayList<>();
        HashMap<Node, Integer> treatedStatesList = new HashMap<>();
        ArrayList<Operator> path = new ArrayList<>();
        Boolean found = false;
        Node actual;
        Node next;

        notTreatedStatesList.add( new Node( initialState, path, 0, 0 ) );

        while ( !found && !notTreatedStatesList.isEmpty() ){
            actual = notTreatedStatesList.get(0);
            notTreatedStatesList.remove(0);

            if ( actual.getState().equals( finalState ) ){
                found = true;
                path = actual.getPath();
                System.out.println("Cost: " + actual.getCost() );
            }
            else {
                for ( Operator o: Operator.values() ){

                    //Creating the next nodes
                    ArrayList<Operator> newPath = actual.copyPath();
                    newPath.add( o );
                    next = new Node( o.operate().apply( actual.getState() ), newPath );

                    if( next.getState().isCorrect(map) && !treatedStatesList.containsKey(next)){
                        next.updateCost( map, actual );
                        //next.setValue( next.heuristic( finalState ).apply( next.getState() ) );
                        next.setValue( next.getHeuristic(heuristic_num, finalState, actual.getState(), map ).apply( next.getState() ) + next.getCost() );
                        notTreatedStatesList.add( next );
                    }

                }
                notTreatedStatesList.sort( Comparator.comparing( Node::getValue ) );
                /*for ( Node node: notTreatedStatesList ) System.out.println( node.getValue() );
                System.out.println( "Next step");*/
                treatedStatesList.put(actual, 0);
            }
        }
        System.out.println( "Treated nodes: " + treatedStatesList.size() );
        return path;
    }

}
