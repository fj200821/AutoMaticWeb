/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package cn.edu.hpu.autoweb.test;



import cn.edu.hpu.autoweb.util.idcard.util.CommonUtil;
import org.apache.http.HttpResponse;

import java.util.*;

/**
 * 调用示例
 * 请替换APP_KEY,APP_SECRET,HOST,CUSTOM_HEADERS_TO_SIGN_PREFIX为真实配置
 */
public class Demo {
    //APP KEY
    private final static String APP_KEY = "23869367";
    // APP密钥
    private final static String APP_SECRET = "90fd05583ff8f7ded39568ad7fb1e438";
    //测试图片位置

    private final static String basePath = "C:\\Users\\Aries\\Pictures\\Saved Pictures\\1111.jpg";
    //API域名
    private final static String HOST = "dm-51.data.aliyun.com";
    //url
    private final static String URL = "/rest/160601/ocr/ocr_idcard.json";
    //自定义参与签名Header前缀（可选,默认只有"X-Ca-"开头的参与到Header签名）
    private final static List<String> CUSTOM_HEADERS_TO_SIGN_PREFIX = new ArrayList<String>();

    public  static void main(String[] args)throws Exception {
        CUSTOM_HEADERS_TO_SIGN_PREFIX.add("Custom");
        CommonUtil commonUtil = new CommonUtil(HOST, URL, APP_KEY, APP_SECRET, CUSTOM_HEADERS_TO_SIGN_PREFIX, basePath);
        HttpResponse response = commonUtil.sendPostRequestWithBody();

            Map map = new HashMap();
    }
}
