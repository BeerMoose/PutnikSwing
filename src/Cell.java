public class Cell{
    final char CHAR_MARK = '1';
    final char CHAR_PAINT = '1';
    final char CHAR_BLOCK = '1';
    final char CHAR_SYMBOL = '^';
    boolean isMark = false;
    boolean isPaint = false;
    boolean isBlock = false;
    boolean isHaveSymbol = false;
    char symbol = '^';
    Cell(){}
    Cell(boolean aMark, boolean aPaint, boolean aBlock, char symbol){
        this.isMark  = aMark;
        this.isPaint = aPaint;
        this.isBlock = aBlock;
        isHaveSymbol = (symbol != CHAR_SYMBOL);
        this.symbol = symbol;
    }
    Cell(char mark, char paint, char block, char symbol){
       // this();
        init(mark, paint, block, symbol);
    }
    Cell(String s){
        this(s.charAt(0), s.charAt(1), s.charAt(2), s.charAt(3));
    }
    public void set(String s){
        init(s.charAt(0), s.charAt(1), s.charAt(2), s.charAt(3));
    }
    private void init(char mark, char paint, char block, char symbol){
        this.isMark       = (mark   == this.CHAR_MARK);
        this.isPaint      = (paint  == this.CHAR_PAINT);
        this.isBlock      = (block  == this.CHAR_BLOCK);
        this.isHaveSymbol = (symbol != this.CHAR_SYMBOL);
        this.symbol = symbol;
    }

    public void setIsMark(boolean isMark)
    {
        this.isMark = isMark;
    }
    public void setIsPaint(boolean isPaint)
    {
        this.isPaint = isPaint;
    }
    public void setIsBlock(boolean isBlock)
    {
        this.isBlock = isBlock;
    }
    public void setIsHaveSymbol(boolean isHaveSymbol)
    {
        this.isHaveSymbol = isHaveSymbol;
    }
    public void setSymbol(char symbol)
    {
        this.symbol = symbol;
    }
    public String toString(){
        String one = (isMark)?"1":"0";
        String two = (isPaint)?"1":"0";
        String three = (isBlock)?"1":"0";
        String four = symbol +"";
        return one + two + three + four;
    }
    public String toFileString(){
        return ((isMark)?"1":"0")+ "0" + ((isBlock)?"1":"0") + symbol;
    }
}
