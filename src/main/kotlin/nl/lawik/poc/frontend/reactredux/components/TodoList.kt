package nl.lawik.poc.frontend.reactredux.components

import nl.lawik.poc.frontend.reactredux.entities.Todo
import react.*
import react.dom.ul

external interface TodoListProps : RProps {
    var todos: Array<Todo>
    var toggleTodo: (Int) -> Unit
}

@JsExport
class TodoList(props: TodoListProps) : RComponent<TodoListProps, State>(props) {
    override fun RBuilder.render() {
        ul {
            props.todos.forEach { todo(it) { props.toggleTodo(it.id) } }
        }
    }
}