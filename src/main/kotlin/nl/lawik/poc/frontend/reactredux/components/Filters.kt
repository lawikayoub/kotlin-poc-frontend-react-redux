package nl.lawik.poc.frontend.reactredux.components

import nl.lawik.poc.frontend.reactredux.containers.filterLink
import nl.lawik.poc.frontend.reactredux.enums.VisibilityFilter
import react.RBuilder
import ringui.ButtonGroup

fun RBuilder.filters() =
    ButtonGroup {
        filterLink {
            attrs.filter = VisibilityFilter.SHOW_ALL
            +"All"
        }
        filterLink {
            attrs.filter = VisibilityFilter.SHOW_ACTIVE
            +"Active"
        }
        filterLink {
            attrs.filter = VisibilityFilter.SHOW_COMPLETED
            +"Completed"
        }
    }