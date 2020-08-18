package in.dd4you.facebookads;

import android.app.Application;

import com.facebook.ads.AudienceNetworkAds;

/**
 * Created by Vinay Singh (https://www.dd4you.in) on 14/08/2020
 * Copyright (c) 2018-2020. All rights reserved.
 */
public class app extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        AudienceNetworkAds.initialize(this);
    }
}
