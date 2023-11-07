package sorterapp.view;
/* 
* Program: controller.java
* Authors: Fraser Robb (s1308002)
*          Satya Pahari (s1282006)             
* Description:
 * Window for the visualiser of the Sorting algorithm visualiser GUI.
 * Acts as part of the MVC architecture's view.
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import sorterapp.controller.*;
import sorterapp.model.*;

public class VisualiserWindow extends JFrame
{

    private static final int WINDOW_WIDTH = 700;
    private static final int WINDOW_HEIGHT = 500;


    //private JTextField taskWidget;
    private JButton closeButton;
    private JButton sortButton;
    private Sorter currSelection;
    
    public VisualiserWindow(Controller inController, Sorter inSelection)
    {
        super("Sorting Algorithm Visualiser"); // Window title.
        setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT)); // Window size
        currSelection = null;
        closeButton = new JButton("Close"); // A button to close the window.
        sortButton = new JButton("SORT!"); // A button to close the window.
        
        // Set up window layout
        JPanel visualiserPanel = new SwapObserver(inSelection);
        inController.addObserver((SorterObserver)visualiserPanel); //typecast is necessary here as the SwapObserver class both extends JPanel AND implements SortObserver
        
        JToolBar toolbar = new JToolBar();
        visualiserPanel.add(toolbar, BorderLayout.NORTH);
        toolbar.add(sortButton);
        toolbar.add(closeButton);
        getRootPane().setContentPane(visualiserPanel);    
        
        visualiserPanel.setBackground(Color.black); // CHANGE COLOUR TO BLACK
        
        // When the "SORT" button is clicked, make the visualisation window visible.
        sortButton.addActionListener(
            new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {
                    currSelection.sort();
                }
            }
        );
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
    
    // Setter for modifying the sorting algorithm to run
    public void setSorter(Sorter inSorter)
    {
        currSelection = inSorter;
    }

}