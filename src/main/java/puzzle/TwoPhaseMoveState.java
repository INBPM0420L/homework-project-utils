package puzzle;

/**
 * Represents the state of a puzzle whose moves are described by two objects,
 * i.e., {@code from} and {@code to}. From a user interface-centric viewpoint,
 * it is suitable for puzzles where moves are made from a source location to a
 * target location. Thus, a move is specified in two phases each of which
 * requires a selection, e.g., two subsequent mouse clicks are required.
 *
 * @param <T> represents the type of the source and the target of the moves
 */
public interface TwoPhaseMoveState<T> extends State<TwoPhaseMoveState.TwoPhaseMove<T>> {

    /**
     * {@return whether it is possible to make a move from the argument
     * specified}
     *
     * @param from represents where to move from
     */
    boolean isLegalToMoveFrom(T from);

    TwoPhaseMoveState<T> clone();

    /**
     * Represents moves that are described by two objects, i.e., {@code from}
     * and {@code to}.
     *
     * @param from represents where to move from
     * @param to represents where to move to
     * @param <T> represents the type of the source and the target of the moves
     */
    record TwoPhaseMove<T>(T from, T to) {}

}
