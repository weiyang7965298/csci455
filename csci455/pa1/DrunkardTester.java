// Name: Yang Wei
// USC loginid: wei495@usc.edu
// CS 455 PA1
// Spring 2016
/**
 * DrunkardTester
 * 
 * @author Yang Wei
 * A program to test Drunkard class independently from its use in the 
 * random walk program. It will have its own main method. 
 */

public class DrunkardTester {
	
	/**
    	Test driver for Drunkard class.
    	@param args not used
    */
	
    public static void main(String[] args) {
		drunkardTester(2,5);// step size is 5, the step number is 5
    }
    	
    /**
		Test all Drunkard methods on given stepNum and stepSize.
		use hard code, the example date is use
		Show the part: Drunkard starts at (3,5); step size is 2
     */
    private static void drunkardTester(int theStepSize, int stepnum) {
    	ImPoint startLoc = new ImPoint(3,5);
		Drunkard testdata = new Drunkard(startLoc, theStepSize);	
	    int x = startLoc.getX();
	    int y = startLoc.getY();
		System.out.println("Drunkard starts at (" +x+ "," +y+ "); step size is " + theStepSize);
		testgetCurrentLoc(startLoc, testdata); // call the method testgetCurrentLoc
		testTakeStep(stepnum, testdata, theStepSize); // call the method testTakeStep
    }

    /**
		Test getCurrentLoc method.
		show the part: get starting loc [expected:(3,5)]: (3,5)
     */
    private static void testgetCurrentLoc(ImPoint startLoc, Drunkard testdata) {
    	int x = testdata.getCurrentLoc().getX();
    	int y = testdata.getCurrentLoc().getY();
    	System.out.println("get starting loc [expected:(3,5)]: (" +x+ "," +y+ ")");
	}

	/**
		Test takeStep method
     */
    private static void testTakeStep(int stepnum, Drunkard testdata, int theStepSize) {
		int x1,x2,y1,y2,PointDis; 
        /*PointDis is the distance between the start to the end point
         * x1 is the location of startposition in x-axis
         * y1 is the location of startposition in y-axis
         * x2 is the location of endposition in x-axis
         * y2 is the location of endposition in y-axis
         */
    	
		/**
		 * This for loop will display the location of every step and judge the move is successful or not.
		 * if have errors, the "fail" will appear.
		 */
		for (int i = 1; i <= stepnum; i++) {
			ImPoint startposition = testdata.getCurrentLoc();
			testdata.takeStep();
			ImPoint endposition = testdata.getCurrentLoc();
			x1 = startposition.getX();
			y1 = startposition.getY();
			x2 = endposition.getX();
			y2 = endposition.getY();
			
	        PointDis = Math.abs(x2-x1) + Math.abs(y2-y1);
	        if (PointDis == theStepSize){
	        	System.out.println("took step to ("+ testdata.getCurrentLoc().getX()+ "," + testdata.getCurrentLoc().getY() + ") SUCCEED");
	        }
	        else{
	        	System.out.println("took step to ("+ testdata.getCurrentLoc().getX()+ "," + testdata.getCurrentLoc().getY() + ") FAILED: not a valid step");
	        }	
		}

    }
	

}

