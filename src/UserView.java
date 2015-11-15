import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserView extends JFrame{
    JLabel[][] leftCells;
    JLabel[][] rightCells;
    int sizeOfCell = 30;
    int width, height;
    int n, m;
    JTextArea userCode;
    JButton compileButton, resetButton;
    Field userField;
    UserView(JLabel[][] leftCells) {
        n = leftCells.length;
        m = leftCells[0].length;
        width =  2 * m * sizeOfCell + 500;
        height = n * sizeOfCell;
        //-------Создание формы
        setLayout(null);
        setSize(width + 6, height + 29); //6 и 29 - это пиксели, которые винда забирает себе
        //--------------------

        createLeftField(leftCells);
        createRightField("originalField.txt");
        changeColorRightField("originalField.txt");
        createMiddle();

        //-------Создание формы
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
        //---------------------
    }
    void createLeftField(JLabel[][] leftCells){
        this.leftCells = leftCells;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                add(leftCells[i][j]);
            }
        }
    }
    void changeColorRightField(String fileName){
        userField = new Field(fileName);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                Color color = Color.GREEN;
                if(userField.cells[i][j].isBlock) {
                    color = Color.GRAY;
                }else {
                    if (userField.cells[i][j].isPaint) {
                        color = new Color(155, 209, 154);
                    }
                }
                if(userField.cells[i][j].isMark){
                    rightCells[i][j].setIcon(new ImageIcon("resources/allo.png"));
                }else{
                    rightCells[i][j].setIcon(null);
                }
                rightCells[i][j].setBackground(color);

            }
        }
        rightCells[userField.x][userField.y].setIcon(new ImageIcon("resources/" + userField.direction + ".png"));
    }
    void createRightField(String fileName){
        userField = new Field(fileName);
        int n = userField.n;
        int m = userField.m;
        rightCells = new JLabel[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                rightCells[i][j] = new JLabel(" ");
                rightCells[i][j].setBounds(width - m * sizeOfCell + sizeOfCell * j, sizeOfCell * i, sizeOfCell, sizeOfCell);
                rightCells[i][j].setOpaque(true);        //Без этой хни не отображает цвет
                rightCells[i][j].setBorder(BorderFactory.createLineBorder(Color.BLACK));
                add(rightCells[i][j]);
            }
        }

    }
    void createMiddle(){
        createMiddleArea();
        createMiddleButton();
    }
    void createMiddleArea(){
        userCode = new JTextArea("сделатьШаг();закрасить();");
        userCode.setBounds(m * sizeOfCell + 20, 0, 460, height - 30);
        add(userCode);
    }
    void createMiddleButton(){
        compileButton = new JButton("Компилировать");
        compileButton.setBounds(m * sizeOfCell + 20, height - 30, 230, 30);
        compileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UserCode hate = new UserCode(userCode, "user.txt");
                while (hate.isAlive);
                new CompilerCode();

                changeColorRightField("user.txt");
            }
        });
        add(compileButton);



        resetButton = new JButton("Очистить");
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeColorRightField("originalField.txt");
            }
        });
        resetButton.setBounds(m * sizeOfCell + 250, height - 30, 230, 30);
        add(resetButton);
    }
}
