package com.nightroomcreation.exampletestonesignal;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.onesignal.OneSignal;

import static com.google.android.gms.internal.zzs.TAG;

/**
 * Created by Administrator on 23-Oct-16.
 */

public class CustomApplication extends Application {

    private static Context context;
    private static CustomApplication mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        mInstance = this;

        //starting to config OneSignal
        // Logging set to help debug issues, remove before releasing your app.
        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.DEBUG, OneSignal.LOG_LEVEL.WARN);

        OneSignal.startInit(this)
                .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.InAppAlert)
                .setNotificationOpenedHandler(new OneSignalNotificationOpenedHandler())
                .autoPromptLocation(true)
                .init();

        //Sync hashed email if you have a login system or collect it.
        //Will be used to reach the user at the most optimal time of day.
        //OneSignal.syncHashedEmail(userEmail);

        //check whether user has an ID and has been registered on OneSignal or not
        OneSignal.idsAvailable(new OneSignal.IdsAvailableHandler() {
            @Override
            public void idsAvailable(String userId, String registrationId) {

                if (registrationId == null){
                    registrationId = "";
                }

                //set value into preference
                GlobalFunction.setOneSignalUserId_pref(userId, registrationId);

                //logging data values
                Log.d(TAG, "idsAvailable: "+GlobalFunction.getOneSignalUserId_pref());

                OneSignal.sendTag("userId", String.valueOf(GlobalFunction.getOneSignalUserId_pref()));
            }
        });
    }

    public static synchronized CustomApplication getInstance(){
        return mInstance;
    }

    public static Context getContext(){
        return context;
    }
}
