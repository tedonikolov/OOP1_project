package bg.tu_varna.sit;

import java.util.Objects;

/**
 * The Symbol class represent a single symbol from the alphabet of the automation.
 * @author Teodor
 * @version 1.0
 */
public class Symbol {
    /**
     * Property "name" for storing the state name.
     */
    private String name;

    /**
     * Empty constructor for the usage of JavaBean xml tools.
     */
    public Symbol() {
    }

    /**
     * Explicit value constructor for Symbol.
     * @param symbol stores the symbol name.
     */
    public Symbol(String symbol) {
        this.name = symbol;
    }

    /**
     * Getter for property "name".
     *
     * @return the name of the Symbol.
     */
    public String getName() {
        return name;
    }

    /**
     * Setter for property "name".
     *
     * @param name stores the symbol name.
     */
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Symbol symbol = (Symbol) o;
        return Objects.equals(name, symbol.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return name;
    }
}
