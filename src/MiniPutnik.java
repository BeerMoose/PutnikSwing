public class MiniPutnik extends Putnik{
    MiniPutnik(String fileName){
        super(new Field(fileName));
    }
    protected void toFile(String fileName){
       super.toFile(fileName);
    }
}
