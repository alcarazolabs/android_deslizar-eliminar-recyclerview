package com.alclabs.deslizareliminarrecyclerview.Adaptadores;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.alclabs.deslizareliminarrecyclerview.Entidades.Persona;
import com.alclabs.deslizareliminarrecyclerview.R;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class RecyclerViewPersonasAdapter  extends RecyclerView.Adapter<RecyclerViewPersonasAdapter.ViewHolder> {

    ArrayList<Persona> model;

    public RecyclerViewPersonasAdapter(ArrayList<Persona> model) {
        this.model = model;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_personas, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.nombres.setText(model.get(position).getNombres());
        holder.descripcion.setText(model.get(position).getDescripcion());
        holder.imagen.setImageResource(model.get(position).getImagenid());
    }

    @Override
    public int getItemCount() {
        if(model == null){
            return 0;
        }else{
            return model.size();
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
         CircleImageView imagen;
         TextView nombres, descripcion;
         public RelativeLayout viewF, viewB;
         public ViewHolder(@NonNull View itemView) {
             super(itemView);
             imagen = itemView.findViewById(R.id.imagenPersona);
             nombres = itemView.findViewById(R.id.txtNombre);
             descripcion = itemView.findViewById(R.id.txtDescripcion);
             viewF = itemView.findViewById(R.id.rl);
             viewB = itemView.findViewById(R.id.view_background);

         }
     }

     public void removeItem(int position){
        model.remove(position);
        notifyItemRemoved(position);
     }
     public void restoreItem(Persona item, int position){
        model.add(position, item);
        notifyItemInserted(position);
     }





}
