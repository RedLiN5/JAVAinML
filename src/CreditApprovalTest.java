import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.core.Instances;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * Created by Leslie on 5/5/17.
 */
public class CreditApprovalTest {

    public static BufferedReader readDataFile(String filename) {
        BufferedReader data = null;

        try {
            data = new BufferedReader(new FileReader(filename));
        } catch (FileNotFoundException ex) {
            System.err.println("File not found: " + filename);
        }

        return data;
    }

    public static Evaluation classify(Classifier model,
                                      Instances train,
                                      Instances test) throws Exception {
        Evaluation evaluation = new Evaluation(train);

        model.buildClassifier(train);
        evaluation.evaluateModel(model, test);

        return evaluation;
    }


    public static void main(String[] args) throws Exception {
        BufferedReader data = readDataFile("data/credit_approval_clean.csv");
        String line = null;
        int i = 0;
        try {
            while(((line = data.readLine()) != null) && i <10) {
                System.out.println(line);
                i++;

            }
        }
        finally {
            data.close();
        }
    }
}
