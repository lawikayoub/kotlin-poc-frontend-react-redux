package reactredux.components

import reactredux.pages.toDoListPage
import react.RBuilder
import react.dom.div
import react.dom.h1
import react.router.dom.*

private const val TODO_LIST_PATH = "/todolist"

fun RBuilder.app() =
    BrowserRouter {
        Switch {
            Route { // "/", exact = true
                attrs.path = arrayOf("/")
                attrs.exact = true
                div {
                    h1 {
                        +"Kotlin React + React-Dom + Redux + React-Redux + React-Router Example"
                    }
                    NavLink {
                        attrs.to = TODO_LIST_PATH
                        +"Go to todo list"
                    }
                }
            }
            Route {
                attrs.path = arrayOf(TODO_LIST_PATH)
                toDoListPage()
            }
        }
    }


