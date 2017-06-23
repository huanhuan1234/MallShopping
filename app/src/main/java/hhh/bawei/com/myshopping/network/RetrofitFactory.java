package hhh.bawei.com.myshopping.network;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import hhh.bawei.com.myshopping.jiekou.LoginService;
import hhh.bawei.com.myshopping.network.cookie.CookiesManager;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * 8.
 */

public class RetrofitFactory {

//底层每次都会新new OkHttpClient 现在就创建一个
    public static OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .cookieJar(new CookiesManager())
            .connectTimeout(20, TimeUnit.SECONDS)
            .readTimeout(20,TimeUnit.SECONDS)
            .writeTimeout(20,TimeUnit.SECONDS)
            .addInterceptor(new LoggingInterceptor())
            .build();


    public  static LoginService apiService = new Retrofit.Builder()
            .baseUrl("http://qbh.2dyt.com")
            .addConverterFactory(ScalarsConverterFactory.create())
            .client(okHttpClient)
            //把 以前的 call 转化成 Observable
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build().create(LoginService.class);



    public  static Observable<String> get(String url){

        return apiService.get(url)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

    }


    public static Observable<String> get(String url, Map<String,String> map){

        return apiService.get(url,map).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }



    public static Observable<String> post(String url,Map<String,String> map){
        return  apiService.post(url,map).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

}
