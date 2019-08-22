package com.example.feednoticias_pdm;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.example.feednoticias_pdm.Fetch.NoticiaAlarmReceiver;
import com.example.feednoticias_pdm.adapter.NoticiaAdapter;
import com.example.feednoticias_pdm.database.DatabaseHelper;
import com.example.feednoticias_pdm.database.configuration.AccessManager;
import com.example.feednoticias_pdm.model.NoticiaEntity;

import java.util.ArrayList;
import java.util.List;

public class Feed extends Activity {
    private static final String TAG = "Feed";

    private static final String NOTIFICATION_CHANNEL_ID = "com.example.feednoticias_pdm";
    private static final int MENU_CONF_ID = 1;
    private static final int MENU_PERFIL_ID = 2;
    private static final int MENU_SAIR_ID = 3;

    private ListView listView;
    private BroadcastReceiver noticiasReceiver;
    private List<NoticiaEntity> noticias = new ArrayList<>();

    // id utilizado para lancar uma notificacao
    // é incrementado por 1 a cada notificacao
    private int notification_id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Layout base
        FrameLayout rootLayout = new FrameLayout(this);
        FrameLayout.LayoutParams rootParams = new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.MATCH_PARENT
        );
        rootLayout.setLayoutParams(rootParams);

        // Container
        LinearLayout container = new LinearLayout(this);
        LinearLayout.LayoutParams containerParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
        );
        container.setLayoutParams(containerParams);
        container.setOrientation(LinearLayout.VERTICAL);

        // Toolbar
        Toolbar toolbar = new Toolbar(this);
        ViewGroup.LayoutParams toolbarParams = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        toolbar.setLayoutParams(toolbarParams);
        toolbar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.toolbarColor)));
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setTitle("Feed");

        // Menu
        Menu menu = toolbar.getMenu();
        menu.add(Menu.NONE, MENU_CONF_ID, Menu.NONE, "Configuração")
                .setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        // TODO: Ir para atividade de configuracao
                        return true;
                    }
                });

        menu.add(Menu.NONE, MENU_PERFIL_ID, Menu.NONE, "Perfil")
                .setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        // Todo: Ir para tela de perfil
                        startActivity(new Intent(Feed.this, Perfil.class));
                        return true;
                    }
                });

        menu.add(Menu.NONE, MENU_SAIR_ID, Menu.NONE, "Sair")
                .setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        // Todo: Desautenticar usuário antes de voltar para tela de login

                        AccessManager am = new AccessManager(Feed.this);
                        am.remove();
                        Intent loginIntent = new Intent(Feed.this, Login.class);
                        finish();
                        startActivity(loginIntent);
                        return false;
                    }
                });

        // ListView
        listView = new ListView(this);
        ViewGroup.LayoutParams listViewParams = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        listView.setLayoutParams(listViewParams);

        container.addView(toolbar);
        container.addView(listView);

        // Instanciando receiver e registrando alarm repeat
        noticiasReceiver = new NoticiasReceiver();
        registerAlarm();

        // Iniciando busca de noticias pelo banco
        Thread searchFromDB = new Thread(new Runnable() {
            @Override
            public void run() {
                DatabaseHelper db = new DatabaseHelper(Feed.this);
                List<NoticiaEntity> noticiasFromDB = db.allfeeds();
                Log.d(TAG, "Noticias do banco: "+noticiasFromDB.size());
                setNoticias(noticiasFromDB, false);
            }
        });
        searchFromDB.start();

        rootLayout.addView(container);
        setContentView(rootLayout);
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Registrando Broadcast Receiver
        LocalBroadcastManager manager = LocalBroadcastManager.getInstance(this);
        IntentFilter filter = new IntentFilter(NoticiasReceiver.FILTER_NOTICIAS_RECEIVER);
        manager.registerReceiver(noticiasReceiver, filter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        // Desregistrando Broadcast Receiver
        LocalBroadcastManager manager = LocalBroadcastManager.getInstance(this);
        manager.unregisterReceiver(noticiasReceiver);
    }

    private void registerAlarm() {
        Intent intent = new Intent(this, NoticiaAlarmReceiver.class);
        PendingIntent pending = PendingIntent.getBroadcast(this, 0, intent, 0);

        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        long triggerAtMillis = SystemClock.elapsedRealtime()+1000; // Disparar imediatamente
        long intervalAtMillis = 1000 * 60 * 5; // 5 minutos

        alarmManager.setRepeating(
                AlarmManager.ELAPSED_REALTIME_WAKEUP,
                triggerAtMillis,
                intervalAtMillis,
                pending
        );
    }

    public void setNoticias (List<NoticiaEntity> noticias, boolean notify) {
        Log.d(TAG, "setNoticias");
        listView.setAdapter(new NoticiaAdapter(this, noticias));
        if(notify){
            for(NoticiaEntity n: noticias){
                notify(n);
            }
        }
    }

    private void notify(NoticiaEntity noticia){

        // Criando canal de notificacao
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel = new NotificationChannel(
                    NOTIFICATION_CHANNEL_ID,
                    "notificationFeedChannel",
                    NotificationManager.IMPORTANCE_DEFAULT
            );
            channel.setDescription("Canal de notificacao da aplicação de feed");

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }

        Intent intent = new Intent(this, Noticia.class);
        intent.putExtra("Noticia", noticia);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, notification_id, intent, 0);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(noticia.getTitulo())
                .setContentText(noticia.getDescricao())
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true); // Remove a notificação quando clicada

        Notification notification = builder.build();

        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(this);
        managerCompat.notify(notification_id, notification);
        notification_id++; 
    }

    public class  NoticiasReceiver extends BroadcastReceiver {

        // String utilizada para filtrar intent do servico de busca de noticias
        public static final String FILTER_NOTICIAS_RECEIVER = "com.example.feednoticias_pdm.Feed.NoticiasReceiver";

        // Strings utilizadas para recuperar o conteudo vindo do servico
        public static final String BUNDLE_KEY = "BUNDLE";
        public static final String NOTICIAS_KEY = "NOTICIAS";

        @Override
        public void onReceive(Context context, Intent intent) {
            Bundle bundle = intent.getBundleExtra(BUNDLE_KEY);
            List<NoticiaEntity> newNoticias = (List<NoticiaEntity>) bundle.getSerializable(NOTICIAS_KEY);
            noticias.addAll(newNoticias); // Adicionando novas noticias a lista inicial
            setNoticias(noticias, true);
        }
    }

}
