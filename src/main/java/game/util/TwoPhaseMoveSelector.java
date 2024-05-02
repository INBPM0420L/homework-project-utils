package game.util;

import game.TwoPhaseMoveState;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.ReadOnlyObjectWrapper;

/**
 * Utility class to determine the move of the next player in two-phase move type
 * games. It serves to select a source and a target that together make up a move.
 *
 * @param <T> represents the moves that can be applied to the states
 */
public class TwoPhaseMoveSelector<T> {

    /**
     * Represents the current selection phase.
     */
    public enum Phase {
        SELECT_FROM,
        SELECT_TO,
        READY_TO_MOVE

    }

    private final TwoPhaseMoveState<T> state;
    private final ReadOnlyObjectWrapper<Phase> phase = new ReadOnlyObjectWrapper<>(Phase.SELECT_FROM);
    private boolean invalidSelection = false;
    private T from;
    private T to;

    /**
     * Creates a {@code TwoPhaseMoveSelector} object to determine the move of
     * the next player in the state specified.
     *
     * @param state the state in which the next move is to be made
     */
    public TwoPhaseMoveSelector(TwoPhaseMoveState<T> state) {
        this.state = state;
    }

    /**
     * {@return the current selection phase}
     */
    public Phase getPhase() {
        return phase.get();
    }

    /**
     * Represents the current selection phase.
     */
    public ReadOnlyObjectProperty<Phase> phaseProperty() {
        return phase.getReadOnlyProperty();
    }

    /**
     * {@return whether the move is ready to be made}
     */
    public boolean isReadyToMove() {
        return phase.get() == Phase.READY_TO_MOVE;
    }

    /**
     * Selects the action specified to be either the source or the target of the
     * move, respectively. However, no selection made if the selection is
     * invalid.
     *
     * @param action the action to be selected
     */
    public void select(T action) {
        switch (phase.get()) {
            case SELECT_FROM -> selectFrom(action);
            case SELECT_TO -> selectTo(action);
            case READY_TO_MOVE -> throw new IllegalStateException();
        }
    }

    private void selectFrom(T from) {
        if (state.isLegalToMoveFrom(from)) {
            this.from = from;
            phase.set(Phase.SELECT_TO);
            invalidSelection = false;
        } else {
            invalidSelection = true;
        }
    }

    private void selectTo(T to) {
        if (state.isLegalMove(from, to)) {
            this.to = to;
            phase.set(Phase.READY_TO_MOVE);
            invalidSelection = false;
        } else {
            invalidSelection = true;
        }
    }

    /**
     * {@return the source selected} If the move is not yet ready to be made,
     * then an {@link IllegalStateException} is thrown.
     */
    public T getFrom() {
        if (phase.get() == Phase.SELECT_FROM) {
            throw new IllegalStateException();
        }
        return from;
    }

    /**
     * {@return the target selected} If the move is not yet ready to be made,
     * then an {@link IllegalStateException} is thrown.
     */
    public T getTo() {
        if (phase.get() == Phase.SELECT_FROM || phase.get() == Phase.SELECT_TO) {
            throw new IllegalStateException();
        }
        return to;
    }

    /**
     * {@return whether the last selection (i.e, for the source or the target,
     * respectively) was invalid}
     */
    public boolean isInvalidSelection() {
        return invalidSelection;
    }

    /**
     * Makes the move selected. If the move is not yet ready to be made, then an
     * {@link IllegalStateException} is thrown.
     */
    public void makeMove() {
        if (phase.get() != Phase.READY_TO_MOVE) {
            throw new IllegalStateException();
        }
        state.makeMove(from, to);
        reset();
    }

    /**
     * Resets the selection, i.e., resets both the source and the target
     * selected.
     */
    public void reset() {
        from = null;
        to = null;
        phase.set(Phase.SELECT_FROM);
        invalidSelection = false;
    }

}
