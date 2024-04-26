package puzzle.solver;

import puzzle.State;

import java.util.Optional;
import java.util.Set;

/**
 * Represents the nodes of a search graph.
 *
 * @param <S> represents the state of a puzzle to be solved
 * @param <T> represents the moves that can be applied to the states
 */
public class Node<S extends State<T>,T> {

    private S state;
    private Set<T> moves;
    private Optional<Node> parent;
    private Optional<T> move;

    /**
     * Creates a {@code Node} without a parent, i.e., a root node.
     *
     * @param state the state represented by the node
     */
    public Node(S state) {
        this.state = state;
        parent = Optional.empty();
        move = Optional.empty();
        moves = state.getLegalMoves();
    }

    /**
     * Creates a {@code Node} with a parent node.
     *
     * @param state the state represented by the node
     * @param parent the parent of the node
     * @param move the move that created the state from the parent node
     */
    public Node(S state, Node parent, T move) {
        this(state);
        this.parent = Optional.of(parent);
        this.move = Optional.of(move);
    }

    /**
     * {@return the state represented by the node}
     */
    public State getState() {
        return state;
    }

    /**
     * Returns the parent of the node.
     *
     * @return an {@code Optional} describing the parent of the node, or an
     * empty optional if the node does not have a parent
     */
    public Optional<Node> getParent() {
        return parent;
    }

    /**
     * Returns the move that created the state from the state of the parent
     * node.
     *
     * @return an {@code Optional} describing the move that created the state
     * from the parent node, or an empty {@code Optional} if the node does not
     * have a parent
     */
    public Optional<T> getMove() {
        return move;
    }

    /**
     * {@return whether the node has at least one child node to be created with
     * the {@link #nextChild()} method}
     */
    public boolean hasNextChild() {
        return !moves.isEmpty();
    }

    /**
     * Creates and returns the next child of the node by applying a legal move
     * to the state represented by the node. The move applied to the state is
     * removed from the set of legal moves.
     *
     * @return an {@code Optional} describing the next child of the node, or an
     * empty {@code Optional} if there are no more children
     */
    public Optional<Node> nextChild() {
        if (! hasNextChild()) {
            return Optional.empty();
        }
        var iterator = moves.iterator();
        var move = iterator.next();
        iterator.remove();
        var newState = state.clone();
        newState.makeMove(move);
        return Optional.of(new Node(newState, this, move));
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return false;
        }
        return (o instanceof Node other) && state.equals(other.getState());
    }

    @Override
    public int hashCode() {
        return state.hashCode();
    }

    @Override
    public String toString() {
        return move.isPresent() ? String.format("%s %s", move.get(), state) : state.toString();
    }

}
