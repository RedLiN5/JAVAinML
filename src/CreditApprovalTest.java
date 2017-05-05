import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.classifiers.bayes.NaiveBayes;
import weka.classifiers.functions.Logistic;
import weka.classifiers.meta.AdaBoostM1;
import weka.classifiers.pmml.consumer.SupportVectorMachineModel;
import weka.core.Instances;
import weka.classifiers.trees.J48;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

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
        BufferedReader dataFile = readDataFile("data/credit_approval_clean.csv");
        String line = null;
        int i = 0;
        try {
            while(((line = dataFile.readLine()) != null) && i <10) {
                System.out.println(line);
                i++;
            }
        }
        finally {
            dataFile.close();
        }

        Instances data = new Instances(dataFile);
        data.setClassIndex(data.numAttributes() - 1);

        int trainSize = (int) Math.round(data.numInstances() * 0.75);
        int testSize = data.numInstances() - trainSize;

        Instances train = new Instances(data, 0, trainSize);
        Instances test = new Instances(data, trainSize, testSize);

        Classifier[] models = {
                new J48(),
                new NaiveBayes(),
                new Logistic(),
                new AdaBoostM1()
        };

        for (int j = 0; j < models.length; j++) {
            ArrayList predictions = new ArrayList();

            Evaluation validation = classify(models[j], train, test);

            predictions.add(validation.predictions());

        }
    }
}
