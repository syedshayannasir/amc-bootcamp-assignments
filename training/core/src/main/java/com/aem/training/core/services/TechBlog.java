package com.aem.training.core.services;

import com.aem.training.core.configurations.TechBlogConfig;
import com.aem.training.core.interfaces.BlogApi;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.metatype.annotations.Designate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component(service = BlogApi.class, property = {"version=1.0"}, immediate = true)
@Designate(ocd = TechBlogConfig.class)
public class TechBlog implements BlogApi {
    Logger logger  = LoggerFactory.getLogger(this.getClass());

    private TechBlogConfig techBlogConfig;

    @Activate
    public void activate(TechBlogConfig techBlogConfig){
        logger.info("TechBlog started");
        this.techBlogConfig = techBlogConfig;

        logger.info("Category: " + getBlogCategory());
        logger.info("Rank: " + getRank().toString());
    }

    public String getBlogCategory() {
        return techBlogConfig.bundleCategory();
    }

    public Integer getRank() {
        return techBlogConfig.rank();
    }
}
