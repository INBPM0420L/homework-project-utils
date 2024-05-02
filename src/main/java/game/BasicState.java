package game;

/**
 * Represents the state of a game whose moves are described by a single object.
 * From a user interface-centric viewpoint, it is suitable for games whose moves
 * can be specified with a single selection, such as a single mouse click.
 *
 * @param <T> represents the moves that can be applied to the states
 */
public interface BasicState<T> extends State {

    /**
     * {@return whether the move provided can be applied to the state}
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

}
