package Apps.TL01E11207.aplicacion;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.cdp.nuestraprimeraagenda.R;

import Apps.TL01E11207.aplicacion.db.DbContactos;

public class NuevoActivity extends AppCompatActivity {

    EditText txtNombre,txtTelefono,txtpais,txtnota;
    Button btnGuarda;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo);




        txtNombre = findViewById(R.id.txtnombre);
        txtTelefono = findViewById(R.id.txtelefono);
        txtpais = findViewById(R.id.txtpais);
        txtnota = findViewById(R.id.txtnota);
        btnGuarda = findViewById(R.id.btnguardar);

        btnGuarda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!txtNombre.getText().toString().equals("") && !txtTelefono.getText().toString().equals("")  ) {

                    DbContactos dbContactos = new DbContactos(NuevoActivity.this);
                    long id = dbContactos.insertarContacto(txtNombre.getText().toString(),txtTelefono.getText().toString(), txtpais.getText().toString(),txtnota.getText().toString());

                    if (id > 0) {
                        Toast.makeText(NuevoActivity.this, "CONTACTO GUARDADO", Toast.LENGTH_LONG).show();
                        limpiar();
                    } else {
                        Toast.makeText(NuevoActivity.this, "ERROR AL GUARDAR EL CONTACTO", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(NuevoActivity.this, "FAVOR LLENAR LOS CAMPOS VACIOS", Toast.LENGTH_LONG).show();
                }
            }
        });


    }

    private void limpiar() {
        txtNombre.setText("");
        txtTelefono.setText("");
        txtpais.setText("");
        txtnota.setText("");
    }
}