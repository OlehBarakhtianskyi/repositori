package com.itschool.repositori;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class MainActivity extends AppCompatActivity {
    ListView listview;
    public static boolean state;
    static TextView textView;
    static HTMLgetter page;
    Button loadButton;

    static void showRep() {
//        try {
//            Thread.sleep(3000l);

        if (page.page != null) {
            textView.setTextSize(16);
            textView.setTextColor(Color.BLACK);
            textView.setMovementMethod(new ScrollingMovementMethod());

            Elements links = page.page.select("div [class=\"d-inline-block mb-1\"]");
            /*
            for (Element link : links)
            {
                //System.out.println(link);

                Elements uls = link.select("h3");
                for (Element ul : uls)
                {
                    //System.out.println(ul);
                    Element a = ul.child(0);
                   // page1.list.add(new Reference( a.attr("href"), a.text()));
                    System.out.println(a.text() + '\t' +'\t' +'\t' +'\t' +'\t' +'\t' + a.attr("href"));

                }
                */
            String stringlinks = "<h3>";
            for (Element link : links)
                stringlinks += "<li><a href='" + link.baseUri().substring(0, link.baseUri().lastIndexOf("/")) + link.attr("href") + "'>" + link.text() + "</a></li>";
            stringlinks += "</h3>";

            textView.setText(Html.fromHtml(page.page.title() + "\n\n" + stringlinks));
            textView.setMovementMethod(LinkMovementMethod.getInstance());
        }
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadButton = findViewById(R.id.buttonLoad);
        textView = findViewById(R.id.textView);
        state = false;
    }

    public void loadNewsClick(View view) {
        page = new HTMLgetter("https://github.com/it-school?tab=repositories");
        Toast.makeText(getApplicationContext(), R.string.loadStart, Toast.LENGTH_LONG).show();
        page.execute();
    }
}

