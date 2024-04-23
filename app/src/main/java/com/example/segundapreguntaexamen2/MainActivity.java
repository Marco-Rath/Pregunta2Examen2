package com.example.segundapreguntaexamen2;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.content.Context;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    EditText cajaAngulo, cajaRadio;
    ImageView imageView;
    Button btnCalcular;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cajaAngulo = findViewById(R.id.cajaAngulo);
        cajaRadio = findViewById(R.id.cajaRadio);
        btnCalcular = findViewById(R.id.btnCalcular);
        imageView = findViewById(R.id.imageradio);

        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                evento(view);
            }
        });
    }

    public class SectorCircularView extends View {

        private Paint paintStroke;
        private Paint paintFill;
        private float angulo;
        private float radio;

        public SectorCircularView(Context context, float angulo, float radio) {
            super(context);
            this.angulo = angulo;
            this.radio = radio;

            paintStroke = new Paint();
            paintStroke.setStyle(Paint.Style.STROKE);
            paintStroke.setColor(Color.BLUE);
            paintStroke.setStrokeWidth(5f);

            paintFill = new Paint();
            paintFill.setStyle(Paint.Style.FILL);
            paintFill.setColor(Color.RED);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);

            float startAngle = -90; // Empieza en el eje horizontal y positivo
            float sweepAngle = angulo;

            float centerX = getWidth() / 2;
            float centerY = getHeight() / 2;

            // Dibuja el círculo con el borde azul
            canvas.drawCircle(centerX, centerY, radio, paintStroke);

            // Dibuja el sector circular con el fondo rojo
            Path path = new Path();
            path.addArc(centerX - radio, centerY - radio, centerX + radio, centerY + radio, startAngle, sweepAngle);
            canvas.drawPath(path, paintFill);
        }
    }

    public void evento(View view) {
        if(view.getId()==R.id.btnCalcular){
            float angulo = Float.parseFloat(cajaAngulo.getText().toString());
            float radio = Float.parseFloat(cajaRadio.getText().toString());

            LinearLayout mainLayout = findViewById(R.id.main);
            SectorCircularView sectorCircularView = new SectorCircularView(MainActivity.this, angulo, radio);
            mainLayout.addView(sectorCircularView);
            mainLayout.requestLayout();

        }
    }
}