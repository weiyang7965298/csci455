// Name: Yang Wei
// USC loginid: wei495@usc.edu
// CS 455 PA1
// Spring 2016
/**
   RandomWalkComponent
   
      Constructor initializes any necessary data. Overrides paintComponent to draw
      the random walk, using a Drunkard object to keep track of current state of 
      drunkard.

    author: Yang Wei

*/

import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JComponent;
import java.awt.geom.Line2D; 

public class RandomWalkComponent extends JComponent {

	    private Drunkard drunkard;
	    private int stepnum; // the number of step that drunkard will walk
	    /**
	     * The method paintComponent is to get the current location and draw
	     * the walking paths of the drunkard.  
	     */
		public void paintComponent(Graphics g)
	    {
	    
			Graphics2D g2 = (Graphics2D) g; 	// Based on the textbook	 
			/* This for loop is to draw the segment of the drunkard from the
			 * start point to end point
			 */
			int i = 1;
			while (i <= stepnum) { 
			    ImPoint startposition = drunkard.getCurrentLoc();	 
			    drunkard.takeStep();  
			    ImPoint endposition = drunkard.getCurrentLoc(); 
		    	Line2D.Double line = new Line2D.Double(startposition.getPoint2D(), endposition.getPoint2D()); 		    
		    	g2.draw(line);
		    	i = i + 1;
		    	}
			
			}
        /**
         * 	This method create the initial position and step number. 
         * @param stepnum: the step number that the drunkard will randomly walk
         */
       public RandomWalkComponent(int stepnum){
    	   ImPoint point = new ImPoint(200,200); //the start point is middle point.
    	   this.stepnum = stepnum;
    	   drunkard = new Drunkard(point, 5); // 5 means five pixel per step
    	   
       }
       

}