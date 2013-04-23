package neural_networks;

public class SubApp {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		
		Perceptron p = new Perceptron();
		System.out.println("Weights before training: ");
		p.printMatrix(p.weights);
		p.deltaRule();
		System.out.println("Weights after training: ");
		p.printMatrix(p.weights);

	}

}
