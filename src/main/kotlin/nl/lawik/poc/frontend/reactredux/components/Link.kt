package nl.lawik.poc.frontend.reactredux.components

import kotlinx.css.marginLeft
import kotlinx.css.px
import kotlinx.html.js.onClickFunction
import react.*
import styled.css
import styled.styledButton

external interface LinkProps : RProps {
    var active: Boolean
    var onClick: () -> Unit
}

@JsExport
class Link(props: LinkProps) : RComponent<LinkProps, State>(props) {
    override fun RBuilder.render() {
        styledButton {
            attrs.onClickFunction = { props.onClick() }
            attrs.disabled = props.active
            css {
                marginLeft = 4.px
            }
            children()
        }
    }
}