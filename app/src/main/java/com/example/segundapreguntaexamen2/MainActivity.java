package com.example.segundapreguntaexamen2;

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
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    EditText cajaAngulo, cajaRadio;
    ImageView imageView;
    Button btnCalcular;
    LinearLayout mainLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cajaAngulo = findViewById(R.id.cajaAngulo);
        cajaRadio = findViewById(R.id.cajaRadio);
        btnCalcular = findViewById(R.id.btnCalcular);
        imageView = findViewById(R.id.imageradio);
        mainLayout = findViewById(R.id.main);

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

        public void actualizar(float angulo, float radio) {
            this.angulo = angulo;
            this.radio = radio;
            invalidate(); // Solicitar redibujar la vista
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
            canvas.drawArc(centerX - radio, centerY - radio, centerX + radio, centerY + radio, startAngle, sweepAngle, true, paintFill);
        }
    }

    public void evento(View view) {
        if (view.getId() == R.id.btnCalcular) {
            // Verificar si los campos de texto están vacíos
            if (cajaAngulo.getText().toString().isEmpty() || cajaRadio.getText().toString().isEmpty()) {
                // Mostrar un mensaje al usuario
                Toast.makeText(MainActivity.this, "Por favor, ingrese el angulo y el radio.", Toast.LENGTH_SHORT).show();
            } else {
                // Proceder con el cálculo y visualización del sector circular
                float angulo = Float.parseFloat(cajaAngulo.getText().toString());
                float radio = Float.parseFloat(cajaRadio.getText().toString());

                // Vaciar las cajas de texto
                cajaAngulo.setText("");
                cajaRadio.setText("");

                // Crear y agregar la nueva vista SectorCircularView
                SectorCircularView sectorCircularView = new SectorCircularView(MainActivity.this, angulo, radio);
                mainLayout.removeAllViews();
                mainLayout.addView(sectorCircularView);

                // Ajustar el tamaño de SectorCircularView
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT);
                sectorCircularView.setLayoutParams(params);
            }
        }
    }
}
