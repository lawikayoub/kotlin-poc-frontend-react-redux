package reactredux.components

import reactredux.entities.Todo
import react.RBuilder
import react.RComponent
import react.RProps
import react.State
import react.dom.ul

external interface TodoListProps : RProps {
    var todos: Array<Todo>
    var toggleTodo: (Int) -> Unit
    var deleteTodo: (Int) -> Unit
    var updateTodo: (Int, String) -> Unit
}

@JsExport
class TodoList(props: TodoListProps) : RComponent<TodoListProps, State>(props) {
    override fun RBuilder.render() {
        ul {
            props.todos.forEach {
                todo(
                    todo = it,
                    onClick = { props.toggleTodo(it.id) },
                    onDelete = { props.deleteTodo(it.id) },
                    onUpdate = { newText -> props.updateTodo(it.id, newText) }
                )
            }
        }
    }
}