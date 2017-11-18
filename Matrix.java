import java.util.Scanner;

public class Matrix {
	//private static int[][] matrix = new int[][] 
	public static int[][] matrix1;
	public static int[][] matrix2;
	public static int[][] matrixResult;
	
	public static void main(String[] args) {

		
		
		
		Scanner input = new Scanner(System.in);
		int order;
		System.out.println("Enter the order of matrix:");
		order = input.nextInt();


		
		matrix1 = new int[order][order];
		matrix2 = new int[order][order];
		matrixResult = new int[order][order];
		
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
		thread1.start();
		thread2.start();
		thread3.start();
		
		System.out.println("The first matrix is:");
		for (int i = 0; i < order; i++) {
			for (int j = 0; j < order; j++){
				System.out.print(matrix1[i][j] + " ");
			}
			System.out.println("");
		}

		System.out.println("The second matrix is:");
		for (int i = 0; i < order; i++) {
			for (int j = 0; j < order; j++){
				System.out.print(matrix2[i][j] + " ");
			}
			System.out.println("");
		}

		System.out.println("The output matrix is:");
		for (int i = 0; i < order; i++) {
			for (int j = 0; j < order; j++){
				System.out.print(matrixResult[i][j] + " ");
			}
			System.out.println("");
		}



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
