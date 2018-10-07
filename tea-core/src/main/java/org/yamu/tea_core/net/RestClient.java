package org.yamu.tea_core.net;

import android.content.Context;

import org.yamu.tea_core.net.callback.IError;
import org.yamu.tea_core.net.callback.IFailure;
import org.yamu.tea_core.net.callback.IRequest;
import org.yamu.tea_core.net.callback.ISuccess;
import org.yamu.tea_core.net.callback.RequestCallbacks;
import org.yamu.tea_core.net.donwload.DonwloadHandler;
import org.yamu.tea_core.ui.loader.LoaderStyle;
import org.yamu.tea_core.ui.loader.SignLoader;
import org.yamu.tea_core.ui.loader.TeaLoader;

import java.io.File;
import java.util.WeakHashMap;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by 马杰涛 on 2018/10/1
 */

public class RestClient {

    private final String URL;
    private static final WeakHashMap<String, Object> PARAMS = RestCreator.getParams();
    private final IRequest REQUEST;
    private final String DOWNLOAD_DIR;
    private final String EXTENSION;
    private final String NAME;
    private final ISuccess SUCCESS;
    private final IFailure FAILURE;
    private final IError ERROR;
    private final RequestBody BODY;
    private final LoaderStyle LOADER_STYLE;
    private final File FILE;
    private final Context CONTEXT;

    public RestClient(String URL,
                      WeakHashMap<String, Object> params,
                      IRequest request,
                      ISuccess success,
                      IFailure failure,
                      IError error,
                      RequestBody body,
                      File file,
                      String donwloadDir,
                      String extension,
                      String name,
                      Context cotext,
                      LoaderStyle loaderStyle) {
        this.URL = URL;
        PARAMS.putAll(params);
        this.REQUEST = request;
        this.SUCCESS = success;
        this.FAILURE = failure;
        this.ERROR = error;
        this.BODY = body;
        this.DOWNLOAD_DIR = donwloadDir;
        this.EXTENSION = extension;
        this.NAME = name;
        this.FILE = file;
        this.CONTEXT = cotext;
        this.LOADER_STYLE = loaderStyle;
    }

    public static RestClientBuilder builder() {
        return new RestClientBuilder();
    }

    private void request(HttpMethod method) {
        final RestService service = RestCreator.getRestService();
        Call<String> call = null;
        if (REQUEST != null) {
            REQUEST.onRequestStart();
        }

        if (LOADER_STYLE != null) {
            SignLoader.showLoading(CONTEXT, LOADER_STYLE.name());
        }

        switch (method) {
            case GET:
                call = service.get(URL, PARAMS);
                break;
            case POST:
                call = service.post(URL, PARAMS);
                break;
            case POST_RAW:
                call = service.postRaw(URL, BODY);
                break;
            case PUT:
                call = service.put(URL, PARAMS);
                break;
            case PUT_RAW:
                call = service.putRaw(URL, BODY);
                break;
            case DELETE:
                call = service.delete(URL, PARAMS);
                break;
            case UPLOAD:
                final RequestBody requestBody =
                        RequestBody.create(MediaType.parse(MultipartBody.FORM.toString()), FILE);
                final MultipartBody.Part body =
                        MultipartBody.Part.createFormData("file", FILE.getName(), requestBody);
                call = RestCreator.getRestService().upload(URL, body);
                break;
            default:
                break;
        }

        if (call != null) {
            call.enqueue(getRequestCallback());
        }
    }

    private Callback<String> getRequestCallback() {
        return new RequestCallbacks(
                REQUEST,
                SUCCESS,
                FAILURE,
                ERROR,
                LOADER_STYLE
        );
    }

    public final void get() {
        request(HttpMethod.GET);
    }

    public final void post() {
        if (BODY == null)
            request(HttpMethod.POST);
        else if (!PARAMS.isEmpty())
            throw new RuntimeException("params must be null!");
        request(HttpMethod.POST_RAW);
    }


    public final void put() {
        if (BODY == null)
            request(HttpMethod.PUT);
        else if (!PARAMS.isEmpty())
            throw new RuntimeException("params must be null!");
        request(HttpMethod.PUT_RAW);

    }

    public final void delete() {
        request(HttpMethod.DELETE);
    }

    public final void upload() {
        request(HttpMethod.UPLOAD);
    }

    public final void donwload() {
        new DonwloadHandler(URL, REQUEST, DOWNLOAD_DIR, EXTENSION, NAME, SUCCESS, FAILURE, ERROR)
                .handleDownload();
    }
}
