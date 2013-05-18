package neural_networks;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Random;



public class Perceptron {

	int numberOfInputNeurons = 3;
	int numberOfOutputNeurons = 1;
	
	final double MSE = 1.5;
	final double LEARNING_FACTOR = 0.001;
	final int MAX_ITERATIONS = (int) 10e6;
	
	public double final_mse=0;
	
	public double[][] weights;
	
	double[][] patterns = { 
		    { 7.2, 11, 6 }, 
		    { 4, 1, 2 }, 
		    { 2, 7, 3 }
		    };
	
	double[][] teachingOutput = { 
		    { 34.55 },
		    { 120.55 }, 
		    { 122.43 }};
	
	int numberOfPatterns = 3;
	
	public Perceptron() {

	    weights = new double[numberOfInputNeurons][numberOfOutputNeurons];
	    
	}
	
	public Perceptron(double[][] inputData, double[][] outputData) {

	    weights = new double[numberOfInputNeurons][numberOfOutputNeurons];
	    
	    patterns = inputData;
	    teachingOutput = outputData;
	    
	    numberOfPatterns = patterns.length;
	    //numberOfInputNeurons = patterns[0].length;
		
	    //numberOfOutputNeurons = teachingOutput[0].length;

	}
	
	
	///////////////////////////////
	
	public void deltaRule() {
		int iteratons = 0;
		double mse_error=Double.MAX_VALUE;
		double learningFactor = LEARNING_FACTOR;
		
		double delta = Double.MAX_VALUE;
		double old_mse = 0;
		
		
		
		while (mse_error > MSE && delta > Double.MIN_VALUE*10 && iteratons < MAX_ITERATIONS) {
			
			old_mse = mse_error;
			
			iteratons++;
			
			
			
			// over patterns
			for (int i = 0; i < numberOfPatterns; i++) {
				
				double[] output = setOutputValues(i);
				
				// over output
				for (int j = 0; j < numberOfOutputNeurons; j++) {
					
				    	// over input
				        for (int k = 0; k < numberOfInputNeurons; k++) {
			        	
				            weights[k][j] = weights[k][j] + learningFactor
				                    * patterns[i][k]
				                    * (teachingOutput[i][j] - output[j]);

				        }
				}
				
			 }
			
			// calculate Mean Square Error between network and training data set
			mse_error = calculateMSE();
			delta = Math.abs(mse_error-old_mse);
						
		}
		
		final_mse = mse_error;
		System.out.println("MSE: "+mse_error);
		System.out.println("No. of learning cycles: "+iteratons);
	
	}
	
	
	double calculateMSE(){
		
		double mse_error = 0;
		
		for (int i = 0; i < numberOfPatterns; i++){
			double[] output = setOutputValues(i);
			
			mse_error += /*Math.sqrt(*/(output[0] - teachingOutput[i][0])*(output[0] - teachingOutput[i][0])/*)*/;

		}
		mse_error /= numberOfPatterns;
		
		return mse_error;
	}
	
	double[] setOutputValues(int patternNo) {
		
		double bias = 0.7;
		double[] result = new double[numberOfOutputNeurons];
		double[] toImpress = patterns[patternNo];
		
		double net=0;
		
		for (int i = 0; i < toImpress.length; i++) {
			for (int j = 0; j < result.length; j++) {
			    net = weights[0][j] * toImpress[0] + weights[1][j]
			            * toImpress[1] + weights[2][j] * toImpress[2];
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
		
		for (int i = 0; i < inputVector.length; i++) {
				
				output += weights[i][0]*inputVector[i];

		}
		return output;
	}
	// TEST 11
	
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
	
	public static int returnInt(){
		return 666;
	}
	
}
