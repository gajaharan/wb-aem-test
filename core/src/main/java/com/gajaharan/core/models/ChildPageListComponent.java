package com.gajaharan.core.models;

import com.adobe.cq.sightly.WCMUsePojo;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jcr.Node;
import javax.jcr.Property;
import javax.jcr.RepositoryException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by gsatkunanandan on 20/04/2018.
 */
public class ChildPageListComponent extends WCMUsePojo {
    private static final Logger LOG = LoggerFactory.getLogger(ChildPageListComponent.class);
    private static final String JCR_PRIMARY_TYPE = "jcr:primaryType";
    private static final String JCR_PAGE_TYPE = "cq:Page";

    private String selectedPath = "";

    private List<String> childrenList = new ArrayList<>();

    @Override
    public void activate() {

        this.selectedPath = get("selectedPath", String.class);
    }

    public List<String> getListChildren() {
        childrenList.clear();
        if (this.selectedPath == null || this.selectedPath.isEmpty()) {
            return childrenList;
        }
        final ResourceResolver resolver = getCurrentResourceResolver();

        final Resource resource = resolver.getResource(this.selectedPath);

        try {
            Iterator<Resource> items = resource.listChildren();
            while (items.hasNext()) {
                Resource childResource = items.next();
                Node node = childResource.adaptTo(Node.class);
                Property property = node.getProperty(JCR_PRIMARY_TYPE);
                String primaryType = property.getString();
                if (primaryType.equals(JCR_PAGE_TYPE)) {
                    childrenList.add(childResource.getName());
                }
            }
        } catch (RepositoryException e) {
            LOG.error("Unable to find the child pages for " + this.selectedPath, e.getMessage());
        }

        return childrenList;
    }

    public ResourceResolver getCurrentResourceResolver() {
        return getResource().getResourceResolver();
    }
}
