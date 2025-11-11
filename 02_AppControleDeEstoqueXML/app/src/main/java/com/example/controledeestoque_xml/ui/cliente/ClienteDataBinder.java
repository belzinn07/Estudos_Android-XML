package com.example.controledeestoque_xml.ui.cliente;


import android.content.Intent;
import android.os.Parcelable;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.controledeestoque_xml.R;
import com.example.controledeestoque_xml.abstractions.BaseDataBinder;
import com.example.controledeestoque_xml.abstractions.ConfirmarAcao;
import com.example.controledeestoque_xml.core.InicializadorDeDependencias;
import com.example.controledeestoque_xml.core.utils.DialogUtils;
import com.example.controledeestoque_xml.data.local.entities.Cliente;
import com.example.controledeestoque_xml.viewmodel.AppViewModelFactory;
import com.example.controledeestoque_xml.viewmodel.ClienteViewModel;
import com.example.controledeestoque_xml.viewmodel.AppViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ClienteDataBinder extends BaseDataBinder<ListaDeClientesActivity> {
    private ClienteViewModel clienteViewModel;
    private AppViewModel appViewModel;
    private ClienteAdapter clienteAdapter;

    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private FloatingActionButton fabAddCliente;

    private static final int ADD_CLIENTE_REQUEST_CODE = 1;
    private static final int EDIT_CLIENTE_REQUEST_CODE = 2;



    protected ClienteDataBinder(ListaDeClientesActivity view) {
        super(view);
    }

    @Override
    protected void inicializarViews() {
        toolbar = view.findViewById(R.id.toolbar);
        recyclerView = view.findViewById(R.id.recycler_view);
        fabAddCliente = view.findViewById(R.id.fabAddCliente);
        view.setSupportActionBar(toolbar);

    }

    @Override
    protected void configurarRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(view));
        recyclerView.setHasFixedSize(true);

        clienteAdapter = new ClienteAdapter();
        recyclerView.setAdapter(clienteAdapter);

    }

    @Override
    protected void configurarViewModel() {
        clienteViewModel = new ViewModelProvider(view).get(ClienteViewModel.class);

        InicializadorDeDependencias inicializador = (InicializadorDeDependencias) view.getApplication();
        AppViewModelFactory factory = new AppViewModelFactory(inicializador.getUsuarioRepository());
        appViewModel = new ViewModelProvider(view, factory).get(AppViewModel.class);

    }

    @Override
    protected void observarViewModel() {
        clienteViewModel.getTodosClientes().observe(view, clientes -> {
            clienteAdapter.setClientes(clientes);
        });

    }

    @Override
    protected void configurarEventos() {
        fabAddCliente.setOnClickListener(v ->  {
            Intent intent = new Intent(view, AddClienteActivity.class);
            view.startActivityForResult(intent, ADD_CLIENTE_REQUEST_CODE);

        });

        clienteAdapter.setOnItemClickListener(cliente -> {
            Intent intent = new Intent(view, AddClienteActivity.class);
            intent.putExtra("CLIENTE EXTRA", (Parcelable) cliente);
            view.startActivityForResult(intent, EDIT_CLIENTE_REQUEST_CODE);
        });

       clienteAdapter.setOnExcluirItemListener(cliente -> {

           DialogUtils.msgConfirmacaoExclusao((Cliente) cliente, view, new ConfirmarAcao() {
                       @Override
                       public void confirmar() {

                           clienteViewModel.excluirCliente((Cliente) cliente);
                           Toast.makeText(view, "Produto excluído com sucesso", Toast.LENGTH_SHORT).show();
                       }
                   }
           );
       });
    }
}
