package com.gajaharan.core.models;

import com.adobe.cq.sightly.WCMBindings;
import org.apache.sling.api.resource.ValueMap;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.script.Bindings;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import static org.mockito.Mockito.when;

/**
 * Created by gsatkunanandan on 20/04/2018.
 */

@RunWith(MockitoJUnitRunner.class)
public class TouchMultComponentTest {

    @Mock
    private Bindings bindings;

    @Mock
    private ValueMap valueMap;

    @InjectMocks
    private TouchMultiComponent touchMultiComponent;

    private static final String MULTIFIELD_KEY = "multifieldtest";
    private static final String[] MULTIFIELD_PROPERTY = {"\"title\":\"test1\"","\"richtex\":\"test1\"","\"file\":\"\""};


    @Before
    public void init() throws URISyntaxException, IOException {
        when(bindings.get(WCMBindings.PROPERTIES)).thenReturn(valueMap);
        when(valueMap.get(MULTIFIELD_KEY, new String[3])).thenReturn(MULTIFIELD_PROPERTY);
    }

    @Test
    public void shouldGetThreeMultiFieldProperties() throws Exception {

        touchMultiComponent.activate();

        List<TouchMultiFieldBean>  touchMultiFieldList = touchMultiComponent.getMultiFieldItems();
        System.out.println(touchMultiFieldList);

    }

}
