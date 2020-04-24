package com.ttn.dam.core.models;

import com.day.cq.dam.api.Asset;
import com.day.cq.dam.api.Rendition;
import com.day.cq.dam.api.RenditionPicker;
import com.day.text.Text;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.Default;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import javax.annotation.PostConstruct;
import java.util.List;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class ImageResolutionResolver {

    @ValueMapValue @Default(values = "")
    private String fileReference;
    @ValueMapValue @Default(values = "original")
    private String assetResolution;
    @SlingObject
    ResourceResolver resourceResolver;

    private String path = "";

    @PostConstruct
    public void init() {

        Resource resource = resourceResolver.getResource(fileReference);
        Asset asset = null;
        if (resource != null) {
            asset = resource.adaptTo(Asset.class);
        }

        assetResolution = assetResolution.replace("x",".").trim();

        if (asset != null) {
            Rendition targetRendition = asset.getRendition(new RenditionPicker() {
                @Override
                public Rendition getRendition(Asset asset) {
                    List<Rendition> list  =asset.getRenditions();
                    for (Rendition rendition : list) {
                        if(Text.getName(rendition.getPath()).contains(assetResolution) && !assetResolution.equals("")){
                            return rendition;
                        }
                    }
                    return asset.getOriginal();
                }
            });
            path = targetRendition.getPath();
        }
    }

    public String getPath() {
        return path;
    }
}
