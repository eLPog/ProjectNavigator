package wsb2023.pogorzelski.models;

public enum Status {
    TODO("todoBtn"), IN_PROGRESS("inProgressBtn"), BLOCKED("blockedBtn"), DONE("doneBtn");

    private final String button;

    Status(String color) {
        this.button = color;
    }
    public String getButton() {
        return this.button;
    }
}
