package nl.lawik.poc.frontend.reactredux.containers

import kotlinx.html.ButtonType
import kotlinx.html.InputType
import kotlinx.html.js.onSubmitFunction
import nl.lawik.poc.frontend.reactredux.actions.AddTodo
import nl.lawik.poc.frontend.reactredux.store
import org.w3c.dom.HTMLInputElement
import react.*
import react.dom.button
import react.dom.div
import react.dom.form
import react.dom.input
import react.redux.rConnect
import redux.WrapperAction


class AddTodo(props: RProps) : RComponent<RProps, RState>(props) {
    private lateinit var input: HTMLInputElement
    override fun RBuilder.render() {
        div {
            form {
                attrs.onSubmitFunction = {
                    it.preventDefault()
                    if (input.value.trim().isNotEmpty()) {
                        store.dispatch(AddTodo(input.value))
                        input.value = ""
                    }
                }
                input(type = InputType.text) {
                    ref { input = it as HTMLInputElement }
                }
                button(type = ButtonType.submit) {
                    +"Add Todo"
                }
            }
        }
    }
}


val addTodo: RClass<RProps> =
    rConnect<AddTodo, WrapperAction>()(nl.lawik.poc.frontend.reactredux.containers.AddTodo::class.js.unsafeCast<RClass<RProps>>())