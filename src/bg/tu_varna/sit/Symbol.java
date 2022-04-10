package bg.tu_varna.sit;

public class Symbol {
    private String name;

    public Symbol(String symbol) {
        this.name = symbol;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
