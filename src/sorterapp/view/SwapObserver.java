package sorterapp.view;
/* 
* Authors: Fraser Robb (s1308002)
*          Satya Pahari (s1282006)             
* Program: Sorter.java
* Description:
* Concrete observer used to display the results of a swap during
* the sorting process.
*/
import sorterapp.model.*;
import javax.swing.*;
import java.awt.*;

public class SwapObserver extends JPanel implements SorterObserver 
{
    private static final int WINDOW_WIDTH = 700;
    private static final int WINDOW_HEIGHT = 500;
    private static final double BAR_SCALE_RATIO = 0.95; // Determines the size of the max array value relative to the size of the window
    
    private int BAR_WIDTH;

    private int[] array;
    private int arrayMax;
    
    public SwapObserver(Sorter inSorter){
        super(new BorderLayout());

    } // Default constructor
    public void update(int[] inArray)
    {
        array = inArray;
        setMax();
        BAR_WIDTH = (WINDOW_WIDTH)/(array.length);
        this.setDoubleBuffered(true); // Use double buffer for smooth component printing (array visualisation)
        paintComponent(getGraphics());
    }
    
    @Override
    public void paintComponent(Graphics g)
    {
        try
        {
            try 
            {
                Thread.sleep(1000/array.length);
                // Delay is scaled relative to array length (e.g. The delay between displays shorted for larger arrays)
            } 
            catch (InterruptedException e) 
            {
                Thread.currentThread().interrupt();
            }

            Graphics2D graphics = (Graphics2D)g;
            super.paintComponent(graphics);
            graphics.setColor(Color.white);
            for(int i = 0; i < array.length; i++)
            {
                double proportion = (double)array[i] / (double)arrayMax; //Ratio of current value to max val
                int height = (int) (proportion * WINDOW_HEIGHT*BAR_SCALE_RATIO);
                graphics.fillRect(i*(BAR_WIDTH), WINDOW_HEIGHT-height, BAR_WIDTH, height);
                
            }
            

        } catch(NullPointerException e){} // We expect that the array is null before sorting algorithms are chosen
    }

    private void setMax()
    {
        int max = array[0];
        for(int num : array) if (max < num) max = num; // Finds max value in the array
        arrayMax = max;
    }
}