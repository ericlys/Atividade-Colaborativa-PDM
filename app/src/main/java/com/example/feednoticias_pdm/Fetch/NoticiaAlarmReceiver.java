package com.example.feednoticias_pdm.Fetch;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class NoticiaAlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        // Iniciando o servico de busca de noticias quando o alarme for disparado
        Intent i = new Intent(context, FetchNoticiasService.class);
        context.startService(i);
    }
}
