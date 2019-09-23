/*
 * Copyright (C) 2017 Baidu, Inc. All Rights Reserved.
 */
package com.example.facetest.utils;

import com.example.facetest.exception.FaceError;
import com.example.facetest.model.FaceVerifyResult;

import org.json.JSONObject;


public class FaceVerifyParser implements Parser<FaceVerifyResult> {
    @Override
    public FaceVerifyResult parse(String json) throws FaceError {
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONObject resultObject = jsonObject.getJSONArray("result").getJSONObject(0);
            FaceVerifyResult verifyResult = new FaceVerifyResult();
            verifyResult.setLivenss(resultObject.optDouble("faceliveness"));
            return verifyResult;
        } catch (Exception e) {
            e.printStackTrace();
            throw new FaceError();
        }
    }
}
