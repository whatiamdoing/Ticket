package com.ticket.ui.fonts

import android.content.Context
import android.graphics.Typeface
import android.util.Log
import java.lang.Exception

class FontCache {
    companion object {
        private val fontCache: HashMap<String, Typeface> = HashMap()

        fun getTypeface(fontName: String,context: Context): Typeface? {
            var typeface = fontCache[fontName]

            if (typeface == null){
                try{
                    typeface = Typeface.createFromAsset(context.assets, fontName)
                } catch (e: Exception){
                    Log.e("Get typeface error", e.message)
                    return null
                }

                fontCache[fontName] = typeface
            }

            return typeface
        }
    }
}