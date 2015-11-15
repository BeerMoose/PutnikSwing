import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Test {
    boolean isAlive = true;
    Test(){
        try {
            new ProcessBuilder("cmd", "/c", "start", "D:\\Projects\\PutnikSwing\\src\\start.bat").start();
            while (!checkFile());
            PrintWriter pw = new PrintWriter("flag.txt");
            pw.print("..!.");
            pw.close();
            isAlive = false;
        } catch (IOException e) {
        }
    }
    boolean checkFile(){
        try(Scanner sc = new Scanner(new File("flag.txt"))) {
            int k = sc.nextInt();
            return (k == 15);
        }catch (Exception e){
            return false;
        }
    }
}
