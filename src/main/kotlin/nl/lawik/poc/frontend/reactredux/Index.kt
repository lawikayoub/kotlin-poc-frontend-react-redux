package nl.lawik.poc.frontend.reactredux

import nl.lawik.poc.frontend.reactredux.components.app
import nl.lawik.poc.frontend.reactredux.reducers.State
import nl.lawik.poc.frontend.reactredux.reducers.combinedReducers
import react.dom.render
import react.redux.provider
import redux.RAction
import redux.compose
import redux.createStore
import redux.rEnhancer
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