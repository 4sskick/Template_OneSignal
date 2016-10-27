package com.nightroomcreation.exampletestonesignal;

import android.content.Intent;
import android.util.Log;

import com.nightroomcreation.exampletestonesignal.activity.Sub_MainActivity;
import com.onesignal.OSNotification;
import com.onesignal.OSNotificationAction;
import com.onesignal.OSNotificationOpenResult;
import com.onesignal.OneSignal;

import org.json.JSONObject;

import static com.nightroomcreation.exampletestonesignal.GlobalVariable.TAG;

/**
 * Created by Administrator on 23-Oct-16.
 */

public class OneSignalNotificationOpenedHandler implements OneSignal.NotificationOpenedHandler {

    private static String stringAction;
    private static String stringActionContent;

    @Override
    public void notificationOpened(OSNotificationOpenResult result) {
        OSNotification osNotification = result.notification;
        OSNotificationAction.ActionType actionType = result.action.type;

        //get notification additional Data in JSONObject format
        JSONObject jo = osNotification.payload.additionalData;

        //get string from additional Data with key 'CustomKey' and default 'null'
        //difference between JSONObject getString() and optString()
        /**
         * when the key of getString() doesn't exist it will be thrown JSONException
         * but when the of optString() doesn't exist it will be return "<empty>" string
         */
        stringAction = jo.optString("action", null);
        stringActionContent = jo.optString("action_content", null);

        //check for value of additionalData sent from OneSignal
        if (stringAction.equals("")) {
            Log.d(TAG, "notificationOpened: stringAction Null");
        } else {
            GlobalFunction.setActionIntent_pref(stringAction);
        }

        //content additional data
        if (stringActionContent.equals("")) {
            Log.d(TAG, "notificationOpened: stringActionContent Null");
        } else {
            GlobalFunction.setActionContentIntent_pref(stringActionContent);

            Intent intent = new Intent(CustomApplication.getContext(), Sub_MainActivity.class);
            intent.putExtra("content_data", GlobalFunction.getActionContentIntent_pref());
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            CustomApplication.getContext().startActivity(intent);
        }

        if (actionType == OSNotificationAction.ActionType.ActionTaken) {
            Log.i(TAG, "notificationOpened: Button Pressed with ID: " + result.action.actionID);
        }

        if (jo != null) {
            Log.d(TAG, jo.toString());

            GlobalFunction.setJSONResultOneSignal_pref(jo.toString());
        }

        //enableInAppAlertNotification and enableNotificationsWhenActive have been combined into
        // inFocusDisplaying which is set from the OneSignal.Builder
        Log.d(TAG, "notificationOpened: App in Focus: " + osNotification.isAppInFocus);
    }
}
