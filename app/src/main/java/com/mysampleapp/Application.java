//
// Copyright 2016 Amazon.com, Inc. or its affiliates (Amazon). All Rights Reserved.
//
// Code generated by AWS Mobile Hub. Amazon gives unlimited permission to 
// copy, distribute and modify it.
//
// Source code generated from template: aws-my-sample-app-android v0.8
//
package com.mysampleapp;

import android.support.multidex.MultiDexApplication;
import android.util.Log;

import com.amazonaws.mobile.AWSMobileClient;
import com.amazonaws.mobile.push.PushManager;

import tw.bir.agrimessenger.service.APIManager;

/**
 * Application class responsible for initializing singletons and other common components.
 */
public class Application extends MultiDexApplication {

    private final static String LOG_TAG = Application.class.getSimpleName();
    private APIManager apiManager;

    @Override
    public void onCreate() {
        Log.d(LOG_TAG, "Application.onCreate - Initializing application...");
        super.onCreate();
        initializeApplication();
        Log.d(LOG_TAG, "Application.onCreate - Application initialized OK");
        apiManager = APIManager.sharedManager(this);
    }

    private void initializeApplication() {
        AWSMobileClient.initializeMobileClientIfNecessary(getApplicationContext());

        // Set a listener for changes in push notification state
        PushManager.setPushStateListener(new PushManager.PushStateListener() {
            @Override
            public void onPushStateChange(final PushManager pushManager, boolean isEnabled) {
                Log.d(LOG_TAG, "Push Notifications Enabled = " + isEnabled);
                // ...Put any application-specific push state change logic here...
            }
        });

        // ...Put any application-specific initialization logic here...
    }
    public APIManager applicationAPIManager(){
        return apiManager;
    }
    public void registered(){

    }
}
