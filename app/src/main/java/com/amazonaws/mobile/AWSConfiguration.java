//
// Copyright 2016 Amazon.com, Inc. or its affiliates (Amazon). All Rights Reserved.
//
// Code generated by AWS Mobile Hub. Amazon gives unlimited permission to 
// copy, distribute and modify it.
//
// Source code generated from template: aws-my-sample-app-android v0.8
//
package com.amazonaws.mobile;

import com.amazonaws.regions.Regions;

/**
 * This class defines constants for the developer's resource
 * identifiers and API keys. This configuration should not
 * be shared or posted to any public source code repository.
 */
public class AWSConfiguration {

    // AWS MobileHub user agent string
    public static final String AWS_MOBILEHUB_USER_AGENT =
        "MobileHub 9bd23780-c85d-4cbd-8bf3-a2e6e41d4d62 aws-my-sample-app-android-v0.8";
    // AMAZON COGNITO
    public static final Regions AMAZON_COGNITO_REGION =
      Regions.fromName("ap-northeast-1");
    public static final String  AMAZON_COGNITO_IDENTITY_POOL_ID =
        "ap-northeast-1:1ec60dcb-e03f-467c-8c69-37d917163ff0";
    // GOOGLE CLOUD MESSAGING API KEY
    public static final String GOOGLE_CLOUD_MESSAGING_API_KEY =
        "AIzaSyCzJSkcWys35oyzNw9N6IkviW5cFTfZyf8";
    // GOOGLE CLOUD MESSAGING SENDER ID
    public static final String GOOGLE_CLOUD_MESSAGING_SENDER_ID =
        "66904834450";

    // SNS PLATFORM APPLICATION ARN
    public static final String AMAZON_SNS_PLATFORM_APPLICATION_ARN =
        "arn:aws:sns:ap-northeast-1:322959393641:app/GCM/agrimessenger_MOBILEHUB_792670297";
    public static final Regions AMAZON_SNS_REGION =
         Regions.fromName("ap-northeast-1");
    // SNS DEFAULT TOPIC ARN
    public static final String AMAZON_SNS_DEFAULT_TOPIC_ARN =
        "arn:aws:sns:ap-northeast-1:322959393641:agrimessenger_alldevices_MOBILEHUB_792670297";
    // SNS PLATFORM TOPIC ARNS
    public static final String[] AMAZON_SNS_TOPIC_ARNS =
        {};
}
