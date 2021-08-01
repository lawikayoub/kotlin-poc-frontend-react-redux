package nl.lawik.poc.frontend.reactredux.containers

import kotlinx.html.ButtonType
import kotlinx.html.js.onSubmitFunction
import nl.lawik.poc.frontend.reactredux.actions.AddTodo
import nl.lawik.poc.frontend.reactredux.ringui.Input
import nl.lawik.poc.frontend.reactredux.store
import org.w3c.dom.HTMLInputElement
import react.*
import react.dom.div
import react.dom.form
import react.redux.rConnect
import redux.WrapperAction
import ringui.*

@JsExport
class AddTodo(props: RProps) : RComponent<RProps, State>(props) {
    private val inputRef = createRef<HTMLInputElement>()
    override fun RBuilder.render() {
        div {
            form {
                attrs.onSubmitFunction = { event ->
                    event.preventDefault()
                    inputRef.current!!.let {
                        if (it.value.trim().isNotEmpty()) {
                            store.dispatch(AddTodo(it.value))
                            it.value = ""
                        }
                    }
                }
                Row {
                    attrs.baseline = RowPosition.xs
                    attrs.center = RowPosition.xs
                    Col {
                        Input {
                            attrs.size = "L"
                            attrs.inputRef = inputRef
                            attrs.label = "Task name"
                        }
                    }
                    Col {
                        Button {
                            attrs.asDynamic().type = ButtonType.submit
                            +"Add Todo"
                        }
                    }
                }
            }
        }
    }
}


val addTodo: ComponentClass<RProps> =
    rConnect<AddTodo, WrapperAction>()(nl.lawik.poc.frontend.reactredux.containers.AddTodo::class.js.unsafeCast<ComponentClass<RProps>>())