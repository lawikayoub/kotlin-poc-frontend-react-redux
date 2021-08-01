package nl.lawik.poc.frontend.reactredux.components

import react.*
import ringui.Button

external interface LinkProps : RProps {
    var active: Boolean
    var onClick: () -> Unit
}

@JsExport
class Link(props: LinkProps) : RComponent<LinkProps, State>(props) {
    override fun RBuilder.render() {
        Button {
            attrs.onMouseDown = { props.onClick() }
            attrs.active = props.active
            children()
        }
    }
}