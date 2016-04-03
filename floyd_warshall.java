package games;

public class floyd_warshall {
	static int[][] DistanceMatrix;
	static final int NoOfNodes = 4;
	
	public floyd_warshall() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*
		 * We can calculate distance from every node to every other node
		 * O(n^3)
		 * 
		 */
		
		/*
		 * Adding 999 ~ Infinity
		 */
		
		int[][] ProblemMatrix = { { 0, 5, 999, 999 }, { 50, 0, 15, 5 }, { 30, 999, 0, 15 },
				{ 15, 999, 5, 0 } };
		DistanceMatrix = new int[NoOfNodes][NoOfNodes];
		System.out.println("Printing the matrix which we need to find shortest path.");
		printMatrix(ProblemMatrix);
		System.out.println("Shortest Path Matrix.");
		printMatrix(FloydAlgo(ProblemMatrix));
		System.out.println("Path Matrix");
		printMatrix(DistanceMatrix);

	}
	public static int[][] FloydAlgo(int[][] M) {
		for (int k = 0; k < NoOfNodes; k++) {
			for (int i = 0; i < NoOfNodes; i++) {
				for (int j = 0; j < NoOfNodes; j++) {
					// to keep track.;
					if (M[i][k] + M[k][j] < M[i][j]) {
						M[i][j] = M[i][k] + M[k][j];
						DistanceMatrix[i][j] = k;
					}
					// or not to keep track.
					//M[i][j] = min(M[i][j], M[i][k] + M[k][j]);
				}
			}
		}
		return M;
	}

	public static int min(int i, int j) {
		if (i > j) {
			return j;
		}
		return i;
	}

	public static void printMatrix(int[][] Matrix) {
		System.out.print("\n\t");
		for (int j = 0; j < NoOfNodes; j++) {
			System.out.print(j + "\t");
		}
		System.out.println();
		for (int j = 0; j < 35; j++) {
			System.out.print("-");
		}
		System.out.println();
		for (int i = 0; i < NoOfNodes; i++) {
			System.out.print(i + " |\t");
			for (int j = 0; j < NoOfNodes; j++) {
				System.out.print(Matrix[i][j]);
				System.out.print("\t");
			}
			System.out.println("\n");
		}
		System.out.println("\n");
	}


}
