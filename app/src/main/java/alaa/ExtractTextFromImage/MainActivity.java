package alaa.ExtractTextFromImage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.text.TextBlock;
import com.google.android.gms.vision.text.TextRecognizer;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextRecognizer textRecognizer = new TextRecognizer.Builder(getApplicationContext()).build();

        TextView mTextView = findViewById(R.id.textView);


        Bitmap bm = BitmapFactory.decodeResource(getResources(), R.drawable.img);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.JPEG, 100, baos); // bm is the bitmap object
        byte[] decodedString = baos.toByteArray();





//        byte[] decodedString = Base64.decode(String.valueOf(R.mipmap.img), Base64.NO_WRAP);
        InputStream input=new ByteArrayInputStream(decodedString);
        Bitmap ext_pic = BitmapFactory.decodeStream(input);

        Frame imageFrame = new Frame.Builder()

                .setBitmap(ext_pic)                 // your image bitmap
                .build();


        String imageText = "";


        SparseArray<TextBlock> textBlocks = textRecognizer.detect(imageFrame);

        for (int i = 0; i < textBlocks.size(); i++) {
            TextBlock textBlock = textBlocks.get(textBlocks.keyAt(i));
            imageText = textBlock.getValue();
            mTextView.append(imageText);
        }

        Log.i("alaasayed" , mTextView.getText().toString());
    }


}