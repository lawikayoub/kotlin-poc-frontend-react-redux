package nl.lawik.poc.frontend.reactredux.components

import nl.lawik.poc.frontend.reactredux.containers.filterLink
import nl.lawik.poc.frontend.reactredux.enums.VisibilityFilter
import nl.lawik.poc.frontend.reactredux.ringui.ButtonGroup
import react.RBuilder

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