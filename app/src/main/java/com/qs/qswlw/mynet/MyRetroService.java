package com.qs.qswlw.mynet;


import com.qs.qswlw.bean.AngelRankingBean;
import com.qs.qswlw.bean.ConsumptionRecordBean;
import com.qs.qswlw.bean.EntrepreneurialDialogBean;
import com.qs.qswlw.bean.EntrepreneurialIncentiveBean;
import com.qs.qswlw.bean.ForgetPassWordBean;
import com.qs.qswlw.bean.GoodProductBean;
import com.qs.qswlw.bean.LoginBean;
import com.qs.qswlw.bean.MainBean;
import com.qs.qswlw.bean.Maindatabean;
import com.qs.qswlw.bean.MerchantAuditBean;
import com.qs.qswlw.bean.MerchantAuditClickBean;
import com.qs.qswlw.bean.MyProfitBean;
import com.qs.qswlw.bean.MyRoleBean;
import com.qs.qswlw.bean.MySliverBean;
import com.qs.qswlw.bean.OldMemberBean;
import com.qs.qswlw.bean.PersonalSettingBean;
import com.qs.qswlw.bean.RankingBean;
import com.qs.qswlw.bean.RecommendedRecordsBean;
import com.qs.qswlw.bean.RecordListBaseBean;
import com.qs.qswlw.bean.RegisterBean;
import com.qs.qswlw.bean.RegisterCheckIdBean;
import com.qs.qswlw.bean.RegisterGetCodeBean;
import com.qs.qswlw.bean.ScanCodeRecordBean;
import com.qs.qswlw.bean.ValidateOldMemberBean;
import com.qs.qswlw.bean.VenturegoldBean;

import java.io.File;
import java.util.List;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;
import retrofit2.http.Url;
import rx.Observable;

/**
 * Created by buring on 2017/5/26.
 */

public interface MyRetroService {
    @GET("index.php?m=Appapi&c=Index&a=index")
    Observable<TestBean> getCommissionSummaryDaily();

    @GET
    Observable<TestBean> getCommissionSummaryDaily(@Url String url);

    /**
     * 给上注释
     *
     * @return
     */
    @POST("index.php?m=Appapi&c=Index&a=index")
    Observable<MainBean<Maindatabean>> getALLdata();

    @Multipart//这是什么--标注 参数格式 prat 括号里对应的是KEY 后面对应的是 v 那不应该写成这个吗 嗯就是这样，value不用该 到时候传就是是吧 对
    //如果有多个参数-就这样
    @POST("index.php?m=Appapi&c=Index&a=ranking_list4")
//头去掉 因为写过了，在那些了
    Observable<MainBean<RankingBean>> getRankingData(@Part("time_slot") String a);

    /**
     * 获取短信验证码
     *
     * @param a
     * @return
     */
    @FormUrlEncoded
    @POST("index.php?m=Appapi&c=Login&a=get_register_send")
    Observable<MainBean<RegisterGetCodeBean>> getCodeData(@Field("mobile") String a);

    @FormUrlEncoded
    @POST("index.php?m=Appapi&c=Login&a=get_register_send")
    Observable<MainBean<RegisterGetCodeBean>> getForgetPwCodeData(@Field("mobile") String a, @Field("type") int type);

    /**
     * 创业种子
     */
    @FormUrlEncoded
    @POST("index.php?m=Appapi&c=UserBonus&a=love")
    Observable<MainBean<EntrepreneurialIncentiveBean>> getEntrepreneurialData(@Field("token") String token, @Field("p") int p, @Field("model") String model);

    /**
     * 录单记录
     */

    @GET("index.php?m=Appapi&c=Single&a=formlist")
    Observable<MainBean<RecordListBaseBean>> getRecordListData(@Query("token") String token, @Query("p") int p, @Query("type") String type, @Query("is_go") String is_go);
    /**
     * 商家审核消费录单
     */
    @GET("index.php?m=Appapi&c=Single&a=shop_review_edit")
    Observable<MainBean<MerchantAuditClickBean>> getMerchantAuditClickData(@Query("token") String token,@Query("id") int id);


    /**
     * 获得消费银豆记录
     */
    @FormUrlEncoded
    @POST("index.php?m=Appapi&c=UserBonus&a=silver")
    Observable<MainBean<MySliverBean>> getMySliverBeanData(@Field("token") String token, @Field("p") int p);



    /**
     * 商家审核
     */
    @FormUrlEncoded
    @POST("index.php?m=Appapi&c=Single&a=shop_review")
    Observable<MainBean<MerchantAuditBean>> getMerchantAuditData(@Field("token") String token, @Field("p") int p,@Field("is_ok") int is_ok);

    /**
     * 我的管理费
     */
    @FormUrlEncoded
    @POST("index.php?m=Appapi&c=Single&a=my_none")
    Observable<MainBean<MyProfitBean>> getMyProfitData(@Field("token") String token,@Field("p") int p);

    /**
     * 兑换创业种子数
     */
    @FormUrlEncoded
    @POST("index.php?m=Appapi&c=UserBonus&a=do_love")
    Observable<MainBean<EntrepreneurialDialogBean>> postEntrepreneurialDialog(@Field("token") String token, @Field("model") String model, @Field("love") int love);


    /**
     * 我的角色
     */
    @FormUrlEncoded
    @POST("index.php?m=Appapi&c=User&a=my_user")
    Observable<MainBean<MyRoleBean>> getMyRoleData(@Field("token") String token);

    /**
     * 线下消费录单
     */
    @FormUrlEncoded
    @POST("index.php?m=Appapi&c=Single&a=getmoney")
    Observable<MainBean<ConsumptionRecordBean>> getConsumptionRecordData(@Field("token") String token);

    /**
     * 提交消费录单
     */
    @FormUrlEncoded
    @POST("index.php?m=Appapi&c=Single&a=do_money")
    Observable<MainBean> PostConsumptionData(@Field("token") String token, @Field("uid") int uid, @Field("money") float money, @Field("ratio") float ratio,
                                             @Field("none") float none, @Field("ratio_key") String ratio_key, @Field("pay_type") String pay_type, @Field("pay_time") String pay_time,
                                             @Field("pay_name") String pay_name, @Field("proof") File proof, @Field("remittance") File remittance);
    /**
     *商家审核提交消费录单
     */
    @FormUrlEncoded
    @POST("index.php?m=Appapi&c=Single&a=shop_review_audited")
    Observable<MainBean> PostConsumptionData1(@Field("token") String token, @Field("id") int id, @Field("goods_id") int goods_id, @Field("uid") int uid,
                                              @Field("money") float money, @Field("ratio") float ratio, @Field("none") float none, @Field("ratio_key") String ratio_key,
                                              @Field("pay_type") String pay_type,@Field("pay_name") String pay_name,@Field("pay_time") String pay_time,@Field("msales_su") int msales_su,
                                              @Field("proof") File proof, @Field("remittance") File remittance);

    /**
     * 线下门店扫码
     */
    @FormUrlEncoded
    @POST("index.php?m=Appapi&c=Single&a=user_review")
    Observable<MainBean<ScanCodeRecordBean>> getScanCodeRecordData(@Field("token") String token, @Field("p") int p, @Field("is_ok") int is_ok);

    /**
     * 提交密码重设
     *
     * @param mobile
     * @param roles
     * @param pass
     * @param repass
     * @param type
     * @param mobile_code
     * @return
     */
    @FormUrlEncoded
    @POST("index.php?m=Appapi&c=Login&a=do_password")
    Observable<MainBean<ForgetPassWordBean>> postForgetPWData(@Field("mobile") String mobile, @Field("roles") int roles, @Field("pass") String pass,
                                                              @Field("repass") String repass, @Field("type") String type,
                                                              @Field("mobile_code") String mobile_code);

    /**
     * 提交验证老会员信息
     */
    @FormUrlEncoded
    @POST("index.php?m=Appapi&c=User&a=do_check_old_member")
    Observable<MainBean<OldMemberBean>> postOldMemberData(@Field("token") String token, @Field("member_number") String member_number, @Field("member_type") String member_type);

    /**
     * 我是老会员
     */
    @FormUrlEncoded
    @POST("index.php?m=Appapi&c=User&a=check_old_member")
    Observable<MainBean<ValidateOldMemberBean>> getOldMemberData(@Field("token") String token);

    /**
     * 获取推荐人信息
     *
     * @param a
     * @return
     */
    @FormUrlEncoded
    @POST("index.php?m=Appapi&c=Login&a=ajaxuser")
    Observable<MainBean<RegisterCheckIdBean>> getIdData(@Field("uid") int a);


    /**
     * 个人中心
     */
    @FormUrlEncoded
    @POST("index.php?m=Appapi&c=User&a=role")
    Observable<MainBean<PersonalSettingBean>> getPersonalData(@Field("token") String token);

    /**
     * 我的金豆
     */
    @FormUrlEncoded
    @POST("index.php?m=Appapi&c=UserBonus&a=gold")
    Observable<MainBean<VenturegoldBean>> getVentureGoldData(@Field("token") String token, @Field("p") int p, @Field("model") String model, @Field("gold_type") String gold_type, @Field("type") String type);

    /**
     * 推荐记录
     */
    @FormUrlEncoded
    @POST("index.php?m=Appapi&c=User&a=recommend_list")
    Observable<MainBean<List<RecommendedRecordsBean>>> getRecommendRecordData(@Field("token") String token, @Field("p") int p, @Field("search_name") String search_name, @Field("tab_name") String tab_name);

    /**
     * 注册
     *
     * @param mobile
     * @param id
     * @param nickname
     * @param password
     * @param repassword
     * @param role
     * @param post_code
     * @return
     */
    @FormUrlEncoded
    @POST("index.php?m=Appapi&c=Login&a=do_register")
    Observable<MainBean<RegisterBean>> postRgisterData(@Field("mobile") String mobile, @Field("id") int id, @Field("nickname") String nickname,
                                                       @Field("password") String password, @Field("repassword") String repassword,
                                                       @Field("role") int role, @Field("post_code") String post_code);

    /**
     * 登录
     *
     * @param username
     * @param password
     * @return
     */
    @FormUrlEncoded
    @POST("index.php?m=Appapi&c=Login&a=do_login")
    Observable<MainBean<LoginBean>> postLogin(@Field("username") String username, @Field("password") String password);

    /**
     * 创业天使创业排名榜
     *
     * @return
     */
    @Multipart
    @POST("index.php?m=Appapi&c=Index&a=ranking_list3")
//头去掉 因为写过了，在那些了
    Observable<MainBean<AngelRankingBean>> getAngelRankingData(@Part("time_slot") String a);

    @POST("index.php?m=Appapi&c=Index&a=good_product")
/**括号里面是路径*/
    Observable<MainBean<GoodProductBean>> getGoodproductdata();


    /**
     * 如果 头部不是实用 就是说有一些是特殊的 不一样的 哦哦 如果不是http://qiansheng.com开头的我就全部写 不对  要改成这样
     * 还有
     */
    //Observable<MainBean<GoodProductBean>> getGoodproductdata(@Url String url);//传进来全部的  哦哦
}
