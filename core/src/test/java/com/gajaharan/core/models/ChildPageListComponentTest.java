package com.gajaharan.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.jcr.Node;
import javax.jcr.Property;
import javax.jcr.RepositoryException;
import javax.script.Bindings;
import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

/**
 * Created by gsatkunanandan on 20/04/2018.
 */
@RunWith(MockitoJUnitRunner.class)
public class ChildPageListComponentTest {

    private static final String JCR_PRIMARY_TYPE = "jcr:primaryType";
    private static final String JCR_PAGE_TYPE = "cq:Page";
    private static final String FIRST_CHILD_PAGE = "test";
    private static final String SELECTED_PATH = "/content/AEMTest62App";

    @Mock
    private Resource resource;

    @Mock
    private ResourceResolver resourceResolver;

    @Mock
    private Iterator<Resource> items;

    @Mock
    private Bindings bindings;

    @Mock
    private Node node;

    @Mock
    private Node jcrContentNode;

    @Mock
    private Property property;

    @InjectMocks
    private ChildPageListComponent childPageListComponent;

    @Before
    public void init() throws RepositoryException {
        when(childPageListComponent.get("selectedPath", String.class)).thenReturn(SELECTED_PATH);
        when(bindings.get("selectedPath")).thenReturn(SELECTED_PATH);

        when(resource.getResourceResolver()).thenReturn(resourceResolver);
        when(resourceResolver.getResource(SELECTED_PATH)).thenReturn(resource);
        when(resource.listChildren()).thenReturn(items);
        when(items.hasNext()).thenReturn(true);
        when(items.next()).thenReturn(resource);
        when(resource.adaptTo(Node.class)).thenReturn(node);



    }

    @Test
    public void getFirstChildInSelectedPath() throws Exception {
        when(node.getProperty(JCR_PRIMARY_TYPE)).thenReturn(property);
        when(property.getString()).thenReturn(JCR_PAGE_TYPE);
        when(resource.getName()).thenReturn(FIRST_CHILD_PAGE);
        when(items.hasNext()).thenReturn(false);

        childPageListComponent.activate();

        List<String> list = childPageListComponent.getListChildren();

        System.out.println(list.size());
        //assertEquals(1, list.size());
    }
}
