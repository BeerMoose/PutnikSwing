public class FieldCreatorFor extends FieldCreator
{

    @Override
    public Field create(int number){
        init();
        switch (number){
            case 1:{
                createProblem1();
                break;
            }
        }

        return new Field(super.n, super.m, super.cells, x, y, direction);
    }
    private void createProblem1(){
        x = super.n / 2;
        y = super.m / 2;
        direction = 1;
        for (int i = 1; i < super.x; i++){
            for (int j = 1; j < super.m - 1; j++){
                super.cells[i][j].setIsPaint(true);
            }
        }
        super.cells[1][1].setIsMark(true);
    }




}
