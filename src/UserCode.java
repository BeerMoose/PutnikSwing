import javax.swing.*;
import java.io.IOException;
import java.io.PrintWriter;

public class UserCode {
    boolean isAlive = true;
    JTextArea userCode;
    String fileName;
    UserCode(JTextArea userCode, String fileName){
        this.userCode = userCode;
        this.fileName = fileName;
        start();
    }
    void start(){
        reWriteFile(changeUserCode());
        Test t = new Test();
        while (t.isAlive);
        isAlive = false;
    }
    StringBuilder changeUserCode(){
        int index = 1;
        StringBuilder changedCode = new StringBuilder(userCode.getText());
        while (index < changedCode.length()){
            char c = changedCode.charAt(index);
            if(isRussianLetter(c)){
                char c1 = changedCode.charAt(index - 1);
                if(!isRussianLetter(c1) || index == 1){
                    if(index == 1) index = 0;
                    changedCode.insert(index,"user.");
                    index += 6;
                }
            }
            index++;
        }
        return changedCode;
    }
    boolean isRussianLetter(char c){
        return ((c >= 'а' && c <= 'я') || (c >= 'А' && c <= 'Я'));
    }
    void reWriteFile(StringBuilder s){
        try(PrintWriter pw = new PrintWriter("src/CompilerCode.java")){
            pw.print("public class CompilerCode {\n");
            pw.print("public static void main(String[] args) {\n");
            pw.print("Putnik user = new MiniPutnik(\"originalField.txt\");\n");
            pw.print(s.toString());
            pw.print("user.toFile(\"user.txt\");");
            pw.print("\n}\n}");
        }catch (IOException e){

        }
    }
}
