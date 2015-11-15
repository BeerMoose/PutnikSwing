import java.io.FileReader;
import java.io.IOError;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Field{
    int n,m;
    int x,y, direction;
    Cell[][] cells;
    Field(){

    }
    Field(int n, int m, Cell[][] cells, int x, int y, int direction){
        this.n = n;
        this.m = m;
        this.cells = cells.clone();
        this.x = x;
        this.y = y;
        this.direction = direction;
    }
    Field(String fileName){
        try{
            Scanner scanner = new Scanner(new FileReader(fileName));
            this.n = scanner.nextInt();
            this.m = scanner.nextInt();
            cells = new Cell[n][m];
            for (int i = 0; i < n; i++){
                for (int j = 0; j < m; j++){
                    String cell = scanner.next();
                    //System.out.print(cell + " ");
                    cells[i][j] = new Cell(cell);
                }
                //System.out.println();
            }
            x = scanner.nextInt();
            y = scanner.nextInt();
            direction = scanner.nextInt();
            scanner.close();
        }catch (IOException e){
            e.printStackTrace();
            System.out.println("Error with file");
        }
    }
    void printToFile(String fileName){
        try(PrintWriter pw = new PrintWriter(fileName)){
            pw.print(n + " " + m + " \n");
            for (int i = 0; i < n; i++){
                for (int j = 0; j < m; j++){
                    pw.print(cells[i][j].toFileString() + " ");
                }
                pw.println();
            }
            pw.print(x + " " + y +  " " + direction);
        }catch (IOException e){

        }
    }

}
