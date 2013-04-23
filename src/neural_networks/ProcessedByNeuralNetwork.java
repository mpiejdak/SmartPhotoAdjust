/**
 * 
 */
package neural_networks;

/**
 * @author Aleks
 *
 */
public interface ProcessedByNeuralNetwork {
	
	// process data by neural network
	public double[] getOutput(double[] inputVector);
	
	// returns true if training correct
	public boolean trainNetwork(double[][] trainingData);
}
