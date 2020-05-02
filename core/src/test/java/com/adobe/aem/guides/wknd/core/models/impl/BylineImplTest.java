package com.adobe.aem.guides.wknd.core.models.impl;

import org.junit.jupiter.api.Nested;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.Mock;

import com.adobe.aem.guides.wknd.core.models.Byline;
import com.adobe.cq.wcm.core.components.models.Image;

import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;

import org.apache.sling.models.factory.ModelFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.apache.sling.api.resource.Resource;
import com.google.common.collect.ImmutableList;


import java.util.Collections;
import java.util.List;


@ExtendWith({AemContextExtension.class, MockitoExtension.class})
class BylineImplTest {

    private final AemContext ctx = new AemContext();

    @Mock
    private Image image;

    @Mock
    private ModelFactory modelFactory;

    @BeforeEach
    void setUp() {
        ctx.addModelsForClasses(BylineImpl.class);

        ctx.load().json("/com/adobe/aem/guides/wknd/core/models/impl/BylineImplTest.json", "/content");

        lenient().when(modelFactory.getModelFromWrappedRequest(eq(ctx.request()), any(Resource.class), eq(Image.class)))
                .thenReturn(image);

        ctx.registerService(ModelFactory.class, modelFactory, org.osgi.framework.Constants.SERVICE_RANKING,
                Integer.MAX_VALUE);
    }

    @Nested
    class GetName{
        @Test
        void should_ReturnName_When_NameGiven() {
            final String expected = "Jane Doe";

            ctx.currentResource("/content/byline");
            Byline byline = ctx.request().adaptTo(Byline.class);

            String actual = byline.getName();

            assertEquals(expected, actual);
        }
    }

    @Nested
    class GetOccupations {
        @Test
        void should_ReturnSortedOccupations_When_OccupationsListNotEmpty() {
            List<String> expected = new ImmutableList.Builder<String>()
                    .add("Blogger")
                    .add("Photographer")
                    .add("YouTuber")
                    .build();
            ctx.currentResource("/content/byline");
            Byline byline = ctx.request().adaptTo(Byline.class);

            List<String> actual =  byline.getOccupations();

            assertEquals(expected, actual);
        }
    }

    @Nested
    class IsEmpty{
        @Test
        void should_ReturnTrue_When_NameOccupationsAndImageIsAbsent() {

            ctx.currentResource("/content/empty");
            Byline byline = ctx.request().adaptTo(Byline.class);

            assertTrue(byline.isEmpty());
        }

        @Test
        public void should_ReturnTrue_When_NameIsAbsent() {
            ctx.currentResource("/content/without-name");

            Byline byline = ctx.request().adaptTo(Byline.class);

            assertTrue(byline.isEmpty());
        }

        @Test
        public void should_ReturnTrue_When_OccupationsIsAbsent() {
            ctx.currentResource("/content/without-occupations");

            Byline byline = ctx.request().adaptTo(Byline.class);

            assertTrue(byline.isEmpty());
        }

        @Test
        public void testIsEmpty_WithEmptyArrayOfOccupations() {
            ctx.currentResource("/content/without-occupations-empty-array");

            Byline byline = ctx.request().adaptTo(Byline.class);

            assertTrue(byline.isEmpty());
        }

        @Test
        public void should_ReturnTrue_When_OccupationsListIsEmpty() {
            List<String> expected = Collections.emptyList();

            ctx.currentResource("/content/empty");
            Byline byline = ctx.request().adaptTo(Byline.class);

            List<String> actual = byline.getOccupations();

            assertEquals(expected, actual);
        }

        @Test
        public void should_ReturnTrue_When_ImageIsNull() {
            ctx.currentResource("/content/byline");

            lenient().when(modelFactory.getModelFromWrappedRequest(eq(ctx.request()),
                    any(Resource.class),
                    eq(Image.class))).thenReturn(null);

            Byline byline = ctx.request().adaptTo(Byline.class);

            assertTrue(byline.isEmpty());
        }

        @Test
        public void should_ReturnTrue_When_ImageSRCIsEmpty() {
            ctx.currentResource("/content/byline");

            when(image.getSrc()).thenReturn("");

            Byline byline = ctx.request().adaptTo(Byline.class);

            assertTrue(byline.isEmpty());
        }

        @Test
        public void should_ReturnFalse_When_NameOccupationsAndImageArePresent() {
            ctx.currentResource("/content/byline");
            when(image.getSrc()).thenReturn("/content/bio.png");

            Byline byline = ctx.request().adaptTo(Byline.class);

            assertFalse(byline.isEmpty());
        }
    }
}