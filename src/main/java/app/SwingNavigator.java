package app;

import app.state.GlobalFilteringType;
import com.google.inject.Inject;
import com.google.inject.Provider;
import domain.image.GrayScaleMatrix;
import domain.image.Image;
import domain.image.processing.linear.GlobalLinearFiltering;
import org.jfree.chart.ChartFrame;
import presenters.components.TextInput;
import ui.implementation.views.DoubleMatrix;
import ui.implementation.views.SimpleImageWTextField;

import java.awt.event.ActionEvent;
import java.util.function.Function;

public class SwingNavigator implements INavigator {

    private final Provider<DoubleMatrix> doubleMatrixProvider;
    private final Provider<SimpleImageWTextField> simpleImageWTextFieldProvider;

    @Inject
    public SwingNavigator(
            Provider<DoubleMatrix> doubleMatrixProvider,
            Provider<SimpleImageWTextField> simpleImageWTextFieldProvider
    ) {
        this.doubleMatrixProvider = doubleMatrixProvider;
        this.simpleImageWTextFieldProvider = simpleImageWTextFieldProvider;
    }

    @Override
    public void showFourier() {
        DoubleMatrix doubleMatrix = doubleMatrixProvider.get();
        doubleMatrix.setVisible(true);
    }

    @Override
    public void showFiltreLineaire(GlobalFilteringType type) {
        // ChartFrame frame = new ChartFrame(type.getDisplayText(), );
        SimpleImageWTextField frame = simpleImageWTextFieldProvider.get();
        Function<Image, GrayScaleMatrix> grayScaleMatrixFunction = (Image img) -> {
            Image result;
            int cutoffFrequency;
            try {
                cutoffFrequency = Integer.parseInt(frame.getTextFieldValue(0));
            } catch (NumberFormatException e) {
                return img.toGrayScale();
            }
            int order;
            try {
                order = Integer.parseInt(frame.getTextFieldValue(1));
            } catch (NumberFormatException e) {
                order = 0;
            }

            result = switch (type) {
                case GlobalFilteringType.IDEAL_LOW_PASS -> GlobalLinearFiltering.idealLowPassFilter(img, cutoffFrequency);
                case GlobalFilteringType.IDEAL_HIGH_PASS -> GlobalLinearFiltering.idealHighPassFilter(img, cutoffFrequency);
                case GlobalFilteringType.BUTTERWORTH_LOW_PASS -> GlobalLinearFiltering.butterworthLowPassFilter(img, cutoffFrequency, order);
                case GlobalFilteringType.BUTTERWORTH_HIGH_PASS -> GlobalLinearFiltering.butterworthHighPassFilter(img, cutoffFrequency, order);
                default -> img;
            };
            return result.toGrayScale();
        };

        TextInput[] textInputs = new TextInput[2];
        textInputs[0] = new TextInput("Fréquence de coupure", "0", grayScaleMatrixFunction);
        if (type == GlobalFilteringType.BUTTERWORTH_HIGH_PASS ||  type == GlobalFilteringType.BUTTERWORTH_LOW_PASS) {
            textInputs[1] = new TextInput("Ordre", "0", grayScaleMatrixFunction);
        }
        frame.setupComponents(type.getDisplayText(), textInputs);
        frame.setVisible(true);
    }

}
