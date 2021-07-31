package nl.lawik.poc.frontend.reactredux.reducers

import nl.lawik.poc.frontend.reactredux.entities.Todo
import nl.lawik.poc.frontend.reactredux.enums.VisibilityFilter
import redux.RAction

data class State(
    val todos: Array<Todo> = emptyArray(),
    val visibilityFilter: VisibilityFilter = VisibilityFilter.SHOW_ALL
)

fun rootReducer(
    state: State,
    action: Any
) = State(
    todos(state.todos, action.unsafeCast<RAction>()),
    visibilityFilter(state.visibilityFilter, action.unsafeCast<RAction>()),
)