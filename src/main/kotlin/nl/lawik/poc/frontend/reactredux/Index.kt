package nl.lawik.poc.frontend.reactredux

import nl.lawik.poc.frontend.reactredux.components.app
import nl.lawik.poc.frontend.reactredux.reducers.State
import react.dom.render
import react.redux.provider
import redux.createStore
import redux.rEnhancer
import kotlinx.browser.document
import nl.lawik.poc.frontend.reactredux.reducers.combinedReducers
import nl.lawik.poc.frontend.reactredux.reducers.rootReducer

val store = createStore(::rootReducer, State(), rEnhancer())

fun main() {
    val rootDiv = document.getElementById("root")
    render(rootDiv) {
        provider(store) {
            app()
        }
    }
}