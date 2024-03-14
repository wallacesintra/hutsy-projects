package com.example.weather.data

import android.content.Context
import android.util.Log
import java.io.BufferedReader
import java.io.InputStreamReader

fun ReadJSON(context: Context, path: String): String{
//    val identifier = "[ReadJSON]"
    try {
        val file = context.assets.open(path)
        val bufferedReader = BufferedReader(InputStreamReader(file))
        val stringBuilder = StringBuilder()
        bufferedReader.useLines { lines ->
            lines.forEach {
                stringBuilder.append(it)
            }
        }

        val jsonString = stringBuilder.toString()
        return jsonString
    } catch (e: Exception) {
        e.printStackTrace()
        return ""
    }
}