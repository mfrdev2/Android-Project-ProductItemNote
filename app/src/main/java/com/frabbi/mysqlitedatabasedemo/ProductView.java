package com.frabbi.mysqlitedatabasedemo;


import androidx.appcompat.app.AppCompatActivity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;
import java.util.List;

public class ProductView extends AppCompatActivity {
    private Controller controller;
    private FloatingActionButton fab;
    private ListView listView;
    private List<Product> productList;
    private Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_view);

        iniit();
        controller = new Controller(this);
        productList = new ArrayList<>();
        dataReady();
        listAdapterReady(ProductView.this, R.layout.view_of_list, productList);

        //insert data to product table.
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertDialog(ProductView.this);
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Product product = adapter.getItem(i);
                longPressedDialog(ProductView.this,product);
                return false;
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Product product = adapter.getItem(i);
                assert product != null;
                dataViewDialog(ProductView.this,product);
            }
        });

    }
    // data view from list in dialog
    private void dataViewDialog(Context context, Product product) {
         View view = LayoutInflater.from(context).inflate(R.layout.custom_dialog_for_viewdata,null);
         AlertDialog dialog = new AlertDialog.Builder(context).create();
        dialog.setView(view);
        TextView p_name = view.findViewById(R.id.pNameVid);
        TextView p_desc = view.findViewById(R.id.pDescVid);
        TextView p_price = view.findViewById(R.id.pPriceVid);
        Button okBtn = view.findViewById(R.id.okBtnVid);

        //set data to view
        p_name.setText("ProductName:\n"+product.getP_name());
        p_desc.setText("ProductDesc:\n"+product.getP_desc());
        p_price.setText("Price:\n"+product.getPrice());


        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog.show();

    }
    //insertDataByDialogBox
    private void insertDialog(Context context) {
        LayoutInflater factory = LayoutInflater.from(context);
        final View addDialogView = factory.inflate(R.layout.custom_dialog_add, null);
        final AlertDialog dialog = new AlertDialog.Builder(context).create();
        dialog.setView(addDialogView);
        EditText p_name = addDialogView.findViewById(R.id.pNameAid);
        EditText p_desc = addDialogView.findViewById(R.id.pDescAid);
        EditText p_price = addDialogView.findViewById(R.id.pPriceAid);
        addDialogView.findViewById(R.id.cancelBtnId).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        //insert data by dialog box
        addDialogView.findViewById(R.id.addBtnId).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String pName = p_name.getText().toString().trim();
                String pDesc = p_desc.getText().toString().trim();
                double pPrice = 0.0;
                try {
                     pPrice = Double.parseDouble(p_price.getText().toString().trim());
                }catch (NumberFormatException e){
                    e.printStackTrace();
                }
                if (!pName.isEmpty() && !pDesc.isEmpty() && pPrice > 0.0) {
                    Product product = new Product(pName, pDesc, pPrice);
                    if (controller.insertDataToProduct(product)) {
                        dataReady();
                        listAdapterReady(ProductView.this, R.layout.view_of_list, productList);
                        Toast.makeText(context, "Data is saved", Toast.LENGTH_LONG).show();
                        dialog.dismiss();
                    }
                }

            }
        });

        dialog.show();
    }
    //updateDataByDialogBox
    private void updateDialog(Context context, Product oldProduct) {
        LayoutInflater factory = LayoutInflater.from(context);
        final View addDialogView = factory.inflate(R.layout.custom_dialog_update, null);
        final AlertDialog dialog = new AlertDialog.Builder(context).create();
        dialog.setView(addDialogView);
        EditText p_name = addDialogView.findViewById(R.id.pNameUid);
        EditText p_desc = addDialogView.findViewById(R.id.pDescUid);
        EditText p_price = addDialogView.findViewById(R.id.pPriceUid);
        p_name.setText(oldProduct.getP_name());
        p_desc.setText(oldProduct.getP_desc());
        p_price.setText(String.valueOf(oldProduct.getPrice()));
        addDialogView.findViewById(R.id.cancelBtnUid).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        //update data by dialog box
        addDialogView.findViewById(R.id.updateBtnUid).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String pName = p_name.getText().toString().trim();
                String pDesc = p_desc.getText().toString().trim();
                double pPrice = 0.0;
                try {
                    pPrice = Double.parseDouble(p_price.getText().toString().trim());
                }catch (NumberFormatException e){
                    e.printStackTrace();
                }
                if (!pName.isEmpty() && !pDesc.isEmpty() && pPrice > 0.0) {
                    Product newProduct = new Product(pName, pDesc, pPrice);

                    if (controller.updateProductData(newProduct,oldProduct)) {
                        dataReady();
                        listAdapterReady(ProductView.this, R.layout.view_of_list, productList);
                        Toast.makeText(context, "Data is updated", Toast.LENGTH_LONG).show();
                        dialog.dismiss();
                    }
                }

            }
        });

        dialog.show();
    }

    private void listAdapterReady(Context context, int id, List<Product> productList) {
        adapter = new Adapter(context, id, productList);
        listView.setAdapter(adapter);
    }

    private void longPressedDialog(Context context,Product product) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(context);
        dialog.setTitle("Choose your option.");
        dialog.setPositiveButton("Update", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                updateDialog(context,product);
            }
        });
        dialog.setNeutralButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                    confirmDialog(ProductView.this,product);


            }
        });
        AlertDialog ad = dialog.create();
        ad.show();
    }

    private void confirmDialog(Context context,Product product) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Confirmation::");
        builder.setMessage("Are sure for delete?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                controller.deleteProductData(product);
                dataReady();
                listAdapterReady(ProductView.this, R.layout.view_of_list, productList);
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        AlertDialog ad = builder.create();
        ad.show();
    }


    private void iniit() {
        fab = findViewById(R.id.fab);
        listView = findViewById(R.id.listViewId);
    }

    void dataReady() {
        productList = controller.getProductData();
    }

}