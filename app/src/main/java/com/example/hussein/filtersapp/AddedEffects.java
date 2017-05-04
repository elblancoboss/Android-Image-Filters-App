package com.example.hussein.filtersapp;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.ImageView;

/**
 * Created by Hussein on 24/03/2017.
 */

public class AddedEffects extends Activity{

    String path;
    String effect_chosen;
    ImageView changed;
    Bitmap out;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.finalview);

        Intent i=getIntent();
        path=i.getStringExtra("path");
        effect_chosen=i.getStringExtra("effect");

        changed=(ImageView)findViewById(R.id.view);


        Bitmap thumbnail = (BitmapFactory.decodeFile(path));

        if(effect_chosen.equalsIgnoreCase("tint"))
        {
            out=addEffect(thumbnail,5,5.0,6.0,0.0);  //red,green,no blue
        }

        else if(effect_chosen.equalsIgnoreCase("violet"))
        {
            out=addEffect(thumbnail,5,5.0,0.0,10.0);  //red,blue,no green
        }

        else if(effect_chosen.equalsIgnoreCase("Green effect"))
        {
            out=addEffect(thumbnail,5,0.0,10.0,0.0);  //only green
        }

        else if(effect_chosen.equalsIgnoreCase("Amaro"))
        {
            out=addEffect(thumbnail,15,5.0,0.0,10.0);  //red,blue,no green, depth increased
        }

        else if(effect_chosen.equalsIgnoreCase("RedEye"))
        {
            out=addEffect(thumbnail,5,10.0,0.0,0.0);  //only red
        }

        else if(effect_chosen.equalsIgnoreCase("greyscale"))
        {
            out=addEffect(thumbnail,5,10.0,10.0,10.0);  //depth, red blue and green
        }

        else if(effect_chosen.equalsIgnoreCase("winter"))
        {
            out=addEffect(thumbnail,15,5.0,5.0,10.0);  //depth increased, red,green, blue
        }

        else if(effect_chosen.equalsIgnoreCase("Willow"))
        {
            out=addEffect(thumbnail,20,0.0,0.0,0.0);  //Increased Depth
        }

        else if(effect_chosen.equalsIgnoreCase("Warm"))
        {
            out=addEffect(thumbnail,15,10.0,5.0,0.0);  //depth, red and green, no blue
        }

        else if(effect_chosen.equalsIgnoreCase("Hudson"))
        {
            out=addEffect(thumbnail,5,10.0,5.0,15.0);  //red,green, increased blue blue
        }

        changed.setImageBitmap(out);

    }


    public static Bitmap addEffect(Bitmap src, int depth, double red, double green, double blue) {
        int width = src.getWidth();
        int height = src.getHeight();
        Bitmap finalBitmap = Bitmap.createBitmap(width, height, src.getConfig());
        final double grayScale_Red = 0.3;
        final double grayScale_Green = 0.59;
        final double grayScale_Blue = 0.11;
        int channel_aplha, channel_red, channel_green, channel_blue;
        int pixel;

        for(int x = 0; x < width; ++x) {
            for(int y = 0; y < height; ++y) {
                pixel = src.getPixel(x, y);
                channel_aplha = Color.alpha(pixel);
                channel_red = Color.red(pixel);
                channel_green = Color.green(pixel);
                channel_blue = Color.blue(pixel);
                channel_blue = channel_green = channel_red = (int)(grayScale_Red * channel_red + grayScale_Green * channel_green + grayScale_Blue * channel_blue);

                channel_red += (depth * red);
                if(channel_red > 255)
                {
                    channel_red = 255;
                }

                channel_green += (depth * green);
                if(channel_green > 255)
                {
                    channel_green = 255;
                }

                channel_blue += (depth * blue);
                if(channel_blue > 255)
                {
                    channel_blue = 255;
                }

                finalBitmap.setPixel(x, y, Color.argb(channel_aplha, channel_red, channel_green, channel_blue));
            }
        }

        return finalBitmap;
    }
}
