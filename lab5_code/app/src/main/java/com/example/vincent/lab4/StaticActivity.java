package com.example.vincent.lab4;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vincent on 2016/10/19.
 */

public class StaticActivity extends Activity {
    private List<Fruit> fruitList = new ArrayList<>();
    public static StaticActivity staticActivity;
    protected void onCreate(Bundle onsavedInstance) {
        staticActivity = this;
        super.onCreate(onsavedInstance);
        setContentView(R.layout.s_broadcastlayout);
        initFruit();
        FruitAdapter adapter = new FruitAdapter(StaticActivity.this, R.layout.s_item, fruitList);
        ListView listView = (ListView) findViewById(R.id.list_view);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Fruit fruit = fruitList.get(position);
                Intent intent = new Intent("com.example.vincent.lab4.MyBroadcast");
                intent.setClass(StaticActivity.this, WidgetDemo.class);
                Bundle bundle = new Bundle();
                bundle.putString("name", fruit.getName());
                bundle.putInt("picture", fruit.getImageId());
                intent.putExtras(bundle);
                sendBroadcast(intent);
            }
        });
    }
    private void initFruit() {
        Fruit apple = new Fruit("Apple", R.mipmap.apple);
        fruitList.add(apple);
        Fruit banana = new Fruit("Banana", R.mipmap.banana);
        fruitList.add(banana);
        Fruit cherry = new Fruit("Cherry", R.mipmap.cherry);
        fruitList.add(cherry);
        Fruit coco = new Fruit("Coco", R.mipmap.coco);
        fruitList.add(coco);
        Fruit kiwi = new Fruit("Kiwi", R.mipmap.kiwi);
        fruitList.add(kiwi);
        Fruit orange = new Fruit("Orange", R.mipmap.orange);
        fruitList.add(orange);
        Fruit pear = new Fruit("Pear", R.mipmap.pear);
        fruitList.add(pear);
        Fruit strawberry = new Fruit("Strawberry", R.mipmap.strawberry);
        fruitList.add(strawberry);
        Fruit watermelon = new Fruit("Watermelon", R.mipmap.watermelon);
        fruitList.add(watermelon);
    }
}
