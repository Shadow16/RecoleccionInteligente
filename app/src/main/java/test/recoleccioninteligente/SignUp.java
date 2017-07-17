package test.recoleccioninteligente;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import prefs.UserInfo;
import prefs.UserSession;

public class SignUp extends AppCompatActivity {
    private String TAG = SignUp.class.getSimpleName();
    private EditText curp, nombre, apellidos, telefono, email, password, municipio;
    private Button signup;
    private ProgressDialog progressDialog;
    private UserSession session;
    private UserInfo userInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        curp            = (EditText) findViewById(R.id.curp);
        nombre          = (EditText)findViewById(R.id.nombre);
        apellidos       = (EditText)findViewById(R.id.apellidos);
        telefono        = (EditText)findViewById(R.id.telefono);
        email           = (EditText)findViewById(R.id.email);
        password        = (EditText)findViewById(R.id.password);
        municipio       = (EditText)findViewById(R.id.municipio);

        signup          = (Button)findViewById(R.id.signup);
        progressDialog  = new ProgressDialog(this);
        session         = new UserSession(this);
        userInfo        = new UserInfo(this);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ucurp = curp.getText().toString().toUpperCase().trim();
                String uName = nombre.getText().toString().trim();
                String uapellido = apellidos.getText().toString().trim();
                String utel = telefono.getText().toString().trim();
                String mail  = email.getText().toString().trim();
                String pass  = password.getText().toString().trim();
                String umun = municipio.getText().toString().trim();

                if(ucurp.isEmpty() || uName.isEmpty() || uapellido.isEmpty() || utel.isEmpty() || mail.isEmpty() || pass.isEmpty() || umun.isEmpty()){
                       toast("Todos los campos son obligatorios!");
                } else {
                    signup(ucurp, uName, uapellido, utel, mail, pass, umun);
                }
            }
        });
    }

    private void signup(final String curp, final String nombre, final String apellido, final String telefono, final String email, final String password, final String municipio){
        // Tag used to cancel the request
        String tag_string_req = "req_signup";
        progressDialog.setMessage("Signing up...");
        progressDialog.show();

        StringRequest strReq = new StringRequest(Request.Method.POST, Utils.REGISTER_URL, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Register Response: " + response.toString());

                try {
                    JSONObject jObj = new JSONObject(response);
                    boolean error = jObj.getBoolean("error");

                    // Check for error node in json
                    if (!error) {
                        JSONObject user = jObj.getJSONObject("user");
                        //String ucurp = user.getString("curp");
                        String uName = user.getString("nombre");
                        //String apellidoP = user.getString("apellidoP");
                        //String apellidoM = user.getString("apellidoM");
                        //String utel = user.getString("telefono");
                        String email = user.getString("email");
                       // String upass = user.getString("password");
                       // String umun = user.getString("municipio");

                        // Inserting row in users table
                        userInfo.setEmail(email);
                        userInfo.setUsername(uName);
                        session.setLoggedin(true);

                        startActivity(new Intent(SignUp.this, MainActivity.class));
                    } else {
                        // Error in login. Get the error message
                        String errorMsg = jObj.getString("error_msg");
                        toast(errorMsg);
                    }
                } catch (JSONException e) {
                    // JSON error
                    e.printStackTrace();
                    toast("Json error: " + e.getMessage());
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Login Error: " + error.getMessage());
                toast("Unknown Error occurred");
                progressDialog.hide();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting parameters to login url
                Map<String, String> params = new HashMap<>();
                params.put("curp", curp);
                params.put("nombre", nombre);
                params.put("apellidos", apellido);
                params.put("telefono", telefono);
                params.put("email", email);
                params.put("password", password);
                params.put("municipio", municipio);

                return params;
            }

        };

        // Adding request to request queue
        AndroidLoginController.getInstance().addToRequestQueue(strReq, tag_string_req);
    }

    private void toast(String x){
        Toast.makeText(this, x, Toast.LENGTH_SHORT).show();
    }
}
