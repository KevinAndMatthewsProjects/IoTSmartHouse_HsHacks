import org.neuroph.core.*;
import org.neuroph.core.data.DataSet;
import org.neuroph.core.data.DataSetRow;
import org.neuroph.nnet.Perceptron;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by matthew on 3/26/17.
 */
public class Neural {

    private DataSet trainingSet;
    private NeuralNetwork neuralNetwork;

    public Neural(int inSize, int outSize) {
        neuralNetwork = new Perceptron(inSize, outSize);
        trainingSet = new DataSet(inSize, outSize);
        System.out.println("Neural Network Created");
    }

    public void addData(double[] in, double[] out) {
        trainingSet.add(new DataSetRow(in, out));
        System.out.println("Starting trainging");
       // neuralNetwork.learn(trainingSet);
        System.out.println("Done training");
    }


    public double[] calculate(double[] in) {
        neuralNetwork.setInput(in);
        neuralNetwork.calculate();
        return neuralNetwork.getOutput();
    }



}
