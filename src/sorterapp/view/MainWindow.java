package sorterapp.view;

/* 
* Program: controller.java
* Authors: Fraser Robb (s1308002)
*          Satya Pahari (s1282006)             
* Description:
 * Main (starting/menu) window of the Sorting algorithm visualiser GUI.
 * Acts as part of the MVC architecture's view.
 */

import sorterapp.controller.*;
import sorterapp.model.*;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.*;
import java.util.*;


public class MainWindow extends JFrame
{
    private JList<String> sortOptions;
    private VisualiserWindow visualiserWindow;
    private InfoWindow infoWindow;
    private Controller controller;
    private Sorter currSelection;
    private int arraySize;

    private static final int WINDOW_WIDTH = 400;
    private static final int WINDOW_HEIGHT = 300;
    
    /**
     * We need the controller so we can retrieve info about the sorts (info, names etc),
     * AND to provide it to the VisualiserWindow constructor for use during the sorting process.
     */
    public MainWindow(Controller inController)
    {
        super("Sorter App"); // Window title.
        setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));      // Window size
        
        // Populate necessary classfields
        controller = inController;
        currSelection = null; // Selected sorter is initially null (user hasn't yet chosen an algorithm)
        infoWindow = new InfoWindow(controller);
        arraySize = 10; //Default array size
        visualiserWindow = new VisualiserWindow(controller, currSelection);
        
        // Make the whole program close when this window is closed.
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // operation TOOLBAR BUTTONS
        JButton infoButton;
        JButton sortButton;
        infoButton = new JButton("  INFO  ");
        sortButton = new JButton("SELECT");

        // RHS TOOLBAR BUTTONS
        JButton randomButton;
        JButton shuffleButton;
        JButton descendingButton;
        randomButton = new JButton("Random");
        shuffleButton = new JButton("Shuffle");
        descendingButton = new JButton("Descending");

        // SLIDER (Array range)
        JSlider sizeSlider;

        // SORTING OPTIONS (MENU)
        sortOptions = new JList<String>();
        sortOptions.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        sortOptions.setFont(new Font("Arial",Font.BOLD,30));
        DefaultListCellRenderer renderer =  (DefaultListCellRenderer)sortOptions.getCellRenderer();  
        renderer.setHorizontalAlignment(JLabel.CENTER); // Center the JList contents  

        // Set up window layout
         JPanel contentPane = new JPanel(new BorderLayout());
        //JPanel contentPane = new JPanel(new FlowLayout());

        JScrollPane scrollPane = new JScrollPane(sortOptions);
        contentPane.add(scrollPane, BorderLayout.CENTER);

        JToolBar operationToolbar = new JToolBar(JToolBar.VERTICAL);
        contentPane.add(operationToolbar, BorderLayout.WEST);
        operationToolbar.add(sortButton);
        operationToolbar.add(new JSeparator());
        operationToolbar.add(infoButton);

        JToolBar compositionToolbar = new JToolBar(JToolBar.VERTICAL);
        contentPane.add(compositionToolbar, BorderLayout.EAST);
        compositionToolbar.add(randomButton);
        compositionToolbar.add(new JSeparator());
        compositionToolbar.add(shuffleButton);
        compositionToolbar.add(new JSeparator());
        compositionToolbar.add(descendingButton);

        // SLIDER (for selecting array size)
        sizeSlider = new JSlider (0, 50);
        sizeSlider.setPaintTicks(true);
        sizeSlider.setMajorTickSpacing(25);
        sizeSlider.setMinorTickSpacing(10);
        sizeSlider.setPaintLabels(true);
        
        contentPane.add(sizeSlider, BorderLayout.SOUTH);

        JLabel appLabel = new JLabel("Sorting Algorithms:", SwingConstants.CENTER);
        appLabel.setVerticalTextPosition(JLabel.BOTTOM);
        appLabel.setHorizontalTextPosition(JLabel.CENTER);
        appLabel.setFont(new Font("Arial",Font.BOLD,16));
        contentPane.add(appLabel, BorderLayout.NORTH);
        
        
        getRootPane().setContentPane(contentPane);
        
        // Make JList register double clicks as selections (for selecting algorithm)
        // Reference: http://www.java2s.com/Tutorial/Java/0240__Swing/Selectioneventfordoubleclickinganiteminthelist.htm
        MouseListener mouseListener = new MouseAdapter() {
            public void mouseClicked(MouseEvent mouseEvent) {
              JList list = (JList) mouseEvent.getSource();
              if (mouseEvent.getClickCount() == 1) {
                int index = list.locationToIndex(mouseEvent.getPoint());
                if (index >= 0) 
                {
                    currSelection = controller.getSorters().get(index); // Set double clicked as the selected algorithm
                    visualiserWindow.setSorter(currSelection); // prepare the visualiser by applying the selection
                    //System.out.println("Selection: " + currSelection.getName());    
                }
              }
            }
          };
          sortOptions.addMouseListener(mouseListener);
      

        // Changes the size of the array to be sorted
        sizeSlider.addChangeListener(
            new ChangeListener()
            {
                public void stateChanged(ChangeEvent e)
                {
                    if (!sizeSlider.getValueIsAdjusting()) { // only runs when slider is stopped
                        //System.out.println("sizeSlider: " + sizeSlider.getValue());
                        arraySize = sizeSlider.getValue();
                   }
                    
                }
            }
        );

        /*** operation TOOLBAR BUTTON-CLICK EVENTS ***/

        // When the "SELECT" button is clicked, make the visualisation window visible.
        sortButton.addActionListener(
            new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {
                    if(currSelection != null) //User has not clicked a sorting option
                        visualiserWindow.setVisible(true);
                    else{} // A sorting algorithm has not been selected, so the program intentionally doesn't proceed
                        //System.out.println("SELECT A SORTING ALG FIRST");
                }
            }
        );
        
        // The controller is used to retrieve useful info from the sorting algs
        // when the "INFO" button is clicked and display them to the user
        infoButton.addActionListener(
            new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {   
                    infoWindow.setVisible(true);
                }
            }
        );
        getOptions(controller.getSorters());

        pack(); // We call pack() to size the frame so that all contents are the desired size
        setLocationRelativeTo(null); // centre the GUI window

        /*** composition TOOLBAR: ARRAY TYPE BUTTON EVENTS ***/

        randomButton.addActionListener(
            new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {
                    controller.setArray(Generator.random_generator(arraySize));
                    //System.out.println(Arrays.toString(controller.getSorters().get(0).getUnsortedArr()));
                }
            }
        );

        descendingButton.addActionListener(
            new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {
                    controller.setArray(Generator.descending_generator(arraySize));
                    //System.out.println(Arrays.toString(controller.getSorters().get(0).getUnsortedArr()));
                }
            }
        );

        shuffleButton.addActionListener(
            new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {
                    controller.setArray(Generator.shuffle_generator(arraySize));
                    //System.out.println(Arrays.toString(controller.getSorters().get(0).getUnsortedArr()));
                }
            }
        );
    }
    
    /**
     * Sorting algorithm list menu
     */
    public void getOptions(List<Sorter> list)
    {
        Vector<String> tasks = new Vector<String>();
        for (Sorter i  : list)
        {
            tasks.add(i.getName());
        }
        sortOptions.setListData(tasks);
    }

}

