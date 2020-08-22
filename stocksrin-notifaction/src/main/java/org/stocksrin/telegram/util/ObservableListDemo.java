package org.stocksrin.telegram.util;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

public class ObservableListDemo {

    public static void main(String[] args) {

        List<String> list = new ArrayList<String>();
        ObservableList<String> observableList = FXCollections.observableList(list);

        observableList.addListener(new ListChangeListener() {
            @Override
            public void onChanged(ListChangeListener.Change change) {
                System.out.println("Detected a change! ");
                while (change.next()) {
                    System.out.println("Was added? " + change.wasAdded());
                    System.out.println("Was removed? " + change.wasRemoved());
                }
            }
        });

        observableList.add("a : item one");

        //System.out.println("Size: " + observableList.size() + observableList.toString());

        //list.add("d : item two");
        System.out.println(list);



    }

}

