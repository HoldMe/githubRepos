package boot.eden.filter;

import org.springframework.context.annotation.Bean;

/**
* @author Eden
* @version 创建时间：2019年1月21日 下午3:18:48
* gateway管理
*/
public class GateWayFileterManager {

	@Bean
	public AddServiceIdFilter addServiceIdFilter(){
		return new AddServiceIdFilter();
	}
	
	@Bean
	public QueryRequestParamFilter queryRequestParamFilter(){
		return new QueryRequestParamFilter();
	}
}
