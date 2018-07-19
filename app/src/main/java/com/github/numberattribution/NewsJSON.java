package com.github.numberattribution;

import java.util.ArrayList;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class NewsJSON {

    private Header headers;

    public NewsJSON(String jsonContent) {
        headers = new Header();
        JSONParser(jsonContent);
    }

    public Header getHeaders() {
        return headers;
    }

    private void JSONParser(String jsonContent) {
        if (jsonContent.length() != 0 && jsonContent != "") {

            JSONObject object = JSON.parseObject(jsonContent);
            JSONObject ob = object.getJSONObject("result");

            Header header = new Header();

            header.setProvince(ob.getString("province"));
            header.setCity(ob.getString("city"));
            header.setCompany(ob.getString("company"));
            header.setCardtype(ob.getString("cardtype"));

            headers = header;

//			System.out.println(headers.toString());
        }

    }

}
