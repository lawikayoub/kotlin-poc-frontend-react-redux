package reactredux.components

import reactredux.containers.filterLink
import reactredux.enums.VisibilityFilter
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