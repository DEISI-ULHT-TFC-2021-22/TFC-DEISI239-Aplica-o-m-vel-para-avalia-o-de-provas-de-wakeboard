package tfc2022.judgingapp_21800876.utils

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.IOException

//Function that reads the trick Json file

inline fun <reified T> readJson(context: Context, path: String): T {
    lateinit var jsonString: String
    try {
        jsonString = context.assets.open(path)
            .bufferedReader()
            .use { it.readText() }
    } catch (ioException: IOException) {
        Log.e("error read json", "${ioException.message}")
    }

    return Gson().fromJson<T>(jsonString, object: TypeToken<T>() {}.type)
}