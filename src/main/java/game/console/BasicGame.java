package game.console;

import game.BasicState;

import java.util.function.Function;

/**
 * Conducts a two-player game of the basic type on the console.
 *
 * @param <T> represents the moves that can be applied to the states
 */
public class BasicGame<T> extends Game<BasicState<T>, T> {

    /**
     * Creates a {@code BasicGame} instance to conduct a two-player game of the
     * basic type on the console.
     *
     * @param state the state from which the game is started
     * @param parser a function that converts a line read from the console to a
     *               move
     * @throws AssertionError if the console is not available
     */
    public BasicGame(BasicState<T> state, Function<String, T> parser) {
        super(state, parser);
    }

    @Override
    protected void makeMoveIfPossible(T move) {
        if (state.isLegalMove(move)) {
            state.makeMove(move);
            printState();
        }
    }

}
