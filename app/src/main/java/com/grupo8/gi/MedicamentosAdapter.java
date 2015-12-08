package com.grupo8.gi;


import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.List;

public class MedicamentosAdapter extends ArrayAdapter<Medicamento> {
    // View lookup cache
    private static class ViewHolder {
        TextView textViewIdMedicamento;
        TextView textViewNombreMedicamento;
        TextView textViewCantidadDisponible;
        TextView textViewLaboratorio;
    }

    public MedicamentosAdapter(Context context, List<Medicamento> medicamentos) {
        super(context, R.layout.item_medicamento, medicamentos);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Medicamento medicamento = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag
        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.item_medicamento, parent, false);

            viewHolder.textViewIdMedicamento = (TextView) convertView.findViewById(R.id.text_view_id_medicamento);
            viewHolder.textViewNombreMedicamento = (TextView) convertView.findViewById(R.id.text_view_nombre_medicamento);
            viewHolder.textViewCantidadDisponible = (TextView) convertView.findViewById(R.id.text_view_cantidad_disponible);
            viewHolder.textViewLaboratorio = (TextView) convertView.findViewById(R.id.text_view_laboratorio);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        // Populate the data into the template view using the data object
        viewHolder.textViewIdMedicamento.setText(String.valueOf(medicamento.getIdMedicamento()));
        viewHolder.textViewNombreMedicamento.setText(medicamento.getNombreMedicamento());
        viewHolder.textViewCantidadDisponible.setText(String.valueOf(medicamento.getCantidadDisponible()));
        viewHolder.textViewLaboratorio.setText(String.valueOf(medicamento.getLaboratorio()));

        if(medicamento.getCantidadDisponible() < 10){
            viewHolder.textViewCantidadDisponible.setTextColor(Color.RED);
        }else{
            viewHolder.textViewCantidadDisponible.setTextColor(Color.BLACK);
        }


        // Return the completed view to render on screen
        return convertView;
    }
}