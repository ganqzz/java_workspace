package com.example.rxjava2.transformer;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class FileCacheObservableTransformer<R> implements ObservableTransformer<R, R> {
    private final String filename;

    private FileCacheObservableTransformer(String filename) {
        this.filename = filename;
    }

    public static <R> FileCacheObservableTransformer<R> cacheToLocalFileNamed(String filename) {
        return new FileCacheObservableTransformer<R>(filename);
    }

    @Override
    public ObservableSource<R> apply(Observable<R> upstream) {
        return readFromFile()
                .onExceptionResumeNext(
                        upstream.take(1)
                                .doOnNext(this::saveToFile)
                );
    }

    private void saveToFile(R r) throws IOException {
        ObjectOutputStream objectOutputStream = null;
        try {
            final FileOutputStream fileOutputStream = new FileOutputStream(getFilename());
            objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(r);
        } finally {
            if (objectOutputStream != null) {
                objectOutputStream.close();
            }
        }
    }

    private Observable<R> readFromFile() {
        return Observable.create(emitter -> {
            ObjectInputStream input = null;
            try {
                final FileInputStream fileInputStream = new FileInputStream(getFilename());
                input = new ObjectInputStream(fileInputStream);
                R foundObject = (R) input.readObject();
                emitter.onNext(foundObject);
            } catch (Exception e) {
                emitter.onError(e);
            } finally {
                if (input != null) input.close();
                emitter.onComplete();
            }
        });
    }

    private String getFilename() {
        return filename;
    }
}
