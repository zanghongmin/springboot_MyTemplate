package top.zang.es;

import top.zang.util.MyJsonUtil;

import java.util.ArrayList;
import java.util.List;

public class ESinit {

    public static String inits = "[\n" +
            "      {\n" +
            "        \"id\": 31,\n" +
            "        \"productSn\": \"HNTBJ2E080A\",\n" +
            "        \"brandId\": 50,\n" +
            "        \"brandName\": \"海澜之家\",\n" +
            "        \"productCategoryId\": 8,\n" +
            "        \"productCategoryName\": \"T恤\",\n" +
            "        \"pic\": \"http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180615/5ac98b64N70acd82f.jpg!cc_350x449.jpg\",\n" +
            "        \"name\": \"HLA海澜之家蓝灰花纹圆领针织布短袖T恤\",\n" +
            "        \"subTitle\": \"2018夏季新品短袖T恤男HNTBJ2E080A 蓝灰花纹80 175/92A/L80A 蓝灰花纹80 175/92A/L\",\n" +
            "        \"keywords\": \"\",\n" +
            "        \"price\": 98,\n" +
            "        \"sale\": 0,\n" +
            "        \"newStatus\": 0,\n" +
            "        \"recommandStatus\": 0,\n" +
            "        \"stock\": 100,\n" +
            "        \"promotionType\": 0,\n" +
            "        \"sort\": 0,\n" +
            "        \"attrValueList\": [\n" +
            "          {\n" +
            "            \"id\": 183,\n" +
            "            \"productAttributeId\": 24,\n" +
            "            \"value\": null,\n" +
            "            \"type\": 1,\n" +
            "            \"name\": \"商品编号\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"id\": 184,\n" +
            "            \"productAttributeId\": 25,\n" +
            "            \"value\": \"夏季\",\n" +
            "            \"type\": 1,\n" +
            "            \"name\": \"适用季节\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"id\": 185,\n" +
            "            \"productAttributeId\": 37,\n" +
            "            \"value\": \"青年\",\n" +
            "            \"type\": 1,\n" +
            "            \"name\": \"适用人群\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"id\": 186,\n" +
            "            \"productAttributeId\": 38,\n" +
            "            \"value\": \"2018年夏\",\n" +
            "            \"type\": 1,\n" +
            "            \"name\": \"上市时间\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"id\": 187,\n" +
            "            \"productAttributeId\": 39,\n" +
            "            \"value\": \"短袖\",\n" +
            "            \"type\": 1,\n" +
            "            \"name\": \"袖长\"\n" +
            "          }\n" +
            "        ]\n" +
            "      },\n" +
            "      {\n" +
            "        \"id\": 30,\n" +
            "        \"productSn\": \"HNTBJ2E042A\",\n" +
            "        \"brandId\": 50,\n" +
            "        \"brandName\": \"海澜之家\",\n" +
            "        \"productCategoryId\": 8,\n" +
            "        \"productCategoryName\": \"T恤\",\n" +
            "        \"pic\": \"http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180615/5ad83a4fN6ff67ecd.jpg!cc_350x449.jpg\",\n" +
            "        \"name\": \"HLA海澜之家简约动物印花短袖T恤\",\n" +
            "        \"subTitle\": \"2018夏季新品微弹舒适新款短T男生 6月6日-6月20日，满300减30，参与互动赢百元礼券，立即分享赢大奖\",\n" +
            "        \"keywords\": \"\",\n" +
            "        \"price\": 98,\n" +
            "        \"sale\": 0,\n" +
            "        \"newStatus\": 1,\n" +
            "        \"recommandStatus\": 1,\n" +
            "        \"stock\": 100,\n" +
            "        \"promotionType\": 0,\n" +
            "        \"sort\": 0,\n" +
            "        \"attrValueList\": [\n" +
            "          {\n" +
            "            \"id\": 198,\n" +
            "            \"productAttributeId\": 24,\n" +
            "            \"value\": null,\n" +
            "            \"type\": 1,\n" +
            "            \"name\": \"商品编号\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"id\": 199,\n" +
            "            \"productAttributeId\": 25,\n" +
            "            \"value\": \"夏季\",\n" +
            "            \"type\": 1,\n" +
            "            \"name\": \"适用季节\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"id\": 200,\n" +
            "            \"productAttributeId\": 37,\n" +
            "            \"value\": \"青年\",\n" +
            "            \"type\": 1,\n" +
            "            \"name\": \"适用人群\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"id\": 201,\n" +
            "            \"productAttributeId\": 38,\n" +
            "            \"value\": \"2018年夏\",\n" +
            "            \"type\": 1,\n" +
            "            \"name\": \"上市时间\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"id\": 202,\n" +
            "            \"productAttributeId\": 39,\n" +
            "            \"value\": \"短袖\",\n" +
            "            \"type\": 1,\n" +
            "            \"name\": \"袖长\"\n" +
            "          }\n" +
            "        ]\n" +
            "      },\n" +
            "      {\n" +
            "        \"id\": 27,\n" +
            "        \"productSn\": \"7437788\",\n" +
            "        \"brandId\": 6,\n" +
            "        \"brandName\": \"小米\",\n" +
            "        \"productCategoryId\": 19,\n" +
            "        \"productCategoryName\": \"手机数码\",\n" +
            "        \"pic\": \"http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180615/xiaomi.jpg\",\n" +
            "        \"name\": \"小米8 全面屏游戏智能手机 6GB+64GB 黑色 全网通4G 双卡双待\",\n" +
            "        \"subTitle\": \"骁龙845处理器，红外人脸解锁，AI变焦双摄，AI语音助手小米6X低至1299，点击抢购\",\n" +
            "        \"keywords\": \"\",\n" +
            "        \"price\": 2699,\n" +
            "        \"sale\": 0,\n" +
            "        \"newStatus\": 1,\n" +
            "        \"recommandStatus\": 1,\n" +
            "        \"stock\": 100,\n" +
            "        \"promotionType\": 3,\n" +
            "        \"sort\": 0,\n" +
            "        \"attrValueList\": [\n" +
            "          {\n" +
            "            \"id\": 213,\n" +
            "            \"productAttributeId\": 43,\n" +
            "            \"value\": \"黑色,蓝色\",\n" +
            "            \"type\": 0,\n" +
            "            \"name\": \"颜色\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"id\": 214,\n" +
            "            \"productAttributeId\": 45,\n" +
            "            \"value\": \"5.8\",\n" +
            "            \"type\": 1,\n" +
            "            \"name\": \"屏幕尺寸\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"id\": 215,\n" +
            "            \"productAttributeId\": 46,\n" +
            "            \"value\": \"4G\",\n" +
            "            \"type\": 1,\n" +
            "            \"name\": \"网络\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"id\": 216,\n" +
            "            \"productAttributeId\": 47,\n" +
            "            \"value\": \"Android\",\n" +
            "            \"type\": 1,\n" +
            "            \"name\": \"系统\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"id\": 217,\n" +
            "            \"productAttributeId\": 48,\n" +
            "            \"value\": \"3000ml\",\n" +
            "            \"type\": 1,\n" +
            "            \"name\": \"电池容量\"\n" +
            "          }\n" +
            "        ]\n" +
            "      },\n" +
            "      {\n" +
            "        \"id\": 28,\n" +
            "        \"productSn\": \"7437789\",\n" +
            "        \"brandId\": 6,\n" +
            "        \"brandName\": \"小米\",\n" +
            "        \"productCategoryId\": 19,\n" +
            "        \"productCategoryName\": \"手机数码\",\n" +
            "        \"pic\": \"http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180615/5a9d248cN071f4959.jpg\",\n" +
            "        \"name\": \"小米 红米5A 全网通版 3GB+32GB 香槟金 移动联通电信4G手机 双卡双待\",\n" +
            "        \"subTitle\": \"8天超长待机，137g轻巧机身，高通骁龙处理器小米6X低至1299，点击抢购\",\n" +
            "        \"keywords\": \"\",\n" +
            "        \"price\": 649,\n" +
            "        \"sale\": 0,\n" +
            "        \"newStatus\": 1,\n" +
            "        \"recommandStatus\": 1,\n" +
            "        \"stock\": 100,\n" +
            "        \"promotionType\": 4,\n" +
            "        \"sort\": 0,\n" +
            "        \"attrValueList\": [\n" +
            "          {\n" +
            "            \"id\": 218,\n" +
            "            \"productAttributeId\": 43,\n" +
            "            \"value\": \"金色,银色\",\n" +
            "            \"type\": 0,\n" +
            "            \"name\": \"颜色\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"id\": 219,\n" +
            "            \"productAttributeId\": 45,\n" +
            "            \"value\": \"5.0\",\n" +
            "            \"type\": 1,\n" +
            "            \"name\": \"屏幕尺寸\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"id\": 220,\n" +
            "            \"productAttributeId\": 46,\n" +
            "            \"value\": \"4G\",\n" +
            "            \"type\": 1,\n" +
            "            \"name\": \"网络\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"id\": 221,\n" +
            "            \"productAttributeId\": 47,\n" +
            "            \"value\": \"Android\",\n" +
            "            \"type\": 1,\n" +
            "            \"name\": \"系统\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"id\": 222,\n" +
            "            \"productAttributeId\": 48,\n" +
            "            \"value\": \"2800ml\",\n" +
            "            \"type\": 1,\n" +
            "            \"name\": \"电池容量\"\n" +
            "          }\n" +
            "        ]\n" +
            "      },\n" +
            "      {\n" +
            "        \"id\": 29,\n" +
            "        \"productSn\": \"7437799\",\n" +
            "        \"brandId\": 51,\n" +
            "        \"brandName\": \"苹果\",\n" +
            "        \"productCategoryId\": 19,\n" +
            "        \"productCategoryName\": \"手机数码\",\n" +
            "        \"pic\": \"http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180615/5acc5248N6a5f81cd.jpg\",\n" +
            "        \"name\": \"Apple iPhone 8 Plus 64GB 红色特别版 移动联通电信4G手机\",\n" +
            "        \"subTitle\": \"【限时限量抢购】Apple产品年中狂欢节，好物尽享，美在智慧！速来 >> 勾选[保障服务][原厂保2年]，获得AppleCare+全方位服务计划，原厂延保售后无忧。\",\n" +
            "        \"keywords\": \"\",\n" +
            "        \"price\": 5499,\n" +
            "        \"sale\": 0,\n" +
            "        \"newStatus\": 1,\n" +
            "        \"recommandStatus\": 1,\n" +
            "        \"stock\": 100,\n" +
            "        \"promotionType\": 0,\n" +
            "        \"sort\": 0,\n" +
            "        \"attrValueList\": [\n" +
            "          {\n" +
            "            \"id\": 223,\n" +
            "            \"productAttributeId\": 43,\n" +
            "            \"value\": \"金色,银色\",\n" +
            "            \"type\": 0,\n" +
            "            \"name\": \"颜色\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"id\": 224,\n" +
            "            \"productAttributeId\": 45,\n" +
            "            \"value\": \"4.7\",\n" +
            "            \"type\": 1,\n" +
            "            \"name\": \"屏幕尺寸\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"id\": 225,\n" +
            "            \"productAttributeId\": 46,\n" +
            "            \"value\": \"4G\",\n" +
            "            \"type\": 1,\n" +
            "            \"name\": \"网络\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"id\": 226,\n" +
            "            \"productAttributeId\": 47,\n" +
            "            \"value\": \"IOS\",\n" +
            "            \"type\": 1,\n" +
            "            \"name\": \"系统\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"id\": 227,\n" +
            "            \"productAttributeId\": 48,\n" +
            "            \"value\": \"1960ml\",\n" +
            "            \"type\": 1,\n" +
            "            \"name\": \"电池容量\"\n" +
            "          }\n" +
            "        ]\n" +
            "      },\n" +
            "      {\n" +
            "        \"id\": 26,\n" +
            "        \"productSn\": \"6946605\",\n" +
            "        \"brandId\": 3,\n" +
            "        \"brandName\": \"华为\",\n" +
            "        \"productCategoryId\": 19,\n" +
            "        \"productCategoryName\": \"手机通讯\",\n" +
            "        \"pic\": \"http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180607/5ac1bf58Ndefaac16.jpg\",\n" +
            "        \"name\": \"华为 HUAWEI P20 \",\n" +
            "        \"subTitle\": \"AI智慧全面屏 6GB +64GB 亮黑色 全网通版 移动联通电信4G手机 双卡双待手机 双卡双待\",\n" +
            "        \"keywords\": \"\",\n" +
            "        \"price\": 3788,\n" +
            "        \"sale\": 0,\n" +
            "        \"newStatus\": 1,\n" +
            "        \"recommandStatus\": 1,\n" +
            "        \"stock\": 1000,\n" +
            "        \"promotionType\": 1,\n" +
            "        \"sort\": 100,\n" +
            "        \"attrValueList\": [\n" +
            "          {\n" +
            "            \"id\": 228,\n" +
            "            \"productAttributeId\": 43,\n" +
            "            \"value\": \"金色,银色\",\n" +
            "            \"type\": 0,\n" +
            "            \"name\": \"颜色\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"id\": 229,\n" +
            "            \"productAttributeId\": 45,\n" +
            "            \"value\": \"5.0\",\n" +
            "            \"type\": 1,\n" +
            "            \"name\": \"屏幕尺寸\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"id\": 230,\n" +
            "            \"productAttributeId\": 46,\n" +
            "            \"value\": \"4G\",\n" +
            "            \"type\": 1,\n" +
            "            \"name\": \"网络\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"id\": 231,\n" +
            "            \"productAttributeId\": 47,\n" +
            "            \"value\": \"Android\",\n" +
            "            \"type\": 1,\n" +
            "            \"name\": \"系统\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"id\": 232,\n" +
            "            \"productAttributeId\": 48,\n" +
            "            \"value\": \"3000\",\n" +
            "            \"type\": 1,\n" +
            "            \"name\": \"电池容量\"\n" +
            "          }\n" +
            "        ]\n" +
            "      },\n" +
            "      {\n" +
            "        \"id\": 32,\n" +
            "        \"productSn\": \"HNTBJ2E153A\",\n" +
            "        \"brandId\": 50,\n" +
            "        \"brandName\": \"海澜之家\",\n" +
            "        \"productCategoryId\": 8,\n" +
            "        \"productCategoryName\": \"T恤\",\n" +
            "        \"pic\": \"http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180615/5a51eb88Na4797877.jpg\",\n" +
            "        \"name\": \"HLA海澜之家短袖T恤男基础款\",\n" +
            "        \"subTitle\": \"HLA海澜之家短袖T恤男基础款简约圆领HNTBJ2E153A藏青(F3)175/92A(50)\",\n" +
            "        \"keywords\": \"\",\n" +
            "        \"price\": 68,\n" +
            "        \"sale\": 0,\n" +
            "        \"newStatus\": 0,\n" +
            "        \"recommandStatus\": 0,\n" +
            "        \"stock\": 100,\n" +
            "        \"promotionType\": 0,\n" +
            "        \"sort\": 0,\n" +
            "        \"attrValueList\": []\n" +
            "      },\n" +
            "      {\n" +
            "        \"id\": 33,\n" +
            "        \"productSn\": \"4609652\",\n" +
            "        \"brandId\": 6,\n" +
            "        \"brandName\": \"小米\",\n" +
            "        \"productCategoryId\": 35,\n" +
            "        \"productCategoryName\": \"手机数码\",\n" +
            "        \"pic\": \"http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180615/5b02804dN66004d73.jpg\",\n" +
            "        \"name\": \"小米（MI）小米电视4A \",\n" +
            "        \"subTitle\": \"小米（MI）小米电视4A 55英寸 L55M5-AZ/L55M5-AD 2GB+8GB HDR 4K超高清 人工智能网络液晶平板电视\",\n" +
            "        \"keywords\": \"\",\n" +
            "        \"price\": 2499,\n" +
            "        \"sale\": 0,\n" +
            "        \"newStatus\": 0,\n" +
            "        \"recommandStatus\": 0,\n" +
            "        \"stock\": 100,\n" +
            "        \"promotionType\": 0,\n" +
            "        \"sort\": 0,\n" +
            "        \"attrValueList\": []\n" +
            "      },\n" +
            "      {\n" +
            "        \"id\": 34,\n" +
            "        \"productSn\": \"4609660\",\n" +
            "        \"brandId\": 6,\n" +
            "        \"brandName\": \"小米\",\n" +
            "        \"productCategoryId\": 35,\n" +
            "        \"productCategoryName\": \"手机数码\",\n" +
            "        \"pic\": \"http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180615/5b028530N51eee7d4.jpg\",\n" +
            "        \"name\": \"小米（MI）小米电视4A 65英寸\",\n" +
            "        \"subTitle\": \" L65M5-AZ/L65M5-AD 2GB+8GB HDR 4K超高清 人工智能网络液晶平板电视\",\n" +
            "        \"keywords\": \"\",\n" +
            "        \"price\": 3999,\n" +
            "        \"sale\": 0,\n" +
            "        \"newStatus\": 0,\n" +
            "        \"recommandStatus\": 0,\n" +
            "        \"stock\": 100,\n" +
            "        \"promotionType\": 0,\n" +
            "        \"sort\": 0,\n" +
            "        \"attrValueList\": []\n" +
            "      },\n" +
            "      {\n" +
            "        \"id\": 35,\n" +
            "        \"productSn\": \"6799342\",\n" +
            "        \"brandId\": 58,\n" +
            "        \"brandName\": \"NIKE\",\n" +
            "        \"productCategoryId\": 29,\n" +
            "        \"productCategoryName\": \"男鞋\",\n" +
            "        \"pic\": \"http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180615/5b235bb9Nf606460b.jpg\",\n" +
            "        \"name\": \"耐克NIKE 男子 休闲鞋 ROSHE RUN 运动鞋 511881-010黑色41码\",\n" +
            "        \"subTitle\": \"耐克NIKE 男子 休闲鞋 ROSHE RUN 运动鞋 511881-010黑色41码\",\n" +
            "        \"keywords\": \"\",\n" +
            "        \"price\": 369,\n" +
            "        \"sale\": 0,\n" +
            "        \"newStatus\": 0,\n" +
            "        \"recommandStatus\": 0,\n" +
            "        \"stock\": 100,\n" +
            "        \"promotionType\": 0,\n" +
            "        \"sort\": 0,\n" +
            "        \"attrValueList\": []\n" +
            "      },\n" +
            "      {\n" +
            "        \"id\": 36,\n" +
            "        \"productSn\": \"6799345\",\n" +
            "        \"brandId\": 58,\n" +
            "        \"brandName\": \"NIKE\",\n" +
            "        \"productCategoryId\": 29,\n" +
            "        \"productCategoryName\": \"男鞋\",\n" +
            "        \"pic\": \"http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180615/5b19403eN9f0b3cb8.jpg\",\n" +
            "        \"name\": \"耐克NIKE 男子 气垫 休闲鞋 AIR MAX 90 ESSENTIAL 运动鞋 AJ1285-101白色41码\",\n" +
            "        \"subTitle\": \"耐克NIKE 男子 气垫 休闲鞋 AIR MAX 90 ESSENTIAL 运动鞋 AJ1285-101白色41码\",\n" +
            "        \"keywords\": \"\",\n" +
            "        \"price\": 499,\n" +
            "        \"sale\": 0,\n" +
            "        \"newStatus\": 1,\n" +
            "        \"recommandStatus\": 1,\n" +
            "        \"stock\": 100,\n" +
            "        \"promotionType\": 0,\n" +
            "        \"sort\": 0,\n" +
            "        \"attrValueList\": []\n" +
            "      }\n" +
            "    ]";

    public static String inits1 = "[\n" +
            "      {\n" +
            "        \"id\": 310,\n" +
            "        \"productSn\": \"HNTBJ2E080A\",\n" +
            "        \"brandId\": 50,\n" +
            "        \"brandName\": \"海澜之家\",\n" +
            "        \"productCategoryId\": 8,\n" +
            "        \"productCategoryName\": \"T恤\",\n" +
            "        \"pic\": \"http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180615/5ac98b64N70acd82f.jpg!cc_350x449.jpg\",\n" +
            "        \"name\": \"HLA海澜之家蓝灰花纹圆领针织布短袖T恤\",\n" +
            "        \"subTitle\": \"2018夏季新品短袖T恤男HNTBJ2E080A 蓝灰花纹80 175/92A/L80A 蓝灰花纹80 175/92A/L\",\n" +
            "        \"keywords\": \"\",\n" +
            "        \"price\": 98,\n" +
            "        \"sale\": 0,\n" +
            "        \"newStatus\": 0,\n" +
            "        \"recommandStatus\": 0,\n" +
            "        \"stock\": 100,\n" +
            "        \"promotionType\": 0,\n" +
            "        \"sort\": 0,\n" +
            "        \"attrValueList\": [\n" +
            "          {\n" +
            "            \"id\": 183,\n" +
            "            \"productAttributeId\": 24,\n" +
            "            \"value\": null,\n" +
            "            \"type\": 1,\n" +
            "            \"name\": \"商品编号\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"id\": 184,\n" +
            "            \"productAttributeId\": 25,\n" +
            "            \"value\": \"夏季\",\n" +
            "            \"type\": 1,\n" +
            "            \"name\": \"适用季节\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"id\": 185,\n" +
            "            \"productAttributeId\": 37,\n" +
            "            \"value\": \"青年\",\n" +
            "            \"type\": 1,\n" +
            "            \"name\": \"适用人群\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"id\": 186,\n" +
            "            \"productAttributeId\": 38,\n" +
            "            \"value\": \"2018年夏\",\n" +
            "            \"type\": 1,\n" +
            "            \"name\": \"上市时间\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"id\": 187,\n" +
            "            \"productAttributeId\": 39,\n" +
            "            \"value\": \"短袖\",\n" +
            "            \"type\": 1,\n" +
            "            \"name\": \"袖长\"\n" +
            "          }\n" +
            "        ]\n" +
            "      }\n" +
            "    ]";
    public static void main(String[] args) {
       List<Object> obs =   MyJsonUtil.parseObject(inits, List.class);

        List<EsProduct> esProducts = new ArrayList<>();


        for(Object ob:obs){
            EsProduct esProduct = MyJsonUtil.parseObject(ob.toString(), EsProduct.class);
            esProducts.add(esProduct);
        }

        System.out.println(esProducts);
    }




}
