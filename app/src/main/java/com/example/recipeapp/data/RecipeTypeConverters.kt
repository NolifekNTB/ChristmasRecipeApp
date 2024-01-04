package com.example.recipeapp.data

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import org.json.JSONObject
import org.json.JSONArray

class HashMapConverter {

    @TypeConverter
    fun convertToDatabaseValue(entityValue: HashMap<String, Int>): String {
        // Convert HashMap to JSON string
        val json = JSONObject()
        for ((key, value) in entityValue) {
            json.put(key, value)
        }
        return json.toString()
    }

    @TypeConverter
    fun convertToEntityValue(databaseValue: String): HashMap<String, Int> {
        val json = JSONObject(databaseValue)
        val ingredients = HashMap<String, Int>()
        val jsonIterator = json.keys()
        while (jsonIterator.hasNext()) {
            val key = jsonIterator.next()
            val ingredientValue = json.get(key) as Int
            ingredients[key] = ingredientValue
        }
        return ingredients
    }
}