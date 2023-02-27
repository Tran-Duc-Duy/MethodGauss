package com.duy.math;

import static java.lang.Math.abs;

public class MethodGauss {
    private static int[][] indexMass;

    public static void setIndexMass(int size) {
        indexMass = new int[size][size+1];
    }

    public static double getDeterminantOfMatrix(double[][] matrix2)
    {
        double det = 1;
        int size = matrix2.length;
        for(int i = 0; i < size; i++)
            det *= matrix2[i][i];

        return det;
    }

    public static double[] getResult(double[][] matrix)
    {
        int lengthOfMatrix = matrix.length;
        double s = 0;
        double[] result = new double[lengthOfMatrix];

        result[lengthOfMatrix-1] = matrix[lengthOfMatrix-1][lengthOfMatrix] / matrix[lengthOfMatrix-1][lengthOfMatrix-1];
        for (int i = lengthOfMatrix - 2 ; i >= 0 ; i --)
        {
            s = 0;
            for (int k = i + 1 ; k < lengthOfMatrix; k++)
            {
                s = s + matrix[i][k] * result[k];
                result[i] = ( matrix[i][lengthOfMatrix]- s ) / matrix[i][i];
            }
        }
        return result;
    }

    public static double[] getDiscrepancyNew(double[][] matrix, double[] x)
    {
        int lengthOfMatrix = matrix.length;
        double[] dis = new double[lengthOfMatrix];

        for(int i = 0; i < lengthOfMatrix; i++){
            double r = matrix[i][lengthOfMatrix];
            for(int j = 0; j < lengthOfMatrix; j++){
                r -= matrix[i][j]*x[j];
            }
            dis[i] = r;
        }
        return dis;
    }

    public static void printMatrix(double[][] matrix)
    {
        int lengthOfMatrix = matrix.length;
        for (int i = 0 ; i < lengthOfMatrix ; i++ )
        {
            for (int j = 0 ; j <= lengthOfMatrix; j++ ){
                System.out.printf("%.2f\t", (double)Math.round(matrix[i][j] * 1000) / 1000);
            }
            System.out.println();
        }
        System.out.println();
    }

    public static double[][] getTriangleNew(double[][] matrix)
    {
        int lengthOfMatrix = matrix.length;
        for (int k = 0 ; k < (lengthOfMatrix - 1) ; k++)
            for ( int i = k + 1 ; i < lengthOfMatrix; i++) {
                if (matrix[k][k] != 0) {
                    double c = matrix[i][k] / matrix[k][k];
                    for (int j = 0; j <= lengthOfMatrix; j++) {
                        matrix[i][j] = matrix[i][j] - (matrix[k][j] * c);
                    }
                }
            }
        if (matrix[lengthOfMatrix-1][lengthOfMatrix-1] == 0 )
        {
            if ( matrix[lengthOfMatrix-1][lengthOfMatrix] == 0 )
                return null;
        }
        return matrix;
    }
}