package bg.tu_varna.sit;

import java.util.Objects;

/**
 * The State class represent a single state from the automation.
 * @author Teodor
 * @version 1.0
 */
public class State {
    /**
     * Property "name" for storing the state name.
     */
    private String name;

    /**
     * Empty constructor for the usage of JavaBean xml tools.
     */
    public State() {
    }

    /**
     * Explicit value constructor for State.
     * @param name stores the name of the state.
     */
    public State(String name) {
        this.name = name;
    }

    /**
     * Getter for property "name".
     *
     * @return the name of the State.
     */
    public String getName() {
        return name;
    }

    /**
     * Setter for property "name".
     *
     * @param name stores the state name.
     */
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        State state = (State) o;
        return Objects.equals(name, state.name);
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
