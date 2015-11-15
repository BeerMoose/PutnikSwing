import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FieldView extends JFrame {
    private JLabel[][] jLabelCells;       //Массив клеток
    private int sizeOfCell = 30;    //Размер клетки / ячейки
    JButton solveTask = new JButton("Решить задачу");

    FieldView(String title, final Field field) {
        super(title);
        int height = field.n * sizeOfCell;
        int width =	 field.m * sizeOfCell;

        //-------Создание формы
        setLayout(null);
        setSize(width + 6, height + 29 + 30); //6 и 29 - это пиксели, которые винда забирает себе
        //--------------------

        createField(field);
        for (int i = 0; i < field.n; i++) {
            for (int j = 0; j < field.m; j++) {
                add(jLabelCells[i][j]);
            }
        }


        solveTask.setBounds(0, height, width, 30);
        solveTask.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                field.printToFile("originalField.txt");
                new UserView(jLabelCells);
                dispose();
            }
        });
        add(solveTask);

        //-------Создание формы
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
        //---------------------
    }

    private void createField(Field field) {
        int n = field.n;
        int m = field.m;
        jLabelCells = new JLabel[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                jLabelCells[i][j] = new JLabel(" ");
                jLabelCells[i][j].setBounds(sizeOfCell * j, sizeOfCell * i, sizeOfCell, sizeOfCell);
                jLabelCells[i][j].setOpaque(true);        //Без этой хни не отображает цвет
                Color color = Color.GREEN;
                if(field.cells[i][j].isBlock) {
                    color = Color.GRAY;
                }else {
                    if (field.cells[i][j].isPaint) {
                        color = new Color(155, 209, 154);
                    }
                }
                if(field.cells[i][j].isMark){
                    jLabelCells[i][j].setIcon(new ImageIcon("resources/allo.png"));
                }
                jLabelCells[i][j].setBackground(color);
                jLabelCells[i][j].setBorder(BorderFactory.createLineBorder(Color.BLACK));
            }
        }
        jLabelCells[field.x][field.y].setIcon(new ImageIcon("resources/" + field.direction + ".png"));
    }

}