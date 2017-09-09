package cn.jiangklijna.ella.model

import android.app.Activity
import android.content.SharedPreferences

/**
 * Created by jiangKlijna on 7/24/2017.
 * 扩展SharedPreferences
 */
object LocalMap {

	var sp: SharedPreferences? = null

	fun init(context: Activity) {
		sp = context.application.getSharedPreferences("setting", 0)
	}

	operator fun get(key: String): String = sp?.getString(key, "")!!

	fun getInt(key: String): Int = sp?.getInt(key, 0)!!

	fun getBool(key: String): Boolean = sp?.getBoolean(key, false)!!

	fun getFloat(key: String): Float = sp?.getFloat(key, 0.toFloat())!!

	fun getLong(key: String): Long = sp?.getLong(key, 0)!!

	fun getStringSet(key: String): Set<String> = sp?.getStringSet(key, setOf())!!

	fun getAll(): Map<String, *> = sp?.all!!

	fun del(key: String) = sp?.edit()?.remove(key)?.apply()

	operator fun set(key: String, value: String?) = sp?.edit()?.putString(key, value)?.apply()

	operator fun set(key: String, value: Int) = sp?.edit()?.putInt(key, value)?.apply()

	operator fun set(key: String, value: Boolean) = sp?.edit()?.putBoolean(key, value)?.apply()

	operator fun set(key: String, value: Float) = sp?.edit()?.putFloat(key, value)?.apply()

	operator fun set(key: String, value: Long) = sp?.edit()?.putLong(key, value)?.apply()

	operator fun set(key: String, value: Set<String>?) = sp?.edit()?.putStringSet(key, value)?.apply()

	fun clear() = sp?.edit()?.clear()?.apply()

	operator fun contains(key: String): Boolean = sp!!.contains(key)

	override fun toString(): String = getAll().toString()

}