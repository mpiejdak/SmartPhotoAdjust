import ij.*;
import ij.process.*;
import ij.gui.*;
import java.awt.*;

import neural_networks.Perceptron;
import ij.plugin.*;
import ij.plugin.frame.*;

public class Smart_Photo_Adjust implements PlugIn {

	public void run(String arg) {
		IJ.showMessage("My_Plugin","Hello world!\n"+Perceptron.returnInt());
	}
	
	


}
