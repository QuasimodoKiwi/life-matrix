
/**
 * The purpose of this project is to generate a Life Matrix made out of boolean values.
 * The values for true will be marked with "#", while the values for false will remain as "-".
 */

import java.util.Scanner;
import java.util.Random;

/**
 * The entire class features the Life Matrix.
 * 
 * @author Connor Hamilton
 */
public class Life {
	/**
	 * Upon startup, the user will be prompted to make the inputs for the Rows,
	 * Columns, and Seed. Then the matrix will be printed.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int rows = scan.nextInt();
		int columns = scan.nextInt();
		long seed = scan.nextLong();
		int birthLow = scan.nextInt();
		int birthHigh = scan.nextInt();
		int liveLow = scan.nextInt();
		int liveHigh = scan.nextInt();
		scan.close();
		boolean[][] matrix = matrixGeneration(rows, columns, seed);
		printMatrix(matrix);
		updateAndPrintMatrix(matrix, birthLow, birthHigh, liveLow, liveHigh);
	}

	/**
	 * When the user makes their inputs, the matrix will automatically be created
	 * with the inputs they made. The inputs are then fed into the parameters.
	 * 
	 * @param rows
	 * @param columns
	 * @param seed
	 * @return 2D Boolean Array
	 */
	public static boolean[][] matrixGeneration(int rows, int columns, long seed) {
		Random rand = new Random(seed);
		boolean[][] truthTable = new boolean[rows][columns];
		for (int i = 1; i < truthTable.length - 1; i++) {
			for (int j = 1; j < truthTable[i].length - 1; j++) {
				truthTable[i][j] = rand.nextBoolean();
			}
		}
		return truthTable;
	}

	/**
	 * Prints out the matrix that is set by Rows, Columns, and Seed.
	 * Every character will be replaced by "-" if false, and "#" if true.
	 * @param matrix
	 */
	public static void printMatrix(boolean[][] matrix) {
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				if (!matrix[i][j]) {
					System.out.print("- ");
				} else {
					System.out.print("# ");
				}
			}
			System.out.println();
		}
		System.out.println();
	}

	/**
	 * Starts by traversing the original matrix. For every iteration,
	 * an iteration of the original will be copied into the clone's index until it's finished.
	 * @param ogMatrix
	 * @return Clone of Original Matrix
	 */
	public static boolean[][] cloneMatrix(boolean[][] ogMatrix) {
		boolean[][] clone = new boolean[ogMatrix.length][ogMatrix[0].length];
		for (int i = 0; i < ogMatrix.length; i++) {
			for (int j = 0; j < ogMatrix[i].length; j++) {
				clone[i][j] = ogMatrix[i][j];
			}
		}
		return clone;
	}

	/**
	 * The purpose of this function is to update the matrix 5 times by checking its
	 * neighbors based on the # of neighbors minimum and maximum. The int variable
	 * liveCount will be compared to minB, maxB. It will also determine the survival
	 * of the cell.
	 * 
	 * @param matrix
	 * @param minB
	 * @param maxB
	 * @param minS
	 * @param maxS
	 */
	public static void updateAndPrintMatrix(boolean[][] matrix, int minB, int maxB, int minS, int maxS) {
		int counter = 0;
		while (counter < 5) {
			boolean[][] clone = cloneMatrix(matrix);
			for (int i = 1; i < matrix.length - 1; i++) {
				for (int j = 1; j < matrix[i].length - 1; j++) {
					int liveCount = 0;
					for (int x = i - 1; x <= i + 1; x++) {
						for (int y = j - 1; y <= j + 1; y++) {
							if (clone[x][y]) {
								liveCount++;
							}
						}
					}
					if (!clone[i][j] && liveCount >= minB && liveCount <= maxB) {
						matrix[i][j] = true;
					}

					if (clone[i][j] && liveCount < minS) {
						matrix[i][j] = false;
					} if (clone[i][j] && liveCount > maxS) {
						matrix[i][j] = false;
					}
				}
			}
			printMatrix(matrix);
			counter++;
		}
	}
}