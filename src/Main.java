import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class Main{
    public static void main(String[] args) {
        createBatFile();
        new BeginFormView();
    }
    static void createBatFile(){
        String myJarPath = Main.class.getProtectionDomain().getCodeSource().getLocation().getPath();
        String dirPath = new File(myJarPath).getParent();
        try(PrintWriter pw = new PrintWriter("src/start.bat")){
            pw.println("echo 16>" + dirPath + "\\flag.txt");
            pw.println("javac -sourcepath ./src -encoding UTF8 " + dirPath + "\\src\\CompilerCode.java");
            pw.println("java -classpath ./src CompilerCode");
            pw.println("echo 15>" + dirPath + "\\flag.txt");
            pw.println("pause");
            pw.println("exit ");
        }catch(IOException e){

        }
    }
}
