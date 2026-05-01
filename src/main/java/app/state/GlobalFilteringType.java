package app.state;

public enum GlobalFilteringType {
    IDEAL_LOW_PASS("Filtre passe-bas idéal"),
    IDEAL_HIGH_PASS("Filtre passe-haut idéal"),
    BUTTERWORTH_LOW_PASS("Filtre passe-bas Butterworth"),
    BUTTERWORTH_HIGH_PASS("Filtre passe-haut Butterworth");

    private String displayText;

    private GlobalFilteringType(String displayText) {
        this.displayText = displayText;
    }

    public String getDisplayText() {
        return displayText;
    }
}
