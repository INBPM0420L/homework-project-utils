package game.console;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;

import de.audioattack.io.Console;
import de.audioattack.io.ConsoleCreator;

import game.State;

/**
 * Conducts a two-player game on the console. It uses a {@link Function} to
 * convert a line read from the console to a move. If the line contains
 * invalid input, the functional method of the {@link Function} should throw an
 * {@link IllegalArgumentException}.
 *
 * <p>For example, to convert two space-separated integers to a {@code Position}
 * object wrapping a row and a column index the following {@code Function}
 * object can be used:
 * {@snippet :
 var parser = new Function<String, Position>() {
     @Override
     public Position apply(String s) {
         s = s.trim();
         if (!s.matches("\\d+\\s+\\d+")) {
             throw new IllegalArgumentException();
         }
         var scanner = new Scanner(s);
         return new Position(scanner.nextInt(), scanner.nextInt());
     }
 };
 * }
 *
 * @param <S> represents the states of the game
 * @param <T> represents the moves that can be applied to the states
 */
public abstract class Game<S extends State,T> {

    protected final Console console;
    protected final S state;
    protected final Function<String, T> parser;

    /**
     * Creates a {@code Game} instance to conduct a two-player game on the
     * console.
     *
     * @param state the state from which the game is started
     * @param parser a function that converts a line read from the console to a
     *               move
     */
    public Game(S state, Function<String, T> parser) {
        console = ConsoleCreator.console();
        Objects.requireNonNull(state);
        Objects.requireNonNull(parser);
        this.state = state;
        this.parser = parser;
    }

    /**
     * Starts the game.
     */
    public void start() {
        printState();
        while (!state.isGameOver()) {
            printPrompt();
            try {
                readMove().ifPresentOrElse(
                        this::makeMoveIfPossible,
                        () -> System.exit(0)
                );
            } catch (IllegalArgumentException e) {
                // Invalid input
            }
        }
        printStatus();
    }

    /**
     * Applies the move specified to the state of the game if the move is a
     * legal one. In case of a legal move, it should also print the updated
     * state on the console with the {@link #printState()} method.
     *
     * @param move the move to be made
     */
    protected abstract void makeMoveIfPossible(T move);

    /**
     * Reads the next move to be made from the console.
     *
     * @return the next move to be made, or an empty {@code Optional} if an end
     * of stream has been reached
     */
    protected Optional<T> readMove() {
        return Optional.ofNullable(console.readLine())
                .map(parser);
    }

    /**
     * Prints a prompt on the console to read the next move to be made.
     */
    protected void printPrompt() {
        console.format("%s's move: ", state.getNextPlayer());
    }

    /**
     * Prints the state of the game on the console.
     */
    protected void printState() {
        console.format("%s%n", state);
    }

    /**
     * Prints the status of the game on the console.
     */
    protected void printStatus() {
        console.format("%s%n", switch (state.getStatus()) {
            case IN_PROGRESS -> "In progress";
            case PLAYER_1_WINS -> "PLAYER_1 won";
            case PLAYER_2_WINS -> "PLAYER_2 won";
            case DRAW -> "Draw";
        });
    }

}
