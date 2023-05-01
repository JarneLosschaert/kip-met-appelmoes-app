package be.howest.jarnelosschaert.kipmetappelmoes.data.models

import be.howest.jarnelosschaert.kipmetappelmoes.R

enum class ChildFriendliness(override val value: Int) : Tag {
    PARKING(R.string.parking),
    CHILD_MENU(R.string.child_menu),
    HIGHCHAIRS(R.string.highchairs),
    PLAYGROUND_INSIDE(R.string.playground_inside),
    MICROWAVE(R.string.microwave),
    CHANGING_TABLE(R.string.changing_table),
    PLAYGROUND_OUTSIDE(R.string.playground_outside)
}

