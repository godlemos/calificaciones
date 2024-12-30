package com.example.calificaciones;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.*;
import java.util.*;

public class MainActivity extends AppCompatActivity {

    class Alumno {
        String dni;
        String apellidos;
        String nombre;
        double nota;
        String calificacion;

        public Alumno(String dni, String apellidos, String nombre, double nota) {
            this.dni = dni;
            this.apellidos = apellidos;
            this.nombre = nombre;
            setNota(nota);
        }

        public void setNota(double nota) {
            this.nota = nota;
            if (nota < 5) {
                this.calificacion = "SS";
            } else if (nota < 7) {
                this.calificacion = "AP";
            } else if (nota < 9) {
                this.calificacion = "NT";
            } else {
                this.calificacion = "SB";
            }
        }

        @Override
        public String toString() {
            return dni + " " + apellidos + ", " + nombre + " " + nota + " " + calificacion;
        }
    }

    private List<Alumno> alumnos = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText inputDni = findViewById(R.id.inputDni);
        EditText inputApellidos = findViewById(R.id.inputApellidos);
        EditText inputNombre = findViewById(R.id.inputNombre);
        EditText inputNota = findViewById(R.id.inputNota);
        Button btnAgregar = findViewById(R.id.btnAgregar);
        Button btnMostrar = findViewById(R.id.btnMostrar);
        TextView txtResultado = findViewById(R.id.txtResultado);

        btnAgregar.setOnClickListener(v -> {
            String dni = inputDni.getText().toString();
            String apellidos = inputApellidos.getText().toString();
            String nombre = inputNombre.getText().toString();
            String notaStr = inputNota.getText().toString();

            if (dni.isEmpty() || apellidos.isEmpty() || nombre.isEmpty() || notaStr.isEmpty()) {
                Toast.makeText(this, "Por favor, rellene todos los campos", Toast.LENGTH_SHORT).show();
                return;
            }

            double nota;
            try {
                nota = Double.parseDouble(notaStr);
            } catch (NumberFormatException e) {
                Toast.makeText(this, "Nota inválida", Toast.LENGTH_SHORT).show();
                return;
            }

            for (Alumno a : alumnos) {
                if (a.dni.equals(dni)) {
                    Toast.makeText(this, "Ya existe un alumno con este DNI", Toast.LENGTH_SHORT).show();
                    return;
                }
            }

            alumnos.add(new Alumno(dni, apellidos, nombre, nota));
            Toast.makeText(this, "Alumno agregado", Toast.LENGTH_SHORT).show();
            limpiarCampos(inputDni, inputApellidos, inputNombre, inputNota);
        });

        btnMostrar.setOnClickListener(v -> {
            StringBuilder resultado = new StringBuilder();
            for (Alumno a : alumnos) {
                resultado.append(a.toString()).append("\n");
            }
            txtResultado.setText(resultado.toString());
        });
    }

    private void limpiarCampos(EditText... campos) {
        for (EditText campo : campos) {
            campo.setText("");
        }
    }
}