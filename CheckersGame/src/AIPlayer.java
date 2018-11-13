import java.util.ArrayList;
import java.util.List;

public class AIPlayer {
    private int maxLevel = 5;
    private int player;

    private Position token, tokenMove;

    public AIPlayer( int player ){
        this.player = player;
        token = new Position();
        tokenMove = new Position();
    }


    public IAReturnValue miniMax( Game board, int level ){
        IAReturnValue result;
        //Generating all the possible movements
        List<Movement> movements = getAllMovements( board );

        if( board.gameOver() ){
            if( board.getWinner() == player ) return ( new IAReturnValue( Integer.MAX_VALUE, null ) );
            else return ( new IAReturnValue( Integer.MIN_VALUE, null ) );
        }
        else if( level == maxLevel || movements.isEmpty() ) return ( new IAReturnValue( getBoardHeuristic(board), null ));
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
                    if( result.value >= returnValue.value  ){
                        returnValue.value = result.value;
                        returnValue.board = newGame;
                        //case of the first node of the tree
                        if( level == 1 ){
                            this.token = move.currentPosition;
                            this.tokenMove = move.nextPosition;
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
        return heuristic2(board);
    }

    public int heuristic1(Game board){
        if( player == board.WHITE ){
            return ( board.whiteTotalTokens + board.whiteKingTokens ) - ( board.redTotalTokens + board.redKingTokens );
        }
        else return ( board.redTotalTokens + board.redKingTokens ) - ( board.whiteTotalTokens + board.whiteKingTokens );
    }

    public int heuristic2(Game board){
        if( player == board.WHITE ){
            return 2*( board.whiteTotalTokens - board.redTotalTokens ) + board.whiteKingTokens;
        }
        else return 2*( board.redTotalTokens - board.whiteTotalTokens ) + board.redKingTokens;
    }

    public int heuristic3(Game board){
        if( player == board.WHITE ){
            return 5*( board.whiteTotalTokens - board.redTotalTokens ) + board.whiteKingTokens;
        }
        else return 5*( board.redTotalTokens - board.whiteTotalTokens ) + board.redKingTokens;
    }



    public Position getToken() {
        return token;
    }

    public Position getTokenMove() {
        return tokenMove;
    }

    public int getIAPlayer(){
        return player;
    }

}

 /*public IAReturnValue miniMax( Game board, int level ){
        List<Position> playerTokens;
        List<Position> tokenMoves;
        IAReturnValue resultValue;
        Boolean hasEntered = false;

        if( board.gameOver() ){
            if( board.getWinner() == player ) return ( new IAReturnValue( Integer.MAX_VALUE, null ) );
            else return ( new IAReturnValue( Integer.MIN_VALUE, null ) );
        }
        else if( level == maxLevel ) return ( new IAReturnValue( getBoardHeuristic(board), null ));
        else {
            IAReturnValue returnValue = new IAReturnValue();
            if( level%2 == 1 ) returnValue.value = Integer.MIN_VALUE;
            else returnValue.value = Integer.MAX_VALUE;


            playerTokens = board.getPlayerTokens( board.getActualPlayer() );
            for( Position token: playerTokens ){
                tokenMoves = board.tokenMovementsAvailable( token.x, token.y );
                for( Position tokenMove: tokenMoves ){
                    hasEntered = true;
                    Game newGame = board.clone();
                    newGame.makeMove( token, tokenMove );

                    resultValue = miniMax( newGame, (level+1) );

                    //Case of a MAX node (IA player)
                    if( level%2 == 1 ){
                        if( resultValue.value > returnValue.value  ){
                            returnValue.value = resultValue.value;
                            returnValue.board = newGame;
                            //case of the first node of the tree
                            if( level == 1 ){
                                this.token = token;
                                this.tokenMove = tokenMove;
                            }
                        }
                    }
                    else{
                        if( resultValue.value < returnValue.value ){
                            returnValue.value = resultValue.value;
                            returnValue.board = newGame;
                        }
                    }
                 }
            }
            if( hasEntered ) return returnValue;
            else return ( new IAReturnValue( getBoardHeuristic(board), null ));
        }
    }*/
