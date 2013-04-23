import ij.*;
import ij.process.*;
import ij.gui.*;
import java.awt.*;

import neural_networks.Perceptron;
import ij.plugin.*;
import ij.plugin.frame.*;

public class Smart_Photo_Adjust implements PlugIn {

	public void run(String arg) {
		
		// variables
		int[] inputVector = null;
		int[] outputVector = null;
		
		/////
		// find inputVector - information about unprocessed image 
		
		// TO DO
		
		/////
		
		/////
		// process by neural network and suggest adjustments (as outputVector)
		
		
		Perceptron p = new Perceptron();
		//outputVector = p.getNetworkOutput(inputVector);
		/////
		
		/////
		// adjust image
		
		// TO DO
		
		/////
		
		IJ.showMessage("Smart Photo Adjust Plugin","Automatic photo adjust conducted succesfully!");
		
	}
	
	


}
