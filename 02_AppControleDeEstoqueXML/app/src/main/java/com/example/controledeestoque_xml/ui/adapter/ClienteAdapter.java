package com.example.controledeestoque_xml.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.controledeestoque_xml.R;
import com.example.controledeestoque_xml.data.local.entities.Cliente;

import java.util.ArrayList;
import java.util.List;

public class ClienteAdapter extends RecyclerView.Adapter<ClienteAdapter.ClienteViewHolder> {

    private List<Cliente> listaDeClientes = new ArrayList<>();
    private OnItemClickListener listener;
    private OnExcluirItemListener onExcluirCliente;




    @NonNull
    @Override
    public ClienteAdapter.ClienteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_clientes, parent, false);
        return new ClienteAdapter.ClienteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ClienteAdapter.ClienteViewHolder holder, int position) {
        Cliente clienteAtual = listaDeClientes.get(position);

        holder.textNome.setText("Nome: " + clienteAtual.getNome());
        holder.textEmail.setText("Email: " + clienteAtual.getEmail());
        holder.textTelefone.setText("Telefone: " +clienteAtual.getTelefone());

        holder.btnExcluir.setOnClickListener(v -> {
            if (onExcluirCliente != null ) onExcluirCliente.onExcluir(clienteAtual);
        });
    }

    @Override
    public int getItemCount() {
        return listaDeClientes.size();
    }

    class ClienteViewHolder extends RecyclerView.ViewHolder{
        TextView textNome;
        TextView textEmail;
        TextView textTelefone;
        ImageButton btnExcluir;

        public ClienteViewHolder( View itemView){
            super(itemView);

            textNome = itemView.findViewById(R.id.textNome);
            textEmail = itemView.findViewById(R.id.textEmail);
            textTelefone = itemView.findViewById(R.id.textTelefone);
            btnExcluir = itemView.findViewById(R.id.btnExcluir);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getBindingAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION){
                        listener.onItemClick(listaDeClientes.get(position));
                    }
                }
            });

        }

    }
}
