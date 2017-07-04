package mki.kehrwochenprojekt.mobilecomputing_sose17.Utility.ExclusionStrategies;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import mki.kehrwochenprojekt.mobilecomputing_sose17.Utility.KehrwochenUtility;


/**
 * Created by Alex on 26.06.2017.
 */

public class ExclusionStrategyFactory extends KehrwochenUtility {
        public static Gson build(final List<String> fieldExclusions, final List<Class<?>> classExclusions) {
            GsonBuilder b = new GsonBuilder();
            b.addSerializationExclusionStrategy(new ExclusionStrategy() {
                @Override
                public boolean shouldSkipField(FieldAttributes f) {
                    return fieldExclusions == null ? false : fieldExclusions.contains(f.getName());
                }

                @Override
                public boolean shouldSkipClass(Class<?> clazz) {
                    return classExclusions == null ? false : classExclusions.contains(clazz);
                }
            });
            return b.create();

        }
    }

