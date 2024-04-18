package com.example.weather.data.dummy

import android.content.Context
import java.io.BufferedReader
import java.io.InputStreamReader

fun ReadJSON(context: Context, path: String): String{
//    val identifier = "[ReadJSON]"
    return try {
        val file = context.assets.open(path)
        val bufferedReader = BufferedReader(InputStreamReader(file))
        val stringBuilder = StringBuilder()
        bufferedReader.useLines { lines ->
            lines.forEach {
                stringBuilder.append(it)
            }
        }

        stringBuilder.toString()
    } catch (e: Exception) {
        e.printStackTrace()
        ""
    }
}