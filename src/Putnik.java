import java.io.IOException;
import java.io.PrintWriter;

public class Putnik {
    private Field field;
    private int x, y,direction;
    Putnik(Field field){
        this.field = field;
        this.x = field.x;
        this.y = field.y;
        this.direction = field.direction;
    }
    private String getIconPath(){
        return "img/" + direction + ".png";
    }
    public void сделатьШаг(){
        switch (direction){
            case 1:
                if(tryMove(x - 1, y)) {
                    makeMove(x - 1, y);
                }
                else{
                    getErrorMessage();
                }
                break;
            case 2:
                if(tryMove(x, y + 1)) {
                    makeMove(x, y + 1);
                }
                else{
                    getErrorMessage();
                }
                break;
            case 3:
                if(tryMove(x + 1, y)) {
                    makeMove(x + 1, y);
                }
                else{
                    getErrorMessage();
                }
                break;
            case 4:
                if(tryMove(x, y - 1)) {
                    makeMove(x, y - 1);
                }
                else{
                    getErrorMessage();
                }
                break;
        }
    }
    public void шагатьДоУпора(){
        while (впередиСвободно())
            сделатьШаг();
    }
    private boolean tryMove(int i, int j){
        return !field.cells[i][j].isBlock;
    }
    private void makeMove(int i, int j){
        this.x = i;
        this.y = j;
    }
    private void getErrorMessage(){
        System.out.println("Мы в дерьме");
    }
    public void закрасить(){
        field.cells[x][y].setIsPaint(true);
    }
    public void повернутьсяНаправо(){
        direction = (direction == 4)? 1 : direction + 1;
    }
    public void повернутьсяНалево(){
        direction = (direction == 1)? 4 : direction - 1;
    }
    public void повернутьсяНаСевер(){
        direction = 1;
    }
    public void повернутьсяНаВосток(){
        direction = 2;
    }
    public void повернутьсяНаЮг(){
        direction = 3;
    }
    public void повернутьсяНаЗапад(){
        direction = 4;
    }
    boolean впередиПрепятствие(){
        switch (direction){
            case 1: return !tryMove(x - 1, y);
            case 2: return !tryMove(x, y + 1);
            case 3: return !tryMove(x + 1, y);
            case 4: return !tryMove(x, y - 1);
            default:return true;
        }
    }
    boolean справаПрепятствие(){
        switch (direction){
            case 4: return tryMove(x - 1, y);
            case 1: return tryMove(x, y + 1);
            case 2: return tryMove(x + 1, y);
            case 3: return tryMove(x, y - 1);
            default:return true;
        }
    }
    boolean слеваПрепятствие(){
        switch (direction){
            case 2: return tryMove(x - 1, y);
            case 3: return tryMove(x, y + 1);
            case 4: return tryMove(x + 1, y);
            case 1: return tryMove(x, y - 1);
            default:return true;
        }
    }
    boolean впередиСвободно(){
        return !впередиПрепятствие();
    }
    boolean справаСвободно(){
        return !справаПрепятствие();
    }
    boolean слеваСвободно(){
        return !слеваПрепятствие();
    }
    boolean клеткаЗакрашена(){
        return field.cells[x][y].isPaint;
    }
    boolean клеткаНеЗакрашена(){
        return !клеткаЗакрашена();
    }
    boolean клеткаПомечена(){
        return field.cells[x][y].isMark;
    }
    boolean клеткаНеПомечена(){
        return !клеткаПомечена();
    }
    protected void toFile(String fileName){
        //field.printToFile(fileName);
        try(PrintWriter pw = new PrintWriter(fileName)){
            pw.print(field.n + " " + field.m + " \n");
            for (int i = 0; i < field.n; i++){
                for (int j = 0; j < field.m; j++){
                    pw.print(field.cells[i][j] + " ");
                }
                pw.println();
            }
            pw.print(x + " " + y +  " " + direction);
        }catch (IOException e){

        }
    }

}
