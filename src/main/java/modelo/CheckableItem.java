package modelo;

public class CheckableItem {
    private final int id;
    private final String text;
    private boolean selected;

    public CheckableItem(int id, String text, boolean selected) {
        this.id = id;
        this.text = text;
        this.selected = selected;
    }

    public int getId() {
        return id;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    @Override
    public String toString() {
        return text;
    }
}