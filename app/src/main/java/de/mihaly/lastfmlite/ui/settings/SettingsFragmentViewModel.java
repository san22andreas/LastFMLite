package de.mihaly.lastfmlite.ui.settings;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

public class SettingsFragmentViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public SettingsFragmentViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is notifications fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}