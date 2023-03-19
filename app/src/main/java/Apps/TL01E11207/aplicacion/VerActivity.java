package Apps.TL01E11207.aplicacion;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.cdp.nuestraprimeraagenda.R;

import Apps.TL01E11207.aplicacion.db.DbContactos;
import Apps.TL01E11207.aplicacion.entidades.Contactos;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class VerActivity extends AppCompatActivity {

    EditText txtNombre, txtTelefono, txtpais;
    private Button btn;
    Button btnGuarda,button,btnllamar,btncompartir;

    FloatingActionButton fabEditar, fabEliminar;

    Contactos contacto;
    int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver);

        txtNombre = findViewById(R.id.txtNombre);
        txtpais = findViewById(R.id.txtpais);
        fabEditar = findViewById(R.id.fabEditar);
        fabEliminar = findViewById(R.id.fabEliminar);
        btnGuarda = findViewById(R.id.btnGuarda);
        btnGuarda.setVisibility(View.INVISIBLE);
         button=findViewById(R.id.btncompartir);
        btnllamar=findViewById(R.id.btnllamar);
        txtTelefono = findViewById(R.id.txtTelefono);
        btncompartir=findViewById(R.id.btncompartir);

        btncompartir.setOnClickListener(new View.OnClickListener() {
            private ContentValues values;

            @Override
            public void onClick(View view) {
               
                //Uri uri = getContentResolver().insert(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, values);
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType(ContactsContract.Contacts.CONTENT_ITEM_TYPE);
                intent.putExtra(ContactsContract.Intents.Insert.NAME, txtNombre.getText().toString());
               intent.putExtra(ContactsContract.Intents.Insert.PHONE, txtTelefono.getText().toString());

                startActivity(Intent.createChooser(intent, "Compartir contacto"));
               // Intent compartir = new Intent(android.content.Intent.ACTION_SEND);
                //compartir.setType("text/plain");
                //compartir.putExtra(android.content.Intent.EXTRA_SUBJECT,"Empleos Baja App");
                //compartir.putExtra(android.content.Intent.EXTRA_TEXT,txtNombre+" "+txtTelefono);
                //startActivity(Intent.createChooser(compartir, "Compartir vía"));


            }
        });


        btnllamar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(VerActivity.this);


                builder.setMessage("¿Está seguro de que desea llamar a esta persona?");
                builder.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Intent intent=new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+txtTelefono.getText().toString()));
                        startActivity(intent);
                    }
                });
                builder.setNegativeButton("No", null);
                AlertDialog dialog = builder.create();
                dialog.show();



            }
        });


        if(savedInstanceState == null){
            Bundle extras = getIntent().getExtras();
            if(extras == null){
                id = Integer.parseInt(null);
            } else {
                id = extras.getInt("ID");
            }
        } else {
            id = (int) savedInstanceState.getSerializable("ID");
        }

        final DbContactos dbContactos = new DbContactos(VerActivity.this);
        contacto = dbContactos.verContacto(id);

        if(contacto != null){
            txtNombre.setText(contacto.getNombre());
            txtTelefono.setText(contacto.getTelefono());
            txtpais.setText(contacto.getPais());
            //txtnota.setText(contacto.getNota());
            txtNombre.setInputType(InputType.TYPE_NULL);
            txtTelefono.setInputType(InputType.TYPE_NULL);
            txtpais.setInputType(InputType.TYPE_NULL);
            //txtnota.setInputType(InputType.TYPE_NULL);





        }




        fabEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(VerActivity.this, EditarActivity.class);
                intent.putExtra("ID", id);
                startActivity(intent);
            }
        });


        fabEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(VerActivity.this);
                builder.setMessage("¿Desea eliminar este contacto?")
                        .setPositiveButton("SI", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                if(dbContactos.eliminarContacto(id)){
                                    lista();
                                }
                            }
                        })
                        .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        }).show();
            }
        });

    }
    private void share(){

    }





    private void lista(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }


}