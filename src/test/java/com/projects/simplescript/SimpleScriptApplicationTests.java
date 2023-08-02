package com.projects.simplescript;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.projects.simplescript.services.AhpService;

@SpringBootTest
class SimpleScriptApplicationTests {

	@Autowired
	private AhpService ahpService;

	@Test
	void contextLoads() {
		// 
        String[] criteria = {"Biaya Pendidikan", "Pembelian Rumah", "Perbaikan Rumah",
                             "Pembayaran Tepat Waktu", "Catatan Pinjaman", "Tanggungan Anak"};
        String[] alternatives = {"Maria", "Agus", "Marni"};

		double[][] pairwiseComparisonMatrix = new double[criteria.length][criteria.length];
        
        pairwiseComparisonMatrix[0][0] = 1;
        pairwiseComparisonMatrix[0][1] = 3;
        pairwiseComparisonMatrix[0][2] = 5;
        pairwiseComparisonMatrix[0][3] = 7;
        pairwiseComparisonMatrix[0][4] = 7;
        pairwiseComparisonMatrix[0][5] = 7;
        
        pairwiseComparisonMatrix[1][0] = 0.333;
        pairwiseComparisonMatrix[1][1] = 1;
        pairwiseComparisonMatrix[1][2] = 3;
        pairwiseComparisonMatrix[1][3] = 5;
        pairwiseComparisonMatrix[1][4] = 7;
        pairwiseComparisonMatrix[1][5] = 7;
        
        pairwiseComparisonMatrix[2][0] = 0.2;
        pairwiseComparisonMatrix[2][1] = 0.333;
        pairwiseComparisonMatrix[2][2] = 1;
        pairwiseComparisonMatrix[2][3] = 5;
        pairwiseComparisonMatrix[2][4] = 7;
        pairwiseComparisonMatrix[2][5] = 7;
        
        pairwiseComparisonMatrix[3][0] = 0.143;
        pairwiseComparisonMatrix[3][1] = 0.2;
        pairwiseComparisonMatrix[3][2] = 0.2;
        pairwiseComparisonMatrix[3][3] = 1;
        pairwiseComparisonMatrix[3][4] = 7;
        pairwiseComparisonMatrix[3][5] = 7;

        pairwiseComparisonMatrix[4][0] = 0.143;
        pairwiseComparisonMatrix[4][1] = 0.2;
        pairwiseComparisonMatrix[4][2] = 0.2;
        pairwiseComparisonMatrix[4][3] = 1;
        pairwiseComparisonMatrix[4][4] = 7;
        pairwiseComparisonMatrix[4][5] = 7;

        pairwiseComparisonMatrix[5][0] = 0.143;
        pairwiseComparisonMatrix[5][1] = 0.2;
        pairwiseComparisonMatrix[5][2] = 0.2;
        pairwiseComparisonMatrix[5][3] = 1;
        pairwiseComparisonMatrix[5][4] = 7;
        pairwiseComparisonMatrix[5][5] = 7;

        for(double[] d : pairwiseComparisonMatrix){
            for(double j : d){
                System.out.format("%.3f ",j);
            }
            System.out.println("");
        }

        double[] criteriaWeights = ahpService.calculatePriorityVector(pairwiseComparisonMatrix);
        double[][] normalizedMatrix = ahpService.normalizeMatrix(pairwiseComparisonMatrix);
        double[] alternativeWeights = ahpService.calculatePriorityVector(normalizedMatrix);

        // Print results
        System.out.println("Criteria Weights: " + java.util.Arrays.toString(criteriaWeights));
        for (int i = 0; i < alternatives.length; i++) {
            System.out.println("Weight for " + alternatives[i] + ": " + alternativeWeights[i]);
        }
    }
}

