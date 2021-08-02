package nl.lawik.poc.frontend.reactredux.pages

import nl.lawik.poc.frontend.reactredux.components.filters
import nl.lawik.poc.frontend.reactredux.containers.addTodo
import nl.lawik.poc.frontend.reactredux.containers.visibleTodoList
import react.RBuilder
import react.RProps
import react.dom.br
import react.router.dom.navLink
import ringui.*

fun RBuilder.toDoListPage() {
    Grid {
        Row {
            attrs { center = RowPosition.xs }

            Col {
                attrs {
                    xs = 10
                    md = 8
                    lg = 5
                }

                Island {
                    IslandHeader {
                        attrs.border = true

                        Heading {
                            +"Todo app sample"
                        }
                    }
                    IslandContent {
                        addTodo {}
                        filters()
                        visibleTodoList {}
                        br {}
                        navLink<RProps>("/") {
                            +"Go back"
                        }
                    }
                }
            }
        }
    }
}