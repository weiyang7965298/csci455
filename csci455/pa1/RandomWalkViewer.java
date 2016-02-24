// Name: Yang Wei
// USC loginid: wei495@usc.edu
// CS 455 PA1
// Spring 2016
/**
 * RandomWalkViewer
 * Author: Yang Wei
 * 
 * Contains the main routine. Prompts for the number of steps, and 
 * creates the JFrame containing the RandomWalkComponent.
 * 
 */
import javax.swing.JFrame;
import java.util.Scanner;

/**
 * This program get the step-number from user and then analog the walk
 * path of the drunkard. The program also build a GUI to draw the paths.
 */
public class RandomWalkViewer {
   public static void main(String[] args)
    {     
      int stepnum;  //the step size in the Drunkard
      Scanner stepinput = new Scanner(System.in);
      
     /* The do-while loop check and input the step number. 
      * Step number is invalid and need to be re-input when 
      * it is equal or less than 0.
      */
      do{
    	  System.out.print("Enter the number of steps: ");
    	  stepnum = stepinput.nextInt();
    	  if (stepnum < 0 )
    	  {
    		  System.out.println("ERROR: Number entered must be greater than 0.");
    	  }
         }while(stepnum <= 0);
      stepinput.close(); // close the input get
      
      RandomWalkComponent Comp = new RandomWalkComponent(stepnum); // Call the class RandomWalkComponent
 
      /**
       * Build a graphical application shows drunkard information inside
       * a frame.  
       */
	  JFrame frame = new JFrame();
      frame.setSize(400,400);
      frame.setTitle ("Random Walk");
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setVisible(true);
      frame.add(Comp); // add RandomWalsComponent to the content pane
     }
    
}