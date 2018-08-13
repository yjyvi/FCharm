package com.whmnrc.feimei.ui.organization;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewStub;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.whmnrc.feimei.R;
import com.whmnrc.feimei.adapter.OrganizationCommentAdapter;
import com.whmnrc.feimei.adapter.OtherInfoAdapter;
import com.whmnrc.feimei.beans.OrganizationDetailsBean;
import com.whmnrc.feimei.pop.PopRatingRule;
import com.whmnrc.feimei.pop.PopShare;
import com.whmnrc.feimei.presener.GetRatingExplainPresenter;
import com.whmnrc.feimei.presener.OrganizationDetailsPresenter;
import com.whmnrc.feimei.ui.BaseActivity;
import com.whmnrc.feimei.ui.CommonH5WebView;
import com.whmnrc.feimei.ui.UserManager;
import com.whmnrc.feimei.ui.mine.CommentActivity;
import com.whmnrc.feimei.ui.mine.PayActivity;
import com.whmnrc.feimei.utils.TextSpannableUtils;
import com.whmnrc.feimei.utils.TimeUtils;
import com.whmnrc.feimei.views.RatingBarView;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author yjyvi
 * @data 2018/7/26.
 */

public class OrganizationDetailsActivity extends BaseActivity implements OrganizationDetailsPresenter.GetOrganizationDetailsListener, GetRatingExplainPresenter.GetRatingExplainListener {
    @BindView(R.id.tv_name)
    TextView mTvName;
    @BindView(R.id.rb_star)
    RatingBarView mRbStar;
    @BindView(R.id.tv_company_type)
    TextView mTvCompanyType;
    @BindView(R.id.tv_legal_person)
    TextView mTvLegalPerson;
    @BindView(R.id.tv_crate_time)
    TextView mTvCrateTime;
    @BindView(R.id.tv_register_price)
    TextView mTvRegisterPrice;
    @BindView(R.id.tv_register_state)
    TextView mTvRegisterState;
    @BindView(R.id.tv_register_state_content)
    TextView mTvRegisterStateContent;
    @BindView(R.id.ll_account)
    LinearLayout mLlAccount;
    @BindView(R.id.v_line)
    View mVLine;
    @BindView(R.id.ll_business_more)
    TextView mLlBusinessMore;
    @BindView(R.id.tv_business_content)
    TextView mTvBusinessContent;
    @BindView(R.id.v_line1)
    View mVLine1;
    @BindView(R.id.ll_overview_more)
    TextView mLlOverviewMore;
    @BindView(R.id.tv_overview_content)
    TextView mTvOverviewContent;
    @BindView(R.id.v_line3)
    View mVLine3;
    @BindView(R.id.ll_link_more)
    TextView mLlLinkMore;
    @BindView(R.id.tv_link_content)
    TextView mTvLinkContent;
    @BindView(R.id.v_line4)
    View mVLine4;
    @BindView(R.id.ll_other_more)
    TextView mLlOtherMore;
    @BindView(R.id.rv_other_list)
    RecyclerView mRvOtherList;
    @BindView(R.id.v_line5)
    View mVLine5;
    @BindView(R.id.tv_intellectual_property)
    TextView mTvIntellectualProperty;
    @BindView(R.id.tv_major_client)
    TextView mTvMajorClient;
    @BindView(R.id.tv_shareholder_information)
    TextView mTvShareholderInformation;
    @BindView(R.id.tv_associated_position)
    TextView mTvAssociatedPosition;
    @BindView(R.id.v_line6)
    View mVLine6;
    @BindView(R.id.tv_all_comment)
    TextView mTvAllComment;
    @BindView(R.id.rv_comment_list)
    RecyclerView mRvCommentList;
    @BindView(R.id.tv_comment_count)
    TextView mTvCommentCount;
    @BindView(R.id.ll_all_comment)
    LinearLayout mLlAllComment;
    @BindView(R.id.et_content)
    EditText mEtContent;
    @BindView(R.id.tv_send)
    TextView mTvSend;
    @BindView(R.id.tv_shareholder_count)
    TextView mTvShareholderCount;
    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.iv_share)
    ImageView mIvShare;
    @BindView(R.id.ll_name)
    LinearLayout mLlName;
    @BindView(R.id.vs_empty)
    ViewStub mVsEmpty;
    public PopShare mPopShare;
    public PopRatingRule mPopRatingRule;
    public OrganizationDetailsPresenter mOrganizationDetailsPresenter;
    public String mIndustryID;
    public OtherInfoAdapter mOtherInfoAdapter;
    public OrganizationCommentAdapter mOrganizationCommentAdapter;
    private OrganizationDetailsBean.ResultdataBean mOrganizationDetailsBean;
    public GetRatingExplainPresenter mGetRatingExplainPresenter;

    @Override
    protected void initViewData() {
        showEmpty(true, mVsEmpty);

        mIndustryID = getIntent().getStringExtra("industryID");
        mOrganizationDetailsPresenter = new OrganizationDetailsPresenter(this);
        mGetRatingExplainPresenter = new GetRatingExplainPresenter(this);
        mOrganizationDetailsPresenter.getOrganizationDetails(mIndustryID);
        isShowDialog(true);


        mRbStar.setClickable(false);
        mRvOtherList.setNestedScrollingEnabled(false);
        mRvOtherList.setLayoutManager(new LinearLayoutManager(this));
        mOtherInfoAdapter = new OtherInfoAdapter(this, R.layout.item_other_info);
        mRvOtherList.setAdapter(mOtherInfoAdapter);

        mRvCommentList.setNestedScrollingEnabled(false);
        mRvCommentList.setLayoutManager(new LinearLayoutManager(this));
        mOrganizationCommentAdapter = new OrganizationCommentAdapter(this, R.layout.item_organization_comment);
        mRvCommentList.setAdapter(mOrganizationCommentAdapter);
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_organiztion_details;
    }


    public static void start(Context context, String industryID) {
        Intent starter = new Intent(context, OrganizationDetailsActivity.class);
        starter.putExtra("industryID", industryID);
        context.startActivity(starter);
    }


    @OnClick({R.id.ll_account, R.id.ll_business_more, R.id.ll_overview_more,
            R.id.ll_link_more, R.id.ll_other_more, R.id.tv_intellectual_property,
            R.id.tv_major_client, R.id.tv_shareholder_information,
            R.id.tv_associated_position, R.id.ll_all_comment,
            R.id.tv_send, R.id.iv_back, R.id.iv_share, R.id.ll_name
    })
    public void onClick(View view) {
        switch (view.getId()) {
            //关于我们
            case R.id.ll_account:

                if (!UserManager.getIsLogin(view.getContext())) {
                    return;
                }

                if (UserManager.getUserIsVip()) {
                    AboutMeActivity.start(view.getContext(), mOrganizationDetailsBean.getEnterprise());
                } else {
                    PayActivity.startOrg(view.getContext(), PayActivity.ORG_PAY, mOrganizationDetailsBean.getEnterprise());
                }
                break;
            //主营业务
            case R.id.ll_business_more:
                CommonH5WebView.startCommonH5WebView(OrganizationDetailsActivity.this, mOrganizationDetailsBean.getEnterprise().getMainBusiness(), "主营业务");
                break;
            //企业概述
            case R.id.ll_overview_more:
                OtherInfoDetailsActivity.start(view.getContext(), "企业概览", mOrganizationDetailsBean.getEnterprise().getIntroduction());
                break;
            //关联企业
            case R.id.ll_link_more:

                if (!UserManager.getIsLogin(view.getContext())) {
                    return;
                }

                if (UserManager.getUserIsVip()) {
                    if (mOrganizationDetailsBean.getRelation().size() > 0) {
                        BusinessMoreActivity.start(view.getContext(), mOrganizationDetailsBean.getRelation(), "关联企业", 0);
                    }
                } else {
                    PayActivity.startOrg(view.getContext(), PayActivity.ORG_PAY,mOrganizationDetailsBean.getEnterprise());
                }


                break;
            //其它信息
            case R.id.ll_other_more:
                if (mOrganizationDetailsBean.getEnterpriseOther().size() > 0) {
                    OtherInfoActivity.start(view.getContext(), mOrganizationDetailsBean.getEnterpriseOther());
                }
                break;
            //知识产权
            case R.id.tv_intellectual_property:
                if (mOrganizationDetailsBean.getCertificate().size() > 0) {
                    IntellectualPropertyActivity.start(view.getContext(), mOrganizationDetailsBean.getCertificate());
                }
                break;
            //主要客户
            case R.id.tv_major_client:

                if (mOrganizationDetailsBean.getEnterprise().getPrice()>0) {
                    if (!UserManager.getIsLogin(view.getContext())) {
                        return;
                    }

                    if (UserManager.getUserIsVip()) {
                        if (mOrganizationDetailsBean.getRelation().size() > 0) {
                            BusinessMoreActivity.start(view.getContext(), mOrganizationDetailsBean.getRelation(), "主要客户", 1);
                        }
                    } else {
                        PayActivity.startOrg(view.getContext(), PayActivity.ORG_PAY, mOrganizationDetailsBean.getEnterprise());
                    }

                }else {
                    if (mOrganizationDetailsBean.getRelation().size() > 0) {
                        BusinessMoreActivity.start(view.getContext(), mOrganizationDetailsBean.getRelation(), "主要客户", 1);
                    }
                }



                break;
            //股东信息
            case R.id.tv_shareholder_information:
                if (mOrganizationDetailsBean.getShareholder().size() > 0) {
                    ShareholderInformationActivity.start(view.getContext(), mOrganizationDetailsBean.getShareholder());
                }
                break;
            //关联岗位
            case R.id.tv_associated_position:
                AssociatedPositionActivity.start(view.getContext(), mOrganizationDetailsBean.getEnterprise().getID());
                break;
            //全部评论
            case R.id.ll_all_comment:
                if (mOrganizationDetailsBean.getComment().size() > 0) {
                    AllCommentActivity.start(view.getContext(), mOrganizationDetailsBean.getEnterprise().getID());
                }
                break;
            case R.id.tv_send:
                if (!UserManager.getIsLogin(view.getContext())) {
                    return;
                }

                CommentActivity.start(view.getContext(), mOrganizationDetailsBean.getEnterprise().getID());
                CommentActivity.setCommentListener(() -> mOrganizationDetailsPresenter.getOrganizationDetails(mIndustryID));
                break;
            case R.id.iv_back:
                finish();
                break;
            case R.id.ll_name:
                mGetRatingExplainPresenter.getRatingExplain();
                break;
            case R.id.iv_share:
                mPopShare = new PopShare(OrganizationDetailsActivity.this, "1", "1", "1", "1");
                mPopShare.show();
                break;
            default:
                break;
        }
    }

    @Override
    public void getOrganizationDetailsSuccess(OrganizationDetailsBean.ResultdataBean beans) {
        showEmpty(false, mVsEmpty);
        mOrganizationDetailsBean = beans;
        OrganizationDetailsBean.ResultdataBean.EnterpriseBean enterprise = beans.getEnterprise();
        mTvName.setText(enterprise.getName());
        mRbStar.setStar(enterprise.getRating(), false);
        mTvCompanyType.setText(enterprise.getExplain());
        mTvLegalPerson.setText(String.format("法定代表人：%s", enterprise.getLegalPerson()));
        mTvCrateTime.setText(String.format("成立日期：%s", TimeUtils.getDateToString(Long.parseLong(enterprise.getCreateTime()))));
        mTvRegisterPrice.setText(String.format("注册资金：%s", enterprise.getRegisteredCapital()));
        mTvRegisterStateContent.setText(enterprise.getRegistrationState());
        mTvBusinessContent.setText(enterprise.getMainExplain());
        mTvOverviewContent.setText(enterprise.getIntroduction());
        if (beans.getRecruitCount() > 0) {
            mTvShareholderCount.setText(String.valueOf(beans.getRecruitCount()));
        } else {
            mTvShareholderCount.setVisibility(View.INVISIBLE);
        }
        if (beans.getCommentCount() > 0) {
            mTvCommentCount.setText(String.format("共%s条", beans.getCommentCount()));
            mTvAllComment.setText(String.format("全部评价(%s)", beans.getCommentCount()));
            String textContent = mTvAllComment.getText().toString().trim();
            TextSpannableUtils.changeTextColor(mTvAllComment, textContent, 4, textContent.length(), ContextCompat.getColor(this, R.color.good_price_red));
        } else {
            mTvCommentCount.setVisibility(View.GONE);
        }

        //关联企业
        List<OrganizationDetailsBean.ResultdataBean.RelationBean> relation = beans.getRelation();
        String relationSt = "";
        for (int i = 0; i < relation.size(); i++) {
            if (i == 3) {
                break;
            }
            if (relation.get(i).getType() == 0) {
                if (relation.size() < 3 && i == relation.size() - 1 || i == 2) {
                    relationSt += relation.get(i).getName();
                } else {
                    relationSt += relation.get(i).getName() + "\n";
                }
            }
        }
        mTvLinkContent.setText(relationSt);

        mOtherInfoAdapter.setDataArray(beans.getEnterpriseOther());
        mOtherInfoAdapter.notifyDataSetChanged();

        mOrganizationCommentAdapter.setDataArray(beans.getComment());
        mOrganizationCommentAdapter.notifyDataSetChanged();

        isShowDialog(false);
    }

    @Override
    public void getOrganizationDetailsField() {
        isShowDialog(false);
    }

    @Override
    public void getRatingExplainSuccess(String content) {
        if (mPopRatingRule == null) {
            mPopRatingRule = new PopRatingRule(OrganizationDetailsActivity.this, "评级规则", content);
        }
        mPopRatingRule.show();
    }

}
