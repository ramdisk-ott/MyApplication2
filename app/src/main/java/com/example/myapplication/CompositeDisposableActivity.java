package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.functions.Predicate;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class CompositeDisposableActivity extends AppCompatActivity {
    private String TAG = "ListActivity";
    private TextView filterTV, capsTV;
    private String temp = "", caps = "";
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    List<String> strings = new ArrayList<String>(Arrays.asList("Hello", "world"));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_composite_disposable);
        filterTV = findViewById(R.id.filterTV);
        capsTV = findViewById(R.id.capsTV);
        filter();
        caps();
    }

    private void caps() {
        Observable<String> observable = Observable.fromIterable(strings);
        Observer<String> observer = new Observer<String>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                Log.d(TAG, "onSubscribe: ");
                compositeDisposable.add(d);
            }

            @Override
            public void onNext(@NonNull String s) {
                Log.d(TAG, "onNext: ");
                caps = caps + s;
                capsTV.setText(caps);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.d(TAG, "onError: ");
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "onComplete: ");
            }
        };
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).filter(s -> s.toLowerCase().contains("llo")).map(s -> s.toUpperCase()).subscribe(observer);
    }

    private void filter() {
        Observable<String> observable = Observable.fromIterable(strings);
        Observer<String> observer = new Observer<String>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                Log.d(TAG, "onSubscribe: ");
                compositeDisposable.add(d);
            }

            @Override
            public void onNext(@NonNull String s) {
                Log.d(TAG, "onNext: ");
                temp = temp + s;
                filterTV.setText(temp);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.d(TAG, "onError: ");
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "onComplete: ");
            }
        };
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).filter(s -> s.toLowerCase().contains("llo")).subscribe(observer);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        compositeDisposable.clear();
    }
}