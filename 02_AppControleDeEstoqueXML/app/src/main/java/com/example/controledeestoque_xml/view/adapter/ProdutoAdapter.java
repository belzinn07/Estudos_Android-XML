package com.example.controledeestoque_xml.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.controledeestoque_xml.R;
import com.example.controledeestoque_xml.model.Produto;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class ProdutoAdapter extends  RecyclerView.Adapter<ProdutoAdapter.ProdutoViewHolder>{

    private List<Produto> listaDeProdutos;
    private OnItemClickListener listener;

    public ProdutoAdapter(){

    }

    @NonNull
    @Override
    public ProdutoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_produto, parent, false);
        return new ProdutoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProdutoViewHolder holder, int position) {
        Produto produto = listaDeProdutos.get(position);
        holder.textnome.setText("Nome: " + produto.getNome());
        holder.textQuantidade.setText("Quantidade: " + produto.getQuantidade());

        String precoFormatado = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"))
                .format(produto.getPrecoUnitario());

        holder.textPreco.setText("Preço: " + precoFormatado);

    }

    @Override
    public int getItemCount() {
        return listaDeProdutos.size();

    }

    public void setProdutos(List<Produto> novosProdutos) {
        this.listaDeProdutos = novosProdutos;
        notifyDataSetChanged();
    }

    public Produto getProdutoPosicao(int posicao){
        return listaDeProdutos.get(posicao);
    }


    class ProdutoViewHolder extends RecyclerView.ViewHolder{
        TextView textnome;
        TextView textPreco;
        TextView textQuantidade;

        public ProdutoViewHolder(@NonNull View itemView){
            super(itemView);
            textnome = itemView.findViewById(R.id.textNome);
            textPreco = itemView.findViewById(R.id.textPreco);
            textQuantidade = itemView.findViewById(R.id.textQuantidade);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int posicao = getBindingAdapterPosition();
                    if (listener != null && posicao != RecyclerView.NO_POSITION){
                        listener.onItemClick(listaDeProdutos.get(posicao));
                    }

                }
            });
        }


    }

    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener =listener;
    }


}
