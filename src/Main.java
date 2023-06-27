import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        /*System.out.println("Enter nxm matrix ({1, 1, 1},{2, 2, 2} or 1,1,1;2,2,2):");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();*/
        double[][] mat = inputToMat("2,3,-9,4;6,4,-8,-17;-1,-4,-3,5;2,9,-9,0");

        Matrix matrix = new Matrix(mat);
        System.out.println(matrix.matToStr());

        Matrix gauss = new Matrix(matrix.GaussianElimination());
        System.out.println(gauss.matToStr());

        System.out.println(matrix.det());


        Matrix transpose = new Matrix(matrix.transpose());
        System.out.println(transpose.matToStr());

    }

    private static double[][] inputToMat(String input) {
        if(input.contains("{")){
            String subStrRows = input.substring(input.indexOf("{") + 1, input.indexOf("}"));

            double[][] mat =
               new double[input.length() - input.replaceAll("}","").length()]
                       [subStrRows.length() - subStrRows.replaceAll(",","").length() + 1];

            String subStrNum = input.substring(1);
            for (int i = 0; i < mat.length; i++) { //this equals to the row in our matrix.
                for (int j = 0; j < mat[0].length; j++) { //this equals to the column in each row.
                    mat[i][j] = Integer.parseInt(subStrNum.substring(0, 1));
                    if (j != mat[0].length - 1)
                        subStrNum = subStrNum.substring(3);
                }
                if(i < mat.length - 1)
                    subStrNum = subStrNum.substring(4);
            }
            return mat;
        }

        else {
            String subStrRows = input.substring(0, input.indexOf(";"));

            double[][] mat =
                    new double[input.length() - input.replaceAll(";","").length() + 1]
                            [subStrRows.length() - subStrRows.replaceAll(",","").length() + 1];
            int index = 0;
            for (int i = 0; i < mat.length; i++) {
                for (int j = 0; j < mat[0].length; j++) {
                    if(i == mat.length - 1 && j == mat[0].length - 1)
                        index = input.length();
                    else if(j == mat[0].length - 1)
                        index = input.indexOf(";");
                    else
                        index = input.indexOf(",");
                    mat[i][j] = Double.parseDouble(input.substring(0, index));
                    if (j != mat[0].length - 1)
                        input = input.substring(index+1);
                }
                if(i < mat.length - 1)
                    input = input.substring(index+1);
            }
            return mat;
        }
    }

}