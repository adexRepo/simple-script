package com.projects.simplescript.services.impl;

import org.apache.commons.math3.linear.EigenDecomposition;
import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;
import org.springframework.stereotype.Service;

import com.projects.simplescript.services.AhpService;

@Service
public class AhpServiceImpl implements AhpService{
    
    @Override
    public  double[] calculatePriorityVector(double[][] matrix) {
        RealMatrix realMatrix = MatrixUtils.createRealMatrix(matrix);
        EigenDecomposition decomposition = new EigenDecomposition(realMatrix);
        double[] eigenvalues = decomposition.getRealEigenvalues();
        double maxEigenvalue = eigenvalues[eigenvalues.length - 1];

        double[] priorityVector = new double[matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            priorityVector[i] = eigenvalues[i] / maxEigenvalue;
        }

        return priorityVector;
    }

    @Override
    public  double[][] normalizeMatrix(double[][] matrix) {
        int n = matrix.length;
        double[][] normalizedMatrix = new double[n][n];
        for (int i = 0; i < n; i++) {
            double sum = 0;
            for (int j = 0; j < n; j++) {
                sum += matrix[j][i];
            }
            for (int j = 0; j < n; j++) {
                normalizedMatrix[j][i] = matrix[j][i] / sum;
            }
        }
        return normalizedMatrix;
    }
}
