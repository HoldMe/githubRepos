package boot.eden.filter;

import javax.servlet.http.HttpServletRequest;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

/**
* @author Eden
* @version 创建时间：2019年1月21日 下午3:57:02
* 请求服务鉴别
*/
public class AddServiceIdFilter extends ZuulFilter{

	/* (non-Javadoc)
	 * @see com.netflix.zuul.IZuulFilter#run()
	 */
	public Object run() {
		RequestContext context = new RequestContext();
		HttpServletRequest request = context.getRequest();
		if(request.getParameter("serviceId")==null){
			int serverPort = request.getServerPort();
			String serviceId = "service-null";
			switch(serverPort){
				case 8000:{
					serviceId = "service-api";
					break;
				}
				case 8001:{
					serviceId = "service-provider";
					break;
				}
				case 8002:{
					serviceId = "service-provider";
					break;
				}
			}
			context.set("serviceId", serviceId);
		}
		return context;
	}

	/* (non-Javadoc)
	 * @see com.netflix.zuul.IZuulFilter#shouldFilter()
	 */
	public boolean shouldFilter() {
		// TODO Auto-generated method stub
		RequestContext context = new RequestContext();
		return context.getRequest().getParameter("serviceId")!=null;
	}

	/* (non-Javadoc)
	 * @see com.netflix.zuul.ZuulFilter#filterOrder()
	 */
	@Override
	public int filterOrder() {
		// TODO Auto-generated method stub
		return 5;
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
