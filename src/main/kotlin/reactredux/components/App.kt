package reactredux.components

import reactredux.pages.toDoListPage
import react.RBuilder
import react.RProps
import react.dom.div
import react.dom.h1
import react.router.dom.browserRouter
import react.router.dom.navLink
import react.router.dom.route
import react.router.dom.switch

private const val TODO_LIST_PATH = "/todolist"

fun RBuilder.app() =
    browserRouter {
        switch {
            route("/", exact = true) {
                div {
                    h1 {
                        +"Kotlin React + React-Dom + Redux + React-Redux + React-Router Example"
                    }
                    navLink<RProps>(TODO_LIST_PATH) {
                        +"Go to todo list"
                    }
                }
            }
            route(TODO_LIST_PATH) {
                toDoListPage()
            }
        }
    }


