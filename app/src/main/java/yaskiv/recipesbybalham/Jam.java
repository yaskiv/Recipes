package yaskiv.recipesbybalham;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class Jam extends AppCompatActivity {
    public List<JamAssortment> jams;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jam);

        Intent intent = this.getIntent();
        int position = intent.getIntExtra("position", 1000);
        jams=intent.getParcelableArrayListExtra("fdf");
        ImageView image=(ImageView)findViewById(R.id.jam2_image);
        TextView text=(TextView)findViewById(R.id.description);
        Button button=(Button) findViewById(R.id.buy);

setTitle( jams.get(position).getName());
        String mobile ="Jam/"+ jams.get(position).getImageUrl2();
        Bitmap myBitmap = null;
        try {
            InputStream istr = this.getAssets().open(mobile);
            myBitmap = BitmapFactory.decodeStream(istr);

        } catch (IOException e) {
            e.printStackTrace();
        }
        image.setImageBitmap(myBitmap);
        text.setText(jams.get(position).getDescription());
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://xn--80adxqm7e.xn--p1ai/")));
            }
        });


    }
}
