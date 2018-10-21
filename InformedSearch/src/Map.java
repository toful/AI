import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Map {
    private int[][] map;
    private int rows, columns;

    public Map( String file ){
        loadMapFromFile( file, this);
    }

    public int[][] getMap() {
        return map;
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public void printMap(){
        int i, j;
        for( i = 0; i < rows; i++ ){
            for( j = 0; j < columns; j++ ){
                System.out.print( map[i][j] + "\t" );
            }
            System.out.println("");
        }
    }

    public static void loadMapFromFile(String file, Map map){
        try {
            int row = 0, column = 0;
            ArrayList<ArrayList <Integer> > tmp_map = new ArrayList();
            FileReader f = new FileReader(file);
            BufferedReader dis = new BufferedReader(f);
            StringTokenizer tokens;
            String line;

            try {
                line = dis.readLine();
                tokens = new StringTokenizer(line, " ,");
                tmp_map.add( new ArrayList<Integer>() );
                while (tokens.hasMoreTokens()) {
                    tmp_map.get( row ).add( Integer.parseInt(tokens.nextToken()) );
                    column ++;
                }
                row ++;
                while ((line = dis.readLine()) != null) {
                    tokens = new StringTokenizer(line, " ,");
                    tmp_map.add( new ArrayList<Integer>() );
                    while (tokens.hasMoreTokens()) {
                        tmp_map .get( row ).add( Integer.parseInt(tokens.nextToken()) );
                    }
                    row ++;
                }
                f.close();
                map.rows = row;
                map.columns = column;
                map.map = new int[row][column];
                row = 0;
                for(ArrayList<Integer> row_list : tmp_map ){
                    column = 0;
                    for( Integer value : row_list ){
                        map.map[row][column] = value;
                        column ++;
                    }
                    row ++;
                }

            } catch (IOException e) {
                System.out.println("ERROR: error while reading the file.");
            }
            f.close();
        } catch (IOException e) {
            System.out.println("ERROR: the file couldn't be opened.");
        }
    }

}
