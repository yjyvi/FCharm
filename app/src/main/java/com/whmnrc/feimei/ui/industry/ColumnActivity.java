package com.whmnrc.feimei.ui.industry;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.ViewStub;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.whmnrc.feimei.R;
import com.whmnrc.feimei.adapter.ResourceListAdapter;
import com.whmnrc.feimei.beans.ColumnBean;
import com.whmnrc.feimei.beans.ReadListBean;
import com.whmnrc.feimei.beans.SearchConditionBean;
import com.whmnrc.feimei.pop.PopShare;
import com.whmnrc.feimei.presener.GetReadPresenter;
import com.whmnrc.feimei.ui.BaseActivity;
import com.whmnrc.feimei.ui.home.SearchActivity;
import com.whmnrc.mylibrary.utils.GlideUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * @author yjyvi
 * @data 2018/7/31.
 */

public class ColumnActivity extends BaseActivity implements GetReadPresenter.GetReadListener {
    @BindView(R.id.tv_search)
    EditText mTvSearch;
    @BindView(R.id.vs_empty)
    ViewStub mVsEmpty;
    @BindView(R.id.rv_product_list)
    RecyclerView mRvProductList;

    public String mSearchContent;
    public PopShare mPopShare;
    @BindView(R.id.iv_user_img)
    CircleImageView mIvUserImg;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.tv_content)
    TextView mTvContent;
    private GetReadPresenter mGetReadPresenter;
    public String mColumnId;
    public ResourceListAdapter mResourceListAdapter;
    public ColumnBean.ResultdataBean mColumnBean;

    @Override
    protected void initViewData() {
        setTitle("专栏");
        rightVisible(R.mipmap.icon_share);

        isShowDialog(true);

        mColumnBean = getIntent().getParcelableExtra("columnBean");

        if (mColumnBean != null) {
            GlideUtils.LoadImage(this, mColumnBean.getImg(), mIvUserImg);
            mTvTitle.setText(mColumnBean.getName());
            mTvContent.setText(mColumnBean.getIntroduce());
            mColumnId = mColumnBean.getID();
        }

        mGetReadPresenter = new GetReadPresenter(this);

        mGetReadPresenter.getReadList(true, "", mColumnId);

        mTvSearch.setOnEditorActionListener((view, keyCode, event) -> {
            if (keyCode == EditorInfo.IME_ACTION_SEARCH) {
                // 先隐藏键盘
                ((InputMethodManager) getSystemService(INPUT_METHOD_SERVICE))
                        .hideSoftInputFromWindow(getCurrentFocus()
                                .getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                mSearchContent = view.getText().toString().trim();

                if (!TextUtils.isEmpty(mSearchContent)) {
                    SearchConditionBean searchConditionBean = new SearchConditionBean();
                    searchConditionBean.setContent(mSearchContent);
                    SearchActivity.start(view.getContext(), SearchActivity.SEARCH_COLUMN, searchConditionBean);
                    mTvSearch.setText("");
                    return true;
                }
            }
            return false;
        });

        mRvProductList.setLayoutManager(new LinearLayoutManager(this));
        mRvProductList.setNestedScrollingEnabled(false);
        mResourceListAdapter = new ResourceListAdapter(this, R.layout.item_resource_list);
        mRvProductList.setAdapter(mResourceListAdapter);
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_column;
    }

    public static void start(Context context, String columnId) {
        Intent starter = new Intent(context, ColumnActivity.class);
        starter.putExtra("columnId", columnId);
        context.startActivity(starter);
    }


    public static void start(Context context, ColumnBean.ResultdataBean columnBean) {
        Intent starter = new Intent(context, ColumnActivity.class);
        starter.putExtra("columnBean", columnBean);
        context.startActivity(starter);
    }


    @OnClick(R.id.rl_right)
    public void onClick() {
        if (mPopShare == null) {
            mPopShare = new PopShare(this, "1", "1", "1", "1");
        }
        mPopShare.show();
    }

    @Override
    public void getReadSuccess(boolean isRefresh, ReadListBean.ResultdataBean bean) {
        if (isRefresh) {
            mResourceListAdapter.setDataArray(bean.getRead());
        } else {
            List<ReadListBean.ResultdataBean.ReadBean> datas = mResourceListAdapter.getDatas();
            if (datas.size() == bean.getPagination().getRecords()) {

            }
            datas.addAll(bean.getRead());
            mResourceListAdapter.setDataArray(datas);
        }

        mResourceListAdapter.notifyDataSetChanged();

        showEmpty(mResourceListAdapter, mVsEmpty);
        isShowDialog(false);
    }

    @Override
    public void getReadField() {
        isShowDialog(false);
    }


}
