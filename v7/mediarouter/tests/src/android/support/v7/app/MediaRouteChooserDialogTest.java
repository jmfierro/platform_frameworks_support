/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package android.support.v7.app;

import static org.junit.Assert.assertTrue;

import android.support.test.filters.SmallTest;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.media.MediaRouter.RouteInfo;
import android.support.v7.media.TestUtils;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
@SmallTest
public class MediaRouteChooserDialogTest {
    private MediaRouteChooserDialog.RouteComparator mComparator;

    @Before
    public void setup() {
        mComparator = new MediaRouteChooserDialog.RouteComparator();
    }

    @Test
    public void testRouteComparatorWithSameRouteName() {
        RouteInfo routeInfo1 = TestUtils.createRouteInfo("ROUTE_ID_1", "ROUTE_NAME_1");
        RouteInfo routeInfo2 = TestUtils.createRouteInfo("ROUTE_ID_2", "ROUTE_NAME_1");
        int result = mComparator.compare(routeInfo1, routeInfo2);
        assertTrue(result == 0);
    }

    @Test
    public void testRouteComparatorWithCommonComparison() {
        RouteInfo routeInfo1 = TestUtils.createRouteInfo("ROUTE_ID_1", "Route ABC");
        RouteInfo routeInfo2 = TestUtils.createRouteInfo("ROUTE_ID_2", "Route XYZ");
        int result = mComparator.compare(routeInfo1, routeInfo2);
        assertTrue(result < 0);
    }

    @Test
    public void testRouteComparatorWithCaseInsensitiveComparison() {
        RouteInfo routeInfo1 = TestUtils.createRouteInfo("ROUTE_ID_1", "living room abc");
        RouteInfo routeInfo2 = TestUtils.createRouteInfo("ROUTE_ID_2", "LIVING ROOM XYZ");
        int result1 = mComparator.compare(routeInfo1, routeInfo2);
        assertTrue(result1 < 0);

        RouteInfo routeInfo3 = TestUtils.createRouteInfo("ROUTE_ID_3", "LIVING ROOM ABC");
        RouteInfo routeInfo4 = TestUtils.createRouteInfo("ROUTE_ID_4", "living room xyz");
        int result2 = mComparator.compare(routeInfo3, routeInfo4);
        assertTrue(result2 < 0);
    }
}
