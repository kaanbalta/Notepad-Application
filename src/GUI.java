import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import javax.swing.text.Caret;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.*;
import java.util.Scanner;

public class GUI extends JFrame {


    private JPanel panel;
    private JTextArea textArea1;
    private File file;

    public void Menu(){
        JMenuBar jMenuBar = new JMenuBar();
        JMenu FileMenu = new JMenu("File");
        JMenu EditMenu = new JMenu("Edit");


        jMenuBar.add(FileMenu);
        jMenuBar.add(EditMenu);

        JMenuItem newItem = new JMenuItem("New");
        newItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, KeyEvent.META_DOWN_MASK));

        JMenuItem openItem = new JMenuItem("Open");
        openItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, KeyEvent.META_DOWN_MASK));

        JMenuItem saveItem = new JMenuItem("Save");
        saveItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.META_DOWN_MASK));

        JMenuItem saveasItem = new JMenuItem("SaveAs");
        saveasItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.META_DOWN_MASK + KeyEvent.SHIFT_DOWN_MASK));

        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, KeyEvent.META_DOWN_MASK));


        JMenu colorMenu = new JMenu("Color");
        JMenu fontMenu = new JMenu("Font");
        JMenu textsizeMenu = new JMenu("Text size");

        JMenuItem changecolorItem = new JMenuItem("Change Color");
        JMenuItem ArialItem = new JMenuItem("Arial");
        JMenuItem TımesNewRomanItem = new JMenuItem("Times New Roman");
        JMenuItem PlusItem = new JMenuItem("+");
        JMenuItem MinusItem = new JMenuItem("-");



        FileMenu.add(newItem);
        FileMenu.add(openItem);
        FileMenu.add(saveItem);
        FileMenu.add(saveasItem);
        FileMenu.add(exitItem);

        EditMenu.add(colorMenu);
        EditMenu.add(fontMenu);
        EditMenu.add(textsizeMenu);

        colorMenu.add(changecolorItem);
        fontMenu.add(ArialItem);
        fontMenu.add(TımesNewRomanItem);
        textsizeMenu.add(PlusItem);
        textsizeMenu.add(MinusItem);

        setJMenuBar(jMenuBar);


        openItem.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                JFileChooser fileChooser = new JFileChooser();
                int i = fileChooser.showOpenDialog(GUI.this);

                if(i == JFileChooser.APPROVE_OPTION){
                    file = fileChooser.getSelectedFile();

                    try(Scanner scanner = new Scanner(new BufferedReader(new FileReader(file)))){

                        String content = "";
                        while(scanner.hasNextLine()){
                            content += scanner.nextLine() + "\n";
                        }

                        textArea1.setText(content);


                    } catch (FileNotFoundException ex) {
                        throw new RuntimeException(ex);
                    }

                }

            }
        });

        saveItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(file != null){

                    try(FileWriter fileWriter = new FileWriter(file)){

                        fileWriter.write(textArea1.getText());
                        JOptionPane.showMessageDialog(GUI.this, "File Saved");
                        textArea1.setText("");

                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }

                }
                else {
                    JOptionPane.showMessageDialog(GUI.this, "File Not Found");
                }


            }
        });

        saveasItem.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();

                int answer = fileChooser.showSaveDialog(GUI.this);

                if(answer == JFileChooser.APPROVE_OPTION){
                    String Filepath = fileChooser.getSelectedFile().getPath();

                    try(FileWriter fileWriter = new FileWriter(Filepath)){

                        fileWriter.write(textArea1.getText());

                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }

                    textArea1.setText("");

                }

            }
        });

        exitItem.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });


        newItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                    int option = JOptionPane.showConfirmDialog(GUI.this,"Do you want to save changes ?"
                            ,"Save File",JOptionPane.YES_NO_OPTION);

                    if(option == JOptionPane.YES_OPTION){

                        JFileChooser fileChooser = new JFileChooser();

                        int answer = fileChooser.showSaveDialog(GUI.this);

                        if(answer == JFileChooser.APPROVE_OPTION){
                            String Filepath = fileChooser.getSelectedFile().getPath();

                            try(FileWriter fileWriter = new FileWriter(Filepath)){

                                fileWriter.write(textArea1.getText());

                            } catch (IOException ex) {
                                throw new RuntimeException(ex);
                            }

                            textArea1.setText("");
                            file = null;

                        }

                    }
                    else if(option == JOptionPane.NO_OPTION){
                        textArea1.setText("");
                        file = null;
                    }






            }
        });


        changecolorItem.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Color color = JColorChooser.showDialog(GUI.this,"Bir renk seçin",Color.RED);
                textArea1.setForeground(color);
            }
        });

        ArialItem.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                Font font = new Font("Arial", Font.PLAIN, textArea1.getFont().getSize());
                textArea1.setFont(font);

            }
        });

        TımesNewRomanItem.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                Font font = new Font("Times New Roman", Font.PLAIN, textArea1.getFont().getSize());
                textArea1.setFont(font);
            }
        });

        PlusItem.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                Font font = textArea1.getFont();
                textArea1.setFont(new Font(font.getName(),font.getStyle(), font.getSize() + 2));
            }
        });

        MinusItem.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                Font font = textArea1.getFont();
                textArea1.setFont(new Font(font.getName(),font.getStyle(), font.getSize() - 2));
            }
        });



    }




    GUI(){
        add(panel);
        setSize(800, 600);
        setLocationRelativeTo(null);
        panel.setBackground(textArea1.getBackground());
        textArea1.setCaretColor(Color.BLUE);


        textArea1.setLineWrap(true); // Satır kaydırmayı etkinleştir
        textArea1.setWrapStyleWord(true); // Kelimeleri bölerken tam kelimeyi kaydır

        // JTextArea'yı JScrollPane içinde ekle (kaydırma çubukları için)
        JScrollPane scrollPane = new JScrollPane(textArea1);
        add(scrollPane);


        Menu();










    }


}
