package game;

/**
 * Represents the state of a game whose moves are described by two objects,
 * i.e., {@code from} and {@code to}. From a user interface-centric viewpoint,
 * it is suitable for games where moves are made from a source location to a
 * target location. Thus, a move is specified in two phases each of which
 * requires a selection, e.g., two subsequent mouse clicks are required.
 *
 * @param <T> represents the moves that can be applied to the states
 */
public interface TwoPhaseMoveState<T> extends State {

    /**
     * {@return whether it is possible to make a move from the argument
     * specified}
     *
     * @param from represents where to move from
     */
    boolean isLegalToMoveFrom(T from);

    /**
     * {@return whether the move provided can be applied to the state}
     *
     * @param from represents where to move from
     * @param to represents where to move to
     */
    boolean isLegalMove(T from, T to);

    /**
     * Applies the move provided to the state. This method should be called if
     * and only if {@link #isLegalMove(Object, Object)} returns {@code true}.
     *
     * @param from represents where to move from
     * @param to represents where to move to
     */
    void makeMove(T from, T to);

}
