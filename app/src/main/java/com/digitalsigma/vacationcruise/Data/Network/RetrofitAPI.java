package com.digitalsigma.vacationcruise.Data.Network;


import com.digitalsigma.vacationcruise.Model.AllCruises;


import io.reactivex.Observable;
import io.reactivex.Single;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Hima on 10/31/2018.
 */

public interface RetrofitAPI {
    @FormUrlEncoded
    @POST("{id}/booking")
    Single<String> bookCrusie(@Path("id") int id,
                              @Field("name") String name,
                              @Field("phone") String phone,
                              @Field("email") String email,
                              @Field("nationality") String nationality,
                              @Field("persons") String persons,
                              @Field("rooms") String rooms,
                              @Field("start_date") String startDate,
                              @Field("end_date") String endDate,
                              @Field("note") String note);

    /*@GET("users?page=2")
    Observable<AllUsers> getUsers();*/

    @GET("cruises")
    Single<AllCruises> getCruises(@Query("page") int page);

    @GET("cruises")
    Observable<AllCruises> getCruises2(@Query("page") int page);
}
