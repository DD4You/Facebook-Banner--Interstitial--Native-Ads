package in.dd4you.facebookads;

import android.os.Bundle;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.AdSize;
import com.facebook.ads.AdView;
import com.facebook.ads.InterstitialAd;
import com.facebook.ads.InterstitialAdListener;
import com.facebook.ads.NativeAd;
import com.facebook.ads.NativeAdsManager;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private AdView adView;
    private InterstitialAd interstitialAd;

    private RecyclerView recyclerView;

    private ArrayList<Object> arrayList = new ArrayList<>();
    private MyAdapter adapter;

    private ArrayList<NativeAd> nativeAds = new ArrayList<>();
    private NativeAdsManager fbNativeAdsManager;
    private final int AD_POSITION = 1;
    private final int AD_POSITION_EVERY_COUNT = 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerview);


        showBannerAd();
        showInterstitialAd();
        loadData();
        initNativeAds();
    }

    private void initNativeAds() {
        fbNativeAdsManager = new NativeAdsManager(this, getString(R.string.native_id), 4);

        fbNativeAdsManager.setListener(new NativeAdsManager.Listener() {
            @Override
            public void onAdsLoaded() {
                int count = fbNativeAdsManager.getUniqueNativeAdCount();
                for (int i = 0; i < count; i++) {
                    NativeAd ad = fbNativeAdsManager.nextNativeAd();
                    addNativeAd(i, ad);
                }
            }

            @Override
            public void onAdError(AdError adError) {

            }
        });
        fbNativeAdsManager.loadAds();
    }

    private void addNativeAd(int i, NativeAd ad) {
        if (ad == null) {
            return;
        }
        if (nativeAds.size() > i && nativeAds.get(i) != null) {
            nativeAds.get(i).unregisterView();
            arrayList.remove(AD_POSITION + (i * AD_POSITION_EVERY_COUNT));
            nativeAds = null;
            adapter.notifyDataSetChanged();
        }
        nativeAds.add(i, ad);
        if (arrayList.size() > (AD_POSITION + (i * AD_POSITION_EVERY_COUNT))) {
            arrayList.add(AD_POSITION + (i * AD_POSITION_EVERY_COUNT), ad);
            adapter.notifyItemInserted(AD_POSITION + (i * AD_POSITION_EVERY_COUNT));
        }
    }

    private void loadData() {
        arrayList = new ArrayList<>();

        arrayList.add(new MyModel("Vinay Singh", "I'm from DD4You.in"));
        arrayList.add(new MyModel("Mohan Singh", "He is nice guy"));
        arrayList.add(new MyModel("Ram Maurya", "He is also  a nice guy"));
        arrayList.add(new MyModel("Raju Yadav", "He is from India"));
        arrayList.add(new MyModel("Pradip Ghosh", "He is my subscriber"));
        arrayList.add(new MyModel("My Sister", "I Miss you my sister"));

        arrayList.add(new MyModel("Vinay Singh", "I'm from DD4You.in"));
        arrayList.add(new MyModel("Mohan Singh", "He is nice guy"));
        arrayList.add(new MyModel("Ram Maurya", "He is also  a nice guy"));
        arrayList.add(new MyModel("Raju Yadav", "He is from India"));
        arrayList.add(new MyModel("Pradip Ghosh", "He is my subscriber"));
        arrayList.add(new MyModel("My Sister", "I Miss you my sister"));

        arrayList.add(new MyModel("Vinay Singh", "I'm from DD4You.in"));
        arrayList.add(new MyModel("Mohan Singh", "He is nice guy"));
        arrayList.add(new MyModel("Ram Maurya", "He is also  a nice guy"));
        arrayList.add(new MyModel("Raju Yadav", "He is from India"));
        arrayList.add(new MyModel("Pradip Ghosh", "He is my subscriber"));
        arrayList.add(new MyModel("My Sister", "I Miss you my sister"));

        adapter = new MyAdapter(this, arrayList);
        recyclerView.setAdapter(adapter);
    }

    private void showInterstitialAd() {
        interstitialAd = new InterstitialAd(this, getString(R.string.interstitial_id));
        interstitialAd.setAdListener(new InterstitialAdListener() {
            @Override
            public void onInterstitialDisplayed(Ad ad) {
                // Interstitial ad displayed callback
                // Log.e(TAG, "Interstitial ad displayed.");
            }

            @Override
            public void onInterstitialDismissed(Ad ad) {
                // Interstitial dismissed callback
                //  Log.e(TAG, "Interstitial ad dismissed.");
            }

            @Override
            public void onError(Ad ad, AdError adError) {
                // Ad error callback
                //   Log.e(TAG, "Interstitial ad failed to load: " + adError.getErrorMessage());
            }

            @Override
            public void onAdLoaded(Ad ad) {
                // Interstitial ad is loaded and ready to be displayed
                //Log.d(TAG, "Interstitial ad is loaded and ready to be displayed!");
                // Show the ad
                interstitialAd.show();
            }

            @Override
            public void onAdClicked(Ad ad) {
                // Ad clicked callback
                // Log.d(TAG, "Interstitial ad clicked!");
            }

            @Override
            public void onLoggingImpression(Ad ad) {
                // Ad impression logged callback
                //Log.d(TAG, "Interstitial ad impression logged!");
            }
        });

        // For auto play video ads, it's recommended to load the ad
        // at least 30 seconds before it is shown
        interstitialAd.loadAd();
    }

    private void showBannerAd() {
        adView = new AdView(this, getString(R.string.banner_id), AdSize.BANNER_HEIGHT_50);
        LinearLayout adContainer = (LinearLayout) findViewById(R.id.banner_container);
        adContainer.addView(adView);
        adView.loadAd();
    }

    @Override
    protected void onDestroy() {
        if (adView != null) {
            adView.destroy();
        }
        if (adView != null) {
            adView.destroy();
        }
        super.onDestroy();
    }
}