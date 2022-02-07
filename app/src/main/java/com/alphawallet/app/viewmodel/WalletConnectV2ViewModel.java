package com.alphawallet.app.viewmodel;

import com.alphawallet.app.entity.Wallet;
import com.alphawallet.app.interact.FetchWalletsInteract;

import javax.inject.Inject;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class WalletConnectV2ViewModel extends BaseViewModel
{
    private final MutableLiveData<Wallet[]> wallets = new MutableLiveData<>();

    private final FetchWalletsInteract fetchWalletsInteract;

    public LiveData<Wallet[]> wallets()
    {
        return wallets;
    }

    @Inject
    WalletConnectV2ViewModel(FetchWalletsInteract fetchWalletsInteract)
    {
        this.fetchWalletsInteract = fetchWalletsInteract;
    }

    public void fetchWallets()
    {
        disposable = fetchWalletsInteract
                .fetch()
                .subscribe(this::onWallets, this::onError);
    }

    private void onWallets(Wallet[] wallets)
    {
        this.wallets.postValue(wallets);
    }
}
