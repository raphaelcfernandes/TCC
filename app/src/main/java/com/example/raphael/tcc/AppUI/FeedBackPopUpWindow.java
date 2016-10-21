package com.example.raphael.tcc.AppUI;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;

import com.example.raphael.tcc.Managers.BrightnessManager;
import com.example.raphael.tcc.Managers.CpuManager;
import com.example.raphael.tcc.R;

import org.adw.library.widgets.discreteseekbar.DiscreteSeekBar;


/**
 * Created by rapha on 18-Sep-16.
 */
public class FeedBackPopUpWindow extends Activity {
    private boolean isProgressBarBrightnessMoved=false;
    private boolean isProgressBarCpuMoved=false;
    DiscreteSeekBar seekBarBrightness;
    DiscreteSeekBar seekBarCpu;
    BrightnessManager brightnessManager = new BrightnessManager();
    CpuManager cpuManager = new CpuManager();
    @Override
    public void onCreate(Bundle savedInstancedBundle){
        super.onCreate(savedInstancedBundle);
        setContentView(R.layout.custom_dialog);

        /**
         * SeekBark Brightness
         */
        seekBarBrightness = (DiscreteSeekBar) findViewById(R.id.seekBarBrightness);
        seekBarBrightness.setMin(0);
        seekBarBrightness.setProgress((brightnessManager.getScreenBrightnessLevel()*100)/255);
        seekBarBrightness.setMax(100);//Percentage
        seekBarBrightness.setOnProgressChangeListener(new DiscreteSeekBar.OnProgressChangeListener() {
            int onProgressChanged=0;
            @Override
            public void onProgressChanged(DiscreteSeekBar seekBar, int value, boolean fromUser) {
                onProgressChanged = value;
                isProgressBarBrightnessMoved=true;
            }

            @Override
            public void onStartTrackingTouch(DiscreteSeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(DiscreteSeekBar seekBar) {
            }
        });


        /**
         * SeekBar CPU
         */
        seekBarCpu = (DiscreteSeekBar) findViewById(R.id.seekBarCpu);
        seekBarCpu.setMin(1);
        seekBarCpu.setProgress(cpuManager.getSumNumberCore());
        seekBarCpu.setMax(100);
        seekBarCpu.setOnProgressChangeListener(new DiscreteSeekBar.OnProgressChangeListener() {
            int onProgressChanged=0;
            @Override
            public void onProgressChanged(DiscreteSeekBar seekBar, int value, boolean fromUser) {
                onProgressChanged = value;
                isProgressBarCpuMoved=true;
            }

            @Override
            public void onStartTrackingTouch(DiscreteSeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(DiscreteSeekBar seekBar) {
            }
        });

        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.x = -20;
        params.height = 500;
        params.width = 550;
        params.y = -10;
        this.getWindow().setAttributes(params);
    }
    public void onResume(){
        super.onResume();
    }
    public void onDestroy(){
        super.onDestroy();
        if(isProgressBarBrightnessMoved==true) {
            if(seekBarBrightness.getProgress()==0)
                brightnessManager.setBrightnessLevel(255/100);
            else
                brightnessManager.setBrightnessLevel((seekBarBrightness.getProgress() * 255) / 100);
        }
        //Create Intent and send to BackgroundService -> ButtonClicked
        /*if(isClicked==true) {
            Intent i = new Intent("com.example.raphael.tcc.REQUESTED_MORE_CPU");
            sendBroadcast(i);
        }*/
    }
}
