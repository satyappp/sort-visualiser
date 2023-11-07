package sorterapp;

import sorterapp.model.*;
import sorterapp.view.MainWindow;
import sorterapp.controller.*;
import javax.swing.*;

public class SorterApp
{
    public static void main(String[] args)
    {
        try
        {
            int[] array = Generator.descending_generator(10); // Defaults array
            final SorterList sorters = new SorterList();
            final Controller controller = new Controller(sorters, array);

            // GUI operates within the "Event Dispatch Thread", as per swing standards
            SwingUtilities.invokeLater(
                new Runnable()
                {
                    public void run()
                    {
                        // Create a window (hidden to begin with)
                        MainWindow window = new MainWindow(controller);
                                            
                        // Exit when the exit button is pressed.
                        window.setVisible(true);
                    }
                }
            );
            //CLI.startingMenu(controller);

        }
        catch(IllegalArgumentException e)
        {
            System.out.println(e.getMessage());
        }
        catch(Exception ex2)
        {
            System.out.println("***\nAn unexpected error occured: " + ex2.getMessage());
        }
       
    }

    
}