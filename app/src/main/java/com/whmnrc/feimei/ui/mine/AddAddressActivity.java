package com.whmnrc.feimei.ui.mine;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.bigkoo.pickerview.OptionsPickerView;
import com.whmnrc.feimei.R;
import com.whmnrc.feimei.beans.AddressBean;
import com.whmnrc.feimei.beans.JsonBean;
import com.whmnrc.feimei.presener.AddressEditPresenter;
import com.whmnrc.feimei.ui.BaseActivity;
import com.whmnrc.feimei.utils.GetCityUtils;
import com.whmnrc.feimei.utils.KeyboardUtils;
import com.whmnrc.feimei.utils.ToastUtils;
import com.whmnrc.feimei.utils.evntBusBean.AddressEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author yjyvi
 * @data 2018/5/22.
 */

public class AddAddressActivity extends BaseActivity implements AddressEditPresenter.AddressEditListener {

    @BindView(R.id.tv_name)
    TextView mTvName;
    @BindView(R.id.et_name)
    EditText mEtName;
    @BindView(R.id.et_tel)
    EditText mEtTel;
    @BindView(R.id.et_pro)
    EditText mEtPro;
    @BindView(R.id.et_city)
    EditText mEtCity;
    @BindView(R.id.et_area)
    EditText mEtArea;
    @BindView(R.id.et_des_address)
    EditText mEtDesAddress;
    @BindView(R.id.iv_is_default)
    ImageView mIvIsDefault;

    String addressId;
    private ArrayList<JsonBean> options1Items = new ArrayList<>();
    private ArrayList<ArrayList<String>> options2Items = new ArrayList<>();
    private ArrayList<ArrayList<ArrayList<String>>> options3Items = new ArrayList<>();
    private OptionsPickerView mPickerView;
    public AddressEditPresenter mAddressEditPresenter;

    @Override
    protected void initViewData() {

        mAddressEditPresenter = new AddressEditPresenter(this);
        int addressSize = getIntent().getIntExtra("addressSize", -1);
        String resultdataBeanJson = getIntent().getStringExtra("resultdataBeanJson");
        if (TextUtils.isEmpty(resultdataBeanJson)) {
            setTitle("新增收货地址");
            addressId = "";
        } else {
            setTitle("编辑收货地址");
            AddressBean.ResultdataBean addressBean = JSON.parseObject(resultdataBeanJson, AddressBean.ResultdataBean.class);
            mEtName.setText(addressBean.getName());
            mEtTel.setText(addressBean.getMobile());
            mEtPro.setText(addressBean.getProvice());
            mEtCity.setText(addressBean.getCity());
            mEtArea.setText(addressBean.getRegion());
            mEtDesAddress.setText(addressBean.getDetail());
            mIvIsDefault.setSelected(addressBean.getIsDefault() == 1);
            addressId = addressBean.getID();
        }

        initJsonData();
        initPickerView();

        if (addressSize == 0) {
            mIvIsDefault.setSelected(true);
        }
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_add_address;
    }

    public static void start(Context context, String resultdataBeanJson, int addressSize) {
        Intent starter = new Intent(context, AddAddressActivity.class);
        starter.putExtra("resultdataBeanJson", resultdataBeanJson);
        starter.putExtra("addressSize", addressSize);
        context.startActivity(starter);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);

    }

    @Subscribe
    public void addressEvent(AddressEvent addressEvent) {

    }


    //城市json数据初始化
    private void initJsonData() {
        //解析数据
        String jsonData = GetCityUtils.getJson(this, "province.json");
        ArrayList<JsonBean> jsonBean = GetCityUtils.parseData(jsonData);
        //添加省份数据
        options1Items = jsonBean;
        for (int i = 0; i < jsonBean.size(); i++) {
            ArrayList<String> cityList = new ArrayList<>();
            ArrayList<ArrayList<String>> provinceAreaList = new ArrayList<>();
            for (int c = 0; c < jsonBean.get(i).getCityList().size(); c++) {
                String cityName = jsonBean.get(i).getCityList().get(c).getName();
                cityList.add(cityName);
                ArrayList<String> cityAreaList = new ArrayList<>();
                if (jsonBean.get(i).getCityList().get(c).getArea() == null
                        || jsonBean.get(i).getCityList().get(c).getArea().size() == 0) {
                    cityAreaList.add("");
                } else {

                    for (int d = 0; d < jsonBean.get(i).getCityList().get(c).getArea().size(); d++) {
                        String areaName = jsonBean.get(i).getCityList().get(c).getArea().get(d);
                        //添加该城市所有地区数据
                        cityAreaList.add(areaName);
                    }
                }
                //添加该省所有地区数据
                provinceAreaList.add(cityAreaList);
            }
            //添加城市数据
            options2Items.add(cityList);
            //添加地区数据
            options3Items.add(provinceAreaList);
        }
    }

    //选择器初始化
    private void initPickerView() {

        mPickerView = new OptionsPickerView.Builder(this, (options1, options2, options3, v) -> {
            mEtPro.setText(options1Items.get(options1).getPickerViewText());
            mEtCity.setText(options2Items.get(options1).get(options2));
            mEtArea.setText(options3Items.get(options1).get(options2).get(options3));
        })
                .setTitleText("城市选择")
                .setCyclic(false, false, false)
                .setDividerColor(R.color.normal_gray)
                .setTextColorCenter(Color.BLACK)
                .setContentTextSize(20)
                .build();

//        //三级选择器
        mPickerView.setPicker(options1Items, options2Items, options3Items);
    }


    @OnClick({R.id.iv_is_default, R.id.ll_commit, R.id.et_pro, R.id.et_city, R.id.et_area})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_is_default:
                mIvIsDefault.setSelected(!mIvIsDefault.isSelected());
                break;
            case R.id.ll_commit:
                if (inputVerification()) {
                    String name = mEtName.getText().toString().trim();
                    String tel = mEtTel.getText().toString().trim();
                    String pro = mEtPro.getText().toString().trim();
                    String city = mEtCity.getText().toString().trim();
                    String area = mEtArea.getText().toString().trim();
                    String addressDes = mEtDesAddress.getText().toString().trim();
                    int isDefault = mIvIsDefault.isSelected() ? 1 : 0;
                    mAddressEditPresenter.editAddress(addressId, name, pro, city, area, addressDes, String.valueOf(isDefault), tel);
                    isShowDialog(true);
                }

                break;
            case R.id.et_pro:
            case R.id.et_city:
            case R.id.et_area:
                KeyboardUtils.hideKeyBoard(AddAddressActivity.this, mEtArea);
                mPickerView.show();
                break;
            default:
                break;
        }
    }


    /**
     * 输入提示
     *
     * @return
     */
    private boolean inputVerification() {

        if (TextUtils.isEmpty(mEtName.getText().toString().trim())) {
            ToastUtils.showToast(mEtName.getHint().toString().trim());
            return false;
        }

        if (TextUtils.isEmpty(mEtTel.getText().toString().trim())) {
            ToastUtils.showToast(mEtTel.getHint().toString().trim());
            return false;
        }

        if (TextUtils.isEmpty(mEtPro.getText().toString().trim())) {
            ToastUtils.showToast(mEtPro.getHint().toString().trim());
            return false;
        }


        if (TextUtils.isEmpty(mEtDesAddress.getText().toString().trim())) {
            ToastUtils.showToast(mEtDesAddress.getHint().toString().trim());
            return false;
        }


        return true;
    }


    @Override
    public void addSuccess() {
        isShowDialog(false);
        EventBus.getDefault().post(new AddressEvent().setEventType(AddressEvent.ADD_ADDRESS_SUCCESS));
        finish();
    }

    @Override
    public void addField() {
        isShowDialog(false);
    }
}
