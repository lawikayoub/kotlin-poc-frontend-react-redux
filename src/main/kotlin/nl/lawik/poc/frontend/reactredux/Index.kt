package nl.lawik.poc.frontend.reactredux

import nl.lawik.poc.frontend.reactredux.components.*
import nl.lawik.poc.frontend.reactredux.reducers.*
import react.dom.*
import react.redux.provider
import redux.*
import kotlin.browser.document

val store = createStore<State, RAction, dynamic>(
    combinedReducers(), State(), compose(
        rEnhancer(),
        js("if(window.__REDUX_DEVTOOLS_EXTENSION__ )window.__REDUX_DEVTOOLS_EXTENSION__ ();else(function(f){return f;});")
    )
)

fun main() {
    val rootDiv = document.getElementById("root")
    render(rootDiv) {
        provider(store) {
            app()
        }
    }
}