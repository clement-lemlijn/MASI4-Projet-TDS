package app;

import com.google.inject.Inject;
import com.google.inject.Provider;
import presenters.DoubleMatrixPresenter;
import ui.implementation.views.DoubleMatrix;

public class SwingNavigatorService implements INavigator {

    private final Provider<DoubleMatrix> doubleMatrixProvider;

    @Inject
    public SwingNavigatorService(
            Provider<DoubleMatrix> doubleMatrixProvider
    ) {
        this.doubleMatrixProvider = doubleMatrixProvider;
    }

    @Override
    public void showFourierModule() {
        DoubleMatrix doubleMatrix = doubleMatrixProvider.get();
        doubleMatrix.setVisible(true);
    }

    @Override
    public void showFourierPhase() {

    }

    @Override
    public void showFourierReal() {

    }

    @Override
    public void showFourierImaginary() {

    }

    @Override
    public void showHistogram() {

    }
}
