package boot.eden.filter;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

/**
* @author Eden
* @version 创建时间：2019年1月21日 下午3:20:08
* 请求查看
*/
public class QueryRequestParamFilter extends ZuulFilter{
	
	Log LOG = LogFactory.getLog(QueryRequestParamFilter.class);

	/* (non-Javadoc)
	 * @see com.netflix.zuul.IZuulFilter#run()
	 */
	public Object run() {
		RequestContext context = new RequestContext();
		HttpServletRequest request = context.getRequest();
		LOG.debug("请求路径======================="+request.getRequestURI());
		LOG.debug("请求服务======================="+request.getParameter("serviceId"));
		Map<String, List<String>> requestQueryParams = context.getRequestQueryParams();
		if(requestQueryParams!=null){
			Set<String> keySet = requestQueryParams.keySet();
			if(keySet!=null && keySet.size()>0){
				Iterator<String> iterator = keySet.iterator();
				while(iterator.hasNext()){
					LOG.debug("请求参数======================="+requestQueryParams.get(iterator.next()));
				}
			}
		}
		return context;
	}

	/* (non-Javadoc)
	 * @see com.netflix.zuul.IZuulFilter#shouldFilter()
	 */
	public boolean shouldFilter() {
		RequestContext context = new RequestContext();
		return context.getRequest().getParameter("token")!=null;
	}

	/* (non-Javadoc)
	 * @see com.netflix.zuul.ZuulFilter#filterOrder()
	 */
	@Override
	public int filterOrder() {
		// TODO Auto-generated method stub
		return 5 - 1;
	}

	/* (non-Javadoc)
	 * @see com.netflix.zuul.ZuulFilter#filterType()
	 */
	@Override
	public String filterType() {
		// TODO Auto-generated method stub
		return "post";
	}

}
