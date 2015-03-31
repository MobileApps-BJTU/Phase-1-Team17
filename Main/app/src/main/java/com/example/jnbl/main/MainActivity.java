package com.example.jnbl.main;


        import android.app.AlertDialog;
        import android.app.Dialog;
        import android.content.DialogInterface;
        import android.os.Bundle;
        import android.support.v4.app.Fragment;
        import android.support.v4.app.FragmentActivity;
        import android.support.v4.app.FragmentManager;
        import android.support.v4.app.FragmentTransaction;
        import android.view.View;
        import android.view.Window;
        import android.widget.AdapterView;
        import android.widget.Button;
        import android.widget.ImageButton;
        import android.widget.ListView;
        import android.widget.RadioButton;
        import android.widget.RadioGroup;
        import android.widget.RadioGroup.OnCheckedChangeListener;
        import android.widget.SimpleAdapter;

        import java.util.List;
        import java.util.Map;

public class MainActivity extends FragmentActivity {
    private Fragment[] mFragments;
   // private RadioGroup bottomRg;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private ImageButton rbOne, rbTwo, rbThree;
    private Button rbFour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        final SimpleAdapter adapter_public = new SimpleAdapter(this,ListItem.getData(),R.layout.list_item,
                new String[]{"image_detail","foodName","payment","address","phone","distance","time"},
                new int[]{R.id.image_detail,R.id.foodName,R.id.payment,R.id.address,R.id.phone,R.id.distance,R.id.time});
        final SimpleAdapter adapter_my = new SimpleAdapter(this,Takeouts.getData(),R.layout.list_item,
                new String[]{"image_detail","foodName","payment","address","phone","distance","time"},
                new int[]{R.id.image_detail,R.id.foodName,R.id.payment,R.id.address,R.id.phone,R.id.distance,R.id.time});

        RadioButton r_1_dis = (RadioButton)findViewById(R.id.radio_distance);
        RadioButton r_1_tim = (RadioButton)findViewById(R.id.radio_time);
        RadioButton r_2_dis = (RadioButton)findViewById(R.id.radio_distance2);
        RadioButton r_2_tim = (RadioButton)findViewById(R.id.radio_distance2);

        RadioGroup R_G_1 =(RadioGroup)findViewById(R.id.r_g_1) ;
        RadioGroup R_G_2 =(RadioGroup)findViewById(R.id.r_g_2) ;

        R_G_1.setOnCheckedChangeListener(new OnCheckedChangeListener(){

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == R.id.radio_distance){
                    ListItem.sort(0);
                    adapter_public.notifyDataSetChanged();
                }else{
                    ListItem.sort(1);
                    adapter_public.notifyDataSetChanged();
                }
            }
        });

        R_G_2.setOnCheckedChangeListener(new OnCheckedChangeListener(){

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == R.id.radio_distance){
                    Takeouts.sort(0);
                    adapter_my.notifyDataSetChanged();
                }else{
                    Takeouts.sort(1);
                    adapter_my.notifyDataSetChanged();
                }
            }
        });

        mFragments = new Fragment[4];
        fragmentManager = getSupportFragmentManager();
        mFragments[0] = fragmentManager.findFragmentById(R.id.fragement_main);


        ListView lv = (ListView) findViewById(R.id.listView);
        lv.setAdapter(adapter_public);
        ListView lv2 = (ListView) findViewById(R.id.listView2);
        lv2.setAdapter(adapter_my);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Map<String, Object> item = ListItem.getItem(position);
                if(item == null) {return;}
                else {
                    Takeouts.push(item);
                    ListItem.remove(position);
                    adapter_public.notifyDataSetChanged();
                    adapter_my.notifyDataSetChanged();
                }

            }
        });
        lv2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                Dialog alertDialog = new AlertDialog.Builder(MainActivity.this).
                        setTitle(R.string.tip).
                        setMessage(R.string.askForFinish).
                        setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Takeouts.remove(position);
                                adapter_my.notifyDataSetChanged();
                            }
                        }).
                        setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                return ;
                            }
                        }).
                        setNeutralButton(R.string.cancellation, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Map<String, Object> item = Takeouts.getItem(position);
                                ListItem.push(item);
                                Takeouts.remove(position);
                                adapter_public.notifyDataSetChanged();
                                adapter_my.notifyDataSetChanged();
                            }
                        }).
                        create();
                alertDialog.show();
            }
         });




        mFragments[1] = fragmentManager.findFragmentById(R.id.fragement_search);
        mFragments[2] = fragmentManager
                .findFragmentById(R.id.fragement_setting);
        mFragments[3] = fragmentManager.findFragmentById(R.id.fragment_withdraw);
        fragmentTransaction = fragmentManager.beginTransaction()
                .hide(mFragments[0]).hide(mFragments[1]).hide(mFragments[2]).hide(mFragments[3]);
        fragmentTransaction.show(mFragments[0]).commit();
        setFragmentIndicator();




    }

    private void setFragmentIndicator() {

      //  bottomRg = (RadioGroup) findViewById(R.id.bottomRg);
        rbOne = (ImageButton) findViewById(R.id.rbOne);
        rbTwo = (ImageButton) findViewById(R.id.rbTwo);
        rbThree = (ImageButton) findViewById(R.id.rbThree);

        rbOne.getBackground().setAlpha(0);
        rbTwo.getBackground().setAlpha(0);
        rbThree.getBackground().setAlpha(0);
        rbFour = (Button)findViewById(R.id.withdraw_button);

     /*   bottomRg.setOnCheckedChangeListener(new OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                fragmentTransaction = fragmentManager.beginTransaction()
                        .hide(mFragments[0]).hide(mFragments[1])
                        .hide(mFragments[2]);
                switch (checkedId) {
                    case R.id.rbOne:
                        fragmentTransaction.show(mFragments[0]).commit();
                        break;

                    case R.id.rbTwo:
                        fragmentTransaction.show(mFragments[1]).commit();
                        break;

                    case R.id.rbThree:
                        fragmentTransaction.show(mFragments[2]).commit();
                        break;

                    default:
                        break;
                }
            }
        });*/

        rbOne.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v) {
                //对话框   Builder是AlertDialog的静态内部类
                fragmentTransaction = fragmentManager.beginTransaction()
                        .hide(mFragments[0]).hide(mFragments[1])
                        .hide(mFragments[2]).hide(mFragments[3]);
                fragmentTransaction.show(mFragments[0]).commit();
            }
        });

        rbTwo.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v) {
                //对话框   Builder是AlertDialog的静态内部类
                fragmentTransaction = fragmentManager.beginTransaction()
                        .hide(mFragments[0]).hide(mFragments[1])
                        .hide(mFragments[2]).hide(mFragments[3]);
                fragmentTransaction.show(mFragments[1]).commit();
            }
        });

        rbThree.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v) {
                //对话框   Builder是AlertDialog的静态内部类
                fragmentTransaction = fragmentManager.beginTransaction()
                        .hide(mFragments[0]).hide(mFragments[1])
                        .hide(mFragments[2]).hide(mFragments[3]);
                fragmentTransaction.show(mFragments[2]).commit();
            }
        });

        rbFour.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v) {
                //对话框   Builder是AlertDialog的静态内部类
                fragmentTransaction = fragmentManager.beginTransaction()
                        .hide(mFragments[0]).hide(mFragments[1])
                        .hide(mFragments[2]).hide(mFragments[3]);
                fragmentTransaction.show(mFragments[3]).commit();

            }
        });
    }

}