package org.trwib.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.trwib.config.ApplicationContext;
import org.trwib.model.QuestionResponse;

import javax.annotation.Resource;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

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

}
