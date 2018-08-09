package com.whmnrc.feimei.adapter.recycleViewBaseAdapter;

/**多条目的接口 创建multiItemadapter后 有多少类型实现多少此接口 并添加与adapter
 * Created by yonghao zeng on 2017/5/9.
 */
public interface ItemViewDelegate<T>
{
    /**
     * 实现此方法后 返回对应类型的layoutid
     * @return
     */
    int getItemViewLayoutId();

    /**
     * 依据此布尔值来进行区分是否显示此类型
     * @param item
     * @param position
     * @return
     */
    boolean isForViewType(T item, int position);

    /**
     * 在此方法对viewholder中的控件进行数据填充
     * @param holder
     * @param t
     * @param position
     */
    void convert(ViewHolder holder, T t, int position);

}
