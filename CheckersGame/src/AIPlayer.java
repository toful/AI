import java.util.ArrayList;
import java.util.List;

public class AIPlayer {
    private int maxLevel = 7;
    private int player;
    private int algorithm;
    private int heuristic;

    private Movement movement;
    private Boolean moveSet;

    public AIPlayer( int player, int algorithm, int heuristic ) {
        this.player = player;
        this.algorithm = algorithm;
        if( algorithm == 1 ) maxLevel += 2;
        this.heuristic = heuristic;
    }

    public Movement decideMove( Game board ){
        switch ( algorithm ){
            case 0:
                moveSet = false;
                miniMax( board, 1 );
                break;
            case 1:
                moveSet = false;
                alphaBeta( board, 1, Integer.MIN_VALUE, Integer.MAX_VALUE );
                break;
            default:
                moveSet = false;
                alphaBeta( board, 1, Integer.MIN_VALUE, Integer.MAX_VALUE );
                break;
        }
        //System.out.println("IA result: "+movement.currentPosition.x+" "+movement.currentPosition.y+" "+movement.nextPosition.x+" "+movement.nextPosition.x );
        return movement;
    }

    public IAReturnValue alphaBeta( Game board, int level, int alpha, int beta ){
        IAReturnValue result;
        //Generating all the possible movements
        List<Movement> movements = getAllMovements( board );

        if( board.gameOver() ){
            if( board.getWinner() == player ) return ( new IAReturnValue( Integer.MAX_VALUE, null ) );
            else return ( new IAReturnValue( Integer.MIN_VALUE, null ) );
        }
        else if( level == maxLevel ) return ( new IAReturnValue( getBoardHeuristic(board), null ) );
        else if( movements.isEmpty() ){
            Game newGame = board.clone();
            newGame.resetPlay();
            result = alphaBeta( newGame, (level+1), alpha, beta );
            if( level%2 == 1 ) {
                if (result.value >= alpha) return ( new IAReturnValue( result.value, newGame ) );
                else return ( new IAReturnValue( alpha, newGame ) );
            }
            else{
                if (result.value < beta) return ( new IAReturnValue( result.value, newGame ) );
                else return ( new IAReturnValue( beta, newGame ) );
            }
        }
        else {
            IAReturnValue returnValue = new IAReturnValue();
            Movement move;

            while( !movements.isEmpty() && alpha < beta ){
                move = movements.get(0);
                movements.remove(0);
                Game newGame = board.clone();
                newGame.makeMove( move.currentPosition, move.nextPosition );

                result = alphaBeta( newGame, (level+1), alpha, beta );

                //Case of a MAX node (IA player)
                if( level%2 == 1 ){
                    if( level == 1 && !moveSet ){
                        moveSet = true;
                        movement = move;
                    }
                    if( result.value > alpha ) {
                        alpha = result.value;
                        returnValue.board = newGame;
                        //case of the first node of the tree
                        if (level == 1){
                            movement = move;
                            //System.out.println( "value: "+ result.value );
                            //if( alpha == Integer.MAX_VALUE )System.out.println("I have won!");
                            //else if ( alpha == Integer.MIN_VALUE )System.out.println("I have lost!");
                        }
                    }
                }//Case of a MIN node
                else{
                    if( result.value < beta ){
                        beta = result.value;
                        returnValue.board = newGame;
                    }
                }
            }
            if( level%2 == 1 ) returnValue.value = alpha;
            else returnValue.value = beta;
            return returnValue;
        }
    }


    public IAReturnValue miniMax( Game board, int level ){
        IAReturnValue result;
        //Generating all the possible movements
        List<Movement> movements = getAllMovements( board );

        if( board.gameOver() ){
            if( board.getWinner() == player ) return ( new IAReturnValue( Integer.MAX_VALUE, null ) );
            else return ( new IAReturnValue( Integer.MIN_VALUE, null ) );
        }
        else if( level == maxLevel ) return ( new IAReturnValue( getBoardHeuristic(board), null ));
        else if( movements.isEmpty() ){
            Game newGame = board.clone();
            newGame.resetPlay();
            return miniMax( newGame, (level+1) );
        }
        else {
            IAReturnValue returnValue = new IAReturnValue();
            if( level%2 == 1 ) returnValue.value = Integer.MIN_VALUE;
            else returnValue.value = Integer.MAX_VALUE;

            for( Movement move : movements){
                Game newGame = board.clone();
                newGame.makeMove( move.currentPosition, move.nextPosition );

                result = miniMax( newGame, (level+1) );

                //Case of a MAX node (IA player)
                if( level%2 == 1 ){
                    if( level == 1 && !moveSet ){
                        moveSet = true;
                        movement = move;
                    }
                    if( result.value > returnValue.value  ){
                        returnValue.value = result.value;
                        returnValue.board = newGame;
                        //case of the first node of the tree
                        if( level == 1 ){
                            movement = move;
                            //System.out.println( "value: "+ result.value );
                            //if( result.value == Integer.MAX_VALUE )System.out.println("I have won!");
                            //else if ( result.value == Integer.MIN_VALUE )System.out.println("I have lost!");
                        }
                    }
                }
                //Case of a MIN node
                else{
                    if( result.value < returnValue.value ){
                        returnValue.value = result.value;
                        returnValue.board = newGame;
                    }
                }
            }
            return returnValue;
        }
    }


    public List<Movement> getAllMovements( Game board){
        List<Movement> movements = new ArrayList<>();
        for( Position token: board.getPlayerTokens( board.getActualPlayer() ) ){
            for( Position tokenMove: board.tokenMovementsAvailable( token.x, token.y ) ){
                movements.add( new Movement(token, tokenMove) );
            }
        }
        return movements;
    }


    public int getBoardHeuristic( Game board ){
        switch ( heuristic ){
            case 0:
                return heuristic1(board);
            case 1:
                return heuristic2(board);
            case 2:
                return heuristic3(board);
            default:
                return heuristic3( board );
        }
    }


    public int heuristic1(Game board){
        List<Position> tokens = board.getPlayerTokens( player );
        int value;
        if( player == board.WHITE ){
            value = 5*board.whiteKingTokens + 3*( board.whiteTotalTokens - board.whiteKingTokens);
            value = value - ( 5*board.redKingTokens + 3*( board.redTotalTokens - board.redKingTokens ) );
            value = 100*value;

        }
        else{
            value = 5*board.redKingTokens + 3*( board.redTotalTokens - board.redKingTokens );
            value = value - ( 5*board.whiteKingTokens + 3*( board.whiteTotalTokens - board.whiteKingTokens) );
            value = 100*value;
        }
        int aux=0;
        for( Position pos : tokens ) if( pos.y == 3 || pos.y == 4 ) aux++;
        value += 10*aux;
        value += ( ( int )( Math.random()* 50+1 ) )%10;
        return value;
    }


    public int heuristic2(Game board){
        if( player == board.WHITE ){
            int value = 5*board.whiteKingTokens + 3*( board.whiteTotalTokens - board.whiteKingTokens);
            value = value - ( 5*board.redKingTokens + 3*( board.redTotalTokens - board.redKingTokens ) );
            value = 100*value;
            int aux=0;
            for( Position token : board.getPlayerTokens( player) ) aux += token.y;
            aux += 7*board.whiteKingTokens;
            value = value +  10*( aux/( board.getPlayerTokens( player).size() + board.whiteKingTokens ) );
            value += ( (int)(Math.random()*50+1) )%10;
            return value;
        }
        else{
            int value = 5*board.redKingTokens + 3*( board.redTotalTokens - board.redKingTokens );
            value = value - ( 5*board.whiteKingTokens + 3*( board.whiteTotalTokens - board.whiteKingTokens) );
            value = 100*value;
            int aux=0;
            for( Position token : board.getPlayerTokens( player ) ) aux += (7 - token.y);
            aux += 7*board.redKingTokens;
            value = value + 10*( aux/ ( board.getPlayerTokens( player).size() + board.redKingTokens ) );
            value += ( (int)(Math.random()*50+1) )%10;
            return value;
        }
    }


    public int heuristic3(Game board){
        List<Position> tokens = board.getPlayerTokens( player );
        int value;
        int aux;
        if( player == board.WHITE ){
            value = 5*board.whiteKingTokens + 3*( board.whiteTotalTokens - board.whiteKingTokens);
            value = value - ( 5*board.redKingTokens + 3*( board.redTotalTokens - board.redKingTokens ) );
            value = (100 * value);
            aux = 0;
            for( Position token : board.getPlayerTokens( player) ) aux += token.y;
            aux += 7*board.whiteKingTokens;
            value = value +  10*( aux/( board.getPlayerTokens( player).size() + board.whiteKingTokens ) );
        }
        else{
            value = 5*board.redKingTokens + 3*( board.redTotalTokens - board.redKingTokens );
            value = value - ( 5*board.whiteKingTokens + 3*( board.whiteTotalTokens - board.whiteKingTokens) );
            value = (100 * value);
            aux=0;
            for( Position token : board.getPlayerTokens( player ) ) aux += (7 - token.y);
            aux += 7*board.redKingTokens;
            value = value + 10*( aux/ ( board.getPlayerTokens( player).size() + board.redKingTokens ) );
        }
        for( Position pos : tokens ) if( pos.y == 3 || pos.y == 4 ) aux++;
        value = value + 10*aux;
        value += ( (int)(Math.random()*50+1) )%10;
        return value;
    }

    public int getIAPlayer(){
        return player;
    }

}
