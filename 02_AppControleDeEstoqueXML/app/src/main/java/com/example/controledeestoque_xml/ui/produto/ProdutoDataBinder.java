package com.example.controledeestoque_xml.ui.produto;

import android.content.Intent;
import android.os.Parcelable;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.controledeestoque_xml.R;
import com.example.controledeestoque_xml.abstractions.ConfirmarAcao;
import com.example.controledeestoque_xml.core.InicializadorDeDependencias;
import com.example.controledeestoque_xml.data.local.entities.Produto;
import com.example.controledeestoque_xml.ui.auth.LoginActivity;
import com.example.controledeestoque_xml.abstractions.BaseDataBinder;
import com.example.controledeestoque_xml.core.utils.DialogUtils;
import com.example.controledeestoque_xml.viewmodel.AppViewModel;
import com.example.controledeestoque_xml.viewmodel.AppViewModelFactory;
import com.example.controledeestoque_xml.viewmodel.ProdutoViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;
public class ProdutoDataBinder extends BaseDataBinder<ListaProdutosActivity> {

    private ProdutoViewModel produtoViewModel;
    private AppViewModel appViewModel;

    private ProdutoAdapter adapter;
    private TextView textValorTotal;
    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private FloatingActionButton fabAddProduto;

    private static final int ADD_PRODUTO_REQUEST_CODE = 1;
    private static final int EDIT_PRODUTO_REQUEST_CODE = 2;

    public ProdutoDataBinder(ListaProdutosActivity view) {
        super(view);
    }

    @Override
    protected void inicializarViews() {
        toolbar = view.findViewById(R.id.toolbar);
        recyclerView = view.findViewById(R.id.recycler_view);
        fabAddProduto = view.findViewById(R.id.fabAddProduto);
        textValorTotal = view.findViewById(R.id.textValorTotal);
        view.setSupportActionBar(toolbar);
    }

    @Override
    protected void configurarRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(view));
        recyclerView.setHasFixedSize(true);

        adapter = new ProdutoAdapter();
        recyclerView.setAdapter(adapter);

    }

    @Override
    protected void configurarViewModel() {
        produtoViewModel = new ViewModelProvider(view).get(ProdutoViewModel.class);


        InicializadorDeDependencias inicializador = (InicializadorDeDependencias) view.getApplication();
        AppViewModelFactory factory = new AppViewModelFactory(inicializador.getUsuarioRepository());
        appViewModel = new ViewModelProvider(view, factory).get(AppViewModel.class);
    }

    @Override
    protected void observarViewModel() {
        produtoViewModel.getTodosProdutos().observe(view, new Observer<List<Produto>>() {
            @Override
            public void onChanged(List<Produto> produtos) {
                adapter.setProdutos(produtos);
            }
        });

        produtoViewModel.getValorTotalEstoque().observe(view, new Observer<Double>() {
            @Override
            public void onChanged(Double valorTotal) {
                if (valorTotal != null) {
                    textValorTotal.setText(String.format("R$ %.2f", valorTotal));

                }

            }
        });
    }

    @Override
    protected void configurarEventos() {
       fabAddProduto.setOnClickListener(v -> {
           Intent intent = new Intent(view, AddProdutoActivity.class);
           view.startActivityForResult(intent, ADD_PRODUTO_REQUEST_CODE);

       });

        adapter.setOnItemClickListener(produto -> {
            Intent intent = new Intent(view, AddProdutoActivity.class);
            intent.putExtra("PRODUTO EXTRA", (Parcelable) produto);
            view.startActivityForResult(intent, EDIT_PRODUTO_REQUEST_CODE);
        });

        adapter.setOnExcluirItemListener(produto -> {
            DialogUtils.msgConfirmacaoExclusao((Produto) produto, view, new ConfirmarAcao() {
                @Override
                public void confirmar() {
                    produtoViewModel.deletarProduto((Produto) produto);
                    Toast.makeText(view, "Produto excluído com sucesso", Toast.LENGTH_SHORT).show();
                }
            });
        });


    }

    public boolean onItemSelecionadoMenu(int itemId) {
        if (itemId == R.id.action_logout) {
            appViewModel.logout();
            Intent intent = new Intent(view, LoginActivity.class);
            view.startActivity(intent);
            view.finish();
            return true;
        }
        return false;
    }


}
