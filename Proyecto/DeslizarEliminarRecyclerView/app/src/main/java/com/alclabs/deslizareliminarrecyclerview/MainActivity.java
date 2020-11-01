package com.alclabs.deslizareliminarrecyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.alclabs.deslizareliminarrecyclerview.Adaptadores.RecyclerViewPersonasAdapter;
import com.alclabs.deslizareliminarrecyclerview.CallBacks.MyItemTouchHelperCallback;
import com.alclabs.deslizareliminarrecyclerview.Entidades.Persona;
import com.alclabs.deslizareliminarrecyclerview.Interfaces.CallBackItemTouch;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements CallBackItemTouch {
    RecyclerView recyclerView;
    RecyclerViewPersonasAdapter adapterRecyclerViewPersonas;
    ArrayList<Persona> listaPersonas;
    RelativeLayout layout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);
        layout = findViewById(R.id.layout_main_activity);
        listaPersonas = new ArrayList<>();
        cargarLista();
        mostrarData();

    }

    public void cargarLista(){
        listaPersonas.add(new Persona("Gohan","descripcion general",R.drawable.uno_gohan));
        listaPersonas.add(new Persona("Goku","descripcion general",R.drawable.dos_goku));
        listaPersonas.add(new Persona("Goten","descripcion general",R.drawable.tres_goten));
        listaPersonas.add(new Persona("Krilin","descripcion general",R.drawable.cuatro_krilin));
        listaPersonas.add(new Persona("Picoro","descripcion general",R.drawable.cinco_picoro));
        listaPersonas.add(new Persona("Trunks","descripcion general",R.drawable.seis_trunks));
        listaPersonas.add(new Persona("Vegueta","descripcion general",R.drawable.siete_vegueta));

    }
    public void mostrarData(){
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapterRecyclerViewPersonas = new RecyclerViewPersonasAdapter(listaPersonas);
        recyclerView.setAdapter(adapterRecyclerViewPersonas);
        ItemTouchHelper.Callback callback = new MyItemTouchHelperCallback(this);
        ItemTouchHelper touchHelper = new ItemTouchHelper(callback);
        touchHelper.attachToRecyclerView(recyclerView);
    }

    @Override
    public void itemTouchOnMode(int oldPosition, int newPosition) {
        listaPersonas.add(newPosition, listaPersonas.remove(oldPosition));
        adapterRecyclerViewPersonas.notifyItemMoved(oldPosition, newPosition);
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int position) {
        String nombre = listaPersonas.get(viewHolder.getAdapterPosition()).getNombres();
        //backup del item que se elimina para luego hacer cancelar
        final Persona deletedItem = listaPersonas.get(viewHolder.getAdapterPosition());
        final int deletedIndex = viewHolder.getAdapterPosition();
        //remover el item del recyclerview
        adapterRecyclerViewPersonas.removeItem(viewHolder.getAdapterPosition());
        //Mostrar el snackbar para hacer undo/cancelar
        Snackbar snackbar = Snackbar.make(layout, nombre +" => Eliminado", Snackbar.LENGTH_LONG);
        snackbar.setAction("CANCELAR/UNDO", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapterRecyclerViewPersonas.restoreItem(deletedItem, deletedIndex);
            }
        });
        snackbar.setActionTextColor(Color.GREEN);
        snackbar.show();

    }
}
