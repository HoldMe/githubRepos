package boot.com.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.resource.ResourceUrlProvider;

/**
 * ��̬��Դ�ļ���ȡ����ȡ��ʽ��${urls.getForLookupPath(path)}
 * @author Administrator
 *
 */
@ControllerAdvice
public class StaticConfigController {

    @Autowired
    ResourceUrlProvider resourceUrlProvider;

    @ModelAttribute("urls")
    public ResourceUrlProvider urls() {
        return this.resourceUrlProvider;
    }

}
