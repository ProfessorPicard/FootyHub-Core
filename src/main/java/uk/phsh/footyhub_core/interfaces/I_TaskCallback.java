package uk.phsh.footyhub_core.interfaces;

public interface I_TaskCallback<T> {

    void onSuccess(T value);
    void onError(String message);

}
