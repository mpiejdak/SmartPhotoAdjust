package neural_networks;

import java.lang.reflect.Array;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.Random;

public class TwoLayerPerceptron {

	
	int numberOfInputNeurons = 3;
	int numberOfOutputNeurons = 1;
	int numberOfHiddenNeurons = 3;
	
	final double MSE = 1.0;
	final double LEARNING_FACTOR = 0.000000005;
	
	public double[][] weights_1;
	public double[][] weights_2;
	
	public double final_mse=0;
	
	int[][] patterns = { 
		    { 7, 11, 6 }, 
		    { 4, 1, 2 }, 
		    { 2, 7, 3 }
		    };
	
	double[] teachingOutput = {  34.55 , 120.55 , 122.43 };
	
	int numberOfPatterns = patterns.length;
	
	TwoLayerPerceptron() {

		Random rnd = new Random();
		
	    weights_1 = new double[numberOfInputNeurons][numberOfHiddenNeurons];
	    // init with random values
	    for (int i = 0; i < numberOfHiddenNeurons; i++) {
			for (int j = 0; j < numberOfInputNeurons; j++) {
				weights_1[i][j] = rnd.nextDouble();
			}
		}
	    
	    
	    weights_2 = new double[numberOfHiddenNeurons][numberOfOutputNeurons];
	    for (int i = 0; i < numberOfHiddenNeurons; i++) {
	    	weights_2[i][0] = rnd.nextDouble();
		}
	    
	    //printMatrix(weights_1);
	}
	
	public TwoLayerPerceptron(int[][] inputData, double[] outputData) {
		
		Random rnd = new Random();

		weights_1 = new double[numberOfInputNeurons][numberOfHiddenNeurons];
	    // init with random values
	    for (int i = 0; i < numberOfHiddenNeurons; i++) {
			for (int j = 0; j < numberOfInputNeurons; j++) {
				weights_1[i][j] = rnd.nextDouble();
			}
		}
	    
	    
	    weights_2 = new double[numberOfHiddenNeurons][numberOfOutputNeurons];
	    for (int i = 0; i < numberOfHiddenNeurons; i++) {
	    	weights_2[i][0] = rnd.nextDouble();
		}
	    

	    patterns = inputData;
	    teachingOutput = outputData;
	    
	    numberOfPatterns = patterns.length;
	    numberOfInputNeurons = patterns[0].length;

	}
	
	
	///////////////////////////////
	
	public void deltaRule() {
		int iteratons = 0;
		double mse_error=Double.MAX_VALUE;
		double learningFactor = LEARNING_FACTOR;
		
		double delta = Double.MAX_VALUE;
		double old_mse = 0;
		
		double output_delta = 0;
		
		
		
		while (mse_error > MSE && delta > 0.00001) {
			
			old_mse = mse_error;
			
			iteratons++;
			
			
			// over patterns
			for (int i = 0; i < numberOfPatterns; i++) {

				double output = getNetworkOutput(patterns[i]);
				
				////////////////////////
				// second layer
				////////////////////////
				
				double[] first_layer_output = getFirstLayerOutput(patterns[i]);
				
				// over output
				for (int j = 0; j < numberOfOutputNeurons; j++) {
					
				    	// over input
				        for (int k = 0; k < numberOfHiddenNeurons; k++) {
				        	
				            weights_2[k][j] = weights_2[k][j] + learningFactor
				            		// output after first layer
				                    * first_layer_output[k]
				                    * (teachingOutput[i] - output);

				        }
				        output_delta = (teachingOutput[i] - output);
				}
				
				////////////////////////
				// first layer
				////////////////////////
				
				// over output
				for (int j = 0; j < numberOfHiddenNeurons; j++) {
					
				    	// over input
				        for (int k = 0; k < numberOfInputNeurons; k++) {
				        	
				            weights_1[k][j] = weights_1[k][j] + learningFactor
				            		// input
				                    * patterns[i][k]
				                    // propagated error
				                    * (weights_2[j][0] * output_delta);

				        }
				}
				
				////////////////////////
				// second layer
				////////////////////////
				
			 }
			
			// calculate Mean Square Error between network and training data set
			mse_error = calculateMSE();
			delta = Math.abs(mse_error-old_mse);
						
		}
		
		final_mse = mse_error;
		System.out.println("MSE: "+mse_error);
		System.out.println("No. of learning cycles: "+iteratons);
	
	}
	
	//////////////////////////////
	
	double calculateMSE(){
		
		double mse_error = 0;
		
		for (int i = 0; i < numberOfPatterns; i++){
			double output = getNetworkOutput(patterns[i]);
			
			mse_error += //Math.abs(output-teachingOutput[i]);
			mse_error += Math.pow(output-teachingOutput[i], 2);
					///*Math.sqrt(*/(output[0] - teachingOutput[i])*(output[0] - teachingOutput[i])/*)*/;

		}
		mse_error /= numberOfPatterns;
		
		return mse_error;
	}
	
	double[] setOutputValues(int patternNo) {
		
		double bias = 0.7;
		double[] result = new double[numberOfOutputNeurons];
		int[] toImpress = patterns[patternNo];
		
		double net=0;
		
		for (int i = 0; i < toImpress.length; i++) {
			for (int j = 0; j < result.length; j++) {
			    net = weights_1[0][j] * toImpress[0] + weights_1[1][j]
			            * toImpress[1] + weights_1[2][j] * toImpress[2];
			    /*if (net > bias)
			        result[j] = 1;
			    else
			        result[j] = 0;*/
			    
			    //net = net*(1/(1+Math.exp(net)));
			    
			    result[j] = net;
			}
		}
		
		return result;
	}
	
	public double getNetworkOutput(int[] inputVector) {
		
		double bias = 0.7;

		double output=0;
		
		output = getSecondLayerOutput(getFirstLayerOutput(inputVector));
		
		return output;
	}
	
public double[] getFirstLayerOutput(int[] inputVector) {
		
		double bias = 0.7;

		double[] output=new double[inputVector.length];
		
		for (int i = 0; i < inputVector.length; i++) {
				
			for (int j = 0; j < output.length; j++) {
				
				output[i] += weights_1[i][j]*inputVector[i];
			}

		}
		return output;
	}

public double getSecondLayerOutput(double[] inputVector) {
	
	double bias = 0.7;

	double output=0;
	
	for (int i = 0; i < inputVector.length; i++) {
			
			output += weights_2[i][0]*inputVector[i];

	}
	return output;
}
	
	
	public void printMatrix(double[][] matrix) {
		
		for (int i = 0; i < matrix.length; i++) {
		    for (int j = 0; j < matrix[i].length; j++) {
		        NumberFormat f = NumberFormat.getInstance();
		        if (f instanceof DecimalFormat) {
		            DecimalFormat decimalFormat = ((DecimalFormat) f);
		            decimalFormat.setMaximumFractionDigits(2);
		            decimalFormat.setMinimumFractionDigits(2);
		            System.out.print("(" + f.format(matrix[i][j]) + ")");
		        }
		    }
		    System.out.println();
		}
	}
	
	
	
	
	///////////////////////////////////////////////////
	//	TEST ZONE	///////////////////////////////////
	///////////////////////////////////////////////////
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		TwoLayerPerceptron tlp = new TwoLayerPerceptron();
		System.out.println("Before learning");
		tlp.printMatrix(tlp.weights_1);
		tlp.printMatrix(tlp.weights_2);
		
		tlp.deltaRule();
		
		System.out.println("After learning");
		tlp.printMatrix(tlp.weights_1);
		tlp.printMatrix(tlp.weights_2);
		

	}

}
