package presenters.components;

import domain.image.GrayScaleMatrix;
import domain.image.Image;

import java.util.function.Function;

public class TextInput {
    private String label;
    private String value;
    private Function<Image, GrayScaleMatrix> processingTask;

    public TextInput(String label, String value, Function<Image, GrayScaleMatrix> processingTask) {
        this.label = label;
        this.value = value;
        this.processingTask = processingTask;
    }

    public String getLabel() {
        return label;
    }

    public String getValue() {
        return value;
    }

    public Function<Image, GrayScaleMatrix> getProcessingTask() {
        return processingTask;
    }
}
