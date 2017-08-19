package cn.jiangklijna.ella.common

import android.content.Context
import android.util.SparseArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter

import java.util.Arrays
import java.util.Collections

/**
 * 万能adapter
 * ViewHolder以静态内部类的形式存在(私有构造),通过getHoder方法来获取ViewHolder对象
 * Author: jiangKlijna
 */
abstract class XAdapter<T>(collection: Collection<T>, protected val context: Context) : BaseAdapter() {

    private val dataSet: CollectionDataSet<T>
    var obj: Any? = null

    constructor(context: Context) : this(Collections.emptyList<T>(), context)

    constructor(array: Array<T>, context: Context) : this(Arrays.asList(*array), context)

    init {
        this.dataSet = CollectionDataSet(collection)
    }

    fun setData(collection: Collection<T>) {
        if (collection !== dataSet.collection)
            dataSet.collection = collection
        dataSet.onNotifyDataSetChanged()
        super.notifyDataSetChanged()
    }

    fun setData(array: Array<T>) {
        setData(Arrays.asList(*array))
    }

    override fun notifyDataSetChanged() {
        dataSet.onNotifyDataSetChanged()
        super.notifyDataSetChanged()
    }

    fun clear() {
        setData(Collections.emptyList<T>())
    }

    val collection: Collection<T>
        get() = dataSet.collection

    override fun getCount(): Int {
        return dataSet.count
    }

    override fun getItem(position: Int): T {
        return dataSet.getItem(position)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    class ViewHolder constructor(private val mConvertView: View) {
        private var mViews: SparseArray<View>? = null

        init {
            mConvertView.tag = this
        }

        fun <T : View> getConvertView(): T {
            return mConvertView as T
        }

        fun <T : View> getView(viewId: Int): T {
            if (mViews == null) mViews = SparseArray(6)
            var view: View? = mViews!!.get(viewId)
            if (view == null) {
                view = mConvertView.findViewById(viewId)
                mViews!!.put(viewId, view)
            }
            return view as T
        }
    }

    //    private interface IDataSet<T> {
    //        int getCount();
    //        T getItem(int position);
    //        void onNotifyDataSetChanged();
    //    }

    private class CollectionDataSet<T> constructor(var collection: Collection<T>) {
        private var datas: Array<Any>? = null

        init {
            onNotifyDataSetChanged()
        }

        fun onNotifyDataSetChanged() {
            datas = (collection as Collection<Any>).toTypedArray<Any>()
        }

        val count: Int
            get() = datas!!.size

        fun getItem(position: Int): T {
            return datas!![position] as T
        }
    }

    companion object {

        fun getHolder(context: Context, convertView: View?, clasz: Class<out View>): ViewHolder {
            if (convertView == null) {
                try {
                    return ViewHolder(clasz.getConstructor(Context::class.java).newInstance(context))
                } catch (e: Exception) {
                    throw RuntimeException(clasz.name + " Construction method error. \n", e)
                }

            }
            return convertView.tag as ViewHolder
        }

        fun getHolder(context: Context, convertView: View?, parent: ViewGroup?, layoutId: Int): ViewHolder {
            if (convertView == null) {
                try {
                    return ViewHolder(LayoutInflater.from(context).inflate(layoutId, parent, false))
                } catch (e: Exception) {
                    throw RuntimeException(layoutId.toString() + " Layout Inflater error. \n", e)
                }

            }
            return convertView.tag as ViewHolder
        }
    }

}
