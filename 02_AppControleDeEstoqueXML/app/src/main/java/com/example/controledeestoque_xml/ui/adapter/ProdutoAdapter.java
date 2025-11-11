package com.example.controledeestoque_xml.ui.adapter;

import static com.example.controledeestoque_xml.R.*;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.controledeestoque_xml.R;
import com.example.controledeestoque_xml.data.local.entities.Produto;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ProdutoAdapter extends  RecyclerView.Adapter<ProdutoAdapter.ProdutoViewHolder>{

    private List<Produto> listaDeProdutos = new ArrayList<>();
    private OnItemClickListener<Produto> listener;
    private OnExcluirItemListener<Produto> onExcluirProduto;






    @NonNull
    @Override
    public ProdutoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_produto, parent, false);
        return new ProdutoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProdutoViewHolder holder, int position) {
            Produto produtoAtual = listaDeProdutos.get(position);

            holder.textNome.setText("Nome do Produto: " + produtoAtual.getNome());
            holder.textQuantidade.setText("Quantidade: " + produtoAtual.getQuantidade());

            String precoFormatado = NumberFormat.getCurrencyInstance(new Locale("pt","BR"))
                    .format(produtoAtual.getPrecoUnitario());

            holder.textPreco.setText("Preço: " + precoFormatado);

            holder.btnExcluir.setOnClickListener(v -> {
                if (onExcluirProduto != null ) onExcluirProduto.onExcluir(produtoAtual);
            });

    }

    @Override
    public int getItemCount() {
        return listaDeProdutos.size();
    }

    public void setProdutos(List<Produto> novosProdutos){
        this.listaDeProdutos = novosProdutos;
        notifyDataSetChanged();
    }

    public Produto getProduto(int position){
        return listaDeProdutos.get(position);
    }




    class ProdutoViewHolder extends RecyclerView.ViewHolder{
        TextView textNome;
        TextView textPreco;
        TextView textQuantidade;
        ImageButton btnExcluir;

        public ProdutoViewHolder( View itemView){
            super(itemView);

            textNome = itemView.findViewById(R.id.textNome);
            textPreco = itemView.findViewById(R.id.textPreco);
            textQuantidade = itemView.findViewById(R.id.textQuantidade);
            btnExcluir = itemView.findViewById(id.btnExcluir);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getBindingAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION){
                        listener.onItemClick(listaDeProdutos.get(position));
                    }
                }
            });

        }

    }

    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener = listener;
    }

    public void setOnExcluirProdutoListener(OnExcluirItemListener onExcluirProduto){
        this.onExcluirProduto = onExcluirProduto;
    }
}
