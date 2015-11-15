public class CompilerCode {
public static void main(String[] args) {
Putnik user = new MiniPutnik("originalField.txt");
user.сделатьШаг();user.закрасить();user.toFile("user.txt");
}
}