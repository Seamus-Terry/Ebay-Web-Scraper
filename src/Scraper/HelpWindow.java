package Scraper;


import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.JFrame;
import javax.swing.JTextArea;

public class HelpWindow {

    public JFrame helpWindow;
    public JTextArea helpLabel;

    public HelpWindow() {

        helpWindow = new JFrame();
        helpWindow.setTitle("Help");
        helpWindow.setLayout(null);
        helpWindow.setSize(400, 275);
        helpWindow.setLayout(new FlowLayout());
        helpWindow.setLocationRelativeTo(null);
        helpWindow.setResizable(false);
        helpWindow.getContentPane().setBackground(new Color(0x003f5b));
        helpWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        helpLabel = new JTextArea("""
                                  
                                  1. Get Desired Ebay Store URLs and paste them each on
                                      a seperate line in the URLs text area.
                                  
                                  2. Enter CSV file name in the 'Document Name' area.
                                  
                                  3. Click 'Start' and wait until the status 'Ready....' appears 
                                      at the bottom.
                                  
                                  4. Click 'Reset' to reset the URLs and the document name.
                                  
                                  5. Enter new URLs into the URLs text area. Set a new document 
                                      name and click 'Start' to run it again.
                                  """);

        helpLabel.setBackground(new Color(0x003f5b));
        helpLabel.setEditable(false);
        helpLabel.setForeground(Color.WHITE);

        helpWindow.add(helpLabel);
    }

}
