import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;


public class BeginFormView extends JFrame {
    private int width = 600, height = 500;
    private int nowIndex = 0; //Текущий
    private boolean showButtonOfTheGodFlag; //Отображать ли божественную кнопку
    private String[] listOfTasks;
    private String[][] listsOfTasks;                        //списки задач вытянутые из папки tasks
    private JPanel left = new JPanel(), right = new JPanel();
    private JList list;                 //выпадающий список типов задач (while, for, if)
    private JLabel descriptionOfTask = new JLabel();
    private JLabel logo = new JLabel(new ImageIcon("resources/logo.png"));
    private JButton buttonOfTheGod = new JButton("Перейти к задаче");
    private JComboBox operators; //Выпадающий список операторов
    private JScrollPane listScrollPane = new JScrollPane();         //панель с скроллбарами для списка задач

    public BeginFormView() {
        super("Путник");
        setLayout(null);
        setSize(width, height);

        createAllListsOfTasks();
        createListOfTasks(0);
        addListToForm();
        createOperatorsComboBox();

        left.setBounds(40, 70, 100, 333);
        left.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Задачи"));
        add(left);

        logo.setBounds(250, 20, 200, 50);
        add(logo);

        right.setBounds(150, 70, 400, 200);
        right.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Описание"));
        right.add(descriptionOfTask);
        add(right);

        buttonOfTheGod.setBounds(250, 300, 160, 60);
        buttonOfTheGod.setVisible(false);
        buttonOfTheGod.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String title = operators.getSelectedItem() + " " + nowIndex;
                new FieldView(title, new FieldCreatorFor().create(nowIndex + 1));

            }
        });
        add(buttonOfTheGod);

        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // EDIT
        setVisible(true);
    }

    private void createAllListsOfTasks() {
        String problemTypes[] = new File("tasks").list();
        listsOfTasks = new String[problemTypes.length][];
        for (int i = 0; i < problemTypes.length; ++i) {
            listsOfTasks[i] = new File("tasks/" + problemTypes[i]).list();
        }
    }

    private void createListOfTasks(int index) {
        listOfTasks = listsOfTasks[index];
    }

    private void addListToForm() {
        list = new JList(listOfTasks);
        listScrollPane.setViewportView(list);
        left.setLayout(new BorderLayout());

        ListSelectionListener listSelectionListener = new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                nowIndex = list.getSelectedIndex();
                showDescriptionOfTask((String) operators.getSelectedItem(), list.getSelectedIndex());
                buttonOfTheGod.setVisible(showButtonOfTheGodFlag);
            }
        };
        list.addListSelectionListener(listSelectionListener);
        left.add(listScrollPane);
    }

    private void showDescriptionOfTask(String operator, int k) {
        File f = new File("tasks/" + operator + "/" + k + ".txt");
        StringBuilder contentOfFile = new StringBuilder("<html>");
        showButtonOfTheGodFlag = true;
        try (BufferedReader reader = new BufferedReader(new FileReader(f))) {//try с ресурсами, так удобней и не нужно отслеживать закрытие файла
            String s;
            while ((s = reader.readLine()) != null) {     //Считывать файл построчно
                contentOfFile.append(s + "<br>");
            }
        } catch (IOException e) {
            contentOfFile.append("Задачи ещё нет =(");  //Если нельзя этого сделать, то увы
            showButtonOfTheGodFlag = false;
        }
        descriptionOfTask.setText(contentOfFile.toString());
    }

    private void createOperatorsComboBox() {
        String[] s = new File("tasks").list();
        operators = new JComboBox(s);
        operators.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createListOfTasks(operators.getSelectedIndex());
                list.setListData(listOfTasks);
            }
        });
        operators.setBounds(40, 30, 100, 30);
        add(operators);
    }

}
