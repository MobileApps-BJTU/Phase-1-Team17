package com.example.jnbl.main;


        import android.os.Bundle;
        import android.support.v4.app.Fragment;
        import android.support.v4.app.FragmentActivity;
        import android.support.v4.app.FragmentManager;
        import android.support.v4.app.FragmentTransaction;
        import android.view.View;
        import android.view.Window;
        import android.widget.Button;
        import android.widget.ImageButton;
        import android.widget.RadioButton;
        import android.widget.RadioGroup;
        import android.widget.RadioGroup.OnCheckedChangeListener;

public class MainActivity extends FragmentActivity {
    private Fragment[] mFragments;
   // private RadioGroup bottomRg;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private ImageButton rbOne, rbTwo, rbThree, rbFour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        mFragments = new Fragment[3];
        fragmentManager = getSupportFragmentManager();
        mFragments[0] = fragmentManager.findFragmentById(R.id.fragement_main);
        mFragments[1] = fragmentManager.findFragmentById(R.id.fragement_search);
        mFragments[2] = fragmentManager
                .findFragmentById(R.id.fragement_setting);
        fragmentTransaction = fragmentManager.beginTransaction()
                .hide(mFragments[0]).hide(mFragments[1]).hide(mFragments[2]);
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
                        .hide(mFragments[2]);
                fragmentTransaction.show(mFragments[0]).commit();
            }
        });

        rbTwo.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v) {
                //对话框   Builder是AlertDialog的静态内部类
                fragmentTransaction = fragmentManager.beginTransaction()
                        .hide(mFragments[0]).hide(mFragments[1])
                        .hide(mFragments[2]);
                fragmentTransaction.show(mFragments[1]).commit();
            }
        });

        rbThree.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v) {
                //对话框   Builder是AlertDialog的静态内部类
                fragmentTransaction = fragmentManager.beginTransaction()
                        .hide(mFragments[0]).hide(mFragments[1])
                        .hide(mFragments[2]);
                fragmentTransaction.show(mFragments[2]).commit();
            }
        });
    }

}