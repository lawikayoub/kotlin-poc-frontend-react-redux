package nl.lawik.poc.frontend.reactredux.containers

import nl.lawik.poc.frontend.reactredux.actions.DeleteTodo
import nl.lawik.poc.frontend.reactredux.actions.EditTodo
import nl.lawik.poc.frontend.reactredux.actions.ToggleTodo
import nl.lawik.poc.frontend.reactredux.components.TodoList
import nl.lawik.poc.frontend.reactredux.components.TodoListProps
import nl.lawik.poc.frontend.reactredux.entities.Todo
import nl.lawik.poc.frontend.reactredux.enums.VisibilityFilter
import nl.lawik.poc.frontend.reactredux.reducers.State
import react.ComponentClass
import react.RProps
import react.invoke
import react.redux.rConnect
import redux.RAction
import redux.WrapperAction

private fun getVisibleTodos(todos: Array<Todo>, filter: VisibilityFilter): Array<Todo> = when (filter) {
    VisibilityFilter.SHOW_ALL -> todos
    VisibilityFilter.SHOW_ACTIVE -> todos.filter { !it.completed }.toTypedArray()
    VisibilityFilter.SHOW_COMPLETED -> todos.filter { it.completed }.toTypedArray()
}

private external interface TodoListStateProps : RProps {
    var todos: Array<Todo>
}

private external interface TodoListDispatchProps : RProps {
    var toggleTodo: (Int) -> Unit
    var deleteTodo: (Int) -> Unit
    var updateTodo: (Int, String) -> Unit
}

val visibleTodoList: ComponentClass<RProps> =
    rConnect<State, RAction, WrapperAction, RProps, TodoListStateProps, TodoListDispatchProps, TodoListProps>(
        { state, _ ->
            todos = getVisibleTodos(state.todos, state.visibilityFilter)
        },
        { dispatch, _ ->
            toggleTodo = { dispatch(ToggleTodo(it)) }
            deleteTodo = { dispatch(DeleteTodo(it)) }
            updateTodo = { id, newText -> dispatch(EditTodo(id, newText)) }
        }
    )(TodoList::class.js.unsafeCast<ComponentClass<TodoListProps>>())