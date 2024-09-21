package wsb2023.pogorzelski.models;

public enum Priority {
    LOW("lowBtn"), MINOR("lowBtn"), NORMAL("normalBtn"),HIGH("highBtn"), MAJOR("highBtn");

    private String button;
    Priority(String button) {
        this.button = button;
    }

    public String getButton() {
        return this.button;
    }
}
