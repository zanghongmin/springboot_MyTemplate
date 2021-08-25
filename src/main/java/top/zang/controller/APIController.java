package top.zang.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.elasticsearch.common.lucene.search.function.FunctionScoreQuery;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.functionscore.FunctionScoreQueryBuilder;
import org.elasticsearch.index.query.functionscore.ScoreFunctionBuilders;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.zang.core.MyPage;
import top.zang.core.ReturnT;
import top.zang.es.ESinit;
import top.zang.es.EsProduct;
import top.zang.es.EsProductRepository;
import top.zang.mongodb.MemberReadHistory;
import top.zang.mongodb.MemberReadHistoryRepository;
import top.zang.rabbitMq.CancelOrderSender;
import top.zang.service.APIService;
import top.zang.util.MyJsonUtil;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Api(value = "测试API", description = "测试API")
@RestController
@RequestMapping("/")
public class APIController {
	@Resource
	public APIService apiService;
	@Resource
	CancelOrderSender cancelOrderSender;
	@Autowired
	private MemberReadHistoryRepository memberReadHistoryRepository;

	@Autowired
	@Qualifier("mongoTemplate")
	protected MongoTemplate mongoTemplate;
	@Autowired
	EsProductRepository esProductRepository;
	@Autowired
	private ElasticsearchRestTemplate elasticsearchRestTemplate;

    @ApiOperation(value = "测试API")
    @GetMapping(value="/")
	public ReturnT test(){
		return ReturnT.Success();
	}
	@ApiOperation(value = "rabbitMq测试")
	@GetMapping(value="/rabbitMq")
	public ReturnT rabbitMq(){
		cancelOrderSender.sendMessage(1111l,1000*6);
		return ReturnT.Success();
	}

	@ApiOperation(value = "MongoRepository测试")
	@GetMapping(value="/MongoRepository")
	public ReturnT MongoRepository(){
		MemberReadHistory memberReadHistory = new MemberReadHistory();
		memberReadHistory.setCreateTime(new Date());
		memberReadHistory.setMemberId(1l);
		memberReadHistory.setProductId(11l);
		memberReadHistory.setProductName("name商品");
		memberReadHistoryRepository.save(memberReadHistory);
		Pageable pageable = PageRequest.of(0, 20);
		Page<MemberReadHistory> memberReadHistorys = memberReadHistoryRepository.findByMemberIdOrderByCreateTimeDesc(1l,pageable);
		MyPage<MemberReadHistory> ss = MyPage.restPage(memberReadHistorys);
		return ReturnT.Success(ss);
	}

	@ApiOperation(value = "MongoTemplate测试")
	@GetMapping(value="/MongoTemplate")
	public ReturnT MongoTemplate(){
		Query query = Query.query(Criteria.where("memberId").is(1l));
		MemberReadHistory mdpage = mongoTemplate.findOne(query,MemberReadHistory.class,"memberReadHistory");
		return ReturnT.Success(mdpage);
	}

	@ApiOperation(value = "EsProductRepository测试")
	@GetMapping(value="/EsProductRepository")
	public ReturnT EsProductRepository(){
		List<Object> obs =   MyJsonUtil.parseObject(ESinit.inits1, List.class);

		List<EsProduct> esProducts = new ArrayList<>();
		for(Object ob:obs){
			EsProduct esProduct = MyJsonUtil.parseObject(ob.toString(), EsProduct.class);
			esProducts.add(esProduct);
		}
		esProductRepository.saveAll(esProducts);

		Iterable<EsProduct> esProducts1 = esProductRepository.findAll();
//		String keyword = "海澜之家";
//		Pageable pageable = PageRequest.of(0, 5);
//		Page<EsProduct> pss = esProductRepository.findByNameOrSubTitleOrKeywords(keyword, keyword, keyword, pageable);
		return ReturnT.Success(esProducts1);
	}

	@ApiOperation(value = "elasticsearchRestTemplate测试")
	@GetMapping(value="/elasticsearchRestTemplate")
	public ReturnT elasticsearchRestTemplate(){

		String keyword = "海";
		Long brandId = 50l;
		Long productCategoryId =null;
		Integer pageNum = 0;
		Integer pageSize = 5;
		Integer sort = 1;
		Pageable pageable = PageRequest.of(pageNum, pageSize);
		NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
		//分页
		nativeSearchQueryBuilder.withPageable(pageable);
		//过滤
		if (brandId != null || productCategoryId != null) {
			BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
			if (brandId != null) {
				boolQueryBuilder.must(QueryBuilders.termQuery("brandId", brandId));
			}
			if (productCategoryId != null) {
				boolQueryBuilder.must(QueryBuilders.termQuery("productCategoryId", productCategoryId));
			}
			nativeSearchQueryBuilder.withFilter(boolQueryBuilder);
		}
		//搜索
		if (StringUtils.isEmpty(keyword)) {
			nativeSearchQueryBuilder.withQuery(QueryBuilders.matchAllQuery());
		} else {
			List<FunctionScoreQueryBuilder.FilterFunctionBuilder> filterFunctionBuilders = new ArrayList<>();
			filterFunctionBuilders.add(new FunctionScoreQueryBuilder.FilterFunctionBuilder(QueryBuilders.matchQuery("name", keyword),
					ScoreFunctionBuilders.weightFactorFunction(10)));
			filterFunctionBuilders.add(new FunctionScoreQueryBuilder.FilterFunctionBuilder(QueryBuilders.matchQuery("subTitle", keyword),
					ScoreFunctionBuilders.weightFactorFunction(5)));
			filterFunctionBuilders.add(new FunctionScoreQueryBuilder.FilterFunctionBuilder(QueryBuilders.matchQuery("keywords", keyword),
					ScoreFunctionBuilders.weightFactorFunction(2)));
			FunctionScoreQueryBuilder.FilterFunctionBuilder[] builders = new FunctionScoreQueryBuilder.FilterFunctionBuilder[filterFunctionBuilders.size()];
			filterFunctionBuilders.toArray(builders);
			FunctionScoreQueryBuilder functionScoreQueryBuilder = QueryBuilders.functionScoreQuery(builders)
					.scoreMode(FunctionScoreQuery.ScoreMode.SUM)
					.setMinScore(2);
			nativeSearchQueryBuilder.withQuery(functionScoreQueryBuilder);
		}
		//排序
		if(sort==1){
			//按新品从新到旧
			nativeSearchQueryBuilder.withSort(SortBuilders.fieldSort("id").order(SortOrder.DESC));
		}else if(sort==2){
			//按销量从高到低
			nativeSearchQueryBuilder.withSort(SortBuilders.fieldSort("sale").order(SortOrder.DESC));
		}else if(sort==3){
			//按价格从低到高
			nativeSearchQueryBuilder.withSort(SortBuilders.fieldSort("price").order(SortOrder.ASC));
		}else if(sort==4){
			//按价格从高到低
			nativeSearchQueryBuilder.withSort(SortBuilders.fieldSort("price").order(SortOrder.DESC));
		}else{
			//按相关度
			nativeSearchQueryBuilder.withSort(SortBuilders.scoreSort().order(SortOrder.DESC));
		}
		nativeSearchQueryBuilder.withSort(SortBuilders.scoreSort().order(SortOrder.DESC));
		NativeSearchQuery searchQuery = nativeSearchQueryBuilder.build();
		System.out.println(searchQuery.getQuery().toString());
		SearchHits<EsProduct> searchHits = elasticsearchRestTemplate.search(searchQuery, EsProduct.class);
		if(searchHits.getTotalHits()<=0){
			return ReturnT.Success();
		}
		List<EsProduct> searchProductList = searchHits.stream().map(SearchHit::getContent).collect(Collectors.toList());
		return ReturnT.Success(new PageImpl<>(searchProductList,pageable,searchHits.getTotalHits()));
	}

}