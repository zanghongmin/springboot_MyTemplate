package top.zang.controller;


import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import top.zang.core.MyPage;
import top.zang.core.ReturnT;
import top.zang.mongodb.MemberReadHistory;
import top.zang.mongodb.MemberReadHistoryRepository;
import top.zang.rabbitMq.CancelOrderSender;
import top.zang.service.APIService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;

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

}