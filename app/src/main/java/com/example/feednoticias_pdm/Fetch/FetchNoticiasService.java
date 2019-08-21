package com.example.feednoticias_pdm.Fetch;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.example.feednoticias_pdm.Feed;
import com.example.feednoticias_pdm.database.DatabaseHelper;
import com.example.feednoticias_pdm.model.NoticiaEntity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FetchNoticiasService extends Service {
    private static final String TAG = "FetchNoticiasService";

    // Formas de busca
    FetchNoticiaStrategy array[] = {
            new FetchNoticiasDiarioDoSertao(),
            // new FetchNoticiasUirauna(),
            new FetchNoticiasJornalParaiba()
    };
    List<FetchNoticiaStrategy> strategies = Arrays.asList(array);

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand");
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                // Formando uma unica lista de noticias
                // composta pelo resultado de todas as estrategias de busca
                List<NoticiaEntity> noticias = new ArrayList<>();
                for(FetchNoticiaStrategy strategy : strategies){
                    noticias.addAll(strategy.fetch());
                }

                // Tentando salvar noticias vindas da busca
                // E armazenando apenas as noticias NOVAS em uma list
                // para mandar para atividade de feed
                DatabaseHelper db = new DatabaseHelper(getApplicationContext());
                List<NoticiaEntity> novasNoticias = new ArrayList<>();
                for(NoticiaEntity n: noticias) {
                    if(db.addfeed(n)){
                        novasNoticias.add(n);
                    }
                }

                // Caso exista novas noticias
                if (novasNoticias.size() > 0){

                    // Salvando o resultado no intent e mandando para a atividade de feed
                    Bundle bundle = new Bundle();
                    bundle.putSerializable(Feed.NoticiasReceiver.NOTICIAS_KEY, (Serializable) novasNoticias);

                    Intent receiverIntent = new Intent();
                    receiverIntent.setAction(Feed.NoticiasReceiver.FILTER_NOTICIAS_RECEIVER);
                    receiverIntent.putExtra(Feed.NoticiasReceiver.BUNDLE_KEY, bundle);

                    Log.d(TAG, "Mandando "+novasNoticias.size()+" noticias por broadcast...");
                    LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(receiverIntent);
                }
            }
        });
        thread.start();
        return START_STICKY;
    }
}
