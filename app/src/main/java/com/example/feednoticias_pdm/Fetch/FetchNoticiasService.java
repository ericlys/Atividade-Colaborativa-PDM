package com.example.feednoticias_pdm.Fetch;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.example.feednoticias_pdm.Feed;
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
            new FetchNoticiasUirauna(),
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

                // Salvando o resultado no intent e mandando para a atividade de feed
                Bundle bundle = new Bundle();
                bundle.putSerializable(Feed.NoticiasReceiver.NOTICIAS_KEY, (Serializable) noticias);

                Intent receiverIntent = new Intent();
                receiverIntent.setAction(Feed.NoticiasReceiver.FILTER_NOTICIAS_RECEIVER);
                receiverIntent.putExtra(Feed.NoticiasReceiver.BUNDLE_KEY, bundle);

                Log.d(TAG, "Mandando noticias por broadcast...");
                LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(receiverIntent);
            }
        });
        thread.start();
        return START_STICKY;
    }
}
