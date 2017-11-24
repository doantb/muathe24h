package com.example.windows10now.muathe24h.fragment;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.example.windows10now.muathe24h.R;
import com.example.windows10now.muathe24h.adapter.ChoseHomeNetWorkAdapter;
import com.example.windows10now.muathe24h.adapter.SpinnerAdapter;
import com.example.windows10now.muathe24h.model.CardType;
import java.text.ParseException;
import java.util.ArrayList;

/**
 * Created by Windows 10 Now on 11/13/2017.
 */

public class ChangerCardFragment extends BaseFragment
        implements AdapterView.OnItemSelectedListener, ChoseHomeNetWorkAdapter.onHandleClick {
    private TextView txtPriceCard;
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private ChoseHomeNetWorkAdapter mWorkAdapter;
    private Spinner mSpinnerChosePrice;
    private Spinner mSpinnerChoseMethod;
    private EditText edtCountCart;
    private Button btnSave;
    private SpinnerAdapter mSpinnerAdapter;
    private LinearLayout llspPrice;
    private ArrayList<String> arrListSpinner = new ArrayList<>();
    private ArrayList<CardType> mCardTypeArrayList = new ArrayList<>();

    public static ChangerCardFragment newInstance() {
        ChangerCardFragment changerCard = new ChangerCardFragment();
        Bundle args = new Bundle();
        return changerCard;
    }

    @Override
    protected int layoutId() {
        return R.layout.fragment_change_card;
    }

    @Override
    protected void init(View mRoot, Bundle savedInstanceState) throws ParseException {
        txtPriceCard = mRoot.findViewById(R.id.txt_price);
        mRecyclerView = mRoot.findViewById(R.id.rclChangeCard);
        mSpinnerChosePrice = mRoot.findViewById(R.id.spChosePrice);
        mSpinnerChoseMethod = mRoot.findViewById(R.id.spChoseMethod);
        edtCountCart = mRoot.findViewById(R.id.edt_number_card);
        btnSave = mRoot.findViewById(R.id.btn_buy_card);
        llspPrice = mRoot.findViewById(R.id.ll_sp_price);
        initVariables(mRoot, savedInstanceState);
    }

    private void initVariables(View mRoot, Bundle saveInstance) {
        loadDummyCardTypeItem();
        mWorkAdapter = new ChoseHomeNetWorkAdapter(getContext(), mCardTypeArrayList);
        mLayoutManager = new GridLayoutManager(getContext(), 3);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mWorkAdapter);
        loadDummySpinnerItem();
        mSpinnerAdapter = new SpinnerAdapter(getContext(), android.R.layout.simple_spinner_item,
                arrListSpinner);
        mSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        mSpinnerChosePrice.setAdapter(mSpinnerAdapter);
        mSpinnerChosePrice.setOnItemSelectedListener(this);
        llspPrice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSpinnerChosePrice.performClick();
            }
        });
        mWorkAdapter.setOnHandleClick(this);
    }

    private ArrayList<String> loadDummySpinnerItem() {
        arrListSpinner.add(getString(R.string.muoinghin));
        arrListSpinner.add(getString(R.string.haimuoi));
        arrListSpinner.add(getString(R.string.bamuoi));
        arrListSpinner.add(getString(R.string.nammuoi));
        arrListSpinner.add(getString(R.string.mottram));
        arrListSpinner.add(getString(R.string.haitram));
        arrListSpinner.add(getString(R.string.batram));
        arrListSpinner.add(getString(R.string.namtram));
        return arrListSpinner;
    }

    private void loadDummyCardTypeItem() {
        mCardTypeArrayList.add(new CardType("Viettel", R.drawable.i1));
        mCardTypeArrayList.add(new CardType("Mobifone", R.drawable.i2));
        mCardTypeArrayList.add(new CardType("VinaPhone", R.drawable.i3));
        mCardTypeArrayList.add(new CardType("Gate", R.drawable.i5));
        mCardTypeArrayList.add(new CardType("Zing", R.drawable.i12));
        mCardTypeArrayList.add(new CardType("VietnamMobile", R.drawable.i7));
        mCardTypeArrayList.add(new CardType("Garena", R.drawable.i14));
        mCardTypeArrayList.add(new CardType("VCoin", R.drawable.i13));
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        switch (i) {
            case 0:
                txtPriceCard.setText(R.string.muoinghin);
                break;
            case 1:
                txtPriceCard.setText(R.string.haimuoi);
                break;
            case 2:
                txtPriceCard.setText(R.string.bamuoi);
                break;
            case 3:
                txtPriceCard.setText(R.string.nammuoi);
                break;
            case 4:
                txtPriceCard.setText(R.string.mottram);
                break;
            case 5:
                txtPriceCard.setText(R.string.haitram);
                break;
            case 6:
                txtPriceCard.setText(R.string.batram);
                break;
            case 7:
                txtPriceCard.setText(R.string.namtram);
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void onClickCardType(int position) {
        CardType cardType = mCardTypeArrayList.get(position);
        for (int i =0;i<mCardTypeArrayList.size();i++) {
            CardType cardType1 = mCardTypeArrayList.get(i);
            if (cardType.getNameHomeNetWork().equals(cardType1.getNameHomeNetWork())){
                cardType1.setWatch(!cardType.isWatch());
            }else {
                cardType1.setWatch(false);
            }
        }
        mWorkAdapter.notifyDataSetChanged();
    }
}
