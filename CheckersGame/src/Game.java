
import java.util.ArrayList;
import java.util.List;

public class Game{
    public int numTilesPerRow = 8;
    public final int EMPTY = 0, RED = 2, WHITE = 1, RED_KING = 3, WHITE_KING = 4; //token values
    public int[][] board;

    public int redTotalTokens = 0, whiteTotalTokens = 0;
    private int redTokensAvailable = 0, whiteTokensAvailable = 0;
    public int redKingTokens = 0, whiteKingTokens = 0;

    public boolean onMove = false;
    private Position token_pos;
    private int player;

    public List< Position > actualToken_movements;
    private Boolean possibleJump;


    public Game() {
        board = new int[numTilesPerRow][numTilesPerRow];
        actualToken_movements = new ArrayList<>();
        redTokensAvailable = 0;
        whiteTokensAvailable = 0;
        player = WHITE;
        possibleJump = false;
        token_pos = new Position();
        initializeBoard();
    }

    public void initializeBoard(){
        //Setting the red tokens
        for( int col=0; col < numTilesPerRow; col+=2 ){
            board[5][col] = WHITE;
            board[7][col] = WHITE;
            whiteTokensAvailable += 2;
        }
        for( int col=1; col < numTilesPerRow; col+=2 ){
            board[6][col] = WHITE;
            whiteTokensAvailable++;
        }
        //Setting the white tokens
        for( int col=1; col < numTilesPerRow; col+=2 ){
            board[0][col] = RED;
            board[2][col] = RED;
            redTokensAvailable += 2;
        }
        for( int col=0; col < (numTilesPerRow); col+=2){
            board[1][col] = RED;
            redTokensAvailable++;
        }
        redTotalTokens = redTokensAvailable;
        whiteTotalTokens = whiteTokensAvailable;
    }

    public boolean move( int row, int col ){
        if( row < 0 || row >= numTilesPerRow || col < 0 || col >= numTilesPerRow ){
            System.out.println("This position is not allowed" );
            return false;
        }
        else{
            if( !onMove ){  //selecting the token to move KINGS not allowed
                if( allowedToken(row, col) ){
                    actualToken_movements = tokenMovementsAvailable(row, col);
                    if ( actualToken_movements.size() > 0 ){
                        token_pos.x = row;
                        token_pos.y = col;
                        onMove = true;
                        return true;
                    }
                    else{
                        System.out.println("Token has no moves.");
                        return false;
                    }
                }
                else{
                    System.out.println("Not an allowed token.");
                    return false;
                }
            }
            else {
                Position new_pos = new Position(row, col);
                if( actualToken_movements.stream().filter( x -> x.x == new_pos.x && x.y == new_pos.y ).count() == 1 ){//do move
                    makeMove( token_pos, new_pos );
                    return true;
                }
                else{
                    System.out.println( "This movement is not available.");
                    return false;
                }
            }
        }

    }

    public void makeMove( Position token_pos, Position  new_pos ){
        int token;

        if( player == RED && new_pos.x == 7 ){
            redTokensAvailable--;
            redKingTokens++;
            token = RED_KING;
        }
        else if ( player == WHITE && new_pos.x == 0 ){
            whiteTokensAvailable--;
            whiteKingTokens++;
            token = WHITE_KING;
        }
        else token = board[token_pos.x][token_pos.y];

        board[ token_pos.x ][ token_pos.y] = 0;
        board[new_pos.x][new_pos.y] = token;

        if( possibleJump ){
            if( Math.abs( (token_pos.x - new_pos.x) ) > 1 ){
                int x, y;
                if( token_pos.x > new_pos.x ) x = token_pos.x -1;
                else x = token_pos.x +1;
                if( token_pos.y > new_pos.y ) y = token_pos.y -1;
                else y = token_pos.y +1;
                removeToken( x, y);
            }
        }
        resetPlay();
    }

    public boolean gameOver( ){
        if ( whiteTokensAvailable == 0 || redTokensAvailable == 0 ) return true;
        if ( getPlayerMovemens( RED ).size() == 0 && getPlayerMovemens( WHITE ).size() == 0 )return true;
        return false;
    }

    public List<Position> getPlayerMovemens( int player ) {
        List<Position> playerMovements = new ArrayList<>();

        for ( Position token : getPlayerTokens(player) )
            for(Position pos : tokenMovementsAvailable(token.x, token.y) ) playerMovements.add(pos);

        return playerMovements;
    }

    public void resetPlay() {
        if( gameOver() ) return;
        onMove = false;
        possibleJump = false;
        actualToken_movements = new ArrayList<>();
        swapPlayer();
        if( getPlayerMovemens(player).size() == 0 ) resetPlay();
        //printBoard();
    }

    public void swapPlayer() {
        if ( player == RED) player = WHITE;
        else player= RED;
    }

    public void removeToken(int x, int y ){
        int token = board[x][y];
        if ( token == RED ){
            redTotalTokens --;
            redTokensAvailable--;
        }
        else{
            whiteTotalTokens --;
            whiteTokensAvailable --;
        }
        board[x][y] = 0;
    }

    public boolean allowedToken(int row, int col){
        if( board[row][col] == player ) return true;
        else return false;
    }

    public List<Position> tokenMovementsAvailable(int row, int col){
        if( board[row][col] == WHITE ) return getUpPositions( row, col );
        else return getDownPositions( row, col );
    }

    public List<Position> getUpPositions( int row, int col){
        List<Position> token_movements = new ArrayList<>();
        //simple movements
        if( row > 0 ){ //checking that the token is not at the end of the board
            if( col > 0  && board[row-1][col-1] == EMPTY ) token_movements.add( new Position( row-1, col-1 ) );
            if( col < numTilesPerRow -1 && board[row-1][col+1] == EMPTY  ) token_movements.add( new Position( row-1, col+1 ) );
        }
        //jumping movements
        if ( row > 1 ){
            if( col > 1 && jumpAvailable(row-1, col-1, row-2, col-2 ) ) token_movements.add( new Position( row-2, col-2 ) );
            if( col < numTilesPerRow -2 && jumpAvailable(row-1, col+1, row-2, col+2 ) ) token_movements.add( new Position( row-2, col+2 ) );
        }
        return token_movements;
    }

    public List<Position> getDownPositions( int row, int col ){
        List<Position> token_movements = new ArrayList<>();
        //simple movements
        if( row < numTilesPerRow -1 ){ //checking that the token is not at the end of the board
            if( col > 0  && board[row+1][col-1] == EMPTY ) token_movements.add( new Position( row+1, col-1 ) );
            if( col < numTilesPerRow -1 && board[row+1][col+1] == EMPTY  ) token_movements.add( new Position( row+1, col+1 ) );
        }
        //jumping movements
        if ( row < numTilesPerRow -2 ){
            if( col > 1 && jumpAvailable(row+1, col-1, row+2, col-2 ) ) token_movements.add( new Position( row+2, col-2 ) );
            if( col < numTilesPerRow -2 && jumpAvailable(row+1, col+1, row+2, col+2 ) ) token_movements.add( new Position( row+2, col+2 ) );
        }
        return token_movements;
    }

    public boolean jumpAvailable( int row, int col, int next_row, int next_col ){
        Boolean jump = ( board[row][col] != EMPTY && board[row][col] != player && board[next_row][next_col] == EMPTY );
        if( jump ) possibleJump = true;
        return jump;
    }

    public void printBoard()  {
        System.out.print("\033[H\033[2J");
        System.out.flush();
        for( int col = 0; col < numTilesPerRow; col++ ) System.out.print( col + "\t" );
        System.out.println("\n-----------------------------");
        for( int row = 0; row < numTilesPerRow; row++ ){
            for( int col = 0; col < numTilesPerRow; col++ ){
                System.out.print( board[row][col] + "\t" );
            }
            System.out.println( "| "+ row );
        }
        System.out.println("RED player:"+redTokensAvailable+"\tWHITE player:"+whiteTokensAvailable+"\nIt's players "+player+ " turn, please move.");
    }

    public int getActualPlayer() {
        return player;
    }

    public List<Position> getPlayerTokens( int player ){
        List<Position> playerTokens = new ArrayList<>();
        for( int row = 0; row < numTilesPerRow; row++ ) {
            for (int col = 0; col < numTilesPerRow; col++) {
                if (board[row][col] == player) playerTokens.add( new Position(row, col) );
            }
        }
        return playerTokens;
    }

    public int getWinner(){
        if( redTotalTokens > whiteTotalTokens ) return RED;
        else if( whiteTotalTokens > redTotalTokens ) return WHITE;
        else return -1;
    }

    @Override
    protected Game clone(){
        Game newGame = new Game();

        for( int row = 0; row < numTilesPerRow; row++ ){
            for( int col = 0; col < numTilesPerRow; col++){
                newGame.board[row][col] = board[row][col];
            }
        }

        newGame.redTokensAvailable = redTokensAvailable;
        newGame.whiteTokensAvailable = whiteTokensAvailable;

        newGame.redTotalTokens = redTotalTokens ;
        newGame.whiteTotalTokens = whiteTotalTokens ;

        newGame.onMove = onMove;
        newGame.token_pos = token_pos;
        newGame.player = player;

        newGame.possibleJump = possibleJump;

        return newGame;
    }

}
