package com.nightroomcreation.exampletestonesignal;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Administrator on 23-Oct-16.
 */

public class GlobalFunction {

    //setting preferences for onesignal
    public static void setOneSignalUserId_pref(String userId, String regId) {
        SharedPreferences sp = CustomApplication.getContext().getSharedPreferences("onesignal_userId", Context.MODE_PRIVATE);
        SharedPreferences.Editor spEditor = sp.edit();
        spEditor.putString("USER_ID", userId);
        spEditor.putString("GCM_REG_ID", regId);
        spEditor.apply();
    }

    public static String getOneSignalUserId_pref() {
        SharedPreferences sp = CustomApplication.getContext().getSharedPreferences("onesignal_userId", Context.MODE_PRIVATE);
        return sp.getString("USER_ID", "");
    }

    public static void setJSONResultOneSignal_pref(String jsonObject){
        SharedPreferences sp = CustomApplication.getContext().getSharedPreferences("onesignal_respond", Context.MODE_PRIVATE);
        SharedPreferences.Editor spEditor = sp.edit();
        spEditor.putString("RESPOND", jsonObject);
        spEditor.apply();
    }

    //setting preferences for action and content of action
    public static void setActionIntent_pref(String actionIntent) {
        SharedPreferences sp = CustomApplication.getContext().getSharedPreferences("action_intent", Context.MODE_PRIVATE);
        SharedPreferences.Editor spEditor = sp.edit();
        spEditor.putString("ACTION", actionIntent);
        spEditor.apply();
    }

    public static String getActionIntent_pref() {
        SharedPreferences sp = CustomApplication.getContext().getSharedPreferences("action_intent", Context.MODE_PRIVATE);
        return sp.getString("ACTION", "");
    }

    public static void setActionContentIntent_pref(String actionContent) {
        SharedPreferences sp = CustomApplication.getContext().getSharedPreferences("action_content_intent", Context.MODE_PRIVATE);
        SharedPreferences.Editor spEditor = sp.edit();
        spEditor.putString("ACTION_CONTENT", actionContent);
        spEditor.apply();
    }

    public static String getActionContentIntent_pref() {
        SharedPreferences sp = CustomApplication.getContext().getSharedPreferences("action_content_intent", Context.MODE_PRIVATE);
        return sp.getString("ACTION_CONTENT", "");
    }
}
