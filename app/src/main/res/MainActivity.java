package es.vcarmen.socialtech;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);

        Button btn_alta=(Button)findViewById(R.id.btn_alta);
        Button btn_cancelar=(Button)findViewById(R.id.btn_cancelar);

        btn_alta.setOnClickListener(this);
        btn_cancelar.setOnClickListener(this);

    }

                                                                                                     @Override
    public void onClick(View view) {
        switch (view.getId()){

            case R.id.btn_alta:

                break;
            case R.id.btn_cancelar:

                break;


        }
    }
}
