package com.ticket.ui.fonts

import android.content.Context
import android.util.AttributeSet
import android.widget.EditText

class CustomFontEditText: EditText {
    constructor(context: Context): super(context){
        applyCustomFont(context)
    }

    constructor(context: Context, attrs: AttributeSet): super(context, attrs){
        applyCustomFont(context)
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int): super(context, attrs, defStyle){
        applyCustomFont(context)
    }

    private fun applyCustomFont(context: Context) {
        includeFontPadding = false
        typeface = FontCache.getTypeface("ticket.otf", context)
    }
}