package puzzle.solver;

import puzzle.State;

import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Optional;

/**
 * Implements the breadth-first search (BFS) algorithm to solve puzzles.
 *
 * @param <S> represents the state of a puzzle to be solved
 * @param <T> represents the moves that can be applied to the states
 */
public class BreadthFirstSearch<S extends State<T>, T> {

    /**
     * Searches for the shortest solution for the puzzle starting from the state
     * provided.
     *
     * @param state the initial state
     * @return an {@code Optional} describing the shortest solution for the puzzle,
     * or an empty {@code Optional} if no solution is found
     */
    public Optional<Node<S, T>> solve(S state) {
        Deque<Node<S, T>> open = new LinkedList<Node<S, T>>();
        var seen = new HashSet<Node<S, T>>();
        var start = new Node(state);
        open.add(start);
        seen.add(start);
        while (! open.isEmpty()) {
            var selected = open.pollFirst();
            if (selected.getState().isSolved()) {
                return Optional.of(selected);
            }
            while (selected.hasNextChild()) {
                var nextChild = selected.nextChild().get();
                if (! seen.contains(nextChild)) {
                    open.offerLast(nextChild);
                    seen.add(nextChild);
                }
            }
        }
        return Optional.empty();
    }

    /**
     * Searches for the shortest solution for the puzzle starting from the state
     * provided, and it also prints the solution to the standard output.
     *
     * @param state the initial state
     * @return an {@code Optional} describing the shortest solution for the puzzle,
     * or an empty {@code Optional} if no solution is found
     */
    public Optional<Node<S, T>> solveAndPrintSolution(S state) {
        var solution = solve(state);
        solution.ifPresentOrElse(
                this::printPathTo,
                () -> System.out.println("No solution found")
        );
        return solution;
    }

    private void printPathTo(Node<S, T> node) {
        node.getParent().ifPresent(this::printPathTo);
        System.out.println(node);
    }

}
