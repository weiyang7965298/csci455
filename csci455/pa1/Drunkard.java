// Name: Yang Wei
// USC loginid: wei495@usc.edu
// CS 455 PA1
// Spring 2016

import java.util.Random;

/**
   Drunkard class
       Represents a "drunkard" doing a random walk on a grid.
*/

public class Drunkard {

    // add private instance variables here:
    private int theStepSize;     // the step size of the drunkard when he moves
    private ImPoint CurrentLoc; // The CurrentLoc is to update and locate the position of drunkard

    
    /**
       Creates drunkard with given starting location and distance
       to move in a single step.
       @param startLoc starting location of drunkard
       @param theStepSize size of one step in the random walk
    */
    public Drunkard(ImPoint startLoc, int theStepSize) {
           this.theStepSize = theStepSize; // use this to avoid confound of theStepSize
           CurrentLoc = startLoc;

    }


    /**
       Takes a step of length step-size (see constructor) in one of
       the four compass directions.  Changes the current location of the
       drunkard.
    */
    public void takeStep() {
    	Random random = new Random(); // only need to build one new random object
    	int randomdirc = random.nextInt(4); // Have four direction, so generate 0,1,2,3. these four number
    	switch(randomdirc){
    	     case 0: CurrentLoc = CurrentLoc.translate(0,theStepSize); break;  // walk down with unit step size
    	     case 1: CurrentLoc = CurrentLoc.translate(0,-theStepSize); break; // walk with unit step size
    	     case 2: CurrentLoc = CurrentLoc.translate(theStepSize,0); break; // walk left with unit step size
    	     default: CurrentLoc = CurrentLoc.translate(-theStepSize,0); break; // walk right with unit step size
    	}
    }


    /**
       gets the current location of the drunkard.
       @return an ImPoint object representing drunkard's current location
    */
    public ImPoint getCurrentLoc() {
        return CurrentLoc;
    }

}