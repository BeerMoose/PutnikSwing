/**
 * Created by ntrln on 07.11.15.
 */
public abstract class FieldCreator {
    int n, m, x, y, direction;
    Cell [][]cells;
    public  abstract  Field create(int number);
    protected void init(){
        n = (int) (Math.random() * 10) + 7; m = (int) (Math.random() * 10) + 5;
        cells= new Cell[n][m];
        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < m; j++)
            {
                cells[i][j] = new Cell();
            }
        }
        setPerimeter();
    }
    private void setPerimeter(){
        String setter = "001^";
        for (int i = 0; i < n; i++){
            cells[i][0].set(setter);
            cells[i][m - 1].set(setter);
        }
        for (int i = 0; i < m; i++)
        {
            cells[0][i].set(setter);
            cells[n - 1][i].set(setter);
        }
    }
}
