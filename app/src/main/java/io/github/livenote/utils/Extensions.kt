package io.github.livenote.utils

import android.text.Editable

object Extensions {
    fun String.toEditable(): Editable =  Editable.Factory.getInstance().newEditable(this)
}