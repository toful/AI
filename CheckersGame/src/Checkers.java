import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Checkers extends JPanel implements ActionListener, MouseListener {
    private static final long serialVersionUID = 1L;
    public static int width = 720, height = width; //square parameters. Optimized for any square resolution
    public static final int tileSize = width/8; //8 Tiles for checkers board
    public static JFrame frame;
    static BufferedImage crownImage = null;
    private int numTilesPerRow;

    private int row, col;
    private Game board;
    private AIPlayer hal, bender;

    private int playMode;

    long time_start, time_end;
    long total_time = 0;
    long total_turns = 0;

    public static void main(String[] args) {
        int mode = 1;       //0-player vs player, 1-player vs IA, 2-IA vs IA
        int algorithm = 1;  //0-minimax 1-alphabeta
        int heuristic = 2;  // heuristic for the AI 0,1,2

        new Checkers( mode, algorithm, heuristic );
    }


    public Checkers( int mode, int algorithm, int heuristic ){
        try {
            crownImage = ImageIO.read(new File("crown.png"));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        playMode = mode;
        board = new Game();
        hal = new AIPlayer( board.RED, algorithm, heuristic );
        bender = new AIPlayer( board.WHITE, 1, 2 );
        setGameWindow( width, height, this );
        numTilesPerRow = board.numTilesPerRow;

        do{
            //Playing until the end
        } while ( !board.gameOver() );
    }

    public void setGameWindow(int width, int height, Checkers game){
        frame = new JFrame();
        frame.setSize(width, height);
        frame.setIconImage(crownImage);
        frame.setBackground(Color.cyan);
        frame.setLocationRelativeTo(null);
        frame.pack();
        Insets insets = frame.getInsets();
        int frameLeftBorder = insets.left;
        int frameRightBorder = insets.right;
        int frameTopBorder = insets.top;
        int frameBottomBorder = insets.bottom;
        frame.setPreferredSize(new Dimension(width + frameLeftBorder + frameRightBorder, height + frameBottomBorder + frameTopBorder));
        frame.setMaximumSize(new Dimension(width + frameLeftBorder + frameRightBorder, height + frameBottomBorder + frameTopBorder));
        frame.setMinimumSize(new Dimension(width + frameLeftBorder + frameRightBorder, height + frameBottomBorder + frameTopBorder));
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.addMouseListener(this);
        frame.requestFocus();
        frame.setVisible(true);
        frame.add( game );
    }

    public static void drawPiece(int col, int row, java.awt.Graphics g, Color color){
        ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g.setColor(color);
        // These 2 and 4 values are arbitrary values that compensate for a smaller piece size than tileSize
        g.fillOval((col*tileSize)+2, (row*tileSize)+2, tileSize-4, tileSize-4);
    }

    public void paint(Graphics g){
        // This method paints the board
        //PRINT THE BOARD & PIECES
        super.paintComponent(g);
        for(int row = 0; row < numTilesPerRow; row++) {
            for (int col = 0; col < numTilesPerRow; col++) {
                if ((row % 2 == 0 && col % 2 == 0) || (row % 2 != 0 && col % 2 != 0)) g.setColor(Color.gray);
                else g.setColor(Color.darkGray);
                g.fillRect(col * tileSize, row * tileSize, tileSize, tileSize);
            }
        }
        for( Position pos : board.getPlayerTokens( board.getActualPlayer() ) ){
            g.setColor(Color.darkGray.darker());
            g.fillRect(pos.y*tileSize, pos.x*tileSize, tileSize, tileSize);
        }

        for( Position pos : board.actualToken_movements ){
            g.setColor(Color.CYAN.darker());
            g.fillRect(pos.y*tileSize, pos.x*tileSize, tileSize, tileSize);
        }


        for(int row = 0; row < numTilesPerRow; row++) {
            for (int col = 0; col < numTilesPerRow; col++) {
                if(board.board[row][col] == board.WHITE)
                    drawPiece(col, row, g, Color.white);
                else if( board.board[row][col] == board.WHITE_KING){
                    drawPiece(col, row, g, Color.white);
                    g.drawImage(crownImage, (col*tileSize)+6, (row*tileSize)+6, tileSize-12, tileSize-12, null);
                }
                else if( board.board[row][col] == board.RED)
                    drawPiece(col, row, g, Color.red);
                else if( board.board[row][col] == board.RED_KING){
                    drawPiece(col, row, g, Color.red);
                    g.drawImage(crownImage, (col*tileSize)+6, (row*tileSize)+6, tileSize-12, tileSize-12, null);
                }
            }
        }
        if( board.gameOver() == true) gameOverDisplay(g);
    }

    public void gameOverDisplay(Graphics g) { //Displays the game over message
        String result;
        if( board.getWinner() == board.WHITE ) result = "The winner is player WHITE";
        else if( board.getWinner() == board.RED ) result = "The winner is player RED";
        else result = "DRAW";
        String msg = "Game Over: "+result;
        Font small = new Font("Helvetica", Font.BOLD, 20);
        FontMetrics metr = getFontMetrics(small);
        g.setColor(Color.white);
        g.setFont(small);
        g.drawString(msg, (width - metr.stringWidth(msg)) / 2, width / 2);
        //System.out.println("Average: "+(float) total_time/total_turns );
        System.out.println("winner: "+board.getWinner() );
        //board = new Game();
        //iaVsIA( hal, bender );
    }

    public void mousePressed(MouseEvent evt) {
        if( board.gameOver() ) board = new Game();
        col = (evt.getX()-8) / tileSize; // 8 is left frame length
        row = (evt.getY()-30) / tileSize; // 30 is top frame length

        try {
            movement(row, col);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        repaint();
    }

    public void movement( int row, int col ) throws InterruptedException {
        switch ( playMode ){
            case 0:
                playerVsPlayer( row, col );
                break;
            case 1:
                playerVsIA( row, col, hal );
                break;
            case 2:
                iaVsIA( hal, bender );
                break;
        }
    }

    public void playerVsPlayer( int row, int col ){
        board.move( row, col );
        if( board.getPlayerMovemens( board.getActualPlayer()).isEmpty() ) board.resetPlay();
    }

    public void playerVsIA( int row, int col, AIPlayer ia ){
        Movement movement;
        if( board.onMove ){
            if ( board.move(row, col) ) {
                if( board.getPlayerMovemens( board.getActualPlayer()).isEmpty() ) board.resetPlay();
                if( !board.gameOver() && ia.getIAPlayer() == board.getActualPlayer() ){
                    repaint();
                    time_start = System.currentTimeMillis();
                    movement = ia.decideMove( board );
                    time_end = System.currentTimeMillis();
                    total_time += ( time_end - time_start );
                    total_turns++;
                    board.makeMove( movement.currentPosition, movement.nextPosition );
                }
            }
        }//case in which we have to select the token
        else{
            if( board.getPlayerMovemens( board.getActualPlayer()).isEmpty() ) board.resetPlay();
            if( ia.getIAPlayer() != board.getActualPlayer() ) board.move(row, col);
            else{
                time_start = System.currentTimeMillis();
                movement = ia.decideMove( board );
                time_end = System.currentTimeMillis();
                total_time += ( time_end - time_start );
                total_turns++;
                board.makeMove( movement.currentPosition, movement.nextPosition );
            }
        }
    }

    public void iaVsIA( AIPlayer ia1, AIPlayer ia2 ) {
        Movement movement;
        update( frame.getGraphics() );
        repaint();
        if( board.getPlayerMovemens( board.getActualPlayer()).isEmpty() ) board.resetPlay();
        if( !board.gameOver() && ia1.getIAPlayer() == board.getActualPlayer() ){
            movement = ia1.decideMove( board );
            board.makeMove( movement.currentPosition, movement.nextPosition );
            iaVsIA( ia1, ia2 );
        }
        else if ( !board.gameOver() && ia2.getIAPlayer() == board.getActualPlayer() ){
            movement = ia2.decideMove( board );
            board.makeMove( movement.currentPosition, movement.nextPosition );
            iaVsIA( ia1, ia2 );
        }
    }

    public void mouseClicked(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    public void actionPerformed(ActionEvent e) {}

}