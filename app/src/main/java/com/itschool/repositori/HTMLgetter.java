package com.itschool.repositori;

import android.os.AsyncTask;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.net.URL;

public class HTMLgetter extends AsyncTask<Void, Void, Void> {
    String url;
    Document page;

    public HTMLgetter() {
        this.url = "https://github.com/it-school?tab=repositories";
        this.page = null;
    }

    public HTMLgetter(String url) {
        this.url = url;
        this.page = null;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Void doInBackground(Void... params) { // В фоновом режим загрузка
        try {
            page = Jsoup.parse(new URL(url), 60000);
        } catch (IOException e) {
            e.printStackTrace();
        }
/*
// Альтернативный вариант получения содержимого страницы
        try {
            page = Jsoup.connect(url).get();// Connect to the web site
        } catch (IOException e) {
            e.printStackTrace();
        }
*/
        return null;
    }

    @Override
    protected void onPostExecute(Void result) {
/*
        if (this.page != null) {
            Elements newses = page.select("span[class=sdc-site-tile__headline-text]");

            for (Element news : newses)
                Log.d("TechNews: ", news.text());
        }
        else
            Log.d("TechNews: ", "No news loaded!");
*/
        MainActivity.showRep();
    }
}