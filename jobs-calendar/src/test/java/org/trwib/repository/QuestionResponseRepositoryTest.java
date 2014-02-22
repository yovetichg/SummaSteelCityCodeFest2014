package org.trwib.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.trwib.config.ApplicationContext;
import org.trwib.model.EventCategory;
import org.trwib.model.Question;
import org.trwib.model.QuestionResponse;

import javax.annotation.Resource;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;

/** @author bgray */
@ContextConfiguration(classes = ApplicationContext.class, loader = AnnotationConfigContextLoader.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class QuestionResponseRepositoryTest {

    private static final Logger LOG = LoggerFactory.getLogger(QuestionResponseRepositoryTest.class);

    @Resource
    private QuestionResponseRepository questionResponseRepository;

    @Test
    public void getFirst() throws Exception {
        QuestionResponse first = questionResponseRepository.getFirst();
        LOG.info("first: {}", first);
        assertNotNull("first is null", first);
        assertEquals(Long.valueOf(1L), first.getId());
        assertEquals("Are you looking for a job?", first.getDescription());
    }

    @Test
    public void getNextYesResponse() throws Exception {
        QuestionResponse next = questionResponseRepository.getNextYesResponse(3L);
        LOG.info("next: {}", next);
        assertNotNull("next is null", next);
        assertEquals(Long.valueOf(4L), next.getId());
        assertTrue(next instanceof Question);
        assertEquals("Have you successfully completed an interview in the last 24 months that resulted in a job offer?", next.getDescription());
    }

    @Test
    public void getNextNoResponse() throws Exception {
        QuestionResponse next = questionResponseRepository.getNextNoResponse(5L);
        LOG.info("next: {}", next);
        assertNotNull("next is null", next);
        assertEquals(Long.valueOf(13L), next.getId());
        assertTrue(next instanceof EventCategory);
        assertEquals("Hiring/Recruitment", next.getDescription());
    }

}
