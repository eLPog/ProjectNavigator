package wsb2023.pogorzelski.models;

public enum Type {
    FEATURE("featureTypeBtn"), BUG("bugTypeBtn"), WISH("wishTypeBtn");

    private String button;
    Type (String button) {
        this.button = button;
    }

    public String getButton() {
        return this.button;
    }
}
