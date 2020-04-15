package com.ttn.training.core;

import com.adobe.cq.sightly.WCMUsePojo;
import javax.jcr.Node;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;

public class URLHandler extends WCMUsePojo {

    private String prop;

    @Override
    public void activate() throws Exception {
        ResourceResolver resourceResolver = getResource().getResourceResolver();
        prop = resourceResolver.getParent(getResource()).getPath();
    }

    public String getProp() {
        return prop;
    }
}
