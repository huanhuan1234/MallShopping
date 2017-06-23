package hhh.bawei.com.myshopping.jiekou;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

/**
 * Created by : Xunqiang
 * 2017/6/21 16:12
 */

public interface LoginService {


    @GET
    public Observable<String> get(@Url String url);

    @GET
    public Observable<String> get(@Url String url, @QueryMap Map<String, String> map);

    @FormUrlEncoded
    @POST
    public Observable<String> post(@Url String url, @FieldMap Map<String, String> map);
}
