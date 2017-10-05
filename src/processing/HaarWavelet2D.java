/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package processing;

/**
 *
 * @author Rico
 */
public class HaarWavelet2D {

    double[][] matrix;

    public HaarWavelet2D(double[][] data) {
        matrix = data;
    }

    /*
	
	
	public void transpose(){
		double[][] transposed = new double[matrix[0].length][matrix.length];
		for (int i = 0 ; i < matrix.length ; i++){
			for (int j = 0 ; j < matrix[0].length ; j++){
				transposed[j][i] = matrix[i][j];
			}
		}
		
		matrix = transposed;
	}
	
	public void standardDecomposition(){
		processRows();
		transpose();
		processRows();
		transpose();
	}
      /*  public void inverseDecomposition(){
		transpose();
                processRowsInv();
                transpose();
		processRowsInv();
		
	}*/

 /*     
	
	public void processRows(){
		int size = matrix.length;
		for (int i = 0 ; i < size ; i++){
			matrix[i] = decompositionRow(matrix[i]);
		}
	}
	
	public double[] decompositionRow(double[] row){
		int steps = row.length;
		while (steps > 1){
			row = decompositionStep(steps, row);
			steps = steps / 2;
		}
		return row;
	}
	
	public double[] decompositionStep(int step, double[] row){
		double[] newRow = new double[row.length];
		System.arraycopy( row, 0, newRow, 0, row.length );
		
		for (int i=0 ; i < step/2 ; i++){
			newRow[i] = (row[2*i + 1] + row[2*i])/ 2;
			newRow[step/2 + i] = (row[(2*i)] - row[(2*i + 1)])/2;	
		}
		
		return newRow;
	}
     */
 /*
        public void processRowsInv(){
		int size = matrix.length;
		for (int i = 0 ; i < size ; i++){
			matrix[i] = decompositionRowInv(matrix[i]);
		}
	}
	
	public double[] decompositionRowInv(double[] row){
		int steps = row.length;
		while (steps > 1){
			row = decompositionStepInv(steps, row);
			steps = steps / 2;
		}
		return row;
	}
	
	public double[] decompositionStepInv(int step, double[] row){
		double[] newRow = new double[row.length];
		System.arraycopy( row, 0, newRow, 0, row.length );
		
		for (int i=0 ; i < (step/2)-2; i++){
			//a1
                        newRow[i] = (row[2*i + 2] + row[2*i]);
                        //d1
			newRow[step/2 + i] = (row[(2*i + 1)] - row[(2*i + 3)]);	
		}
		
		return newRow;
	}
        
     */
    public void inverseDecomposition() {
        int w = matrix[0].length;
        int h = matrix.length;
        int[][] result = new int[1000][1000];
        double[] temp = new double[h + w];
        //column transformation
        for (int i = 0; i < w; i++) {
            int l = h / 4;
            do {
                int k = 0;

                for (int j = 0; j < l; j++) {
                    temp[k++] = matrix[j][i] + matrix[j + l][i];
                    temp[k++] = matrix[j][i] - matrix[j + l][i];
                }
                for (int j = 0; j < k; j++) {
                    matrix[j][i] = temp[j];
                }
                l *= 2;
            } while (l <= h / 2);
        }

        temp = new double[h + w];
        //rows transformation
        for (int i = 0; i < h; i++) {
            int l = w / 4;
            //System.out.println("rowcek: "+l);
            do {
                int k = 0;
                for (int j = 0; j < l; j++) {
                    temp[k++] = matrix[i][j] + matrix[i][j + l];
                    temp[k++] = matrix[i][j] - matrix[i][j + l];
                }
                for (int j = 0; j < k; j++) {
                    matrix[i][j] = temp[j];
                }
                l *= 2;
            } while (l <= w / 2);
        }
    }

    public void standardDecomposition() {
        int w = matrix[0].length;
        int h = matrix.length;
        double temp[] = new double[w];

        int time;
        //rows 
        for (int i = 0; i < h; i++) {
            int l = w;
            do {
                int k = 0;
                for (int j = 0; j < l - 1; j += 2)//averaging
                {
                    temp[k++] = (matrix[i][j] + matrix[i][j + 1]) / 2;
                }
                for (int j = 0; j < l - 1; j += 2) {
                    temp[k++] = (matrix[i][j] - matrix[i][j + 1]) / 2;//difference
                }
                for (int j = 0; j < w; j++) {
                    matrix[i][j] = temp[j];
                }
                l = l / 2;
            } while (l != w / 4);//cek per segment Average 2, difference 2
        }
        temp = new double[h];
        //column transformation
        for (int i = 0; i < w; i++) {
            int l = h;
            do {
                int k = 0;
                //time=0;
                for (int j = 0; j < l - 1; j += 2) {
                    temp[k++] = (matrix[j][i] + matrix[j + 1][i]) / 2;
                }
                for (int j = 0; j < l - 1; j += 2) {
                    temp[k++] = (matrix[j][i] - matrix[j + 1][i]) / 2;
                }
                for (int j = 0; j < h; j++) {
                    matrix[j][i] = temp[j];
                }
                l = l / 2;
            } while (l != h / 4);
        }

    }

    public void show() {
        for (double[] matrix1 : matrix) {
            for (int j = 0; j < matrix1.length; j++) {
                System.out.print(matrix1[j] + ", ");
            }
            System.out.println("");
        }
    }

    public double[][] getMatrix() {
        return matrix;
    }
}
