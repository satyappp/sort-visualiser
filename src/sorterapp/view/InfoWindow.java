package sorterapp.view;

/* 
* Program: controller.java
* Authors: Fraser Robb (s1308002)
*          Satya Pahari (s1282006)             
* Description:
 * Information window of the Sorting algorithm visualiser GUI.
 * Acts as part of the MVC architecture's view.
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import sorterapp.controller.*;
import sorterapp.model.*;
import java.util.List;

public class InfoWindow extends JFrame
{
    private static final int WINDOW_WIDTH = 700;
    private static final int WINDOW_HEIGHT = 500;
    
    private Controller controller;
    private JButton closeButton;
    
    public InfoWindow(Controller inController)
    {
        super("SORTING ALGORITHM INFORMATION"); // Window title.
        setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));      // Window size
        controller = inController;

        closeButton = new JButton("Close"); // A button to close the window.

        // Set up window layout
        JPanel contentPane = new JPanel(new BorderLayout());
        JTextArea textArea = new JTextArea(getSorterInfo(controller.getSorters()));
        JScrollPane scrollPane = new JScrollPane(textArea);
        textArea.setEditable(false);
        textArea.setFont(new Font("Arial",Font.BOLD, 13));
        JToolBar toolbar = new JToolBar();
        contentPane.add(toolbar, BorderLayout.NORTH);
        contentPane.add(scrollPane, BorderLayout.CENTER);
        toolbar.add(closeButton);
        getRootPane().setContentPane(contentPane);

        // The close button simply hides this window; i.e. making it invisible 
        // to the user (but still present in memory).
        closeButton.addActionListener(
            new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {
                    setVisible(false);
                }
            }
        );
        
        pack();
        setLocationRelativeTo(null); // centre the GUI window
    }
    /**
     * Takes a set of Sorter objects and displays them in the window as options
     */
    public String getSorterInfo(List<Sorter> list)
    {
        String info = """
            Welcome to the Team SE Sorter App!
            This application is a user extendable educational tool used to visualise various sorting algorithms.
            Click on the SORT button to get started, or customise your own integer array's size and composition 
            using the buttons on the right!\n\nInformation about each sorting algorithm is provided below:\n
            _________________
            """;
        
        for (Sorter i  : list) {
            info += "\n" + i.getName() + ":\n\n" + i.getInfo() + "\n_________________\n";
        }
        return info;
    }


}