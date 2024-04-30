package puzzle;

import java.util.Set;

/**
 * Represents the state of a puzzle to be solved.
 *
 * @param <T> represents the moves that can be applied to the states
 */
public interface State<T> extends Cloneable {

    /**
     * {@return whether the puzzle is solved}
     */
    boolean isSolved();

    /**
     * {@return whether the move provided can be applied to the state}
     *
     * @param move represents the move to be made
     */
    boolean isLegalMove(T move);

    /**
     * Applies the move provided to the state. This method should be called if
     * and only if {@link #isLegalMove(Object)} returns {@code true}.
     *
     * @param move represents the move to be made
     */
    void makeMove(T move);

    /**
     * {@return the set of all moves that can be applied to the state}
     */
    Set<T> getLegalMoves();

    /**
     * {@return a copy of the state}
     */
    State<T> clone();

}
