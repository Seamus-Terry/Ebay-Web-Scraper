package Scraper;


import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Window implements ActionListener {

    //Variables
    public JFrame window;
    public JButton start, reset, help;
    public JTextArea urls;
    public JScrollPane scrollUrl;
    public JLabel inputTxt, sucFail, status;
    public FileOutputStream fos;
    public PrintWriter pw;
    public static JFileChooser fc;
    public String url;

    Scrape scrape = new Scrape();
    HelpWindow helpWin = new HelpWindow();

    public Window() {

        //Window Initializer
        window = new JFrame();
        window.setTitle("ebay Scraper - Copyright © 2023 by Seamus P. Terry");
        window.setLayout(null);
        window.setSize(600, 265);
        window.setResizable(false);
        window.setLocationRelativeTo(null);
        window.getContentPane().setBackground(new Color(0x003f5b));
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Start Button Initializer
        start = new JButton("Start");
        start.setFocusable(false);
        start.addActionListener(this);
        start.setBounds(17, 190, 70, 25);

        //Reset Button Initializer
        reset = new JButton("Reset");
        reset.setFocusable(false);
        reset.addActionListener(this);
        reset.setBounds(110, 190, 70, 25);

        //Help Button Initializer
        help = new JButton("Help");
        help.setFocusable(false);
        help.addActionListener(this);
        help.setForeground(new Color(0xb22222));
        help.setBounds(497, 190, 70, 25);

        //Url Text Area
        urls = new JTextArea();

        //Input Title Text Properties
        inputTxt = new JLabel("Urls Here:");
        inputTxt.setForeground(Color.WHITE);
        inputTxt.setBounds(23, 5, 100, 20);

        //Status Label Properties
        status = new JLabel("Status:");
        status.setForeground(Color.WHITE);
        status.setBounds(200, 200, 100, 20);

        //Success/Fail Label Properties
        sucFail = new JLabel("Waiting...");
        sucFail.setForeground(Color.YELLOW);
        sucFail.setBounds(243, 200, 300, 20);

        //Url Scroll Pane
        scrollUrl = new JScrollPane(urls);
        scrollUrl.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollUrl.setBounds(17, 25, 550, 150);

        //File Chooser
        fc = new JFileChooser();
        fc.addActionListener(this);

        //Adding Components to the Window Frame
        window.add(status);
        window.add(sucFail);
        window.add(inputTxt);
        window.add(scrollUrl);
        window.add(start);
        window.add(reset);
        window.add(help);
        window.setVisible(true);

    }

    @Override
    //Checks for Button Presses
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == start) {

            //Creates Integer to get whether "Save" or "Cancel" is pressed
            int result = fc.showSaveDialog(window);

            //Checks is save button pressed
            if (result == JFileChooser.APPROVE_OPTION) {

                //Sets status to working
                sucFail.setText("Working...");
                sucFail.setForeground(Color.ORANGE);

                System.out.println("Approved");
                System.out.println(fc.getSelectedFile());

                //Runs the "Scrape" on a new thread when "Start" pressed
                Thread t1 = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String[] lines = urls.getText().split("\\n");

                        try {
                            //fileName = output.getText();
                            fos = new FileOutputStream(fc.getSelectedFile() + ".csv", true);
                            pw = new PrintWriter(fos);
                            pw.println("Item, Price, Amount, Store Name, Store Link");
                        } catch (IOException ex) {
                        }

                        //Loops Through "lines" Array for Urls and Scrapes Them
                        for (int i = 0; i < lines.length; i++) {

                            url = lines[i];

                            try {
                                scrape.WebScrape(url, pw);
                            } catch (IOException ex) {
                            }

                        }

                        pw.close();
                        sucFail.setText("Ready...");
                        sucFail.setForeground(Color.GREEN);
                    }
                });
                t1.start();

                //If cancel is pressed
            } else if (result == JFileChooser.CANCEL_OPTION) {
                System.out.println("Cancel was selected");
            }

        }

        //Resets the Program
        if (e.getSource() == reset) {

            urls.setText("");
            sucFail.setText("Waiting...");
            sucFail.setForeground(Color.YELLOW);

        }

        //Opens help menu if button pressed
        if (e.getSource() == help) {

            helpWin.helpWindow.setVisible(true);

        }
    }
}
