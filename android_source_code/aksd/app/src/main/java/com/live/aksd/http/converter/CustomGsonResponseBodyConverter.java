package com.live.aksd.http.converter;


import com.live.aksd.http.exception.ServerException;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;

import okhttp3.MediaType;
import okhttp3.ResponseBody;
import retrofit2.Converter;

import static okhttp3.internal.Util.UTF_8;


/**
 * @author YorkYu
 * @version V1.0
 * @Description:
 * @time 2017/3/7 10:30
 */
final class CustomGsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {
    private final Gson gson;
    private final TypeAdapter<T> adapter;
    private final JsonParser jsonParser;

    CustomGsonResponseBodyConverter(Gson gson, TypeAdapter<T> adapter) {
        this.gson = gson;
        this.adapter = adapter;
        jsonParser = new JsonParser();
    }

    @Override
    public T convert(ResponseBody value) throws IOException {
        String response = value.string();
        JsonElement jsonElement = jsonParser.parse(response);
        String parseCode = jsonElement.getAsJsonObject().get("status").getAsString();

        if (!parseCode.equals("ok")) {
            value.close();
            String msg = jsonElement.getAsJsonObject().get("error").getAsString();
            throw new ServerException(msg, parseCode);
        } else {

            MediaType contentType = value.contentType();
            Charset charset = contentType != null ? contentType.charset(UTF_8) : UTF_8;
            InputStream inputStream = new ByteArrayInputStream(response.getBytes());
            Reader reader = new InputStreamReader(inputStream, charset);
            JsonReader jsonReader = gson.newJsonReader(reader);

            try {
                return adapter.read(jsonReader);
            } finally {
                value.close();
            }
        }
    }
}
