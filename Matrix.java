import java.util.Scanner;


class MultiThread implements Runnable{ 
	private int row;
	private int order;

	public MultiThread(int row , int order){
		this.row = row;
		this.order = order;
	}

	public void run(){
		for (int i = 0; i < order  ; i++ ) {
			int sum = 0;
			for (int j = 0 ; j < order ; j++ ) {
				sum = sum + Matrix.matrix1[row][j] * Matrix.matrix2[j][i];
			}
			Matrix.outputMatrix[row][i] = sum;
		}
	}
}


//the main class
public class Matrix {
	//private static int[][] matrix = new int[][] 
	public static int[][] matrix1, matrix2, outputMatrix, outputMatrix2;
	
	public static void main(String[] args) {

		long startTime;
		Scanner input = new Scanner(System.in);
		int order;
		System.out.println("Enter the order of matrix:");
		order = input.nextInt();

		matrix1 = new int[order][order];
		matrix2 = new int[order][order];
		outputMatrix = new int[order][order];
		outputMatrix2 = new int[order][order];


		System.out.println("Enter the entries of matrix1");
		for(int i=0; i<order; ++i) 
			for(int j=0; j<order; ++j) 
				matrix1[i][j] = input.nextInt();

		System.out.println("Enter the entries of matrix2");
		for(int i=0; i<order; ++i) 
			for(int j=0; j<order; ++j) 
				matrix2[i][j] = input.nextInt();


		startTime = System.nanoTime();
		Thread threads[] = new Thread[order];

		for(int i=0; i<order; ++i){
			threads[i] = new Thread(new MultiThread(i,order));
			threads[i].setPriority(1);
			threads[i].start();
		}

		try{
			threads[order-1].join();
		}
		catch (Exception e){
			System.out.println("Thread exception");
		}

		System.out.println("The output matrix is:");
		for (int i = 0; i < order; i++) {
			for (int j = 0; j < order; j++){
				System.out.print(outputMatrix[i][j] + " ");
			}
			System.out.println("");
		}
		System.out.println("The runtime for threads is:");
		System.out.println((System.nanoTime() - startTime)/1000);

		


		startTime = System.nanoTime();
		for (int row = 0 ; row < order; row++){
	
			for (int i = 0; i < order  ; i++ ) {
				int sum = 0;
				for (int j = 0 ; j < order ; j++ ) {
					sum = sum + matrix1[row][j] * matrix2[j][i];
				}
				outputMatrix2[row][i] = sum;
			}
		}

		System.out.println("The output matrix is:");
		for (int i = 0; i < order; i++) {
			for (int j = 0; j < order; j++){
				System.out.print(outputMatrix2[i][j] + " ");
			}
			System.out.println("");
		}
		System.out.println("The runtime without threads is:");
		System.out.println((System.nanoTime() - startTime)/1000);
	}
}



