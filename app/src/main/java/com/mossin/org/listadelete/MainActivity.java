package com.mossin.org.listadelete;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;


import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {
   private ListView listView;
   private ArrayList<String> arrayList;
   private ArrayAdapter<String> adapter;
   private String valores [] = {"Elemento 1","Elemento 2","Elemento 3","Elemento 4","Elemento 5","Elemento 6","Elemento 7","Elemento 8"
   ,"Elemento 9","Elemento 10","Elemento 11","Elemento 12","Elemento 13","Elemento 14","Elemento 15","Elemento 16","Elemento 17","Elemento 18"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.listView);
        arrayList = new ArrayList<String >();
        for (int i = 0; i < valores.length; i++){
            arrayList.add(valores[i]);
        }
        System.out.println("arrayList: "+arrayList.get(0));
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,arrayList);
        listView.setAdapter(adapter);
        System.out.println("Lista sin Eliminar nada"+arrayList);
        System.out.println("Tamaño sin Eliminar Nada\t"+arrayList.size());
      //Este contructor necesita de una LisView como paramtro
        SwipeListViewTouchListener touchListener = new SwipeListViewTouchListener(listView,new SwipeListViewTouchListener.OnSwipeCallback() {
            //Metodo de Borrado hacia la Izquierda
            @Override
            public void onSwipeLeft(ListView listView, int[] reverseSortedPositions) {
                //posicion Eliminada
                int position = reverseSortedPositions[0];
                System.out.println("Posicion: "+position);
               //Pendiente HOY 1 d Enero de 2015 adaptador.notifyDataSetChanged();
                openAlert(arrayList,position);


                adapter.notifyDataSetChanged();
                System.out.println("tamaño despues de eliminar: "+ arrayList.size());

            }
            //Metodo de Borrado hacia la Derecha
            @Override
            public void onSwipeRight(ListView listView, int[] reverseSortedPositions) {
                //posicion Eliminada
                int position = reverseSortedPositions[0];
                System.out.println(position);

                openAlert(arrayList,position);
                adapter.notifyDataSetChanged();

                System.out.println("tamaño despues de eliminar: "+ arrayList.size());
            }
        },true,false);
          /*Escuchadores de estos metodos*/
        listView.setOnTouchListener(touchListener);
        listView.setOnScrollListener(touchListener.makeScrollListener());


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.action_settings:

            break;
            case R.id.delete:

            break;
        }

        return super.onOptionsItemSelected(item);
    }
    /*Metodo que mandara el mensaje
  //paramteros ArrayList,y una posicion*/
    public void openAlert(final ArrayList<String> arrayLista, final int position){

     AlertDialog.Builder mensaje = new AlertDialog.Builder(this);
       //Titulo del mensaje de la Alerta
         mensaje.setMessage(R.string.pregunta);
        //Respuesta Positiva...parametros string(ok) y un DialogInterface
        mensaje.setPositiveButton("OK",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                arrayLista.remove(position);
                //Mensaje para vefiricar cual registro fue eliminado
           //Toast.makeText(getApplicationContext(),"Haz elimindo el regitro: "+ arrayLista.get(position),Toast.LENGTH_SHORT).show();
             System.out.println("Posicion Eliminada: "+position);

            }
        });
        mensaje.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
              //No Realizar nada
            }
        });
        AlertDialog alertDialog = mensaje.create();
        alertDialog.show();
    }
}
