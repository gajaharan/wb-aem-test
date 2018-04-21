package com.gajaharan.core.models;

import com.adobe.cq.sightly.WCMUsePojo;
import org.apache.sling.commons.json.JSONException;
import org.apache.sling.commons.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class TouchMultiComponent extends WCMUsePojo {

    private static final Logger LOGGER = LoggerFactory.getLogger(TouchMultiComponent.class);
    private List<TouchMultiFieldBean> multiFieldItems = new ArrayList<>();

    @Override
    public void activate() throws Exception {
        setMultiFieldItems();
    }

    private List<TouchMultiFieldBean> setMultiFieldItems() {

        JSONObject jObject;
        try{
            String[] itemsProps = getProperties().get("multifieldtest", new String[0]);


            if (itemsProps == null) {
                LOGGER.info("There are no multifield properties") ;
            }

            if (itemsProps != null) {
                for (int i = 0; i < itemsProps.length; i++) {

                    jObject = new JSONObject(itemsProps[i]);
                    TouchMultiFieldBean menuItem = new TouchMultiFieldBean();

                    String title = jObject.getString("title");
                    String text = jObject.getString("text");
                    String image = jObject.getString("file");

                    menuItem.setTitle(title);
                    menuItem.setText(text);
                    menuItem.setImage(image);
                    multiFieldItems.add(menuItem);
                }
            }
        }catch(JSONException ex){
            LOGGER.error("Exception while multifield data {}", ex.getMessage(), ex);
        }
        return multiFieldItems;
    }

    public List<TouchMultiFieldBean> getMultiFieldItems() {
        return multiFieldItems;
    }
}
