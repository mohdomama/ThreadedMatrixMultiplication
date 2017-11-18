import java.util.Scanner;

public class Matrix {
	//private static int[][] matrix = new int[][] 
	public static int[][] matrix1;
	public static int[][] matrix2;
	public static int[][] matrixResult;
	public static int[][] matrixResult2;
	
	public static void main(String[] args) {

		long startTime;
		
		
		Scanner input = new Scanner(System.in);
		int order;
		System.out.println("Enter the order of matrix:");
		order = input.nextInt();


		
		matrix1 = new int[order][order];
		matrix2 = new int[order][order];
		matrixResult = new int[order][order];
		matrixResult2 = new int[order][order];
		
		System.out.println("Enter input for first matrix:");
        for (int i = 0; i < order; i++) {
			for (int j = 0; j < order; j++){
				matrix1[i][j] = input.nextInt();

			}
			
		}

		System.out.println("Enter input for second matrix:");
		for (int i = 0; i < order; i++) {
			for (int j = 0; j < order; j++){
				matrix2[i][j] = input.nextInt();
			}
		}

		

		Thread thread1 = new Thread (new MultiThread(0, 3));
		Thread thread2 = new Thread (new MultiThread(1, 3));
		Thread thread3 = new Thread (new MultiThread(2, 3));
		thread1.setPriority( Thread.MAX_PRIORITY );
		thread2.setPriority( Thread.MAX_PRIORITY );
		thread3.setPriority( Thread.MAX_PRIORITY );
		thread1.start();
		thread2.start();
		thread3.start();
		startTime = System.nanoTime(); 
		
		try {
			thread1.join();
			thread2.join();
			thread3.join();
		}
		catch (Exception e){
			System.out.println("Some Exception");
		}


		System.out.println("The output matrix is:");
		for (int i = 0; i < order; i++) {
			for (int j = 0; j < order; j++){
				System.out.print(matrixResult[i][j] + " ");
			}
			System.out.println("");
		}

		System.out.println((System.nanoTime() - startTime)/1000);

		


		startTime = System.nanoTime();
		for (int row = 0 ; row < 3 ; row++){
	
			for (int i = 0; i < 3  ; i++ ) {
				int sum = 0;
				for (int j = 0 ; j < 3 ; j++ ) {
					sum = sum + matrix1[row][j] * matrix2[j][i];
				}
				matrixResult2[row][i] = sum;
			}
		}

		System.out.println("The output matrix is:");
		for (int i = 0; i < order; i++) {
			for (int j = 0; j < order; j++){
				System.out.print(matrixResult2[i][j] + " ");
			}
			System.out.println("");
		}

		System.out.println((System.nanoTime() - startTime)/1000);


	}
}

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
			Matrix.matrixResult[row][i] = sum;
		}
	}
}
