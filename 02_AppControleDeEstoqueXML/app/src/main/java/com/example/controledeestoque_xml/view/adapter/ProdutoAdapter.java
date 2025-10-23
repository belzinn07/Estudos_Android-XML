package com.example.controledeestoque_xml.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.controledeestoque_xml.R;
import com.example.controledeestoque_xml.model.Produto;


import java.util.ArrayList;
import java.util.List;

public class ProdutoAdapter extends RecyclerView.Adapter<ProdutoAdapter.ProdutoViewHolder> {
    private List<Produto> produtos = new ArrayList<>();
    private OnItemClickListener listener;

    @NonNull
    @Override
    public ProdutoAdapter.ProdutoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_produto,parent,false);
        return new ProdutoViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(@NonNull ProdutoAdapter.ProdutoViewHolder holder, int position) {
        Produto produtoAtual = produtos.get(position);
        holder.bind(produtoAtual);

    }

    @Override
    public int getItemCount() {
        return produtos.size();
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;

        notifyDataSetChanged();
    }


    class ProdutoViewHolder extends  RecyclerView.ViewHolder{
        private final TextView textNome;
        private final TextView textPreco;
        private final  TextView textQuantidade;

        public  ProdutoViewHolder(@NonNull View itemView){
            super(itemView);

            textNome = itemView.findViewById(R.id.textNome);
            textPreco = itemView.findViewById(R.id.textPreco);
            textQuantidade = itemView.findViewById(R.id.textQuantidade);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(produtos.get(position));
                    }
                }
            });
        }

        public void bind(Produto produto){
            textNome.setText(produto.getNome());
            textPreco.setText(String.format("Preço : R$%.2f",produto.getPrecoUnitario()));
            textQuantidade.setText(String.format("Quantidade: %d",produto.getQuantidade()));
        }

    }


    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}
