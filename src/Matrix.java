import java.lang.reflect.Array;
import java.util.Arrays;
public class Matrix {
    private final double[][] matrix;
    //rows - matrix.length
    //columns - matrix[0].length

    public Matrix(double[][] matrix) {
        this.matrix = matrix;
    }
    public String matToStr(){
        //System.out.println(Arrays.deepToString(matrix));
        StringBuilder str = new StringBuilder();
        for (double[] ints : matrix) { //this equals to the row in our matrix.
            for (double anInt : ints) { //this equals to the column in each row.
                str.append(anInt).append(" ");
            }
            str.append(System.lineSeparator()); //change line on console as row comes to end in the matrix.
        }
        return str.toString();
    }

    public double[][] transpose(){
        double[][] trans = new double[matrix[0].length][matrix.length];
        for(int i = 0; i < matrix.length; i++){
            for (int j = 0; j < matrix[0].length; j++){
                trans[j][i] = matrix[i][j];
            }
        }
        return trans;
    }

    public double[][] GaussianElimination(){
        double divisor;
        int leadCoef = 0;

        for (int j = 0; j < matrix[0].length; j++){
            int i = leadCoef;
            while (i < matrix.length && matrix[i][j] == 0)
                i++;
            if (i != matrix.length){
                double lineDiv = matrix[i][j];
                for (int l = 0; l < matrix[0].length; l++){
                    matrix[i][l] /= lineDiv;
                }
                for (int k = 0; k < matrix.length; k++){
                    if(k == i){
                        continue;
                    }
                    divisor = matrix[k][j]/matrix[i][j];
//                    System.out.println(divisor);
                    for (int r = 0; r < matrix[0].length; r++){
                        matrix[k][r] -= divisor*matrix[i][r];
                    }

                }

                double[] temp;
                temp = matrix[leadCoef];
                matrix[leadCoef] = matrix[i];
                matrix[i] = temp;
                leadCoef++;

            }
        }
        for (int i = 0; i < matrix.length; i++){
            for (int j = 0; j < matrix[0].length; j++){
                if (matrix[i][j] == -0.0)
                    matrix[i][j] = 0.0;
            }
        }

        return matrix;
    }

    public double det(){
        if (matrix.length != matrix[0].length)
            return Double.POSITIVE_INFINITY;
        double divisor, det = 1;
        int leadCoef = 0, switchCount = 0;

        for (int j = 0; j < matrix[0].length; j++){
            int i = leadCoef;
            while (i < matrix.length && matrix[i][j] == 0)
                i++;
            if (i != matrix.length){
                for (int k = 0; k < matrix.length; k++){
                    if(k == i){
                        continue;
                    }
                    divisor = matrix[k][j]/matrix[i][j];
                    for (int r = 0; r < matrix[0].length; r++){
                        matrix[k][r] -= divisor*matrix[i][r];
                    }

                }
                if (i != leadCoef)
                    switchCount++;

                double[] temp;
                temp = matrix[leadCoef];
                matrix[leadCoef] = matrix[i];
                matrix[i] = temp;
                leadCoef++;

            }
        }
        for (int i = 0; i < matrix.length; i++){
            for (int j = 0; j < matrix[0].length; j++){
                if (matrix[i][j] == -0.0)
                    matrix[i][j] = 0.0;
            }
        }

        for (int i = 0; i < matrix.length; i++)
            det *= matrix[i][i];

        return Math.pow(-1, switchCount) * det;
    }



}
