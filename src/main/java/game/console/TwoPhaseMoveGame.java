package game.console;

import game.TwoPhaseMoveState;
import game.util.TwoPhaseMoveSelector;

import java.util.function.Function;

/**
 * Conducts a two-player game of the two-phase move type on the console.
 *
 * @param <T> represents the moves that can be applied to the states
 */
public class TwoPhaseMoveGame<T> extends Game<TwoPhaseMoveState<T>, T> {

    private final TwoPhaseMoveSelector<T> selector;

    /**
     * Creates a {@code TwoPhaseMoveGame} instance to conduct a two-player game
     * of the two-phase move type on the console.
     *
     * @param state the state from which the game is started
     * @param parser a function that converts a line read from the console to a
     *               move
     * @throws AssertionError if the console is not available
     */
    public TwoPhaseMoveGame(TwoPhaseMoveState<T> state, Function<String, T> parser) {
        super(state, parser);
        this.selector = new TwoPhaseMoveSelector<>(state);
    }

    protected void makeMoveIfPossible(T move) {
        selector.select(move);
        if (selector.isReadyToMove()) {
            selector.makeMove();
            console.printf("%s%n", state);
        }
    }

    protected void printPrompt() {
        console.format("%s' move [%s]: ", state.getNextPlayer(),
                switch (selector.getPhase()) {
                    case SELECT_FROM -> "from";
                    case SELECT_TO -> "to";
                    case READY_TO_MOVE -> throw new AssertionError();
                });
    }

}
